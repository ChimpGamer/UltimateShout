package nl.chimpgamer.ultimateshout.utils;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;

public class FileUtils {
    private final File file;
    private final FileConfiguration config;

    public FileUtils(String path, String file) {
        this.file = new File(path, file);
        this.config = YamlConfiguration.loadConfiguration(this.file);
    }

    public FileUtils addDefault(String path, Object value) {
        this.config.addDefault(path, value);
        return this;
    }

    public FileUtils saveToFile(InputStream in) {
        try {
            Files.copy(in, this.file.toPath());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return this;
    }

    public FileUtils copyDefaults(boolean copyDefaults) {
        this.config.options().copyDefaults(copyDefaults);
        return this;
    }

    public FileUtils set(String path, Object value) {
        this.config.set(path, value);
        return this;
    }

    public String getString(String path) {
        return this.getConfig().getString(path);
    }

    public String getString(String path, String def) {
        return this.getConfig().getString(path, def);
    }

    public List<String> getStringList(String path) {
        return this.getConfig().getStringList(path);
    }

    public boolean contains(String path) {
        return this.getConfig().contains(path);
    }

    public int getInt(String path) {
        return this.getConfig().getInt(path);
    }

    public int getInt(String path, int def) {
        return this.getConfig().getInt(path, def);
    }

    public boolean getBoolean(String path) {
        return this.getConfig().getBoolean(path);
    }

    public boolean getBoolean(String path, boolean def) {
        return this.getConfig().getBoolean(path, def);
    }

    public long getLong(String path) {
        return this.getConfig().getLong(path);
    }

    public long getLong(String path, long def) {
        return this.getConfig().getLong(path, def);
    }

    public FileUtils save() {
        try {
            this.config.save(this.file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return this;
    }

    public File getFile() {
        return this.file;
    }

    public void reload() {
        try {
            this.config.load(this.file);
        } catch (InvalidConfigurationException | IOException ex) {
            ex.printStackTrace();
        }
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public void delete() {
        this.file.delete();
    }
}