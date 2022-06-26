package com.badminton.controller;

import java.io.File;
import java.io.FileFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.badminton.broadcaster.Doad;
import com.badminton.containers.Configurations;
import com.badminton.containers.Scene;
import com.badminton.model.*;
import com.badminton.service.BadmintonService;
import com.badminton.util.BadmintonUtil;

import net.sf.json.JSONObject;

@Controller
public class IndexController 
{
	@Autowired
	BadmintonService badmintonService;
	public static Configurations session_Configurations;
	public static BadmintonMatch session_match;
	public static String selectedmatch;
	//public static EventFile session_event_file;
	public static Socket session_socket;
	public static Doad this_doad;
	public static PrintWriter print_writer;
	//string static png_extension = '.png';
	String session_selected_broadcaster,session_selected_ip,which_graphics_onscreen,viz_scene_path,stat;
	int session_selected_port ;
	boolean is_ScoreBug_on_Screen = false;
	
	@RequestMapping(value = {"/","/initialise"}, method={RequestMethod.GET,RequestMethod.POST}) 
	public String initialisePage(ModelMap model) throws JAXBException, IOException 
	{
		model.addAttribute("session_viz_scenes", new File(BadmintonUtil.BADMINTON_DIRECTORY + BadmintonUtil.SCENES_DIRECTORY).listFiles(new FileFilter() {
			@Override
		    public boolean accept(File pathname) {
		        String name = pathname.getName().toLowerCase();
		        return name.endsWith(".via") && pathname.isFile();
		    }
		}));
		model.addAttribute("match_files", new File(BadmintonUtil.BADMINTON_DIRECTORY + BadmintonUtil.MATCHES_DIRECTORY).listFiles(new FileFilter() {
			@Override
		    public boolean accept(File pathname) {
		        String name = pathname.getName().toLowerCase();
		        return name.endsWith(".xml") && pathname.isFile();
		    }
		}));
		
		if(new File(BadmintonUtil.BADMINTON_DIRECTORY + BadmintonUtil.CONFIGURATIONS_DIRECTORY + BadmintonUtil.OUTPUT_XML).exists()) {
			session_Configurations = (Configurations)JAXBContext.newInstance(Configurations.class).createUnmarshaller().unmarshal(
					new File(BadmintonUtil.BADMINTON_DIRECTORY + BadmintonUtil.CONFIGURATIONS_DIRECTORY + BadmintonUtil.OUTPUT_XML));
		} else {
			session_Configurations = new Configurations();
			JAXBContext.newInstance(Configurations.class).createMarshaller().marshal(session_Configurations, 
					new File(BadmintonUtil.BADMINTON_DIRECTORY + BadmintonUtil.CONFIGURATIONS_DIRECTORY + BadmintonUtil.OUTPUT_XML));
		}
	
		model.addAttribute("session_Configurations",session_Configurations);
		
		return "initialise";
	
	}

	@RequestMapping(value = {"/output"}, method={RequestMethod.GET,RequestMethod.POST}) 
	public String outputPage(ModelMap model,
			@RequestParam(value = "select_broadcaster", required = false, defaultValue = "") String select_broadcaster,
			@RequestParam(value = "selectedMatch", required = false, defaultValue = "") String selectmatch,
			@RequestParam(value = "vizIPAddress", required = false, defaultValue = "") String vizIPAddresss,
			@RequestParam(value = "vizPortNumber", required = false, defaultValue = "") int vizPortNumber,
			@RequestParam(value = "vizScene", required = false, defaultValue = "") String vizScene) 
					throws UnknownHostException, IOException, JAXBException, IllegalAccessException, InvocationTargetException 
	{
		which_graphics_onscreen = "";
		stat = "";
		selectedmatch = selectmatch;
		is_ScoreBug_on_Screen = false;
		this_doad = new Doad();
		session_selected_broadcaster = select_broadcaster;
		session_selected_port = vizPortNumber;
		//session_selected_ip = String.valueOf(vizIPAddress);
		
		session_socket = new Socket(vizIPAddresss, Integer.valueOf(vizPortNumber));
		print_writer = new PrintWriter(session_socket.getOutputStream(), true);
		session_Configurations = new Configurations(selectedmatch, select_broadcaster, vizIPAddresss, vizPortNumber, vizScene);
		
		JAXBContext.newInstance(Configurations.class).createMarshaller().marshal(session_Configurations, 
				new File(BadmintonUtil.BADMINTON_DIRECTORY + BadmintonUtil.CONFIGURATIONS_DIRECTORY + BadmintonUtil.OUTPUT_XML));

		/*session_event_file = (EventFile) JAXBContext.newInstance(EventFile.class).createUnmarshaller().unmarshal(
				new File(CricketUtil.CRICKET_DIRECTORY + CricketUtil.EVENT_DIRECTORY + selectedMatch));*/
		
		session_match = (BadmintonMatch) JAXBContext.newInstance(BadmintonMatch.class).createUnmarshaller().unmarshal(
				new File(BadmintonUtil.BADMINTON_DIRECTORY + BadmintonUtil.MATCHES_DIRECTORY + selectedmatch));
		//session_match.setMatchFileName(selectedMatch);
		session_match.setMatch_file_timestamp(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()));
		
		//session_match.setEvents(session_event_file.getEvents());
		
		model.addAttribute("session_match", session_match);
		model.addAttribute("session_selected_broadcaster", session_selected_broadcaster);
		model.addAttribute("session_selected_port", session_selected_port);
		//model.addAttribute("session_selected_ip", session_selected_ip);
		
		return "output";
	}

	@RequestMapping(value = {"/processBadmintonProcedures"}, method={RequestMethod.GET,RequestMethod.POST})    
	public @ResponseBody String processBadmintonProcedures(
			@RequestParam(value = "whatToProcess", required = false, defaultValue = "") String whatToProcess,
			@RequestParam(value = "valueToProcess", required = false, defaultValue = "") String valueToProcess)  
					throws IOException, IllegalAccessException, InvocationTargetException, JAXBException, InterruptedException 
	{
		
		
		switch (whatToProcess.toUpperCase()) {
		
		case "READ-MATCH-AND-POPULATE":
			System.out.println("name= " + selectedmatch);
			
			if(!valueToProcess.equalsIgnoreCase(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(
					new File(BadmintonUtil.BADMINTON_DIRECTORY + BadmintonUtil.MATCHES_DIRECTORY + session_match.getMatch_file_name()).lastModified())))
			{
				session_match = populateMatchVariables((BadmintonMatch) JAXBContext.newInstance(BadmintonMatch.class).createUnmarshaller().unmarshal(
						new File(BadmintonUtil.BADMINTON_DIRECTORY + BadmintonUtil.MATCHES_DIRECTORY + selectedmatch)));
				
				session_match.setMatch_file_timestamp(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(
						new File(BadmintonUtil.BADMINTON_DIRECTORY + BadmintonUtil.MATCHES_DIRECTORY + session_match.getMatch_file_name()).lastModified()));
				//session_event_file = (EventFile) JAXBContext.newInstance(EventFile.class).createUnmarshaller().unmarshal(
					//	new File(CricketUtil.CRICKET_DIRECTORY + CricketUtil.EVENT_DIRECTORY + session_match.getMatchFileName()));
				//System.out.println("name= " + session_match.getMatch_file_name()).lastModified());
				//session_match.setEvents(session_event_file.getEvents());
				System.out.println("which_graphics_onscreen= " + which_graphics_onscreen);
				switch(which_graphics_onscreen) {
				case "SCOREBUG":
					this_doad.populateScoreBugName(true,print_writer, viz_scene_path, session_match, session_selected_broadcaster);
					break;
				
					
				}
				//System.out.println("Inning ="+ whichInning);
				return JSONObject.fromObject(session_match).toString();
			}
			else {
				return JSONObject.fromObject(null).toString();
			}
		
		case "SCOREBUG_GRAPHICS-OPTIONS": 
			return JSONObject.fromObject(session_match).toString();
			
		case "SCOREBUGSTAT_GRAPHICS-OPTIONS":
			return JSONObject.fromObject(session_match).toString();
			
		case "POPULATE-SCOREBUG": case "POPULATE-SCOREBUGSTATS": case "POPULATE-SINGLE-L3-MATCHID": case "POPULATE-SINGLE-FF-MATCHID": case "POPULATE-DOUBLE-L3_MATCHID":
		case "POPULATE-DOUBLE-FF-MATCHID": case "POPULATE-L3-TIEID": case "POPULATE-FF-TIEID": case "POPULATE-SIDES":
			switch(session_selected_broadcaster) {
			case "DOAD_In_House_Everest":
				switch(whatToProcess.toUpperCase()) {
				case "POPULATE-SCOREBUGSTATS":
					break;
				default:
					viz_scene_path = valueToProcess;
					new Scene(viz_scene_path).scene_load(print_writer,session_selected_broadcaster,viz_scene_path);
					break;
				}
				switch(whatToProcess.toUpperCase()) {
				case "POPULATE-SCOREBUG":
					this_doad.populateScoreBug(print_writer, viz_scene_path, stat , session_match, session_selected_broadcaster);
					
					break;
				case "POPULATE-SINGLE-L3-MATCHID":
					this_doad.populateSingleL3MatchId(print_writer, viz_scene_path, session_match, session_selected_broadcaster);
					break;
				case "POPULATE-SINGLE-FF-MATCHID":
					this_doad.populateSingleFFMatchId(print_writer, viz_scene_path, session_match, session_selected_broadcaster);
					break;
				case "POPULATE-DOUBLE-L3_MATCHID":
					this_doad.populateDoubleL3MatchId(print_writer, viz_scene_path, session_match, session_selected_broadcaster);
					break;
				case "POPULATE-DOUBLE-FF-MATCHID":
					this_doad.populateDoubleFFMatchId(print_writer, viz_scene_path, session_match, session_selected_broadcaster);
					break;
				case "POPULATE-L3-TIEID":
					this_doad.populateLtTieId(print_writer, viz_scene_path, session_match, session_selected_broadcaster);
					break;
				case "POPULATE-FF-TIEID":
					this_doad.populateFFTieId(print_writer, viz_scene_path, session_match, session_selected_broadcaster);
					break;
				case "POPULATE-SIDES":
					this_doad.populateSides(print_writer, viz_scene_path, session_match, session_selected_broadcaster);
					break;
				case "POPULATE-SCOREBUGSTATS":
					switch(valueToProcess.toUpperCase()) {
					case "TEAM_NAME":
						stat = valueToProcess;
						this_doad.processAnimation(print_writer, "OtherInfoOut", "START", session_selected_broadcaster);
						
						this_doad.populateScoreBugStat(false,print_writer, viz_scene_path, valueToProcess , session_match, session_selected_broadcaster);
						
						this_doad.processAnimation(print_writer, "TeamIn", "COUNTINUE_REVERSE", session_selected_broadcaster);
						which_graphics_onscreen = "TEAM_NAME";
						//System.out.println(valueToProcess);
						break;
					default:
						stat = valueToProcess;
						this_doad.processAnimation(print_writer, "TeamOut", "START", session_selected_broadcaster);
						
						this_doad.populateScoreBugStat(false,print_writer, viz_scene_path, valueToProcess , session_match, session_selected_broadcaster);
						
						this_doad.processAnimation(print_writer, "OtherInfoIn", "START", session_selected_broadcaster);
						which_graphics_onscreen = valueToProcess.toUpperCase();
						break;
				
					}
					
					
					break;
				}
				return JSONObject.fromObject(this_doad).toString();
			}
			
		case "ANIMATE-IN-SCOREBUG": case "ANIMATE-IN-SINGLE-L3_MATCHID": case "ANIMATE-IN-SINGLE-FF_MATCHID": case "ANIMATE-IN-DOUBLE-L3_MATCHID": case "ANIMATE-IN-DOUBLE-FF_MATCHID": case "ANIMATE-IN-L3_TIEID": case "ANIMATE-IN-FF_TIEID": 
		case "ANIMATE-OUT": case "ANIMATE-OUT-STAT": case "ANIMATE-IN-SIDES":
			switch(session_selected_broadcaster) {
			case "DOAD_In_House_Everest":
				//System.out.println("whatToProcess = "+ whatToProcess);
				//System.out.println("which_graphics_onscreen = "+ which_graphics_onscreen);
				switch (whatToProcess.toUpperCase()) {
				case "ANIMATE-IN-SCOREBUG":
					if(session_match.getSets() == null) {
						//this_doad.processAnimation(print_writer, "OtherInfoOut", "SHOW 0.0", session_selected_broadcaster);
						this_doad.processAnimation(print_writer, "In", "START", session_selected_broadcaster);
					}
					else {
						//this_doad.processAnimation(print_writer, "OtherInfoOut", "SHOW 0.0", session_selected_broadcaster);
						this_doad.processAnimation(print_writer, "In", "START", session_selected_broadcaster);
						TimeUnit.SECONDS.sleep(3);
						
						this_doad.processAnimation(print_writer, "ScoreIn", "START", session_selected_broadcaster);
					}
					which_graphics_onscreen = "SCOREBUG";
					break;
				case "ANIMATE-IN-SINGLE-L3_MATCHID":
					this_doad.processAnimation(print_writer, "In", "START", session_selected_broadcaster);
					which_graphics_onscreen = "SINGLE_L3_MATCHID";
					break;
				case "ANIMATE-IN-SINGLE-FF_MATCHID":
					this_doad.processAnimation(print_writer, "In", "START", session_selected_broadcaster);
					which_graphics_onscreen = "SINGLE_FF_MATCHID";
					break;
				case "ANIMATE-IN-DOUBLE-L3_MATCHID":
					this_doad.processAnimation(print_writer, "In", "START", session_selected_broadcaster);
					which_graphics_onscreen = "DOUBLE_L3_MATCHID";
					break;
				case "ANIMATE-IN-DOUBLE-FF_MATCHID":
					this_doad.processAnimation(print_writer, "In", "START", session_selected_broadcaster);
					which_graphics_onscreen = "DOUBLE_FF_MATCHID";
					break;
				case "ANIMATE-IN-L3_TIEID":
					this_doad.processAnimation(print_writer, "In", "START", session_selected_broadcaster);
					which_graphics_onscreen = "L3_TIEID";
					break;
				case "ANIMATE-IN-FF_TIEID":
					this_doad.processAnimation(print_writer, "In", "START", session_selected_broadcaster);
					which_graphics_onscreen = "FF_TIEID";
					break;
				case "ANIMATE-IN-SIDES":
					this_doad.processAnimation(print_writer, "In", "START", session_selected_broadcaster);
					which_graphics_onscreen = "SIDES";
					break;
				case "ANIMATE-OUT": 
					switch(which_graphics_onscreen) {
					case "SCOREBUG":
						this_doad.processAnimation(print_writer, "Out", "START", session_selected_broadcaster);
						which_graphics_onscreen = "";
						break;
					case "SINGLE_L3_MATCHID":
						this_doad.processAnimation(print_writer, "Out", "START", session_selected_broadcaster);
						which_graphics_onscreen = "";
						break;
					case "SINGLE_FF_MATCHID":
						this_doad.processAnimation(print_writer, "Out", "START", session_selected_broadcaster);
						which_graphics_onscreen = "";
						break;
					case "DOUBLE_L3_MATCHID":
						this_doad.processAnimation(print_writer, "Out", "START", session_selected_broadcaster);
						which_graphics_onscreen = "";
						break;
					case "DOUBLE_FF_MATCHID":
						this_doad.processAnimation(print_writer, "Out", "START", session_selected_broadcaster);
						which_graphics_onscreen = "";
						break;
					case "L3_TIEID":
						this_doad.processAnimation(print_writer, "Out", "START", session_selected_broadcaster);
						which_graphics_onscreen = "";
						break;
					case "FF_TIEID":
						this_doad.processAnimation(print_writer, "Out", "START", session_selected_broadcaster);
						which_graphics_onscreen = "";
						break;
					case "SIDES":
						this_doad.processAnimation(print_writer, "Out", "START", session_selected_broadcaster);
						which_graphics_onscreen = "";
						break;
					}
					break;
				case "ANIMATE-OUT-STAT":
					switch(which_graphics_onscreen) {
					case "FOREHAND_WINNER": case "FOREHAND_ERROR": case "BACKHAND_WINNER": case "BACKHAND_ERROR":
						this_doad.processAnimation(print_writer, "OtherInfoOut", "START", session_selected_broadcaster);
						which_graphics_onscreen = "";
						break;
					case "TEAM_NAME":
						this_doad.processAnimation(print_writer, "TeamOut", "START", session_selected_broadcaster);
						which_graphics_onscreen = "";
						break;
					}
					break;
				
				}
			}
			return JSONObject.fromObject(this_doad).toString();
		default:
			return JSONObject.fromObject(null).toString();
		}
	}
	public BadmintonMatch populateMatchVariables(BadmintonMatch match)
	{
		if(match.getMatch() != null && match.getMatch().getMatchId() > 0) {
			match.setMatch(populateMatchVariables(match.getMatch()));
		}
		return match;
	}
	public Match populateMatchVariables(Match match)
	{
		if(match != null && match.getMatchId() > 0) {
			
			List<Player> players = new ArrayList<Player>();
			Team team = null;
			match = badmintonService.getMatch(match.getMatchId());
			
			if(match.getHomeFirstPlayerId() > 0) {
				players.add(badmintonService.getPlayer(match.getHomeFirstPlayerId()));
				team = badmintonService.getTeam(players.get(players.size() - 1).getTeamId());
				players.get(players.size() - 1).setTeam(team);
			}
			if(match.getHomeSecondPlayerId() > 0) {
				players.add(badmintonService.getPlayer(match.getHomeSecondPlayerId()));
				team = badmintonService.getTeam(players.get(players.size() - 1).getTeamId());
				players.get(players.size() - 1).setTeam(team);
			}
			if(match.getHomeThirdPlayerId() > 0) {
				players.add(badmintonService.getPlayer(match.getHomeThirdPlayerId()));
				team = badmintonService.getTeam(players.get(players.size() - 1).getTeamId());
				players.get(players.size() - 1).setTeam(team);
			}
			match.setHomePlayers(players);
			if(team != null)
				match.setHomeTeam(team);
			
			players = new ArrayList<Player>();
			team = null;
			if(match.getAwayFirstPlayerId() > 0) {
				players.add(badmintonService.getPlayer(match.getAwayFirstPlayerId()));
				team = badmintonService.getTeam(players.get(players.size() - 1).getTeamId());
				players.get(players.size() - 1).setTeam(team);
			}
			if(match.getAwaySecondPlayerId() > 0) {
				players.add(badmintonService.getPlayer(match.getAwaySecondPlayerId()));
				team = badmintonService.getTeam(players.get(players.size() - 1).getTeamId());
				players.get(players.size() - 1).setTeam(team);
			}
			if(match.getAwayThirdPlayerId() > 0) {
				players.add(badmintonService.getPlayer(match.getAwayThirdPlayerId()));
				team = badmintonService.getTeam(players.get(players.size() - 1).getTeamId());
				players.get(players.size() - 1).setTeam(team);
			}
			match.setAwayPlayers(players);
			if(team != null)
				match.setAwayTeam(team);
			
		}
		return match;
	}
}