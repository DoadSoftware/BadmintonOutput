package com.badminton.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiData {
	
	@JsonProperty("leaderboard")
	private List<LeaderBoard> leaderboard;

	public List<LeaderBoard> getLeaderboard() {
		return leaderboard;
	}

	public void setLeaderboard(List<LeaderBoard> leaderboard) {
		this.leaderboard = leaderboard;
	}
	
}