package ui;

import domain.controllers.Controller;

public class Main {

	public static void main(String[] args) {
		LoginUI loginUI = new LoginUI();
		Controller.getInstance().setLoginUI(loginUI);
		loginUI.showLoginScreen();
		//loginUI.showLoginScreen();
	}

}
