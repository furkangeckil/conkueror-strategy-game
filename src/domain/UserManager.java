package domain;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private Map<String, String> users;
    private final String userFile = "users.txt";

    public UserManager() {
        users = new HashMap<>();
        loadUsersFromFile();
    }

    public void registerUser(String username, String password) {
        users.put(username, password);
        saveUsersToFile();
    }

    public boolean authenticateUser(String username, String password) {
        String storedPassword = users.get(username);
        return storedPassword != null && storedPassword.equals(password);
    }

    private void loadUsersFromFile() {
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

    private void saveUsersToFile() {
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