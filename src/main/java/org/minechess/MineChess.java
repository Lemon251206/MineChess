package org.minechess;

import org.bukkit.plugin.java.JavaPlugin;
import org.minechess.command.content.MainCommand;

public final class MineChess extends JavaPlugin {

    private static MineChess instance;

    public static MineChess getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        new MainCommand();
    }

    @Override
    public void onDisable() {

    }
}
