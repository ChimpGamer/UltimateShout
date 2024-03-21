package nl.chimpgamer.ultimateshout.utils;

import nl.chimpgamer.ultimateshout.UltimateShout;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.logging.Level;

public abstract class FileUtils extends YamlConfiguration {
    @NotNull
    private final UltimateShout ultimateShout;
    @NotNull
    private final File file;

    public FileUtils(@NotNull UltimateShout ultimateShout, @NotNull File file) {
        this.ultimateShout = ultimateShout;
        this.file = file;

        this.load();
    }

    public void saveToFile(InputStream in) {
        try {
            Files.copy(in, this.file.toPath());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void load() {
        try {
            load(file);
        } catch (FileNotFoundException ignored) {
        } catch (IOException | InvalidConfigurationException ex) {
            ultimateShout.getLogger().log(Level.SEVERE, "Cannot load " + file, ex);
        }
    }

    public void reload() {
        load();
    }

    public @NotNull File getFile() {
        return this.file;
    }
}
