package net.etum.vpnj;

import net.etum.vpnj.Listeners.Commands;
import net.etum.vpnj.Listeners.Events;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import static org.bukkit.Bukkit.getVersion;

public final class Main extends JavaPlugin {

    private static Economy econ = null;
    private static Permission perms = null;
    private static Chat chat = null;
    private static Main instance;

    @Override
    public void onDisable() {
        getLogger().info(String.format("[%s] Disabled", getVersion()));
    }

    @Override
    public void onEnable() {
        if (!setupEconomy() ) {
            getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }


        instance = this;
        setupPermissions();
        setupChat();


        new Events(this);
        new Commands(this);
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return true;
    }

    private void setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        assert rsp != null;
        chat = rsp.getProvider();
    }

    private void setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        assert rsp != null;
        perms = rsp.getProvider();
    }


    public static Economy getEconomy() {
        return econ;
    }

    public static Permission getPermissions() {
        return perms;
    }

    public static Chat getChat() {
        return chat;
    }

    public static Main getInstance() {
        return instance;
    }
}
