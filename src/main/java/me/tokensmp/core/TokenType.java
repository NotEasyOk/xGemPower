package me.tokensmp.core.token;

public enum TokenType {

    ENDERMAN,
    SKELETON,
    BLAZE,
    CREEPER,
    WITHER,
    WARDEN,
    BREEZE,
    DOLPHIN;

    public static TokenType random() {
        TokenType[] values = values();
        return values[(int) (Math.random() * values.length)];
    }
}
