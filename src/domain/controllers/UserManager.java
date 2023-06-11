package domain.controllers;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private static Map<String, String> users;
    private static final String userFile = "users.txt";

    public UserManager() {
        users = new HashMap<>();
        loadUsersFromFile();
    }

    public static boolean registerUser(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            return false;
        }
        users.put(username, password);
        saveUsersToFile();
        return true;
    }

    public static boolean authenticateUser(String username, String password) {
        String storedPassword = users.get(username);
        return storedPassword != null && storedPassword.equals(password);
    }

    private static void loadUsersFromFile() {
        File file = new File(userFile);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(userFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] userData = line.split(",");
                    if (userData.length == 2) {
                        users.put(userData[0], userData[1]);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void saveUsersToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(userFile))) {
            for (Map.Entry<String, String> user : users.entrySet()) {
                writer.write(user.getKey() + "," + user.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}