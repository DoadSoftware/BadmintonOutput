package com.badminton.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SubleaderBoard {
	
	@JsonProperty("sid")
	private String sid;
	
	@JsonProperty("rank")
	private String rank;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("city")
	private String city;
	
	@JsonProperty("points")
	private String points;
	
	@JsonProperty("avguserfanemote")
	private String avguserfanemote;
	
	@JsonProperty("avguserhr")
	private String avguserhr;
	
	@JsonProperty("highestuserfanemote")
	private String highestuserfanemote;
	
	@JsonProperty("totaltapcount")
	private String totaltapcount;
	
	@JsonProperty("totalwavecount")
	private String totalwavecount;
	
	@JsonProperty("totalwhistleredeemed")
	private String totalwhistleredeemed;
	
	@JsonProperty("highestcheeredplayer")
	private String highestcheeredplayer;
	
	@JsonProperty("highestclicksponsor")
	private String highestclicksponsor;
	
	@JsonProperty("usersponsorclicks")
	private String usersponsorclicks;
	
	@JsonProperty("totalheartratecount")
	private String totalheartratecount;

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}

	public String getAvguserfanemote() {
		return avguserfanemote;
	}

	public void setAvguserfanemote(String avguserfanemote) {
		this.avguserfanemote = avguserfanemote;
	}

	public String getAvguserhr() {
		return avguserhr;
	}

	public void setAvguserhr(String avguserhr) {
		this.avguserhr = avguserhr;
	}

	public String getHighestuserfanemote() {
		return highestuserfanemote;
	}

	public void setHighestuserfanemote(String highestuserfanemote) {
		this.highestuserfanemote = highestuserfanemote;
	}

	public String getTotaltapcount() {
		return totaltapcount;
	}

	public void setTotaltapcount(String totaltapcount) {
		this.totaltapcount = totaltapcount;
	}

	public String getTotalwavecount() {
		return totalwavecount;
	}

	public void setTotalwavecount(String totalwavecount) {
		this.totalwavecount = totalwavecount;
	}

	public String getTotalwhistleredeemed() {
		return totalwhistleredeemed;
	}

	public void setTotalwhistleredeemed(String totalwhistleredeemed) {
		this.totalwhistleredeemed = totalwhistleredeemed;
	}

	public String getHighestcheeredplayer() {
		return highestcheeredplayer;
	}

	public void setHighestcheeredplayer(String highestcheeredplayer) {
		this.highestcheeredplayer = highestcheeredplayer;
	}

	public String getHighestclicksponsor() {
		return highestclicksponsor;
	}

	public void setHighestclicksponsor(String highestclicksponsor) {
		this.highestclicksponsor = highestclicksponsor;
	}

	public String getUsersponsorclicks() {
		return usersponsorclicks;
	}

	public void setUsersponsorclicks(String usersponsorclicks) {
		this.usersponsorclicks = usersponsorclicks;
	}

	public String getTotalheartratecount() {
		return totalheartratecount;
	}

	public void setTotalheartratecount(String totalheartratecount) {
		this.totalheartratecount = totalheartratecount;
	}

	@Override
	public String toString() {
		return "SubleaderBoard [sid=" + sid + ", rank=" + rank + ", name=" + name + ", city=" + city + ", points="
				+ points + ", avguserfanemote=" + avguserfanemote + ", avguserhr=" + avguserhr
				+ ", highestuserfanemote=" + highestuserfanemote + ", totaltapcount=" + totaltapcount
				+ ", totalwavecount=" + totalwavecount + ", totalwhistleredeemed=" + totalwhistleredeemed
				+ ", highestcheeredplayer=" + highestcheeredplayer + ", highestclicksponsor=" + highestclicksponsor
				+ ", usersponsorclicks=" + usersponsorclicks + ", totalheartratecount=" + totalheartratecount + "]";
	}

}