package pers.bibong.autofishing.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pers.bibong.autofishing.AutoFishing;
import pers.bibong.autofishing.utils.Messages;

import java.util.ArrayList;
import java.util.List;

public class CommandMain implements TabExecutor {

    @Override
    public boolean onCommand (@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s,
                              @NotNull String[] strings) {

        if (strings.length == 0) {
            if (! (commandSender instanceof Player player)) {
                Messages.sendMsgToPlayer(commandSender, Messages.command_NotPlayer());
                return false;
            }

            if (! player.hasPermission(AutoFishing.str_UsePermission()) && ! player.hasPermission(
                    AutoFishing.str_AdminPermission())) {

                Messages.sendMsgToPlayer(commandSender, Messages.command_NoPermission());
                return false;
            }

            boolean aBoolean = AutoFishing.playerData.get().getBoolean(player.getUniqueId().toString());
            AutoFishing.playerData.get().set(player.getUniqueId().toString(), ! aBoolean);
            AutoFishing.playerData.save();

            if (! aBoolean) {
                Messages.sendMsgToPlayer(player, Messages.command_Enabled_AutoFishing());
            }
            else {
                Messages.sendMsgToPlayer(player, Messages.command_Disabled_AutoFishing());
            }

            return true;
        }

        if ("reload".equals(strings[0])) {
            if (! commandSender.hasPermission(AutoFishing.str_AdminPermission())) {
                Messages.sendMsgToPlayer(commandSender, Messages.command_NoPermission());
                return false;
            }

            AutoFishing.inst().reload();
            Messages.sendMsgToPlayer(commandSender, Messages.command_Reload());
        }

        return true;
    }

    @Nullable
    @Override
    public List<String> onTabComplete (@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s,
                                       @NotNull String[] strings) {
        List<String> tab = new ArrayList<>();

        switch (strings.length) {
            case 1 -> {
                if (! commandSender.hasPermission(AutoFishing.str_AdminPermission())) {
                    Messages.sendMsgToPlayer(commandSender, Messages.command_NoPermission());
                    return tab;
                }

                tab.add("reload");
            }
        }

        return tab;
    }

}
