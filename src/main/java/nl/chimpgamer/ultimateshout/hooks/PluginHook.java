package nl.chimpgamer.ultimateshout.hooks;

import nl.chimpgamer.ultimateshout.UltimateShout;

public abstract class PluginHook {
    private final UltimateShout ultimateShout;
    private final String pluginName;

    public PluginHook(UltimateShout ultimateShout, String pluginName) {
        this.ultimateShout = ultimateShout;
        this.pluginName = pluginName;
    }

    public abstract void onHook();

    public boolean isEnabled() {
        return ultimateShout.isPluginEnabled(this.getPluginName());
    }

    public String getPluginName() {
        return pluginName;
    }

    protected UltimateShout getUltimateShout() {
        return ultimateShout;
    }
}