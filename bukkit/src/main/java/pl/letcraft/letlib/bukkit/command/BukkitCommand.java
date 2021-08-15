package pl.letcraft.letlib.bukkit.command;

import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pl.letcraft.letlib.bukkit.util.TextUtil;
import pl.letcraft.letlib.command.CommandOptions;

import java.util.HashSet;
import java.util.Set;

public abstract class BukkitCommand implements CommandExecutor {

    protected final String name;
    protected final CommandOptions commandOptions;
    protected final Set<BukkitSubCommand> subCommands;

    public BukkitCommand(@NotNull String name) {
        this.name = name;
        this.commandOptions = CommandOptions.defaultOptions();
        this.subCommands = new HashSet<>();
    }

    public BukkitCommand(@NotNull String name, @NotNull CommandOptions commandOptions) {
        this.name = name;
        this.commandOptions = commandOptions;
        this.subCommands = new HashSet<>();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (this.commandOptions.consoleOnly() && !(sender instanceof ConsoleCommandSender)) {
            TextUtil.sendMessage(sender, this.commandOptions.consoleOnlyMessage());
            return false;
        }

        if (this.commandOptions.playerOnly() && !(sender instanceof Player)) {
            TextUtil.sendMessage(sender, this.commandOptions.playerOnlyMessage());
            return false;
        }

        final String permission = this.commandOptions.permission();
        if (permission != null && !sender.hasPermission(permission)) {
            TextUtil.sendMessage(sender, this.commandOptions.permissionMessage().replace("%perm%", permission));
            return false;
        }

        if (args.length < this.commandOptions.requiredArgs()) {
            TextUtil.sendMessage(sender, this.commandOptions.usageMessage());
            return false;
        }

        onCommand(sender, args);
        return true;
    }

    public abstract void onCommand(CommandSender sender, String[] args);

    public void addSubCommand(BukkitSubCommand subCommand) {
        if(subCommands.contains(subCommand)) {
            return;
        }

        subCommands.add(subCommand);
    }

    public void register(JavaPlugin plugin) {
        final PluginCommand command = plugin.getCommand(this.name);
        if (command == null) {
            throw new NullPointerException("Provided plugin does not have command " + this.name + "!");
        }

        command.setExecutor(this);
    }

    public void unregister(CommandMap commandMap) {
        final Command command = commandMap.getCommand(this.name);
        if (command == null) {
            throw new NullPointerException("Command is not registered in provided command map!");
        }

        command.unregister(commandMap);
    }

    @Nullable
    protected BukkitSubCommand getSubCommand(String name) {
        for (BukkitSubCommand subCommand : subCommands) {
            if (subCommand.getName().equalsIgnoreCase(name)) {
                return subCommand;
            }

            if (subCommand.getSubCommandOptions().aliases().contains(name.toLowerCase())) {
                return subCommand;
            }
        }

        return null;
    }

    public String getName() {
        return name;
    }

    public CommandOptions getCommandOptions() {
        return commandOptions;
    }

    public Set<BukkitSubCommand> getSubCommands() {
        return subCommands;
    }
}
