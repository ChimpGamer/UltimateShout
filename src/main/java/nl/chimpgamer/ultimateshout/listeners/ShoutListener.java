package nl.chimpgamer.ultimateshout.listeners;

import nl.chimpgamer.ultimateshout.UltimateShout;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ShoutListener implements Listener {
    private final UltimateShout ultimateShout;

    public ShoutListener(UltimateShout ultimateShout) {
        this.ultimateShout = ultimateShout;
    }

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onAsyncChat(AsyncPlayerChatEvent event) {
        final Player player = event.getPlayer();
        final String message = event.getMessage();
        String shortcut = ultimateShout.getSettings().getShoutShortcut();
        if (shortcut == null || shortcut.isEmpty() || shortcut.equals(" ")) {
            return;
        }
        if (message.startsWith(shortcut) && player.hasPermission("ultimateshout.shout.use")) {
            event.setCancelled(true);
            ultimateShout.handleShout(player, message.replaceFirst(shortcut, ""));
        }
    }
}