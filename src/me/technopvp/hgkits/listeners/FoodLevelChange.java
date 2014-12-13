package me.technopvp.hgkits.listeners;

import me.technopvp.hgkits.HGKits;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FoodLevelChange implements Listener {
	HGKits plugin = HGKits.instance;

	@EventHandler
	public void HungerLoseEvent(FoodLevelChangeEvent event) {
		Entity entity = event.getEntity();

		/* Check if the user would like them to lose hunger or not */
		if (plugin.getConfig().getBoolean("lose-hunger") == false) {

			/* Check if the entity is a player */
			if (entity instanceof Player) {

				/* Check if the food level is below the maxmium food level. */
				if (event.getFoodLevel() > 20) {
					event.setFoodLevel(20);
					return;
				}

				/* Disable food changing */
				event.setCancelled(true);
			}
		}
	}

}
