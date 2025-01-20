package com.NPLTicket.model;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents a match in the Nepal Premier League (NPL) related to ticketing.
 * 
 * @LMUID: 23048667
 * @author Pawan Pokhrel
 */
public class TicketMatchModel {

    // Unique match number.
    private int matchNo;

    // Teams playing in the match (e.g., "Team 1 vs Team 2").
    private String teams;

    // Date on which the match is scheduled.
    private LocalDate matchDate;

    // Time at which the match is scheduled to start.
    private LocalTime matchTime;

    /**
     * Default constructor for creating an empty TicketMatchModel object.
     * 
     * @LMUID: 23048667
     */
    public TicketMatchModel() {
    }

    /**
     * Parameterized constructor for initializing all the fields of the TicketMatchModel object.
     * 
     * @param matchNo   Match number.
     * @param teams     Teams playing in the match.
     * @param matchDate Date of the match.
     * @param matchTime Time of the match.
     * 
     * @LMUID: 23048667
     */
    public TicketMatchModel(int matchNo, String teams, LocalDate matchDate, LocalTime matchTime) {
        this.matchNo = matchNo;
        this.teams = teams;
        this.matchDate = matchDate;
        this.matchTime = matchTime;
    }

    /**
     * Retrieves the unique match number.
     * 
     * @return The match number.
     */
    public int getMatchNo() {
        return matchNo;
    }

    /**
     * Updates the unique match number.
     * 
     * @param matchNo The new match number.
     */
    public void setMatchNo(int matchNo) {
        this.matchNo = matchNo;
    }

    /**
     * Retrieves the teams playing in the match.
     * 
     * @return The teams.
     */
    public String getTeams() {
        return teams;
    }

    /**
     * Updates the teams playing in the match.
     * 
     * @param teams The new teams.
     */
    public void setTeams(String teams) {
        this.teams = teams;
    }

    /**
     * Retrieves the date of the match.
     * 
     * @return The match date.
     */
    public LocalDate getMatchDate() {
        return matchDate;
    }

    /**
     * Updates the date of the match.
     * 
     * @param matchDate The new match date.
     */
    public void setMatchDate(LocalDate matchDate) {
        this.matchDate = matchDate;
    }

    /**
     * Retrieves the time of the match.
     * 
     * @return The match time.
     */
    public LocalTime getMatchTime() {
        return matchTime;
    }

    /**
     * Updates the time of the match.
     * 
     * @param matchTime The new match time.
     */
    public void setMatchTime(LocalTime matchTime) {
        this.matchTime = matchTime;
    }
}
