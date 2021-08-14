package pl.letcraft.letlib.bukkit.command;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pl.letcraft.letlib.command.CommandOptions;

public abstract class BukkitSubCommand extends BukkitCommand {

    private BukkitCommand parent;

    public BukkitSubCommand(@NotNull String name) {
        super(name);
    }

    public BukkitSubCommand(@NotNull String name, BukkitCommand parent) {
        super(name);
        this.withParent(parent);
    }

    public BukkitSubCommand(@NotNull String name, @NotNull CommandOptions commandOptions) {
        super(name, commandOptions);
    }

    public BukkitSubCommand(@NotNull String name, @NotNull CommandOptions commandOptions, BukkitCommand parent) {
        super(name, commandOptions);
        this.withParent(parent);
    }

    public void withParent(BukkitCommand parent) {
        this.parent = parent;
        parent.addSubCommand(this);
    }

    @Nullable
    public BukkitCommand getParent() {
        return parent;
    }
}
