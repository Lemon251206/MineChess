package org.minechess;

import org.bukkit.plugin.java.JavaPlugin;

public final class MineChess extends JavaPlugin {

    private static MineChess instance;

    public static MineChess getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
    }

    @Override
    public void onDisable() {

    }
}
