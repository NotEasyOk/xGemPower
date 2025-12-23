package me.tokensmp.core;

import me.tokensmp.core.life.LifeListener;
import me.tokensmp.core.life.LifeManager;
import me.tokensmp.core.life.LifeShieldManager;
import me.tokensmp.core.life.TotemListener;
import me.tokensmp.core.token.TokenListener;
import me.tokensmp.core.token.TokenManager;
import me.tokensmp.core.life.LifeCommand;
import me.tokensmp.core.token.UnlockRecipeListener;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;
    
public class TokenSmpCore extends JavaPlugin {

    private static TokenSmpCore instance;

    private TokenManager tokenManager;
    private LifeManager lifeManager;
    private LifeShieldManager lifeShieldManager;
    private NamespacedKey tokenKey;

    @Override
    public void onEnable() {
        instance = this;

        // 🔥 THIS LINE FIXES FOLDER ISSUE
        saveDefaultConfig(); // plugins/xGemPower/ create karega

        // Optional safety
        if (!getDataFolder().exists()) {
            getDataFolder().mkdirs();
        }

        tokenKey = new NamespacedKey(this, "token");

        tokenManager = new TokenManager(this);
        lifeManager = new LifeManager(this);
        lifeShieldManager = new LifeShieldManager(this);

        // Register listeners
        getServer().getPluginManager().registerEvents(
                new TokenListener(tokenManager), this
        );

        getServer().getPluginManager().registerEvents(
                new LifeListener(lifeManager), this
        );

        getServer().getPluginManager().registerEvents(
                new TotemListener(this), this
        );

        getLogger().info("xGemPower enabled successfully!");
    }

    @Override
    public void onDisable() {
        getLogger().info("xGemPower disabled!");
    }

    public static TokenSmpCore getInstance() {
        return instance;
    }

    public NamespacedKey getTokenKey() {
        return tokenKey;
    }

    public LifeShieldManager getLifeShieldManager() {
        return lifeShieldManager;
    }
}

