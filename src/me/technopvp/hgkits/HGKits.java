package me.technopvp.hgkits;

import java.util.logging.Logger;

import me.technopvp.common.utilities.enums.Level;
import me.technopvp.hgkits.commands.CommonCommand;
import me.technopvp.hgkits.managers.InitializeManager;
import me.technopvp.hgkits.managers.MessageManager;
import me.technopvp.hgkits.utilities.Lang;
import me.technopvp.hgkits.utilities.enums.Permissions.PermissionUtils;
import me.technopvp.hgkits.utilities.enums.Source.SourceUtils;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class HGKits extends JavaPlugin {

	/*
	 * TODO:
	 * - Finish disabling kits.
	 * - Make enable kit method, and subcommand.
	 * - Make it use permissions.
	 * - Make system for abilitys.
	 * - Make config for kits.
	 * - ADD BETTER TODO LIST
	 */

	public Logger log = Logger.getLogger("Minecraft");

	public static HGKits instance;
	public InitializeManager initializeManager;

	public void onEnable() {
		instance = this;
		initializeManager = new InitializeManager();

		log.info(Lang.PREFIX.getMessage() + "HGKits has been succesfully enabled.");
	}

	public void onDisable() {

		log.info(Lang.PREFIX.getMessage() + "HGKits has been disabled.");
	}

	   public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
	        final CommonCommand dispatcher;

	        // Load and initialize class
	        try {
	            ClassLoader classLoader = HGKits.class.getClassLoader();
	            dispatcher = (CommonCommand) classLoader.loadClass(String.format("%s.%s", CommonCommand.class.getPackage().getName(), "Command_" + cmd.getName().toLowerCase())).newInstance();
	            dispatcher.setCommandSender(sender);
	        } catch (Exception e) {
	            log.severe("Command not loaded: " + cmd.getName());
	            log.severe(e.toString());
	            sender.sendMessage(ChatColor.RED + "Command Error: Command not loaded: " + cmd.getName());
	            return true;
	        }

	        // Check for permissions
	        try {
	            if (!SourceUtils.fromSource(sender, dispatcher.getClass(), this)) {
	                return (sender instanceof Player ? dispatcher.consoleOnly() : dispatcher.playerOnly());
	            }

	            if (PermissionUtils.hasPermission(sender, dispatcher.getClass(), this)) {
	                return dispatcher.run(sender, cmd, args);
	            } else {
	                return dispatcher.noPermission();
	            }
	        } catch (Exception e) {
	            MessageManager.log(Level.HIGH, "Unknown command error: " + e.getMessage());
	            MessageManager.log(Level.HIGH, e.toString());
	            sender.sendMessage(ChatColor.RED + "Command Error: " + e.getMessage());
	            e.printStackTrace();
	            return true;
	        }

	    }

}
