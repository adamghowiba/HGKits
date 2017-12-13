package me.technopvp.hgkits.commands;

import me.technopvp.common.managers.MessageManager;
import me.technopvp.common.utilities.enums.Level;
import me.technopvp.hgkits.HGKits;
import me.technopvp.hgkits.utilities.enums.Permissions;
import me.technopvp.hgkits.utilities.enums.Permissions.Permission;
import me.technopvp.hgkits.utilities.enums.Source;
import me.technopvp.hgkits.utilities.enums.SourceType;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

@Source(SourceType.ANY)
@Permissions(Permission.OWNER)
public class Command_hgkits extends CommonCommand {
	HGKits plugin = HGKits.instance;

	public boolean run(CommandSender sender, Command cmd, String[] args) {
		if (args.length == 0) {
			MessageManager.message(true, sender, "&a**** &6" + Bold + "HGKits" + " &a****");
			MessageManager.message(true, sender, "&6/" + "&areload" + " &6- " + "&aReload the plugin.");
			MessageManager.message(true, sender, "&6/" + "&astop" + " &6- " + "&aStop the plugin fully.");
			MessageManager.message(true, sender, "&6/" + "&astart" + " &6- " + "&aStart the plugin..");
			return true;
		}
		if (args.length == 1) {
			if (args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("rl") || args[0].equalsIgnoreCase("reloadconfig")) {
				/* Reload the default config.yml */
				plugin.reloadConfig();

				/* Log that the plugin has been reloaded */
				MessageManager.log(Level.MEDIUM, plugin.getName() + " has been reloaded by " + sender.getName());

				MessageManager.message(true, sender, "&aYou have reloaded &6" + plugin.getName() + " &aversion &6" + plugin.getDescription().getVersion() + "&a.");
				return true;
			}
			if (args[0].equalsIgnoreCase("stop") || args[0].equalsIgnoreCase("disable") || args[0].equalsIgnoreCase("shutdown")) {
				/* Disable the plugin. */
				Bukkit.getPluginManager().disablePlugin(plugin);

				/* Log that the plugin has been stopped, and who it has been stopped by. */
				MessageManager.log(Level.FATAL, plugin.getName() + " has been stopped by " + sender.getName());

				MessageManager.message(true, sender, "&aYou have stopped &6" + plugin.getName() + " &aall commands, and attributes have been halted.");
				return true;
			}
			if (args[0].equalsIgnoreCase("enable") || args[0].equalsIgnoreCase("start") || args[0].equalsIgnoreCase("startup")) {
				/* Enable the plugin. */
				Bukkit.getPluginManager().enablePlugin(plugin);

				/* Log that the plugin has been enabled, and who it was been enabled by. */
				MessageManager.log(Level.HIGH, plugin.getName() + " has been enabled by " + sender.getName());

				MessageManager.message(true, sender, "&aYou have enable " + plugin.getName() + ", plugin has been fully restored.");
				return true;
			}
			return true;
		}
		return true;
	}

}
