package controllers;
import dao.PlayerDAO;
import models.Player;
import models.Team;
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
                    int teamId = playerView.askTeam();
                    String teamName = team.name.getById(id) ... //Logique du genre ? Faire la méthode dans le teamDAO
                    Player player = new Player(name,teamName);
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
                    Team t1 = new Team(null, null, null);
                    String newName = playerView.askNewName();
                    String  newTeam = playerView.askNewTeamName();
                    t1.setName(newTeam);
                    playerDAO.updatePlayer(newName, t1, 0);
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
