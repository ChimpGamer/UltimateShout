package nl.chimpgamer.ultimateshout.utils;

import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.ChatColor;
import nl.chimpgamer.ultimateshout.UltimateShout;
import nl.chimpgamer.ultimateshout.hooks.VaultHook;
import org.bukkit.entity.Player;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUtils {
    private static final Pattern HEX_PATTERN = Pattern.compile("&#(?:[0-9a-fA-F]{6})");

    public static String parsePlaceholders(Player player, String text) {
        final VaultHook vaultHook = UltimateShout.getInstance().getPluginHookManager().getVaultHook();
        if (UltimateShout.getInstance().getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            text = PlaceholderAPI.setPlaceholders(player, text);
        }
        if (vaultHook.isEnabled() && vaultHook.getChat() != null) {
            String prefix = vaultHook.getChat().getPlayerPrefix(player);
            String suffix = vaultHook.getChat().getPlayerSuffix(player);
            text = text
                    .replace("%vault_prefix%", prefix)
                    .replace("%vault_suffix%", suffix)
                    .replace("%vault_rank%", vaultHook.getChat().getPrimaryGroup(player))
                    .replace("%vault_prefix_color%", prefix.substring(prefix.length() - 2))
                    .replace("%vault_suffix_color%", suffix.substring(suffix.length() - 2));
        }
        return text
                .replace("%playername%", player.getName())
                .replace("%displayname%", player.getDisplayName());
    }

    public static String formatColorCodes(String input) {
        return ChatColor.translateAlternateColorCodes('&', formatHexColors(input));
    }

    private static String formatHexColors(String input) {
        try {
            String result = input;
            net.md_5.bungee.api.ChatColor.class.getMethod("of", String.class);
            Matcher matcher = HEX_PATTERN.matcher(result);
            while (matcher.find()) {
                result = result.replace(HEX_PATTERN.pattern(), "" + ChatColor.of(matcher.group().replaceFirst("&", "")));
            }
            return result;
        } catch (Exception ignored) {
            return input;
        }
    }
}
