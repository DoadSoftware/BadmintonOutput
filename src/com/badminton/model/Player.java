package com.badminton.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Column;

@Entity
@Table(name = "Players")
public class Player
{
  @Id
  @Column(name = "PLAYERID")
  private int playerId;
	
  @Column(name = "FULLNAME")
  private String full_name;

  @Column(name = "TICKERNAME")
  private String ticker_name;

  @Column(name = "TEAMID")
  private int teamId;
  
  @Column(name = "AGE")
  private String age;
  
  @Column(name = "TEXT1")
  private String text1;
  
  @Column(name = "TEXT2")
  private String text2;
  
  @Column(name = "BAIRANKING")
  private String baiRanking;
  
  @Column(name = "BWFRANKING")
  private String bwfRanking;
  
  @Column(name = "ICONPLAYER")
  private String iconPlayer;
  
  @Column(name = "PLAYERPHOTONAME")
  private String playerPhotoName;
  
  @Transient
  private Team team;
  
public int getPlayerId() {
	return playerId;
}

public void setPlayerId(int playerId) {
	this.playerId = playerId;
}

public String getFull_name() {
	return full_name;
}

public void setFull_name(String full_name) {
	this.full_name = full_name;
}

public String getTicker_name() {
	return ticker_name;
}

public void setTicker_name(String ticker_name) {
	this.ticker_name = ticker_name;
}

public int getTeamId() {
	return teamId;
}

public void setTeamId(int teamId) {
	this.teamId = teamId;
}

public String getAge() {
	return age;
}

public void setAge(String age) {
	this.age = age;
}

public String getText1() {
	return text1;
}

public void setText1(String text1) {
	this.text1 = text1;
}

public String getText2() {
	return text2;
}

public void setText2(String text2) {
	this.text2 = text2;
}

public String getBaiRanking() {
	return baiRanking;
}

public void setBaiRanking(String baiRanking) {
	this.baiRanking = baiRanking;
}

public String getBwfRanking() {
	return bwfRanking;
}

public void setBwfRanking(String bwfRanking) {
	this.bwfRanking = bwfRanking;
}

public String getIconPlayer() {
	return iconPlayer;
}

public void setIconPlayer(String iconPlayer) {
	this.iconPlayer = iconPlayer;
}

public String getPlayerPhotoName() {
	return playerPhotoName;
}

public void setPlayerPhotoName(String playerPhotoName) {
	this.playerPhotoName = playerPhotoName;
}

public Team getTeam() {
	return team;
}

public void setTeam(Team team) {
	this.team = team;
}

}