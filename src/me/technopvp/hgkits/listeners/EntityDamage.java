package me.technopvp.hgkits.listeners;

import me.technopvp.hgkits.HGKits;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class EntityDamage implements Listener {
	HGKits plugin = HGKits.instance;

	@EventHandler
	public void CancelFallDamage(EntityDamageEvent event) {
		Entity entity = event.getEntity();

		if (plugin.getConfig().getBoolean("take-fall-damage") == false) {
			if (entity instanceof Player) {
				if (event.getCause().equals(DamageCause.FALL)) {
					event.setCancelled(true);
				}
			}
		}
	}

}
