package org.minechess.command.content;

import io.lumine.mythic.bukkit.MythicBukkit;
import org.bukkit.entity.Player;
import org.minechess.command.CommandPart;
import org.minechess.command.RegisteredCommand;
import org.minechess.hook.MythicMobsHook;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

public class MainCommand {
    public MainCommand(){
        RegisteredCommand command = new RegisteredCommand("minechess");

        command.addChildren(
                new CommandPart("summon")
                        .addChildren(new CommandPart("mobType")
                                .setCompleter(pack -> {
                                    List<String> tab = new ArrayList<>();
                                    MythicBukkit
                                            .inst()
                                            .getMobManager()
                                            .getMobTypes()
                                            .forEach(mt -> tab.add(mt.getInternalName()));
                                    return tab;
                                })
                                .setExecutor(pack -> {
                                    if (!(pack.getSender() instanceof Player)) return false;
                                    new MythicMobsHook().summon(
                                            pack.getArg()[0],
                                            ((Player)pack.getSender()).getLocation());
                                    return true;
                                })
                        )
        );

        command.addChildren(arena());
    }

    private CommandPart arena() {
        CommandPart main = new CommandPart("arena");
        CommandPart create = new CommandPart("create");
        CommandPart edit = new CommandPart("edit");
        CommandPart help = new CommandPart("help");

        main.setCompleter(pack -> {
            String[] tabs = {"create", "edit", "help"};
            return Lists.newArrayList(tabs);
        });
        main.setExecutor(pack -> {
            pack.getSender().sendMessage("Sai lệnh, sử dụng /arena help để xem danh sách lệnh.");
            return false;
        });
        create.setCompleter(pack -> {
            String[] tabs = {"[<arena>]"};
            return Lists.newArrayList(tabs);
        });
        create.setExecutor(pack -> {

            return true;
        });
        edit.setCompleter(pack -> {
            String[] tabs = {"[<arena>]"};
            return Lists.newArrayList(tabs);
        });
        edit.setExecutor(pack -> {
            
            return true;
        });
        help.setExecutor(pack -> {
            
            return true;
        });

        main.addChildren(create);
        main.addChildren(edit);
        main.addChildren(help);
        return main;
    }
}
