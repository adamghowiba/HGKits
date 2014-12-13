package me.technopvp.hgkits.managers;

import me.technopvp.hgkits.HGKits;
import me.technopvp.hgkits.listeners.EntityDamage;
import me.technopvp.hgkits.listeners.FoodLevelChange;
import me.technopvp.hgkits.listeners.PlayerDropItem;
import me.technopvp.hgkits.listeners.PlayerListener;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class InitializeManager {
	HGKits plugin = HGKits.instance;

	public InitializeManager() {
		registerListeners();
		setupConfigerationFiles();
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
		pluginManager.registerEvents(new PlayerDropItem(), plugin);
	}

}
