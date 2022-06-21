package com.badminton.broadcaster;

import java.io.PrintWriter;

//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.concurrent.TimeUnit;

import com.badminton.model.*;
import com.badminton.containers.Scene;
//import com.badminton.util.BadmintonFunctions;
import com.badminton.util.BadmintonUtil;

public class Doad extends Scene{

	private String status;
	private String slashOrDash = "-";
	//private String logo_path = "D:\\DOAD_In_House_Everest\\Textures and Images\\Logos\\";
	//private String photo_path = "D:\\DOAD_In_House_Everest\\Textures and Images\\Photos\\MEDIUM\\";
	//private String backslash = "\\";
	
	public Doad() {
		super();
	}

	public Doad(String scene_path) {
		super(scene_path);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public void populateScoreBug(PrintWriter print_writer,String viz_sence_path,BadmintonMatch match, String selectedbroadcaster) 
	{
		if (match == null) {
			this.status = "ERROR: Match is null";
		} else {
			
			System.out.println("on Strike player id-" + match.getOnStrikePlayerId());
			

			this.status = BadmintonUtil.SUCCESSFUL;	
		}
	}
	public void processAnimation(PrintWriter print_writer, String animationName,String animationCommand, String which_broadcaster)
	{
		switch(which_broadcaster) {
		case "DOAD_In_House_Everest":
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*" + animationName + " " + animationCommand + ";");
			
			break;
		}
		
	}
	public String toString() {
		return "Doad [status=" + status + ", slashOrDash=" + slashOrDash + "]";
	}	
}