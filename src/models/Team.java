package models;

import java.time.LocalDate;

public class Team {
    private String name;
    private String tag;
    private LocalDate foundedDate;
    private int teamId;

    public Team(String name, String tag, LocalDate foundedDate, int teamId) {
        this.name = name;
        this.tag = tag;
        this.foundedDate = foundedDate;
        this.teamId = teamId;
    }

    public Team(String name, String tag, LocalDate foundedDate) {
        this(name, tag, foundedDate, 0);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public LocalDate getFoundedDate() {
        return foundedDate;
    }

    public void setFoundedDate(LocalDate foundedDate) {
        this.foundedDate = foundedDate;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }
}
