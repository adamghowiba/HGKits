package me.technopvp.hgkits.listeners;

import me.technopvp.hgkits.utilities.user.UserUtils;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeath implements Listener {

	@EventHandler
	public void onPlayerDeathEvent(PlayerDeathEvent event) {
		Entity entity = event.getEntity();

		if (entity instanceof Player) {
			Player player = event.getEntity();

			/* Remove the users kit on death. */
			UserUtils.removeUserKit(player.getName());
		}
	}

}
