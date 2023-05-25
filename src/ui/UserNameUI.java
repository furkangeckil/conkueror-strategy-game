package ui;

import domain.Controller;
import domain.GameState;
import domain.Territory;
import domain.TerritoryController;

import java.util.List;

public class UserNameUI {

    private Controller controller;

    public UserNameUI(Controller controller) {
        this.controller = controller;
    }

    public void startLoginProcess(GameState gameState) {
        List<String> nicknames = controller.showLogin(gameState);
        TerritoryController territoryController = new TerritoryController();
        Territory[] allTerritories = territoryController.getAllTerritories();
        BoardUI.showBoard(GameState.getNumContinents(), nicknames, allTerritories);
    }
}