package com.NPLTicket.model;

/**
 * @LMUID 23048667
 * @author Pawan Pokhrel
 */
public class MatchModel
{
    
    private int matchNo;
    private String teams;
    private String matchDate;
    private String matchTime;
    private String matchStatus;
    private String matchSeats;
    
    public MatchModel () {}
    
    public MatchModel (int matchNo, String teams, String matchDate, String matchTime, String matchStatus, String matchSeats) {
        this.matchNo = matchNo;
        this.teams = teams;
        this.matchDate = matchDate;
        this.matchTime = matchTime;
        this.matchStatus = matchStatus;
        this.matchSeats = matchSeats;
    }

    public int getMatchNo() {
        return matchNo;
    }

    public void setMatchNo(int matchNo) {
        this.matchNo = matchNo;
    }

    public String getTeams() {
        return teams;
    }

    public void setTeams(String teams) {
        this.teams = teams;
    }

    public String getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(String matchDate) {
        this.matchDate = matchDate;
    }

    public String getMatchTime() {
        return matchTime;
    }

    public void setMatchTime(String matchTime) {
        this.matchTime = matchTime;
    }

    public String getMatchStatus() {
        return matchStatus;
    }

    public void setMatchStatus(String matchStatus) {
        this.matchStatus = matchStatus;
    }

    public String getMatchSeats() {
        return matchSeats;
    }

    public void setMatchSeats(String matchSeats) {
        this.matchSeats = matchSeats;
    }
    
}
