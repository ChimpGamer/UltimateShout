package nl.chimpgamer.ultimateshout.commands;

import com.google.common.base.Joiner;
import nl.chimpgamer.ultimateshout.UltimateShout;
import nl.chimpgamer.ultimateshout.utils.TextUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ShoutCommand implements CommandExecutor {
    private final UltimateShout ultimateShout;

    public ShoutCommand(UltimateShout ultimateShout) {
        this.ultimateShout = ultimateShout;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }
        Player player = (Player) sender;
        if (args.length == 0) {
            player.sendMessage(TextUtils.formatColorCodes(ultimateShout.getSettings().getShoutHelp()));
            return true;
        }
        if (args.length == 1 && args[0].equals("reload") && player.hasPermission("ultimateshout.shout.reload")) {
            ultimateShout.getSettings().reload();
            player.sendMessage(TextUtils.formatColorCodes("&aSuccessfully &7reloaded the &6config&7!"));
            return true;
        }

        String message = Joiner.on(" ").join(args);
        ultimateShout.handleShout(player, message);
        return true;
    }
}