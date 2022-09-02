package org.minechess.command;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import redempt.redlib.commandmanager.CommandHook;

public class MainCommand implements Listener {

    //  /minechess create test_arena
    @CommandHook("arena_create")
    public void create(Player player, String arena){

    }

    @CommandHook("arena_edit")
    public void edit(Player player, String arena){

    }

    @CommandHook("arena_join")
    public void join(Player player, String arena){

    }

    @CommandHook("arena_spec")
    public void spectator(Player player, String arena){

    }

}
