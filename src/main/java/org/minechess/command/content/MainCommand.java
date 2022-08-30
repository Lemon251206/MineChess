package org.minechess.command.content;

import io.lumine.mythic.api.MythicProvider;
import io.lumine.mythic.api.mobs.MythicMob;
import io.lumine.mythic.bukkit.MythicBukkit;
import io.lumine.mythic.core.mobs.MobType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.minechess.command.CommandPart;
import org.minechess.command.RegisteredCommand;
import org.minechess.hook.MythicMobsHook;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    }
}
