package com.badminton.service;

import java.util.List;

import com.badminton.model.Match;
import com.badminton.model.Player;
import com.badminton.model.Team;

public interface BadmintonService {
  Player getPlayer(int player_id);
  Team getTeam(int team_id);
  List<Match> getAllMatches();
  Match getMatch(int matchID);
}