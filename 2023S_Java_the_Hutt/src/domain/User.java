package domain;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class User {
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