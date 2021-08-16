package pl.letcraft.letlib.bukkit.command;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CommandOptions {

    private String permission;
    private String usage;
    private boolean playerOnly;
    private boolean consoleOnly;
    private int requiredArgs;
    private String permissionMessage;
    private String usageMessage;
    private String playerOnlyMessage;
    private String consoleOnlyMessage;

    public static CommandOptions defaultOptions() {
        return new CommandOptions();
    }

    public CommandOptions() {
        this.permission = null;
        this.usage = null;
        this.playerOnly = false;
        this.consoleOnly = false;
        this.requiredArgs = 0;
        this.permissionMessage = "&8>> &cNie posiadasz uprawnień! &e(%perm%)";
        this.usageMessage = "&8>> &cUżycie: &e" + usage;
        this.playerOnlyMessage = "&8>> &cTa komenda może zostać użyta tylko przez gracza.";
        this.consoleOnlyMessage = "&8>> &cTa komenda może zostać użyta tylko przez konsolę.";
    }

    public boolean playerOnly() {
        return this.playerOnly;
    }

    public CommandOptions playerOnly(boolean playerOnly) {
        this.playerOnly = playerOnly;
        return this;
    }

    public boolean consoleOnly() {
        return this.consoleOnly;
    }

    public CommandOptions consoleOnly(boolean consoleOnly) {
        this.consoleOnly = consoleOnly;
        return this;
    }

    @Nullable
    public String permission() {
        return this.permission;
    }

    public CommandOptions permission(String permission) {
        this.permission = permission;
        return this;
    }

    @Nullable
    public String usage() {
        return usage;
    }

    public CommandOptions usage(String usage) {
        this.usage = usage;
        return this;
    }

    public int requiredArgs() {
        return requiredArgs;
    }

    public CommandOptions requiredArgs(int requiredArgs) {
        this.requiredArgs = requiredArgs;
        return this;
    }

    public String permissionMessage() {
        return permissionMessage;
    }

    public CommandOptions permissionMessage(@NotNull String permissionMessage) {
        this.permissionMessage = permissionMessage;
        return this;
    }

    public String usageMessage() {
        return usageMessage;
    }

    public CommandOptions usageMessage(@NotNull String usageMessage) {
        this.usageMessage = usageMessage;
        return this;
    }

    public String playerOnlyMessage() {
        return playerOnlyMessage;
    }

    public CommandOptions playerOnlyMessage(@NotNull String playerOnlyMessage) {
        this.playerOnlyMessage = playerOnlyMessage;
        return this;
    }

    public String consoleOnlyMessage() {
        return consoleOnlyMessage;
    }

    public CommandOptions consoleOnlyMessage(@NotNull String consoleOnlyMessage) {
        this.consoleOnlyMessage = consoleOnlyMessage;
        return this;
    }
}
