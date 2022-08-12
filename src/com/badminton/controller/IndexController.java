package com.badminton.controller;

import java.io.File;
import java.io.FileFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
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
	public static LeagueTable league_table;
	public static ContainerData session_Data;
	
	public static String selectedmatch;
	
	public static Socket session_socket;
	public static Doad this_doad;
	public static PrintWriter print_writer;

	
	//public static BadmintonMatch bm;
	List<NameSuper> namesuper = new ArrayList<NameSuper>();
	List<Player> player = new ArrayList<Player>();
	List<Team> team = new ArrayList<Team>();
	List<Fixture> fixture = new ArrayList<Fixture>();

	List<BadmintonMatch> badminton_matches = new ArrayList<BadmintonMatch>();
	List<Match> match = new ArrayList<Match>();
	List<Team> allteams = new ArrayList<Team>();

	
	String session_selected_broadcaster,session_selected_ip,which_graphics_onscreen,viz_scene_path,stat,category_onscreen;
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
		
		
		//selectedxml = scene_files;
		if(new File(BadmintonUtil.BADMINTON_DIRECTORY + BadmintonUtil.CONFIGURATIONS_DIRECTORY + BadmintonUtil.OUTPUT_XML).exists()) {
			session_Configurations = (Configurations)JAXBContext.newInstance(Configurations.class).createUnmarshaller().unmarshal(
					new File(BadmintonUtil.BADMINTON_DIRECTORY + BadmintonUtil.CONFIGURATIONS_DIRECTORY + BadmintonUtil.OUTPUT_XML));
		} else {
			session_Configurations = new Configurations();
			JAXBContext.newInstance(Configurations.class).createMarshaller().marshal(session_Configurations, 
					new File(BadmintonUtil.BADMINTON_DIRECTORY + BadmintonUtil.CONFIGURATIONS_DIRECTORY + BadmintonUtil.OUTPUT_XML));
		}
		
		model.addAttribute("session_Configurations",session_Configurations);
		//model.addAttribute("session_Configurations",session_Configurations);
		model.addAttribute("league_table", league_table);
		
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
		category_onscreen = "";
		selectedmatch = selectmatch;
		is_ScoreBug_on_Screen = false;
		this_doad = new Doad();
		
		//badminton_matches = new ArrayList<BadmintonMatch>();
		session_selected_broadcaster = select_broadcaster;
		session_selected_port = vizPortNumber;
		
		session_selected_ip = vizIPAddresss;
		
		session_socket = new Socket(vizIPAddresss, Integer.valueOf(vizPortNumber));
		print_writer = new PrintWriter(session_socket.getOutputStream(), true);
		//BadmintonMatch badminton_match = new BadmintonMatch();
		session_Configurations = new Configurations(selectedmatch, select_broadcaster, vizIPAddresss, vizPortNumber, vizScene);
		
		model.addAttribute("manual_files", new File(BadmintonUtil.BADMINTON_DIRECTORY + BadmintonUtil.MANUAL_DIRECTORY + BadmintonUtil.DATA_DIRECTORY).listFiles(new FileFilter() {
			@Override
		    public boolean accept(File pathname) {
		        String name = pathname.getName().toLowerCase();
		        return name.endsWith(".xml") && pathname.isFile();
		    }
		}));
		
		JAXBContext.newInstance(Configurations.class).createMarshaller().marshal(session_Configurations, 
				new File(BadmintonUtil.BADMINTON_DIRECTORY + BadmintonUtil.CONFIGURATIONS_DIRECTORY + BadmintonUtil.OUTPUT_XML));
		
		session_match = (BadmintonMatch) JAXBContext.newInstance(BadmintonMatch.class).createUnmarshaller().unmarshal(
				new File(BadmintonUtil.BADMINTON_DIRECTORY + BadmintonUtil.MATCHES_DIRECTORY + selectedmatch));
		
		session_match.setMatch_file_timestamp(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()));
		
		
		model.addAttribute("session_match", session_match);
		model.addAttribute("session_selected_broadcaster", session_selected_broadcaster);
		model.addAttribute("session_selected_port", session_selected_port);
		model.addAttribute("session_selected_ip", session_selected_ip);
		
		return "output";
	}

	@RequestMapping(value = {"/processBadmintonProcedures"}, method={RequestMethod.GET,RequestMethod.POST})    
	public @ResponseBody String processBadmintonProcedures(
			@RequestParam(value = "whatToProcess", required = false, defaultValue = "") String whatToProcess,
			@RequestParam(value = "valueToProcess", required = false, defaultValue = "") String valueToProcess)  
					throws IOException, IllegalAccessException, InvocationTargetException, JAXBException, InterruptedException, ParseException 
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
					this_doad.populateScoreBug(true,print_writer, viz_scene_path, session_match, session_selected_broadcaster);
				}
				
				return JSONObject.fromObject(session_match).toString();
			}
			else {
				return JSONObject.fromObject(null).toString();
			}
		
		case "SCOREBUG_GRAPHICS-OPTIONS": case "SCOREBUGSTAT_GRAPHICS-OPTIONS": case"POINT_GRAPHICS-OPTIONS": case"MATCH_POINT_GRAPHICS-OPTIONS": case"SUPER_MATCH1_GRAPHICS-OPTIONS":
			return JSONObject.fromObject(session_match).toString();		
			
		case "PLAYER_PROFILE_GRAPHICS-OPTIONS":	case "NAMESUPER_PLAYER_GRAPHICS-OPTIONS":
			
			player = badmintonService.getAllPlayer();
				return JSONArray.fromObject(player).toString();
				
		case "SQUADS_GRAPHICS-OPTIONS":
			
			team = badmintonService.getAllTeam();
			return JSONArray.fromObject(team).toString();
			
		//case "PREVIOUS_GRAPHICS-OPTIONS":
			//return JSONObject.fromObject(badminton_matches_scene).toString();
		
		case "ORDER_OF_PLAY_GRAPHICS-OPTIONS": case "FF-TIE_GRAPHICS-OPTIONS": case "L3-TIE_GRAPHICS-OPTIONS": case "SCHEDULE_GRAPHICS-OPTIONS": case "LT-TIEID-DOUBLE_GRAPHICS-OPTIONS":
			
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
			
		case "NAMESUPER_GRAPHICS-OPTIONS": case "BUGSUPER_GRAPHICS-OPTIONS":
			
			namesuper = badmintonService.getNameSupers();
			return JSONArray.fromObject(namesuper).toString();
		
		case "FF-SINGLE_GRAPHICS-OPTIONS": case "FF-DOUBLE_GRAPHICS-OPTIONS": case "LT-SINGLE_GRAPHICS-OPTIONS": case "LT-DOUBLE_GRAPHICS-OPTIONS":
			//match.clear();
			List<Match> current_date_match = new ArrayList<Match>(); 
			match = badmintonService.getAllMatches();
			for(Match mtch : match) {
				//System.out.println(mtch.getMatchDate());
				if(mtch.getMatchDate().equalsIgnoreCase(new SimpleDateFormat("dd-MM-yyyy").format(new Date()))) {
					current_date_match.add(mtch);
				}
			}
			return JSONArray.fromObject(current_date_match).toString();
			
			
			
		case "POPULATE-SCOREBUG": case "POPULATE-SCOREBUGSTATS": case "POPULATE-SINGLE-L3-MATCHID": case "POPULATE-SINGLE-FF-MATCHID": case "POPULATE-DOUBLE-L3_MATCHID":
		case "POPULATE-DOUBLE-FF-MATCHID": case "POPULATE-L3-TIEID": case "POPULATE-FF-TIEID": case "POPULATE-SIDES": case "POPULATE-PLAYER_PROFILE": case "POPULATE-SUPER":
		case "POPULATE-FF_TIE_PROMO": case "POPULATE-L3_TIE_PROMO": case "POPULATE-ORDER_OF_PLAY": case "POPULATE-TEAMS_LOGO": case "POPULATE-SUPER_MATCH": 
		case "POPULATE-SUPER_MATCH1": case "POPULATE-SUPER_MATCH2": case "POPULATE-SINGLE_MATCH_PROMO": case "POPULATE-DOUBLE_MATCH_PROMO": case "POPULATE-LT_SINGLE_MATCH_PROMO":
		case "POPULATE-LT_DOUBLE_MATCH_PROMO": case "POPULATE-SQUADS": case "POPULATE-NAMESUPER_PLAYER": case "POPULATE-POINTS_TABLE": case "POPULATE-RULES": 
		case "POPULATE-SCHEDULE": case "POPULATE-BUG_SUPER": case "POPULATE-TIEID-DOUBLE": case "LOAD_MANUAL_XML_SCENE":
			switch(session_selected_broadcaster) {
			case "DOAD_In_House_Everest":
				switch(whatToProcess.toUpperCase()) {
				case "LOAD_MANUAL_XML_SCENE":
					new Scene(BadmintonUtil.BADMINTON_DIRECTORY + BadmintonUtil.SCENES_DIRECTORY + valueToProcess.replace(".xml", ".sum")).scene_load(print_writer,session_selected_broadcaster,BadmintonUtil.BADMINTON_DIRECTORY + BadmintonUtil.SCENES_DIRECTORY + valueToProcess.replace(".xml", ".sum"));
					break;
				case "POPULATE-SCOREBUGSTATS":
					break;
				default:
					viz_scene_path = valueToProcess.split(",")[0];
					new Scene(viz_scene_path).scene_load(print_writer,session_selected_broadcaster,viz_scene_path);
					break;
				}
				switch(whatToProcess.toUpperCase()) {
				case "LOAD_MANUAL_XML_SCENE":
					//Collections.sort(valueToProcess);
					//System.out.println("2 = "  + which_graphic_on_screen);
					session_Data = (ContainerData)JAXBContext.newInstance(ContainerData.class).createUnmarshaller().unmarshal(
							new File(BadmintonUtil.BADMINTON_DIRECTORY + BadmintonUtil.MANUAL_DIRECTORY + BadmintonUtil.DATA_DIRECTORY + valueToProcess));
					//System.out.println(contain);
					
					for(int i = 0; i < session_Data.getContainers().size() ; i++) {
						//Collections.sort(session_Data.getContainers().get(i).getContainer_id());
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET " + session_Data.getContainers().get(i).getContainer_id() + " " + session_Data.getContainers().get(i).getContainer_value() + ";");
					}
					
					which_graphics_onscreen = "ANY_SCENE";
					
					return JSONObject.fromObject(session_Data).toString();
				}
				
				switch(whatToProcess.toUpperCase()) {
				
				case "POPULATE-SCOREBUG":
					
					this_doad.populateScoreBug(false ,print_writer, viz_scene_path, session_match, session_selected_broadcaster);

					break;
				case "POPULATE-SINGLE-L3-MATCHID":
					this_doad.populateSingleL3MatchId(print_writer, viz_scene_path,badmintonService.getFixtures(), session_match, session_selected_broadcaster);
					break;
				case "POPULATE-SINGLE-FF-MATCHID":
					this_doad.populateSingleFFMatchId(print_writer, viz_scene_path,badmintonService.getFixtures(), session_match, session_selected_broadcaster);
					break;
				case "POPULATE-DOUBLE-L3_MATCHID":
					this_doad.populateDoubleL3MatchId(print_writer, viz_scene_path,badmintonService.getFixtures(), session_match, session_selected_broadcaster);
					break;
				case "POPULATE-DOUBLE-FF-MATCHID":
					this_doad.populateDoubleFFMatchId(print_writer, viz_scene_path, badmintonService.getFixtures(), session_match, session_selected_broadcaster);
					break;
				case "POPULATE-L3-TIEID":
					this_doad.populateLtTieId(print_writer, viz_scene_path, badmintonService.getFixtures(), session_match, session_selected_broadcaster);
					break;
				case "POPULATE-FF-TIEID":
					this_doad.populateFFTieId(print_writer, viz_scene_path, badmintonService.getFixtures(), session_match, session_selected_broadcaster);
					break;
				case "POPULATE-SIDES":
					this_doad.populateSides(print_writer, viz_scene_path ,valueToProcess.split(",")[1],valueToProcess.split(",")[2], session_match, session_selected_broadcaster);
					break;
				case "POPULATE-SUPER":
					for(NameSuper ns : namesuper) {
					  if(ns.getNamesuperId() == Integer.valueOf(valueToProcess.split(",")[1])) {
						  this_doad.populateSuper(print_writer, viz_scene_path, ns, Integer.valueOf(valueToProcess.split(",")[2]), session_selected_broadcaster);
					  }
					}
					break;
				case "POPULATE-BUG_SUPER":
					for(NameSuper ns : namesuper) {
						  if(ns.getNamesuperId() == Integer.valueOf(valueToProcess.split(",")[1])) {
							  this_doad.populateBugSuper(print_writer, viz_scene_path, ns, session_selected_broadcaster);
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
              		 this_doad.populateTeamsLogo(badmintonService.getAllTeam(),print_writer, viz_scene_path,session_match, session_selected_broadcaster);
					break;
				case "POPULATE-SUPER_MATCH":
	              	this_doad.populateSuperMatch(print_writer, viz_scene_path,badmintonService.getFixtures(), session_match, session_selected_broadcaster);
					break;
				case "POPULATE-SUPER_MATCH1":
	              	this_doad.populateSuperMatch1(print_writer, viz_scene_path,badmintonService.getFixtures(), session_match, session_selected_broadcaster);
					break;
				case "POPULATE-SUPER_MATCH2":
	              	this_doad.populateSuperMatch2(print_writer, viz_scene_path,badmintonService.getFixtures(), session_match, session_selected_broadcaster);
					break;
				case "POPULATE-ORDER_OF_PLAY":
					//System.out.println(Integer.valueOf(valueToProcess.split(",")[1]));
					//List<BadmintonMatch> badminton_match = new ArrayList<BadmintonMatch>();
					badminton_matches.clear();
					BadmintonMatch bad_match = new BadmintonMatch();
					File files[] = new File(BadmintonUtil.BADMINTON_DIRECTORY + BadmintonUtil.MATCHES_DIRECTORY).listFiles(new FileFilter() {
						@Override
					    public boolean accept(File pathname) {
					        String name = pathname.getName().toLowerCase();
					        return name.endsWith(".xml") && pathname.isFile();
					    }
					});
					for(File file : files) {
						
						bad_match = (populateMatchVariables((BadmintonMatch) JAXBContext.newInstance(BadmintonMatch.class).createUnmarshaller().unmarshal(
									new File(BadmintonUtil.BADMINTON_DIRECTORY + BadmintonUtil.MATCHES_DIRECTORY + file.getName()))));
						for(Fixture fx : fixture) {
							if(bad_match.getMatch().getMatchDate().equalsIgnoreCase(new SimpleDateFormat("dd-MM-yyyy").format(new Date()))) {
								if(fx.getMatchnumber() == Integer.valueOf(valueToProcess.split(",")[1])) {
									if(bad_match.getMatch().getHomeTeam().getTeamId() == fx.getHome_Team().getTeamId() || 
											bad_match.getMatch().getAwayTeam().getTeamId() == fx.getAway_Team().getTeamId() || 
													bad_match.getMatch().getHomeTeam().getTeamId() == fx.getAway_Team().getTeamId() ||
															bad_match.getMatch().getAwayTeam().getTeamId() == fx.getHome_Team().getTeamId()) {
										
										badminton_matches.add(bad_match);
									}
									//this_doad.populateOrderOfPlay(print_writer, viz_scene_path ,Integer.valueOf(valueToProcess.split(",")[1]),badminton_matches,fx, session_match, session_selected_broadcaster);
								}
							}
						}
					}
					this_doad.populateOrderOfPlay(print_writer, viz_scene_path ,Integer.valueOf(valueToProcess.split(",")[1]),badminton_matches,fixture, session_match, session_selected_broadcaster);
					break;
				case "POPULATE-FF_TIE_PROMO":
					this_doad.populateFFTiePromo(print_writer, viz_scene_path ,Integer.valueOf(valueToProcess.split(",")[1]),badmintonService.getAllTeam(),
							badmintonService.getFixtures(), session_match, session_selected_broadcaster);
					break;
				case "POPULATE-L3_TIE_PROMO":
					this_doad.populateL3TiePromo(print_writer, viz_scene_path ,Integer.valueOf(valueToProcess.split(",")[1]),badmintonService.getAllTeam(), 
							badmintonService.getFixtures(), session_match, session_selected_broadcaster);
					break;
				case "POPULATE-SINGLE_MATCH_PROMO":
					this_doad.populateFFSingleMatchPromo(print_writer, viz_scene_path ,Integer.valueOf(valueToProcess.split(",")[1]),badmintonService.getAllMatches(), 
							badmintonService.getAllPlayer(),badmintonService.getAllTeam(), session_match,session_selected_broadcaster);
					break;
				case "POPULATE-DOUBLE_MATCH_PROMO":
					this_doad.populateFFDoubleMatchPromo(print_writer, viz_scene_path ,Integer.valueOf(valueToProcess.split(",")[1]),badmintonService.getAllMatches(), 
							badmintonService.getAllPlayer(),badmintonService.getAllTeam(), session_match,session_selected_broadcaster);
					break;
				case "POPULATE-LT_SINGLE_MATCH_PROMO":
					this_doad.populateLTSingleMatchPromo(print_writer, viz_scene_path ,Integer.valueOf(valueToProcess.split(",")[1]),badmintonService.getAllMatches(), 
							badmintonService.getAllPlayer(),badmintonService.getAllTeam(),session_selected_broadcaster);
					break;
				case "POPULATE-LT_DOUBLE_MATCH_PROMO":
					this_doad.populateLTDoubleMatchPromo(print_writer, viz_scene_path ,Integer.valueOf(valueToProcess.split(",")[1]),badmintonService.getAllMatches(), 
							badmintonService.getAllPlayer(),badmintonService.getAllTeam(),session_selected_broadcaster);
					break;
				case "POPULATE-SQUADS":
					this_doad.populateSquads(print_writer, viz_scene_path ,Integer.valueOf(valueToProcess.split(",")[1]), 
							badmintonService.getAllPlayer(),badmintonService.getAllTeam(), session_match,session_selected_broadcaster);
					break;
				case "POPULATE-POINTS_TABLE":
					if(new File(BadmintonUtil.BADMINTON_DIRECTORY + BadmintonUtil.LEAGUE_TABLE_DIRECTORY + BadmintonUtil.LEAGUETABLE_XML).exists()) {
						league_table = (LeagueTable)JAXBContext.newInstance(LeagueTable.class).createUnmarshaller().unmarshal(
								new File(BadmintonUtil.BADMINTON_DIRECTORY + BadmintonUtil.LEAGUE_TABLE_DIRECTORY + BadmintonUtil.LEAGUETABLE_XML));
					}
					this_doad.populatePointsTable(print_writer, viz_scene_path, league_table.getLeagueTeams(),session_selected_broadcaster);
					break;
				case "POPULATE-RULES":
					this_doad.populateRules(print_writer, viz_scene_path,badmintonService.getRules(), session_match,session_selected_broadcaster);
					break;
				case "POPULATE-SCHEDULE":
					this_doad.populateSchedule(print_writer, viz_scene_path,valueToProcess.split(",")[1],fixture,
								badmintonService.getAllTeam(), session_match,session_selected_broadcaster);
					break;
				case "POPULATE-TIEID-DOUBLE":
					//System.out.println(valueToProcess.split(",")[1]);
					this_doad.populateTieIdDouble(print_writer, viz_scene_path,valueToProcess.split(",")[1],fixture, session_match, session_selected_broadcaster);
					break;
					
				case "POPULATE-SCOREBUGSTATS":
					
					switch(valueToProcess.toUpperCase()) {
					
					case "TEAM_NAME": case"HOME": case"AWAY": case"MATCH_HOME": case"MATCH_AWAY": case"MATCH_POINT": case"SET_POINT": case "CATEGORY":
						if(valueToProcess.toUpperCase().equalsIgnoreCase("CATEGORY")) {
							category_onscreen = "CATEGORY";
						}
						
						if(which_graphics_onscreen.equalsIgnoreCase("FOREHAND_WINNER" ) || which_graphics_onscreen.equalsIgnoreCase("FOREHAND_ERROR")  || 
								which_graphics_onscreen.equalsIgnoreCase("BACKHAND_WINNER") || which_graphics_onscreen.equalsIgnoreCase("BACKHAND_ERROR")) {
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
		case "ANIMATE-IN-LT_SINGLE_MATCH_PROMO": case "ANIMATE-IN-LT_DOUBLE_MATCH_PROMO": case "ANIMATE-IN-SQUADS": case "ANIMATE-IN-NAMESUPER_PLAYER": case "ANIMATE-IN-POINTS_TABLE": case "ANIMATE-IN-MANUAL_GRAPHIC": case "CLEAR-ALL":
		case "ANIMATE-IN-RULES": case "ANIMATE-IN-BUG_SUPER": case "ANIMATE-IN-SCHEDULE": case "ANIMATE-OUT-CATEGORY": case "ANIMATE-IN-TIEID-DOUBLE":
			switch(session_selected_broadcaster) {
			case "DOAD_In_House_Everest":
				switch (whatToProcess.toUpperCase()) {
				case "ANIMATE-IN-SCOREBUG":
					if(session_match.getSets() == null) {
						this_doad.processAnimation(print_writer, "In", "START", session_selected_broadcaster);
						if(session_match.getMatch().getTrumpHomeMatch() == 1 && session_match.getMatch().getTrumpAwayMatch() == 0) {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vTrump " + "1" +";");
							TimeUnit.SECONDS.sleep(7);
							this_doad.processAnimation(print_writer, "TrumpIn", "START", session_selected_broadcaster);
						}
						else if(session_match.getMatch().getTrumpHomeMatch() == 0 && session_match.getMatch().getTrumpAwayMatch() == 1) {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vTrump " + "2" +";");
							TimeUnit.SECONDS.sleep(7);
							this_doad.processAnimation(print_writer, "TrumpIn", "START", session_selected_broadcaster);
						}
						else if(session_match.getMatch().getTrumpHomeMatch() == 1 && session_match.getMatch().getTrumpAwayMatch() == 1) {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vTrump " + "3" +";");
							TimeUnit.SECONDS.sleep(7);
							this_doad.processAnimation(print_writer, "TrumpIn", "START", session_selected_broadcaster);
						}
						else {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vTrump " + "0" +";");
						}
						
						is_ScoreBug_on_Screen = true;
					}
					else {
						//this_doad.processAnimation(print_writer, "OtherInfoOut", "SHOW 0.0", session_selected_broadcaster);
						this_doad.processAnimation(print_writer, "In", "START", session_selected_broadcaster);
						
						TimeUnit.SECONDS.sleep(3);	
						this_doad.processAnimation(print_writer, "Score1In", "START", session_selected_broadcaster);
						this_doad.processAnimation(print_writer, "Score2In", "START", session_selected_broadcaster);
						this_doad.processAnimation(print_writer, "Score3In", "START", session_selected_broadcaster);
						
						if(session_match.getMatch().getTrumpHomeMatch() == 1 && session_match.getMatch().getTrumpAwayMatch() == 0) {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vTrump " + "1" +";");
							TimeUnit.SECONDS.sleep(4);
							//this_doad.processAnimation(print_writer, "TrumpIn", "START", session_selected_broadcaster);
							this_doad.processAnimation(print_writer, "TrumpLoop", "START", session_selected_broadcaster);
						}
						else if(session_match.getMatch().getTrumpHomeMatch() == 0 && session_match.getMatch().getTrumpAwayMatch() == 1) {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vTrump " + "2" +";");
							TimeUnit.SECONDS.sleep(4);
							//this_doad.processAnimation(print_writer, "TrumpIn", "START", session_selected_broadcaster);
							this_doad.processAnimation(print_writer, "TrumpLoop", "START", session_selected_broadcaster);
						}
						else if(session_match.getMatch().getTrumpHomeMatch() == 1 && session_match.getMatch().getTrumpAwayMatch() == 1) {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vTrump " + "3" +";");
							TimeUnit.SECONDS.sleep(4);
							//this_doad.processAnimation(print_writer, "TrumpIn", "START", session_selected_broadcaster);
							this_doad.processAnimation(print_writer, "TrumpLoop", "START", session_selected_broadcaster);
							//TimeUnit.SECONDS.sleep(5);
							//this_doad.processAnimation(print_writer, "TrumpOut", "START", session_selected_broadcaster);
						}
						else {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vTrump " + "0" +";");
						}
						//TimeUnit.SECONDS.sleep(2);
						/*if(session_match.getSets().get(0).getStatus().equalsIgnoreCase("START") ||  session_match.getSets().get(0).getStatus().equalsIgnoreCase("END")) {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vSet " + "1" +";");
							this_doad.processAnimation(print_writer, "Score1In", "START", session_selected_broadcaster);
						}
						if(session_match.getSets().get(1).getStatus().equalsIgnoreCase("START") ||  session_match.getSets().get(1).getStatus().equalsIgnoreCase("END")) {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vSet " + "2" +";");
							this_doad.processAnimation(print_writer, "Score2In", "START", session_selected_broadcaster);
						}
						if(session_match.getSets().get(2).getStatus().equalsIgnoreCase("START") ||  session_match.getSets().get(2).getStatus().equalsIgnoreCase("END")) {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vSet " + "3" +";");
							this_doad.processAnimation(print_writer, "Score3In", "START", session_selected_broadcaster);
						}*/
						/*TimeUnit.SECONDS.sleep(3);	
						this_doad.processAnimation(print_writer, "Score1In", "START", session_selected_broadcaster);
						this_doad.processAnimation(print_writer, "Score2In", "START", session_selected_broadcaster);
						this_doad.processAnimation(print_writer, "Score3In", "START", session_selected_broadcaster);*/
						
						//TimeUnit.SECONDS.sleep(5);
						//this_doad.processAnimation(print_writer, "TrumpOut", "START", session_selected_broadcaster);
						is_ScoreBug_on_Screen = true;
					}
					which_graphics_onscreen = "SCOREBUG";
					break;
				case "ANIMATE-IN-SINGLE-L3_MATCHID":
					
					this_doad.processAnimation(print_writer, "In", "START", session_selected_broadcaster);
					print_writer.println("LAYER1*EVEREST*STAGE START;");
					which_graphics_onscreen = "SINGLE_L3_MATCHID";
					break;
				case "ANIMATE-IN-SINGLE-FF_MATCHID":
					this_doad.processAnimation(print_writer, "In", "START", session_selected_broadcaster);
					print_writer.println("LAYER1*EVEREST*STAGE START;");
					which_graphics_onscreen = "SINGLE_FF_MATCHID";
					break;
				case "ANIMATE-IN-DOUBLE-L3_MATCHID":
					this_doad.processAnimation(print_writer, "In", "START", session_selected_broadcaster);
					print_writer.println("LAYER1*EVEREST*STAGE START;");
					which_graphics_onscreen = "DOUBLE_L3_MATCHID";
					break;
				case "ANIMATE-IN-DOUBLE-FF_MATCHID":
					this_doad.processAnimation(print_writer, "In", "START", session_selected_broadcaster);
					print_writer.println("LAYER1*EVEREST*STAGE START;");
					which_graphics_onscreen = "DOUBLE_FF_MATCHID";
					break;
				case "ANIMATE-IN-L3_TIEID":
					this_doad.processAnimation(print_writer, "In", "START", session_selected_broadcaster);
					print_writer.println("LAYER1*EVEREST*STAGE START;");
					which_graphics_onscreen = "L3_TIEID";
					break;
				case "ANIMATE-IN-FF_TIEID":
					this_doad.processAnimation(print_writer, "In", "START", session_selected_broadcaster);
					print_writer.println("LAYER1*EVEREST*STAGE START;");
					which_graphics_onscreen = "FF_TIEID";
					break;
				case "ANIMATE-IN-SIDES":
					this_doad.processAnimation(print_writer, "In", "START", session_selected_broadcaster);
					print_writer.println("LAYER1*EVEREST*STAGE START;");
					which_graphics_onscreen = "SIDES";
					break;
				case "ANIMATE-IN-SUPER":
					this_doad.processAnimation(print_writer, "In", "START", session_selected_broadcaster);
					print_writer.println("LAYER1*EVEREST*STAGE START;");
					which_graphics_onscreen = "SUPER";
					break;
				case "ANIMATE-IN-BUG_SUPER":
					this_doad.processAnimation(print_writer, "In", "START", session_selected_broadcaster);
					print_writer.println("LAYER1*EVEREST*STAGE START;");
					which_graphics_onscreen = "BUG_SUPER";
					break;
				case "ANIMATE-IN-NAMESUPER_PLAYER":
					this_doad.processAnimation(print_writer, "In", "START", session_selected_broadcaster);
					print_writer.println("LAYER1*EVEREST*STAGE START;");
					which_graphics_onscreen = "NAMESUPER_PLAYER";
					break;
				case "ANIMATE-IN-PLAYER_PROFILE":
					this_doad.processAnimation(print_writer, "In", "START", session_selected_broadcaster);
					print_writer.println("LAYER1*EVEREST*STAGE START;");
					which_graphics_onscreen = "PLAYER_PROFILE";
					break;
				case "ANIMATE-IN-ORDER_OF_PLAY":
					this_doad.processAnimation(print_writer, "In", "START", session_selected_broadcaster);
					print_writer.println("LAYER1*EVEREST*STAGE START;");
					which_graphics_onscreen = "ORDER_OF_PLAY";
					break;
				case "ANIMATE-IN-FF_TIE_PROMO":
					this_doad.processAnimation(print_writer, "In", "START", session_selected_broadcaster);
					print_writer.println("LAYER1*EVEREST*STAGE START;");
					which_graphics_onscreen = "FF_TIE_PROMO";
					break;
				case "ANIMATE-IN-L3_TIE_PROMO":
					this_doad.processAnimation(print_writer, "In", "START", session_selected_broadcaster);
					print_writer.println("LAYER1*EVEREST*STAGE START;");
					which_graphics_onscreen = "L3_TIE_PROMO";
					break;
				case "ANIMATE-IN-FF_SINGLE_MATCH_PROMO":
					this_doad.processAnimation(print_writer, "In", "START", session_selected_broadcaster);
					print_writer.println("LAYER1*EVEREST*STAGE START;");
					which_graphics_onscreen = "FF_SINGLE_MATCH_PROMO";
					break;
				case "ANIMATE-IN-FF_DOUBLE_MATCH_PROMO":
					this_doad.processAnimation(print_writer, "In", "START", session_selected_broadcaster);
					print_writer.println("LAYER1*EVEREST*STAGE START;");
					which_graphics_onscreen = "FF_DOUBLE_MATCH_PROMO";
					break;
				case "ANIMATE-IN-LT_SINGLE_MATCH_PROMO":
					this_doad.processAnimation(print_writer, "In", "START", session_selected_broadcaster);
					print_writer.println("LAYER1*EVEREST*STAGE START;");
					which_graphics_onscreen = "LT_SINGLE_MATCH_PROMO";
					break;
				case "ANIMATE-IN-LT_DOUBLE_MATCH_PROMO":
					this_doad.processAnimation(print_writer, "In", "START", session_selected_broadcaster);
					print_writer.println("LAYER1*EVEREST*STAGE START;");
					which_graphics_onscreen = "LT_DOUBLE_MATCH_PROMO";
					break;
				case "ANIMATE-IN-SQUADS":
					this_doad.processAnimation(print_writer, "In", "START", session_selected_broadcaster);
					print_writer.println("LAYER1*EVEREST*STAGE START;");
					which_graphics_onscreen = "SQUADS";
					break;
				case "ANIMATE-IN-POINTS_TABLE":
					this_doad.processAnimation(print_writer, "In", "START", session_selected_broadcaster);
					print_writer.println("LAYER1*EVEREST*STAGE START;");
					which_graphics_onscreen = "POINTS_TABLE";
					break;
				case "ANIMATE-IN-TEAMS_LOGO":
					this_doad.processAnimation(print_writer, "In", "START", session_selected_broadcaster);
					print_writer.println("LAYER1*EVEREST*STAGE START;");
					which_graphics_onscreen = "TEAMS_LOGO";
					break;
				case "ANIMATE-IN-SUPER_MATCH":
					this_doad.processAnimation(print_writer, "In", "START", session_selected_broadcaster);
					print_writer.println("LAYER1*EVEREST*STAGE START;");
					which_graphics_onscreen = "SUPER_MATCH";
					break;
				case "ANIMATE-IN-SUPER_MATCH1":
					this_doad.processAnimation(print_writer, "In", "START", session_selected_broadcaster);
					print_writer.println("LAYER1*EVEREST*STAGE START;");
					which_graphics_onscreen = "SUPER_MATCH1";
					break;
				case "ANIMATE-IN-SUPER_MATCH2":
					this_doad.processAnimation(print_writer, "In", "START", session_selected_broadcaster);
					print_writer.println("LAYER1*EVEREST*STAGE START;");
					which_graphics_onscreen = "SUPER_MATCH2";
					break;
				case "ANIMATE-IN-RULES":
					this_doad.processAnimation(print_writer, "In", "START", session_selected_broadcaster);
					print_writer.println("LAYER1*EVEREST*STAGE START;");
					which_graphics_onscreen = "RULES";
					break;
				case "ANIMATE-IN-SCHEDULE":
					this_doad.processAnimation(print_writer, "In", "START", session_selected_broadcaster);
					print_writer.println("LAYER1*EVEREST*STAGE START;");
					which_graphics_onscreen = "SCHEDULE";
					break;
				case "ANIMATE-IN-TIEID-DOUBLE":
					this_doad.processAnimation(print_writer, "In", "START", session_selected_broadcaster);
					print_writer.println("LAYER1*EVEREST*STAGE START;");
					which_graphics_onscreen = "TIEID_DOUBLE";
					break;
				case "ANIMATE-IN-MANUAL_GRAPHIC":
					print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In START;");
					TimeUnit.SECONDS.sleep(2);
					print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW ON;");
					print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT_PATH C:/Temp/Preview.bmp;");
					print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT 1920 1080;");
					print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW OFF;");
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
					case "LT_DOUBLE_MATCH_PROMO": case "SQUADS": case "NAMESUPER_PLAYER": case "POINTS_TABLE": case "RULES": case "SCHEDULE": case "BUG_SUPER": case "TIEID_DOUBLE":
						this_doad.processAnimation(print_writer, "Out", "START", session_selected_broadcaster);
						which_graphics_onscreen = "";
						break;
					}
					break;
				case "ANIMATE-OUT-CATEGORY":
					switch(category_onscreen) {
					case "CATEGORY":
						this_doad.processAnimation(print_writer, "TeamOut", "START", session_selected_broadcaster);
						category_onscreen = "";
						which_graphics_onscreen = "SCOREBUG";
						break;
					}
					break;
				case "ANIMATE-OUT-STAT":
					switch(which_graphics_onscreen) {
					case "FOREHAND_WINNER": case "FOREHAND_ERROR": case "BACKHAND_WINNER": case "BACKHAND_ERROR":
						this_doad.processAnimation(print_writer, "OtherInfoOut", "START", session_selected_broadcaster);
						which_graphics_onscreen = "SCOREBUG";
						break;
					case "TEAM_NAME":
						this_doad.processAnimation(print_writer, "TeamOut", "START", session_selected_broadcaster);
						which_graphics_onscreen = "SCOREBUG";
						break;
					}
					break;
				case "CLEAR-ALL":
					//System.out.println("3 = "  + which_graphic_on_screen);
					print_writer.println("LAYER1*EVEREST*SINGLE_SCENE CLEAR;");
					which_graphics_onscreen = "";
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
}