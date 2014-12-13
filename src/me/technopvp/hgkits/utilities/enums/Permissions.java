package me.technopvp.hgkits.utilities.enums;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import me.technopvp.hgkits.HGKits;
import me.technopvp.hgkits.commands.CommonCommand;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Retention(RetentionPolicy.RUNTIME)
public @interface Permissions {

    Permission value() default Permission.ANYONE;

    public static enum Permission {

        // Permission levels
        ANYONE(""),
        DONOR("hgkits.donor"),
        TRIAL("hgkits.trial"),
        MOD("hgkits.mod"),
        ADMIN("hgkits.admin"),
        OWNER("hgkits.owner");

        private String permission;

        public String getPermission() {
            return permission;
        }

        private Permission(String permission) {
            this.permission = permission;
        }
    }

    public static class PermissionUtils {

        public static boolean hasPermission(CommandSender sender, Permission permissionLevel) {
            if (!(sender instanceof Player) || permissionLevel == Permission.ANYONE) {
                return true;
            }

            return sender.hasPermission(permissionLevel.getPermission());
        }

        public static boolean hasPermission(CommandSender sender, Class<? extends CommonCommand> commandClass, HGKits plugin) {
            Permissions permissions;
            try {
                permissions = commandClass.getAnnotation(Permissions.class);
            } catch (NullPointerException e) {
                plugin.log.warning("Command " + commandClass.getName() + " doesn't have permissions set!");
                return true;
            }

            return hasPermission(sender, permissions.value());
        }
    }
}