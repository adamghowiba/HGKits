package me.technopvp.hgkits.commands;

import me.technopvp.common.managers.MessageManager;
import me.technopvp.common.utilities.CommonCore;
import me.technopvp.hgkits.HGKits;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public abstract class CommonCommand extends CommonCore {
	HGKits plugin = HGKits.instance;

	private CommandSender commandSender;

	public void setCommandSender(CommandSender sender) {
		this.commandSender = sender;
	}

	public boolean invalidUsage() {
		commandSender.sendMessage("§cInvalid Usage");
		return true;
	}

	public boolean invalidArguments() {
		commandSender.sendMessage("§cInvalid Arguments");
		return true;
	}

	public boolean notStaff() {
		commandSender.sendMessage("§7You are not a staff member");
		return true;
	}

	public boolean notDonor() {
		commandSender.sendMessage("§7You must donate for this command");
		return true;
	}

	public boolean invalidImput() {
		commandSender.sendMessage("§cInvalid Usage");
		return true;
	}

	public boolean noPermission() {
		MessageManager.message(commandSender, "&aYou do not have sufficient permission to this.", true);
		return true;
	}

	public boolean targetOffline(String target) {
		commandSender.sendMessage(Red + "Couldn't find player " + "'" + target.toString() + "'");
		return true;
	}

	public boolean showUsage(Command command) {
		commandSender.sendMessage(ChatColor.YELLOW + "Usage: " + command.getUsage().replaceAll("<command>", command.getName()));
		return true;
	}

	public boolean consoleOnly() {
		warn("This command can only be executed from the console.");
		return true;
	}

	public boolean playerOnly() {
		warn("This command can only be executed by players.");
		return true;
	}

	public boolean warn(String message) {
		commandSender.sendMessage(ChatColor.YELLOW + message);
		return true;
	}

	public boolean msg(String message) {
		commandSender.sendMessage(ChatColor.GRAY + message);
		return true;
	}

	public abstract boolean run(CommandSender sender, Command cmd, String[] args);
}
