package domain;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class User {
	// This part is added	
	private int numOfWins;
    private int numOfLoses;
    private float averagePlacement;

    public User() {
        this.numOfWins = 0;
        this.numOfLoses = 0;
        this.averagePlacement = 0;
    }

    public int getNumOfWins() {
        return numOfWins;
    }

    public void incrementNumOfWins() {
        this.numOfWins += 1;
    }

    public int getNumOfLoses() {
        return numOfLoses;
    }

    public void incrementNumOfLoses() {
        this.numOfLoses += 1;
    }

    public float getAveragePlacement() {
        return averagePlacement;
    }

    public void updateAveragePlacement(float newPlacement) {
        float totalPlacements = numOfWins + numOfLoses;
        float currentTotalPlacementValue = averagePlacement * totalPlacements;
        float newTotalPlacementValue = currentTotalPlacementValue + newPlacement;
        this.averagePlacement = newTotalPlacementValue / (totalPlacements + 1);
    }
	
	
	// This part is added
	
	
	
	
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void register() throws IOException {
        BufferedWriter br = new BufferedWriter(new FileWriter("credentials.txt"));
        br.close();
    }

    public boolean login() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("credentials.txt"));
        String storedUsername = br.readLine();
        String storedPassword = br.readLine();
        br.close();

        return username.equals(storedUsername) && password.equals(storedPassword);
    }

    public void logout() {

    }
}