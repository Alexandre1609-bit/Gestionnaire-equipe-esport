package dao;

import models.Team;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TeamDAO {

    private final DataSource dataSource;

    public TeamDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void createTeam(Team teamtoAdd) {

        try {
            Connection con = dataSource.getConnection();
            String sql = """
                    INSERT INTO team (
                    name,
                    tag,
                    date,
                    VALUES (?, ?, ?, ?)""";

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, teamtoAdd.getName());
                ps.setString(2, teamtoAdd.getTag());
                ps.setObject(3, teamtoAdd.getFoundedDate());
                ps.setInt(4, teamtoAdd.getTeamId());

                ps.executeUpdate();
                ps.close();
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Team> showTeaminfo() {
        List<Team> teams = new ArrayList<>();

        try {
            Connection con = dataSource.getConnection();
            Statement st = con.createStatement();
            String sql = """
                    SELECT *
                    FROM
                    team
                    """;

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String name = rs.getString("name");
                String team = rs.getString("team");
                LocalDate foundedDate = rs.getObject("fondedDate", LocalDate.class);
                int teamId = rs.getInt("teamId");

                Team teamToList = new Team(name, team, foundedDate, teamId);
                teams.add(teamToList);
            }
            rs.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return teams;
    }

    public void updateTeam(String newName, String newTag) {

        try {
            Connection con = dataSource.getConnection();
            String sql = """
                    
                    UPDATE team 
                    SET name = ?, 
                    tag = ?
                    WHERE teamId = ?
                    """;
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, newName);
                ps.setString(2, newTag);

                ps.close();
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteTeam(int idToDelete) {

        try {
            Connection con = dataSource.getConnection();
            String sql = """
                        DELETE team
                        WHERE teamId = ?""";

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, idToDelete);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
