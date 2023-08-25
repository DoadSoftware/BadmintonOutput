package com.badminton.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name = "Teams")
public class Team {

  @Id
  @Column(name = "TeamId")
  private int teamId;
	
  @Column(name = "TeamName1")
  private String teamName1;
  
  @Column(name = "TeamName2")
  private String teamName2;
  
  @Column(name = "TeamName3")
  private String teamName3;

  @Column(name = "TeamName4")
  private String teamName4;
  
  @Column(name = "MentorName")
  private String mentorName;
  
  @Column(name = "GroupName")
  private String groupname;
  
  @Column(name = "TeamApiId")
  private String teamApiId;
  
  @Column(name = "TeamColor")
  private String teamcolor;
  
  @Column(name = "RGB")
  private String rgb;

public int getTeamId() {
	return teamId;
}

public void setTeamId(int teamId) {
	this.teamId = teamId;
}

public String getTeamName1() {
	return teamName1;
}

public void setTeamName1(String teamName1) {
	this.teamName1 = teamName1;
}

public String getTeamName2() {
	return teamName2;
}

public void setTeamName2(String teamName2) {
	this.teamName2 = teamName2;
}

public String getTeamName3() {
	return teamName3;
}

public void setTeamName3(String teamName3) {
	this.teamName3 = teamName3;
}

public String getTeamName4() {
	return teamName4;
}

public void setTeamName4(String teamName4) {
	this.teamName4 = teamName4;
}

public String getMentorName() {
	return mentorName;
}

public void setMentorName(String mentorName) {
	this.mentorName = mentorName;
}

public String getGroupname() {
	return groupname;
}

public void setGroupname(String groupname) {
	this.groupname = groupname;
}

public String getTeamApiId() {
	return teamApiId;
}

public void setTeamApiId(String teamApiId) {
	this.teamApiId = teamApiId;
}

public String getTeamcolor() {
	return teamcolor;
}

public void setTeamcolor(String teamcolor) {
	this.teamcolor = teamcolor;
}

public String getRgb() {
	return rgb;
}

public void setRgb(String rgb) {
	this.rgb = rgb;
}

}