package com.badminton.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="LeagueTeam")
@XmlAccessorType(XmlAccessType.FIELD)
public class LeagueTeam {

	@XmlElement(name="QualifiedStatus")
	private String qualifiedStatus;
	
	@XmlElement(name="TeamName")
	private String TeamName;
	
	@XmlElement(name="GroupName")
	private String GroupName;
	
	@XmlElement(name="Played")
	private int Played;
	
	@XmlElement(name="Won")
	private int Won;
	
	@XmlElement(name="Lost")
	private int Lost;
	
	@XmlElement(name="TrumpMatchWin")
	private int TrumpMatchWin;
	
	@XmlElement(name="TrumpMatchLost")
	private int TrumpMatchLost;
	
	@XmlElement(name="SuperMatchWin")
	private int SuperMatchWin;
	
	@XmlElement(name="Points")
	private int Points;
	
	public String getQualifiedStatus() {
		return qualifiedStatus;
	}

	public void setQualifiedStatus(String qualifiedStatus) {
		this.qualifiedStatus = qualifiedStatus;
	}

	public String getTeamName() {
		return TeamName;
	}

	public void setTeamName(String teamName) {
		TeamName = teamName;
	}

	public String getGroupName() {
		return GroupName;
	}

	public void setGroupName(String groupName) {
		GroupName = groupName;
	}

	public int getPlayed() {
		return Played;
	}

	public void setPlayed(int played) {
		Played = played;
	}

	public int getWon() {
		return Won;
	}

	public void setWon(int won) {
		Won = won;
	}

	public int getLost() {
		return Lost;
	}

	public void setLost(int lost) {
		Lost = lost;
	}

	public int getTrumpMatchWin() {
		return TrumpMatchWin;
	}

	public void setTrumpMatchWin(int trumpMatchWin) {
		TrumpMatchWin = trumpMatchWin;
	}

	public int getTrumpMatchLost() {
		return TrumpMatchLost;
	}

	public void setTrumpMatchLost(int trumpMatchLost) {
		TrumpMatchLost = trumpMatchLost;
	}

	public int getSuperMatchWin() {
		return SuperMatchWin;
	}

	public void setSuperMatchWin(int superMatchWin) {
		SuperMatchWin = superMatchWin;
	}

	public int getPoints() {
		return Points;
	}

	public void setPoints(int points) {
		Points = points;
	}
	
}