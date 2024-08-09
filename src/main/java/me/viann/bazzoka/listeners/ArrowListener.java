package me.viann.bazzoka.listeners;

import me.viann.bazzoka.Bazzoka;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.World;

public class ArrowListener implements Listener {
    @EventHandler
    public void onArrowHit(ProjectileHitEvent event) {
        // Check if the projectile is an arrow
        if (event.getEntity() instanceof Arrow) {
            Arrow arrow = (Arrow) event.getEntity();

            // Check if the shooter is a player
            if (arrow.getShooter() instanceof Player) {
                Player player = (Player) arrow.getShooter();
                ItemStack bow = player.getInventory().getItemInMainHand();

                // Check if the bow has the special name (metadata)
                if (bow.hasItemMeta()) {
                    ItemMeta meta = bow.getItemMeta();
                    if (meta != null && meta.getDisplayName().equals(Bazzoka.getBowName())) {
                        World world = arrow.getWorld();

                        // Create an explosion at the arrow's location
                        world.createExplosion(arrow.getLocation(), 50.0F);

                        // Remove the arrow after the explosion
                        arrow.remove();
                    }
                }
            }
        }
    }
}
