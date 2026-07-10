package com.vqru.nodrop;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class NoDropDestroy extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("NoDropDestroy enabled — dropped items are now immune to TNT explosion damage.");
    }

    @EventHandler(ignoreCancelled = true)
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        Entity victim = event.getEntity();
        if (!(victim instanceof Item)) {
            return;
        }

        if (event.getDamager() instanceof TNTPrimed) {
            event.setCancelled(true);
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onEntityDamage(EntityDamageEvent event) {
        if (event instanceof EntityDamageByEntityEvent) {
            return;
        }

        if (!(event.getEntity() instanceof Item)) {
            return;
        }

        if (event.getCause() == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION) {
            event.setCancelled(true);
        }
    }
}
