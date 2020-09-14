package nl.chimpgamer.ultimateshout.hooks;

import nl.chimpgamer.ultimateshout.UltimateShout;

public class PluginHookManager {
    private final UltimateShout ultimateShout;
    private VaultHook vaultHook;

    public PluginHookManager(UltimateShout ultimateShout) {
        this.ultimateShout = ultimateShout;
    }

    public void load() {
        vaultHook = new VaultHook(ultimateShout);

        vaultHook.onHook();
    }

    public VaultHook getVaultHook() {
        return vaultHook;
    }
}