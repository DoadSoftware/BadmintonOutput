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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class IndexController 
{
	@Autowired
	BadmintonService badmintonService;
	public static Configurations session_Configurations;
	public static BadmintonMatch session_match;
	
	public static String selectedmatch;
	public static Socket session_socket;
	public static Doad this_doad;
	public static PrintWriter print_writer;
	
	List<Match> match = new ArrayList<Match>();
	List<Team> allteams;
	List<Match> mtch = new ArrayList<Match>();
	List<Player> player = new ArrayList<Player>();
	List<Player> plyr = new ArrayList<Player>();
	List<Team> team = new ArrayList<Team>();
	List<NameSuper> namesuper = new ArrayList<NameSuper>();
	List<TeamColor> teamcolor = new ArrayList<TeamColor>();
	List<Fixture> fixture = new ArrayList<Fixture>();
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
		allteams = new ArrayList<Team>();
		//session_selected_ip = String.valueOf(vizIPAddress);
		
		session_socket = new Socket(vizIPAddresss, Integer.valueOf(vizPortNumber));
		print_writer = new PrintWriter(session_socket.getOutputStream(), true);
		session_Configurations = new Configurations(selectedmatch, select_broadcaster, vizIPAddresss, vizPortNumber, vizScene);
		
		
		/*for (Match mtch : badmintonService.getAllMatches())
		{
			mtch = populateMatchVariables(mtch);
			if(mtch.getHomeTeam().getTeamId() == fixture.getHomeTeamId) {
				badmin_matches.add(populateMatchVariables(JAXBContext.newInstance(BadmintonMatch.class).createUnmarshaller().unmarshal(
						new File(BadmintonUtil.BADMINTON_DIRECTORY + BadmintonUtil.MATCHES_DIRECTORY + file.getName())));
			}
		}*/
		
		JAXBContext.newInstance(Configurations.class).createMarshaller().marshal(session_Configurations, 
				new File(BadmintonUtil.BADMINTON_DIRECTORY + BadmintonUtil.CONFIGURATIONS_DIRECTORY + BadmintonUtil.OUTPUT_XML));
		
		session_match = (BadmintonMatch) JAXBContext.newInstance(BadmintonMatch.class).createUnmarshaller().unmarshal(
				new File(BadmintonUtil.BADMINTON_DIRECTORY + BadmintonUtil.MATCHES_DIRECTORY + selectedmatch));
		
		session_match.setMatch_file_timestamp(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()));
		
		
		model.addAttribute("session_match", session_match);
		model.addAttribute("session_selected_broadcaster", session_selected_broadcaster);
		model.addAttribute("session_selected_port", session_selected_port);
		
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
			
			if(!valueToProcess.equalsIgnoreCase(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(
					new File(BadmintonUtil.BADMINTON_DIRECTORY + BadmintonUtil.MATCHES_DIRECTORY + selectedmatch).lastModified())))
			{
				session_match = populateMatchVariables((BadmintonMatch) JAXBContext.newInstance(BadmintonMatch.class).createUnmarshaller().unmarshal(
						new File(BadmintonUtil.BADMINTON_DIRECTORY + BadmintonUtil.MATCHES_DIRECTORY + selectedmatch)));
				
				session_match.setMatch_file_timestamp(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(
						new File(BadmintonUtil.BADMINTON_DIRECTORY + BadmintonUtil.MATCHES_DIRECTORY + selectedmatch).lastModified()));
				
				if(is_ScoreBug_on_Screen == true) {
					teamcolor = badmintonService.getTeamColors();
					for(TeamColor tc : teamcolor) {
						this_doad.populateScoreBugName(true,print_writer, viz_scene_path, tc, session_match, session_selected_broadcaster);
					}
				}
				
				return JSONObject.fromObject(session_match).toString();
			}
			else {
				return JSONObject.fromObject(null).toString();
			}
		
		case "SCOREBUG_GRAPHICS-OPTIONS": case "SCOREBUGSTAT_GRAPHICS-OPTIONS": case"POINT_GRAPHICS-OPTIONS": case"MATCH_POINT_GRAPHICS-OPTIONS":
			return JSONObject.fromObject(session_match).toString();		
			
		case "PLAYER_PROFILE_GRAPHICS-OPTIONS":	case "NAMESUPER_PLAYER_GRAPHICS-OPTIONS":
			player = badmintonService.getAllPlayer();
				return JSONArray.fromObject(player).toString();
				
		case "SQUADS_GRAPHICS-OPTIONS":
			team = badmintonService.getAllTeam();
			return JSONArray.fromObject(team).toString();
		
		case "ORDER_OF_PLAY_GRAPHICS-OPTIONS": case "FF-TIE_GRAPHICS-OPTIONS": case "L3-TIE_GRAPHICS-OPTIONS":
			fixture = badmintonService.getFixtures();
			team = badmintonService.getAllTeam();
			
				for(Team tm : team) {
					for(int i= 0; i < fixture.size() ; i++) {
						if(fixture.get(i).getHometeam() == tm.getTeamId()) {
							fixture.get(i).setHome_Team(tm);
						}
						if(fixture.get(i).getAwayteam() == tm.getTeamId()) {
							fixture.get(i).setAway_Team(tm);
						}
					}
				}
				return JSONArray.fromObject(fixture).toString();
			
		case "NAMESUPER_GRAPHICS-OPTIONS": 
			namesuper = badmintonService.getNameSupers();
			return JSONArray.fromObject(namesuper).toString();
		
		case "FF-SINGLE_GRAPHICS-OPTIONS": case "FF-DOUBLE_GRAPHICS-OPTIONS": case "LT-SINGLE_GRAPHICS-OPTIONS": case "LT-DOUBLE_GRAPHICS-OPTIONS":
			match = badmintonService.getAllMatches();
			return JSONArray.fromObject(match).toString();
			
		case "POPULATE-SCOREBUG": case "POPULATE-SCOREBUGSTATS": case "POPULATE-SINGLE-L3-MATCHID": case "POPULATE-SINGLE-FF-MATCHID": case "POPULATE-DOUBLE-L3_MATCHID":
		case "POPULATE-DOUBLE-FF-MATCHID": case "POPULATE-L3-TIEID": case "POPULATE-FF-TIEID": case "POPULATE-SIDES": case "POPULATE-PLAYER_PROFILE": case "POPULATE-SUPER":
		case "POPULATE-FF_TIE_PROMO": case "POPULATE-L3_TIE_PROMO": case "POPULATE-ORDER_OF_PLAY": case "POPULATE-TEAMS_LOGO": case "POPULATE-SUPER_MATCH": 
		case "POPULATE-SUPER_MATCH1": case "POPULATE-SUPER_MATCH2": case "POPULATE-SINGLE_MATCH_PROMO": case "POPULATE-DOUBLE_MATCH_PROMO": case "POPULATE-LT_SINGLE_MATCH_PROMO":
		case "POPULATE-LT_DOUBLE_MATCH_PROMO": case "POPULATE-SQUADS": case "POPULATE-NAMESUPER_PLAYER":
			switch(session_selected_broadcaster) {
			case "DOAD_In_House_Everest":
				switch(whatToProcess.toUpperCase()) {
				case "POPULATE-SCOREBUGSTATS":
					break;
				default:
					viz_scene_path = valueToProcess.split(",")[0];
					new Scene(viz_scene_path).scene_load(print_writer,session_selected_broadcaster,viz_scene_path);
					break;
				}
				switch(whatToProcess.toUpperCase()) {
				case "POPULATE-SCOREBUG":
					teamcolor = badmintonService.getTeamColors();
					for(TeamColor tc : teamcolor) {
						this_doad.populateScoreBug(print_writer, viz_scene_path, stat , session_match, tc, session_selected_broadcaster);
					}

					break;
				case "POPULATE-SINGLE-L3-MATCHID":
					fixture = badmintonService.getFixtures();
					for(Fixture fx : fixture) {
						this_doad.populateSingleL3MatchId(print_writer, viz_scene_path,fx, session_match, session_selected_broadcaster);
					}
					break;
				case "POPULATE-SINGLE-FF-MATCHID":
					fixture = badmintonService.getFixtures();
					for(Fixture fx : fixture) {
						this_doad.populateSingleFFMatchId(print_writer, viz_scene_path,fx, session_match, session_selected_broadcaster);
					}
					break;
				case "POPULATE-DOUBLE-L3_MATCHID":
					fixture = badmintonService.getFixtures();
					for(Fixture fx : fixture) {
						this_doad.populateDoubleL3MatchId(print_writer, viz_scene_path,fx, session_match, session_selected_broadcaster);
					}
					break;
				case "POPULATE-DOUBLE-FF-MATCHID":
					fixture = badmintonService.getFixtures();
					for(Fixture fx : fixture) {
						this_doad.populateDoubleFFMatchId(print_writer, viz_scene_path, fx, session_match, session_selected_broadcaster);
					}
					break;
				case "POPULATE-L3-TIEID":
					fixture = badmintonService.getFixtures();
					for(Fixture fx : fixture) {
						this_doad.populateLtTieId(print_writer, viz_scene_path, fx, session_match, session_selected_broadcaster);
					}
					break;
				case "POPULATE-FF-TIEID":
					fixture = badmintonService.getFixtures();
					for(Fixture fx : fixture) {
						this_doad.populateFFTieId(print_writer, viz_scene_path, fx, session_match, session_selected_broadcaster);
					}
					break;
				case "POPULATE-SIDES":
					this_doad.populateSides(print_writer, viz_scene_path ,valueToProcess.split(",")[1],valueToProcess.split(",")[2], session_match, session_selected_broadcaster);
					break;
				case "POPULATE-SUPER":
					for(NameSuper ns : namesuper) {
						  
						  if(ns.getNamesuperId() == Integer.valueOf(valueToProcess.split(",")[1])) {
							  this_doad.populateSuper(print_writer, viz_scene_path, ns, session_match, session_selected_broadcaster);
						  }
						}
					break;
				case "POPULATE-NAMESUPER_PLAYER":
					for(Player pp : player) {
						if(pp.getPlayerId() == Integer.valueOf(valueToProcess.split(",")[1])) {
	                    	this_doad.populateNameSuperPlayer(print_writer, viz_scene_path, pp, badmintonService.getAllTeam(), Integer.valueOf(valueToProcess.split(",")[2]), 
	                    			session_selected_broadcaster);  
	                    }
	                }
					break;
				case "POPULATE-PLAYER_PROFILE":
					for(Player pp : player) {
						  team = badmintonService.getAllTeam();
						  
	                      if(pp.getPlayerId() == Integer.valueOf(valueToProcess.split(",")[1])) {
	                    	  for(Team tm : team) {
	                    		  this_doad.populatePlayerProfile(print_writer, viz_scene_path, pp, tm, session_match, session_selected_broadcaster);
							  }
	                      }
	                    }
					break;
				case "POPULATE-TEAMS_LOGO":
					
              		 this_doad.populateTeamsLogo(badmintonService.getAllTeam(),print_writer, viz_scene_path, session_selected_broadcaster);
					
					break;
				case "POPULATE-SUPER_MATCH":
					fixture = badmintonService.getFixtures();
					for(Fixture fx : fixture) {
	              		 this_doad.populateSuperMatch(print_writer, viz_scene_path,fx, session_match, session_selected_broadcaster);
					}
					
					break;
				case "POPULATE-SUPER_MATCH1":
					fixture = badmintonService.getFixtures();
					for(Fixture fx : fixture) {
	              		 this_doad.populateSuperMatch1(print_writer, viz_scene_path,fx, session_match, session_selected_broadcaster);
					}
					
					break;
				case "POPULATE-SUPER_MATCH2":
					fixture = badmintonService.getFixtures();
					for(Fixture fx : fixture) {
	              		 this_doad.populateSuperMatch2(print_writer, viz_scene_path,fx, session_match, session_selected_broadcaster);
					}
					
					break;
				case "POPULATE-ORDER_OF_PLAY":
					//System.out.println(Integer.valueOf(valueToProcess.split(",")[1]));
					List<BadmintonMatch> badminton_matches = new ArrayList<BadmintonMatch>();

					BadmintonMatch bm = new BadmintonMatch();
					File files[] = new File(BadmintonUtil.BADMINTON_DIRECTORY + BadmintonUtil.MATCHES_DIRECTORY).listFiles(new FileFilter() {
						@Override
					    public boolean accept(File pathname) {
					        String name = pathname.getName().toLowerCase();
					        return name.endsWith(".xml") && pathname.isFile();
					    }
					});
					for(File file : files) {
						
							bm = (populateMatchVariables((BadmintonMatch) JAXBContext.newInstance(BadmintonMatch.class).createUnmarshaller().unmarshal(
									new File(BadmintonUtil.BADMINTON_DIRECTORY + BadmintonUtil.MATCHES_DIRECTORY + file.getName()))));
									if(bm.getMatch().getHomeTeam().getTeamId() == Integer.valueOf(valueToProcess.split(",")[1]) || 
											bm.getMatch().getAwayTeam().getTeamId() == Integer.valueOf(valueToProcess.split(",")[1]) || 
											bm.getMatch().getHomeTeam().getTeamId() == Integer.valueOf(valueToProcess.split(",")[2]) ||
											bm.getMatch().getAwayTeam().getTeamId() == Integer.valueOf(valueToProcess.split(",")[2])) {
										
										badminton_matches.add(bm);
									}
					}
					fixture = badmintonService.getFixtures();
					for(Fixture fx : fixture) {
						this_doad.populateOrderOfPlay(print_writer, viz_scene_path ,Integer.valueOf(valueToProcess.split(",")[1]),Integer.valueOf(valueToProcess.split(",")[2]),badminton_matches,fx, session_match, session_selected_broadcaster);
					}
			
					break;
				case "POPULATE-FF_TIE_PROMO":
					for(Fixture fx : fixture) {
						if(fx.getMatchnumber() == Integer.valueOf(valueToProcess.split(",")[1])) {
							//System.out.println(fx.getHome_Team().getTeamId());
							//System.out.println(fx.getAway_Team().getTeamId());
							this_doad.populateFFTiePromo(print_writer, viz_scene_path ,fx, session_match, session_selected_broadcaster);
						}
					}
					break;
				case "POPULATE-L3_TIE_PROMO":
					for(Fixture fx : fixture) {
						if(fx.getMatchnumber() == Integer.valueOf(valueToProcess.split(",")[1])) {
							//System.out.println(fx.getHome_Team().getTeamId());
							//System.out.println(fx.getAway_Team().getTeamId());
							this_doad.populateL3TiePromo(print_writer, viz_scene_path ,fx, session_match, session_selected_broadcaster);
						}
					}
					break;
				case "POPULATE-SINGLE_MATCH_PROMO":
					for(Match singlematch : match) {
						if(singlematch.getMatchId() == Integer.valueOf(valueToProcess.split(",")[1])) {
							this_doad.populateFFSingleMatchPromo(print_writer, viz_scene_path ,singlematch, badmintonService.getAllPlayer(),badmintonService.getAllTeam(),session_selected_broadcaster);
						}
					}
					break;
				case "POPULATE-DOUBLE_MATCH_PROMO":
					for(Match doublematch : match) {
						if(doublematch.getMatchId() == Integer.valueOf(valueToProcess.split(",")[1])) {
							this_doad.populateFFDoubleMatchPromo(print_writer, viz_scene_path ,doublematch, badmintonService.getAllPlayer(),badmintonService.getAllTeam(),session_selected_broadcaster);
						}
					}
					break;
				case "POPULATE-LT_SINGLE_MATCH_PROMO":
					for(Match singlematch : match) {
						if(singlematch.getMatchId() == Integer.valueOf(valueToProcess.split(",")[1])) {
							this_doad.populateLTSingleMatchPromo(print_writer, viz_scene_path ,singlematch, badmintonService.getAllPlayer(),badmintonService.getAllTeam(),session_selected_broadcaster);
						}
					}
					break;
				case "POPULATE-LT_DOUBLE_MATCH_PROMO":
					for(Match doublematch : match) {
						if(doublematch.getMatchId() == Integer.valueOf(valueToProcess.split(",")[1])) {
							this_doad.populateLTDoubleMatchPromo(print_writer, viz_scene_path ,doublematch, badmintonService.getAllPlayer(),badmintonService.getAllTeam(),session_selected_broadcaster);
						}
					}
					break;
				case "POPULATE-SQUADS":
					for(Team select_team : team) {
						if(select_team.getTeamId() == Integer.valueOf(valueToProcess.split(",")[1])) {
							this_doad.populateSquads(print_writer, viz_scene_path ,select_team, badmintonService.getAllPlayer(),badmintonService.getAllTeam(),session_selected_broadcaster);
						}
					}
					break;
					
				case "POPULATE-SCOREBUGSTATS":
					
					switch(valueToProcess.toUpperCase()) {
					case "TEAM_NAME": case"HOME": case"AWAY": case"MATCH_HOME": case"MATCH_AWAY":
						if(which_graphics_onscreen.equalsIgnoreCase("FOREHAND_WINNER" ) || which_graphics_onscreen.equalsIgnoreCase("FOREHAND_ERROR")  || which_graphics_onscreen.equalsIgnoreCase("BACKHAND_WINNER") || which_graphics_onscreen.equalsIgnoreCase("BACKHAND_ERROR")) {
							stat = valueToProcess;
							
							
							this_doad.processAnimation(print_writer, "OtherInfoOut", "START", session_selected_broadcaster);
							
							this_doad.processAnimation(print_writer, "TeamIn", "START", session_selected_broadcaster);
							this_doad.populateScoreBugStat(false,print_writer, viz_scene_path, valueToProcess, session_match, session_selected_broadcaster);
							
							which_graphics_onscreen = "TEAM_NAME";
						}
						else {
							
							stat = valueToProcess;
							this_doad.processAnimation(print_writer, "TeamIn", "START", session_selected_broadcaster);
							this_doad.populateScoreBugStat(false,print_writer, viz_scene_path, valueToProcess , session_match, session_selected_broadcaster);
							
							
							which_graphics_onscreen = "TEAM_NAME";
						}
						break;
					default:
						if(which_graphics_onscreen.equalsIgnoreCase("TEAM_NAME")) {
							stat = valueToProcess;
							this_doad.processAnimation(print_writer, "TeamOut", "START", session_selected_broadcaster);
							
							this_doad.processAnimation(print_writer, "OtherInfoIn", "START", session_selected_broadcaster);
							this_doad.populateScoreBugStat(false,print_writer, viz_scene_path, valueToProcess , session_match, session_selected_broadcaster);
							
							which_graphics_onscreen = valueToProcess.toUpperCase();
						}
						else {
							stat = valueToProcess;
							this_doad.processAnimation(print_writer, "OtherInfoIn", "START", session_selected_broadcaster);
							this_doad.populateScoreBugStat(false,print_writer, viz_scene_path, valueToProcess , session_match, session_selected_broadcaster);
							
							which_graphics_onscreen = valueToProcess.toUpperCase();
						}
						
						break;
				
					}
					
					
					break;
				}
				return JSONObject.fromObject(this_doad).toString();
			}
			
		case "ANIMATE-IN-SCOREBUG": case "ANIMATE-IN-SINGLE-L3_MATCHID": case "ANIMATE-IN-SINGLE-FF_MATCHID": case "ANIMATE-IN-DOUBLE-L3_MATCHID": case "ANIMATE-IN-DOUBLE-FF_MATCHID": case "ANIMATE-IN-L3_TIEID": case "ANIMATE-IN-FF_TIEID": 
		case "ANIMATE-OUT": case "ANIMATE-OUT-STAT": case "ANIMATE-IN-SIDES": case "ANIMATE-IN-SUPER": case "ANIMATE-IN-PLAYER_PROFILE": case "ANIMATE-IN-ORDER_OF_PLAY": case "ANIMATE-IN-TEAMS_LOGO": case "ANIMATE-IN-SUPER_MATCH":
		case "ANIMATE-IN-SUPER_MATCH1": case "ANIMATE-IN-SUPER_MATCH2":	case "ANIMATE-IN-FF_TIE_PROMO": case "ANIMATE-IN-L3_TIE_PROMO": case "ANIMATE-IN-FF_SINGLE_MATCH_PROMO": case "ANIMATE-IN-FF_DOUBLE_MATCH_PROMO":
		case "ANIMATE-IN-LT_SINGLE_MATCH_PROMO": case "ANIMATE-IN-LT_DOUBLE_MATCH_PROMO": case "ANIMATE-IN-SQUADS": case "ANIMATE-IN-NAMESUPER_PLAYER":
			switch(session_selected_broadcaster) {
			case "DOAD_In_House_Everest":
				//System.out.println("whatToProcess = "+ whatToProcess);
				//System.out.println("which_graphics_onscreen = "+ which_graphics_onscreen);
				switch (whatToProcess.toUpperCase()) {
				case "ANIMATE-IN-SCOREBUG":
					if(session_match.getSets() == null) {
						//this_doad.processAnimation(print_writer, "OtherInfoOut", "SHOW 0.0", session_selected_broadcaster);
						this_doad.processAnimation(print_writer, "In", "START", session_selected_broadcaster);
						
						if(session_match.getMatch().getTrumpHomeMatch() == 1) {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vTrump " + "1" +";");
							TimeUnit.SECONDS.sleep(3);
							this_doad.processAnimation(print_writer, "TrumpIn", "START", session_selected_broadcaster);
							//TimeUnit.SECONDS.sleep(5);
							//this_doad.processAnimation(print_writer, "TrumpOut", "START", session_selected_broadcaster);
						}
						else if(session_match.getMatch().getTrumpAwayMatch() == 1) {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vTrump " + "2" +";");
							TimeUnit.SECONDS.sleep(3);
							this_doad.processAnimation(print_writer, "TrumpIn", "START", session_selected_broadcaster);
							//TimeUnit.SECONDS.sleep(5);
							//this_doad.processAnimation(print_writer, "TrumpOut", "START", session_selected_broadcaster);
						}
						else if(session_match.getMatch().getTrumpHomeMatch() == 1 && session_match.getMatch().getTrumpAwayMatch() == 1) {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vTrump " + "3" +";");
							TimeUnit.SECONDS.sleep(3);
							this_doad.processAnimation(print_writer, "TrumpIn", "START", session_selected_broadcaster);
							//TimeUnit.SECONDS.sleep(5);
							//this_doad.processAnimation(print_writer, "TrumpOut", "START", session_selected_broadcaster);
						}
						else {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vTrump " + "0" +";");
						}
						
						is_ScoreBug_on_Screen = true;
					}
					else {
						//this_doad.processAnimation(print_writer, "OtherInfoOut", "SHOW 0.0", session_selected_broadcaster);
						this_doad.processAnimation(print_writer, "In", "START", session_selected_broadcaster);
						
						if(session_match.getMatch().getTrumpHomeMatch() == 1) {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vTrump " + "1" +";");
							TimeUnit.SECONDS.sleep(3);
							this_doad.processAnimation(print_writer, "TrumpIn", "START", session_selected_broadcaster);
						}
						else if(session_match.getMatch().getTrumpAwayMatch() == 1) {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vTrump " + "2" +";");
							TimeUnit.SECONDS.sleep(3);
							this_doad.processAnimation(print_writer, "TrumpIn", "START", session_selected_broadcaster);
						}
						else if(session_match.getMatch().getTrumpHomeMatch() == 1 && session_match.getMatch().getTrumpAwayMatch() == 1) {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vTrump " + "3" +";");
							TimeUnit.SECONDS.sleep(3);
							this_doad.processAnimation(print_writer, "TrumpIn", "START", session_selected_broadcaster);
							//TimeUnit.SECONDS.sleep(5);
							//this_doad.processAnimation(print_writer, "TrumpOut", "START", session_selected_broadcaster);
						}
						else {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vTrump " + "0" +";");
						}
						
						
						
						this_doad.processAnimation(print_writer, "Score1In", "START", session_selected_broadcaster);
						//TimeUnit.SECONDS.sleep(5);
						//this_doad.processAnimation(print_writer, "TrumpOut", "START", session_selected_broadcaster);
						is_ScoreBug_on_Screen = true;
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
				case "ANIMATE-IN-SUPER":
					this_doad.processAnimation(print_writer, "In", "START", session_selected_broadcaster);
					which_graphics_onscreen = "SUPER";
					break;
				case "ANIMATE-IN-NAMESUPER_PLAYER":
					this_doad.processAnimation(print_writer, "In", "START", session_selected_broadcaster);
					which_graphics_onscreen = "NAMESUPER_PLAYER";
					break;
				case "ANIMATE-IN-PLAYER_PROFILE":
					this_doad.processAnimation(print_writer, "In", "START", session_selected_broadcaster);
					which_graphics_onscreen = "PLAYER_PROFILE";
					break;
				case "ANIMATE-IN-ORDER_OF_PLAY":
					this_doad.processAnimation(print_writer, "In", "START", session_selected_broadcaster);
					which_graphics_onscreen = "ORDER_OF_PLAY";
					break;
				case "ANIMATE-IN-FF_TIE_PROMO":
					this_doad.processAnimation(print_writer, "In", "START", session_selected_broadcaster);
					which_graphics_onscreen = "FF_TIE_PROMO";
					break;
				case "ANIMATE-IN-L3_TIE_PROMO":
					this_doad.processAnimation(print_writer, "In", "START", session_selected_broadcaster);
					which_graphics_onscreen = "L3_TIE_PROMO";
					break;
				case "ANIMATE-IN-FF_SINGLE_MATCH_PROMO":
					this_doad.processAnimation(print_writer, "In", "START", session_selected_broadcaster);
					which_graphics_onscreen = "FF_SINGLE_MATCH_PROMO";
					break;
				case "ANIMATE-IN-FF_DOUBLE_MATCH_PROMO":
					this_doad.processAnimation(print_writer, "In", "START", session_selected_broadcaster);
					which_graphics_onscreen = "FF_DOUBLE_MATCH_PROMO";
					break;
				case "ANIMATE-IN-LT_SINGLE_MATCH_PROMO":
					this_doad.processAnimation(print_writer, "In", "START", session_selected_broadcaster);
					which_graphics_onscreen = "LT_SINGLE_MATCH_PROMO";
					break;
				case "ANIMATE-IN-LT_DOUBLE_MATCH_PROMO":
					this_doad.processAnimation(print_writer, "In", "START", session_selected_broadcaster);
					which_graphics_onscreen = "LT_DOUBLE_MATCH_PROMO";
					break;
				case "ANIMATE-IN-SQUADS":
					this_doad.processAnimation(print_writer, "In", "START", session_selected_broadcaster);
					which_graphics_onscreen = "SQUADS";
					break;
				case "ANIMATE-IN-TEAMS_LOGO":
					this_doad.processAnimation(print_writer, "In", "START", session_selected_broadcaster);
					which_graphics_onscreen = "TEAMS_LOGO";
					break;
				case "ANIMATE-IN-SUPER_MATCH":
					this_doad.processAnimation(print_writer, "In", "START", session_selected_broadcaster);
					which_graphics_onscreen = "SUPER_MATCH";
					break;
				case "ANIMATE-IN-SUPER_MATCH1":
					this_doad.processAnimation(print_writer, "In", "START", session_selected_broadcaster);
					which_graphics_onscreen = "SUPER_MATCH1";
					break;
				case "ANIMATE-IN-SUPER_MATCH2":
					this_doad.processAnimation(print_writer, "In", "START", session_selected_broadcaster);
					which_graphics_onscreen = "SUPER_MATCH2";
					break;
				case "ANIMATE-OUT": 
					switch(which_graphics_onscreen) {
					case "SCOREBUG":
						//this_doad.processAnimation(print_writer, "Score1In", "COUNTINUE_REVERSE", session_selected_broadcaster);
						//TimeUnit.SECONDS.sleep(1);
						//this_doad.processAnimation(print_writer, "Out", "STOP", session_selected_broadcaster);
						this_doad.processAnimation(print_writer, "Out", "START", session_selected_broadcaster);
						
						which_graphics_onscreen = "";
						is_ScoreBug_on_Screen = false;
						break;
						
					case "SINGLE_L3_MATCHID": case "SINGLE_FF_MATCHID": case "DOUBLE_L3_MATCHID": case "DOUBLE_FF_MATCHID": case "L3_TIEID": case "FF_TIEID":
					case "FF_TIE_PROMO": case "L3_TIE_PROMO": case "SIDES": case "SUPER": case "PLAYER_PROFILE": case "ORDER_OF_PLAY": case "TEAMS_LOGO": 
					case "SUPER_MATCH": case "SUPER_MATCH1": case "SUPER_MATCH2": case "FF_SINGLE_MATCH_PROMO": case "FF_DOUBLE_MATCH_PROMO": case "LT_SINGLE_MATCH_PROMO":
					case "LT_DOUBLE_MATCH_PROMO": case "SQUADS": case "NAMESUPER_PLAYER":
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
	public BadmintonMatch populateMatchVariables(BadmintonMatch Badmatch)
	{
		if(Badmatch.getMatch() != null && Badmatch.getMatch().getMatchId() > 0) {
			Badmatch.setMatch(populateMatchVariables(Badmatch.getMatch()));
		}
		return Badmatch;
	}
	public Match populateMatchVariables(Match match)
	{
		if(match != null && match.getMatchId() > 0) {
			
			List<Player> players = new ArrayList<Player>();
			Team team = null;
			
			
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
	/*public Fixture updateBadmintonData(Match mtch,String typeOfProfile ) 
	{
		boolean player_found = false;
		//mtch = badmintonService.getAllMatches();
		for(BadmintonMatch match : badminton_matches) {
			player_found = false;
			if(mtch.getGroupname() == match.getMatch().getGroupname() && mtch.getHomeFirstPlayerId() == match.getMatch().getHomeFirstPlayerId() && mtch.getAwayFirstPlayerId() == match.getMatch().getAwayFirstPlayerId()) {
					
					
				}
				
				
				
		}
<<<<<<< Updated upstream
		return mtch;
=======
		
>>>>>>> Stashed changes
	}*/
}