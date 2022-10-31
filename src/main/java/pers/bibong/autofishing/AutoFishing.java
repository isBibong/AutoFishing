package pers.bibong.autofishing;

import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import pers.bibong.autofishing.command.CommandMain;
import pers.bibong.autofishing.yaml.ConfigLoader;
import pers.bibong.autofishing.yaml.PlayerDataLoader;

public class AutoFishing extends JavaPlugin {
    private static AutoFishing plugin;
    private static String      prefix;

    public static ConfigLoader     config;
    public static PlayerDataLoader playerData;

    @Override
    public void onLoad () {
        AutoFishing.plugin = this;
    }

    @Override
    public void onEnable () {
        this.reload();
        this.regCommand();
        this.regListeners();
    }

    @Override
    public void onDisable () {

    }

    public void reload () {
        this.loadAllYaml();
    }

    private void loadAllYaml () {
        config     = new ConfigLoader();
        playerData = new PlayerDataLoader();

        String getPrefix = config.get().getString("Prefix");
        if (getPrefix == null) { throw new NullPointerException("請檢查 [config.yml], 找不到路徑 [Prefix]!"); }
        prefix = getPrefix;
    }

    private void regCommand () {
        PluginCommand autofishing = getCommand("autofishing");
        if (autofishing == null) { throw new NullPointerException("找不到指令 \"autofishing\""); }
        autofishing.setExecutor(new CommandMain());
    }

    private void regListeners () {
        getServer().getPluginManager().registerEvents(new Listeners(), this);
    }

    public static String getPrefix () {
        return prefix;
    }

    public static AutoFishing inst () {
        return plugin;
    }

    @Contract (pure = true)
    public static @NotNull String str_AdminPermission () {
        return "autofishing.admin";
    }

    @Contract (pure = true)
    public static @NotNull String str_UsePermission () {
        return "autofishing.use";
    }
}
