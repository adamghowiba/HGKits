package me.technopvp.hgkits.managers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import me.technopvp.common.api.fancymessage.FancyMessage;
import me.technopvp.common.utilities.StringUtils;
import me.technopvp.common.utilities.enums.Level;
import me.technopvp.common.utilities.player.InventoryUtils;
import me.technopvp.hgkits.HGKits;
import me.technopvp.hgkits.utilities.Lang;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

public class KitManager {
	static HGKits plugin = HGKits.instance;

	/**
	 *
	 * Create a kit that is saved in the configeration file. <br>
	 * The kit will be checking for the item display name, lore, and any enchantments. <br>
	 * Method will handle all checking, and messaging.
	 *
	 * @param player - The player that will be messaged apon creating the kit.
	 * @param kitName - The kit name that will be input into the configeration file.
	 *
	 * @see KitManager#createKit(Player, String)
	 */

	public static void createKit(Player player, String kitName) {
		FileConfiguration config = plugin.getConfig();
		PlayerInventory playerInventory = player.getInventory();

		/* Check if the kit has any items, if it doesn't tell them */
		if (config.getConfigurationSection("kits." + kitName) != null) {
			MessageManager.message(true, player, "&aKit '" + ChatColor.GOLD + kitName + "&a'" + " is alredy spesficed in the configeration file.");
			MessageManager.log(Level.MEDIUM, player.getName() + " has recived an error while trying to get the kit '" + kitName + "'.");
			return;
		}

		/* Create the section for kits, since it will be used often */
		String path = "kits." + kitName + ".";
		config.createSection("kits." + kitName);

		/* Iterate through the loop to check if there inventory slot has an item. */
		for (int i = 0; i < 36; i++) {
			ItemStack playerItems = playerInventory.getItem(i);

			/* Check if it's a nulled item, or if it's air. If it is, don't do anything */
			if (playerItems == null || playerItems.getType() == Material.AIR) continue;

			/* Get the path of the item, that we are setting */
			String slot = path + "items." + i;

			/* Set the inventory item in the configeration file */
			config.set(slot + ".type", playerItems.getType().toString().toLowerCase());
			config.set(slot + ".amount", playerItems.getAmount());

			/* If item has no meta ignore this part */
			if (!playerItems.hasItemMeta()) continue;

			/* Check if the item has a display name, if it does add it to the item in the configeration file. */
			if (playerItems.getItemMeta().hasDisplayName()) config.set(slot + ".name", playerItems.getItemMeta().getDisplayName());

			/* Check if the item has a lore, if it does add it to the item in the configeration file */
			if (playerItems.getItemMeta().hasLore()) config.set(slot + ".lore", playerItems.getItemMeta().getLore());

			/* If it has an enchantment add it as well */
			if (playerItems.getItemMeta().hasEnchants()) {
				Map<Enchantment, Integer> enchants = playerItems.getEnchantments();
				List<String> enchantList = new ArrayList<String>();
				for (Enchantment e : playerItems.getEnchantments().keySet()) {
					int level = enchants.get(e);
					enchantList.add(e.getName().toLowerCase() + ":" + level);
				}
				config.set(slot + ".enchants", enchantList);
			}
			continue;
		}

		/* For reading purposes check if the arumor is null(air) if it is set it to air */

		/* Check the helmet if it existes, or if it's air? */
		config.set(path + "armor.helmet", playerInventory.getHelmet() != null ? playerInventory.getHelmet().getType().toString().toLowerCase() : "air");

		/* Check the chestplate if it existes, or if it's air? */
		config.set(path + "armor.chestplate", playerInventory.getChestplate() != null ? playerInventory.getChestplate().getType().toString().toLowerCase() : "air");

		/* Check the leggings if it existes, or if it's air? */
		config.set(path + "armor.leggings", playerInventory.getLeggings() != null ? playerInventory.getLeggings().getType().toString().toLowerCase() : "air");

		/* Check the boots if it existes, or if it's air? */
		config.set(path + "armor.boots", playerInventory.getBoots() != null ? playerInventory.getBoots().getType().toString().toLowerCase() : "air");

		/* Message the player the kit was created */
		MessageManager.message(true, player, "&aYou have created the kit '" + ChatColor.GOLD + kitName + "&a'.");

		/* Save the kit to the configeration file */
		plugin.saveConfig();
	}

	/**
	 *
	 * Give a kit to a player specified, by checking lore, dispaly name, and enchantments. <br>
	 * If the kit has no items, it will tell the player the kit is nulled, and has '0' items. <br>
	 * Method handle's all checking, and messaging.
	 *
	 * @param player - The player that will be receiving the kit
	 * @param kitName - The kit name that will be grabbed, and retrieved from the configeration file.
	 *
	 * @see KitManager#giveKit(Player, String)
	 */

	@SuppressWarnings("deprecation")
	public static void giveKit(Player player, String kitName) {
		FileConfiguration config = plugin.getConfig();

		/* Check if the kit is disabled, if it is don't allow them to receive the kit. */
		if (kitDisabled(kitName) == true) {
			MessageManager.message(true, player, "&aThis kit is currently disabled.");
			player.playSound(player.getLocation(), Sound.HORSE_LAND, 1, 5);
			return;
		}

		/* Check if the kit existes in the configeration file, if it doesn't tell them */
		if (config.getConfigurationSection("kits." + kitName) == null) {
			MessageManager.message(true, player, "&aKit '" + ChatColor.GOLD + kitName + "&a' does not exist in the confieration file.");
			return;
		}

		/* Clear the players inventory, to allow room for the kit */
		InventoryUtils.clearLoadout(player);


		/* Get the configeration path for the kit */
		String kitPath = "kits." + kitName + ".";

		/* Get the configeration keys, which we will eventually loop through */
		ConfigurationSection configerationKey = config.getConfigurationSection(kitPath + "items");

		/* Check if the keys are equal to null, so if the kit has no items */
		if (configerationKey == null) {
			MessageManager.message(true, player, "&aKit '&6" + kitName + "&a' has no items set in the configeration file.");
			return;
		}

		/* Loop through the configeration key and get the items. Check for lore, name, and enchantments */
		for (String items : configerationKey.getKeys(false)) {
			/* Get the item slot, and parse it */
			int slot = Integer.parseInt(items);

			/* Check if the slot is above 0, and below 36. A players default inventory */
			if (0 > slot && slot > 36) return;

			/* Grab the kits path, for writing purposes */
			String string = kitPath + "items." + slot + ".";

			/* Grab the kit path's type (the item) */
			String type = config.getString(string + "type");

			/* Grab the kit path's name. (the display name */
			String name = config.getString(string + "name");

			/* Grab the kit path's lore. (the lore on the item) */
			List<String> lore = config.getStringList(string + "lore");

			/* Grab the kit path's enchantments */
			List<String> enchants = config.getStringList(string + "enchants");

			/* Get the amount of the item */
			int amount = config.getInt(string + "amount");

			/* Get the item stack by matching it with the type */
			ItemStack kitItemStack = new ItemStack(Material.matchMaterial(type.toUpperCase()), amount);

			/* Get the item meta from the item meta in the configeration file */
			ItemMeta kitItemMeta = kitItemStack.getItemMeta();

			/* Check if the kit has an item meta, and it's not equal to null */
			if (kitItemMeta == null) continue;

			/* Check if the name is not equal to null, translate color codes */
			if (name != null) kitItemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));

			/* Check if the lore is not equal to null */
			if (lore != null) kitItemMeta.setLore(Arrays.asList(ChatColor.translateAlternateColorCodes('&', lore.toString())));

			/* Check the enchantments onthe kit, and if there not null */
			if (enchants != null) {
				/* Loop through the enchantments */
				for (String s1 : enchants) {
					String[] indiEnchants = s1.split(":");
					kitItemMeta.addEnchant(Enchantment.getByName(indiEnchants[0].toUpperCase()), Integer.parseInt(indiEnchants[1]), true);
				}
			}

			/* Set the item meta on the item that was added */
			kitItemStack.setItemMeta(kitItemMeta);

			/* Set each slot to the item in the configeration file */
			player.getInventory().setItem(slot, kitItemStack);
		}

		/* Get the helmet from the configeration file */
		String helmet = config.getString(kitPath + "armor.helmet").toUpperCase();

		/* Get the chestplate from the configeration file */
		String chestplate = config.getString(kitPath + "armor.chestplate").toUpperCase();

		/* Get the leggings from the configeration file */
		String leggings = config.getString(kitPath + "armor.leggings").toUpperCase();

		/* Get the boots from the configeration file */
		String boots = config.getString(kitPath + "armor.boots").toUpperCase();

		/* Check if the helmet is equal to null, if it is set the armor type to air. */
		player.getInventory().setHelmet(new ItemStack(helmet != null ? Material.matchMaterial(helmet) : Material.AIR));

		/* Check if the chestplate is equal to null, if it is set the armor type to air. */
		player.getInventory().setChestplate(new ItemStack(chestplate != null ? Material.matchMaterial(chestplate) : Material.AIR));

		/* Check if the leggings is equal to null, if it is set the armor type to air. */
		player.getInventory().setLeggings(new ItemStack(leggings != null ? Material.matchMaterial(leggings) : Material.AIR));

		/* Check if the boots is equal to null, if it is set the armor type to air. */
		player.getInventory().setBoots(new ItemStack(boots != null ? Material.matchMaterial(boots) : Material.AIR));

		/* We are all done, update the players inventory with the new kit */
		player.updateInventory();

		/* Message the player that they have the new kit */
		MessageManager.message(true, player, "&aYou have recived the kit '" + ChatColor.GOLD + kitName + "&a'.");
	}

	/**
	 *
	 * If the kit exists, remove it from the configeration file.
	 *
	 * @param player - The player that is removing the kit.
	 * @param kitName - The name of the kit that is being removed.
	 *
	 * @see KitManager#removeKit(Player, String)
	 */

	public static void removeKit(Player player, String kitName) {
		if (plugin.getConfig().contains("kits." + kitName)) {
			plugin.getConfig().set("kits." + kitName, null);
			MessageManager.message(player, "&aYou have removed the kit '&6" + kitName + "&a'.");
			plugin.saveConfig();
		} else {
			MessageManager.message(true, player, "&aThe kit '&6" + kitName + "&a' doesn't exist in the configeration file.");
			return;
		}
	}

	/**
	 * Disable a kit in the configeration file by adding a sub line saying the kit is disabled. <br>
	 * If the sub line exists, then it's disabled. If it's not there it's enabled. <br>
	 * It checks if the kit is alredy disabled. If the kit's disabled it tells them. If not it disables the kit.
	 *
	 *
	 * @param playe - The player that's disabling the kit.
	 * @param kitName - The name of the kit that's being disabled.
	 *
	 * @see KitManager#disableKit(Player, String)
	 */

	public static void disableKit(Player player, String kitName) {
		if (kitDisabled(kitName) == true) {
			new FancyMessage(StringUtils.colorize(Lang.PREFIX.getMessage() + "&aThe kit '&6" + kitName + "&a' is already disabled. Click here to re-enable it.")).tooltip("&aClick here to re-enable this kit.").command("/kit enable " + kitName).send(player);
			return;
		} else {
			plugin.getConfig().set("kits." + kitName + ".disabled", true);
			MessageManager.message(true, player, "&aYou have disabled the kit '&6" + kitName + "&a'.");
			plugin.saveConfig();
		}
	}

	/**
	 *
	 * Enable a kit in the configeration file by removing the sub line 'disabled: value'. <br>
	 * If the kit is disabled it will remove it, if it's alredy enabled it will return, and tell the player. <br>
	 * Method does checking, no need to check out side of method.
	 *
	 * @param player - The player that's enabling the kit.
	 * @param kitName - The name of the kit that's being enabled.
	 *
	 * @see KitManager#enableKit(Player, String)
	 */

	public static void enableKit(Player player, String kitName) {
		if (kitDisabled(kitName) == false) {
			new FancyMessage(Lang.PREFIX.getMessage() + ChatColor.GREEN + "The kit '" + ChatColor.GOLD + kitName + ChatColor.GREEN + "' is already enabled. Click here " + ChatColor.GREEN + "to disable it.").tooltip(ChatColor.GREEN + "Click here to disable this kit.").command("/kit disable " + kitName).send(player);
			return;
		} else {
			plugin.getConfig().set("kits." + kitName + ".disabled", null);
			MessageManager.message(true, player, "&aYou have enabled the kit '&6" + kitName + "&a'.");
			plugin.saveConfig();
		}
	}

	/**
	 *
	 * Check if the kit is disabled, and return the value. <br>
	 * It checks by checking if the configeration file contains the line 'disabled: value'
	 *
	 * @param kitName - The name of the kit that's being check if it's disabled.
	 * @return The value of the condition on if it's disabled.
	 *
	 * @see KitManager#kitDisabled(String)
	 */

	public static boolean kitDisabled(String kitName) {
		return (plugin.getConfig().contains("kits." + kitName + ".disabled"));
	}
}
