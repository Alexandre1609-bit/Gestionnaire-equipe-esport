package dao;

import models.Player;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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
                    playerId,
                    killCount,
                    deathCount
                    VALUES (?, ?, ?, ?, ?, ?)""";

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, playerToAdd.getName());
                ps.setString(2, playerToAdd.getTeam());
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
                    SELECT * FROM player""";

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String name = rs.getString("name");
                String team = rs.getString("team");
                int score = rs.getInt("score");
                int playerId = rs.getInt("playerId");
                int killCount = rs.getInt("killCount");
                int deathCount = rs.getInt("deathCount");

                Player playerToList = new Player(name, team, score, playerId, killCount, deathCount);
                players.add(playerToList);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return players;
    }

    public Player getPlayerInfo(int id) {
        Player test = null;
        try {
            Connection con = dataSource.getConnection();
            String sql = """
                    SELECT
                    name, team
                    WHERE playerID = ?""";

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    String name = rs.getString("name");
                    String team = rs.getString("team");
                    test = new Player(name, team, 0, 0, 0, 0);
                }
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return test;
    }

    public void updatePlayer(String newName, String newTeam, int id) {

        try {
            Connection con = dataSource.getConnection();
            String sql = """
                    UPDATE player SET
                    name = ?, team = ?
                    WHERE playerId = ?""";

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, newName);
                ps.setString(2, newTeam);
                ps.setInt(3, id);

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

                ps.close();
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

