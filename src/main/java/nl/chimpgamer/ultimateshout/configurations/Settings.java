package nl.chimpgamer.ultimateshout.configurations;

import nl.chimpgamer.ultimateshout.UltimateShout;
import nl.chimpgamer.ultimateshout.utils.FileUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

public class Settings extends FileUtils {
    private final UltimateShout ultimateShout;

    public Settings(UltimateShout ultimateShout) {
        super(ultimateShout, ultimateShout.getDataFolder().toPath().resolve("settings.yml").toFile());
        this.ultimateShout = ultimateShout;
    }

    public void load() {
        setupFile();
    }

    @Nullable
    public String getShoutFormat() {
        return this.getString("format");
    }

    @NotNull
    public String getShoutHelp() {
        return this.getString("help", "&7Please use &6/shout <message>");
    }

    public int getShoutCooldown() {
        return this.getInt("cooldown");
    }

    @NotNull
    public String getShoutCooldownMessage() {
        return this.getString("cooldownMessage", "&6You''ll have to wait %cooldown% second(s) before shouting again.");
    }

    @Nullable
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