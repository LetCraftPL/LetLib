package pl.letcraft.letlib.bukkit.version;

import org.bukkit.Bukkit;
import pl.letcraft.letlib.version.ProtocolVersion;

public final class ServerVersion {

    public static String getServerNmsVersion() {
        final String version = Bukkit.getServer().getClass().getPackage().getName();
        return version.substring(version.lastIndexOf('.') + 1);
    }

    public static ProtocolVersion getProtocolVersion() {
        final String version = Bukkit.getMinecraftVersion();
        return ProtocolVersion.fromMinecraftVersion(version);
    }


}
