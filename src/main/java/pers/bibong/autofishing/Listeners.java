package pers.bibong.autofishing;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerFishEvent.State;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.InvocationTargetException;

public class Listeners implements Listener {

    @EventHandler
    public void onFishing (@NotNull PlayerFishEvent event) {
        if (event.isCancelled()) { return; }

        Player player = event.getPlayer();

        if (! AutoFishing.playerData.get().getBoolean(player.getUniqueId().toString())) { return; }

        if (event.getState() == State.BITE || event.getState() == State.CAUGHT_FISH) {
            long delay = 5L;
            if (event.getState() == State.BITE) {
                AutoFishing.config.get().getLong("AutoFishingSetting.Ticks_After_Bitten");
            }
            else {
                AutoFishing.config.get().getLong("AutoFishingSetting.Ticks_After_Caught");
            }

            new BukkitRunnable() {
                @Override
                public void run () {

                    InteractionHand hand = getHand(player);
                    if (hand == null) { return; }

                    doRightClick(player, hand);

                }
            }.runTaskLater(AutoFishing.inst(), delay);
        }
    }

    private void doRightClick (@NotNull Player player, InteractionHand hand) {
        ServerPlayer serverPlayer;
        try {
            serverPlayer = (ServerPlayer) player.getClass().getMethod("getHandle").invoke(player);
        }
        catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
            return;
        }
        serverPlayer.gameMode.useItem(serverPlayer, serverPlayer.getLevel(), serverPlayer.getItemInHand(hand), hand);
        serverPlayer.swing(hand, true);
    }

    private @Nullable InteractionHand getHand (@NotNull Player player) {


        if (player.getInventory().getItemInMainHand().getType().equals(Material.FISHING_ROD)) {
            return InteractionHand.MAIN_HAND;
        }
        if (player.getInventory().getItemInOffHand().getType().equals(Material.FISHING_ROD)) {
            return InteractionHand.OFF_HAND;
        }
        return null;
    }

}
