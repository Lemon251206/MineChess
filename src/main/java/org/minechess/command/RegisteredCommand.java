package org.minechess.command;

import io.lumine.mythic.bukkit.utils.command.CommandMapUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.plugin.SimplePluginManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.minechess.MineChess;

import java.lang.reflect.Field;
import java.util.List;

public class RegisteredCommand extends CommandPart implements CommandExecutor, TabCompleter {

    public RegisteredCommand(String name){
        super(name);
        try {
            Field f = Bukkit.getPluginManager().getClass().getDeclaredField("commandMap");
            f.setAccessible(true);
            CommandMap cm = (CommandMap) f.get(Bukkit.getPluginManager());
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
