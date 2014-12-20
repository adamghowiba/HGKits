package me.technopvp.hgkits.managers;

import me.technopvp.hgkits.HGKits;
import me.technopvp.hgkits.listeners.EntityDamage;
import me.technopvp.hgkits.listeners.FoodLevelChange;
import me.technopvp.hgkits.listeners.PlayerDeath;
import me.technopvp.hgkits.listeners.PlayerDropItem;
import me.technopvp.hgkits.listeners.PlayerListener;
import me.technopvp.hgkits.listeners.kits.KitMenu;
import me.technopvp.hgkits.listeners.kits.ShifterKit;
import me.technopvp.hgkits.utilities.Lang;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class InitializeManager {
	HGKits plugin = HGKits.instance;

	public InitializeManager() {
		registerListeners();
		TEST();
		setupConfigerationFiles();
		plugin.getCommand("kit").setExecutor(new SubCommandManager());
	}

	/**
	 * Set up confiferation files. (Loading, Setting, Finalizing)
	 */

	public void setupConfigerationFiles() {
		plugin.getConfig().options().copyDefaults(true);
		plugin.saveDefaultConfig();;
	}

	/**
	 * Register all listeners.
	 */

	public void registerListeners() {
		PluginManager pluginManager = Bukkit.getServer().getPluginManager();

		pluginManager.registerEvents(new EntityDamage(), plugin);
		pluginManager.registerEvents(new FoodLevelChange(), plugin);
		pluginManager.registerEvents(new PlayerListener(), plugin);
		pluginManager.registerEvents(new PlayerDeath(), plugin);
		pluginManager.registerEvents(new PlayerDropItem(), plugin);
		pluginManager.registerEvents(new ShifterKit(), plugin);
		pluginManager.registerEvents(new KitMenu(), plugin);
	}

	public void TEST() {
		for (Lang langs : Lang.class.getEnumConstants()) {
			if (!plugin.getConfig().contains(langs.name())) {
			plugin.getConfig().set(langs.name(), langs.getMessage().replace(" ", ""));
			}
		}
		plugin.saveConfig();
	}

}
