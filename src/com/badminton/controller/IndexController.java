package com.badminton.controller;

import java.io.File;
import java.io.FileFilter;

import java.io.IOException;

import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
	//public static EventFile session_event_file;
	public static Socket session_socket;
	public static Doad this_doad;
	public static PrintWriter print_writer;
	//string static png_extension = '.png';
	String session_selected_broadcaster,which_graphics_onscreen,viz_scene_path;
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
			@RequestParam(value = "selectedMatch", required = false, defaultValue = "") String selectedMatch,
			@RequestParam(value = "vizIPAddress", required = false, defaultValue = "") String vizIPAddresss,
			@RequestParam(value = "vizPortNumber", required = false, defaultValue = "") int vizPortNumber,
			@RequestParam(value = "vizScene", required = false, defaultValue = "") String vizScene) 
					throws UnknownHostException, IOException, JAXBException, IllegalAccessException, InvocationTargetException 
	{
		which_graphics_onscreen = "";
		is_ScoreBug_on_Screen = false;
		this_doad = new Doad();
		session_selected_broadcaster = select_broadcaster;
		
		System.out.println("Broadcaster in IndexController-output:" + select_broadcaster);
		
		session_socket = new Socket(vizIPAddresss, Integer.valueOf(vizPortNumber));
		print_writer = new PrintWriter(session_socket.getOutputStream(), true);
		session_Configurations = new Configurations(selectedMatch, select_broadcaster, vizIPAddresss, vizPortNumber, vizScene);
		
		JAXBContext.newInstance(Configurations.class).createMarshaller().marshal(session_Configurations, 
				new File(BadmintonUtil.BADMINTON_DIRECTORY + BadmintonUtil.CONFIGURATIONS_DIRECTORY + BadmintonUtil.OUTPUT_XML));

		/*session_event_file = (EventFile) JAXBContext.newInstance(EventFile.class).createUnmarshaller().unmarshal(
				new File(CricketUtil.CRICKET_DIRECTORY + CricketUtil.EVENT_DIRECTORY + selectedMatch));*/
		
		session_match = (BadmintonMatch) JAXBContext.newInstance(BadmintonMatch.class).createUnmarshaller().unmarshal(
				new File(BadmintonUtil.BADMINTON_DIRECTORY + BadmintonUtil.MATCHES_DIRECTORY + selectedMatch));
		//session_match.setMatchFileName(selectedMatch);
		//session_match.setMatch_file_timestamp(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()));
		
		//session_match.setEvents(session_event_file.getEvents());
		
		model.addAttribute("session_match", session_match);
		model.addAttribute("session_selected_broadcaster", session_selected_broadcaster);
		
		return "output";
	}

	@RequestMapping(value = {"/processBadmintonProcedures"}, method={RequestMethod.GET,RequestMethod.POST})    
	public @ResponseBody String processBadmintonProcedures(
			@RequestParam(value = "whatToProcess", required = false, defaultValue = "") String whatToProcess,
			@RequestParam(value = "valueToProcess", required = false, defaultValue = "") String valueToProcess)  
					throws IOException, IllegalAccessException, InvocationTargetException, JAXBException, InterruptedException 
	{
		
		
		
		switch (whatToProcess.toUpperCase()) {
		
		case "SCOREBUG_GRAPHICS-OPTIONS":
			return JSONObject.fromObject(session_match).toString();
			
		/*case "READ-MATCH-AND-POPULATE":
			if(!valueToProcess.equalsIgnoreCase(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(
					new File(BadmintonUtil.BADMINTON_DIRECTORY + BadmintonUtil.MATCHES_DIRECTORY + session_match.getMatch_file_timestamp()).lastModified())))
			{
				session_match = CricketFunctions.populateMatchVariables(cricketService, (Match) JAXBContext.newInstance(Match.class).createUnmarshaller().unmarshal(
						new File(CricketUtil.CRICKET_DIRECTORY + CricketUtil.MATCHES_DIRECTORY + session_match.getMatchFileName())));
				
				session_match.setMatch_file_timestamp(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(
						new File(BadmintonUtil.BADMINTON_DIRECTORY + BadmintonUtil.MATCHES_DIRECTORY + session_match.getMatch_file_timestamp()).lastModified()));
				
				session_event_file = (EventFile) JAXBContext.newInstance(EventFile.class).createUnmarshaller().unmarshal(
						new File(CricketUtil.CRICKET_DIRECTORY + CricketUtil.EVENT_DIRECTORY + session_match.getMatch_file_timestamp()));
				
				session_match.setEvents(session_event_file.getEvents());
				switch(which_graphics_onscreen) {
				
					
				}
				
				return JSONObject.fromObject(session_match).toString();
			}
			else {
				return JSONObject.fromObject(null).toString();
			}*/
			
		case "POPULATE-SCOREBUG":
			System.out.println();
			viz_scene_path = valueToProcess;
			new Scene(viz_scene_path).scene_load(print_writer,session_selected_broadcaster,viz_scene_path);
			System.out.println("Broadcaster in IndexController-scorebug" + session_selected_broadcaster);
			switch(session_selected_broadcaster) {
			case "DOAD_In_House_Everest":
				switch(whatToProcess.toUpperCase()) {
				case "POPULATE-SCOREBUG":
					this_doad.populateScoreBug(print_writer, viz_scene_path, session_match, session_selected_broadcaster);
					break;
				}
				return JSONObject.fromObject(this_doad).toString();
			}
			
		case "ANIMATE-IN-SCOREBUG": case "ANIMATE-OUT":
			System.out.println("Broadcaster in IndexController-animate-scorebug" + session_selected_broadcaster);
			switch(session_selected_broadcaster) {
			case "DOAD_In_House_Everest":
				switch (whatToProcess.toUpperCase()) {
				case "ANIMATE-IN-SCOREBUG":
					this_doad.processAnimation(print_writer, "In", "START", session_selected_broadcaster);
					is_ScoreBug_on_Screen = true;
					break;
					
				case "ANIMATE-OUT":
					switch(which_graphics_onscreen) {
					case "SCOREBUG":
						this_doad.processAnimation(print_writer, "In", "CONTINUE", session_selected_broadcaster);
						is_ScoreBug_on_Screen = false;
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
}