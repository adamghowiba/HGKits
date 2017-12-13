package me.technopvp.hgkits.listeners;

import java.util.ArrayList;

import me.technopvp.hgkits.HGKits;
import me.technopvp.hgkits.utilities.user.UserUtils;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerDeath implements Listener {
	HGKits plugin = HGKits.instance;

	private ArrayList<ItemStack> droppedItemsList = new ArrayList<ItemStack>();

	@EventHandler
	public void onPlayerDeathEvent(final PlayerDeathEvent event) {
		final Player player = event.getEntity();
		Player killer = event.getEntity().getKiller();


		if (player instanceof Player) {
			/* Remove EXP no matter what */
			event.setDroppedExp(0);

			/* If they have a kit, remove there drops on death. */
			if (UserUtils.userHasKit(player.getName())) {
				/* Since Bukkit API removes the objects in the drops, we have to do all this bull sh*t. */
				for (ItemStack drops : event.getDrops()) {
					droppedItemsList.add(drops);
				}

				Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
					/* I guess someone doesn't like to update things. */
					@SuppressWarnings("deprecation")
					public void run() {
						for (Entity droppedEntity : player.getWorld().getEntities()) {
							if (droppedEntity instanceof Item) {
								if (droppedItemsList.contains(((Item) droppedEntity).getItemStack())) {
									droppedEntity.remove();
									for (Player allPlayers : Bukkit.getOnlinePlayers()) {
										allPlayers.playEffect(droppedEntity.getLocation(), Effect.SMOKE, 1);
									}
								}
							}
						}
					}
				}, 70L);
			}

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
