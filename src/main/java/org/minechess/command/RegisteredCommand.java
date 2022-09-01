package org.minechess.command;

import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.minechess.MineChess;

import java.util.List;

public class RegisteredCommand extends CommandPart implements CommandExecutor, TabCompleter {

    public RegisteredCommand(String name){
        super(name);
        try {
            CommandMap cm = (CommandMap) Bukkit.getPluginManager().getClass().getDeclaredField("commandMap").get(Bukkit.getPluginManager());
            cm.register(name,this);

        } catch ( NoSuchFieldException | IllegalAccessException e ) {
            e.printStackTrace();
        } finally {
            PluginCommand cmd = MineChess.getInstance().getCommand("name");
            assert cmd != null;
            cmd.setExecutor(this);
            cmd.setTabCompleter(this);
        }
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command c, @NotNull String s, @NotNull String[] arg) {
        return execute(new CommandPackage(sender,arg));
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command c, @NotNull String s, @NotNull String[] arg) {
        return getChildrenTab(new CommandPackage(sender,arg));
    }
}
