package com.badminton.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LeaderBoard {
	
	@JsonProperty("teamname")
	private String teamname;
	
	@JsonProperty("leaderboard")
	private List<SubleaderBoard> subleaderboard;

	public String getTeamname() {
		return teamname;
	}

	public void setTeamname(String teamname) {
		this.teamname = teamname;
	}

	public List<SubleaderBoard> getSubleaderboard() {
		return subleaderboard;
	}

	public void setSubleaderboard(List<SubleaderBoard> subleaderboard) {
		this.subleaderboard = subleaderboard;
	}
	
	
	
}