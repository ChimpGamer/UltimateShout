package nl.chimpgamer.ultimateshout;

import nl.chimpgamer.ultimateshout.commands.ShoutCommand;
import nl.chimpgamer.ultimateshout.configurations.Settings;
import nl.chimpgamer.ultimateshout.events.ShoutEvent;
import nl.chimpgamer.ultimateshout.hooks.PluginHookManager;
import nl.chimpgamer.ultimateshout.listeners.ShoutListener;
import nl.chimpgamer.ultimateshout.utils.Cooldown;
import nl.chimpgamer.ultimateshout.utils.TextUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.logging.Level;

public final class UltimateShout extends JavaPlugin {
    private static UltimateShout instance;

    private Settings settings;

    private PluginHookManager pluginHookManager;

    public static UltimateShout getInstance() {
        return instance;
    }

    @Override
    public void onLoad() {
        // Make sure that the UltimateShout folder exists.
        try {
            Path dataFolderPath = getDataFolder().toPath();
            if (!Files.isDirectory(getDataFolder().toPath())) {
                Files.createDirectories(dataFolderPath);
            }
        } catch (IOException ex) {
            getLogger().log(Level.SEVERE, "Unable to create plugin directory", ex);
        }
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        this.initSettings();
        this.initPluginHookManager();
        this.initCommands();
        this.initListeners();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        // Unregister all event listeners of the plugin
        HandlerList.unregisterAll(this);
    }

    private void initSettings() {
        this.settings = new Settings(this);
        this.settings.load();
    }

    private void initPluginHookManager() {
        this.pluginHookManager = new PluginHookManager(this);
        this.pluginHookManager.load();
    }

    private void initCommands() {
        Objects.requireNonNull(this.getCommand("shout")).setExecutor(new ShoutCommand(this));
    }

    private void initListeners() {
        this.getServer().getPluginManager().registerEvents(new ShoutListener(this), this);
    }

    public void handleShout(@NotNull Player player, @NotNull String message) {
        if (Cooldown.isInCooldown(player.getUniqueId(), "shout") && !player.hasPermission("ultimateshout.shout.nocooldown")) {
            player.sendMessage(TextUtils.formatColorCodes(settings.getShoutCooldownMessage()
                    .replace("%cooldown%", String.valueOf(Cooldown.getTimeLeft(player.getUniqueId(), "shout")))));
            return;
        }
        int cooldown = settings.getShoutCooldown();

        if (cooldown > 0 && !player.hasPermission("ultimateshout.shout.nocooldown")) {
            new Cooldown(player.getUniqueId(), "shout", cooldown).start();
        }

        ShoutEvent shoutEvent = new ShoutEvent(player, message);
        this.getServer().getPluginManager().callEvent(shoutEvent);
        if (shoutEvent.isCancelled()) {
            return;
        }

        String shoutFormat = settings.getShoutFormat();
        if (shoutFormat == null) {
            shoutFormat = "Config error: Shout format seems to be missing from the configuration.";
        }

        shoutFormat = TextUtils.parsePlaceholders(player, shoutFormat)
                .replace("%message%", message);
        this.getServer().broadcastMessage(TextUtils.formatColorCodes(shoutFormat));
    }

    public PluginHookManager getPluginHookManager() {
        return pluginHookManager;
    }

    public Settings getSettings() {
        return settings;
    }

    public boolean isPluginEnabled(String pluginName) {
        return getServer().getPluginManager().isPluginEnabled(pluginName);
    }
}