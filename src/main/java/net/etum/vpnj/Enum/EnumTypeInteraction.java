package net.etum.vpnj.Enum;

public enum EnumTypeInteraction {


    DEFAULT("DEFAULT"),
    WEATHER("WEATHER"),
    TIME("TIME"),
    PLAYER("PLAYER");

    private final String value;

    EnumTypeInteraction(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
