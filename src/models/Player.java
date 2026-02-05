package models;

import java.util.List;

public class Player {
    private String name;
    private String team;
    private int score;
    private int playerId;
    private int killCount;
    private int deathCount;

    public Player(String name, String team, int score, int playerId, int killCount, int deathCount) {
        this.name = name;
        this.team = team;
        this.score = score;
        this.playerId = playerId;
        this.killCount = killCount;
        this.deathCount = deathCount;
    }

    public Player(String name, String team) {
        this(name, team, 0, 0, 0, 0);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getKillCount() {
        return killCount;
    }

    public void setKillCount(int killCount) {
        this.killCount = killCount;
    }

    public int getDeathCount() {
        return deathCount;
    }

    public void setDeathCount(int deathCount) {
        this.deathCount = deathCount;
    }

    public double calculateKd() {

        double killCount = this.getKillCount();
        double deathCount = this.getDeathCount();
        if (deathCount == 0) {
            return killCount;
        }
        return killCount / deathCount;
    }
}
