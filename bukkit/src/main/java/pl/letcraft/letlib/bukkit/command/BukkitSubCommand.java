package pl.letcraft.letlib.bukkit.command;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class BukkitSubCommand extends BukkitCommand {

    private final SubCommandOptions subCommandOptions;
    private BukkitCommand parent;

    public BukkitSubCommand(@NotNull String name) {
        super(name);
        this.subCommandOptions = SubCommandOptions.defaultOptions();
    }

    public BukkitSubCommand(@NotNull String name, BukkitCommand parent) {
        super(name);
        this.withParent(parent);
        this.subCommandOptions = SubCommandOptions.defaultOptions();
    }

    public BukkitSubCommand(@NotNull String name, @NotNull SubCommandOptions commandOptions) {
        super(name, commandOptions);
        this.subCommandOptions = commandOptions;
    }

    public BukkitSubCommand(@NotNull String name, @NotNull SubCommandOptions commandOptions, @NotNull BukkitCommand parent) {
        super(name, commandOptions);
        this.withParent(parent);
        this.subCommandOptions = commandOptions;
    }

    public void withParent(BukkitCommand parent) {
        this.parent = parent;
        parent.addSubCommand(this);
    }

    @Nullable
    public BukkitCommand getParent() {
        return parent;
    }

    public SubCommandOptions getSubCommandOptions() {
        return subCommandOptions;
    }
}
