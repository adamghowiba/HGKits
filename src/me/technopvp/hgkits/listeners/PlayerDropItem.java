package me.technopvp.hgkits.listeners;

import me.technopvp.hgkits.HGKits;
import me.technopvp.hgkits.managers.MessageManager;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerDropItem implements Listener {
	HGKits plugin = HGKits.instance;

	@EventHandler
	public void PlayerDropItemEvent(PlayerDropItemEvent event) {
		Player player = event.getPlayer();

		if (plugin.getConfig().getBoolean("player-drop-item") == false) {
			if (!player.isOp()) {
				ItemStack dropedItem = event.getItemDrop().getItemStack();

				if (!dropedItem.getType().equals(Material.MUSHROOM_SOUP) || !dropedItem.getType().equals(Material.BOWL)) {
					MessageManager.message(true, player, "&aYou can only drop items with '/drop'");
					event.setCancelled(true);
				}
			}
		}
	}

}
