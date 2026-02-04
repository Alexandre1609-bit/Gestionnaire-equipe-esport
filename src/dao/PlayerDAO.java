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


    public void creatPlayer(Player playerToAdd) {
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
}

