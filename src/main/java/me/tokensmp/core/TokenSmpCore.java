package me.tokensmp.core;

import org.bukkit.plugin.java.JavaPlugin;
import me.tokensmp.core.life.LifeManager;
import me.tokensmp.core.life.LifeListener;
import me.tokensmp.core.life.LifeCommand;
import me.tokensmp.core.token.TokenManager;
import me.tokensmp.core.token.TokenListener;
import org.bukkit.NamespacedKey;
import org.bukkit.event.player.PlayerInteractEvent;
import me.tokensmp.core.token.abilities.*;

public class TokenSmpCore extends JavaPlugin {

    private static TokenSmpCore instance;
    private LifeManager lifeManager;
    private TokenManager tokenManager;

    @Override
    public void onEnable() {
        instance = this;
        
        saveDefaultConfig();

        lifeManager = new LifeManager(this);
        tokenManager = new TokenManager(this);

        getServer().getPluginManager().registerEvents(new LifeListener(lifeManager), this);
        getServer().getPluginManager().registerEvents(new TokenListener(tokenManager), this);

        getCommand("tokens").setExecutor(new LifeCommand(lifeManager));

        getLogger().info("TokenSMP-Core ENABLED");
    }

    @Override
    public void onDisable() {
        getLogger().info("TokenSMP-Core DISABLED");
    }
    public NamespacedKey getNamespacedKey() {
        return new NamespacedKey(this, "token_type");
}
    
    public static TokenSmpCore getInstance() {
        return instance;
    }
  }
