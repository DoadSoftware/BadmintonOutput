package com.badminton.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.DiffBuilder;
import org.apache.commons.lang3.builder.DiffResult;
import org.apache.commons.lang3.builder.Diffable;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;


@SuppressWarnings("unused")
@Entity
@Table(name = "Fixtures")
public class Fixture {

  @Id
  @Column(name = "MATCHNUMBER")
  private int matchnumber;
  
  @Column(name = "Date")
  private String date;
  
  @Column(name = "GROUPNAME")
  private String groupname;

  @Column(name = "HOMETEAM")
  private int hometeam;

  @Column(name = "AWAYTEAM")
  private int awayteam;
  
  @Column(name = "WINNER")
  private String winner;

  @Column(name = "MARGIN")
  private String margin;
  
  @Transient
  private Team home_Team;

  @Transient
  private Team away_Team;

public Fixture() {
	super();
}

public Fixture(int matchnumber) {
	super();
	this.matchnumber = matchnumber;
}

public int getMatchnumber() {
	return matchnumber;
}

public void setMatchnumber(int matchnumber) {
	this.matchnumber = matchnumber;
}

public String getDate() {
	return date;
}

public void setDate(String date) {
	this.date = date;
}

public String getGroupname() {
	return groupname;
}

public void setGroupname(String groupname) {
	this.groupname = groupname;
}

public int getHometeam() {
	return hometeam;
}

public void setHometeam(int hometeam) {
	this.hometeam = hometeam;
}

public int getAwayteam() {
	return awayteam;
}

public void setAwayteam(int awayteam) {
	this.awayteam = awayteam;
}

public String getWinner() {
	return winner;
}

public void setWinner(String winner) {
	this.winner = winner;
}

public String getMargin() {
	return margin;
}

public void setMargin(String margin) {
	this.margin = margin;
}

public Team getHome_Team() {
	return home_Team;
}

public void setHome_Team(Team home_Team) {
	this.home_Team = home_Team;
}

public Team getAway_Team() {
	return away_Team;
}

public void setAway_Team(Team away_Team) {
	this.away_Team = away_Team;
}

  
}