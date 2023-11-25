package net.etum.vpnj.Events;

import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WeatherMessage {

    public static String action(Player player, YamlConfiguration config, String interaction){

        World world = player.getWorld();
        boolean isRaining = world.hasStorm();
        boolean isThundering = world.isThundering();

        String weatherValue = config.getString("INTERACTION." + interaction + ".VALUE");

        if (weatherValue != null) {
            if (weatherValue.equalsIgnoreCase("SUN") && !isRaining && !isThundering) {
                return getRandomMessage(config.getStringList("INTERACTION." + interaction + ".MESSAGE"));
            } else if (weatherValue.equalsIgnoreCase("RAIN") && isRaining && !isThundering) {
                return getRandomMessage(config.getStringList("INTERACTION." + interaction + ".MESSAGE"));
            } else if (weatherValue.equalsIgnoreCase("THUNDER") && isThundering) {
                return getRandomMessage(config.getStringList("INTERACTION." + interaction + ".MESSAGE"));
            }
        }

        return "Rien ne me vient à l'esprit";
    }


    private static String getRandomMessage(List<String> messages) {
        List<String> allMessages = new ArrayList<>(messages);

        Random random = new Random();
        int randomIndex = random.nextInt(allMessages.size());
        return allMessages.get(randomIndex);
    }

}
