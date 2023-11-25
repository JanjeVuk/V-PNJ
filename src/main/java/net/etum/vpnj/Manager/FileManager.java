package net.etum.vpnj.Manager;

import net.etum.vpnj.Main;

import java.io.File;
import java.io.IOException;

public class FileManager {


    public static File getFile(int npcId) {
        File folder = new File("plugins//" + Main.getInstance().getName() + "//NPC//" + npcId);
        folder.mkdirs();

        File file = new File(folder, "data.yml");


        // Crée le fichier uniquement si des interactions existent
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return file;
    }



}
