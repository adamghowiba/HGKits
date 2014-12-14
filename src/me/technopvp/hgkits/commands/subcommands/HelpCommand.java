package me.technopvp.hgkits.commands.subcommands;

import me.technopvp.hgkits.utilities.enums.CommandType;

import org.bukkit.entity.Player;

public class HelpCommand extends SubCommand {

	public HelpCommand() {
		super(CommandType.DEFAULT, "", "", "help");
	}

	@Override
	public boolean onCommand(Player player, String[] args) {
		return false;
	}

}
