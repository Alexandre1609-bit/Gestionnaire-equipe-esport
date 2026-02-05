package controllers;
import dao.PlayerDAO;

public class playerController {
    private PlayerDAO playerDAO;

    public playerController(PlayerDAO playerDAO) {
        this.playerDAO = playerDAO;
    }
}
