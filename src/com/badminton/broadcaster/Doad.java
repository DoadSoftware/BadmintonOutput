package com.badminton.broadcaster;


import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
	
	private String logo_path = "D:\\DOAD_In_House_Everest\\Everest_Sports\\Everest_GBPL\\Logos\\";
	//private String center_path = "D:\\DOAD_In_House_Everest\\Everest_Sports\\Everest_GBPL\\Photos\\MEDIUM\\";
	private String left_path = "D:\\DOAD_In_House_Everest\\Everest_Sports\\Everest_GBPL\\Photos\\LEFT\\";
	private String right_path = "D:\\DOAD_In_House_Everest\\Everest_Sports\\Everest_GBPL\\Photos\\RIGHT\\";
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

	public void populateScoreBug(PrintWriter print_writer,String viz_sence_path,String Stats,BadmintonMatch Bad_match,Team tm, String selectedbroadcaster)
	{
		if (Bad_match == null) {
			this.status = "ERROR: Match is null";
		} else {
			
			populateScoreBugName(false,print_writer, viz_sence_path, tm, Bad_match, selectedbroadcaster);
			//populateScoreBugStat(false,print_writer, viz_sence_path, Stats, Bad_match, selectedbroadcaster);
			this.status = BadmintonUtil.SUCCESSFUL;	
		}
	}	
	
	public void populateScoreBugName(boolean is_this_updating, PrintWriter print_writer,String viz_sence_path, Team tm, BadmintonMatch Bad_match, String selectedbroadcaster) 
	{
		if (Bad_match == null) {
			this.status = "ERROR: Match is null";
		} else {
			
			if(Bad_match.getGoldenPointsPlayerId() == Bad_match.getMatch().getHomeFirstPlayerId()) {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vGoldenPointOption " + "1" +";");
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vGoldenPoint " + "1" +";");
				//print_writer.println("LAYER1*EVEREST*TREEVIEW*GoldenPoint*CONTAINER SET ACTIVE 1;");
			}
			else if(Bad_match.getGoldenPointsPlayerId() == Bad_match.getMatch().getAwayFirstPlayerId()) {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vGoldenPointOption " + "1" +";");
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vGoldenPoint " + "2" +";");
				//print_writer.println("LAYER1*EVEREST*TREEVIEW*GoldenPoint*CONTAINER SET ACTIVE 1;");
			}
			else if(Bad_match.getGoldenPointsPlayerId() == 0) {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vGoldenPointOption " + "1" +";");
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vGoldenPoint " + "0" +";");
			}
			//print_writer.println("LAYER1*EVEREST*TREEVIEW*StartLogo_OR_TRUMP*CONTAINER SET ACTIVE 1;");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vLogo " + "0" +";");
			
			for(Player hp : Bad_match.getMatch().getHomePlayers()) {
				if(hp.getPlayerId() == Bad_match.getOnStrikePlayerId()) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vService " + "1" +";");
				}
			}
			
			for(Player ap : Bad_match.getMatch().getAwayPlayers()) {
				if(ap.getPlayerId() == Bad_match.getOnStrikePlayerId()) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vService " + "2" +";");
				}
			}
			if(is_this_updating == false) {
				
				if(Bad_match.getMatch().getHomePlayers() !=null && Bad_match.getMatch().getHomePlayers().size() > 0) {
					for(int a=1; a <= Bad_match.getMatch().getHomePlayers().size();a++){
						if(a==1){
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerName1 " + Bad_match.getMatch().getHomePlayers().get(Bad_match.getMatch().getHomePlayers().size() - a).getTicker_name() +";");
						}
						else if(a==2){
						int j=a-1;
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerName1 " + Bad_match.getMatch().getHomePlayers().get(Bad_match.getMatch().getHomePlayers().size() - a).getTicker_name() + "/" 
						+ Bad_match.getMatch().getHomePlayers().get(Bad_match.getMatch().getHomePlayers().size() - j).getTicker_name() + ";");
						
						}
						else if(a==3){
							int j=a-1;
							int k=a-2;
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerName1 " + Bad_match.getMatch().getHomePlayers().get(Bad_match.getMatch().getHomePlayers().size() - a).getTicker_name() + "/" 
									+ Bad_match.getMatch().getHomePlayers().get(Bad_match.getMatch().getHomePlayers().size() - j).getTicker_name() + "/" 
									+ Bad_match.getMatch().getHomePlayers().get(Bad_match.getMatch().getHomePlayers().size() - k).getTicker_name() + ";");
							
						}
						
					}
				}				
								
				if(Bad_match.getMatch().getAwayPlayers() !=null && Bad_match.getMatch().getAwayPlayers().size() > 0) {
					for(int a=1; a <= Bad_match.getMatch().getAwayPlayers().size();a++){
						if(a == 1){
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerName2 " + Bad_match.getMatch().getAwayPlayers().get(Bad_match.getMatch().getAwayPlayers().size() - a).getTicker_name() +";");
						}
						
						else if(a == 2){
						int j=a-1;
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerName2 " + Bad_match.getMatch().getAwayPlayers().get(Bad_match.getMatch().getAwayPlayers().size() - a).getTicker_name() + "/" 
						+ Bad_match.getMatch().getAwayPlayers().get(Bad_match.getMatch().getAwayPlayers().size() - j).getTicker_name() + ";");
						}
						
						else if(a == 3){
							int j=a-1;
							int k=a-2;
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerName2 " + Bad_match.getMatch().getAwayPlayers().get(Bad_match.getMatch().getAwayPlayers().size() - a).getTicker_name() + "/" 
									+ Bad_match.getMatch().getAwayPlayers().get(Bad_match.getMatch().getAwayPlayers().size() - j).getTicker_name() + "/" 
									+ Bad_match.getMatch().getAwayPlayers().get(Bad_match.getMatch().getAwayPlayers().size() - k).getTicker_name() + ";");
						}
						
					}
				}
				if(Bad_match.getMatch().getHomeTeam().getTeamcolor() == tm.getTeamcolor()) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTeamColor1 " + tm.getTeamcolor() +";");
				}
			
				if(Bad_match.getMatch().getAwayTeam().getTeamcolor() == tm.getTeamcolor()) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTeamColor2 " + tm.getTeamcolor() +";");
				}
			}
			
			if(Bad_match.getSets() == null ) {
				if(is_this_updating == false) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vSet " + "0" +";");
				}
			}
			else {
				
				for(Set st : Bad_match.getSets()) {
					if(st.getSetNumber() == 1) {
						if(st.getStatus().equalsIgnoreCase("START") || st.getStatus().equalsIgnoreCase("END")) {
							if((st.getHomeTeamTotalScore() == 0 && st.getAwayTeamTotalScore() == 1) || (st.getHomeTeamTotalScore() == 1 && st.getAwayTeamTotalScore() == 0)) {
								
								print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Score1In START;");
								
							}
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vSet " + "1" +";");
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScoreTeam1_R1 " + st.getHomeTeamTotalScore() +";");
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScoreTeam2_R1 " + st.getAwayTeamTotalScore() +";");
						}
						
					}
					if(st.getSetNumber() == 2) {
						if(st.getStatus().equalsIgnoreCase("START") || st.getStatus().equalsIgnoreCase("END")) {
							if((st.getHomeTeamTotalScore() == 0 && st.getAwayTeamTotalScore() == 1) || (st.getHomeTeamTotalScore() == 1 && st.getAwayTeamTotalScore() == 0)) {
								print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Score2In START;");
								
							}
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vSet " + "2" +";");
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScoreTeam1_R2 " + st.getHomeTeamTotalScore() +";");
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScoreTeam2_R2 " + st.getAwayTeamTotalScore() +";");
						}
					}
					if(st.getSetNumber() == 3) {
						if(st.getStatus().equalsIgnoreCase("START") || st.getStatus().equalsIgnoreCase("END")) {
							if((st.getHomeTeamTotalScore() == 0 && st.getAwayTeamTotalScore() == 1) || (st.getHomeTeamTotalScore() == 1 && st.getAwayTeamTotalScore() == 0)) {
								print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Score3In START;");
							}
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vSet " + "3" +";");

							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScoreTeam1_R3 " + st.getHomeTeamTotalScore() +";");
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScoreTeam2_R3 " + st.getAwayTeamTotalScore() +";");
						}
					}
				}
			}
			
		}
		this.status = BadmintonUtil.SUCCESSFUL;	
	}
	public void populateScoreBugStat(boolean is_this_updating,PrintWriter print_writer,String viz_sence_path,String Stats,BadmintonMatch Bad_match, String selectedbroadcaster) 
	{
		if (Bad_match == null) {
			this.status = "ERROR: Match is null";
		} else {
			
			switch(Stats.toUpperCase()) {
			case "FOREHAND_WINNER":
				for(Stats sta : Bad_match.getStats()) {
					if(Bad_match.getStats() !=null && Bad_match.getStats().size() > 0) {
						if(sta.getStatType().equalsIgnoreCase(BadmintonUtil.FOREHAND_WINNER)) {
							if(is_this_updating == false) {
								print_writer.println("LAYER1*EVEREST*TREEVIEW*NEW_GROUP*CONTAINER SET ACTIVE 1;");
								print_writer.println("LAYER1*EVEREST*TREEVIEW*OthersNameGRP*CONTAINER SET ACTIVE 1;");
								print_writer.println("LAYER1*EVEREST*TREEVIEW*OtherDatsGrp*CONTAINER SET ACTIVE 1;");
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vExtra " + "1" +";");
							}
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tExtraHead " + "FOREHAND WINNER" +";");
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tExtraData01 " + sta.getHomeStatCount() +";");
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tExtraData02 " + sta.getAwayStatCount() +";");
						}
					}
				}
				break;
			case "FOREHAND_ERROR":
				for(Stats sta : Bad_match.getStats()) {
					if(Bad_match.getStats() !=null && Bad_match.getStats().size() > 0) {
						if(sta.getStatType().toUpperCase().equalsIgnoreCase(BadmintonUtil.FOREHAND_ERRORS)) {
							if(is_this_updating == false) {
								print_writer.println("LAYER1*EVEREST*TREEVIEW*NEW_GROUP*CONTAINER SET ACTIVE 1;");
								print_writer.println("LAYER1*EVEREST*TREEVIEW*OthersNameGRP*CONTAINER SET ACTIVE 1;");
								print_writer.println("LAYER1*EVEREST*TREEVIEW*OtherDatsGrp*CONTAINER SET ACTIVE 1;");
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vExtra " + "1" +";");
							}
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tExtraHead " + "FOREHAND ERROR" +";");
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tExtraData01 " + sta.getHomeStatCount() +";");
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tExtraData02 " + sta.getAwayStatCount() +";");
						}
					}
				}
				break;
			case "BACKHAND_WINNER":
				for(Stats sta : Bad_match.getStats()) {
					if(Bad_match.getStats() !=null && Bad_match.getStats().size() > 0) {
						if(sta.getStatType().toUpperCase().equalsIgnoreCase(BadmintonUtil.BACKHAND_WINNER)) {
							if(is_this_updating == false) {
								print_writer.println("LAYER1*EVEREST*TREEVIEW*NEW_GROUP*CONTAINER SET ACTIVE 1;");
								print_writer.println("LAYER1*EVEREST*TREEVIEW*OthersNameGRP*CONTAINER SET ACTIVE 1;");
								print_writer.println("LAYER1*EVEREST*TREEVIEW*OtherDatsGrp*CONTAINER SET ACTIVE 1;");
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vExtra " + "1" +";");
							}
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tExtraHead " + "BACKHAND WINNER" +";");
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tExtraData01 " + sta.getHomeStatCount() +";");
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tExtraData02 " + sta.getAwayStatCount() +";");
						}
					}
				}
				break;
			case "BACKHAND_ERROR":
				for(Stats sta : Bad_match.getStats()) {
					if(Bad_match.getStats() !=null && Bad_match.getStats().size() > 0) {
						if(sta.getStatType().toUpperCase().equalsIgnoreCase(BadmintonUtil.BACKHAND_ERRORS)) {
							if(is_this_updating == false) {
								print_writer.println("LAYER1*EVEREST*TREEVIEW*NEW_GROUP*CONTAINER SET ACTIVE 1;");
								print_writer.println("LAYER1*EVEREST*TREEVIEW*OthersNameGRP*CONTAINER SET ACTIVE 1;");
								print_writer.println("LAYER1*EVEREST*TREEVIEW*OtherDatsGrp*CONTAINER SET ACTIVE 1;");
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vExtra " + "1" +";");
							}
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tExtraHead " + "BACKHAND ERROR" +";");
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tExtraData01 " + sta.getHomeStatCount() +";");
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tExtraData02 " + sta.getAwayStatCount() +";");
						}
					}
				}
				break;
			case "TEAM_NAME":
				if(is_this_updating == false) {
					//print_writer.println("LAYER1*EVEREST*TREEVIEW*TeamName*CONTAINER SET ACTIVE 1;");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*NEW_GROUP*CONTAINER SET ACTIVE 1;");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vExtra " + "0" +";");
					//print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*TeamIn START;");
				}
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTeamName01 " + Bad_match.getMatch().getHomeTeam().getFullname().toUpperCase() +";");
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTeamName02 " + Bad_match.getMatch().getAwayTeam().getFullname().toUpperCase() +";");
				break;
			case "HOME":
				if(is_this_updating == false) {
					//print_writer.println("LAYER1*EVEREST*TREEVIEW*TeamName*CONTAINER SET ACTIVE 1;");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*NEW_GROUP*CONTAINER SET ACTIVE 1;");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vExtra " + "0" +";");
					//print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*TeamIn START;");
				}
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTeamName01 " + "SET POINT" +";");
				print_writer.println("LAYER1*EVEREST*TREEVIEW*BottomTeamGRP*CONTAINER SET ACTIVE 0;");
				print_writer.println("LAYER1*EVEREST*TREEVIEW*TopTeamGRP*CONTAINER SET ACTIVE 1;");
				break;
			case "AWAY":
				if(is_this_updating == false) {
					//print_writer.println("LAYER1*EVEREST*TREEVIEW*TeamName*CONTAINER SET ACTIVE 1;");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*NEW_GROUP*CONTAINER SET ACTIVE 1;");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vExtra " + "0" +";");
					//print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*TeamIn START;");
				}
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTeamName02 " + "SET POINT" +";");
				print_writer.println("LAYER1*EVEREST*TREEVIEW*TopTeamGRP*CONTAINER SET ACTIVE 0;");
				print_writer.println("LAYER1*EVEREST*TREEVIEW*BottomTeamGRP*CONTAINER SET ACTIVE 1;");
				break;
			case "MATCH_HOME":
				if(is_this_updating == false) {
					//print_writer.println("LAYER1*EVEREST*TREEVIEW*TeamName*CONTAINER SET ACTIVE 1;");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*NEW_GROUP*CONTAINER SET ACTIVE 1;");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vExtra " + "0" +";");
					//print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*TeamIn START;");
				}
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTeamName01 " + "MATCH POINT" +";");
				print_writer.println("LAYER1*EVEREST*TREEVIEW*BottomTeamGRP*CONTAINER SET ACTIVE 0;");
				print_writer.println("LAYER1*EVEREST*TREEVIEW*TopTeamGRP*CONTAINER SET ACTIVE 1;");
				break;
			case "MATCH_AWAY":
				if(is_this_updating == false) {
					//print_writer.println("LAYER1*EVEREST*TREEVIEW*TeamName*CONTAINER SET ACTIVE 1;");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*NEW_GROUP*CONTAINER SET ACTIVE 1;");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vExtra " + "0" +";");
					//print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*TeamIn START;");
				}
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTeamName02 " + "MATCH POINT" +";");
				print_writer.println("LAYER1*EVEREST*TREEVIEW*TopTeamGRP*CONTAINER SET ACTIVE 0;");
				print_writer.println("LAYER1*EVEREST*TREEVIEW*BottomTeamGRP*CONTAINER SET ACTIVE 1;");
				break;
			}
			
			
			this.status = BadmintonUtil.SUCCESSFUL;	
		}
	}
	
	public void populateSingleL3MatchId(PrintWriter print_writer,String viz_sence_path,Fixture fx,BadmintonMatch Bad_match, String selectedbroadcaster) throws InterruptedException 
	{
		
		if (Bad_match == null) {
			this.status = "ERROR: Match is null";
		} else {
			int Set1h = 0,Set1a=0, Set2h = 0,Set2a=0;
			if((Bad_match.getMatch().getHomeTeam().getTeamId() == fx.getHometeam()) && (Bad_match.getMatch().getAwayTeam().getTeamId() == fx.getAwayteam())) {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHeader " + "TIE " + fx.getMatchnumber() + " - " + "MATCH " + Bad_match.getMatch().getMatchnumber() +";");
			}
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgHomePlayer1Image " + left_path + Bad_match.getMatch().getHomePlayers().get(0).getTicker_name() + ".png" + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgAwayPlayer1Image " + right_path + Bad_match.getMatch().getAwayPlayers().get(0).getTicker_name() + ".png" + ";");

			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHomePlayerName " + Bad_match.getMatch().getHomePlayers().get(0).getTicker_name().toUpperCase()+ ";");
			//System.out.println(Bad_match.getMatch().getHomePlayers().get(0).getFull_name().toUpperCase());
			//System.out.println(Bad_match.getMatch().getAwayPlayers().get(0).getFull_name().toUpperCase());
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tAwayPlayerName " + Bad_match.getMatch().getAwayPlayers().get(0).getTicker_name().toUpperCase() + ";");
			
			if(Bad_match.getMatch().getCategoryId() == 0 ) {
				if(Bad_match.getMatch().getSuperMatch() == 1) {
					if(Bad_match.getMatch().getTrumpHomeMatch() == 1 || Bad_match.getMatch().getTrumpAwayMatch() == 1) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader " + "SUPER MATCH"+ "-"+ "TRUMP MATCH" +";");
					}
					else {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader " + "SUPER MATCH" + ";");
					}
				}
			}
			else {
				if(Bad_match.getMatch().getCategoryId() == 1) {
					
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader " + "WOMEN'S SINGLES" +";");
					
				}
				if(Bad_match.getMatch().getCategoryId() == 2) {
					
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader " + "MEN'S DOUBLES" +";");
					
				}
				if(Bad_match.getMatch().getCategoryId() == 3) {
					
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader " + "MEN'S SINGLES" +";");
					
				}
				if(Bad_match.getMatch().getCategoryId() == 4) {
					
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader " + "MIXED DOUBLES" +";");
					
				}
			}
			if(Bad_match.getSets() == null ) {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScore_VS " + "VS" +";");
			}
			else {
				for(Set st : Bad_match.getSets()) {
					if(st.getSetNumber() == 1) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScore_VS " + st.getHomeTeamTotalScore() + "-" + st.getAwayTeamTotalScore() +";");
						Set1h = st.getHomeTeamTotalScore(); 
						Set1a = st.getAwayTeamTotalScore();
					}
					if(st.getSetNumber() == 2) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScore_VS " + Set1h + "-" + Set1a + " | " + st.getHomeTeamTotalScore() + "-" + st.getAwayTeamTotalScore() +";");
						Set2h = st.getHomeTeamTotalScore();
						Set2a = st.getAwayTeamTotalScore();
					}
					if(st.getSetNumber() == 3) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScore_VS " + Set1h + "-" + Set1a + " | " + Set2h + "-" + Set2a + " | " + st.getHomeTeamTotalScore() + "-" + st.getAwayTeamTotalScore() +";");
							
					}
					if(st.getSetNumber() == 3 && st.getStatus().equalsIgnoreCase("END") )  {
						if(Bad_match.getHomeTeamSetsWon() > Bad_match.getAwayTeamSetsWon()) {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader " + "BEAT" +";");
						}
						else {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader " + "LOST TO" +";");
						}
					}
					if(st.getSetNumber() == 2 && st.getStatus().equalsIgnoreCase("END")) {
						
						if(Bad_match.getHomeTeamSetsWon() > Bad_match.getAwayTeamSetsWon()) {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader " + "BEAT" +";");
						}
						else if(Bad_match.getHomeTeamSetsWon() < Bad_match.getAwayTeamSetsWon()) {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader " + "LOST TO" +";");
						}
					}
				}
			}
			
			if(Bad_match.getMatch().getTrumpHomeMatch() == 1) {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vHomeTrump_NOTrump " + "1" + ";");
				
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHomeTeamName " + Bad_match.getMatch().getHomeTeam().getFullname().toUpperCase() + ";");
			}
			else {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vHomeTrump_NOTrump " + "0" + ";");
				
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHomeTeamName " + Bad_match.getMatch().getHomeTeam().getFullname().toUpperCase() + ";");
			}
			if(Bad_match.getMatch().getTrumpAwayMatch() == 1) {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vAwayTrump_NOTrump " + "1" + ";");
				
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tAwayTeamName " + Bad_match.getMatch().getAwayTeam().getFullname().toUpperCase() + ";");
			}
			else {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vAwayTrump_NOTrump " + "0" + ";");
				
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tAwayTeamName " + Bad_match.getMatch().getAwayTeam().getFullname().toUpperCase() + ";");
			}
			
			//print_writer.println("LAYER1*EVEREST*TREEVIEW*Subheader1*CONTAINER SET ACTIVE 0;");
			
		print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW ON;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In STOP;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out STOP;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 106.0;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
		print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT_PATH C:/Temp/LT_SingleMatchId.bmp;");
		print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT 1920 1080;");
		TimeUnit.SECONDS.sleep(1);
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 0.0;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
		print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW OFF;");
			
		this.status = BadmintonUtil.SUCCESSFUL;	
		}
	}
	public void populateDoubleL3MatchId(PrintWriter print_writer,String viz_sence_path,Fixture fx,BadmintonMatch Bad_match, String selectedbroadcaster) throws InterruptedException 
	{
		if (Bad_match == null) {
			this.status = "ERROR: Match is null";
		} else {
			int Set1h = 0,Set1a=0, Set2h = 0,Set2a=0;
			if((Bad_match.getMatch().getHomeTeam().getTeamId() == fx.getHometeam()) && (Bad_match.getMatch().getAwayTeam().getTeamId() == fx.getAwayteam())) {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHeader " + "TIE " + fx.getMatchnumber() + " - " + "MATCH " + Bad_match.getMatch().getMatchnumber() +";");
			}
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgHomePlayer1Image " + left_path + Bad_match.getMatch().getHomePlayers().get(0).getTicker_name() + ".png" + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgHomePlayer2Image " + left_path + Bad_match.getMatch().getHomePlayers().get(1).getTicker_name() + ".png" + ";");

			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgAwayPlayer1Image " + right_path + Bad_match.getMatch().getAwayPlayers().get(0).getTicker_name() + ".png" + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgAwayPlayer2Image " + right_path + Bad_match.getMatch().getAwayPlayers().get(1).getTicker_name() + ".png" + ";");
			
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHomePlayerName " + Bad_match.getMatch().getHomePlayers().get(0).getTicker_name().toUpperCase() + " / " + Bad_match.getMatch().getHomePlayers().get(1).getTicker_name().toUpperCase() + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tAwayPlayerName " + Bad_match.getMatch().getAwayPlayers().get(0).getTicker_name().toUpperCase() + " / " + Bad_match.getMatch().getAwayPlayers().get(1).getTicker_name().toUpperCase() + ";");
			
			
			if(Bad_match.getMatch().getCategoryId() == 0 ) {
				if(Bad_match.getMatch().getSuperMatch() == 1) {
					if(Bad_match.getMatch().getTrumpHomeMatch() == 1 || Bad_match.getMatch().getTrumpAwayMatch() == 1) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader " + "SUPER MATCH"+ "-"+ "TRUMP MATCH" +";");
					}
					else {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader " + "SUPER MATCH" + ";");
					}
				}
			}
			else {
				if(Bad_match.getMatch().getCategoryId() == 1) {
					
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader " + "WOMEN'S SINGLES" +";");
					
				}
				if(Bad_match.getMatch().getCategoryId() == 2) {
					
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader " + "MEN'S DOUBLES" +";");
					
				}
				if(Bad_match.getMatch().getCategoryId() == 3) {
					
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader " + "MEN'S SINGLES" +";");
					
				}
				if(Bad_match.getMatch().getCategoryId() == 4) {
					
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader " + "MIXED DOUBLES" +";");
					}
			}
			
			if(Bad_match.getSets() == null ) {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScore_VS " + "VS" +";");
			}
			else {
				for(Set st : Bad_match.getSets()) {
					if(st.getSetNumber() == 1) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScore_VS " + st.getHomeTeamTotalScore() + "-" + st.getAwayTeamTotalScore() +";");
						Set1h = st.getHomeTeamTotalScore(); 
						Set1a = st.getAwayTeamTotalScore();
					}
					if(st.getSetNumber() == 2) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScore_VS " + Set1h + "-" + Set1a + " | " + st.getHomeTeamTotalScore() + "-" + st.getAwayTeamTotalScore() +";");
						Set2h = st.getHomeTeamTotalScore();
						Set2a = st.getAwayTeamTotalScore();
					}
					if(st.getSetNumber() == 3) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScore_VS " + Set1h + "-" + Set1a + " | " + Set2h + "-" + Set2a + " | " + st.getHomeTeamTotalScore() + "-" + st.getAwayTeamTotalScore() +";");
					
					}
					if(st.getSetNumber() == 3 && st.getStatus().equalsIgnoreCase("END") )  {
						if(Bad_match.getHomeTeamSetsWon() > Bad_match.getAwayTeamSetsWon()) {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader " + "BEAT" +";");
						}
						else {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader " + "LOST TO" +";");
						}
					}
					if(st.getSetNumber() == 2 && st.getStatus().equalsIgnoreCase("END")) {
						
						if(Bad_match.getHomeTeamSetsWon() > Bad_match.getAwayTeamSetsWon()) {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader " + "BEAT" +";");
						}
						else if(Bad_match.getHomeTeamSetsWon() < Bad_match.getAwayTeamSetsWon()) {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader " + "LOST TO" +";");
						}
					}
				}
			}
			
			if(Bad_match.getMatch().getTrumpHomeMatch() == 1) {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vHomeTrump_NOTrump " + "1" + ";");
				
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHomeTeamName " + Bad_match.getMatch().getHomeTeam().getFullname().toUpperCase() + ";");
			}
			else if(Bad_match.getMatch().getTrumpHomeMatch() == 0) {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vHomeTrump_NOTrump " + "0" + ";");
				
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHomeTeamName " + Bad_match.getMatch().getHomeTeam().getFullname().toUpperCase() + ";");
			}
			if(Bad_match.getMatch().getTrumpAwayMatch() == 1) {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vAwayTrump_NOTrump " + "1" + ";");
				
				
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tAwayTeamName " + Bad_match.getMatch().getAwayTeam().getFullname().toUpperCase() + ";");
			}
			else if(Bad_match.getMatch().getTrumpAwayMatch() == 0) {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vAwayTrump_NOTrump " + "0" + ";");
				
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tAwayTeamName " + Bad_match.getMatch().getAwayTeam().getFullname().toUpperCase() + ";");
			}
		print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW ON;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In STOP;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out STOP;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 75.0;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
		print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT_PATH C:/Temp/LT_DoubleMatchId.bmp;");
		print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT 1920 1080;");
		TimeUnit.SECONDS.sleep(1);
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 0.0;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
		print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW OFF;");				
		this.status = BadmintonUtil.SUCCESSFUL;	
		}
	}
	public void populateSingleFFMatchId(PrintWriter print_writer,String viz_sence_path,Fixture fx,BadmintonMatch Bad_match, String selectedbroadcaster) throws InterruptedException 
	{
		if (Bad_match == null) {
			this.status = "ERROR: Match is null";
		} else {
			int Set1h = 0,Set1a=0, Set2h = 0,Set2a=0;
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHeader " + "GRAND PRIX BADMINTON LEAGUE 2022" +";");
			
			if((Bad_match.getMatch().getHomeTeam().getTeamId() == fx.getHometeam()) && (Bad_match.getMatch().getAwayTeam().getTeamId() == fx.getAwayteam())) {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader1 " + "TIE " + fx.getMatchnumber() + " - " + "MATCH " + Bad_match.getMatch().getMatchnumber() +";");
			}
			if(Bad_match.getMatch().getTrumpHomeMatch() == 1 || Bad_match.getMatch().getTrumpAwayMatch() == 1) {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vTrumpMatch " + "0" +";");
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTrumpText " + " " +";");
			}
			else {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vTrumpMatch " + "0" +";");
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTrumpText " + " " +";");
			}
			if(Bad_match.getSets() == null ) {
				if(Bad_match.getMatch().getCategoryId() == 1) {
					
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader2 " + "WOMEN'S SINGLES" +";");
					
				}
				if(Bad_match.getMatch().getCategoryId() == 2) {
					
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader2 " + "MEN'S DOUBLES" +";");
					
				}
				if(Bad_match.getMatch().getCategoryId() == 3) {
					
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader2 " + "MEN'S SINGLES" +";");
					
				}
				if(Bad_match.getMatch().getCategoryId() == 4) {
					
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader2 " + "MIXED DOUBLES" +";");
					
				}
			}
			else {
				for(Set st : Bad_match.getSets()) {
					if(st.getSetNumber() == 1) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader2 " + st.getHomeTeamTotalScore() + "-" + st.getAwayTeamTotalScore() +";");
						Set1h = st.getHomeTeamTotalScore(); 
						Set1a = st.getAwayTeamTotalScore();
					}
					if(st.getSetNumber() == 2) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader2 " + Set1h + "-" + Set1a + " | " + st.getHomeTeamTotalScore() + "-" + st.getAwayTeamTotalScore() +";");
						Set2h = st.getHomeTeamTotalScore();
						Set2a = st.getAwayTeamTotalScore();
					}
					if(st.getSetNumber() == 3) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader2 " + Set1h + "-" + Set1a + " | " + Set2h + "-" + Set2a + " | " + st.getHomeTeamTotalScore() + "-" + st.getAwayTeamTotalScore() +";");
					}
					if(st.getSetNumber() == 3 && st.getStatus().equalsIgnoreCase("END") )  {
						if(Bad_match.getHomeTeamSetsWon() > Bad_match.getAwayTeamSetsWon()) {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vTrumpMatch " + "1" +";");
							print_writer.println("LAYER1*EVEREST*TREEVIEW*TrumpGrp*CONTAINER SET ACTIVE 1;");
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTrumpText " + "BEAT" +";");
						}
						else {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vTrumpMatch " + "1" +";");
							print_writer.println("LAYER1*EVEREST*TREEVIEW*TrumpGrp*CONTAINER SET ACTIVE 1;");
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTrumpText " + "LOST TO" +";");
						}
					}
					if(st.getSetNumber() == 2 && st.getStatus().equalsIgnoreCase("END")) {
						
						if(Bad_match.getHomeTeamSetsWon() > Bad_match.getAwayTeamSetsWon()) {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vTrumpMatch " + "1" +";");
							print_writer.println("LAYER1*EVEREST*TREEVIEW*TrumpGrp*CONTAINER SET ACTIVE 1;");
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTrumpText " + "BEAT" +";");
						}
						else if(Bad_match.getHomeTeamSetsWon() < Bad_match.getAwayTeamSetsWon()) {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vTrumpMatch " + "1" +";");
							print_writer.println("LAYER1*EVEREST*TREEVIEW*TrumpGrp*CONTAINER SET ACTIVE 1;");
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTrumpText " + "LOST TO" +";");
						}
					}
				}
			}
			
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgHomePlayerImage " + left_path + Bad_match.getMatch().getHomePlayers().get(0).getTicker_name() + ".png" + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgHomeTeamLogo " + logo_path +  Bad_match.getMatch().getHomeTeam().getShortname().toUpperCase() + ".png"  + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgAwayTeamLogo " + logo_path +  Bad_match.getMatch().getAwayTeam().getShortname().toUpperCase() + ".png"  + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgAwayPlayerImage " + right_path + Bad_match.getMatch().getAwayPlayers().get(0).getTicker_name() + ".png" + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHomePlayerName " + Bad_match.getMatch().getHomePlayers().get(0).getFull_name().toUpperCase()+ ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tAwayPlayerName " + Bad_match.getMatch().getAwayPlayers().get(0).getFull_name().toUpperCase() + ";");
			if(Bad_match.getMatch().getTrumpHomeMatch() == 1 ) {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vHomeTrump " + "1" +";");
				//print_writer.println("LAYER1*EVEREST*TREEVIEW*TrumpGrp*CONTAINER SET ACTIVE 1;");
				
				//print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHomeTeamName " + Bad_match.getMatch().getHomeTeam().getFullname().toUpperCase() + ";");
			}
			else {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vHomeTrump " + "0" +";");
				//print_writer.println("LAYER1*EVEREST*TREEVIEW*TrumpGrp*CONTAINER SET ACTIVE 0;");
				
				//print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHomeTeamName " + Bad_match.getMatch().getHomeTeam().getFullname().toUpperCase() + ";");
			}
			if(Bad_match.getMatch().getTrumpAwayMatch() == 1 ) {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vAwayTrump " + "1" +";");
				//print_writer.println("LAYER1*EVEREST*TREEVIEW*TrumpGrp1*CONTAINER SET ACTIVE 1;");
				
				//print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tAwayTeamName " + Bad_match.getMatch().getAwayTeam().getFullname().toUpperCase() + ";");
			}
			else {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vAwayTrump " + "0" +";");
				//print_writer.println("LAYER1*EVEREST*TREEVIEW*TrumpGrp1*CONTAINER SET ACTIVE 0;");
				
				//print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tAwayTeamName " + Bad_match.getMatch().getAwayTeam().getFullname().toUpperCase() + ";");
			}
		print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW ON;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In STOP;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out STOP;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 123.0;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
		print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT_PATH C:/Temp/FF_SingleMatchId.bmp;");
		print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT 1920 1080;");
		TimeUnit.SECONDS.sleep(1);
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 0.0;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
		print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW OFF;");	
		this.status = BadmintonUtil.SUCCESSFUL;	
		}
	}
	public void populateDoubleFFMatchId(PrintWriter print_writer,String viz_sence_path,Fixture fx,BadmintonMatch Bad_match, String selectedbroadcaster) throws InterruptedException 
	{
		if (Bad_match == null) {
			this.status = "ERROR: Match is null";
		} else {
			int Set1h = 0,Set1a=0, Set2h = 0,Set2a=0;
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHeader " + "GRAND PRIX BADMINTON LEAGUE 2022 " +";");
			//print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader1 " + "MATCH " + Bad_match.getMatch().getMatchId() +";");
			//print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader2 " + "MATCH " + Bad_match.getMatch().getMatchId() +";");
			if((Bad_match.getMatch().getHomeTeam().getTeamId() == fx.getHometeam()) && (Bad_match.getMatch().getAwayTeam().getTeamId() == fx.getAwayteam())) {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader1 " + "TIE " + fx.getMatchnumber() + " - " + "MATCH " + Bad_match.getMatch().getMatchnumber() +";");
			}
			
			if(Bad_match.getMatch().getTrumpHomeMatch() == 1 || Bad_match.getMatch().getTrumpAwayMatch() == 1) {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vTrumpMatch " + "0" +";");
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTrumpText " + " " +";");
			}
			else {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vTrumpMatch " + "0" +";");
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTrumpText " + " " +";");
			}
			
			if(Bad_match.getSets() == null ) {
				if(Bad_match.getMatch().getCategoryId() == 1) {
					
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader2 " + "WOMEN'S SINGLES" +";");
					
				}
				if(Bad_match.getMatch().getCategoryId() == 2) {
					
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader2 " + "MEN'S DOUBLES" +";");
					
				}
				if(Bad_match.getMatch().getCategoryId() == 3) {
					
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader2 " + "MEN'S SINGLES" +";");
					
				}
				if(Bad_match.getMatch().getCategoryId() == 4) {
					
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader2 " + "MIXED DOUBLES" +";");
					
				}
			}
			else {
				for(Set st : Bad_match.getSets()) {
					if(st.getSetNumber() == 1) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader2 " + st.getHomeTeamTotalScore() + "-" + st.getAwayTeamTotalScore() +";");
						Set1h = st.getHomeTeamTotalScore(); 
						Set1a = st.getAwayTeamTotalScore();
					}
					if(st.getSetNumber() == 2) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader2 " + Set1h + "-" + Set1a + " | " + st.getHomeTeamTotalScore() + "-" + st.getAwayTeamTotalScore() +";");
						Set2h = st.getHomeTeamTotalScore();
						Set2a = st.getAwayTeamTotalScore();
					}
					if(st.getSetNumber() == 3) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader2 " + Set1h + "-" + Set1a + " | " + Set2h + "-" + Set2a + " | " + st.getHomeTeamTotalScore() + "-" + st.getAwayTeamTotalScore() +";");
					}
					if(st.getSetNumber() == 3 && st.getStatus().equalsIgnoreCase("END") )  {
						if(Bad_match.getHomeTeamSetsWon() > Bad_match.getAwayTeamSetsWon()) {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vTrumpMatch " + "1" +";");
							print_writer.println("LAYER1*EVEREST*TREEVIEW*TrumpGrp*CONTAINER SET ACTIVE 1;");
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTrumpText " + "BEAT" +";");
						}
						else {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vTrumpMatch " + "1" +";");
							print_writer.println("LAYER1*EVEREST*TREEVIEW*TrumpGrp*CONTAINER SET ACTIVE 1;");
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTrumpText " + "LOST TO" +";");
						}
					}
					else if(st.getSetNumber() == 2 && st.getStatus().equalsIgnoreCase("END")) {
						
						if(Bad_match.getHomeTeamSetsWon() > Bad_match.getAwayTeamSetsWon()) {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vTrumpMatch " + "1" +";");
							print_writer.println("LAYER1*EVEREST*TREEVIEW*TrumpGrp*CONTAINER SET ACTIVE 1;");
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTrumpText " + "BEAT" +";");
						}
						else if(Bad_match.getHomeTeamSetsWon() < Bad_match.getAwayTeamSetsWon()) {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vTrumpMatch " + "1" +";");
							print_writer.println("LAYER1*EVEREST*TREEVIEW*TrumpGrp*CONTAINER SET ACTIVE 1;");
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTrumpText " + "LOST TO" +";");
						}
					}
					
					/*else {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*TrumpGrp*CONTAINER SET ACTIVE 1;");
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTrumpText " + " " +";");
					}*/
					/*if(Bad_match.getMatch().getTrumpHomeMatch() == 1 || Bad_match.getMatch().getTrumpAwayMatch() == 1) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vTrumpMatch " + "1" +";");
					}
					else {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vTrumpMatch " + "0" +";");
					}*/
				}
			}
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgHomePlayerImage1 " + left_path + Bad_match.getMatch().getHomePlayers().get(0).getTicker_name() + ".png" + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgHomePlayerImage2 " + left_path + Bad_match.getMatch().getHomePlayers().get(1).getTicker_name() + ".png" + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgHomeTeamLogo " + logo_path +  Bad_match.getMatch().getHomeTeam().getShortname() + ".png"  + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgAwayTeamLogo " + logo_path +  Bad_match.getMatch().getAwayTeam().getShortname() + ".png"  + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgAwayPlayerImage1 " + right_path + Bad_match.getMatch().getAwayPlayers().get(0).getTicker_name() + ".png" + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgAwayPlayerImage2 " + right_path + Bad_match.getMatch().getAwayPlayers().get(1).getTicker_name() + ".png" + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHomePlayer1Name " + Bad_match.getMatch().getHomePlayers().get(0).getFull_name().toUpperCase() + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tAwayPlayer1Name " + Bad_match.getMatch().getAwayPlayers().get(0).getFull_name().toUpperCase() +  ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHomePlayer2Name " + Bad_match.getMatch().getHomePlayers().get(1).getFull_name().toUpperCase() + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tAwayPlayer2Name " + Bad_match.getMatch().getAwayPlayers().get(1).getFull_name().toUpperCase() +  ";");
			
			if(Bad_match.getMatch().getTrumpHomeMatch() == 1 ) {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vHomeTrump " + "1" +";");
				
			}
			else {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vHomeTrump " + "0" +";");
				
			}
			if(Bad_match.getMatch().getTrumpAwayMatch() == 1 ) {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vAwayTrump " + "1" +";");
				
			}
			else {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vAwayTrump " + "0" +";");
				
			}
		print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW ON;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In STOP;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out STOP;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 82.0;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
		print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT_PATH C:/Temp/FF_DoubleMatchId.bmp;");
		print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT 1920 1080;");
		TimeUnit.SECONDS.sleep(1);
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 0.0;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
		print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW OFF;");	
		this.status = BadmintonUtil.SUCCESSFUL;	
		}
	}
	public void populateLtTieId(PrintWriter print_writer,String viz_sence_path,Fixture fx,BadmintonMatch Bad_match, String selectedbroadcaster) throws InterruptedException 
	{
		if (Bad_match == null) {
			this.status = "ERROR: Match is null";
		} else {
			
			if((Bad_match.getMatch().getHomeTeam().getTeamId() == fx.getHometeam()) && (Bad_match.getMatch().getAwayTeam().getTeamId() == fx.getAwayteam())) {
				if(fx.getMargin() == null) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScore_VS " + "VS" +";");
				}
				else {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScore_VS " + fx.getMargin() +";");
				}			
			}
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHeader " + Bad_match.getMatch().getGroupname().toUpperCase() +";");
			if((Bad_match.getMatch().getHomeTeam().getTeamId() == fx.getHometeam()) && (Bad_match.getMatch().getAwayTeam().getTeamId() == fx.getAwayteam())) {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader " + "TIE " + fx.getMatchnumber() +";");
				
			}
			if(Bad_match.getSets() != null ) {
				for(Set st : Bad_match.getSets()) {
					if(st.getSetNumber() == 3 && st.getStatus().toUpperCase().equalsIgnoreCase("END"))  {
						if((Bad_match.getMatch().getHomeTeam().getTeamId() == fx.getHometeam()) && (Bad_match.getMatch().getAwayTeam().getTeamId() == fx.getAwayteam())) {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScore_VS " + fx.getMargin() +";");
						}
						
					}
				}
			}
			
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgHomePlayer1Image " + logo_path + Bad_match.getMatch().getHomeTeam().getShortname().toUpperCase() + ".png" + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHomeTeamName " + Bad_match.getMatch().getHomeTeam().getFullname().toUpperCase() + ";");
			
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgAwayPlayer1Image " + logo_path + Bad_match.getMatch().getAwayTeam().getShortname().toUpperCase() + ".png" + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tAwayTeamName " + Bad_match.getMatch().getAwayTeam().getFullname().toUpperCase() + ";");			
			
			print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW ON;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In STOP;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out STOP;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 75.0;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
			print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT_PATH C:/Temp/LT_TieId.bmp;");
			print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT 1920 1080;");
			TimeUnit.SECONDS.sleep(1);
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 0.0;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
			print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW OFF;");				
			this.status = BadmintonUtil.SUCCESSFUL;	
		}
	}
	public void populateFFTieId(PrintWriter print_writer,String viz_sence_path,Fixture fx,BadmintonMatch Bad_match, String selectedbroadcaster) throws InterruptedException 
	{
		if (Bad_match == null) {
			this.status = "ERROR: Match is null";
		} else {
			
			
			if((Bad_match.getMatch().getHomeTeam().getTeamId() == fx.getHometeam()) && (Bad_match.getMatch().getAwayTeam().getTeamId() == fx.getAwayteam())) {
				if(fx.getMargin() == null) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*CenterPart*CONTAINER SET ACTIVE 0;");
				}
				else {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*CenterPart*CONTAINER SET ACTIVE 1;");
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader2 " + fx.getMargin() +";");
				}			
			}
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHeader " + "GRAND PRIX BADMINTON LEAGUE 2022 " +";");
			if((Bad_match.getMatch().getHomeTeam().getTeamId() == fx.getHometeam()) && (Bad_match.getMatch().getAwayTeam().getTeamId() == fx.getAwayteam())) {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader1 " +Bad_match.getMatch().getGroupname().toUpperCase() +" - "+ "TIE " + fx.getMatchnumber() +";");
			}
			//print_writer.println("LAYER1*EVEREST*TREEVIEW*CenterPart*CONTAINER SET ACTIVE 0;");
			if(Bad_match.getSets() != null ) {
				for(Set st : Bad_match.getSets()) {
					if(st.getSetNumber() == 3 && st.getStatus().equalsIgnoreCase("END") )  {
						if((Bad_match.getMatch().getHomeTeam().getTeamId() == fx.getHometeam()) && (Bad_match.getMatch().getAwayTeam().getTeamId() == fx.getAwayteam())) {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*CenterPart*CONTAINER SET ACTIVE 1;");
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader2 " + fx.getMargin() +";");
						}
					}
				}
			}
			//print_writer.println("LAYER1*EVEREST*TREEVIEW*CenterPart*CONTAINER SET ACTIVE 0;");
			
			//print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader2 " + " " +";");
			
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgHomeTeamLogo " + logo_path + Bad_match.getMatch().getHomeTeam().getShortname().toUpperCase() + ".png" + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHomeTeamFirstName " + Bad_match.getMatch().getHomeTeam().getFirstname().toUpperCase() + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHomeTeamLastName " + Bad_match.getMatch().getHomeTeam().getLastname().toUpperCase() + ";");
			//print_writer.println("LAYER1*EVEREST*TREEVIEW*TEAM1LastName*CONTAINER SET ACTIVE 0;");
			
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgAwayTeamLogo " + logo_path + Bad_match.getMatch().getAwayTeam().getShortname().toUpperCase() + ".png" + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tAwayTeamFirstName " + Bad_match.getMatch().getAwayTeam().getFirstname().toUpperCase() + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tAwayTeamLastName " + Bad_match.getMatch().getAwayTeam().getLastname().toUpperCase() + ";");			
			//print_writer.println("LAYER1*EVEREST*TREEVIEW*TEAM2LastName*CONTAINER SET ACTIVE 0;");
			
			print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW ON;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In STOP;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out STOP;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 98.0;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
			print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT_PATH C:/Temp/FFTieId.bmp;");
			print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT 1920 1080;");
			TimeUnit.SECONDS.sleep(1);
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 0.0;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
			print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW OFF;");	
			this.status = BadmintonUtil.SUCCESSFUL;	
		}
	}
	public void populateSides(PrintWriter print_writer,String viz_sence_path,String Top,String Bottom,BadmintonMatch Bad_match, String selectedbroadcaster) throws InterruptedException 
	{
		if (Bad_match == null) {
			this.status = "ERROR: Match is null";
		} else {
			switch(Top.toUpperCase()) {
			case "HOME_PLAYER":
				for(int i=1; i <= Bad_match.getMatch().getHomePlayers().size();i++){
					if(i==1){
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTopName " + Bad_match.getMatch().getHomePlayers().get(Bad_match.getMatch().getHomePlayers().size() - i).getTicker_name().toUpperCase() +";");
					}
					else if(i==2){
					int j=i-1;
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTopName " + Bad_match.getMatch().getHomePlayers().get(Bad_match.getMatch().getHomePlayers().size() - j).getTicker_name().toUpperCase() + "/" 
					+ Bad_match.getMatch().getHomePlayers().get(Bad_match.getMatch().getHomePlayers().size() - i).getTicker_name().toUpperCase() + ";");
					}
					else if(i==3){
						int j=i-1;
						int k=i-2;
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTopName " + Bad_match.getMatch().getHomePlayers().get(Bad_match.getMatch().getHomePlayers().size() - j).getTicker_name().toUpperCase() + "/" 
								+ Bad_match.getMatch().getHomePlayers().get(Bad_match.getMatch().getHomePlayers().size() - k).getTicker_name().toUpperCase() + "/" 
								+ Bad_match.getMatch().getHomePlayers().get(Bad_match.getMatch().getHomePlayers().size() - i).getTicker_name().toUpperCase() + ";");
					}
					
					}
				break;
			case "AWAY_PLAYER":
				for(int i=1; i <= Bad_match.getMatch().getHomePlayers().size();i++){
					if(i==1){
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTopName " + Bad_match.getMatch().getAwayPlayers().get(Bad_match.getMatch().getAwayPlayers().size() - i).getTicker_name().toUpperCase() +";");
					}
					else if(i==2){
					int j=i-1;
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTopName " + Bad_match.getMatch().getAwayPlayers().get(Bad_match.getMatch().getAwayPlayers().size() - j).getTicker_name().toUpperCase() + "/" 
					+ Bad_match.getMatch().getAwayPlayers().get(Bad_match.getMatch().getAwayPlayers().size() - i).getTicker_name().toUpperCase() + ";");
					}
					else if(i==3){
						int j=i-1;
						int k=i-2;
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTopName " + Bad_match.getMatch().getAwayPlayers().get(Bad_match.getMatch().getAwayPlayers().size() - j).getTicker_name().toUpperCase() + "/" 
								+ Bad_match.getMatch().getAwayPlayers().get(Bad_match.getMatch().getAwayPlayers().size() - k).getTicker_name().toUpperCase() + "/" 
								+ Bad_match.getMatch().getAwayPlayers().get(Bad_match.getMatch().getAwayPlayers().size() - i).getTicker_name().toUpperCase() + ";");
					}
					
					}
				break;
			}
			switch(Bottom.toUpperCase()) {
			case "HOME_PLAYER":
				for(int i=1; i <= Bad_match.getMatch().getHomePlayers().size();i++){
					if(i==1){
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET BottomName " + Bad_match.getMatch().getHomePlayers().get(Bad_match.getMatch().getHomePlayers().size() - i).getTicker_name().toUpperCase() +";");
					}
					else if(i==2){
					int j=i-1;
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET BottomName " + Bad_match.getMatch().getHomePlayers().get(Bad_match.getMatch().getHomePlayers().size() - j).getTicker_name().toUpperCase() + "/" 
					+ Bad_match.getMatch().getHomePlayers().get(Bad_match.getMatch().getHomePlayers().size() - i).getTicker_name().toUpperCase() + ";");
					}
					else if(i==3){
						int j=i-1;
						int k=i-2;
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET BottomName " + Bad_match.getMatch().getHomePlayers().get(Bad_match.getMatch().getHomePlayers().size() - j).getTicker_name().toUpperCase() + "/" 
								+ Bad_match.getMatch().getHomePlayers().get(Bad_match.getMatch().getHomePlayers().size() - k).getTicker_name().toUpperCase() + "/" 
								+ Bad_match.getMatch().getHomePlayers().get(Bad_match.getMatch().getHomePlayers().size() - i).getTicker_name().toUpperCase() + ";");
					}
					
					}
				break;
			case "AWAY_PLAYER":
				for(int i=1; i <= Bad_match.getMatch().getHomePlayers().size();i++){
					if(i==1){
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET BottomName " + Bad_match.getMatch().getAwayPlayers().get(Bad_match.getMatch().getAwayPlayers().size() - i).getTicker_name().toUpperCase() +";");
					}
					else if(i==2){
					int j=i-1;
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET BottomName " + Bad_match.getMatch().getAwayPlayers().get(Bad_match.getMatch().getAwayPlayers().size() - j).getTicker_name().toUpperCase() + "/" 
					+ Bad_match.getMatch().getAwayPlayers().get(Bad_match.getMatch().getAwayPlayers().size() - i).getTicker_name().toUpperCase() + ";");
					}
					else if(i==3){
						int j=i-1;
						int k=i-2;
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET BottomName " + Bad_match.getMatch().getAwayPlayers().get(Bad_match.getMatch().getAwayPlayers().size() - j).getTicker_name().toUpperCase() + "/" 
								+ Bad_match.getMatch().getAwayPlayers().get(Bad_match.getMatch().getAwayPlayers().size() - k).getTicker_name().toUpperCase() + "/" 
								+ Bad_match.getMatch().getAwayPlayers().get(Bad_match.getMatch().getAwayPlayers().size() - i).getTicker_name().toUpperCase() + ";");
					}
					
					}
				break;
			}
			
			print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW ON;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In STOP;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out STOP;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 149.00;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
			print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT_PATH C:/Temp/Sides.bmp;");
			print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT 1920 1080;");
			TimeUnit.SECONDS.sleep(1);
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 0.0;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
			print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW OFF;");	
			this.status = BadmintonUtil.SUCCESSFUL;	
		}
	}
	public void populateOrderOfPlay(PrintWriter print_writer,String viz_sence_path,int match_number,List<BadmintonMatch> mtch,Fixture fx,BadmintonMatch Bad_match, String selectedbroadcaster) throws InterruptedException 
	{
		if (Bad_match == null) {
			this.status = "ERROR: Match is null";
		} else {
			int Set1h = 0,Set1a=0, Set2h = 0,Set2a=0;
			
			
			for(int i = 0; i <= mtch.size()-1; i++) {
				switch(i) {
				case 0 :
					print_writer.println("LAYER1*EVEREST*TREEVIEW*First*CONTAINER SET ACTIVE 1;");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Second*CONTAINER SET ACTIVE 0;");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Third*CONTAINER SET ACTIVE 0;");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Fourth*CONTAINER SET ACTIVE 0;");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vSuperMatch " + "0" + ";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Fifth*CONTAINER SET ACTIVE 0;");
					
					if(mtch.get(i).getMatch().getHomeSecondPlayerId() == 0) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerA1 " + mtch.get(i).getMatch().getHomePlayers().get(0).getTicker_name().toUpperCase() +";");
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerA2 " + mtch.get(i).getMatch().getAwayPlayers().get(0).getTicker_name().toUpperCase() +";");
					}
					else if(mtch.get(i).getMatch().getHomeSecondPlayerId() != 0) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerA1 " + mtch.get(i).getMatch().getHomePlayers().get(0).getTicker_name().toUpperCase() + " / " + 
								mtch.get(i).getMatch().getHomePlayers().get(1).getTicker_name().toUpperCase() +";");
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerA2 " + mtch.get(i).getMatch().getAwayPlayers().get(0).getTicker_name().toUpperCase() + " / " + 
								mtch.get(i).getMatch().getAwayPlayers().get(1).getTicker_name().toUpperCase() +";");
					}
					else if(mtch.get(i).getMatch().getHomeThirdPlayerId() != 0) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerA1 " + mtch.get(i).getMatch().getHomePlayers().get(0).getTicker_name().toUpperCase() + " / " + 
								mtch.get(i).getMatch().getHomePlayers().get(1).getTicker_name().toUpperCase() + " / " + mtch.get(i).getMatch().getHomePlayers().get(2).getTicker_name().toUpperCase() +";");
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerA2 " + mtch.get(i).getMatch().getAwayPlayers().get(0).getTicker_name().toUpperCase() + " / " + 
								mtch.get(i).getMatch().getAwayPlayers().get(1).getTicker_name().toUpperCase() + " / " + mtch.get(i).getMatch().getAwayPlayers().get(2).getTicker_name().toUpperCase() +";");
					}
					

				if(mtch.get(i).getSets() == null ) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScore1 " + " " +";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET 1 " + "0" +";");
					
				}
				else {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET 1 " + "1" +";");
					for(Set st : mtch.get(i).getSets()) {
						if(st.getSetNumber() == 1) {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScore1 " + st.getHomeTeamTotalScore() + "-" + st.getAwayTeamTotalScore() +";");
							Set1h = st.getHomeTeamTotalScore(); 
							Set1a = st.getAwayTeamTotalScore();
						}
						if(st.getSetNumber() == 2) {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScore1 " + Set1h + "-" + Set1a + " | " + st.getHomeTeamTotalScore() + "-" + st.getAwayTeamTotalScore() +";");
							Set2h = st.getHomeTeamTotalScore();
							Set2a = st.getAwayTeamTotalScore();
						}
						if(st.getSetNumber() == 3) {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScore1 " + Set1h + "-" + Set1a + " | " + Set2h + "-" + Set2a + " | " + st.getHomeTeamTotalScore() + "-" + st.getAwayTeamTotalScore() +";");	
						}
						if(st.getSetNumber() == 3 && st.getStatus().equalsIgnoreCase("END") )  {
							if(mtch.get(i).getHomeTeamSetsWon() > mtch.get(i).getAwayTeamSetsWon()) {
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tVs1 " + "BEAT" +";");
							}
							else {
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tVs1 " + "LOST TO" +";");
							}
						}
						if(st.getSetNumber() == 2 && st.getStatus().equalsIgnoreCase("END")) {
							
							if(mtch.get(i).getHomeTeamSetsWon() > mtch.get(i).getAwayTeamSetsWon()) {
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tVs1 " + "BEAT" +";");
							}
							else if(mtch.get(i).getHomeTeamSetsWon() < mtch.get(i).getAwayTeamSetsWon()) {
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tVs1 " + "LOST TO" +";");
							}
						}
					}
				}
				if(mtch.get(i).getMatch().getTrumpHomeMatch() == 1) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vTrumpA1 " + "1" + ";");
				}
				else {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vTrumpA1 " + "0" + ";");
				}
				if(mtch.get(i).getMatch().getTrumpAwayMatch() == 1) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vTrumpA2 " + "1" + ";");
				}
				else {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vTrumpA2 " + "0" + ";");
				}
				if(mtch.get(i).getMatch().getCategoryId() == 1) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tMatchName1 " + " WS " +";");
				}
				if(mtch.get(i).getMatch().getCategoryId() == 2) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tMatchName1 " + " MD " +";");
				}
				if(mtch.get(i).getMatch().getCategoryId() == 3) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tMatchName1 " + " MS " +";");
				}
				if(mtch.get(i).getMatch().getCategoryId() == 4) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tMatchName1 " + " XD " +";");
				}
				
					break;
					
				case 1 :
					print_writer.println("LAYER1*EVEREST*TREEVIEW*First*CONTAINER SET ACTIVE 1;");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Second*CONTAINER SET ACTIVE 1;");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Third*CONTAINER SET ACTIVE 0;");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Fourth*CONTAINER SET ACTIVE 0;");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vSuperMatch " + "0" + ";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Fifth*CONTAINER SET ACTIVE 0;");
					
					if(mtch.get(i).getMatch().getHomeSecondPlayerId() == 0) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerB1 " + mtch.get(i).getMatch().getHomePlayers().get(0).getTicker_name().toUpperCase() +";");
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerB2 " + mtch.get(i).getMatch().getAwayPlayers().get(0).getTicker_name().toUpperCase() +";");
					}
					else if(mtch.get(i).getMatch().getHomeSecondPlayerId() != 0) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerB1 " + mtch.get(i).getMatch().getHomePlayers().get(0).getTicker_name().toUpperCase() + " / " + 
								mtch.get(i).getMatch().getHomePlayers().get(1).getTicker_name().toUpperCase() +";");
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerB2 " + mtch.get(i).getMatch().getAwayPlayers().get(0).getTicker_name().toUpperCase() + " / " + 
								mtch.get(i).getMatch().getAwayPlayers().get(1).getTicker_name().toUpperCase() +";");
					}
					else if(mtch.get(i).getMatch().getHomeThirdPlayerId() != 0) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerB1 " + mtch.get(i).getMatch().getHomePlayers().get(0).getTicker_name().toUpperCase() + " / " + 
								mtch.get(i).getMatch().getHomePlayers().get(1).getTicker_name().toUpperCase() + " / " + mtch.get(i).getMatch().getHomePlayers().get(2).getTicker_name().toUpperCase() +";");
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerB2 " + mtch.get(i).getMatch().getAwayPlayers().get(0).getTicker_name().toUpperCase() + " / " + 
								mtch.get(i).getMatch().getAwayPlayers().get(1).getTicker_name().toUpperCase() + " / " + mtch.get(i).getMatch().getAwayPlayers().get(2).getTicker_name().toUpperCase() +";");
					}
					
				if(mtch.get(i).getSets() == null ) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScore2 " + " " +";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET 2 " + "0" +";");
					
				}
				else {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET 2 " + "1" +";");
					for(Set st : mtch.get(i).getSets()) {
						if(st.getSetNumber() == 1) {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScore2 " + st.getHomeTeamTotalScore() + "-" + st.getAwayTeamTotalScore() +";");
							Set1h = st.getHomeTeamTotalScore(); 
							Set1a = st.getAwayTeamTotalScore();
						}
						if(st.getSetNumber() == 2) {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScore2 " + Set1h + "-" + Set1a + " | " + st.getHomeTeamTotalScore() + "-" + st.getAwayTeamTotalScore() +";");
							Set2h = st.getHomeTeamTotalScore();
							Set2a = st.getAwayTeamTotalScore();
						}
						if(st.getSetNumber() == 3) {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScore2 " + Set1h + "-" + Set1a + " | " + Set2h + "-" + Set2a + " | " + st.getHomeTeamTotalScore() + "-" + st.getAwayTeamTotalScore() +";");	
						}
						if(st.getSetNumber() == 3 && st.getStatus().equalsIgnoreCase("END") )  {
							if(mtch.get(i).getHomeTeamSetsWon() > mtch.get(i).getAwayTeamSetsWon()) {
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tVs2 " + "BEAT" +";");
							}
							else {
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tVs2 " + "LOST TO" +";");
							}
						}
						if(st.getSetNumber() == 2 && st.getStatus().equalsIgnoreCase("END")) {
							
							if(mtch.get(i).getHomeTeamSetsWon() > mtch.get(i).getAwayTeamSetsWon()) {
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tVs2 " + "BEAT" +";");
							}
							else if(mtch.get(i).getHomeTeamSetsWon() < mtch.get(i).getAwayTeamSetsWon()) {
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tVs2 " + "LOST TO" +";");
							}
						}
					}
				}
				if(mtch.get(i).getMatch().getTrumpHomeMatch() == 1) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vTrumpB1 " + "1" + ";");
				}
				else {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vTrumpB1 " + "0" + ";");
				}
				if(mtch.get(i).getMatch().getTrumpAwayMatch() == 1) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vTrumpB2 " + "1" + ";");
				}
				else {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vTrumpB2 " + "0" + ";");
				}
				if(mtch.get(i).getMatch().getCategoryId() == 1) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tMatchName2 " + "WS" +";");
				}
				if(mtch.get(i).getMatch().getCategoryId() == 2) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tMatchName2 " + "MD" +";");
				}
				if(mtch.get(i).getMatch().getCategoryId() == 3) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tMatchName2 " + "MS" +";");
				}
				if(mtch.get(i).getMatch().getCategoryId() == 4) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tMatchName2 " + "XD" +";");
				}
					break;
					
				case 2 :
					print_writer.println("LAYER1*EVEREST*TREEVIEW*First*CONTAINER SET ACTIVE 1;");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Second*CONTAINER SET ACTIVE 1;");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Third*CONTAINER SET ACTIVE 1;");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Fourth*CONTAINER SET ACTIVE 0;");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vSuperMatch " + "0" + ";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Fifth*CONTAINER SET ACTIVE 0;");
					
					if(mtch.get(i).getMatch().getHomeSecondPlayerId() == 0) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerC1 " + mtch.get(i).getMatch().getHomePlayers().get(0).getTicker_name().toUpperCase() +";");
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerC2 " + mtch.get(i).getMatch().getAwayPlayers().get(0).getTicker_name().toUpperCase() +";");
					}
					else if(mtch.get(i).getMatch().getHomeSecondPlayerId() != 0) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerC1 " + mtch.get(i).getMatch().getHomePlayers().get(0).getTicker_name().toUpperCase() + " / " + 
								mtch.get(i).getMatch().getHomePlayers().get(1).getTicker_name().toUpperCase() +";");
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerC2 " + mtch.get(i).getMatch().getAwayPlayers().get(0).getTicker_name().toUpperCase() + " / " + 
								mtch.get(i).getMatch().getAwayPlayers().get(1).getTicker_name().toUpperCase() +";");
					}
					else if(mtch.get(i).getMatch().getHomeThirdPlayerId() != 0) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerC1 " + mtch.get(i).getMatch().getHomePlayers().get(0).getTicker_name().toUpperCase() + " / " + 
								mtch.get(i).getMatch().getHomePlayers().get(1).getTicker_name().toUpperCase() + " / " + mtch.get(i).getMatch().getHomePlayers().get(2).getTicker_name().toUpperCase() +";");
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerC2 " + mtch.get(i).getMatch().getAwayPlayers().get(0).getTicker_name().toUpperCase() + " / " + 
								mtch.get(i).getMatch().getAwayPlayers().get(1).getTicker_name().toUpperCase() + " / " + mtch.get(i).getMatch().getAwayPlayers().get(2).getTicker_name().toUpperCase() +";");
					}
					
				if(mtch.get(i).getSets() == null ) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScore3 " + " " +";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET 3 " + "0" +";");
					
				}
				else {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET 3 " + "1" +";");
					for(Set st : mtch.get(i).getSets()) {
						if(st.getSetNumber() == 1) {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScore3 " + st.getHomeTeamTotalScore() + "-" + st.getAwayTeamTotalScore() +";");
							Set1h = st.getHomeTeamTotalScore(); 
							Set1a = st.getAwayTeamTotalScore();
						}
						if(st.getSetNumber() == 2) {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScore3 " + Set1h + "-" + Set1a + " | " + st.getHomeTeamTotalScore() + "-" + st.getAwayTeamTotalScore() +";");
							Set2h = st.getHomeTeamTotalScore();
							Set2a = st.getAwayTeamTotalScore();
						}
						if(st.getSetNumber() == 3) {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScore3 " + Set1h + "-" + Set1a + " | " + Set2h + "-" + Set2a + " | " + st.getHomeTeamTotalScore() + "-" + st.getAwayTeamTotalScore() +";");	
						}
						if(st.getSetNumber() == 3 && st.getStatus().equalsIgnoreCase("END") )  {
							if(mtch.get(i).getHomeTeamSetsWon() > mtch.get(i).getAwayTeamSetsWon()) {
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tVs3 " + "BEAT" +";");
							}
							else {
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tVs3 " + "LOST TO" +";");
							}
						}
						if(st.getSetNumber() == 2 && st.getStatus().equalsIgnoreCase("END")) {
							
							if(mtch.get(i).getHomeTeamSetsWon() > mtch.get(i).getAwayTeamSetsWon()) {
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tVs3 " + "BEAT" +";");
							}
							else if(mtch.get(i).getHomeTeamSetsWon() < mtch.get(i).getAwayTeamSetsWon()) {
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tVs3 " + "LOST TO" +";");
							}
						}
					}
				}
				if(mtch.get(i).getMatch().getTrumpHomeMatch() == 1) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vTrumpC1 " + "1" + ";");
				}
				else {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vTrumpC1 " + "0" + ";");
				}
				if(mtch.get(i).getMatch().getTrumpAwayMatch() == 1) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vTrumpC2 " + "1" + ";");
				}
				else {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vTrumpC2 " + "0" + ";");
				}
				if(mtch.get(i).getMatch().getCategoryId() == 1) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tMatchName3 " + "WS" +";");
				}
				if(mtch.get(i).getMatch().getCategoryId() == 2) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tMatchName3 " + "MD" +";");
				}
				if(mtch.get(i).getMatch().getCategoryId() == 3) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tMatchName3 " + "MS" +";");
				}
				if(mtch.get(i).getMatch().getCategoryId() == 4) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tMatchName3 " + "XD" +";");
				}
					break;
					
				case 3 :
					print_writer.println("LAYER1*EVEREST*TREEVIEW*First*CONTAINER SET ACTIVE 1;");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Second*CONTAINER SET ACTIVE 1;");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Third*CONTAINER SET ACTIVE 1;");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Fourth*CONTAINER SET ACTIVE 1;");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Fifth*CONTAINER SET ACTIVE 1;");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vSuperMatch " + "0" + ";");
					
					if(mtch.get(i).getMatch().getHomeSecondPlayerId() == 0) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerD1 " + mtch.get(i).getMatch().getHomePlayers().get(0).getTicker_name().toUpperCase() +";");
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerD2 " + mtch.get(i).getMatch().getAwayPlayers().get(0).getTicker_name().toUpperCase() +";");
					}
					else if(mtch.get(i).getMatch().getHomeSecondPlayerId() != 0) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerD1 " + mtch.get(i).getMatch().getHomePlayers().get(0).getTicker_name().toUpperCase() + " / " + 
								mtch.get(i).getMatch().getHomePlayers().get(1).getTicker_name().toUpperCase() +";");
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerD2 " + mtch.get(i).getMatch().getAwayPlayers().get(0).getTicker_name().toUpperCase() + " / " + 
								mtch.get(i).getMatch().getAwayPlayers().get(1).getTicker_name().toUpperCase() +";");
					}
					else if(mtch.get(i).getMatch().getHomeThirdPlayerId() != 0) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerD1 " + mtch.get(i).getMatch().getHomePlayers().get(0).getTicker_name().toUpperCase() + " / " + 
								mtch.get(i).getMatch().getHomePlayers().get(1).getTicker_name().toUpperCase() + " / " + mtch.get(i).getMatch().getHomePlayers().get(2).getTicker_name().toUpperCase() +";");
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerD2 " + mtch.get(i).getMatch().getAwayPlayers().get(0).getTicker_name().toUpperCase() + " / " + 
								mtch.get(i).getMatch().getAwayPlayers().get(1).getTicker_name().toUpperCase() + " / " + mtch.get(i).getMatch().getAwayPlayers().get(2).getTicker_name().toUpperCase() +";");
					}
					
				if(mtch.get(i).getSets() == null ) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScore4 " + " " +";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET 4 " + "0" +";");
					
				}
				else {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET 4 " + "1" +";");
					for(Set st : mtch.get(i).getSets()) {
						if(st.getSetNumber() == 1) {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScore4 " + st.getHomeTeamTotalScore() + "-" + st.getAwayTeamTotalScore() +";");
							Set1h = st.getHomeTeamTotalScore(); 
							Set1a = st.getAwayTeamTotalScore();
						}
						if(st.getSetNumber() == 2) {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScore4 " + Set1h + "-" + Set1a + " | " + st.getHomeTeamTotalScore() + "-" + st.getAwayTeamTotalScore() +";");
							Set2h = st.getHomeTeamTotalScore();
							Set2a = st.getAwayTeamTotalScore();
						}
						if(st.getSetNumber() == 3) {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScore4 " + Set1h + "-" + Set1a + " | " + Set2h + "-" + Set2a + " | " + st.getHomeTeamTotalScore() + "-" + st.getAwayTeamTotalScore() +";");	
						}
						if(st.getSetNumber() == 3 && st.getStatus().equalsIgnoreCase("END") )  {
							if(mtch.get(i).getHomeTeamSetsWon() > mtch.get(i).getAwayTeamSetsWon()) {
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tVs4 " + "BEAT" +";");
							}
							else {
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tVs4 " + "LOST TO" +";");
							}
						}
						if(st.getSetNumber() == 2 && st.getStatus().equalsIgnoreCase("END")) {
							
							if(mtch.get(i).getHomeTeamSetsWon() > mtch.get(i).getAwayTeamSetsWon()) {
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tVs4 " + "BEAT" +";");
							}
							else if(mtch.get(i).getHomeTeamSetsWon() < mtch.get(i).getAwayTeamSetsWon()) {
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tVs4 " + "LOST TO" +";");
							}
						}
					}
				}
				if(mtch.get(i).getMatch().getTrumpHomeMatch() == 1) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vTrumpD1 " + "1" + ";");
				}
				else {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vTrumpD1 " + "0" + ";");
				}
				if(mtch.get(i).getMatch().getTrumpAwayMatch() == 1) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vTrumpD2 " + "1" + ";");
				}
				else {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vTrumpD2 " + "0" + ";");
				}
				if(mtch.get(i).getMatch().getCategoryId() == 1) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tMatchName4 " + "WS" +";");
				}
				if(mtch.get(i).getMatch().getCategoryId() == 2) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tMatchName4 " + "MD" +";");
				}
				if(mtch.get(i).getMatch().getCategoryId() == 3) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tMatchName4 " + "MS" +";");
				}
				if(mtch.get(i).getMatch().getCategoryId() == 4) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tMatchName4 " + "XD" +";");
				}
					break;
					
				case 4 :
					print_writer.println("LAYER1*EVEREST*TREEVIEW*First*CONTAINER SET ACTIVE 1;");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Second*CONTAINER SET ACTIVE 1;");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Third*CONTAINER SET ACTIVE 1;");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Fourth*CONTAINER SET ACTIVE 1;");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Fifth*CONTAINER SET ACTIVE 1;");
					
					if(mtch.get(i).getMatch().getSuperMatch() == 1) {
						
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vSuperMatch " + "1" + ";");
						
						if(mtch.get(i).getMatch().getHomeSecondPlayerId() == 0) {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerE1 " + mtch.get(i).getMatch().getHomePlayers().get(0).getTicker_name().toUpperCase() +";");
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerE2 " + mtch.get(i).getMatch().getAwayPlayers().get(0).getTicker_name().toUpperCase() +";");
						}
						else if(mtch.get(i).getMatch().getHomeSecondPlayerId() != 0) {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerE1 " + mtch.get(i).getMatch().getHomePlayers().get(0).getTicker_name().toUpperCase() + " / " + 
									mtch.get(i).getMatch().getHomePlayers().get(1).getTicker_name().toUpperCase() +";");
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerE2 " + mtch.get(i).getMatch().getAwayPlayers().get(0).getTicker_name().toUpperCase() + " / " + 
									mtch.get(i).getMatch().getAwayPlayers().get(1).getTicker_name().toUpperCase() +";");
						}
						else if(mtch.get(i).getMatch().getHomeThirdPlayerId() != 0) {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerE1 " + mtch.get(i).getMatch().getHomePlayers().get(0).getTicker_name().toUpperCase() + " / " + 
									mtch.get(i).getMatch().getHomePlayers().get(1).getTicker_name().toUpperCase() + " / " + mtch.get(i).getMatch().getHomePlayers().get(2).getTicker_name().toUpperCase() +";");
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerE2 " + mtch.get(i).getMatch().getAwayPlayers().get(0).getTicker_name().toUpperCase() + " / " + 
									mtch.get(i).getMatch().getAwayPlayers().get(1).getTicker_name().toUpperCase() + " / " + mtch.get(i).getMatch().getAwayPlayers().get(2).getTicker_name().toUpperCase() +";");
						}
						
					if(mtch.get(i).getSets() == null ) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScore5 " + " " +";");
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET 5 " + "0" +";");
					}
					else {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET 5 " + "1" +";");
						for(Set st : mtch.get(i).getSets()) {
							if(st.getSetNumber() == 1) {
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScore5 " + st.getHomeTeamTotalScore() + "-" + st.getAwayTeamTotalScore() +";");
								Set1h = st.getHomeTeamTotalScore(); 
								Set1a = st.getAwayTeamTotalScore();
							}
							if(st.getSetNumber() == 2) {
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScore5 " + Set1h + "-" + Set1a + " | " + st.getHomeTeamTotalScore() + "-" + st.getAwayTeamTotalScore() +";");
								Set2h = st.getHomeTeamTotalScore();
								Set2a = st.getAwayTeamTotalScore();
							}
							if(st.getSetNumber() == 3) {
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScore5 " + Set1h + "-" + Set1a + " | " + Set2h + "-" + Set2a + " | " + st.getHomeTeamTotalScore() + "-" + st.getAwayTeamTotalScore() +";");	
							}
							
							if(st.getSetNumber() == 1 && st.getStatus().equalsIgnoreCase("END")) {
								
								if(mtch.get(i).getHomeTeamSetsWon() > mtch.get(i).getAwayTeamSetsWon()) {
									print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tVs5 " + "BEAT" +";");
								}
								else if(mtch.get(i).getHomeTeamSetsWon() < mtch.get(i).getAwayTeamSetsWon()) {
									print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tVs5 " + "LOST TO" +";");
								}
							}
						}
					}
					if(mtch.get(i).getMatch().getTrumpHomeMatch() == 1) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vTrumpE1 " + "1" + ";");
					}
					else {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vTrumpE1 " + "0" + ";");
					}
					if(mtch.get(i).getMatch().getTrumpAwayMatch() == 1) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vTrumpE2 " + "1" + ";");
					}
					else {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vTrumpE2 " + "0" + ";");
					}
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tMatchName5 " + "SM" +";");
					}
					break;
				}
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHomeTeamName " + mtch.get(i).getMatch().getHomeTeam().getFullname().toUpperCase() +";");
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tAwayTeamName " + mtch.get(i).getMatch().getAwayTeam().getFullname().toUpperCase() +";");

				if((mtch.get(i).getMatch().getHomeTeam().getTeamId() == fx.getHometeam()) && (mtch.get(i).getMatch().getAwayTeam().getTeamId() == fx.getAwayteam())) {
					if(fx.getMargin() == null) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScore " + " " +";");
					}
					else {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScore " + fx.getMargin() +";");
					}			
				}
			
			}
			
			print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW ON;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In STOP;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out STOP;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 109.0;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
			print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT_PATH C:/Temp/OrderOfPlay.bmp;");
			print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT 1920 1080;");
			TimeUnit.SECONDS.sleep(1);
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 0.0;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
			print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW OFF;");	
			this.status = BadmintonUtil.SUCCESSFUL;	
		}
	}
	public void populateTeamsLogo(List<Team> teams, PrintWriter print_writer,String viz_sence_path, String selectedbroadcaster) throws InterruptedException 
	{
		int left_row_id =0,right_row_id = 0;
		print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader1 " + "TEAMS" + ";");
		print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader2 " + " " + ";");
		print_writer.println("LAYER1*EVEREST*TREEVIEW*Underline*CONTAINER SET ACTIVE 0;");

		for(Team team : teams) {
			if(team.getGroupname().equalsIgnoreCase("A")) {
				left_row_id = left_row_id + 1;
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgGroupALogo" + left_row_id + " " + logo_path + team.getShortname().toUpperCase() + ".png" +";");
			}
			else if(team.getGroupname().equalsIgnoreCase("B")) {
				right_row_id = right_row_id + 1;
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgGroupBLogo" + right_row_id + " " + logo_path + team.getShortname().toUpperCase() + ".png" +";");
			}
		}
		
		print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW ON;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In STOP;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out STOP;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 98.0;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
		print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT_PATH C:/Temp/TeamsLogo.bmp;");
		print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT 1920 1080;");
		TimeUnit.SECONDS.sleep(1);
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 0.0;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
		print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW OFF;");
		this.status = BadmintonUtil.SUCCESSFUL;	
		
	}
	public void populatePlayerProfile(PrintWriter print_writer,String viz_sence_path,Player pp,Team tm,BadmintonMatch Bad_match, String selectedbroadcaster) throws InterruptedException 
	{
		if (Bad_match == null) {
			this.status = "ERROR: Match is null";
		} else {
			//print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHeader " +  +";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader1 " + " " +";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tStatValue1 " + pp.getAge() +";");
			if(pp.getTeamId() == tm.getTeamId()) {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tStatValue2 " + tm.getFullname().toUpperCase() +";");
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgHomeTeamLogo " + logo_path + tm.getShortname() + ".png" + ";");
			}
			
			if(pp.getIconPlayer().equalsIgnoreCase("YES")) {
				if(pp.getBwfRanking() == null) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tStatHead3A " + " " +";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tStatHead3B " + " " +";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tStatValue3 " + " " +";");
				}
				else {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tStatHead3A " + "BWF" +";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tStatValue3 " + pp.getBwfRanking() +";");
				}
				
			}
			else if(pp.getIconPlayer().equalsIgnoreCase("NO")) {
				if(pp.getBaiRanking() == null) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tStatHead3A " + " " +";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tStatHead3B " + " " +";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tStatValue3 " + " " +";");
				}
				else {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tStatHead3A " + "BAI" +";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tStatValue3 " + pp.getBaiRanking() +";");
				}
				
			}
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tRunningText1 " + pp.getText1()  +";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tRunningText2 " + pp.getText2() +";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgHomePlayerImage " + left_path + pp.getTicker_name() + ".png" + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerName " + pp.getFull_name().toUpperCase() + ";");
			
			print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW ON;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In STOP;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out STOP;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 127.0;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
			print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT_PATH C:/Temp/PlayerProfile.bmp;");
			print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT 1920 1080;");
			TimeUnit.SECONDS.sleep(1);
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 0.0;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
			print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW OFF;");	
			this.status = BadmintonUtil.SUCCESSFUL;	
		}
	}
	public void populateL3TiePromo(PrintWriter print_writer,String viz_sence_path,Fixture fx,BadmintonMatch Bad_match, String selectedbroadcaster) throws InterruptedException 
	{
		if (Bad_match == null) {
			this.status = "ERROR: Match is null";
		} else {
			
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHeader " + "GROUP " + fx.getHome_Team().getGroupname().toUpperCase() +";");
			
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader " + "TIE " + fx.getMatchnumber() +";");
			
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScore_VS " + "UP NEXT" +";");
			
			
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgHomePlayer1Image " + logo_path + fx.getHome_Team().getShortname().toUpperCase() + ".png" + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHomeTeamName " + fx.getHome_Team().getFullname().toUpperCase() + ";");
			
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgAwayPlayer1Image " + logo_path + fx.getAway_Team().getShortname().toUpperCase() + ".png" + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tAwayTeamName " + fx.getAway_Team().getFullname().toUpperCase() + ";");			
		
			print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW ON;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In STOP;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out STOP;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 75.0;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
			print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT_PATH C:/Temp/LT_TiePromo.bmp;");
			print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT 1920 1080;");
			TimeUnit.SECONDS.sleep(1);
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 0.0;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
			print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW OFF;");
			this.status = BadmintonUtil.SUCCESSFUL;	
		}
	}
	
	public void populateFFTiePromo(PrintWriter print_writer,String viz_sence_path,Fixture fx,BadmintonMatch Bad_match, String selectedbroadcaster) throws InterruptedException 
	{
		if (Bad_match == null) {
			this.status = "ERROR: Match is null";
		} else {
			
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHeader " + "GRAND PRIX BADMINTON LEAGUE 2022 " +";");
			
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader1 " + "GROUP " + fx.getHome_Team().getGroupname() + " " + slashOrDash + " TIE " + fx.getMatchnumber() +";");
			
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader2 " + "UP NEXT" +";");
			
			
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgHomeTeamLogo " + logo_path + fx.getHome_Team().getShortname().toUpperCase() + ".png" + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHomeTeamFirstName " + fx.getHome_Team().getFirstname().toUpperCase() + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHomeTeamLastName " + fx.getHome_Team().getLastname().toUpperCase() + ";");
			//print_writer.println("LAYER1*EVEREST*TREEVIEW*TEAM1LastName*CONTAINER SET ACTIVE 0;");
			
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgAwayTeamLogo " + logo_path + fx.getAway_Team().getShortname().toUpperCase() + ".png" + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tAwayTeamFirstName " + fx.getAway_Team().getFirstname().toUpperCase() + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tAwayTeamLastName " + fx.getAway_Team().getLastname().toUpperCase() + ";");			
			//print_writer.println("LAYER1*EVEREST*TREEVIEW*TEAM2LastName*CONTAINER SET ACTIVE 0;");
		
			print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW ON;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In STOP;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out STOP;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 98.0;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
			print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT_PATH C:/Temp/FF_TiePromo.bmp;");
			print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT 1920 1080;");
			TimeUnit.SECONDS.sleep(1);
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 0.0;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
			print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW OFF;");
			this.status = BadmintonUtil.SUCCESSFUL;	
		}
	}
	public void populateFFSingleMatchPromo(PrintWriter print_writer,String viz_sence_path,Match single_match, List<Player> allplayer, List<Team> allteam,String selectedbroadcaster) throws InterruptedException 
	{
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHeader " + "GRAND PRIX BADMINTON LEAGUE 2022" +";");
			
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader1 " + single_match.getMatchIdent() + " " + slashOrDash + 
					" MATCH " + single_match.getMatchnumber() +";");
			
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTrumpText " + "UP NEXT" +";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTrumpText " + "UP NEXT" +";");
			
			if(single_match.getCategoryId() == 1) {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader2 " + "WOMEN'S SINGLES" +";");
			}
			
			if(single_match.getCategoryId() == 3) {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader2 " + "MEN'S SINGLES" +";");
			}
			
			for(Player player : allplayer) {
				if(single_match.getHomeFirstPlayerId() == player.getPlayerId()) {
					for(Team team : allteam) {
						if(player.getTeamId() == team.getTeamId()) {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgHomeTeamLogo " + logo_path +  
									team.getShortname().toUpperCase() + ".png"  + ";");
						}
					}
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgHomePlayerImage " + left_path + player.getTicker_name() + ".png" + ";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHomePlayerName " + player.getFull_name().toUpperCase()+ ";");
					
				}
				if(single_match.getAwayFirstPlayerId() == player.getPlayerId()) {
					for(Team team : allteam) {
						if(player.getTeamId() == team.getTeamId()) {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgAwayTeamLogo " + logo_path +  
									team.getShortname().toUpperCase() + ".png"  + ";");
						}
					}
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgAwayPlayerImage " + right_path + player.getTicker_name() + ".png" + ";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tAwayPlayerName " + player.getFull_name().toUpperCase() + ";");
				}
			}
			
			if(single_match.getTrumpHomeMatch() == 1 ) {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vHomeTrump " + "1" +";");
			}else {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vHomeTrump " + "0" +";");
			}
			
			if(single_match.getTrumpAwayMatch() == 1 ) {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vAwayTrump " + "1" +";");
			}else {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vAwayTrump " + "0" +";");
			}
			
			print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW ON;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In STOP;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out STOP;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 123.0;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
			print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT_PATH C:/Temp/FF_SingleMatchPromo.bmp;");
			print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT 1920 1080;");
			TimeUnit.SECONDS.sleep(1);
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 0.0;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
			print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW OFF;");	
			this.status = BadmintonUtil.SUCCESSFUL;	

	}
	public void populateLTSingleMatchPromo(PrintWriter print_writer,String viz_sence_path,Match single_match, List<Player> allplayer, List<Team> allteam,String selectedbroadcaster) throws InterruptedException 
	{
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHeader " + single_match.getMatchIdent() + " " + slashOrDash + 
					" MATCH " + single_match.getMatchnumber() +";");
			
			//print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader1 "  +";");
			
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScore_VS " + "UP NEXT" +";");
			
			if(single_match.getCategoryId() == 1) {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader " + "WOMEN'S SINGLES" +";");
			}
			
			if(single_match.getCategoryId() == 3) {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader " + "MEN'S SINGLES" +";");
			}
			
			for(Player player : allplayer) {
				if(single_match.getHomeFirstPlayerId() == player.getPlayerId()) {
					for(Team team : allteam) {
						if(player.getTeamId() == team.getTeamId()) {
							if(single_match.getTrumpHomeMatch() == 1) {
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vHomeTrump_NOTrump " + "1" + ";");
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHomeTeamName " + team.getFullname().toUpperCase()  + ";");
							}else {
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vHomeTrump_NOTrump " + "0" + ";");
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHomeTeamName " + team.getFullname().toUpperCase()  + ";");
							}
							//print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHomeTeamName " + team.getFullname().toUpperCase() + ";");
						}
					}
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgHomePlayer1Image " + left_path + player.getTicker_name() + ".png" + ";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHomePlayerName " + player.getFull_name().toUpperCase()+ ";");
					
				}
				if(single_match.getAwayFirstPlayerId() == player.getPlayerId()) {
					for(Team team : allteam) {
						if(player.getTeamId() == team.getTeamId()) {
							if(single_match.getTrumpAwayMatch() == 1) {
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vAwayTrump_NOTrump " + "1" + ";");
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tAwayTeamName " + team.getFullname().toUpperCase() + ";");
							}else {
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vAwayTrump_NOTrump " + "0" + ";");
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tAwayTeamName " + team.getFullname().toUpperCase() + ";");
							}
							//print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tAwayTeamName " + team.getFullname().toUpperCase() + ";");
						}
					}
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgAwayPlayer1Image " + right_path + player.getTicker_name() + ".png" + ";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tAwayPlayerName " + player.getFull_name().toUpperCase() + ";");
				}
			}
			
			/*if(single_match.getTrumpHomeMatch() == 1 ) {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vHomeTrump " + "1" +";");
			}else {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vHomeTrump " + "0" +";");
			}
			
			if(single_match.getTrumpAwayMatch() == 1 ) {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vAwayTrump " + "1" +";");
			}else {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vAwayTrump " + "0" +";");
			}*/
			print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW ON;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In STOP;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out STOP;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 106.0;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
			print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT_PATH C:/Temp/LT_SingleMatchPromo.bmp;");
			print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT 1920 1080;");
			TimeUnit.SECONDS.sleep(1);
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 0.0;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
			print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW OFF;");	
			this.status = BadmintonUtil.SUCCESSFUL;	

	}	
	public void populateLTDoubleMatchPromo(PrintWriter print_writer,String viz_sence_path,Match double_match, List<Player> allplayer, List<Team> allteam,String selectedbroadcaster) throws InterruptedException 
	{
		String home_second_player_name = "" , away_first_player_name = "" ;
		
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHeader " + double_match.getMatchIdent() + " " + slashOrDash + 
					" MATCH " + double_match.getMatchnumber() +";");
			
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScore_VS " + "UP NEXT" +";");
			
			if(double_match.getCategoryId() == 2) {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader " + "MEN'S DOUBLES" +";");
			}
			
			if(double_match.getCategoryId() == 4) {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader " + "MIXED DOUBLES" +";");
			}
			
			for(Player player : allplayer) {
				if(double_match.getHomeSecondPlayerId() == player.getPlayerId()) {
					for(Team team : allteam) {
						if(player.getTeamId() == team.getTeamId()) {
							if(double_match.getTrumpHomeMatch() == 1) {
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vHomeTrump_NOTrump " + "1" + ";");
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHomeTeamName " + team.getFullname().toUpperCase()  + ";");
							}else {
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vHomeTrump_NOTrump " + "0" + ";");
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHomeTeamName " + team.getFullname().toUpperCase()  + ";");
							}
						}
					}
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgHomePlayer2Image " + left_path + player.getTicker_name() + ".png" + ";");
					home_second_player_name = player.getTicker_name().toUpperCase();
				}
				
				if(double_match.getHomeFirstPlayerId() == player.getPlayerId()) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgHomePlayer1Image " + left_path + player.getTicker_name() + ".png" + ";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHomePlayerName " + player.getTicker_name().toUpperCase() + " / " + home_second_player_name + ";");
				}
				
				if(double_match.getAwayFirstPlayerId() == player.getPlayerId()) {
					for(Team team : allteam) {
						if(player.getTeamId() == team.getTeamId()) {
							if(double_match.getTrumpAwayMatch() == 1) {
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vAwayTrump_NOTrump " + "1" + ";");
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tAwayTeamName " + team.getFullname().toUpperCase() + ";");
							}else {
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vAwayTrump_NOTrump " + "0" + ";");
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tAwayTeamName " + team.getFullname().toUpperCase() + ";");
							}
						}
					}
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgAwayPlayer1Image " + right_path + player.getTicker_name() + ".png" + ";");
					away_first_player_name = player.getTicker_name().toUpperCase() ;
				}
				
				if(double_match.getAwaySecondPlayerId() == player.getPlayerId()) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgAwayPlayer2Image " + right_path + player.getTicker_name() + ".png" + ";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tAwayPlayerName " + away_first_player_name + " / " + player.getTicker_name().toUpperCase() + ";");
				}
				
			}
			
			
			print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW ON;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In STOP;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out STOP;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 75.0;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
			print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT_PATH C:/Temp/LT_DoubleMatchPromo.bmp;");
			print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT 1920 1080;");
			TimeUnit.SECONDS.sleep(1);
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 0.0;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
			print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW OFF;");	
			this.status = BadmintonUtil.SUCCESSFUL;	

	}
	public void populateFFDoubleMatchPromo(PrintWriter print_writer,String viz_sence_path,Match double_match, List<Player> allplayer, List<Team> allteam, String selectedbroadcaster) throws InterruptedException 
	{
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHeader " + "GRAND PRIX BADMINTON LEAGUE 2022 " +";");
			
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader1 " + double_match.getMatchIdent() + " " + slashOrDash + 
					" MATCH " + double_match.getMatchnumber() +";");
			
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vTrumpMatch " + "1" +";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*TrumpGrp*CONTAINER SET ACTIVE 1;");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTrumpText " + "UP NEXT" + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTrumpText " + "UP NEXT" + ";");
			
				
				if(double_match.getCategoryId() == 2) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader2 " + "MEN'S DOUBLES" +";");
				}
				
				if(double_match.getCategoryId() == 4) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader2 " + "MIXED DOUBLES" +";");
				}

				for(Player player : allplayer) {
					if(double_match.getHomeFirstPlayerId() == player.getPlayerId()) {
						for(Team team : allteam) {
							if(player.getTeamId() == team.getTeamId()) {
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgHomeTeamLogo " + logo_path +  team.getShortname().toUpperCase() + ".png"  + ";");
							}
						}
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgHomePlayerImage1 " + left_path + player.getTicker_name() + ".png" + ";");
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHomePlayer1Name " + player.getFull_name().toUpperCase() + ";");
					}
					
					if(double_match.getHomeSecondPlayerId() == player.getPlayerId()) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgHomePlayerImage2 " + left_path + player.getTicker_name() + ".png" + ";");
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHomePlayer2Name " + player.getFull_name().toUpperCase() + ";");
					}
					
					
					
					if(double_match.getAwayFirstPlayerId() == player.getPlayerId()) {
						for(Team team : allteam) {
							if(player.getTeamId() == team.getTeamId()) {
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgAwayTeamLogo " + logo_path +  team.getShortname().toUpperCase() + ".png"  + ";");
							}
						}
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgAwayPlayerImage1 " + right_path + player.getTicker_name() + ".png" + ";");
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tAwayPlayer1Name " + player.getFull_name().toUpperCase() +  ";");
					}
					
					if(double_match.getAwaySecondPlayerId() == player.getPlayerId()) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgAwayPlayerImage2 " + right_path + player.getTicker_name() + ".png" + ";");
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tAwayPlayer2Name " + player.getFull_name().toUpperCase() +  ";");
						
					}
					
				}
			
			if(double_match.getTrumpHomeMatch() == 1 ) {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vHomeTrump " + "1" +";");
			}else {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vHomeTrump " + "0" +";");	
			}
			
			if(double_match.getTrumpAwayMatch() == 1 ) {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vAwayTrump " + "1" +";");
			}else {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vAwayTrump " + "0" +";");
			}
			
			print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW ON;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In STOP;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out STOP;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 82.0;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
			print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT_PATH C:/Temp/FF_DoubleMatchPromo.bmp;");
			print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT 1920 1080;");
			TimeUnit.SECONDS.sleep(1);
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 0.0;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
			print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW OFF;");	
			this.status = BadmintonUtil.SUCCESSFUL;	
	}
	
	public void populateSquads(PrintWriter print_writer,String viz_sence_path,Team selected_team, List<Player> allplayer, List<Team> allteam,String selectedbroadcaster) throws InterruptedException 
	{
		int row_id=1;
		
		print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHeader " + "GRAND PRIX BADMINTON LEAGUE 2022 " +";");
		print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader1 " + "SQUAD " + slashOrDash + " " + 
				selected_team.getFullname().toUpperCase() + ";");
		print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgTeamLogo " + logo_path +  selected_team.getShortname().toUpperCase() + ".png"  + ";");
		
		
		
		
		for(Player player : allplayer) {
			if(selected_team.getMentorName() == null) {
				if(selected_team.getTeamId() == player.getTeamId()) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vHidePlayer8 " + "0" +";");
					if(player.getIconPlayer().toUpperCase().equalsIgnoreCase("YES")) {
						//row_id = row_id + 1;
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgMentroImage " + right_path + player.getTicker_name() + ".png" + ";");
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tMentorHead " + "ICON" +";");
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tMentorName " + player.getFull_name().toUpperCase() +";");
					}else {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgPlayerImage" + row_id + " " + left_path + player.getTicker_name() + ".png" + ";");

						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerName" + row_id + " " + player.getFull_name().toUpperCase() +";");
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerAge" + row_id + " " + "Age: " +  player.getAge() +";");

						
						row_id = row_id + 1;
					}
				}
				
			}else {
				if(selected_team.getTeamId() == player.getTeamId()) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vKeyPlayer " + "1" +";");
					if(player.getIconPlayer().toUpperCase().equalsIgnoreCase("YES")) {
						//row_id = row_id + 1;
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgPlayerImage1 " + left_path + player.getTicker_name() + ".png" + ";");

						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerName1 " + player.getFull_name().toUpperCase() +";");
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerAge1 " + "Age: " + player.getAge() +";");
					}else {
						row_id = row_id + 1;
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgPlayerImage" + row_id + " " + left_path + player.getTicker_name() + ".png" + ";");
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerName" + row_id + " " + player.getFull_name().toUpperCase() +";");
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerAge" + row_id + " " + "Age: " + player.getAge() +";");

					}
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgMentroImage " + right_path + selected_team.getMentorName() + ".png" + ";");
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tMentorName " + selected_team.getMentorName().toUpperCase() +";");
				}
			}
		}
		
		print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW ON;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In STOP;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out STOP;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 83.0;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
		print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT_PATH C:/Temp/Squad.bmp;");
		print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT 1920 1080;");
		TimeUnit.SECONDS.sleep(1);
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 0.0;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
		print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW OFF;");
		this.status = BadmintonUtil.SUCCESSFUL;	

	}
	

	public void populatePointsTable(PrintWriter print_writer,String viz_sence_path,List<LeagueTeam> lt, String selectedbroadcaster) 
	{
		int row_id=0;
		for(int i = 0; i <= lt.size()-1; i++) {
			row_id = row_id + 1;
			if(lt.get(i).getGroupName().equalsIgnoreCase("Group A")) {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vGroup0" + row_id + " " + "0" +";");
			}else {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vGroup0" + row_id + " " + "1" +";");
			}
			
			System.out.println("Team names:" + lt.get(i).getTeamName());
			
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tStatValue0" + row_id + "A " + lt.get(i).getTeamName() + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tStatValue0" + row_id + "B " + lt.get(i).getPlayed() + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tStatValue0" + row_id + "C " + lt.get(i).getWon() + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tStatValue0" + row_id + "D " + lt.get(i).getLost() + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tStatValue0" + row_id + "E " + lt.get(i).getTrumpMatchWin() + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tStatValue0" + row_id + "F " + lt.get(i).getTrumpMatchLost() + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tStatValue0" + row_id + "G " + lt.get(i).getSuperMatchWin() + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tStatValue0" + row_id + "H " + lt.get(i).getPoints() + ";");
		}
		
		this.status = BadmintonUtil.SUCCESSFUL;	
	}
	public void populateSuper(PrintWriter print_writer,String viz_sence_path,NameSuper ns,int sponsor, String selectedbroadcaster) throws InterruptedException 

	{
		print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHeader " + ns.getFirstname().toUpperCase() + " " + ns.getSurname().toUpperCase() + ";");
		print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader " + ns.getSubLine()  + ";");
		print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vSponsor " + "0" + ";");
		//print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHeader " + "DAY " + Bad_match.getMatch().getMatchId() +";");
		
		if(sponsor == 0) {
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vSponsor " + "0" + ";");
		}else {
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vSponsor " + "1" + ";");
		}
		
		print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW ON;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In STOP;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out STOP;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 79.0;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
		print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT_PATH C:/Temp/NameSuper.bmp;");
		print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT 1920 1080;");
		TimeUnit.SECONDS.sleep(1);
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 0.0;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
		print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW OFF;");
		this.status = BadmintonUtil.SUCCESSFUL;	
	}
	public void populateNameSuperPlayer(PrintWriter print_writer,String viz_sence_path,Player pp,List<Team> allteam, int sponsor ,String selectedbroadcaster) throws InterruptedException 
	{
		print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHeader " + pp.getFull_name().toUpperCase() + ";");
		for(Team team : allteam) {
			if(pp.getTeamId() == team.getTeamId()) {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader " + team.getFullname().toUpperCase() + ";");
			}
		}
		if(sponsor == 0) {
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vSponsor " + "0" + ";");
		}else {
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vSponsor " + "1" + ";");
		}
		
		//print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHeader " + "DAY " + Bad_match.getMatch().getMatchId() +";");
			
		print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW ON;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In STOP;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out STOP;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 79.0;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
		print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT_PATH C:/Temp/NameSuperPlayer.bmp;");
		print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT 1920 1080;");
		TimeUnit.SECONDS.sleep(1);
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 0.0;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
		print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW OFF;");	
		this.status = BadmintonUtil.SUCCESSFUL;	
	}
	
	public void populateSuperMatch(PrintWriter print_writer,String viz_sence_path,Fixture fx,BadmintonMatch Bad_match, String selectedbroadcaster) throws InterruptedException 
	{
		if (Bad_match == null) {
			this.status = "ERROR: Match is null";
		} else {
			if(Bad_match.getMatch().getSuperMatch() == 1) {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScore1 " + " " +";");
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScore2 " + " " +";");
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScore3 " + " " +";");
				
				if(Bad_match.getSets() == null ) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScore " + "VS"  +";");
				}
				else {
					for(Set st : Bad_match.getSets()) {
						if(st.getSetNumber() == 1) {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScore " + st.getHomeTeamTotalScore() + "-" + st.getAwayTeamTotalScore() +";");
						}
					}
				}
				
				if((Bad_match.getMatch().getHomeTeam().getTeamId() == fx.getHometeam()) && (Bad_match.getMatch().getAwayTeam().getTeamId() == fx.getAwayteam())) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader2 " + "TIE " + fx.getMatchnumber() + " - " + "MATCH " + Bad_match.getMatch().getMatchnumber() +";");
					
				}

				if(Bad_match.getMatch().getHomeSecondPlayerId() == 0) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerA1 " + Bad_match.getMatch().getHomePlayers().get(0).getTicker_name().toUpperCase() +";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerA2 " + Bad_match.getMatch().getAwayPlayers().get(0).getTicker_name().toUpperCase() +";");

					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerB1 " + " " +";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerB2 " + " " +";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerC1 " + " " +";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerC2 " + " " +";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Second*CONTAINER SET ACTIVE 1;");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Third*CONTAINER SET ACTIVE 1;");

					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vHomePlayerNum " + "0" +";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vAwayPlayerNum " + "0" +";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgHomePlayerImage1 " + left_path + Bad_match.getMatch().getHomePlayers().get(0).getTicker_name() + ".png" +";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgAwayPlayerImage1 " + right_path + Bad_match.getMatch().getAwayPlayers().get(0).getTicker_name() + ".png" +";");

				}
				else {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerA1 " + Bad_match.getMatch().getHomePlayers().get(0).getTicker_name().toUpperCase() +";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerA2 " + Bad_match.getMatch().getAwayPlayers().get(0).getTicker_name().toUpperCase() +";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerB1 " + Bad_match.getMatch().getHomePlayers().get(1).getTicker_name().toUpperCase() +";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerB2 " + Bad_match.getMatch().getAwayPlayers().get(1).getTicker_name().toUpperCase() +";");

					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerC1 " + " " +";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerC2 " + " " +";");

					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgHomePlayerImage1 " + left_path + Bad_match.getMatch().getHomePlayers().get(0).getTicker_name() + ".png" +";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgAwayPlayerImage1 " + right_path + Bad_match.getMatch().getAwayPlayers().get(0).getTicker_name() + ".png" +";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgHomePlayerImage2 " + left_path + Bad_match.getMatch().getHomePlayers().get(1).getTicker_name() + ".png" +";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgAwayPlayerImage2 " + right_path + Bad_match.getMatch().getAwayPlayers().get(1).getTicker_name() + ".png" +";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vHomePlayerNum " + "1" +";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vAwayPlayerNum " + "1" +";");

					print_writer.println("LAYER1*EVEREST*TREEVIEW*Second*CONTAINER SET ACTIVE 1;");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Third*CONTAINER SET ACTIVE 1;");
				}
				if(Bad_match.getMatch().getHomeThirdPlayerId() != 0) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerA1 " + Bad_match.getMatch().getHomePlayers().get(0).getTicker_name().toUpperCase() +";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerA2 " + Bad_match.getMatch().getAwayPlayers().get(0).getTicker_name().toUpperCase() +";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerB1 " + Bad_match.getMatch().getHomePlayers().get(1).getTicker_name().toUpperCase() +";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerB2 " + Bad_match.getMatch().getAwayPlayers().get(1).getTicker_name().toUpperCase() +";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerC1 " + Bad_match.getMatch().getHomePlayers().get(2).getTicker_name().toUpperCase() +";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerC2 " + Bad_match.getMatch().getAwayPlayers().get(2).getTicker_name().toUpperCase() +";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgHomePlayerImage1 " + left_path + Bad_match.getMatch().getHomePlayers().get(0).getTicker_name() + ".png" +";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgAwayPlayerImage1 " + right_path + Bad_match.getMatch().getAwayPlayers().get(0).getTicker_name() + ".png" +";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgHomePlayerImage2 " + left_path + Bad_match.getMatch().getHomePlayers().get(1).getTicker_name() + ".png" +";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgAwayPlayerImage2 " + right_path + Bad_match.getMatch().getAwayPlayers().get(1).getTicker_name() + ".png" +";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgHomePlayerImage3 " + left_path + Bad_match.getMatch().getHomePlayers().get(2).getTicker_name() + ".png" +";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgAwayPlayerImage3 " + right_path + Bad_match.getMatch().getAwayPlayers().get(2).getTicker_name() + ".png" +";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vHomePlayerNum " + "2" +";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vAwayPlayerNum " + "2" +";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Second*CONTAINER SET ACTIVE 1;");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Third*CONTAINER SET ACTIVE 1;");
				}
			}
			else {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*CenterPart*CONTAINER SET ACTIVE 0;");
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Score*CONTAINER SET ACTIVE 0;");
				print_writer.println("LAYER1*EVEREST*TREEVIEW*First*CONTAINER SET ACTIVE 0;");
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Second*CONTAINER SET ACTIVE 0;");
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Third*CONTAINER SET ACTIVE 0;");
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgHomeTeamLogo " + logo_path + Bad_match.getMatch().getHomeTeam().getShortname().toUpperCase() + ".png" +";");
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgAwayTeamLogo " + logo_path + Bad_match.getMatch().getAwayTeam().getShortname().toUpperCase() + ".png" +";");
			}
			
			
			print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW ON;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In STOP;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out STOP;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 110.0;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
			print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT_PATH C:/Temp/FF_SuperMatch.bmp;");
			print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT 1920 1080;");
			TimeUnit.SECONDS.sleep(1);
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 0.0;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
			print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW OFF;");	
			this.status = BadmintonUtil.SUCCESSFUL;	
		}
	}
	public void populateSuperMatch1(PrintWriter print_writer,String viz_sence_path,Fixture fx,BadmintonMatch Bad_match, String selectedbroadcaster) throws InterruptedException 
	{
		if (Bad_match == null) {
			this.status = "ERROR: Match is null";
		} else {
			if(Bad_match.getMatch().getSuperMatch() == 1) {
				
				if((Bad_match.getMatch().getHomeTeam().getTeamId() == fx.getHometeam()) && (Bad_match.getMatch().getAwayTeam().getTeamId() == fx.getAwayteam())) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHeader " + "TIE " + fx.getMatchnumber() + " - " + "MATCH " + Bad_match.getMatch().getMatchnumber() +";");
				}
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader " + "SUPER MATCH" +";");
				print_writer.println("LAYER1*EVEREST*TREEVIEW*HomePlayer1*CONTAINER SET ACTIVE 0;");
				print_writer.println("LAYER1*EVEREST*TREEVIEW*AwaysPlayer1*CONTAINER SET ACTIVE 0;");
				if(Bad_match.getSets() == null ) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScore_VS " + " "  +";");
				}
				else {
					for(Set st : Bad_match.getSets()) {
						if(st.getSetNumber() == 1) {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScore_VS " + st.getHomeTeamTotalScore() + "-" + st.getAwayTeamTotalScore() +";");
						}
					}
				}
				if(Bad_match.getMatch().getHomeSecondPlayerId() == 0) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHomePlayerName " + Bad_match.getMatch().getHomePlayers().get(0).getTicker_name().toUpperCase() +";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tAwayPlayerName " + Bad_match.getMatch().getAwayPlayers().get(0).getTicker_name().toUpperCase() +";");
				}
				if(Bad_match.getMatch().getTrumpHomeMatch() == 1) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vHomeTrump_NOTrump " + "1"  +";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHomeTeamName " + Bad_match.getMatch().getHomeTeam().getFullname().toUpperCase()  +";");

				}
				else if (Bad_match.getMatch().getTrumpHomeMatch() == 0) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vHomeTrump_NOTrump " + "0"  +";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHomeTeamName " + Bad_match.getMatch().getHomeTeam().getFullname().toUpperCase()  +";");
				}
				if(Bad_match.getMatch().getTrumpAwayMatch() == 1) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vAwayTrump_NOTrump " + "1"  +";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tAwayTeamName " + Bad_match.getMatch().getAwayTeam().getFullname().toUpperCase()  +";");

				}
				else if (Bad_match.getMatch().getTrumpAwayMatch() == 0) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vAwayTrump_NOTrump " + "0"  +";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tAwayTeamName " + Bad_match.getMatch().getAwayTeam().getFullname().toUpperCase()  +";");
				}
			
				print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW ON;");
				print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In STOP;");
				print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out STOP;");
				print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 106.0;");
				print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
				print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT_PATH C:/Temp/SuperMatch1.bmp;");
				print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT 1920 1080;");
				TimeUnit.SECONDS.sleep(1);
				print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 0.0;");
				print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
				print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW OFF;");	
				this.status = BadmintonUtil.SUCCESSFUL;	
		}
	}
}
	public void populateSuperMatch2(PrintWriter print_writer,String viz_sence_path,Fixture fx,BadmintonMatch Bad_match, String selectedbroadcaster) throws InterruptedException 
	{
		if (Bad_match == null) {
			this.status = "ERROR: Match is null";
		} else {
			if(Bad_match.getMatch().getSuperMatch() == 1) {
				
				if((Bad_match.getMatch().getHomeTeam().getTeamId() == fx.getHometeam()) && (Bad_match.getMatch().getAwayTeam().getTeamId() == fx.getAwayteam())) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHeader " + "TIE " + fx.getMatchnumber() + " - " + "MATCH " + Bad_match.getMatch().getMatchnumber() +";");
				}
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader " + "SUPER MATCH" +";");
				print_writer.println("LAYER1*EVEREST*TREEVIEW*HomePlayer1*CONTAINER SET ACTIVE 0;");
				print_writer.println("LAYER1*EVEREST*TREEVIEW*HomePlayer2*CONTAINER SET ACTIVE 0;");
				print_writer.println("LAYER1*EVEREST*TREEVIEW*AwaysPlayer1*CONTAINER SET ACTIVE 0;");
				print_writer.println("LAYER1*EVEREST*TREEVIEW*AwaysPlayer2*CONTAINER SET ACTIVE 0;");
				if(Bad_match.getSets() == null ) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScore_VS " + " "  +";");
				}
				else {
					for(Set st : Bad_match.getSets()) {
						if(st.getSetNumber() == 1) {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScore_VS " + st.getHomeTeamTotalScore() + "-" + st.getAwayTeamTotalScore() +";");
						}
					}
				}
				if(Bad_match.getMatch().getHomeSecondPlayerId() != 0) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHomePlayerName " + Bad_match.getMatch().getHomePlayers().get(0).getTicker_name().toUpperCase() + " / " + Bad_match.getMatch().getHomePlayers().get(1).getTicker_name().toUpperCase() +";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tAwayPlayerName " + Bad_match.getMatch().getAwayPlayers().get(0).getTicker_name().toUpperCase() + " / " + Bad_match.getMatch().getAwayPlayers().get(1).getTicker_name().toUpperCase() +";");
				}
				if(Bad_match.getMatch().getTrumpHomeMatch() == 1) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vHomeTrump_NOTrump " + "1"  +";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHomeTeamName " + Bad_match.getMatch().getHomeTeam().getFullname().toUpperCase()  +";");
				}
				else if (Bad_match.getMatch().getTrumpHomeMatch() == 0) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vHomeTrump_NOTrump " + "0"  +";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHomeTeamName " + Bad_match.getMatch().getHomeTeam().getFullname().toUpperCase()  +";");
				}
				if(Bad_match.getMatch().getTrumpAwayMatch() == 1) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vAwayTrump_NOTrump " + "1"  +";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tAwayTeamName " + Bad_match.getMatch().getAwayTeam().getFullname().toUpperCase()  +";");
				}
				else if (Bad_match.getMatch().getTrumpAwayMatch() == 0) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vAwayTrump_NOTrump " + "0"  +";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tAwayTeamName " + Bad_match.getMatch().getAwayTeam().getFullname().toUpperCase()  +";");
				}
			
				print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW ON;");
				print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In STOP;");
				print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out STOP;");
				print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 75.0;");
				print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
				print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT_PATH C:/Temp/SuperMatch2.bmp;");
				print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT 1920 1080;");
				TimeUnit.SECONDS.sleep(1);
				print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 0.0;");
				print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
				print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW OFF;");	
				this.status = BadmintonUtil.SUCCESSFUL;	
			}
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