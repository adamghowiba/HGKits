package me.technopvp.hgkits.listeners;

import me.technopvp.hgkits.utilities.user.UserUtils;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeath implements Listener {

	@EventHandler
	public void onPlayerDeathEvent(PlayerDeathEvent event) {
		Player player = event.getEntity();
		Player killer = event.getEntity().getKiller();

		if (player instanceof Player) {
			/* Add the death to the player's file */
			UserUtils.addDeath(player.getName());

			/* Remove the users kit on death. */
			UserUtils.removeUserKit(player.getName());
			UserUtils.setKillstreak(player.getName(), 0);
		}
		if (killer instanceof Player) {
			/* Add the kill to the killer */
			UserUtils.addKill(killer.getName());
			UserUtils.setKillstreak(killer.getName(), UserUtils.getCurrentKillstreak(killer.getName()) + 1);
		}
	}
}
