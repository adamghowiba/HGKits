package me.technopvp.hgkits.listeners.kits;

import me.technopvp.common.utilities.StringUtils;
import me.technopvp.common.utilities.Utils;
import me.technopvp.hgkits.HGKits;
import me.technopvp.hgkits.managers.KitManager;
import me.technopvp.hgkits.utilities.Lang;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class KitMenu implements Listener {
	HGKits plugin = HGKits.instance;

	@EventHandler
	public void onOpenKitMenu(PlayerInteractEvent event) {
		Player player = event.getPlayer();

		Inventory kitMenu = Bukkit.createInventory(null, 18, StringUtils.colorize(Lang.KIT_MENU.getMessage()));

		if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			for (String kit : plugin.getConfig().getConfigurationSection("kits").getKeys(false)) {
				ItemStack wood = new ItemStack(Material.WOOD);
				Utils.setName(wood, ChatColor.RESET + kit.toString());

				kitMenu.addItem(wood);
			}
			player.openInventory(kitMenu);
		}
	}

	@EventHandler
	public void onChoseKitFromMenu(InventoryClickEvent event) {
		if (event.getWhoClicked() instanceof Player) {
			Player player = (Player) event.getWhoClicked();

			if (event.getInventory().getName().equals(StringUtils.colorize(Lang.KIT_MENU.getMessage()))) {
				for (String kits : plugin.getConfig().getConfigurationSection("kits").getKeys(false)) {
					if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RESET + kits.toString())) {
						KitManager.giveKit(player, kits);
					}
				}
				event.setCancelled(true);
			}
		}
	}

}
