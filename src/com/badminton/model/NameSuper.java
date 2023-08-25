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
@Table(name = "NameSupers")
public class NameSuper
{
  @Id
  @Column(name = "NameSuperID")
  private int namesuperId;

  @Column(name = "Prompt")
  private String prompt;
  
  @Column(name = "FirstName")
  private String firstname;

  @Column(name = "SurName")
  private String surname;

  @Column(name = "SubLine")
  private String subLine;

  @Column(name = "Logo")
  private String logo;
  
  public NameSuper() {
		super();
  }

  public NameSuper(int namesuperId) {
	super();
	this.namesuperId = namesuperId;
  }

public int getNamesuperId() {
	return namesuperId;
}

public void setNamesuperId(int namesuperId) {
	this.namesuperId = namesuperId;
}

public String getPrompt() {
	return prompt;
}

public void setPrompt(String prompt) {
	this.prompt = prompt;
}

public String getFirstname() {
	return firstname;
}

public void setFirstname(String firstname) {
	this.firstname = firstname;
}

public String getSurname() {
	return surname;
}

public void setSurname(String surname) {
	this.surname = surname;
}

public String getSubLine() {
	return subLine;
}

public void setSubLine(String subLine) {
	this.subLine = subLine;
}

public String getLogo() {
	return logo;
}

public void setLogo(String logo) {
	this.logo = logo;
}

@Override
public String toString() {
	return "NameSuper [namesuperId=" + namesuperId + ", prompt=" + prompt + ", firstname=" + firstname + ", surname="
			+ surname + ", subLine=" + subLine + ", logo=" + logo + "]";
}

}