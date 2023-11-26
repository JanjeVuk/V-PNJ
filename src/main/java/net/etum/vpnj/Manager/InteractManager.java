package net.etum.vpnj.Manager;

import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.etum.vpnj.Events.WeatherMessage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InteractManager implements Listener {

    @EventHandler
    public void onNPCRightClick(NPCRightClickEvent event) {
        Player player = event.getClicker();
        if (player == null) return;

        int npcId = event.getNPC().getId();
        File npcFile = FileManager.getFile(npcId);
        YamlConfiguration config = YamlConfiguration.loadConfiguration(npcFile);

        try {
            String message = getMessage(config, player);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', event.getNPC().getName() + " §7: " + message));
        } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
            Bukkit.getLogger().warning("Erreur lors de la récupération du message pour NPC ID " + npcId + ": " + e.getMessage());
        }
    }

    private String getMessage(YamlConfiguration config, Player player) {
        List<String> defaultMessages = getDefaultMessages(config);

        ConfigurationSection interactionSection = config.getConfigurationSection("INTERACTION");
        if (interactionSection != null) {
            for (String key : interactionSection.getKeys(false)) {
                if (!key.equalsIgnoreCase("DEFAULT")) {
                    String type = interactionSection.getString(key + ".TYPE");
                    if(type == null)continue;
                    switch (type){
                        case "WEATHER":
                            return WeatherMessage.action(player, config, key);
                        default:
                            break;
                    }
                }
            }
        }

        return getRandomMessage(defaultMessages);
    }

    private String getRandomMessage(List<String> messages) {
        List<String> allMessages = new ArrayList<>(messages);

        Random random = new Random();
        int randomIndex = random.nextInt(allMessages.size());
        return allMessages.get(randomIndex);
    }

    private List<String> getDefaultMessages(YamlConfiguration config) {
        List<String> allDefaultMessages = new ArrayList<>();

        ConfigurationSection interactionSection = config.getConfigurationSection("INTERACTION");
        if (interactionSection != null) {
            for (String key : interactionSection.getKeys(false)) {
                if (config.getString("INTERACTION." + key + ".TYPE").equalsIgnoreCase("DEFAULT")) {
                    List<String> defaultMessages = config.getStringList("INTERACTION." + key + ".MESSAGE");
                    allDefaultMessages.addAll(defaultMessages);
                }
            }
        }

        if (allDefaultMessages.isEmpty()) {
            allDefaultMessages.add("Rien ne me vient à l'esprit"); // Message par défaut
        }

        return allDefaultMessages;
    }
}
