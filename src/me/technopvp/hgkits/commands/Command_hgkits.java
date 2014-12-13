package me.technopvp.hgkits.commands;

import me.technopvp.common.commands.CommonCommand;
import me.technopvp.common.utilities.enums.Level;
import me.technopvp.hgkits.HGKits;
import me.technopvp.hgkits.managers.MessageManager;
import me.technopvp.hgkits.utilities.enums.Permissions;
import me.technopvp.hgkits.utilities.enums.Source;
import me.technopvp.hgkits.utilities.enums.SourceType;
import me.technopvp.hgkits.utilities.enums.Permissions.Permission;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

@Source(SourceType.ANY)
@Permissions(Permission.OWNER)
public class Command_hgkits extends CommonCommand {
	HGKits plugin = HGKits.instance;

	public boolean run(CommandSender sender, Command cmd, String[] args) {
		if (args.length == 0) {
			msg("**** " + Gold + Bold + "HGKits" + Green + " ****");
			msg(Green + "/" + Gold + "reload" + Green + " - " + Gold + " Reload the plugin.");
			msg(Green + "/" + Gold + "stop" + Green + " - " + Gold + " Stop the plugin fully.");
			msg(Green + "/" + Gold + "start" + Green + " - " + Gold + " Start/Restore the plugin.");
			return true;
		}

			if (args.length == 1) {
			if (args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("rl") || args[0].equalsIgnoreCase("reloadconfig")) {
				/* Reload the default config.yml */
				plugin.reloadConfig();
				/* Log the reload */
				MessageManager.log(Level.MEDIUM, plugin.getName() + " has been reloaded by " + sender.getName());

				msg("You have reloaded " + plugin.getName() + " v" + Gold + plugin.getDescription().getVersion() + Green + ".");
				return true;
			}
			if (args[0].equalsIgnoreCase("stop") || args[0].equalsIgnoreCase("disable") || args[0].equalsIgnoreCase("shutdown")) {
				/* Stop the plugin from running */
				Bukkit.getPluginManager().disablePlugin(plugin);
				/* Log the reload */
				MessageManager.log(Level.FATAL, plugin.getName() + " has been stopped by " + sender.getName());

				msg("You have stopped " + plugin.getName() + " all commands, and attributes have been halted.");
				return true;
			}
			if (args[0].equalsIgnoreCase("enable") || args[0].equalsIgnoreCase("start") || args[0].equalsIgnoreCase("startup")) {
				/* Stop the plugin from running */
				Bukkit.getPluginManager().enablePlugin(plugin);
				/* Log the reload */
				MessageManager.log(Level.HIGH, plugin.getName() + " has been enabled by " + sender.getName());

				msg("You have enable " + plugin.getName() + ", plugin has been fully restored.");
				return true;
			}
				return true;
			}
		return true;
	}

}
