package view;

import models.Player;

import java.util.List;
import java.util.Scanner;

public class PlayerView {

    private Scanner scanner = new Scanner(System.in);

    public PlayerView() {
    }

    ;


    public int startMenue() {

        System.out.println("=== Welcome ===");
        System.out.println("1. Ajouter un joueur");
        System.out.println("2. Afficher les joueurs");
        System.out.println("3.Modifier un joueur");
        System.out.println("4. Supprimer un joueur");

        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }


    public void showMessage(String msg) {
        System.out.println(msg);
    }

    public void displayPlayer(List<Player> players) {
        if (players.isEmpty()) {
            System.out.println("Aucun joueur trouvé");
            return;
        }

        System.out.println("=== Liste des joueurs ===");
        for (Player p : players) {
            System.out.println("Nom :" + p.getName() + "Team :" + p.getTeam().getName() + "id joueur : " + p.getPlayerId() + "id team : " + p.getTeam().getTeamId());
        }
    }

    public void getKd(List<Player> players) {
        if (players.isEmpty()) {
            System.out.println("Aucun joueur trouvé");
            return;
        }

        System.out.println("=== Liste des joueurs avec leur Kd ===");
        for (Player p : players) {
            p.calculateKd();
            System.out.println("Nom : " + p.getName() + "Team : " + p.getTeam() + "kd : " + p.calculateKd());
        }
    }

    public String askName() {
        System.out.println("Entrez votre nom : ");
        String nom = scanner.nextLine();

        return nom;
    }

    public int askTeam() {
        //à changer ! Proposer la liste des équipe avec leur ID et demander de rentrer l'id concerné !!
        System.out.println("Entrez l'id votre team : ");
        int teamId = scanner.nextInt();

        return teamId;
    }

    public String askNewName() {
        System.out.println("Saisissez le nouveau nom : ");
        String newName = scanner.nextLine();

        return newName;
    }

    public String askNewTeamName() {
        System.out.println("Saisissez le nouveau nom de team : ");
        String newTeam = scanner.nextLine();

        return newTeam;
    }

    public int askDeletePlayer() {
        System.out.println("Entrez l'id du joueur que vous voulez supprimer : ");

        int toDeleteId = scanner.nextInt();
        return toDeleteId;
    }
}
