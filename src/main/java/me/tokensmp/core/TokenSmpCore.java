package me.tokensmp.core;

import org.bukkit.plugin.java.JavaPlugin;
import me.tokensmp.core.life.LifeManager;
import me.tokensmp.core.life.LifeListener;
import me.tokensmp.core.life.LifeCommand;
import me.tokensmp.core.token.TokenManager;
import me.tokensmp.core.token.TokenListener;
import org.bukkit.NamespacedKey;
import me.tokensmp.core.token.UnlockRecipeListener;
import me.tokensmp.core.life.TotemListener;
import me.tokensmp.core.life.LifeShieldManager;

public class TokenSmpCore extends JavaPlugin {

    private static TokenSmpCore instance;
    private TokenManager tokenManager;
    private LifeManager lifeManager;
    private LifeShieldManager lifeShieldManager;
    private NamespacedKey tokenKey;

    @Override
    public void onEnable() {
        instance = this;
        tokenKey = new NamespacedKey(this, "token");

        tokenManager = new TokenManager(this);
        lifeManager = new LifeManager();
        lifeShieldManager = new LifeShieldManager(lifeManager);
        getServer().getPluginManager().registerEvents(
                new TokenListener(tokenManager), this
        );
        getServer().getPluginManager().registerEvents(
                new LifeListener(lifeManager), this
        );
    }

    public static TokenSmpCore getInstance() {
        return instance;
    }

    public NamespacedKey getTokenKey() {
        return tokenKey;
    }
}

