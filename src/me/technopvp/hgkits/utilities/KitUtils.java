package me.technopvp.hgkits.utilities;

import java.util.ArrayList;
import java.util.List;

import me.technopvp.common.utilities.StringUtils;
import me.technopvp.common.utilities.Utils;
import me.technopvp.hgkits.HGKits;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class KitUtils {
	static HGKits plugin = HGKits.instance;

	/**
	 * List the kit names from the configeration file by iterating through the
	 * configeration file. Kit color will be returned diffrently depending on
	 * the condintion.
	 *
	 * @param sender
	 *            - The player that will be checking for permission.
	 * @return kits - The string list of kits. Color depedning on if they have
	 *         permission to the kit.
	 */

	public static List<String> listKits(CommandSender sender) {
		List<String> kits = new ArrayList<String>();

		/* Check if the config contaions the configerationn section 'kits'. *
		 * Error porpouses */
		if (plugin.getConfig().contains("kits")) {

			/* Iterate through the kits and get the kitNames */
			for (String kitNames : plugin.getConfig().getConfigurationSection("kits").getKeys(false)) {
				/* Add the kitNames to an array list of strings if they have the
				 * permission set the color green, else make the color red. */
				kits.add((sender.hasPermission("hgkits." + kitNames)
						? ChatColor.GREEN + kitNames : ChatColor.RED + kitNames) + ChatColor.GREEN);
			}
		}
		return kits;
	}

	/**
	 * Return a list of the kits.
	 *
	 * @return kits - The string list of kits. .
	 */

	public static List<String> getAllKits() {
		List<String> kits = new ArrayList<String>();

		/* Check if the config contaions the configerationn section 'kits'. *
		 * Error porpouses */
		if (plugin.getConfig().contains("kits")) {
			/* Iterate through the kits and get the kitNames */
			for (String kitNames : plugin.getConfig().getConfigurationSection("kits").getKeys(false)) {
				/* Add the kitNames to an array list of strings. */
				kits.add(kitNames.toString().toLowerCase());
			}
		}
		return kits;
	}

	/**
	 *
	 * Set a players armor, from the helment, to boots.
	 *
	 * @param player
	 *            - The player you are setting the armor for.
	 * @param helment
	 *            - The material that will be set for the players helment.
	 * @param chestplate
	 *            - The material that will be set for the players chestplate.
	 * @param leggings
	 *            - The material that will be set for the players leggings.
	 * @param boots
	 *            - The material that will be set for the players boots.
	 */

	public static void setArmor(Player player, Material helment, Material chestplate, Material leggings, Material boots) {
		player.getInventory().setHelmet(new ItemStack(helment));
		player.getInventory().setChestplate(new ItemStack(chestplate));
		player.getInventory().setLeggings(new ItemStack(leggings));
		player.getInventory().setBoots(new ItemStack(boots));
	}

	/**
	 * Add 1 soup to a players inventory.
	 *
	 * @param player
	 */

	public static void addSoup(Player player) {
		player.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP, 1));
	}

	/**
	 *
	 * Returns an Inventory with all the kits, and allow them to be clicked.
	 *
	 * @return kitMenu - The kit GUI, with all kits.
	 */

	public static Inventory getKitMenu() {
		Inventory kitMenu = Bukkit.createInventory(null, 9, StringUtils.colorize(Lang.KIT_MENU.getMessage()));

		for (String kit : plugin.getConfig().getConfigurationSection("kits").getKeys(false)) {
			ItemStack wood = new ItemStack(Material.WOOD);
			Utils.setName(wood, ChatColor.RESET + kit.toString());

			kitMenu.addItem(wood);
		}
		return kitMenu;
	}

	public static ItemStack getKitArmor(String kitName, int slot) {
		String path = "kits." + kitName + ".armor.";
		switch (slot) {
		case 1:
			return new ItemStack(Material.matchMaterial(path + "1"));
		case 2:
			return new ItemStack(Material.matchMaterial(path + "2"));
		case 3:
			return new ItemStack(Material.matchMaterial(path + "3"));
		case 4:
			return new ItemStack(Material.matchMaterial(path + "4"));
		default:
			return null;
		}
	}

}
