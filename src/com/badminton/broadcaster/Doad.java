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
	
	private String logo_path = "D:\\DOAD_In_House_Everest\\Everest_Sports\\Everest_GBPL\\Logos\\";
	private String photo_path = "D:\\DOAD_In_House_Everest\\Everest_Sports\\Everest_GBPL\\Photos\\MEDIUM\\";
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

	public void populateScoreBug(PrintWriter print_writer,String viz_sence_path,String Stats,BadmintonMatch Bad_match, String selectedbroadcaster) 
	{
		if (Bad_match == null) {
			this.status = "ERROR: Match is null";
		} else {
			
			populateScoreBugName(false,print_writer, viz_sence_path, Bad_match, selectedbroadcaster);
			//populateScoreBugStat(false,print_writer, viz_sence_path, Stats, Bad_match, selectedbroadcaster);
			this.status = BadmintonUtil.SUCCESSFUL;	
		}
	}	
	
	public void populateScoreBugName(boolean is_this_updating, PrintWriter print_writer,String viz_sence_path,BadmintonMatch Bad_match, String selectedbroadcaster) 
	{
		if (Bad_match == null) {
			this.status = "ERROR: Match is null";
		} else {
			
			print_writer.println("LAYER1*EVEREST*TREEVIEW*StartLogo_OR_TRUMP*CONTAINER SET ACTIVE 0;");
			for(Player hp : Bad_match.getMatch().getHomePlayers()) {
				if(Bad_match.getMatch().getHomePlayers() !=null && Bad_match.getMatch().getHomePlayers().size() > 0) {
					for(int i=1; i <= Bad_match.getMatch().getHomePlayers().size();i++){
						if(i==1){
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerName1 " + Bad_match.getMatch().getHomePlayers().get(Bad_match.getMatch().getHomePlayers().size() - i).getTicker_name() +";");
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTeamColor1 " + "255:255:255" +";");
						}
						else if(i==2){
						int j=i-1;
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerName1 " + Bad_match.getMatch().getHomePlayers().get(Bad_match.getMatch().getHomePlayers().size() - j).getTicker_name() + "/" 
						+ Bad_match.getMatch().getHomePlayers().get(Bad_match.getMatch().getHomePlayers().size() - i).getTicker_name() + ";");
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTeamColor1 " + "255:255:255" +";");
						}
						else if(i==3){
							int j=i-1;
							int k=i-2;
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerName1 " + Bad_match.getMatch().getHomePlayers().get(Bad_match.getMatch().getHomePlayers().size() - j).getTicker_name() + "/" 
									+ Bad_match.getMatch().getHomePlayers().get(Bad_match.getMatch().getHomePlayers().size() - k).getTicker_name() + "/" 
									+ Bad_match.getMatch().getHomePlayers().get(Bad_match.getMatch().getHomePlayers().size() - i).getTicker_name() + ";");
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTeamColor1 " + "255:255:255" +";");
						}
						
						}
					
					if(hp.getPlayerId() == Bad_match.getOnStrikePlayerId()) {
						//if(is_this_updating == false) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*ServiceGrp*CONTAINER SET ACTIVE 1;");
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vService " + "1" +";");
						//}
					}	
				}
			}					
				//}		
			//}
			for(Player ap : Bad_match.getMatch().getAwayPlayers()) {
				if(Bad_match.getMatch().getAwayPlayers() !=null && Bad_match.getMatch().getAwayPlayers().size() > 0) {
					//System.out.println(Bad_match.getMatch().getAwayPlayers().size());
					for(int i=1; i <= Bad_match.getMatch().getAwayPlayers().size();i++){
						if(i == 1){
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerName2 " + Bad_match.getMatch().getAwayPlayers().get(Bad_match.getMatch().getAwayPlayers().size() - i).getTicker_name() +";");
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTeamColor2 " + "0:0:0" +";");
						}
						else if(i == 2){
						int j=i-1;
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerName2 " + Bad_match.getMatch().getAwayPlayers().get(Bad_match.getMatch().getAwayPlayers().size() - j).getTicker_name() + "/" 
						+ Bad_match.getMatch().getAwayPlayers().get(Bad_match.getMatch().getAwayPlayers().size() - i).getTicker_name() + ";");
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTeamColor2 " + "0:0:0" +";");
						}
						else if(i == 3){
							int j=i-1;
							int k=i-2;
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tPlayerName2 " + Bad_match.getMatch().getAwayPlayers().get(Bad_match.getMatch().getAwayPlayers().size() - j).getTicker_name() + "/" 
									+ Bad_match.getMatch().getAwayPlayers().get(Bad_match.getMatch().getAwayPlayers().size() - k).getTicker_name() + "/" 
									+ Bad_match.getMatch().getAwayPlayers().get(Bad_match.getMatch().getAwayPlayers().size() - i).getTicker_name() + ";");
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTeamColor2 " + "0:0:0" +";");
						}
						
						}
					if(ap.getPlayerId() == Bad_match.getOnStrikePlayerId()) {
						//if(is_this_updating == false) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*ServiceGrp*CONTAINER SET ACTIVE 1;");
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vService " + "2" +";");
						//}
					}
					
				}
			}
			if(Bad_match.getSets() == null ) {
				if(is_this_updating == false) {
					processAnimation(print_writer, "ScoreIn", "START", selectedbroadcaster);
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vSet " + "0" +";");
				}
			}
			else {
				
				for(Set st : Bad_match.getSets()) {
					if(st.getSetNumber() == 1) {
						if(st.getStatus().equalsIgnoreCase("START") || st.getStatus().equalsIgnoreCase("END")) {
							if((st.getHomeTeamTotalScore() == 0 && st.getAwayTeamTotalScore() == 1) || (st.getHomeTeamTotalScore() == 1 && st.getAwayTeamTotalScore() == 0)) {
								print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*ScoreIn START;");
							}
							//if(is_this_updating == false) {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vSet " + "1" +";");
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Score1_grp*CONTAINER SET ACTIVE 1;");
							
							//}
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScoreTeam1_R1 " + st.getHomeTeamTotalScore() +";");
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScoreTeam2_R1 " + st.getAwayTeamTotalScore() +";");
						}
					}
					if(st.getSetNumber() == 2) {
						if(st.getStatus().equalsIgnoreCase("START") || st.getStatus().equalsIgnoreCase("END")) {
							if((st.getHomeTeamTotalScore() == 0 && st.getAwayTeamTotalScore() == 1) || (st.getHomeTeamTotalScore() == 1 && st.getAwayTeamTotalScore() == 0)) {
								print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*ScoreIn START;");
							}
							//if(is_this_updating == false) {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vSet " + "2" +";");
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Score1_grp*CONTAINER SET ACTIVE 1;");
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Score2_grp*CONTAINER SET ACTIVE 1;");
								//print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*ScoreIn START;");
							//}
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScoreTeam1_R2 " + st.getHomeTeamTotalScore() +";");
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScoreTeam2_R2 " + st.getAwayTeamTotalScore() +";");
						}
					}
					if(st.getSetNumber() == 3) {
						if(st.getStatus().equalsIgnoreCase("START") || st.getStatus().equalsIgnoreCase("END")) {
							if((st.getHomeTeamTotalScore() == 0 && st.getAwayTeamTotalScore() == 1) || (st.getHomeTeamTotalScore() == 1 && st.getAwayTeamTotalScore() == 0)) {
								print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*ScoreIn START;");
							}
							//if(is_this_updating == false) {
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vSet " + "3" +";");
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Score1_grp*CONTAINER SET ACTIVE 1;");
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Score2_grp*CONTAINER SET ACTIVE 1;");
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Score3_grp*CONTAINER SET ACTIVE 1;");
								//print_writer.println("LAYER1*EVEREST*STAGE*DIRECTOR*ScoreIn START;");
							//}
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScoreTeam1_R3 " + st.getHomeTeamTotalScore() +";");
							print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScoreTeam2_R3 " + st.getAwayTeamTotalScore() +";");
						}
					}
				}
			}
				//}
			//}
			this.status = BadmintonUtil.SUCCESSFUL;	
		}
	}
	public void populateScoreBugStat(boolean is_this_updating,PrintWriter print_writer,String viz_sence_path,String Stats,BadmintonMatch Bad_match, String selectedbroadcaster) 
	{
		if (Bad_match == null) {
			this.status = "ERROR: Match is null";
		} else {
			//System.out.println(Stats);
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
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTeamName01 " + Bad_match.getMatch().getHomeTeam().getFullname() +";");
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTeamName02 " + Bad_match.getMatch().getAwayTeam().getFullname() +";");
				break;
			}
			
			
			this.status = BadmintonUtil.SUCCESSFUL;	
		}
	}
	public void populateSingleL3MatchId(PrintWriter print_writer,String viz_sence_path,BadmintonMatch Bad_match, String selectedbroadcaster) 
	{
		if (Bad_match == null) {
			this.status = "ERROR: Match is null";
		} else {
			int Set1h = 0,Set1a=0, Set2h = 0,Set2a=0;
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgHomePlayer1Image " + photo_path + Bad_match.getMatch().getHomePlayers().get(0).getTicker_name() + ".png" + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgAwayPlayer1Image " + photo_path + Bad_match.getMatch().getAwayPlayers().get(0).getTicker_name() + ".png" + ";");

			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHomePlayerName " + Bad_match.getMatch().getHomePlayers().get(0).getFull_name().toUpperCase()+ ";");
			System.out.println(Bad_match.getMatch().getHomePlayers().get(0).getFull_name().toUpperCase());
			System.out.println(Bad_match.getMatch().getAwayPlayers().get(0).getFull_name().toUpperCase());
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tAwayPlayerName " + Bad_match.getMatch().getAwayPlayers().get(0).getFull_name().toUpperCase() + ";");
			
			for(Set st : Bad_match.getSets()) {
				if(st.getSetNumber() == 1) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScore_VS " + st.getHomeTeamTotalScore() + "-" + st.getAwayTeamTotalScore() +";");
					Set1h = st.getHomeTeamTotalScore(); 
					Set1a = st.getAwayTeamTotalScore();
				}
				if(st.getSetNumber() == 2) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScore_VS " + Set1h + "-" + Set1a + "," + st.getHomeTeamTotalScore() + "-" + st.getAwayTeamTotalScore() +";");
					Set2h = st.getHomeTeamTotalScore();
					Set2a = st.getAwayTeamTotalScore();
				}
				if(st.getSetNumber() == 3) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScore_VS " + Set1h + "-" + Set1a + "," + Set2h + "-" + Set2a + "," + st.getHomeTeamTotalScore() + "-" + st.getAwayTeamTotalScore() +";");
				}
			}
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHeader " + "MATCH " + Bad_match.getMatch().getMatchId() +";");
			if(Bad_match.getMatch().getCategoryId() == 0 ) {
				if(Bad_match.getMatch().getSuperMatch() == 1) {
					if(Bad_match.getMatch().getTrumpHomeMatch() == 1 || Bad_match.getMatch().getTrumpAwayMatch() == 1) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader " + "Super Match"+ "-"+ "Trump Match" +";");
					}
					else {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader " + "Super Match" + ";");
					}
				}
			}
			else {
				if(Bad_match.getMatch().getCategoryId() == 1) {
					
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader " + "Women's Single" +";");
					
				}
				if(Bad_match.getMatch().getCategoryId() == 2) {
					
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader " + "Men's Double" +";");
					
				}
				if(Bad_match.getMatch().getCategoryId() == 3) {
					
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader " + "Men's Single" +";");
					
				}
				if(Bad_match.getMatch().getCategoryId() == 4) {
					
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader " + "Mixed Double" +";");
					
				}
			}
			if(Bad_match.getMatch().getTrumpHomeMatch() == 1) {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vHomeTrump_NOTrump " + Bad_match.getMatch().getTrumpHomeMatch() + ";");
				
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHomeTeamName " + Bad_match.getMatch().getHomeTeam().getFullname().toUpperCase() + ";");
			}
			else {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vHomeTrump_NOTrump " + Bad_match.getMatch().getTrumpHomeMatch() + ";");
				
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHomeTeamName " + Bad_match.getMatch().getHomeTeam().getFullname().toUpperCase() + ";");
			}
			if(Bad_match.getMatch().getTrumpAwayMatch() == 1) {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vAwayTrump_NOTrump " + Bad_match.getMatch().getTrumpAwayMatch() + ";");
				
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tAwayTeamName " + Bad_match.getMatch().getAwayTeam().getFullname().toUpperCase() + ";");
			}
			else {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vAwayTrump_NOTrump " + Bad_match.getMatch().getTrumpAwayMatch() + ";");
				
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tAwayTeamName " + Bad_match.getMatch().getAwayTeam().getFullname().toUpperCase() + ";");
			}
			
			//print_writer.println("LAYER1*EVEREST*TREEVIEW*Subheader1*CONTAINER SET ACTIVE 0;");
			
		this.status = BadmintonUtil.SUCCESSFUL;	
		}
	}
	public void populateDoubleL3MatchId(PrintWriter print_writer,String viz_sence_path,BadmintonMatch Bad_match, String selectedbroadcaster) 
	{
		if (Bad_match == null) {
			this.status = "ERROR: Match is null";
		} else {
			int Set1h = 0,Set1a=0, Set2h = 0,Set2a=0;
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgHomePlayer1Image " + photo_path + Bad_match.getMatch().getHomePlayers().get(0).getTicker_name() + ".png" + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgHomePlayer2Image " + photo_path + Bad_match.getMatch().getHomePlayers().get(1).getTicker_name() + ".png" + ";");

			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgAwayPlayer1Image " + photo_path + Bad_match.getMatch().getAwayPlayers().get(0).getTicker_name() + ".png" + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgAwayPlayer2Image " + photo_path + Bad_match.getMatch().getAwayPlayers().get(1).getTicker_name() + ".png" + ";");
			
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHomePlayerName " + Bad_match.getMatch().getHomePlayers().get(0).getTicker_name().toUpperCase() + "/" + Bad_match.getMatch().getHomePlayers().get(1).getTicker_name().toUpperCase() + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tAwayPlayerName " + Bad_match.getMatch().getAwayPlayers().get(0).getTicker_name().toUpperCase() + "/" + Bad_match.getMatch().getAwayPlayers().get(1).getTicker_name().toUpperCase() + ";");
			
			for(Set st : Bad_match.getSets()) {
				if(st.getSetNumber() == 1) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScore_VS " + st.getHomeTeamTotalScore() + "-" + st.getAwayTeamTotalScore() +";");
					Set1h = st.getHomeTeamTotalScore(); 
					Set1a = st.getAwayTeamTotalScore();
				}
				if(st.getSetNumber() == 2) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScore_VS " + Set1h + "-" + Set1a + "," + st.getHomeTeamTotalScore() + "-" + st.getAwayTeamTotalScore() +";");
					Set2h = st.getHomeTeamTotalScore();
					Set2a = st.getAwayTeamTotalScore();
				}
				if(st.getSetNumber() == 3) {
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tScore_VS " + Set1h + "-" + Set1a + "," + Set2h + "-" + Set2a + "," + st.getHomeTeamTotalScore() + "-" + st.getAwayTeamTotalScore() +";");
				}
			}
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHeader " + "MATCH " + Bad_match.getMatch().getMatchId() +";");
			if(Bad_match.getMatch().getCategoryId() == 0 ) {
				if(Bad_match.getMatch().getSuperMatch() == 1) {
					if(Bad_match.getMatch().getTrumpHomeMatch() == 1 || Bad_match.getMatch().getTrumpAwayMatch() == 1) {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader " + "Super Match"+ "-"+ "Trump Match" +";");
					}
					else {
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader " + "Super Match" + ";");
					}
				}
			}
			else {
				if(Bad_match.getMatch().getCategoryId() == 1) {
					
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader " + "Women's Single" +";");
					
				}
				if(Bad_match.getMatch().getCategoryId() == 2) {
					
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader " + "Men's Double" +";");
					
				}
				if(Bad_match.getMatch().getCategoryId() == 3) {
					
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader " + "Men's Single" +";");
					
				}
				if(Bad_match.getMatch().getCategoryId() == 4) {
					
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader " + "Mixed Double" +";");
					}
			}
			if(Bad_match.getMatch().getTrumpHomeMatch() == 1) {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vHomeTrump_NOTrump " + Bad_match.getMatch().getTrumpHomeMatch() + ";");
				
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHomeTeamName " + Bad_match.getMatch().getHomeTeam().getFullname().toUpperCase() + ";");
			}
			else if(Bad_match.getMatch().getTrumpHomeMatch() == 0) {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vHomeTrump_NOTrump " + Bad_match.getMatch().getTrumpHomeMatch() + ";");
				
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHomeTeamName " + Bad_match.getMatch().getHomeTeam().getFullname().toUpperCase() + ";");
			}
			if(Bad_match.getMatch().getTrumpAwayMatch() == 1) {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vAwayTrump_NOTrump " + Bad_match.getMatch().getTrumpAwayMatch() + ";");
				
				
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tAwayTeamName " + Bad_match.getMatch().getAwayTeam().getFullname().toUpperCase() + ";");
			}
			else if(Bad_match.getMatch().getTrumpAwayMatch() == 0) {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vAwayTrump_NOTrump " + Bad_match.getMatch().getTrumpAwayMatch() + ";");
				
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tAwayTeamName " + Bad_match.getMatch().getAwayTeam().getFullname().toUpperCase() + ";");
			}
						
		this.status = BadmintonUtil.SUCCESSFUL;	
		}
	}
	public void populateSingleFFMatchId(PrintWriter print_writer,String viz_sence_path,BadmintonMatch Bad_match, String selectedbroadcaster) 
	{
		if (Bad_match == null) {
			this.status = "ERROR: Match is null";
		} else {
			
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHeader " + "MATCH " + Bad_match.getMatch().getMatchId() +";");
			//print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader1 " + "MATCH " + Bad_match.getMatch().getMatchId() +";");
			//print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader2 " + "MATCH " + Bad_match.getMatch().getMatchId() +";");
			if(Bad_match.getMatch().getTrumpHomeMatch() == 1 || Bad_match.getMatch().getTrumpAwayMatch() == 1) {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vTrumpMatch " + "1" +";");
			}
			else {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vTrumpMatch " + "0" +";");
			}
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgHomePlayerImage " + photo_path + Bad_match.getMatch().getHomePlayers().get(0).getTicker_name() + ".png" + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgHomeTeamLogo " + logo_path +  Bad_match.getMatch().getHomeTeam().getFullname() + ".png"  + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgAwayTeamLogo " + logo_path +  Bad_match.getMatch().getAwayTeam().getFullname() + ".png"  + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgAwayPlayerImage " + photo_path + Bad_match.getMatch().getAwayPlayers().get(0).getTicker_name() + ".png" + ";");
			//print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHomePlayerName " + Bad_match.getMatch().getHomePlayers().get(0).getFull_name().toUpperCase()+ ";");
			//print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tAwayPlayerName " + Bad_match.getMatch().getAwayPlayers().get(0).getFull_name().toUpperCase() + ";");
			if(Bad_match.getMatch().getTrumpHomeMatch() == 1 ) {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vHomeTrump " + Bad_match.getMatch().getTrumpHomeMatch() +";");
				print_writer.println("LAYER1*EVEREST*TREEVIEW*TrumpGrp*CONTAINER SET ACTIVE 1;");
				
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHomeTeamName " + Bad_match.getMatch().getHomeTeam().getFullname().toUpperCase() + ";");
			}
			else {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vHomeTrump " + Bad_match.getMatch().getTrumpHomeMatch() +";");
				print_writer.println("LAYER1*EVEREST*TREEVIEW*TrumpGrp*CONTAINER SET ACTIVE 0;");
				
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHomeTeamName " + Bad_match.getMatch().getHomeTeam().getFullname().toUpperCase() + ";");
			}
			if(Bad_match.getMatch().getTrumpAwayMatch() == 1 ) {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vAwayTrump " + Bad_match.getMatch().getTrumpAwayMatch() +";");
				print_writer.println("LAYER1*EVEREST*TREEVIEW*TrumpGrp1*CONTAINER SET ACTIVE 1;");
				
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tAwayTeamName " + Bad_match.getMatch().getAwayTeam().getFullname().toUpperCase() + ";");
			}
			else {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vAwayTrump " + Bad_match.getMatch().getTrumpAwayMatch() +";");
				print_writer.println("LAYER1*EVEREST*TREEVIEW*TrumpGrp1*CONTAINER SET ACTIVE 0;");
				
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tAwayTeamName " + Bad_match.getMatch().getAwayTeam().getFullname().toUpperCase() + ";");
			}
			
		this.status = BadmintonUtil.SUCCESSFUL;	
		}
	}
	public void populateDoubleFFMatchId(PrintWriter print_writer,String viz_sence_path,BadmintonMatch Bad_match, String selectedbroadcaster) 
	{
		if (Bad_match == null) {
			this.status = "ERROR: Match is null";
		} else {
			
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHeader " + "MATCH " + Bad_match.getMatch().getMatchId() +";");
			//print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader1 " + "MATCH " + Bad_match.getMatch().getMatchId() +";");
			//print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader2 " + "MATCH " + Bad_match.getMatch().getMatchId() +";");
			if(Bad_match.getMatch().getTrumpHomeMatch() == 1 || Bad_match.getMatch().getTrumpAwayMatch() == 1) {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vTrumpMatch " + "1" +";");
			}
			else {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vTrumpMatch " + "0" +";");
			}
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgHomePlayerImage1 " + photo_path + Bad_match.getMatch().getHomePlayers().get(0).getTicker_name() + ".png" + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgHomePlayerImage2 " + photo_path + Bad_match.getMatch().getHomePlayers().get(1).getTicker_name() + ".png" + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgHomeTeamLogo " + logo_path +  Bad_match.getMatch().getHomeTeam().getFullname() + ".png"  + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgAwayTeamLogo " + logo_path +  Bad_match.getMatch().getAwayTeam().getFullname() + ".png"  + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgAwayPlayerImage1 " + photo_path + Bad_match.getMatch().getAwayPlayers().get(0).getTicker_name() + ".png" + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgAwayPlayerImage2 " + photo_path + Bad_match.getMatch().getAwayPlayers().get(1).getTicker_name() + ".png" + ";");
			//print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHomePlayerName " + Bad_match.getMatch().getHomePlayers().get(0).getFull_name().toUpperCase()+ ";");
			//print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tAwayPlayerName " + Bad_match.getMatch().getAwayPlayers().get(0).getFull_name().toUpperCase() + ";");
			if(Bad_match.getMatch().getTrumpHomeMatch() == 1 ) {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vHomeTrump " + Bad_match.getMatch().getTrumpHomeMatch() +";");
				print_writer.println("LAYER1*EVEREST*TREEVIEW*TrumpGrp*CONTAINER SET ACTIVE 1;");
				
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHomeTeamName " + Bad_match.getMatch().getHomeTeam().getFullname().toUpperCase() + ";");
			}
			else {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vHomeTrump " + Bad_match.getMatch().getTrumpHomeMatch() +";");
				print_writer.println("LAYER1*EVEREST*TREEVIEW*TrumpGrp*CONTAINER SET ACTIVE 0;");
				
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHomeTeamName " + Bad_match.getMatch().getHomeTeam().getFullname().toUpperCase() + ";");
			}
			if(Bad_match.getMatch().getTrumpAwayMatch() == 1 ) {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vAwayTrump " + Bad_match.getMatch().getTrumpAwayMatch() +";");
				print_writer.println("LAYER1*EVEREST*TREEVIEW*TrumpGrp1*CONTAINER SET ACTIVE 1;");
				
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tAwayTeamName " + Bad_match.getMatch().getAwayTeam().getFullname().toUpperCase() + ";");
			}
			else {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET vAwayTrump " + Bad_match.getMatch().getTrumpAwayMatch() +";");
				print_writer.println("LAYER1*EVEREST*TREEVIEW*TrumpGrp1*CONTAINER SET ACTIVE 0;");
				
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tAwayTeamName " + Bad_match.getMatch().getAwayTeam().getFullname().toUpperCase() + ";");
			}
			
		this.status = BadmintonUtil.SUCCESSFUL;	
		}
	}
	public void populateLtTieId(PrintWriter print_writer,String viz_sence_path,BadmintonMatch Bad_match, String selectedbroadcaster) 
	{
		if (Bad_match == null) {
			this.status = "ERROR: Match is null";
		} else {
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHeader " + "DAY " + Bad_match.getMatch().getMatchId() +";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader " + "TIE " + Bad_match.getMatch().getMatchId() +";");
			
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgHomePlayer1Image " + logo_path + Bad_match.getMatch().getHomeTeam().getFullname() + ".png" + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHomeTeamName " + Bad_match.getMatch().getHomeTeam().getFullname().toUpperCase() + ";");
			
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgAwayPlayer1Image " + logo_path + Bad_match.getMatch().getAwayTeam().getFullname() + ".png" + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tAwayTeamName " + Bad_match.getMatch().getAwayTeam().getFullname().toUpperCase() + ";");			
						
		this.status = BadmintonUtil.SUCCESSFUL;	
		}
	}
	public void populateFFTieId(PrintWriter print_writer,String viz_sence_path,BadmintonMatch Bad_match, String selectedbroadcaster) 
	{
		if (Bad_match == null) {
			this.status = "ERROR: Match is null";
		} else {
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHeader " + "DAY " + Bad_match.getMatch().getMatchId() +";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader1 " + "LIVE FROM DEHRADUN " + Bad_match.getMatch().getMatchId() +";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader2 " + "TIE " + Bad_match.getMatch().getMatchId() +";");
			
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgHomeTeamLogo " + logo_path + Bad_match.getMatch().getHomeTeam().getFullname() + ".png" + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHomeTeamFirstName " + Bad_match.getMatch().getHomeTeam().getFullname().toUpperCase() + ";");
			//print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHomeTeamLastName " + Bad_match.getMatch().getHomeTeam().getFullname().toUpperCase() + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*TEAM1LastName*CONTAINER SET ACTIVE 0;");
			
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgAwayTeamLogo " + logo_path + Bad_match.getMatch().getAwayTeam().getFullname() + ".png" + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tAwayTeamFirstName " + Bad_match.getMatch().getAwayTeam().getFullname().toUpperCase() + ";");
			//print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tAwayTeamFirstName " + Bad_match.getMatch().getAwayTeam().getFullname().toUpperCase() + ";");			
			print_writer.println("LAYER1*EVEREST*TREEVIEW*TEAM2LastName*CONTAINER SET ACTIVE 0;");
			
		this.status = BadmintonUtil.SUCCESSFUL;	
		}
	}
	public void populateSides(PrintWriter print_writer,String viz_sence_path,String Top,String Bottom,BadmintonMatch Bad_match, String selectedbroadcaster) 
	{
		if (Bad_match == null) {
			this.status = "ERROR: Match is null";
		} else {
			switch(Top.toUpperCase()) {
			case "HOME_PLAYER":
				for(int i=1; i <= Bad_match.getMatch().getHomePlayers().size();i++){
					if(i==1){
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTopName " + Bad_match.getMatch().getHomePlayers().get(Bad_match.getMatch().getHomePlayers().size() - i).getTicker_name() +";");
					}
					else if(i==2){
					int j=i-1;
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTopName " + Bad_match.getMatch().getHomePlayers().get(Bad_match.getMatch().getHomePlayers().size() - j).getTicker_name() + "/" 
					+ Bad_match.getMatch().getHomePlayers().get(Bad_match.getMatch().getHomePlayers().size() - i).getTicker_name() + ";");
					}
					else if(i==3){
						int j=i-1;
						int k=i-2;
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTopName " + Bad_match.getMatch().getHomePlayers().get(Bad_match.getMatch().getHomePlayers().size() - j).getTicker_name() + "/" 
								+ Bad_match.getMatch().getHomePlayers().get(Bad_match.getMatch().getHomePlayers().size() - k).getTicker_name() + "/" 
								+ Bad_match.getMatch().getHomePlayers().get(Bad_match.getMatch().getHomePlayers().size() - i).getTicker_name() + ";");
					}
					
					}
				break;
			case "AWAY_PLAYER":
				for(int i=1; i <= Bad_match.getMatch().getHomePlayers().size();i++){
					if(i==1){
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTopName " + Bad_match.getMatch().getAwayPlayers().get(Bad_match.getMatch().getAwayPlayers().size() - i).getTicker_name() +";");
					}
					else if(i==2){
					int j=i-1;
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTopName " + Bad_match.getMatch().getAwayPlayers().get(Bad_match.getMatch().getAwayPlayers().size() - j).getTicker_name() + "/" 
					+ Bad_match.getMatch().getAwayPlayers().get(Bad_match.getMatch().getAwayPlayers().size() - i).getTicker_name() + ";");
					}
					else if(i==3){
						int j=i-1;
						int k=i-2;
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tTopName " + Bad_match.getMatch().getAwayPlayers().get(Bad_match.getMatch().getAwayPlayers().size() - j).getTicker_name() + "/" 
								+ Bad_match.getMatch().getAwayPlayers().get(Bad_match.getMatch().getAwayPlayers().size() - k).getTicker_name() + "/" 
								+ Bad_match.getMatch().getAwayPlayers().get(Bad_match.getMatch().getAwayPlayers().size() - i).getTicker_name() + ";");
					}
					
					}
				break;
			}
			switch(Bottom.toUpperCase()) {
			case "HOME_PLAYER":
				for(int i=1; i <= Bad_match.getMatch().getHomePlayers().size();i++){
					if(i==1){
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET BottomName " + Bad_match.getMatch().getHomePlayers().get(Bad_match.getMatch().getHomePlayers().size() - i).getTicker_name() +";");
					}
					else if(i==2){
					int j=i-1;
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET BottomName " + Bad_match.getMatch().getHomePlayers().get(Bad_match.getMatch().getHomePlayers().size() - j).getTicker_name() + "/" 
					+ Bad_match.getMatch().getHomePlayers().get(Bad_match.getMatch().getHomePlayers().size() - i).getTicker_name() + ";");
					}
					else if(i==3){
						int j=i-1;
						int k=i-2;
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET BottomName " + Bad_match.getMatch().getHomePlayers().get(Bad_match.getMatch().getHomePlayers().size() - j).getTicker_name() + "/" 
								+ Bad_match.getMatch().getHomePlayers().get(Bad_match.getMatch().getHomePlayers().size() - k).getTicker_name() + "/" 
								+ Bad_match.getMatch().getHomePlayers().get(Bad_match.getMatch().getHomePlayers().size() - i).getTicker_name() + ";");
					}
					
					}
				break;
			case "AWAY_PLAYER":
				for(int i=1; i <= Bad_match.getMatch().getHomePlayers().size();i++){
					if(i==1){
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET BottomName " + Bad_match.getMatch().getAwayPlayers().get(Bad_match.getMatch().getAwayPlayers().size() - i).getTicker_name() +";");
					}
					else if(i==2){
					int j=i-1;
					print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET BottomName " + Bad_match.getMatch().getAwayPlayers().get(Bad_match.getMatch().getAwayPlayers().size() - j).getTicker_name() + "/" 
					+ Bad_match.getMatch().getAwayPlayers().get(Bad_match.getMatch().getAwayPlayers().size() - i).getTicker_name() + ";");
					}
					else if(i==3){
						int j=i-1;
						int k=i-2;
						print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET BottomName " + Bad_match.getMatch().getAwayPlayers().get(Bad_match.getMatch().getAwayPlayers().size() - j).getTicker_name() + "/" 
								+ Bad_match.getMatch().getAwayPlayers().get(Bad_match.getMatch().getAwayPlayers().size() - k).getTicker_name() + "/" 
								+ Bad_match.getMatch().getAwayPlayers().get(Bad_match.getMatch().getAwayPlayers().size() - i).getTicker_name() + ";");
					}
					
					}
				break;
			}
			
		this.status = BadmintonUtil.SUCCESSFUL;	
		}
	}
	public void populatePlayerProfile(PrintWriter print_writer,String viz_sence_path,Player pp,Team tm,BadmintonMatch Bad_match, String selectedbroadcaster) 
	{
		if (Bad_match == null) {
			this.status = "ERROR: Match is null";
		} else {
			//print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHeader " +  +";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader1 " + "PLAYER PROFILE" +";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tStatValue1 " + pp.getAge() +";");
			if(pp.getTeamId() == tm.getTeamId()) {
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tStatValue2 " + tm.getFullname().toUpperCase() +";");
				print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgHomeTeamLogo " + logo_path + tm.getFullname() + ".png" + ";");
				
			}
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tStatValue3 " + pp.getRank() +";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tRunningText1 " + pp.getText1()  +";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tRunningText2 " + pp.getText2() +";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET lgHomePlayerImage " + photo_path + pp.getTicker_name() + ".png" + ";");
			
		this.status = BadmintonUtil.SUCCESSFUL;	
		}
	}
	public void populateSuper(PrintWriter print_writer,String viz_sence_path,NameSuper ns,BadmintonMatch Bad_match, String selectedbroadcaster) 
	{
		if (Bad_match == null) {
			this.status = "ERROR: Match is null";
		} else {
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHeader " + ns.getFirstname() + " " + ns.getSurname() + ";");
			print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tSubHeader " + ns.getSubLine()  + ";");
			//print_writer.println("LAYER1*EVEREST*TREEVIEW*Main*FUNCTION*TAG_CONTROL SET tHeader " + "DAY " + Bad_match.getMatch().getMatchId() +";");
			
			
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