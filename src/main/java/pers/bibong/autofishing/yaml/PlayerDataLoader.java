package pers.bibong.autofishing.yaml;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import pers.bibong.autofishing.AutoFishing;

import java.io.File;

public class PlayerDataLoader {
    private File              file;
    private YamlConfiguration configuration;

    private final String fileName;
    private final Plugin plugin;

    public PlayerDataLoader () {
        this.plugin   = AutoFishing.inst();
        this.fileName = "playerData";
        this.load();
    }

    public void load () {
        this.file          = new File(this.plugin.getDataFolder().getPath(), File.separator + this.fileName + ".yml");
        this.configuration = new YamlConfiguration();

        if (! file.exists()) {
            try { this.plugin.saveResource(this.fileName + ".yml", false); }
            catch (IllegalArgumentException e) {
                this.plugin.getServer().getConsoleSender().sendMessage("取得失敗: [" + this.fileName + ".yml]");
            }
        }

        try { this.configuration = YamlConfiguration.loadConfiguration(file); }
        catch (Exception e) {
            this.plugin.getServer().getConsoleSender().sendMessage("加載失敗: [" + this.fileName + ".yml]");
        }
    }

    public boolean save () {
        try { this.configuration.save(file); }
        catch (Exception e) {
            this.plugin.getServer().getConsoleSender().sendMessage("儲存失敗: [" + this.fileName + ".yml]");
            return false;
        }
        return true;
    }

    public YamlConfiguration get () {
        return this.configuration;
    }
}
