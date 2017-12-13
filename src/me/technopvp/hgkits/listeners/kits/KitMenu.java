package me.technopvp.hgkits.listeners.kits;

import me.technopvp.common.managers.MessageManager;
import me.technopvp.common.utilities.StringUtils;
import me.technopvp.hgkits.Duel;
import me.technopvp.hgkits.HGKits;
import me.technopvp.hgkits.Lists;
import me.technopvp.hgkits.managers.KitManager;
import me.technopvp.hgkits.utilities.KitUtils;
import me.technopvp.hgkits.utilities.Lang;
import me.technopvp.hgkits.utilities.user.UserUtils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class KitMenu implements Listener {
	HGKits plugin = HGKits.instance;

	@EventHandler
	public void onOpenKitMenu(PlayerInteractEvent event) {
		Player player = event.getPlayer();

		if (player.getItemInHand().getType().equals(Material.COMPASS)) {
			if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
				player.openInventory(KitUtils.getKitMenu());
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onChoseKitFromMenu(InventoryClickEvent event) {
		if (event.getWhoClicked() instanceof Player) {
			Player player = (Player) event.getWhoClicked();
			Duel duel = new Duel(player, Bukkit.getPlayer(Lists.duelAppending.get(player.getName())), "pot1", true);

			if (event.getInventory().getName().equals(StringUtils.colorize(Lang.KIT_MENU.getMessage()))) {
				for (String kits : plugin.getConfig().getConfigurationSection("kits").getKeys(false)) {
					if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RESET + kits.toString())) {
						MessageManager.message(true, duel.getDuelingTarget(), "&aPlayer &6" + player.getName() + "&a has sent you a duel request with the kit &6" + duel.getKit());
						MessageManager.message(true, player, "&aYou have sent a duel request to &6" + duel.getDuelingTarget().getName() + "&a.");
						KitManager.giveKit(player, duel.getKit());
						KitManager.giveKit(duel.getDuelingTarget(), duel.getKit());
					}
				}
				player.closeInventory();
				event.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void closeFromChosingKit(InventoryCloseEvent event) {
		if (event.getPlayer() instanceof Player) {
			Player player = (Player) event.getPlayer();

			if (event.getInventory().getName().equals(StringUtils.colorize(Lang.KIT_MENU.getMessage())) && Lists.duelAppending.containsKey(player.getName())) {
				if (!UserUtils.userHasKit(player.getName())) {
					Lists.duelAppending.remove(player.getName());
					MessageManager.message(true, player, "&aYou must choose a kit, to duel with.");
				}
			}
		}
	}
}
