package me.technopvp.hgkits.managers;

import me.technopvp.common.PluginPrefix;
import me.technopvp.hgkits.HGKits;
import me.technopvp.hgkits.listeners.EntityDamage;
import me.technopvp.hgkits.listeners.FoodLevelChange;
import me.technopvp.hgkits.listeners.PlayerDeath;
import me.technopvp.hgkits.listeners.PlayerDropItem;
import me.technopvp.hgkits.listeners.PlayerListener;
import me.technopvp.hgkits.listeners.PlayerRespawn;
import me.technopvp.hgkits.listeners.duel.DuelPlayerCommandPreprocess;
import me.technopvp.hgkits.listeners.duel.DuelPlayerDeath;
import me.technopvp.hgkits.listeners.kits.KitMenu;
import me.technopvp.hgkits.listeners.kits.KitShifter;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class InitializeManager {
	HGKits plugin = HGKits.instance;

	public InitializeManager() {
		plugin.getCommand("kit").setExecutor(new SubCommandManager());
		PluginPrefix prefix = new PluginPrefix(plugin, "adawd");
		prefix.addAttachmentPrefix();

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

		/* Register server listeners */
		pluginManager.registerEvents(new EntityDamage(), plugin);
		pluginManager.registerEvents(new FoodLevelChange(), plugin);
		pluginManager.registerEvents(new PlayerListener(), plugin);
		pluginManager.registerEvents(new PlayerDeath(), plugin);
		pluginManager.registerEvents(new PlayerDropItem(), plugin);
		pluginManager.registerEvents(new PlayerRespawn(), plugin);

		/* Register kit listeners */
		pluginManager.registerEvents(new KitShifter(), plugin);
		pluginManager.registerEvents(new KitMenu(), plugin);

		/* Register duel listeners */
		pluginManager.registerEvents(new DuelPlayerDeath(), plugin);
		pluginManager.registerEvents(new DuelPlayerCommandPreprocess(), plugin);

	}
}
