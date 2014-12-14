package me.technopvp.hgkits.commands.subcommands;

import me.technopvp.common.utilities.CommonCore;
import me.technopvp.hgkits.utilities.enums.CommandType;

import org.bukkit.entity.Player;

public abstract class SubCommand extends CommonCore {

	public abstract boolean onCommand(Player player, String[] args);

	private String usage, description;
	private CommandType commandType;
	private String[] aliases;

	public SubCommand(CommandType type, String usage, String description, String... aliases) {
		this.commandType = type;
		this.usage = usage;
		this.description = description;
		this.aliases = aliases;
	}

	public String getUsage() {
		return usage;
	}

	public final String getDescription() {
		return description;
	}

	public final String[] getAliases() {
		return aliases;
	}

	public CommandType getCommandType() {
		return commandType;
	}

	public String getSubCommandUsage() {
		return "&a/kit " + getUsage();
	}
}