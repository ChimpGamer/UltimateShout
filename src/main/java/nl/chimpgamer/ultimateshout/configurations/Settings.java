package nl.chimpgamer.ultimateshout.configurations;

import nl.chimpgamer.ultimateshout.UltimateShout;
import nl.chimpgamer.ultimateshout.utils.FileUtils;

import java.io.IOException;

public class Settings extends FileUtils {
    private final UltimateShout ultimateShout;

    public Settings(UltimateShout ultimateShout) {
        super(ultimateShout.getDataFolder().getAbsolutePath(), "settings.yml");
        this.ultimateShout = ultimateShout;
    }

    public void load() {
        setupFile();
    }

    public String getShoutFormat() {
        return this.getString("format");
    }

    public String getShoutHelp() {
        return this.getString("help");
    }

    public int getShoutCooldown() {
        return this.getInt("cooldown");
    }

    public String getShoutCooldownMessage() {
        return this.getString("cooldownMessage");
    }

    public String getShoutShortcut() {
        return this.getString("shortcut");
    }

    private void setupFile() {
        if (!this.getFile().exists()) {
            try {
                this.saveToFile(ultimateShout.getResource("settings.yml"));
                this.reload();
            } catch (NullPointerException ex) {
                try {
                    this.getFile().createNewFile();
                } catch (IOException ex1) {
                    ex1.printStackTrace();
                }
            }
        }
    }
}