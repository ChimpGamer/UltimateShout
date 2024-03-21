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
        if (!isEnabled()) return;
        RegisteredServiceProvider<Chat> rsp = getUltimateShout().getServer().getServicesManager().getRegistration(Chat.class);
        if (rsp == null) {
            getUltimateShout().getLogger().warning("It seems that vault is not installed. Proceeding without Vault support.");
            return;
        }
        this.chat = rsp.getProvider();
        getUltimateShout().getLogger().info("Successfully hooked into Vault. Using " + chat.getName() + " via vault as chat provider.");
    }

    @Nullable
    public Chat getChat() {
        return chat;
    }
}