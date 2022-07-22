package com.badminton.dao.impl;

import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.badminton.dao.BadmintonDao;
import com.badminton.model.NameSuper;
import com.badminton.model.Fixture;
import com.badminton.model.Match;
import com.badminton.model.Player;
import com.badminton.model.Team;
import com.badminton.model.TeamColor;

@Transactional
@Repository("badmintonDao")
@SuppressWarnings("unchecked")
public class BadmintonDaoImpl implements BadmintonDao {

 @Autowired
 private SessionFactory sessionFactory;
 
@Override
public Player getPlayer(int player_id) {
	return (Player) sessionFactory.getCurrentSession().createQuery("from Player WHERE PlayerId=" + player_id).uniqueResult();  
}

@Override
public Team getTeam(int team_id) {
	return (Team) sessionFactory.getCurrentSession().createQuery("from Team WHERE TeamId=" + team_id).uniqueResult();  
}

@Override
public List<Match> getAllMatches() {
	return sessionFactory.getCurrentSession().createQuery("from Match").list();
}

@Override
public Match getMatch(int matchID) {
	return (Match) sessionFactory.getCurrentSession().createQuery("from Match WHERE matchId = " + String.valueOf(matchID)).uniqueResult();
}

@Override
public List<Player> getAllPlayer() {
	return sessionFactory.getCurrentSession().createQuery("from Player").list();
}

@Override
public List<Team> getAllTeam() {
	return sessionFactory.getCurrentSession().createQuery("from Team").list();
}

@Override
public List<NameSuper> getNameSupers() {
	return sessionFactory.getCurrentSession().createQuery("from NameSuper").list();
}

@Override
public List<TeamColor> getTeamColors() {
	return sessionFactory.getCurrentSession().createQuery("from TeamColor").list();
}

@Override
public List<Fixture> getFixtures() {
	return sessionFactory.getCurrentSession().createQuery("from Fixture").list();
}

@Override
public List<Player> getCurrentMatchPlayer(Match match) {
	return sessionFactory.getCurrentSession().createQuery("from Player WHERE teamId=" + String.valueOf(match.getHomeTeam().getTeamId()) + 
			" OR teamId=" + String.valueOf(match.getAwayTeam().getTeamId())).list(); 
}

}