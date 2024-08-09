package me.viann.bazzoka.listeners;

import me.viann.bazzoka.Bazzoka;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class BowFireRateListener implements Listener {

    private final Bazzoka plugin;

    public BowFireRateListener(Bazzoka plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBowUse(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

        // Check if the player is holding the special bow
        if (item.hasItemMeta() && item.getItemMeta().getDisplayName().equals(Bazzoka.getBowName())) {
            // Increase fire rate by scheduling arrow shoots faster
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (player.isHandRaised()) { // Checks if the player is still drawing the bow
                        Arrow arrow = player.launchProjectile(Arrow.class);

                        // Increase the power by multiplying the arrow's velocity
                        arrow.setVelocity(player.getLocation().getDirection().multiply(10)); // Adjust this multiplier to increase/decrease power

                        // Optionally set other properties like critical hits, fire, etc.
                        arrow.setCritical(true);
                    } else {
                        this.cancel();
                    }
                }
            }.runTaskTimer(plugin, 0, 5);  // Adjust the second parameter (ticks) to control the rate of fire
        }
    }

    @EventHandler
    public void onBowShoot(EntityShootBowEvent event) {
        // Cancel the default shoot event to prevent normal firing behavior
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            ItemStack item = event.getBow();

            if (item.hasItemMeta() && item.getItemMeta().getDisplayName().equals(Bazzoka.getBowName())) {
                event.setCancelled(true);
            }
        }
    }
}
