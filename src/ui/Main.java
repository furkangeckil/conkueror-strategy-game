package ui;

import domain.Controller;

public class Main {

	public static void main(String[] args) {
		LoginUI loginUI = new LoginUI();
		Controller controller = new Controller(null,null,null,null,null,loginUI);
		loginUI.showLoginScreen();
	}

}
