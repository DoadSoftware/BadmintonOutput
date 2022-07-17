package com.badminton.dao;

import java.util.List;

import com.badminton.model.Match;
import com.badminton.model.Player;
import com.badminton.model.Team;
import com.badminton.model.NameSuper;

public interface BadmintonDao {
  Player getPlayer(int player_id);
  Team getTeam(int team_id);
  List<Match> getAllMatches();
  Match getMatch(int matchID);
  List<Player> getAllPlayer();
  List<Team> getAllTeam();
  List<NameSuper> getNameSupers();
}