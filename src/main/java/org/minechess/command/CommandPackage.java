package org.minechess.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashMap;

public class CommandPackage {
    private final CommandSender sender;
    private final String[] arg;
    private final HashMap<String,String> meta = new HashMap<>();
    public CommandPackage(CommandSender sender, String[] arg){
        this.sender = sender;
        this.arg = arg;
    }

    public CommandPackage(CommandSender sender, String[] arg, HashMap<String, String> meta) {
        this(sender,arg);
        this.meta.putAll(meta);
    }

    public CommandSender getSender() {
        return sender;
    }

    public boolean isPlayer() {
        return (sender instanceof Player);
    }

    public Player getPlayer() {
        return ((Player) sender);
    }

    public String[] getArg() {
        return arg;
    }
    public CommandPackage getSubPackage(){
        String[] subArg = new String[0];
        if (arg.length>1) subArg = Arrays.copyOfRange(arg,1,arg.length);
        return new CommandPackage(sender,subArg,getMeta());
    }
    public HashMap<String, String> getMeta() {
        return meta;
    }
}
