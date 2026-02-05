package controllers;
import dao.PlayerDAO;
import models.Player;

import java.util.List;

public class PlayerController {
    private PlayerDAO playerDAO;

    public PlayerController(PlayerDAO playerDAO) {
        this.playerDAO = playerDAO;
    }


    public void start() {
        boolean run = true;

        while (run) {

            int choice = player.View.startMenue();
            System.out.printf("=== Welcome ===");

            switch(choice) {
                case 1:
                    //Ajouter un joueur
                    String name = /*Faire une demande via la vue*/;
                    String team = /*idem*/;
                    Player player = new Player(null, null);
                    playerDAO.createPlayer(player);
                    //Ajouter message de confirmation
                    break;

                case 2:
                    //Afficher la liste complète des joueurs
                    List<player> players = playerDAO.readAllPlayerInfo();
                    break;

                case 3:
                    //Modifier un joueur (nom et / ou équipe via id joueur)
                    String newName = /*demande*/;
                    String newTeam = /*idem*/;
                    playerDAO.updatePlayer(newName, newTeam, 0);
                    break;

                case 4:
                    //Supprimer un joueur via l'id
                    int id = /* demander l'id via vue*/;
                    playerDAO.deletePlayer(id);
            }
        }
    }
}
