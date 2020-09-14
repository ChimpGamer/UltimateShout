package nl.chimpgamer.ultimateshout.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class ShoutEvent extends Event implements Cancellable {
    private final Player player;
    private final String message;
    private boolean cancelled;

    private static final HandlerList HANDLERS_LIST = new HandlerList();

    public ShoutEvent(Player player, String message) {
        this.player = player;
        this.message = message;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS_LIST;
    }

    @NotNull
    public Player getPlayer() {
        return player;
    }

    @NotNull
    public String getMessage() {
        return message;
    }
}