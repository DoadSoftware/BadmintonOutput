package com.badminton.broadcaster;


import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.badminton.model.*;
import com.badminton.containers.Scene;
import com.badminton.util.BadmintonUtil;

public class Doad extends Scene{
	
	private String status;
	private String slashOrDash = "-";
	
	private String logo_path = "D:\\DOAD_In_House_Everest\\Everest_Sports\\Everest_GBPL\\Logos\\";
	//private String center_path = "D:\\DOAD_In_House_Everest\\Everest_Sports\\Everest_GBPL\\Photos\\MEDIUM\\";
	private String left_path = "C:\\Images\\LEFT\\";
	private String right_path = "C:\\Images\\RIGHT\\";
	//private String centre_path = "C:\\Images\\CENTRE\\";
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

	public void populateScoreBug(boolean is_this_updating, PrintWriter print_writer,String viz_sence_path, BadmintonMatch Bad_match, String selectedbroadcaster) 
	{
		if (Bad_match == null) {
			this.status = "ERROR: Match is null";
		} else {
			this.status = BadmintonUtil.SUCCESSFUL;	
			int Count = 0;
			//System.out.println("outside ="+Count);
			if(Bad_match.getGoldenPointsPlayerId() == Bad_match.getMatch().getHomeFirstPlayerId()) {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vGoldenPointOption " + "1" +";");
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vGoldenPoint " + "1" +";");
				Count = Count + 1 ;
				//print_writer.println("LAYER1*EVEREST*TREEVIEW*GoldenPoint*CONTAINER SET ACTIVE 1;");
			}
			else if(Bad_match.getGoldenPointsPlayerId() == Bad_match.getMatch().getAwayFirstPlayerId()) {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vGoldenPointOption " + "1" +";");
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vGoldenPoint " + "2" +";");
				Count = Count + 1 ;
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
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerName1 " + Bad_match.getMatch().getHomePlayers().
									get(Bad_match.getMatch().getHomePlayers().size() - a).getTicker_name().toUpperCase() +";");
						}
						else if(a==2){
							int j=a-1;
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerName1 " + Bad_match.getMatch().getHomePlayers().
										get(Bad_match.getMatch().getHomePlayers().size() - a).getTicker_name().toUpperCase() + "/" 
										+ Bad_match.getMatch().getHomePlayers().get(Bad_match.getMatch().getHomePlayers().size() - j).getTicker_name().toUpperCase() + ";");
						}
						else if(a==3){
							int j=a-1;
							int k=a-2;
							if(Bad_match.getMatch().getSuperMatch() == 1) {
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerName1 " + Bad_match.getMatch().getHomePlayers().get(Bad_match.getMatch().getHomePlayers().size() - a).getTicker_sm_name().toUpperCase() + 
										" / " + Bad_match.getMatch().getHomePlayers().get(Bad_match.getMatch().getHomePlayers().size() - j).getTicker_sm_name().toUpperCase() + " / " 
										+ Bad_match.getMatch().getHomePlayers().get(Bad_match.getMatch().getHomePlayers().size() - k).getTicker_sm_name().toUpperCase() + ";");
							}else{
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerName1 " + Bad_match.getMatch().getHomePlayers().get(Bad_match.getMatch().getHomePlayers().size() - a).getTicker_name().toUpperCase() + "/" 
										+ Bad_match.getMatch().getHomePlayers().get(Bad_match.getMatch().getHomePlayers().size() - j).getTicker_name().toUpperCase() + "/" 
										+ Bad_match.getMatch().getHomePlayers().get(Bad_match.getMatch().getHomePlayers().size() - k).getTicker_name().toUpperCase() + ";");
							}
							
							
						}
						
					}
				}				
								
				if(Bad_match.getMatch().getAwayPlayers() !=null && Bad_match.getMatch().getAwayPlayers().size() > 0) {
					for(int a=1; a <= Bad_match.getMatch().getAwayPlayers().size();a++){
						if(a == 1){
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerName2 " + Bad_match.getMatch().getAwayPlayers().
									get(Bad_match.getMatch().getAwayPlayers().size() - a).getTicker_name().toUpperCase() +";");
						}
						else if(a == 2){
							int j=a-1;
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerName2 " + Bad_match.getMatch().getAwayPlayers().
										get(Bad_match.getMatch().getAwayPlayers().size() - a).getTicker_name().toUpperCase() + "/" 
										+ Bad_match.getMatch().getAwayPlayers().get(Bad_match.getMatch().getAwayPlayers().size() - j).getTicker_name().toUpperCase() + ";");
						}
						
						else if(a == 3){
							int j=a-1;
							int k=a-2;
							if(Bad_match.getMatch().getSuperMatch() == 1) {
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerName2 " + Bad_match.getMatch().getAwayPlayers().get(Bad_match.getMatch().getAwayPlayers().size() - a).getTicker_sm_name().toUpperCase() + 
										" / " + Bad_match.getMatch().getAwayPlayers().get(Bad_match.getMatch().getAwayPlayers().size() - j).getTicker_sm_name().toUpperCase() + " / " 
										+ Bad_match.getMatch().getAwayPlayers().get(Bad_match.getMatch().getAwayPlayers().size() - k).getTicker_sm_name().toUpperCase() + ";");
							}else {
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerName2 " + Bad_match.getMatch().getAwayPlayers().get(Bad_match.getMatch().getAwayPlayers().size() - a).getTicker_name().toUpperCase() + "/" 
										+ Bad_match.getMatch().getAwayPlayers().get(Bad_match.getMatch().getAwayPlayers().size() - j).getTicker_name().toUpperCase() + "/" 
										+ Bad_match.getMatch().getAwayPlayers().get(Bad_match.getMatch().getAwayPlayers().size() - k).getTicker_name().toUpperCase() + ";");
							}
						}
						
					}
				}
				
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTeamColor1 " + Bad_match.getMatch().getHomeTeam().getRgb() +";");
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTeamColor2 " + Bad_match.getMatch().getAwayTeam().getRgb() +";");
				
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
							if(Bad_match.getGoldenPointsPlayerId() != 0  && st.getHomeTeamTotalScore() == 0 &&  st.getAwayTeamTotalScore() == 0) {
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vGoldenPoint " + "0" +";");
							}
							
							
							if(((st.getHomeTeamTotalScore() == 0 && st.getAwayTeamTotalScore() == 1) || (st.getHomeTeamTotalScore() == 1 && st.getAwayTeamTotalScore() == 0))
									|| ((st.getHomeTeamTotalScore() == 0 && st.getAwayTeamTotalScore() == 2) || (st.getHomeTeamTotalScore() == 2 && st.getAwayTeamTotalScore() == 0))) {
								this.status = BadmintonUtil.SCORES;
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vSet " + "1" +";");
							}
							
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScoreTeam1_R1 " + st.getHomeTeamTotalScore() +";");
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScoreTeam2_R1 " + st.getAwayTeamTotalScore() +";");
						}
						
					}
					if(st.getSetNumber() == 2) {
						if(st.getStatus().equalsIgnoreCase("START") || st.getStatus().equalsIgnoreCase("END")) {
							
							if(Bad_match.getGoldenPointsPlayerId() != 0  && st.getHomeTeamTotalScore() == 0 &&  st.getAwayTeamTotalScore() == 0) {
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vGoldenPoint " + "0" +";");
							}
							
							if(((st.getHomeTeamTotalScore() == 0 && st.getAwayTeamTotalScore() == 1) || (st.getHomeTeamTotalScore() == 1 && st.getAwayTeamTotalScore() == 0))
									|| ((st.getHomeTeamTotalScore() == 0 && st.getAwayTeamTotalScore() == 2) || (st.getHomeTeamTotalScore() == 2 && st.getAwayTeamTotalScore() == 0))) {
								this.status = BadmintonUtil.SCORES;
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vSet " + "2" +";");
							}
							
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScoreTeam1_R2 " + st.getHomeTeamTotalScore() +";");
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScoreTeam2_R2 " + st.getAwayTeamTotalScore() +";");
						}
					}
					if(st.getSetNumber() == 3) {
						if(st.getStatus().equalsIgnoreCase("START") || st.getStatus().equalsIgnoreCase("END")) {
							
							if(Bad_match.getGoldenPointsPlayerId() != 0  && st.getHomeTeamTotalScore() == 0 &&  st.getAwayTeamTotalScore() == 0) {
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vGoldenPoint " + "0" +";");
							}
							
							if(((st.getHomeTeamTotalScore() == 0 && st.getAwayTeamTotalScore() == 1) || (st.getHomeTeamTotalScore() == 1 && st.getAwayTeamTotalScore() == 0))
									|| ((st.getHomeTeamTotalScore() == 0 && st.getAwayTeamTotalScore() == 2) || (st.getHomeTeamTotalScore() == 2 && st.getAwayTeamTotalScore() == 0))) {
								this.status = BadmintonUtil.SCORES;
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vSet " + "3" +";");
							}

							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScoreTeam1_R3 " + st.getHomeTeamTotalScore() +";");
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScoreTeam2_R3 " + st.getAwayTeamTotalScore() +";");
						}
					}
				}
			}
			
		}
	}
	public void populateScoreBugStat(boolean is_this_updating,PrintWriter print_writer,String viz_sence_path,String Stats,BadmintonMatch Bad_match,List<Fixture> fix, String selectedbroadcaster) 
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
					print_writer.println("LAYER1*EVEREST*TREEVIEW*TopTeamGRP*CONTAINER SET ACTIVE 1;");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*BottomTeamGRP*CONTAINER SET ACTIVE 1;");
					//print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*TeamIn START;");
				}
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTeamName01 " + Bad_match.getMatch().getHomeTeam().getFullname().toUpperCase() +";");
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTeamName02 " + Bad_match.getMatch().getAwayTeam().getFullname().toUpperCase() +";");
				break;
				
			case"GAME_POINT":
				if(is_this_updating == false) {
					//print_writer.println("LAYER1*EVEREST*TREEVIEW*TeamName*CONTAINER SET ACTIVE 1;");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*NEW_GROUP*CONTAINER SET ACTIVE 1;");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vExtra " + "0" +";");
					//print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*TeamIn START;");
				}
				if(Bad_match.getMatch().getCategoryId() == null) {
					if(Bad_match.getMatch().getSuperMatch() == 1) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTeamName01 " + "SUPER MATCH" + ";");
					}
				}
				else {
					if(Bad_match.getMatch().getCategoryId() == 1) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTeamName01 " + "WOMEN'S SINGLES" +";");
					}
					if(Bad_match.getMatch().getCategoryId() == 2) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTeamName01 " + "MEN'S DOUBLES" +";");
					}
					if(Bad_match.getMatch().getCategoryId() == 3) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTeamName01 " + "MEN'S SINGLES" +";");
					}
					if(Bad_match.getMatch().getCategoryId() == 4) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTeamName01 " + "MIXED DOUBLES" +";");
					}
				}
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTeamName02 " + "GAME POINT" +";");
				print_writer.println("LAYER1*EVEREST*TREEVIEW*TopTeamGRP*CONTAINER SET ACTIVE 1;");
				print_writer.println("LAYER1*EVEREST*TREEVIEW*BottomTeamGRP*CONTAINER SET ACTIVE 1;");
				break;
				
			case "MATCH_POINT":
				if(is_this_updating == false) {
					//print_writer.println("LAYER1*EVEREST*TREEVIEW*TeamName*CONTAINER SET ACTIVE 1;");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*NEW_GROUP*CONTAINER SET ACTIVE 1;");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vExtra " + "0" +";");
					//print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*TeamIn START;");
				}
				if(Bad_match.getMatch().getCategoryId() == null) {
					if(Bad_match.getMatch().getSuperMatch() == 1) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTeamName01 " + "SUPER MATCH" + ";");
					}
				}
				else {
					if(Bad_match.getMatch().getCategoryId() == 1) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTeamName01 " + "WOMEN'S SINGLES" +";");
					}
					if(Bad_match.getMatch().getCategoryId() == 2) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTeamName01 " + "MEN'S DOUBLES" +";");
					}
					if(Bad_match.getMatch().getCategoryId() == 3) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTeamName01 " + "MEN'S SINGLES" +";");
					}
					if(Bad_match.getMatch().getCategoryId() == 4) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTeamName01 " + "MIXED DOUBLES" +";");
					}
				}
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTeamName02 " + "MATCH POINT" +";");
				print_writer.println("LAYER1*EVEREST*TREEVIEW*TopTeamGRP*CONTAINER SET ACTIVE 1;");
				print_writer.println("LAYER1*EVEREST*TREEVIEW*BottomTeamGRP*CONTAINER SET ACTIVE 1;");
				break;
				
			case "SET_POINT":
				if(is_this_updating == false) {
					//print_writer.println("LAYER1*EVEREST*TREEVIEW*TeamName*CONTAINER SET ACTIVE 1;");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*NEW_GROUP*CONTAINER SET ACTIVE 1;");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vExtra " + "0" +";");
					//print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*TeamIn START;");
				}if(Bad_match.getMatch().getCategoryId() == null) {
					if(Bad_match.getMatch().getSuperMatch() == 1) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTeamName01 " + "SUPER MATCH" + ";");
					}
				}
				else {
					if(Bad_match.getMatch().getCategoryId() == 1) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTeamName01 " + "WOMEN'S SINGLES" +";");
					}
					if(Bad_match.getMatch().getCategoryId() == 2) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTeamName01 " + "MEN'S DOUBLES" +";");
					}
					if(Bad_match.getMatch().getCategoryId() == 3) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTeamName01 " + "MEN'S SINGLES" +";");
					}
					if(Bad_match.getMatch().getCategoryId() == 4) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTeamName01 " + "MIXED DOUBLES" +";");
					}
				}
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTeamName02 " + "SET POINT" +";");
				print_writer.println("LAYER1*EVEREST*TREEVIEW*TopTeamGRP*CONTAINER SET ACTIVE 1;");
				print_writer.println("LAYER1*EVEREST*TREEVIEW*BottomTeamGRP*CONTAINER SET ACTIVE 1;");
				break;
			
			case "TIE_SCORE":
				if(is_this_updating == false) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*NEW_GROUP*CONTAINER SET ACTIVE 1;");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*OthersNameGRP*CONTAINER SET ACTIVE 1;");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*OtherDatsGrp*CONTAINER SET ACTIVE 1;");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vExtra " + "1" +";");
				}
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tExtraHead " + "CURRENT TIE SCORE" +";");
				for(Fixture fx : fix) {
					if((Bad_match.getMatch().getHomeTeam().getTeamId() == fx.getHometeam()) && (Bad_match.getMatch().getAwayTeam().getTeamId() == fx.getAwayteam())) {

						if(fx.getMargin() == null) {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tExtraData01 " + "0" +";");
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tExtraData02 " + "0" +";");
						}
						else {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tExtraData01 " + fx.getMargin().split("-")[0] +";");
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tExtraData02 " + fx.getMargin().split("-")[1] +";");
						}			
					}
				}
				break;
				
			case "CATEGORY":
				if(is_this_updating == false) {
					//print_writer.println("LAYER1*EVEREST*TREEVIEW*TeamName*CONTAINER SET ACTIVE 1;");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*NEW_GROUP*CONTAINER SET ACTIVE 1;");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vExtra " + "0" +";");
					//print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*TeamIn START;");
				}
				if(Bad_match.getMatch().getCategoryId() == null) {
					if(Bad_match.getMatch().getSuperMatch() == 1) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTeamName01 " + "SUPER MATCH" + ";");
					}
				}
				else {
					if(Bad_match.getMatch().getCategoryId() == 1) {
						
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTeamName01 " + "WOMEN'S SINGLES" +";");
						
					}
					if(Bad_match.getMatch().getCategoryId() == 2) {
						
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTeamName01 " + "MEN'S DOUBLES" +";");
						
					}
					if(Bad_match.getMatch().getCategoryId() == 3) {
						
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTeamName01 " + "MEN'S SINGLES" +";");
						
					}
					if(Bad_match.getMatch().getCategoryId() == 4) {
						
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTeamName01 " + "MIXED DOUBLES" +";");
						
					}
				}
				//print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTeamName01 " + "MATCH POINT" +";");
				print_writer.println("LAYER1*EVEREST*TREEVIEW*BottomTeamGRP*CONTAINER SET ACTIVE 0;");
				print_writer.println("LAYER1*EVEREST*TREEVIEW*TopTeamGRP*CONTAINER SET ACTIVE 1;");
				break;
			/*case "HOME":
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
				break;*/
			}
			
			
			this.status = BadmintonUtil.SUCCESSFUL;	
		}
	}
	public void populateSingleL3MatchId(PrintWriter print_writer,String viz_sence_path,List<Fixture> fix,BadmintonMatch Bad_match, String selectedbroadcaster) throws InterruptedException 
	{
		
		if (Bad_match == null) {
			this.status = "ERROR: Match is null";
		} else {
			int Set1h = 0,Set1a=0, Set2h = 0,Set2a=0;
			for(Fixture fx : fix) {
				if((Bad_match.getMatch().getHomeTeam().getTeamId() == fx.getHometeam()) && (Bad_match.getMatch().getAwayTeam().getTeamId() == fx.getAwayteam())) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHeader " + "MATCH " + Bad_match.getMatch().getMatchnumber() +";");
				}
			}
			
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgHomePlayer1Image " + left_path + Bad_match.getMatch().getHomeTeam().getFirstname() + BadmintonUtil.DOUBLE_BACKSLASH +
					Bad_match.getMatch().getHomePlayers().get(0).getTicker_name() + BadmintonUtil.PNG_EXTENSION + ";");
			
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgAwayPlayer1Image " + right_path + Bad_match.getMatch().getAwayTeam().getFirstname() + BadmintonUtil.DOUBLE_BACKSLASH +
					Bad_match.getMatch().getAwayPlayers().get(0).getTicker_name() + BadmintonUtil.PNG_EXTENSION + ";");

			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHomePlayerName " + Bad_match.getMatch().getHomePlayers().get(0).getTicker_name().toUpperCase()+ ";");
			
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tAwayPlayerName " + Bad_match.getMatch().getAwayPlayers().get(0).getTicker_name().toUpperCase() + ";");
			
			if(Bad_match.getMatch().getCategoryId() == null) {
				if(Bad_match.getMatch().getSuperMatch() == 1) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader " + "SUPER MATCH" + ";");
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
		print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT_PATH C:/Temp/Preview.bmp;");
		print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT 1920 1080;");
		TimeUnit.SECONDS.sleep(1);
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 0.0;");
		print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW OFF;");
			
		this.status = BadmintonUtil.SUCCESSFUL;	
		}
	}
	public void populateDoubleL3MatchId(PrintWriter print_writer,String viz_sence_path,List<Fixture> fix,BadmintonMatch Bad_match, String selectedbroadcaster) throws InterruptedException 
	{
		if (Bad_match == null) {
			this.status = "ERROR: Match is null";
		} else {
			int Set1h = 0,Set1a=0, Set2h = 0,Set2a=0;
			for(Fixture fx : fix) {
				if((Bad_match.getMatch().getHomeTeam().getTeamId() == fx.getHometeam()) && (Bad_match.getMatch().getAwayTeam().getTeamId() == fx.getAwayteam())) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHeader " + "MATCH " + Bad_match.getMatch().getMatchnumber() +";");
				}
			}
			
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgHomePlayer1Image " + left_path + Bad_match.getMatch().getHomeTeam().getFirstname() + BadmintonUtil.DOUBLE_BACKSLASH +
					Bad_match.getMatch().getHomePlayers().get(0).getTicker_name() + BadmintonUtil.PNG_EXTENSION + ";");
			
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgHomePlayer2Image " + left_path + Bad_match.getMatch().getHomeTeam().getFirstname() + BadmintonUtil.DOUBLE_BACKSLASH +
					Bad_match.getMatch().getHomePlayers().get(1).getTicker_name() + BadmintonUtil.PNG_EXTENSION + ";");

			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgAwayPlayer2Image " + right_path + Bad_match.getMatch().getAwayTeam().getFirstname() + BadmintonUtil.DOUBLE_BACKSLASH +
					Bad_match.getMatch().getAwayPlayers().get(0).getTicker_name() + BadmintonUtil.PNG_EXTENSION + ";");
			
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgAwayPlayer1Image " + right_path + Bad_match.getMatch().getAwayTeam().getFirstname() + BadmintonUtil.DOUBLE_BACKSLASH +
					Bad_match.getMatch().getAwayPlayers().get(1).getTicker_name() + BadmintonUtil.PNG_EXTENSION + ";");
			
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHomePlayerName " + Bad_match.getMatch().getHomePlayers().get(0).getTicker_name().toUpperCase() + " / " + 
					Bad_match.getMatch().getHomePlayers().get(1).getTicker_name().toUpperCase() + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tAwayPlayerName " + Bad_match.getMatch().getAwayPlayers().get(0).getTicker_name().toUpperCase() + " / " + 
					Bad_match.getMatch().getAwayPlayers().get(1).getTicker_name().toUpperCase() + ";");
			
			
			if(Bad_match.getMatch().getCategoryId() == null) {
				if(Bad_match.getMatch().getSuperMatch() == 1) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader " + "SUPER MATCH" + ";");
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
		print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT_PATH C:/Temp/Preview.bmp;");
		print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT 1920 1080;");
		TimeUnit.SECONDS.sleep(1);
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 0.0;");
		print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW OFF;");				
		this.status = BadmintonUtil.SUCCESSFUL;	
		}
	}
	public void populateSingleFFMatchId(PrintWriter print_writer,String viz_sence_path,List<Fixture> fix,BadmintonMatch Bad_match, String selectedbroadcaster) throws InterruptedException 
	{
		if (Bad_match == null) {
			this.status = "ERROR: Match is null";
		} else {
			int Set1h = 0,Set1a=0, Set2h = 0,Set2a=0;

			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHeader " + Bad_match.getTournamentName().toUpperCase() +";");

			for(Fixture fx : fix) {
				if((Bad_match.getMatch().getHomeTeam().getTeamId() == fx.getHometeam()) && (Bad_match.getMatch().getAwayTeam().getTeamId() == fx.getAwayteam())) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader1 " + "MATCH " + Bad_match.getMatch().getMatchnumber() +";");
				}
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
			
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgHomeTeamLogo " + logo_path +  Bad_match.getMatch().getHomeTeam().getFirstname() + "_" + Bad_match.getMatch().getHomeTeam().getLastname() + BadmintonUtil.PNG_EXTENSION  + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgAwayTeamLogo " + logo_path +  Bad_match.getMatch().getAwayTeam().getFirstname() + "_" + Bad_match.getMatch().getAwayTeam().getLastname() + BadmintonUtil.PNG_EXTENSION  + ";");
			
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgHomePlayerImage " + left_path + Bad_match.getMatch().getHomeTeam().getFirstname() + BadmintonUtil.DOUBLE_BACKSLASH +
					Bad_match.getMatch().getHomePlayers().get(0).getTicker_name() + BadmintonUtil.PNG_EXTENSION + ";");
			
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgAwayPlayerImage " + right_path + Bad_match.getMatch().getAwayTeam().getFirstname() + BadmintonUtil.DOUBLE_BACKSLASH +
					Bad_match.getMatch().getAwayPlayers().get(0).getTicker_name() + BadmintonUtil.PNG_EXTENSION + ";");
			
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
		print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT_PATH C:/Temp/Preview.bmp;");
		print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT 1920 1080;");
		TimeUnit.SECONDS.sleep(1);
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 0.0;");
		print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW OFF;");	
		this.status = BadmintonUtil.SUCCESSFUL;	
		}
	}
	public void populateDoubleFFMatchId(PrintWriter print_writer,String viz_sence_path,List<Fixture> fix,BadmintonMatch Bad_match, String selectedbroadcaster) throws InterruptedException 
	{
		if (Bad_match == null) {
			this.status = "ERROR: Match is null";
		} else {
			int Set1h = 0,Set1a=0, Set2h = 0,Set2a=0;
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHeader " + Bad_match.getTournamentName().toUpperCase() +";");
			//print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader1 " + "MATCH " + Bad_match.getMatch().getMatchId() +";");
			//print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader2 " + "MATCH " + Bad_match.getMatch().getMatchId() +";");
			for(Fixture fx : fix) {
				if((Bad_match.getMatch().getHomeTeam().getTeamId() == fx.getHometeam()) && (Bad_match.getMatch().getAwayTeam().getTeamId() == fx.getAwayteam())) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader1 " + "MATCH " + Bad_match.getMatch().getMatchnumber() +";");
				}
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
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgHomePlayerImage1 " + left_path + Bad_match.getMatch().getHomeTeam().getFirstname() + BadmintonUtil.DOUBLE_BACKSLASH +
					Bad_match.getMatch().getHomePlayers().get(0).getTicker_name() + BadmintonUtil.PNG_EXTENSION + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgHomePlayerImage2 " + left_path + Bad_match.getMatch().getHomeTeam().getFirstname() + BadmintonUtil.DOUBLE_BACKSLASH +
					Bad_match.getMatch().getHomePlayers().get(1).getTicker_name() + BadmintonUtil.PNG_EXTENSION + ";");
			
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgHomeTeamLogo " + logo_path +  Bad_match.getMatch().getHomeTeam().getFirstname() + "_" + Bad_match.getMatch().getHomeTeam().getLastname() + BadmintonUtil.PNG_EXTENSION  + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgAwayTeamLogo " + logo_path +  Bad_match.getMatch().getAwayTeam().getFirstname() + "_" + Bad_match.getMatch().getAwayTeam().getLastname() + BadmintonUtil.PNG_EXTENSION  + ";");
			
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgAwayPlayerImage1 " + right_path + Bad_match.getMatch().getAwayTeam().getFirstname() + BadmintonUtil.DOUBLE_BACKSLASH +
					Bad_match.getMatch().getAwayPlayers().get(0).getTicker_name() + BadmintonUtil.PNG_EXTENSION + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgAwayPlayerImage2 " + right_path + Bad_match.getMatch().getAwayTeam().getFirstname() + BadmintonUtil.DOUBLE_BACKSLASH +
					Bad_match.getMatch().getAwayPlayers().get(1).getTicker_name() + BadmintonUtil.PNG_EXTENSION + ";");
			
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
		print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT_PATH C:/Temp/Preview.bmp;");
		print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT 1920 1080;");
		TimeUnit.SECONDS.sleep(1);
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 0.0;");
		print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW OFF;");	
		this.status = BadmintonUtil.SUCCESSFUL;	
		}
	}
	public void populateLtTieId(PrintWriter print_writer,String viz_sence_path,List<Fixture> fix,BadmintonMatch Bad_match, String selectedbroadcaster) throws InterruptedException 
	{
		if (Bad_match == null) {
			this.status = "ERROR: Match is null";
		} else {
			
			for(Fixture fx : fix) {
				if((Bad_match.getMatch().getHomeTeam().getTeamId() == fx.getHometeam()) && (Bad_match.getMatch().getAwayTeam().getTeamId() == fx.getAwayteam())) {
					if(fx.getMargin() == null) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScore_VS " + "VS" +";");
					}
					else {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScore_VS " + fx.getMargin() +";");
					}			
				}
				
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHeader " + Bad_match.getMatch().getGroupname().toUpperCase() + " - " + 
							Bad_match.getMatch().getMatchIdent() +";");
				
				//if((Bad_match.getMatch().getHomeTeam().getTeamId() == fx.getHometeam()) && (Bad_match.getMatch().getAwayTeam().getTeamId() == fx.getAwayteam())) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader " + " " +";");
					
				//}
				if(Bad_match.getSets() != null ) {
					for(Set st : Bad_match.getSets()) {
						if(st.getSetNumber() == 3 && st.getStatus().toUpperCase().equalsIgnoreCase("END"))  {
							if((Bad_match.getMatch().getHomeTeam().getTeamId() == fx.getHometeam()) && (Bad_match.getMatch().getAwayTeam().getTeamId() == fx.getAwayteam())) {
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScore_VS " + fx.getMargin() +";");
							}
							
						}
					}
				}
			}
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgHomePlayer1Image " + logo_path + Bad_match.getMatch().getHomeTeam().getFirstname() + "_" + Bad_match.getMatch().getHomeTeam().getLastname() + BadmintonUtil.PNG_EXTENSION + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHomeTeamName " + Bad_match.getMatch().getHomeTeam().getFullname().toUpperCase() + ";");
			
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgAwayPlayer1Image " + logo_path + Bad_match.getMatch().getAwayTeam().getFirstname() + "_" + Bad_match.getMatch().getAwayTeam().getLastname() + BadmintonUtil.PNG_EXTENSION + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tAwayTeamName " + Bad_match.getMatch().getAwayTeam().getFullname().toUpperCase() + ";");			
			
			print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW ON;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In STOP;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out STOP;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 75.0;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
			print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT_PATH C:/Temp/Preview.bmp;");
			print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT 1920 1080;");
			TimeUnit.SECONDS.sleep(1);
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 0.0;");
			print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW OFF;");				
			this.status = BadmintonUtil.SUCCESSFUL;	
		}
	}
	public void populateFFTieId(PrintWriter print_writer,String viz_sence_path,List<Fixture> fix,BadmintonMatch Bad_match, String selectedbroadcaster) throws InterruptedException 
	{
		if (Bad_match == null) {
			this.status = "ERROR: Match is null";
		} else {
			
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHeader " + Bad_match.getTournamentName().toUpperCase() +";");
			
			for(Fixture fx : fix) {
				if((Bad_match.getMatch().getHomeTeam().getTeamId() == fx.getHometeam()) && (Bad_match.getMatch().getAwayTeam().getTeamId() == fx.getAwayteam())) {
					if(fx.getMargin() == null) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*CenterPart*CONTAINER SET ACTIVE 0;");
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader2 " + " " +";");
					}
					else {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*CenterPart*CONTAINER SET ACTIVE 1;");
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader2 " + fx.getMargin() +";");
					}			
				}
				
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader1 " + Bad_match.getMatch().getGroupname().toUpperCase() + " - " + 
						Bad_match.getMatch().getMatchIdent() +";");
					
				
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
			}
			
			//print_writer.println("LAYER1*EVEREST*TREEVIEW*CenterPart*CONTAINER SET ACTIVE 0;");
			
			//print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader2 " + " " +";");
			
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgHomeTeamLogo " + logo_path + Bad_match.getMatch().getHomeTeam().getFirstname() + "_" + Bad_match.getMatch().getHomeTeam().getLastname() + BadmintonUtil.PNG_EXTENSION + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHomeTeamFirstName " + Bad_match.getMatch().getHomeTeam().getFirstname().toUpperCase() + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHomeTeamLastName " + Bad_match.getMatch().getHomeTeam().getLastname().toUpperCase() + ";");
			//print_writer.println("LAYER1*EVEREST*TREEVIEW*TEAM1LastName*CONTAINER SET ACTIVE 0;");
			
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgAwayTeamLogo " + logo_path + Bad_match.getMatch().getAwayTeam().getFirstname() + "_" + Bad_match.getMatch().getAwayTeam().getLastname() + BadmintonUtil.PNG_EXTENSION + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tAwayTeamFirstName " + Bad_match.getMatch().getAwayTeam().getFirstname().toUpperCase() + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tAwayTeamLastName " + Bad_match.getMatch().getAwayTeam().getLastname().toUpperCase() + ";");			
			//print_writer.println("LAYER1*EVEREST*TREEVIEW*TEAM2LastName*CONTAINER SET ACTIVE 0;");
			
			print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW ON;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In STOP;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out STOP;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 98.0;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
			print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT_PATH C:/Temp/Preview.bmp;");
			print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT 1920 1080;");
			TimeUnit.SECONDS.sleep(1);
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 0.0;");
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
			print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT_PATH C:/Temp/Preview.bmp;");
			print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT 1920 1080;");
			TimeUnit.SECONDS.sleep(1);
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 0.0;");
			print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW OFF;");	
			this.status = BadmintonUtil.SUCCESSFUL;	
		}
	}
	public void populateOrderOfPlay(PrintWriter print_writer,String viz_sence_path,int match_number,String Player_name,List<BadmintonMatch> mtch,List<Fixture> fix,BadmintonMatch Bad_match, String selectedbroadcaster) throws InterruptedException 
	{
		if (Bad_match == null) {
			this.status = "ERROR: Match is null";
		} else {
			int Set1h = 0,Set1a = 0, Set2h = 0,Set2a = 0,row_id = 0,super_match_val = 0;
			String container = "";
			for(Fixture fx : fix) {
				if(fx.getMatchnumber() == match_number) {
					if(fx.getMargin() == null) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScore " + " " +";");
					}
					else {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScore " + fx.getMargin() +";");
					}
					
					if(fx.getDate().equalsIgnoreCase(new SimpleDateFormat("dd-MM-yyyy").format(new Date()))){
						if(Bad_match.getMatch().getSuperMatch() == 1 && Bad_match.getSets() != null) {
							if(Bad_match.getSets().get(0).getStatus().equalsIgnoreCase(BadmintonUtil.START)) {
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHeader " + "ORDER OF PLAY" +";");
							}else {
								if(match_number % 2 == 0) {
									print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHeader " + "RESULTS - " + "TIE 2" +";");
								}else {
									print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHeader " + "RESULTS - " + "TIE 1" +";");
								}
							}
						}else {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHeader " + "ORDER OF PLAY" +";");
						}
						
					}else {
						Calendar cal = Calendar.getInstance();
						cal.add(Calendar.DATE, -1);
						if(fx.getDate().equalsIgnoreCase(new SimpleDateFormat("dd-MM-yyyy").format(cal.getTime()))) {
							if(match_number % 2 == 0) {
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHeader " + "RESULTS - TIE 2" +";");
							}else {
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHeader " + "RESULTS - TIE 1" +";");
							}
						}else {
							if(match_number % 2 == 0) {
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHeader " + "RESULTS "  + " - TIE 2" +";");
							}else {
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHeader " + "RESULTS "  + " - TIE 1" +";");
							}
						}
						
					}
				}
			}
			
			for(int i = 0; i <= mtch.size() - 1; i++) {
				row_id = row_id + 1;
				switch(i) {
				case 0 :
					container = "A";
					print_writer.println("LAYER1*EVEREST*TREEVIEW*First*CONTAINER SET ACTIVE 1;");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Second*CONTAINER SET ACTIVE 0;");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Third*CONTAINER SET ACTIVE 0;");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Fourth*CONTAINER SET ACTIVE 0;");
					break;
				case 1 :
					container = "B";
					print_writer.println("LAYER1*EVEREST*TREEVIEW*First*CONTAINER SET ACTIVE 1;");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Second*CONTAINER SET ACTIVE 1;");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Third*CONTAINER SET ACTIVE 0;");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Fourth*CONTAINER SET ACTIVE 0;");
					break;
				case 2 :
					container = "C";
					print_writer.println("LAYER1*EVEREST*TREEVIEW*First*CONTAINER SET ACTIVE 1;");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Second*CONTAINER SET ACTIVE 1;");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Third*CONTAINER SET ACTIVE 1;");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Fourth*CONTAINER SET ACTIVE 0;");
					break;
				case 3 :
					container = "D";
					print_writer.println("LAYER1*EVEREST*TREEVIEW*First*CONTAINER SET ACTIVE 1;");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Second*CONTAINER SET ACTIVE 1;");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Third*CONTAINER SET ACTIVE 1;");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Fourth*CONTAINER SET ACTIVE 1;");
					break;
				case 4 :
					container = "E";
					print_writer.println("LAYER1*EVEREST*TREEVIEW*First*CONTAINER SET ACTIVE 1;");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Second*CONTAINER SET ACTIVE 1;");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Third*CONTAINER SET ACTIVE 1;");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Fourth*CONTAINER SET ACTIVE 1;");
					break;
				}
				
				if(mtch.get(i).getMatch().getSuperMatch() == 1) {
					super_match_val = 1;
					
				}
				else {
					super_match_val = 0;
				}
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vSuperMatch " + super_match_val + ";");
				
				if(mtch.get(i).getMatch().getHomeSecondPlayerId() == 0) {
					if(Player_name.toUpperCase().equalsIgnoreCase(mtch.get(i).getMatch().getHomePlayers().get(0).getTicker_name().toUpperCase())) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayer" + container + "1 " + mtch.get(i).getMatch().getHomePlayers().get(0).
								getTicker_name().toUpperCase() + " (PoT)" +";");
					}else {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayer" + container + "1 " + mtch.get(i).getMatch().getHomePlayers().get(0).
								getTicker_name().toUpperCase() +";");
					}
					
					if(Player_name.toUpperCase().equalsIgnoreCase(mtch.get(i).getMatch().getAwayPlayers().get(0).getTicker_name().toUpperCase())) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayer" + container + "2 " + mtch.get(i).getMatch().getAwayPlayers().get(0).
								getTicker_name().toUpperCase() + " (PoT)" +";");
					}else {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayer" + container + "2 " + mtch.get(i).getMatch().getAwayPlayers().get(0).
								getTicker_name().toUpperCase() +";");
					}
				}
				
				else if(mtch.get(i).getMatch().getHomeSecondPlayerId() != 0 && mtch.get(i).getMatch().getHomeThirdPlayerId() == 0) {
					if(Player_name.toUpperCase().equalsIgnoreCase(mtch.get(i).getMatch().getHomePlayers().get(0).getTicker_name().toUpperCase())) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayer" + container + "1 " + mtch.get(i).getMatch().getHomePlayers().get(0).
								getTicker_name().toUpperCase() + " (PoT)" + " / " + mtch.get(i).getMatch().getHomePlayers().get(1).getTicker_name().toUpperCase() +";");
					}
					else if(Player_name.toUpperCase().equalsIgnoreCase(mtch.get(i).getMatch().getHomePlayers().get(1).getTicker_name().toUpperCase())) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayer" + container + "1 " + mtch.get(i).getMatch().getHomePlayers().get(0).
								getTicker_name().toUpperCase() + " / " + mtch.get(i).getMatch().getHomePlayers().get(1).getTicker_name().toUpperCase() + " (PoT)" +";");
					}
					else {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayer" + container + "1 " + mtch.get(i).getMatch().getHomePlayers().get(0).getTicker_name().
								toUpperCase() + " / " + mtch.get(i).getMatch().getHomePlayers().get(1).getTicker_name().toUpperCase() +";");
					}
					
					if(Player_name.toUpperCase().equalsIgnoreCase(mtch.get(i).getMatch().getAwayPlayers().get(0).getTicker_name().toUpperCase())) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayer" + container + "2 " + mtch.get(i).getMatch().getAwayPlayers().get(0).
								getTicker_name().toUpperCase() + " (PoT)" + " / " + mtch.get(i).getMatch().getAwayPlayers().get(1).getTicker_name().toUpperCase() +";");
					}
					else if(Player_name.toUpperCase().equalsIgnoreCase(mtch.get(i).getMatch().getAwayPlayers().get(1).getTicker_name().toUpperCase())) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayer" + container + "2 " + mtch.get(i).getMatch().getAwayPlayers().get(0).
								getTicker_name().toUpperCase() + " / " + mtch.get(i).getMatch().getAwayPlayers().get(1).getTicker_name().toUpperCase() + " (PoT)" +";");
					}
					else {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayer" + container + "2 " + mtch.get(i).getMatch().getAwayPlayers().get(0).getTicker_name().
								toUpperCase() + " / " + mtch.get(i).getMatch().getAwayPlayers().get(1).getTicker_name().toUpperCase() +";");
					}
					
					
					//print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayer" + container + "1 " + mtch.get(i).getMatch().getHomePlayers().get(0).getTicker_name().toUpperCase() + " / " + 
							//mtch.get(i).getMatch().getHomePlayers().get(1).getTicker_name().toUpperCase() +";");
					//print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayer" + container + "2 " + mtch.get(i).getMatch().getAwayPlayers().get(0).getTicker_name().toUpperCase() + " / " + 
							//mtch.get(i).getMatch().getAwayPlayers().get(1).getTicker_name().toUpperCase() +";");
				}
				else if(mtch.get(i).getMatch().getHomeThirdPlayerId() != 0) {
					
					if(Player_name.toUpperCase().equalsIgnoreCase(mtch.get(i).getMatch().getHomePlayers().get(0).getTicker_name().toUpperCase())) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayer" + container + "1 " + mtch.get(i).getMatch().getHomePlayers().get(0).
								getTicker_name().toUpperCase() + " (PoT)" + " / " + mtch.get(i).getMatch().getHomePlayers().get(1).getTicker_name().toUpperCase() + " / " + 
									mtch.get(i).getMatch().getHomePlayers().get(2).getTicker_name().toUpperCase() +";");
					}
					else if(Player_name.toUpperCase().equalsIgnoreCase(mtch.get(i).getMatch().getHomePlayers().get(1).getTicker_name().toUpperCase())) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayer" + container + "1 " + mtch.get(i).getMatch().getHomePlayers().get(0).
								getTicker_name().toUpperCase() + " / " + mtch.get(i).getMatch().getHomePlayers().get(1).getTicker_name().toUpperCase() + " (PoT)" + " / " + 
								 mtch.get(i).getMatch().getHomePlayers().get(2).getTicker_name().toUpperCase() + ";");
					}
					else if(Player_name.toUpperCase().equalsIgnoreCase(mtch.get(i).getMatch().getHomePlayers().get(2).getTicker_name().toUpperCase())) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayer" + container + "1 " + mtch.get(i).getMatch().getHomePlayers().get(0).
								getTicker_name().toUpperCase() + " / " + mtch.get(i).getMatch().getHomePlayers().get(1).getTicker_name().toUpperCase() + " / " + 
								 mtch.get(i).getMatch().getHomePlayers().get(2).getTicker_name().toUpperCase() + " (PoT)" + ";");
					}else {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayer" + container + "1 " + mtch.get(i).getMatch().getHomePlayers().get(0).
								getTicker_name().toUpperCase() + " / " + mtch.get(i).getMatch().getHomePlayers().get(1).getTicker_name().toUpperCase() + " / " + 
								 mtch.get(i).getMatch().getHomePlayers().get(2).getTicker_name().toUpperCase() + ";");
					}
					
					if(Player_name.toUpperCase().equalsIgnoreCase(mtch.get(i).getMatch().getAwayPlayers().get(0).getTicker_name().toUpperCase())) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayer" + container + "2 " + mtch.get(i).getMatch().getAwayPlayers().get(0).
								getTicker_name().toUpperCase() + " (PoT)" + " / " + mtch.get(i).getMatch().getAwayPlayers().get(1).getTicker_name().toUpperCase() + " / " + 
									mtch.get(i).getMatch().getAwayPlayers().get(2).getTicker_name().toUpperCase() +";");
					}
					else if(Player_name.toUpperCase().equalsIgnoreCase(mtch.get(i).getMatch().getAwayPlayers().get(1).getTicker_name().toUpperCase())) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayer" + container + "2 " + mtch.get(i).getMatch().getAwayPlayers().get(0).
								getTicker_name().toUpperCase() + " / " + mtch.get(i).getMatch().getAwayPlayers().get(1).getTicker_name().toUpperCase() + " (PoT)" + " / " + 
								 mtch.get(i).getMatch().getAwayPlayers().get(2).getTicker_name().toUpperCase() + ";");
					}
					else if(Player_name.toUpperCase().equalsIgnoreCase(mtch.get(i).getMatch().getAwayPlayers().get(2).getTicker_name().toUpperCase())) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayer" + container + "2 " + mtch.get(i).getMatch().getAwayPlayers().get(0).
								getTicker_name().toUpperCase() + " / " + mtch.get(i).getMatch().getAwayPlayers().get(1).getTicker_name().toUpperCase() + " / " + 
								 mtch.get(i).getMatch().getAwayPlayers().get(2).getTicker_name().toUpperCase() + " (PoT)" + ";");
					}else {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayer" + container + "2 " + mtch.get(i).getMatch().getAwayPlayers().get(0).
								getTicker_name().toUpperCase() + " / " + mtch.get(i).getMatch().getAwayPlayers().get(1).getTicker_name().toUpperCase() + " / " + 
								 mtch.get(i).getMatch().getAwayPlayers().get(2).getTicker_name().toUpperCase() + ";");
					}
					
					//print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayer" + container + "1 " + mtch.get(i).getMatch().getHomePlayers().get(0).getTicker_name().toUpperCase() + " / " + 
							//mtch.get(i).getMatch().getHomePlayers().get(1).getTicker_name().toUpperCase() + " / " + mtch.get(i).getMatch().getHomePlayers().get(2).getTicker_name().toUpperCase() +";");
					//print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayer" + container + "2 " + mtch.get(i).getMatch().getAwayPlayers().get(0).getTicker_name().toUpperCase() + " / " + 
							//mtch.get(i).getMatch().getAwayPlayers().get(1).getTicker_name().toUpperCase() + " / " + mtch.get(i).getMatch().getAwayPlayers().get(2).getTicker_name().toUpperCase() +";");
				}
				

			if(mtch.get(i).getSets() == null ) {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScore" + row_id + " " + " " +";");
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET " + row_id + " " + "0" +";");
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tVs" + row_id + " " + "VS" +";");
				
			}
			else {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET " + row_id + " " + "1" +";");
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tVs" + row_id + " " + "VS" +";");
				for(Set st : mtch.get(i).getSets()) {
					if(st.getSetNumber() == 1) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScore" + row_id + " " + st.getHomeTeamTotalScore() + "-" + st.getAwayTeamTotalScore() +";");
						Set1h = st.getHomeTeamTotalScore(); 
						Set1a = st.getAwayTeamTotalScore();
					}
					if(st.getSetNumber() == 2) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScore" + row_id + " " + Set1h + "-" + Set1a + " | " + st.getHomeTeamTotalScore() + "-" + st.getAwayTeamTotalScore() +";");
						Set2h = st.getHomeTeamTotalScore();
						Set2a = st.getAwayTeamTotalScore();
					}
					if(st.getSetNumber() == 3) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScore" + row_id + " " + Set1h + "-" + Set1a + " | " + Set2h + "-" + Set2a + " | " + st.getHomeTeamTotalScore() + "-" + st.getAwayTeamTotalScore() +";");	
					}
					if(st.getSetNumber() == 3 && st.getStatus().equalsIgnoreCase("END") )  {
						if(mtch.get(i).getHomeTeamSetsWon() > mtch.get(i).getAwayTeamSetsWon()) {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tVs" + row_id + " " + "BEAT" +";");
						}
						else {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tVs" + row_id + " " + "LOST TO" +";");
						}
					}
					if(st.getSetNumber() == 2 && st.getStatus().equalsIgnoreCase("END")) {
						
						if(mtch.get(i).getHomeTeamSetsWon() > mtch.get(i).getAwayTeamSetsWon()) {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tVs" + row_id + " " + "BEAT" +";");
						}
						else if(mtch.get(i).getHomeTeamSetsWon() < mtch.get(i).getAwayTeamSetsWon()) {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tVs" + row_id + " " + "LOST TO" +";");
						}
					}
					if(mtch.get(i).getMatch().getSuperMatch() == 1) {
						if(st.getSetNumber() == 1 && st.getStatus().equalsIgnoreCase("END")) {
							
							if(mtch.get(i).getHomeTeamSetsWon() > mtch.get(i).getAwayTeamSetsWon()) {
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tVs" + row_id + " " + "BEAT" +";");
							}
							else if(mtch.get(i).getHomeTeamSetsWon() < mtch.get(i).getAwayTeamSetsWon()) {
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tVs" + row_id + " " + "LOST TO" +";");
							}
						}
					}
				}
			}
			if(mtch.get(i).getMatch().getTrumpHomeMatch() == 1) {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*TrumpGrp*CONTAINER SET ACTIVE 1;");
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vTrump" + container + "1 " + "2" + ";");
				processAnimation(print_writer, "TrumpLoop", "START", selectedbroadcaster);
				
			}
			else {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vTrump" + container + "1 " + "1" + ";");
			}
			if(mtch.get(i).getMatch().getTrumpAwayMatch() == 1) {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*TrumpGrp*CONTAINER SET ACTIVE 1;");
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vTrump" + container + "2 " + "2" + ";");
				processAnimation(print_writer, "TrumpLoop", "START", selectedbroadcaster);
				
			}
			else {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vTrump" + container + "2 " + "1" + ";");
			}
			if(mtch.get(i).getMatch().getCategoryId() == null) {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tMatchName" + row_id + " " + "SM" +";");
			}
			else {
				if(mtch.get(i).getMatch().getCategoryId() == 1) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tMatchName" + row_id + " " + " WS " +";");
				}
				else if(mtch.get(i).getMatch().getCategoryId() == 2) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tMatchName" + row_id + " " + " MD " +";");
				}
				else if(mtch.get(i).getMatch().getCategoryId() == 3) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tMatchName" + row_id + " " + " MS " +";");
				}
				else if(mtch.get(i).getMatch().getCategoryId() == 4) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tMatchName" + row_id + " " + " XD " +";");
				}
			}
		}
			for(Fixture fx : fix) {
				if(fx.getMatchnumber() == match_number) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHomeTeamName " + fx.getHome_Team().getFullname().toUpperCase() +";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tAwayTeamName " + fx.getAway_Team().getFullname().toUpperCase() +";");
				}
			}
			print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW ON;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In STOP;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out STOP;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 109.0;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
			print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT_PATH C:/Temp/Preview.bmp;");
			print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT 1920 1080;");
			TimeUnit.SECONDS.sleep(1);
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 0.0;");
			print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW OFF;");	
			this.status = BadmintonUtil.SUCCESSFUL;	
		}
	}
	public void populateTeamsLogo(List<Team> teams, PrintWriter print_writer,String viz_sence_path,BadmintonMatch Bad_match, String selectedbroadcaster) throws InterruptedException 
	{
		int left_row_id =0,right_row_id = 0;
		print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHeader " + Bad_match.getTournamentName().toUpperCase() +";");
		print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader1 " + "TEAMS" + ";");
		print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader2 " + " " + ";");
		print_writer.println("LAYER1*EVEREST*TREEVIEW*Underline*CONTAINER SET ACTIVE 0;");

		for(Team team : teams) {
			if(team.getGroupname().equalsIgnoreCase("GROUP A")) {
				left_row_id = left_row_id + 1;
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgGroupALogo" + left_row_id + " " + logo_path + team.getFirstname() + "_" + team.getLastname() + ".png" +";");
			}
			else if(team.getGroupname().equalsIgnoreCase("GROUP B")) {
				right_row_id = right_row_id + 1;
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgGroupBLogo" + right_row_id + " " + logo_path + team.getFirstname() + "_" + team.getLastname() + ".png" +";");
			}
		}
		
		print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW ON;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In STOP;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out STOP;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 98.0;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
		print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT_PATH C:/Temp/Preview.bmp;");
		print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT 1920 1080;");
		TimeUnit.SECONDS.sleep(1);
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 0.0;");
		print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW OFF;");
		this.status = BadmintonUtil.SUCCESSFUL;	
		
	}
	public void populatePlayerProfile(PrintWriter print_writer,String viz_sence_path,int player_id, List<Player> Player,List<Team> Team,BadmintonMatch Bad_match, String selectedbroadcaster) throws InterruptedException 
	{
		if (Bad_match == null) {
			this.status = "ERROR: Match is null";
		} else {
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHeader " + Bad_match.getTournamentName().toUpperCase() +";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader1 " + " " +";");
			for(Player pp : Player) {
				if(pp.getPlayerId() == player_id) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tStatValue1 " + pp.getAge() +";");
					for(Team tm : Team) {
						if(pp.getTeamId() == tm.getTeamId()) {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tStatValue2 " + tm.getFullname().toUpperCase() +";");
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgHomeTeamLogo " + logo_path + tm.getFirstname() + "_" + tm.getLastname() + BadmintonUtil.PNG_EXTENSION + ";");
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgHomePlayerImage " + left_path + tm.getFirstname() + BadmintonUtil.DOUBLE_BACKSLASH +
									pp.getTicker_name() + BadmintonUtil.PNG_EXTENSION + ";");
						}
					}
					
					if(pp.getIconPlayer().equalsIgnoreCase("YES")) {
						if(pp.getBwfRanking() == null && pp.getBaiRanking() == null) {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tStatHead3A " + " " +";");
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tStatHead3B " + " " +";");
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tStatValue3 " + " " +";");
						}
						else if(pp.getBwfRanking() == null) {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tStatHead3A " + "BAI" +";");
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tStatValue3 " + pp.getBaiRanking() +";");
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
					
					if(pp.getText1() == null && pp.getText2() == null) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Circle1*CONTAINER SET ACTIVE 0;");
						print_writer.println("LAYER1*EVEREST*TREEVIEW*RunningLine1*CONTAINER SET ACTIVE 0;");
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Circle2*CONTAINER SET ACTIVE 0;");
						print_writer.println("LAYER1*EVEREST*TREEVIEW*RunningLine2*CONTAINER SET ACTIVE 0;");
					}else if(pp.getText2() == null) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Circle2*CONTAINER SET ACTIVE 0;");
						print_writer.println("LAYER1*EVEREST*TREEVIEW*RunningLine2*CONTAINER SET ACTIVE 0;");
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tRunningText1 " + pp.getText1()  +";");
					}else if(pp.getText1() == null) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Circle1*CONTAINER SET ACTIVE 0;");
						print_writer.println("LAYER1*EVEREST*TREEVIEW*RunningLine1*CONTAINER SET ACTIVE 0;");
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tRunningText2 " + pp.getText2() +";");
						
					}else {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tRunningText1 " + pp.getText1()  +";");
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tRunningText2 " + pp.getText2() +";");
					}
					
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerName " + pp.getFull_name().toUpperCase() + ";");
				}
			}
			
			
			
			
			print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW ON;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In STOP;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out STOP;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 127.0;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
			print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT_PATH C:/Temp/Preview.bmp;");
			print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT 1920 1080;");
			TimeUnit.SECONDS.sleep((long) 0.5);
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 0.0;");
			print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW OFF;");	
			this.status = BadmintonUtil.SUCCESSFUL;	
		}
	}
	public void populateL3TiePromo(PrintWriter print_writer,String viz_sence_path, int match_number ,List<Team> team,List<Fixture> fix,BadmintonMatch Bad_match, String selectedbroadcaster) throws InterruptedException 
	{
		if (Bad_match == null) {
			this.status = "ERROR: Match is null";
		} else {
			
			for(Fixture fx : fix) {
				if(fx.getMatchnumber() == match_number) {
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.DATE, +1);
					if(fx.getDate().equalsIgnoreCase(new SimpleDateFormat("dd-MM-yyyy").format(cal.getTime()))) {
						if(match_number % 2 == 0) {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHeader "  + fx.getGroupname() +" - TIE 2" +";");
						}else {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHeader "  + fx.getGroupname() +" - TIE 1" +";");
						}
					}else {
						if(match_number % 2 == 0) {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHeader "  + fx.getGroupname() +" - TIE 2" +";");
						}else {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHeader "  + fx.getGroupname() +" - TIE 1" +";");
						}
					}
					
					for(Team tm : team) {
						if(tm.getTeamId() == fx.getHometeam()) {
							
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgHomePlayer1Image " + logo_path + tm.getFirstname() + "_" + tm.getLastname() + BadmintonUtil.PNG_EXTENSION + ";");
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHomeTeamName " + tm.getFullname().toUpperCase() + ";");
						}
						if(tm.getTeamId() == fx.getAwayteam()) {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgAwayPlayer1Image " + logo_path + tm.getFirstname() + "_" + tm.getLastname() + BadmintonUtil.PNG_EXTENSION + ";");
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tAwayTeamName " + tm.getFullname().toUpperCase() + ";");
						}
					}
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader " + " " +";");
				}
			}
			
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScore_VS " + "UP NEXT" +";");
		
			print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW ON;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In STOP;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out STOP;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 75.0;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
			print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT_PATH C:/Temp/Preview.bmp;");
			print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT 1920 1080;");
			TimeUnit.SECONDS.sleep(1);
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 0.0;");
			print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW OFF;");
			this.status = BadmintonUtil.SUCCESSFUL;	
		}
	}
	public void populateFFTiePromo(PrintWriter print_writer,String viz_sence_path, int match_number ,List<Team> team,List<Fixture> fix,BadmintonMatch Bad_match, String selectedbroadcaster) throws InterruptedException 
	{
		if (Bad_match == null) {
			this.status = "ERROR: Match is null";
		} else {
			
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHeader " + Bad_match.getTournamentName().toUpperCase() +";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader2 " + "UP NEXT" +";");
			for(Fixture fx : fix) {
				if(fx.getMatchnumber() == match_number) {
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.DATE, +1);
					if(fx.getDate().equalsIgnoreCase(new SimpleDateFormat("dd-MM-yyyy").format(cal.getTime()))) {
						if(match_number % 2 == 0) {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader1 "  + fx.getGroupname() +" - TIE 2" +";");
						}else {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader1 "  + fx.getGroupname() +" - TIE 1" +";");
						}
						
					}else {
						if(match_number % 2 == 0) {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader1 "  + fx.getGroupname() +" - TIE 2" +";");
						}else {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader1 "  + fx.getGroupname() +" - TIE 1" +";");
						}
					}
					for(Team tm : team) {
						if(tm.getTeamId() == fx.getHometeam()) {
							
							//print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader1 " + fx.getGroupname().toUpperCase() +";");
							
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgHomeTeamLogo " + logo_path + tm.getFirstname() + "_" + tm.getLastname() + BadmintonUtil.PNG_EXTENSION + ";");
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHomeTeamFirstName " + tm.getFirstname().toUpperCase() + ";");
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHomeTeamLastName " + tm.getLastname().toUpperCase() + ";");
						}
						if(tm.getTeamId() == fx.getAwayteam()) {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgAwayTeamLogo " + logo_path + tm.getFirstname() + "_" + tm.getLastname() + BadmintonUtil.PNG_EXTENSION + ";");
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tAwayTeamFirstName " + tm.getFirstname().toUpperCase() + ";");
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tAwayTeamLastName " + tm.getLastname().toUpperCase() + ";");	
						}
					}
					//print_writer.println("LAYER1*EVEREST*TREEVIEW*TEAM1LastName*CONTAINER SET ACTIVE 0;");
				}
			}
			//print_writer.println("LAYER1*EVEREST*TREEVIEW*TEAM2LastName*CONTAINER SET ACTIVE 0;");
		
			print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW ON;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In STOP;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out STOP;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 98.0;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
			print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT_PATH C:/Temp/Preview.bmp;");
			print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT 1920 1080;");
			TimeUnit.SECONDS.sleep(1);
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 0.0;");
			print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW OFF;");
			this.status = BadmintonUtil.SUCCESSFUL;	
		}
	}
	public void populateFFSingleMatchPromo(PrintWriter print_writer,String viz_sence_path,int match_id,List<Match> match, List<Player> allplayer, List<Team> allteam,BadmintonMatch Bad_match,String selectedbroadcaster) throws InterruptedException 
	{
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHeader " + Bad_match.getTournamentName().toUpperCase() +";");
			//print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHeader " + "GRAND PRIX BADMINTON LEAGUE 2022" +";");
			
			for(Match single_match : match) {
				if(single_match.getMatchId() == match_id ) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader1 " + "MATCH " + single_match.getMatchnumber() +";");
					
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
											team.getFirstname() + "_" + team.getLastname() + BadmintonUtil.PNG_EXTENSION  + ";");
									print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgHomePlayerImage " + left_path + team.getFirstname() + BadmintonUtil.DOUBLE_BACKSLASH +
											player.getTicker_name() + BadmintonUtil.PNG_EXTENSION + ";");
								}
							}
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHomePlayerName " + player.getFull_name().toUpperCase()+ ";");
							
						}
						if(single_match.getAwayFirstPlayerId() == player.getPlayerId()) {
							for(Team team : allteam) {
								if(player.getTeamId() == team.getTeamId()) {
									print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgAwayTeamLogo " + logo_path +  
											team.getFirstname() + "_" + team.getLastname() + BadmintonUtil.PNG_EXTENSION  + ";");
									print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgAwayPlayerImage " + right_path + team.getFirstname() + BadmintonUtil.DOUBLE_BACKSLASH +
											player.getTicker_name() + BadmintonUtil.PNG_EXTENSION + ";");
								}
							}
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
				}
			}
			
			
			
			print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW ON;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In STOP;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out STOP;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 123.0;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
			print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT_PATH C:/Temp/Preview.bmp;");
			print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT 1920 1080;");
			TimeUnit.SECONDS.sleep(1);
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 0.0;");
			print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW OFF;");	
			this.status = BadmintonUtil.SUCCESSFUL;	

	}
	public void populateLTSingleMatchPromo(PrintWriter print_writer,String viz_sence_path,int match_id,List<Match> match, List<Player> allplayer, List<Team> allteam,String selectedbroadcaster) throws InterruptedException 
	{
		for(Match single_match : match) {
			if(single_match.getMatchId() == match_id) {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHeader " + " MATCH " + single_match.getMatchnumber() +";");
				
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
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgHomePlayer1Image " + left_path + team.getFirstname() + BadmintonUtil.DOUBLE_BACKSLASH +
										player.getTicker_name() + BadmintonUtil.PNG_EXTENSION + ";");
							}
						}
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
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgAwayPlayer1Image " + right_path + team.getFirstname() + BadmintonUtil.DOUBLE_BACKSLASH +
										player.getTicker_name() + BadmintonUtil.PNG_EXTENSION + ";");
							}
						}
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tAwayPlayerName " + player.getFull_name().toUpperCase() + ";");
					}
				}
			}
		}
		print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW ON;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In STOP;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out STOP;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 106.0;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
		print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT_PATH C:/Temp/Preview.bmp;");
		print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT 1920 1080;");
		TimeUnit.SECONDS.sleep(1);
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 0.0;");
		print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW OFF;");	
		this.status = BadmintonUtil.SUCCESSFUL;	

	}	
	public void populateLTDoubleMatchPromo(PrintWriter print_writer,String viz_sence_path,int match_id,List<Match> match, List<Player> allplayer, List<Team> allteam,String selectedbroadcaster) throws InterruptedException 
	{	
		for(Match double_match : match) {
			if(double_match.getMatchId() == match_id) {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHeader " + "MATCH " + double_match.getMatchnumber() +";");
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScore_VS " + "UP NEXT" +";");
				
				//Match Detail
				
				if(double_match.getCategoryId() == 2) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader " + "MEN'S DOUBLES" +";");
				}
				
				if(double_match.getCategoryId() == 4) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader " + "MIXED DOUBLES" +";");
				}
				
				//Trump Detail
				
				if(double_match.getTrumpHomeMatch() == 1) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vHomeTrump_NOTrump " + "1" + ";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHomeTeamName " + allteam.get(allplayer.get(double_match.getHomeFirstPlayerId() - 1).
							getTeamId() - 1).getFullname().toUpperCase()  + ";");
				}else {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vHomeTrump_NOTrump " + "0" + ";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHomeTeamName " + allteam.get(allplayer.get(double_match.getHomeFirstPlayerId() - 1).
							getTeamId() - 1).getFullname().toUpperCase()  + ";");
				}
				
				if(double_match.getTrumpAwayMatch() == 1) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vAwayTrump_NOTrump " + "1" + ";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tAwayTeamName " + allteam.get(allplayer.get(double_match.getAwayFirstPlayerId() - 1).
							getTeamId() - 1).getFullname().toUpperCase() + ";");
				}else {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vAwayTrump_NOTrump " + "0" + ";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tAwayTeamName " + allteam.get(allplayer.get(double_match.getAwayFirstPlayerId() - 1).
							getTeamId() - 1).getFullname().toUpperCase() + ";");
				}
				
				//Team and Player Detail
				
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgHomePlayer1Image " + left_path + allteam.get(allplayer.get(double_match.getHomeFirstPlayerId() - 1).
					getTeamId() - 1).getFirstname() + BadmintonUtil.DOUBLE_BACKSLASH + allplayer.get(double_match.getHomeFirstPlayerId() - 1).getTicker_name() + BadmintonUtil.PNG_EXTENSION + ";");
				
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgHomePlayer2Image " + left_path + allteam.get(allplayer.get(double_match.getHomeSecondPlayerId() - 1).
					getTeamId() - 1).getFirstname() + BadmintonUtil.DOUBLE_BACKSLASH + allplayer.get(double_match.getHomeSecondPlayerId() - 1).getTicker_name() + BadmintonUtil.PNG_EXTENSION + ";");
				
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHomePlayerName " + allplayer.get(double_match.getHomeFirstPlayerId() - 1).getTicker_name().toUpperCase() 
					+ " / " + allplayer.get(double_match.getHomeSecondPlayerId() - 1).getTicker_name().toUpperCase() + ";");
				
				
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgAwayPlayer2Image " + right_path + allteam.get(allplayer.get(double_match.getAwayFirstPlayerId() - 1).
					getTeamId() - 1).getFirstname() + BadmintonUtil.DOUBLE_BACKSLASH + allplayer.get(double_match.getAwayFirstPlayerId() - 1).getTicker_name() + BadmintonUtil.PNG_EXTENSION + ";");
				
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgAwayPlayer1Image " + right_path + allteam.get(allplayer.get(double_match.getAwayFirstPlayerId() - 1).
					getTeamId() - 1).getFirstname() + BadmintonUtil.DOUBLE_BACKSLASH + allplayer.get(double_match.getAwaySecondPlayerId() - 1).getTicker_name() + BadmintonUtil.PNG_EXTENSION + ";");
				
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tAwayPlayerName " + allplayer.get(double_match.getAwayFirstPlayerId() - 1).getTicker_name().toUpperCase() 
					+ " / " + allplayer.get(double_match.getAwaySecondPlayerId() - 1).getTicker_name().toUpperCase() + ";");
				}
			}
			
			print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW ON;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In STOP;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out STOP;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 75.0;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
			print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT_PATH C:/Temp/Preview.bmp;");
			print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT 1920 1080;");
			TimeUnit.SECONDS.sleep(1);
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 0.0;");
			print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW OFF;");	
			this.status = BadmintonUtil.SUCCESSFUL;	

	}
	public void populateFFDoubleMatchPromo(PrintWriter print_writer,String viz_sence_path,int match_id,List<Match> match, List<Player> allplayer, List<Team> allteam,BadmintonMatch Bad_match, String selectedbroadcaster) throws InterruptedException 
	{
			//print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHeader " + "GRAND PRIX BADMINTON LEAGUE 2022 " +";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHeader " + Bad_match.getTournamentName().toUpperCase() +";");
			for(Match double_match : match) {
				if(double_match.getMatchId() == match_id ) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader1 " + " MATCH " + double_match.getMatchnumber() +";");
					
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
										print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgHomeTeamLogo " + logo_path +  team.getFirstname() + "_" + team.getLastname() + BadmintonUtil.PNG_EXTENSION  + ";");
										print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgHomePlayerImage1 " + left_path + team.getFirstname() + BadmintonUtil.DOUBLE_BACKSLASH +
												player.getTicker_name() + BadmintonUtil.PNG_EXTENSION + ";");
									}
								}
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHomePlayer1Name " + player.getFull_name().toUpperCase() + ";");
							}
								
							if(double_match.getHomeSecondPlayerId() == player.getPlayerId()) {
								for(Team team : allteam) {
									if(player.getTeamId() == team.getTeamId()) {
										print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgHomePlayerImage2 " + left_path + team.getFirstname() + BadmintonUtil.DOUBLE_BACKSLASH +
												player.getTicker_name() + BadmintonUtil.PNG_EXTENSION + ";");
									}
								}
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHomePlayer2Name " + player.getFull_name().toUpperCase() + ";");
							}
								
								
								
							if(double_match.getAwayFirstPlayerId() == player.getPlayerId()) {
								for(Team team : allteam) {
									if(player.getTeamId() == team.getTeamId()) {
										print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgAwayTeamLogo " + logo_path +  team.getFirstname() + "_" + team.getLastname() + BadmintonUtil.PNG_EXTENSION  + ";");
										print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgAwayPlayerImage1 " + right_path + team.getFirstname() + BadmintonUtil.DOUBLE_BACKSLASH +
												player.getTicker_name() + BadmintonUtil.PNG_EXTENSION + ";");
									}
								}
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tAwayPlayer1Name " + player.getFull_name().toUpperCase() +  ";");
							}
								
							if(double_match.getAwaySecondPlayerId() == player.getPlayerId()) {
								for(Team team : allteam) {
									if(player.getTeamId() == team.getTeamId()) {
										print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgAwayPlayerImage2 " + right_path + team.getFirstname() + BadmintonUtil.DOUBLE_BACKSLASH +
												player.getTicker_name() + BadmintonUtil.PNG_EXTENSION + ";");
									}
								}
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
				}
			}
				
			
			
			print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW ON;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In STOP;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out STOP;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 82.0;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
			print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT_PATH C:/Temp/Preview.bmp;");
			print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT 1920 1080;");
			TimeUnit.SECONDS.sleep(1);
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 0.0;");
			print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW OFF;");	
			this.status = BadmintonUtil.SUCCESSFUL;	
	}
	public void populateSquads(PrintWriter print_writer,String viz_sence_path,int team_id, List<Player> allplayer, List<Team> allteam,BadmintonMatch Bad_match,String selectedbroadcaster) throws InterruptedException 
	{
		int row_id=1;
		
		//print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHeader " + "GRAND PRIX BADMINTON LEAGUE 2022 " +";");
		print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHeader " + Bad_match.getTournamentName().toUpperCase() +";");
		
		for (Team selected_team : allteam) {
			if(selected_team.getTeamId() == team_id) {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader1 " + "SQUAD " + slashOrDash + " " + 
						selected_team.getFullname().toUpperCase() + ";");
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgTeamLogo " + logo_path +  selected_team.getFirstname() + "_" + selected_team.getLastname() + BadmintonUtil.PNG_EXTENSION  + ";");
				
				if(selected_team.getMentorName() == null) {
					for(Player player : allplayer) {
						if(selected_team.getTeamId() == player.getTeamId()) {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vHidePlayer8 " + "0" +";");
							if(player.getIconPlayer().toUpperCase().equalsIgnoreCase("YES")) {
								//row_id = row_id + 1;
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgMentroImage " + right_path + selected_team.getFirstname() + BadmintonUtil.DOUBLE_BACKSLASH +
										player.getTicker_name() + BadmintonUtil.PNG_EXTENSION + ";");
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tMentorHead " + "ICON" +";");
								
								//if(captain == 0) {
									print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tMentorName " + player.getFull_name().toUpperCase() +";");
								/*}else {
									if(player.getCaptain().toUpperCase().equalsIgnoreCase("YES")) {
										print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tMentorName " + player.getFull_name().toUpperCase() + " (C)" + ";");
									}else {
										print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tMentorName " + player.getFull_name().toUpperCase() +";");
									}
								}*/
								
							}else {
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgPlayerImage" + row_id + " " + left_path + selected_team.getFirstname() + BadmintonUtil.DOUBLE_BACKSLASH +
										player.getTicker_name() + BadmintonUtil.PNG_EXTENSION + ";");
								
								//if(captain == 0) {
									print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerName" + row_id + " " + player.getFull_name().toUpperCase() +";");
								/*}else {
									if(player.getCaptain().toUpperCase().equalsIgnoreCase("YES")) {
										print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerName" + row_id + " " + player.getFull_name().toUpperCase() + " (C)" + ";");
									}else {
										print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerName" + row_id + " " + player.getFull_name().toUpperCase() +";");
									}
								}*/
								
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerAge" + row_id + " " + "Age: " +  player.getAge() +";");

								row_id = row_id + 1;
							}
						}
					}
				}else {
					for(Player player : allplayer) {
						if(selected_team.getTeamId() == player.getTeamId()) {
							if(player.getIconPlayer().toUpperCase().equalsIgnoreCase("YES")) {
								//row_id = row_id + 1;
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vKeyPlayer " + "1" +";");
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgPlayerImage1 " + left_path + selected_team.getFirstname() + BadmintonUtil.DOUBLE_BACKSLASH +
										player.getTicker_name() + BadmintonUtil.PNG_EXTENSION + ";");
								
								//if(captain == 0) {
									print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerName1 " + player.getFull_name().toUpperCase() +";");
								/*}else {
									if(player.getCaptain().toUpperCase().equalsIgnoreCase("YES")) {
										print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerName1 " + player.getFull_name().toUpperCase() + " (C)" + ";");
									}else {
										print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerName1 " + player.getFull_name().toUpperCase() +";");
									}
								}*/
								
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerAge1 " + "Age: " + player.getAge() +";");
							}else {
								row_id = row_id + 1;
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgPlayerImage" + row_id + " " + left_path + selected_team.getFirstname() + BadmintonUtil.DOUBLE_BACKSLASH +
										player.getTicker_name() + BadmintonUtil.PNG_EXTENSION + ";");
								
								//if(captain == 0) {
									print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerName" + row_id + " " + player.getFull_name().toUpperCase() +";");
								/*}else {
									if(player.getCaptain().toUpperCase().equalsIgnoreCase("YES")) {
										print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerName" + row_id + " " + player.getFull_name().toUpperCase() + " (C)" + ";");
									}else {
										print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerName" + row_id + " " + player.getFull_name().toUpperCase() +";");
									}
								}*/
								print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerAge" + row_id + " " + "Age: " + player.getAge() +";");
							}
						}
					}
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgMentroImage " + right_path + selected_team.getFirstname() + BadmintonUtil.DOUBLE_BACKSLASH +
							selected_team.getMentorName().toUpperCase() + BadmintonUtil.PNG_EXTENSION + ";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tMentorName " + selected_team.getMentorName().toUpperCase() +";");
				}
			}
		}
		
		
		print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW ON;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In STOP;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out STOP;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 83.0;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
		print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT_PATH C:/Temp/Preview.bmp;");
		print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT 1920 1080;");
		TimeUnit.SECONDS.sleep(1);
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 0.0;");
		print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW OFF;");
		this.status = BadmintonUtil.SUCCESSFUL;	

	}
	public void populatePointsTable(PrintWriter print_writer,String viz_sence_path,List<LeagueTeam> point_table, String selectedbroadcaster) throws InterruptedException 
	{
		int row_id=0;
		print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHeader " + "POINTS TABLE " +";");
		for(int i = 0; i <= point_table.size()-1; i++) {
			row_id = row_id + 1;
			if(point_table.get(i).getGroupName().equalsIgnoreCase("Group A")) {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vGroup0" + row_id + " " + "0" +";");
			}else {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vGroup0" + row_id + " " + "1" +";");
			}
			

			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tStatValue0" + row_id + "A " + point_table.get(i).getTeamName().toUpperCase() + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tStatValue0" + row_id + "B " + point_table.get(i).getPlayed() + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tStatValue0" + row_id + "C " + point_table.get(i).getWon() + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tStatValue0" + row_id + "D " + point_table.get(i).getLost() + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tStatValue0" + row_id + "E " + point_table.get(i).getTrumpMatchWin() + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tStatValue0" + row_id + "F " + point_table.get(i).getTrumpMatchLost() + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tStatValue0" + row_id + "G " + point_table.get(i).getSuperMatchWin() + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tStatValue0" + row_id + "H " + point_table.get(i).getPoints() + ";");

		}
		print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW ON;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In STOP;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out STOP;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 115.0;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
		print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT_PATH C:/Temp/Preview.bmp;");
		print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT 1920 1080;");
		TimeUnit.SECONDS.sleep(1);
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 0.0;");
		print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW OFF;");
		this.status = BadmintonUtil.SUCCESSFUL;	
	}
	public void populateRules(PrintWriter print_writer,String viz_sence_path,List<Rules> rule,BadmintonMatch Bad_match, String selectedbroadcaster) throws InterruptedException 
	{
		//print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHeader " + "GRAND PRIX BADMINTON LEAGUE 2022 " +";");
		print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHeader " + "RULES" +";");
		print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vCoumms " + rule.size() +";");
		for(int i = 0; i <= rule.size()-1; i++) {
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tStatValue0" + rule.get(i).getRuleId()  + "A " + 
					rule.get(i).getText1().toUpperCase() + ";");
			
		}
		print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW ON;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In STOP;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out STOP;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 84.0;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
		print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT_PATH C:/Temp/Preview.bmp;");
		print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT 1920 1080;");
		TimeUnit.SECONDS.sleep(1);
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 0.0;");
		print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW OFF;");
		this.status = BadmintonUtil.SUCCESSFUL;	
	}
	public void populateSchedule(PrintWriter print_writer,String viz_sence_path,String GroupName,List<Fixture> fix,List<Team> team,BadmintonMatch Bad_match, String selectedbroadcaster) throws InterruptedException, ParseException 
	{
		int row_id=0;
		//print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHeader " + "GRAND PRIX BADMINTON LEAGUE 2022 " +";");
		print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHeader " + Bad_match.getTournamentName().toUpperCase() +";");
		print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader " + GroupName.toUpperCase() + " - FIXTURES" +";");
		
		for(int i = 0; i <= fix.size()-1; i++) {
			if(fix.get(i).getGroupname().equalsIgnoreCase(GroupName)) {
				row_id = row_id + 1;
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vCoumms " + row_id +";");
				//System.out.println("Match Date ("+ row_id + ") - " + fix.get(i).getDate().compareTo(new SimpleDateFormat("dd-MM-yyyy").format(new Date())));
				
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tStatValue0" + row_id + "A " + fix.get(i).getHome_Team().getFirstname().toUpperCase()
						+ " VS " + fix.get(i).getAway_Team().getFirstname().toUpperCase() + ";");
				
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tStatValue0" + row_id + "B " + "  " + ";");
				
				if(fix.get(i).getWinner() == null && fix.get(i).getMargin() == null) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tStatValue0" + row_id + "C " + fix.get(i).getDate() + ";");
				} else if(fix.get(i).getWinner() == null) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tStatValue0" + row_id + "C " + "(" + fix.get(i).getMargin() + ")" + ";");
				} else {
					if(fix.get(i).getWinner().equalsIgnoreCase(String.valueOf(team.get(Integer.valueOf(fix.get(i).getWinner())  - 1).getTeamId()))) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tStatValue0" + row_id + "C " + team.get(Integer.valueOf(fix.get(i).getWinner())  - 1).
								getFirstname().toUpperCase() + " WON " + "(" + fix.get(i).getMargin() + ")" + ";");
					}
				}
			}
		}
		print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW ON;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In STOP;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out STOP;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 98.0;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
		print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT_PATH C:/Temp/Preview.bmp;");
		print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT 1920 1080;");
		TimeUnit.SECONDS.sleep(1);
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 0.0;");
		print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW OFF;");
		this.status = BadmintonUtil.SUCCESSFUL;	
	}
	public void populateSuper(PrintWriter print_writer,String viz_sence_path,int super_value,int sponsor,List<NameSuper> namesuper, String selectedbroadcaster) throws InterruptedException 

	{
		for(NameSuper ns : namesuper) {
			if(ns.getNamesuperId() == super_value) {
				if(ns.getSurname() != null) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHeader " + ns.getFirstname().toUpperCase() + " " + 
							ns.getSurname().toUpperCase() + ";");
				}
				else if(ns.getSurname() == null) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHeader " + ns.getFirstname().toUpperCase() + ";");
				}
				
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader " + ns.getSubLine()  + ";");
				//print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vSponsor " + "0" + ";");
				//print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHeader " + "DAY " + Bad_match.getMatch().getMatchId() +";");
				
				if(sponsor == 0) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vSponsor " + "0" + ";");
				}else {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vSponsor " + "1" + ";");
				}
			}
		}
		
		
		print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW ON;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In STOP;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out STOP;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 79.0;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
		print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT_PATH C:/Temp/Preview.bmp;");
		print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT 1920 1080;");
		TimeUnit.SECONDS.sleep(1);
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 0.0;");
		print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW OFF;");
		this.status = BadmintonUtil.SUCCESSFUL;	
	}
	public void populateBugSuper(PrintWriter print_writer,String viz_sence_path,int bug_value,List<NameSuper> namesuper, String selectedbroadcaster) throws InterruptedException 

	{
		
		for(NameSuper ns : namesuper) {
			if(ns.getNamesuperId() == bug_value) {
				if(ns.getSurname() != null) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerName01 " + ns.getFirstname().toUpperCase() + " " + 
									ns.getSurname().toUpperCase() + ";");
				}
				else if(ns.getSurname() == null) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerName01 " + ns.getFirstname().toUpperCase() + ";");
				}
				
				if(ns.getSubLine() == null) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tInfo1A " + " "  + ";");
				}else {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tInfo1A " + ns.getSubLine()  + ";");
				}
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tInfo1B " + " "  + ";");
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tInfo1C " + " "  + ";");
			}
		}
		
		print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW ON;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In STOP;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out STOP;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 32.0;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
		print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT_PATH C:/Temp/Preview.bmp;");
		print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT 1920 1080;");
		TimeUnit.SECONDS.sleep(1);
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 0.0;");
		print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW OFF;");
		this.status = BadmintonUtil.SUCCESSFUL;	
	}
	public void populateNameSuperPlayer(PrintWriter print_writer,String viz_sence_path,List<Player> Player,List<Team> allteam,int player_id,int detail, int sponsor ,String selectedbroadcaster) throws InterruptedException 
	{
		print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHeader " + Player.get(player_id - 1).getFull_name().toUpperCase() + ";");
		
		if(detail == 0) {
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader " + allteam.get(Player.get(player_id - 1).getTeamId() - 1).
					getFullname().toUpperCase() + ";");
		}else if(detail == 1) {
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader " + "PLAYER OF THE TIE" + ";");
			
		}else {
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader " + "FASTRACK SMART PLAY OF THE TIE" + ";");
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
		print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT_PATH C:/Temp/Preview.bmp;");
		print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT 1920 1080;");
		TimeUnit.SECONDS.sleep(1);
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 0.0;");
		print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW OFF;");	
		this.status = BadmintonUtil.SUCCESSFUL;	
	}
	public void populateSuperMatch(PrintWriter print_writer,String viz_sence_path,List<Fixture> fix,BadmintonMatch Bad_match, String selectedbroadcaster) throws InterruptedException 
	{
		if (Bad_match == null) {
			this.status = "ERROR: Match is null";
		} else {
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHeader " + "SUPER MATCH" +";");
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
				
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader2 " + "MATCH " + Bad_match.getMatch().getMatchnumber() +";");
				
				/*for(Fixture fx : fix) {
					if((Bad_match.getMatch().getHomeTeam().getTeamId() == fx.getHometeam()) && (Bad_match.getMatch().getAwayTeam().getTeamId() == fx.getAwayteam())) {
						System.out.println(fx.getMatchnumber());
						
						
					}
				}*/

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
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgHomePlayerImage1 " + left_path + Bad_match.getMatch().getHomeTeam().getFirstname() + BadmintonUtil.DOUBLE_BACKSLASH +
							Bad_match.getMatch().getHomePlayers().get(0).getTicker_name() + ".png" +";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgAwayPlayerImage1 " + right_path + Bad_match.getMatch().getAwayTeam().getFirstname() + BadmintonUtil.DOUBLE_BACKSLASH +
							Bad_match.getMatch().getAwayPlayers().get(0).getTicker_name() + ".png" +";");

				}
				else {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerA1 " + Bad_match.getMatch().getHomePlayers().get(0).getTicker_name().toUpperCase() +";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerA2 " + Bad_match.getMatch().getAwayPlayers().get(0).getTicker_name().toUpperCase() +";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerB1 " + Bad_match.getMatch().getHomePlayers().get(1).getTicker_name().toUpperCase() +";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerB2 " + Bad_match.getMatch().getAwayPlayers().get(1).getTicker_name().toUpperCase() +";");

					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerC1 " + " " +";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerC2 " + " " +";");

					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgHomePlayerImage1 " + left_path + Bad_match.getMatch().getHomeTeam().getFirstname() + BadmintonUtil.DOUBLE_BACKSLASH +
							Bad_match.getMatch().getHomePlayers().get(0).getTicker_name() + ".png" +";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgAwayPlayerImage1 " + right_path + Bad_match.getMatch().getAwayTeam().getFirstname() + BadmintonUtil.DOUBLE_BACKSLASH +
							Bad_match.getMatch().getAwayPlayers().get(0).getTicker_name() + ".png" +";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgHomePlayerImage2 " + left_path + Bad_match.getMatch().getHomeTeam().getFirstname() + BadmintonUtil.DOUBLE_BACKSLASH +
							Bad_match.getMatch().getHomePlayers().get(1).getTicker_name() + ".png" +";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgAwayPlayerImage2 " + right_path + Bad_match.getMatch().getAwayTeam().getFirstname() + BadmintonUtil.DOUBLE_BACKSLASH +
							Bad_match.getMatch().getAwayPlayers().get(1).getTicker_name() + ".png" +";");
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
					//System.out.println("Away name team" + Bad_match.getMatch().getAwayTeam().getFirstname());
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgHomePlayerImage1 " + left_path + Bad_match.getMatch().getHomeTeam().getFirstname() + BadmintonUtil.DOUBLE_BACKSLASH +
							Bad_match.getMatch().getHomePlayers().get(0).getTicker_name() + ".png" +";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgAwayPlayerImage1 " + right_path + Bad_match.getMatch().getAwayTeam().getFirstname() + BadmintonUtil.DOUBLE_BACKSLASH +
							Bad_match.getMatch().getAwayPlayers().get(0).getTicker_name() + ".png" +";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgHomePlayerImage2 " + left_path + Bad_match.getMatch().getHomeTeam().getFirstname() + BadmintonUtil.DOUBLE_BACKSLASH +
							Bad_match.getMatch().getHomePlayers().get(1).getTicker_name() + ".png" +";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgAwayPlayerImage2 " + right_path + Bad_match.getMatch().getAwayTeam().getFirstname() + BadmintonUtil.DOUBLE_BACKSLASH +
							Bad_match.getMatch().getAwayPlayers().get(1).getTicker_name() + ".png" +";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgHomePlayerImage3 " + left_path + Bad_match.getMatch().getHomeTeam().getFirstname() + BadmintonUtil.DOUBLE_BACKSLASH +
							Bad_match.getMatch().getHomePlayers().get(2).getTicker_name() + ".png" +";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgAwayPlayerImage3 " + right_path + Bad_match.getMatch().getAwayTeam().getFirstname() + BadmintonUtil.DOUBLE_BACKSLASH +
							Bad_match.getMatch().getAwayPlayers().get(2).getTicker_name() + ".png" +";");
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
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgHomeTeamLogo " + logo_path + Bad_match.getMatch().getHomeTeam().getFirstname() + "_" + Bad_match.getMatch().getHomeTeam().getLastname() + ".png" +";");
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgAwayTeamLogo " + logo_path + Bad_match.getMatch().getAwayTeam().getFirstname() + "_" + Bad_match.getMatch().getAwayTeam().getLastname() + ".png" +";");
			}
			
			
			print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW ON;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In STOP;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out STOP;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 110.0;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
			print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT_PATH C:/Temp/Preview.bmp;");
			print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT 1920 1080;");
			TimeUnit.SECONDS.sleep(1);
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
			print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 0.0;");
			print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW OFF;");	
			this.status = BadmintonUtil.SUCCESSFUL;	
		}
	}
	public void populateSuperMatch1(PrintWriter print_writer,String viz_sence_path,List<Fixture> fix,BadmintonMatch Bad_match, String selectedbroadcaster) throws InterruptedException 
	{
		if (Bad_match == null) {
			this.status = "ERROR: Match is null";
		} else {
			if(Bad_match.getMatch().getSuperMatch() == 1) {
				
				for(Fixture fx : fix) {
					if((Bad_match.getMatch().getHomeTeam().getTeamId() == fx.getHometeam()) && (Bad_match.getMatch().getAwayTeam().getTeamId() == fx.getAwayteam())) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHeader " + "MATCH " + Bad_match.getMatch().getMatchnumber() +";");
					}
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
				print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT_PATH C:/Temp/Preview.bmp;");
				print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT 1920 1080;");
				TimeUnit.SECONDS.sleep(1);
				print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
				print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 0.0;");
				print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW OFF;");	
				this.status = BadmintonUtil.SUCCESSFUL;	
		}
	}
}
	public void populateSuperMatch2(PrintWriter print_writer,String viz_sence_path,List<Fixture> fix,BadmintonMatch Bad_match, String selectedbroadcaster) throws InterruptedException 
	{
		if (Bad_match == null) {
			this.status = "ERROR: Match is null";
		} else {
			if(Bad_match.getMatch().getSuperMatch() == 1) {
				
				for(Fixture fx : fix) {
					if((Bad_match.getMatch().getHomeTeam().getTeamId() == fx.getHometeam()) && (Bad_match.getMatch().getAwayTeam().getTeamId() == fx.getAwayteam())) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHeader " + "MATCH " + Bad_match.getMatch().getMatchnumber() +";");
					}
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
				print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT_PATH C:/Temp/Preview.bmp;");
				print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT 1920 1080;");
				TimeUnit.SECONDS.sleep(1);
				print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
				print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 0.0;");
				print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW OFF;");	
				this.status = BadmintonUtil.SUCCESSFUL;	
			}
		}
	}
	public void populateTieIdDouble(PrintWriter print_writer,String viz_sence_path,String day,List<Fixture> fix,BadmintonMatch Bad_match, String selectedbroadcaster) throws InterruptedException 
	{
		
		if (Bad_match == null) {
			this.status = "ERROR: Match is null";
		} else {
			int row_id = 1;
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHeader " + Bad_match.getTournamentName().toUpperCase() +";");
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, +1);
			if(day.equalsIgnoreCase(new SimpleDateFormat("dd-MM-yyyy").format(cal.getTime()))) {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader1 " + "FIXTURES" +";");
			}else {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader1 " + "FIXTURES" +";");
			}
			//System.out.println(day.compareTo(fix.get(0).getDate()) + 1); // want it to check which day match is this
			for(int i = 0; i <= fix.size()-1; i++) {
				if(fix.get(i).getDate().equalsIgnoreCase(day)) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgHomeTeam" +row_id+ "Logo " + logo_path + fix.get(i).getHome_Team().getFirstname() 
							+ "_" + fix.get(i).getHome_Team().getLastname() + BadmintonUtil.PNG_EXTENSION +";");
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgAwayTeam" +row_id+ "Logo " + logo_path + fix.get(i).getAway_Team().getFirstname() 
							+ "_" + fix.get(i).getAway_Team().getLastname() + BadmintonUtil.PNG_EXTENSION +";");
				 row_id = row_id +1;
				}
			}
			
		print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW ON;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In STOP;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out STOP;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 83.0;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
		print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT_PATH C:/Temp/Preview.bmp;");
		print_writer.println("LAYER1*EVEREST*GLOBAL SNAPSHOT 1920 1080;");
		TimeUnit.SECONDS.sleep(1);
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*Out SHOW 0.0;");
		print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*In SHOW 0.0;");
		print_writer.println("LAYER1*EVEREST*GLOBAL PREVIEW OFF;");
			
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