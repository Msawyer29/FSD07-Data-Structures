package Day01PlayersTeams;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TeamMembers {
    public static void main(String[] args) {
        String filePath = "Day01PlayersTeams/teams.txt";
        Map<String, String> playersAndTeams = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] splitLine = line.split(":");
                String teamName = splitLine[0].trim();
                String[] players = splitLine[1].trim().split(",");

                for (String player : players) {
                    String existingTeams = playersAndTeams.getOrDefault(player, "");
                    playersAndTeams.put(player, existingTeams.isEmpty() ? teamName : existingTeams + ", " + teamName);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }

        for (Map.Entry<String, String> entry : playersAndTeams.entrySet()) {
            System.out.println(entry.getKey() + " plays in: " + entry.getValue());
        }
    }
}
