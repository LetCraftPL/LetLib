package pl.letcraft.letlib.version;

import org.jetbrains.annotations.NotNull;

public enum ProtocolVersion {

    v1_17_1(756, "1.17.1"),
    v1_17(755, "1.17"),
    v1_16_4(754, "1.16.4 - 1.16.5"),
    v1_16_3(753, "1.16.3"),
    v1_16_2(751, "1.16.2"),
    v1_16_1(736, "1.16.1"),
    v1_16(735, "1.16"),
    v1_15_2(578, "1.15.2"),
    v1_15_1(575, "1.15.1"),
    v1_15(573, "1.15"),
    v1_14_4(498, "1.14.4"),
    v1_14_3(490, "1.14.3"),
    v1_14_2(485, "1.14.2"),
    v1_14_1(480, "1.14.1"),
    v1_14(477, "1.14"),
    v1_13_2(404, "1.13.2"),
    v1_13_1(401, "1.13.1"),
    v1_13(393, "1.13"),
    v1_12_2(340, "1.12.2"),
    v1_12_1(338, "1.12.1"),
    v1_12(335, "1.12"),
    v1_11_1(316, "1.11.1 - 1.11.2"),
    v1_11(315, "1.11"),
    v1_10(210, "1.10.x"),
    v1_9_3(110, "1.9.3 - 1.9.4"),
    v1_9_2(109, "1.9.2"),
    v1_9_1(108, "1.9.1"),
    v1_9(107, "1.9"),
    v1_8(47, "1.8.x"),
    v1_7_6(5, "1.7.6 - 1.17.10"),
    v1_7_2(4, "1.7.2 - 1.7.5"),
    UNKNOWN(-1, "UNKNOWN");


    private final int protocol;
    private final String formattedVersion;

    ProtocolVersion(final int protocol, @NotNull final String formattedVersion) {
        this.protocol = protocol;
        this.formattedVersion = formattedVersion;
    }

    public static ProtocolVersion fromProtocol(final int protocol) {
        for (ProtocolVersion value : values()) {
            if(value.getProtocol() == protocol) {
                return value;
            }
        }

        return UNKNOWN;
    }

    public static ProtocolVersion fromMinecraftVersion(@NotNull final String version) {
        return switch (version) {
            case "1.17.1" -> ProtocolVersion.v1_17_1;
            case "1.17" -> ProtocolVersion.v1_17;
            case "1.16.5", "1.16.4" -> ProtocolVersion.v1_16_4;
            case "1.16.3" -> ProtocolVersion.v1_16_3;
            case "1.16.2" -> ProtocolVersion.v1_16_2;
            case "1.16.1" -> ProtocolVersion.v1_16_1;
            case "1.16" -> ProtocolVersion.v1_16;
            case "1.15.2" -> ProtocolVersion.v1_15_2;
            case "1.15.1" -> ProtocolVersion.v1_15_1;
            case "1.15" -> ProtocolVersion.v1_15;
            case "1.14.4" -> ProtocolVersion.v1_14_4;
            case "1.14.3" -> ProtocolVersion.v1_14_3;
            case "1.14.2" -> ProtocolVersion.v1_14_2;
            case "1.14.1" -> ProtocolVersion.v1_14_1;
            case "1.14" -> ProtocolVersion.v1_14;
            case "1.13.2" -> ProtocolVersion.v1_13_2;
            case "1.13.1" -> ProtocolVersion.v1_13_1;
            case "1.13" -> ProtocolVersion.v1_13;
            case "1.12.2" -> ProtocolVersion.v1_12_2;
            case "1.12.1" -> ProtocolVersion.v1_12_1;
            case "1.12" -> ProtocolVersion.v1_12;
            case "1.11.2", "1.11.1" -> ProtocolVersion.v1_11_1;
            case "1.11" -> ProtocolVersion.v1_11;
            case "1.10.2", "1.10.1", "1.10" -> ProtocolVersion.v1_10;
            case "1.9.4", "1.9.3" -> ProtocolVersion.v1_9_3;
            case "1.9.2" -> ProtocolVersion.v1_9_2;
            case "1.9.1" -> ProtocolVersion.v1_9_1;
            case "1.9" -> ProtocolVersion.v1_9;
            case "1.8.9", "1.8.8", "1.8.7", "1.8.6", "1.8.5", "1.8.4", "1.8.3", "1.8.2", "1.8.1", "1.8" -> ProtocolVersion.v1_8;
            case "1.7.10", "1.7.9", "1.7.8", "1.7.7", "1.7.6" -> ProtocolVersion.v1_7_6;
            case "1.7.5", "1.7.4", "1.7.3", "1.7.2" -> ProtocolVersion.v1_7_2;
            default -> ProtocolVersion.UNKNOWN;
        };
    }


    public int getProtocol() {
        return protocol;
    }

    public String getFormattedVersion() {
        return formattedVersion;
    }
}
