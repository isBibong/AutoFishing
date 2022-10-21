package pers.bibong.autofishing.utils;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import pers.bibong.autofishing.AutoFishing;

public class Messages {

    public static @NotNull String command_Reload () {
        String prefix = AutoFishing.config.get().getString("Prefix");
        String reload = AutoFishing.config.get().getString("CommandMessage.Reload");
        return ChatColor.color(prefix + reload);
    }

    public static @NotNull String command_NoPermission () {
        String prefix = AutoFishing.config.get().getString("Prefix");
        String reload = AutoFishing.config.get().getString("CommandMessage.NoPermission");
        return ChatColor.color(prefix + reload);
    }

    public static @NotNull String command_NotPlayer () {
        String prefix = AutoFishing.config.get().getString("Prefix");
        String reload = AutoFishing.config.get().getString("CommandMessage.NotPlayer");
        return ChatColor.color(prefix + reload);
    }

    public static @NotNull String command_Enabled_AutoFishing () {
        String prefix = AutoFishing.config.get().getString("Prefix");
        String reload = AutoFishing.config.get().getString("CommandMessage.Enabled_AutoFishing");
        return ChatColor.color(prefix + reload);
    }

    public static @NotNull String command_Disabled_AutoFishing () {
        String prefix = AutoFishing.config.get().getString("Prefix");
        String reload = AutoFishing.config.get().getString("CommandMessage.Disabled_AutoFishing");
        return ChatColor.color(prefix + reload);
    }

    public static void sendMsgToConsole (@NotNull JavaPlugin plugin, String s) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.color("&a[&6" + plugin.getName() + "&a]&r " + s));
    }

    public static void sendMsgToPlayer (@NotNull CommandSender sender, String s) {
        sender.sendMessage(ChatColor.color(s));
    }

}
