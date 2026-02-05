package controllers;
import dao.PlayerDAO;
import models.Player;
import view.PlayerView;

import javax.swing.text.View;
import java.util.ArrayList;
import java.util.List;

public class PlayerController {
    private PlayerDAO playerDAO;
    private PlayerView playerView;

    public PlayerController(PlayerDAO playerDAO, PlayerView playerView) {
        this.playerDAO = playerDAO;
        this.playerView = playerView;
    }


    public void start() {
        boolean run = true;

        while (run) {

            int choice = playerView.startMenue();

            switch(choice) {
                case 1:
                    //Ajouter un joueur
                    String name = playerView.askName();
                    String team = playerView.askTeam();
                    Player player = new Player(name, team);
                    playerDAO.createPlayer(player);
                    playerView.showMessage("Joueur ajouté avec succès");
                    break;

                case 2:
                    //Afficher la liste complète des joueurs
                    List<Player> players = playerDAO.readAllPlayerInfo();
                    playerView.displayPlayer(players);

                    break;

                case 3:
                    //Afficher le kd des joueurs
                    List<Player> p = playerDAO.readAllPlayerInfo();
                    playerView.getKd(p);
                    break;

                case 4:
                    //Modifier un joueur (nom et / ou équipe via id joueur)

                    String newName = playerView.askNewName();
                    String newTeam = playerView.askNewTeamName();
                    playerDAO.updatePlayer(newName, newTeam, 0);
                    break;

                case 5:
                    //Supprimer un joueur via l'id
                    List<Player> p2d = playerDAO.readAllPlayerInfo();
                    playerView.displayPlayer(p2d);
                    int playerToDeleteId = playerView.askDeletePlayer();
                    playerDAO.deletePlayer(playerToDeleteId);


            }
        }
    }
}
