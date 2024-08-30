package io.phanisment.brutal.util;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class PlayerDataManager {

    private final File dataFile;
    private final FileConfiguration dataConfig;

    public PlayerDataManager(JavaPlugin plugin) {
        // Menginisialisasi file playerdata.yml
        dataFile = new File(plugin.getDataFolder(), "playerdata.yml");

        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        dataConfig = YamlConfiguration.loadConfiguration(dataFile);
    }

    public int getTotemUsage(UUID playerUUID) {
        return dataConfig.getInt("players." + playerUUID + ".totemUsage", 0);
    }

    public void setTotemUsage(UUID playerUUID, int usageCount) {
        dataConfig.set("players." + playerUUID + ".totemUsage", usageCount);
        saveDataConfig();
    }

    private void saveDataConfig() {
        try {
            dataConfig.save(dataFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
