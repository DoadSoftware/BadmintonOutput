package com.badminton.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.badminton.dao.BadmintonDao;
import com.badminton.model.Fixture;
import com.badminton.model.Match;
import com.badminton.model.Player;
import com.badminton.model.Team;
import com.badminton.model.TeamColor;
import com.badminton.service.BadmintonService;
import com.badminton.model.NameSuper;

@Service("badmintonService")
@Transactional
public class BadmintonServiceImpl implements BadmintonService {

 @Autowired
 private BadmintonDao badmintonDao;
 
@Override
public Player getPlayer(int player_id) {
	return badmintonDao.getPlayer(player_id);
}

@Override
public Team getTeam(int team_id) {
	return badmintonDao.getTeam(team_id);
}

@Override
public List<Match> getAllMatches() {
	return badmintonDao.getAllMatches();
}

@Override
public Match getMatch(int matchID) {
	return badmintonDao.getMatch(matchID);
}

@Override
public List<Player> getAllPlayer() {
	return badmintonDao.getAllPlayer();
}

@Override
public List<Team> getAllTeam() {
	return badmintonDao.getAllTeam();
}

@Override
public List<NameSuper> getNameSupers() {
	return badmintonDao.getNameSupers();
}

@Override
public List<TeamColor> getTeamColors() {
	return badmintonDao.getTeamColors();
}

@Override
public List<Fixture> getFixtures() {
	return badmintonDao.getFixtures();
}

@Override
public List<Player> getCurrPlayer(Match match) {
	return badmintonDao.getCurrPlayer(match);
}

}