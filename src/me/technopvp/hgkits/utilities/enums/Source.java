package me.technopvp.hgkits.utilities.enums;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import me.technopvp.hgkits.HGKits;
import me.technopvp.hgkits.commands.CommonCommand;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Retention(RetentionPolicy.RUNTIME)
public @interface Source {

    SourceType value();

    public static class SourceUtils {

        public static boolean fromSource(CommandSender sender, SourceType source) {
            if (source == SourceType.ANY) {
                return true;
            }

            return (source == SourceType.PLAYER ? sender instanceof Player : !(sender instanceof Player));
        }

        public static boolean fromSource(CommandSender sender, Class<? extends CommonCommand> commandClass, HGKits plugin) {
            Source source;
            try {
                source = commandClass.getAnnotation(Source.class);
            } catch (NullPointerException ex) {
                plugin.log.warning("Command " + commandClass.getName() + " doesn't have a command-source set!");
                return true;
            }

            return fromSource(sender, source.value());
        }
    }
}