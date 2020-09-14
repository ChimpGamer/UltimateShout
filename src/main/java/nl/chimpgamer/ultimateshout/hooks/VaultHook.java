package nl.chimpgamer.ultimateshout.hooks;

import net.milkbowl.vault.chat.Chat;
import nl.chimpgamer.ultimateshout.UltimateShout;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.jetbrains.annotations.Nullable;

public class VaultHook extends PluginHook {
    private Chat chat = null;

    public VaultHook(UltimateShout ultimateShout) {
        super(ultimateShout, "Vault");
    }

    @Override
    public void onHook() {
        RegisteredServiceProvider<Chat> rsp = getUltimateShout().getServer().getServicesManager().getRegistration(Chat.class);
        if (rsp == null) {
            return;
        }
        this.chat = rsp.getProvider();
    }

    @Nullable
    public Chat getChat() {
        return chat;
    }
}