package pl.letcraft.letlib.bukkit.util;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

public final class TextUtil {

    public static final String ARROW_RIGHT = "»";
    public static final String ARROW_LEFT = "«";

    private TextUtil() {}

    public static void sendMessage(@NotNull CommandSender sender, @NotNull String message) {
        sender.sendMessage(format(message));

    }

    public static String format(@NotNull String message) {
        message = applySpecialChars(message);
        message = translateLegacyColor(message);

        return message;
    }

    public static List<String> format(@NotNull List<String> messages) {
        return messages
                .stream()
                .map(msg -> {
                    msg = format(msg);
                    return msg;
                })
                .collect(Collectors.toList());
    }

    public static String translateLegacyColor(@NotNull String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static String applySpecialChars(@NotNull String message) {
        message = message.replace(">>", ARROW_RIGHT);
        message = message.replace("<<", ARROW_LEFT);
        message = message.replace("%nl%", "\n").replace("%newline%", "\n");

        return message;
    }
}
