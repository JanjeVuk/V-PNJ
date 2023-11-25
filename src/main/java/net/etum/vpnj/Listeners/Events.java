package net.etum.vpnj.Listeners;


import net.etum.vpnj.Main;
import net.etum.vpnj.Manager.InteractManager;
import org.bukkit.plugin.PluginManager;

public class Events {


    public Events(Main main) {

        PluginManager pm = main.getServer().getPluginManager();

        pm.registerEvents(new InteractManager(), main);


    }





}
