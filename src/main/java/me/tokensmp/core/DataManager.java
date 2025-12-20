package me.tokensmp.core.data;

import me.tokensmp.core.token.TokenType;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class DataManager {

    private final File file;
    private final YamlConfiguration config;

    public DataManager(File folder) {
        file = new File(folder, "data.yml");
        config = YamlConfiguration.loadConfiguration(file);
    }

    public void saveToken(UUID uuid, TokenType type) {
        config.set(uuid + ".token", type.name());
        save();
    }

    public TokenType loadToken(UUID uuid) {
        String t = config.getString(uuid + ".token");
        return t == null ? null : TokenType.valueOf(t);
    }

    public void saveLife(UUID uuid, int life) {
        config.set(uuid + ".life", life);
        save();
    }

    public int loadLife(UUID uuid) {
        return config.getInt(uuid + ".life", 5);
    }

    private void save() {
        try {
            config.save(file);
        } catch (IOException ignored) {}
    }
              }
      
