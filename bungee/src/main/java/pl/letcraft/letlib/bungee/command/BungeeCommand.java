package pl.letcraft.letlib.bungee.command;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pl.letcraft.letlib.bungee.util.TextUtil;

import java.util.HashSet;
import java.util.Set;

public abstract class BungeeCommand extends Command {

    protected final CommandOptions commandOptions;
    protected final Set<BungeeCommand> subCommands;

    public BungeeCommand(String name) {
        super(name);
        this.commandOptions = CommandOptions.defaultOptions();
        this.subCommands = new HashSet<>();
    }

    public BungeeCommand(String name, CommandOptions commandOptions) {
        super(name, commandOptions.permission(), commandOptions.aliases().toArray(new String[0]));
        this.commandOptions = commandOptions;
        this.subCommands = new HashSet<>();
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (this.commandOptions.consoleOnly() && sender instanceof ProxiedPlayer) {
            TextUtil.sendMessage(sender, this.commandOptions.consoleOnlyMessage());
            return;
        }

        if (this.commandOptions.playerOnly() && !(sender instanceof ProxiedPlayer)) {
            TextUtil.sendMessage(sender, this.commandOptions.playerOnlyMessage());
            return;
        }

        final String permission = this.commandOptions.permission();
        if (permission != null && !sender.hasPermission(permission)) {
            TextUtil.sendMessage(sender, this.commandOptions.permissionMessage().replace("%perm%", permission));
            return;
        }

        if (args.length < this.commandOptions.requiredArgs()) {
            TextUtil.sendMessage(sender, this.commandOptions.usageMessage());
            return;
        }

        onCommand(sender, args);
    }

    public abstract void onCommand(CommandSender sender, String[] args);

    public void register(@NotNull Plugin plugin) {
        PluginManager pluginManager = ProxyServer.getInstance().getPluginManager();
        pluginManager.registerCommand(plugin, this);
    }

    public void unregister() {
        PluginManager pluginManager = ProxyServer.getInstance().getPluginManager();
        pluginManager.unregisterCommand(this);
    }

    @Nullable
    protected BungeeCommand getSubCommand(String name) {
        for (BungeeCommand subCommand : subCommands) {
            if (subCommand.getName().equalsIgnoreCase(name)) {
                return subCommand;
            }

            if (subCommand.getCommandOptions().aliases().contains(name.toLowerCase())) {
                return subCommand;
            }
        }

        return null;
    }

    public CommandOptions getCommandOptions() {
        return commandOptions;
    }

    public Set<BungeeCommand> getSubCommands() {
        return subCommands;
    }
}
