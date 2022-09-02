package org.minechess;

import org.bukkit.plugin.java.JavaPlugin;
import org.minechess.command.MainCommand;
import redempt.redlib.commandmanager.CommandCollection;
import redempt.redlib.commandmanager.CommandParser;

public final class MineChess extends JavaPlugin {

    private static MineChess instance;

    public static MineChess getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        registerCommand();
    }

    public void registerCommand(){
        CommandCollection collection = new CommandParser(this.getResource("command.rdcml")).parse();
        collection.register(this,"minechess",new MainCommand());

    }
    @Override
    public void onDisable() {

    }
}
