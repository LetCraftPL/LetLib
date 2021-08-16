package pl.letcraft.letlib.bukkit.command;

import java.util.Collections;
import java.util.List;

public class SubCommandOptions extends CommandOptions {

    private List<String> aliases;

    public static SubCommandOptions defaultOptions() {
        return new SubCommandOptions();
    }

    public SubCommandOptions() {
        super();
        this.aliases = Collections.emptyList();
    }


    public List<String> aliases() {
        return aliases;
    }

    public SubCommandOptions aliases(List<String> aliases) {
        this.aliases = aliases;
        return this;
    }
}
