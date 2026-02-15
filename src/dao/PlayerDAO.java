package dao;

import models.Player;
import models.Team;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAO {

    private final DataSource dataSource;

    public PlayerDAO(DataSource datasource) {
        this.dataSource = datasource;
    }


    public void createPlayer(Player playerToAdd) {
        try {
            Connection con = dataSource.getConnection();
            String sql = """
                    INSERT INTO player (
                    name,
                    team,
                    score,
                    killCount,
                    deathCount
                    VALUES (?, ?, ?, ?, ?, ?)""";

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, playerToAdd.getName());
                ps.setObject(2, playerToAdd.getTeam().getTeamId());
                ps.setInt(3, playerToAdd.getScore());
                ps.setInt(4, playerToAdd.getPlayerId());
                ps.setInt(5, playerToAdd.getKillCount());
                ps.setInt(6, playerToAdd.getDeathCount());

                ps.executeUpdate();
                ps.close();
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Player> readAllPlayerInfo() {
        ArrayList<Player> players = new ArrayList<>();

        try {
            Connection con = dataSource.getConnection();
            Statement st = con.createStatement();
            String sql = """
                     SELECT 
                     p.name AS player_name,
                     t.name AS team_name,
                     t.tag AS team_tag,
                     t.teamId AS team_id,
                     t.foundedDate AS team_foundedDate,
                     p.score AS player_score,
                     p.playerId AS player_id,
                     p.killCount AS player_killCount,
                     p.deathCount AS player_deathCount
                     
                    FROM player AS p
                     LEFT JOIN team AS t
                     ON
                     p.teamIdFk = t.teamId""";

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String name = rs.getString("player_name");
                String team = rs.getString("team_name");
                String team_tag = rs.getString("team_tag");
                int team_id = rs.getInt("team_id");
                LocalDate team_date = rs.getObject("team_foundedDate", LocalDate.class);
                int score = rs.getInt("player_score");
                int playerId = rs.getInt("player_id");
                int killCount = rs.getInt("player_killCount");
                int deathCount = rs.getInt("player_deathCount");

                Team t = new Team(team, team_tag, team_date, team_id);
                Player playerToList = new Player(name, t, score, playerId, killCount, deathCount);
                players.add(playerToList);
            }
            con.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return players;
    }

    public Player findById(int id) {
        Player test = null;
        try {
            Connection con = dataSource.getConnection();
            String sql = """
                    SELECT
                    p.name AS player_name, p.score AS player_score, P.playerId as player_id, p.killCount AS player_kc, p.deathCount AS player_dc, t.name AS team_name, t.tag AS team_tag, t.teamId AS team_id
                    
                    FROM player AS p
                    LEFT JOIN team AS t 
                    ON p.teamIdFk = t.teamId
                    WHERE playerID = ?""";

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    String name = rs.getString("player_name");
                    int score = rs.getInt("player_score");
                    int playerId = rs.getInt("player_Id");
                    int killCount = rs.getInt("player_kc");
                    int deathCount = rs.getInt("player_dc");
                    String team = rs.getString("team_name");
                    String tag = rs.getString("team_tag");
                    int team_id = rs.getInt("team_id");
                    Team t1 = new Team(team, tag, null, team_id);
                    test = new Player(name, t1, score, playerId, killCount, deathCount);
                }
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return test;
    }

    public void updatePlayer(String newName, Team newTeam, int id) {

        try {
            Connection con = dataSource.getConnection();
            String sql = """
                    UPDATE player SET
                    name = ?, teamIdFk = ?
                    WHERE playerId = ?""";

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, newName);
                ps.setInt(2, newTeam.getTeamId());
                ps.setInt(3, id);

                ps.executeUpdate();
                ps.close();
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deletePlayer(int id) {

        try {
            Connection con = dataSource.getConnection();
            String sql = """
                    DELETE FROM player
                    WHERE
                    playerId = ?""";

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, id);

                ps.executeUpdate();
                ps.close();
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}

