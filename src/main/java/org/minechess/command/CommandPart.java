package org.minechess.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Function;

public class CommandPart extends Command {

    private Function<CommandPackage, Boolean> executor = null;
    private Function<CommandPackage, List<String>> completer = null;
    private final ArrayList<CommandPart> children = new ArrayList<>();

    public CommandPart(String name) {
        super(name);
    }


    public CommandPart setExecutor(Function<CommandPackage,Boolean> function){
        executor = function;
        return this;
    }
    public CommandPart setCompleter(Function<CommandPackage, List<String>> function){
        completer = function;
    return this;
    }

    /**
     * Kích hoạt executor (nếu có) và đẩy package xuống các children nếu có >1 arg <br>
     * Cơ Chế đẩy children có 2 kiểu tĩnh và động ({@link CommandPart#addChildren(CommandPart)}) <br>
     * Kiểu tĩnh không có completer còn kiểu động thì có<br>
     * Kiểu tĩnh sẽ không kích hoạt Completer. Thay vào đó, tên của nó sẽ được sử dụng
     * Còn kiểu động thì ngược lại, Tên của nó chỉ để hệ thống phân biệt còn Completer sẽ được sử dụng<br>
     * <br>
     * Bổ sung: để dễ hiểu thì kiểu động sẽ dạng như tên người chơi hay loại mythicmobs
     * còn kiểu tĩnh sẽ dạng như reload, getItem, join, leave
     */
    public boolean execute(CommandPackage pack){
        boolean success = false;
        if (executor!=null) success = executor.apply(pack);
        CommandPackage subPackage = pack.getSubPackage();
        if (subPackage.getArg().length==0 && !Objects.equals(subPackage.getArg()[0], "")) return success;
        String nextArg = subPackage.getArg()[0];
        for (CommandPart part : children) {
            if ( part.getTab(subPackage).contains(nextArg) )
            return part.execute(subPackage);
        }
        return success;
    }

    @Override
    public boolean execute(@NotNull CommandSender commandSender, @NotNull String s, @NotNull String[] strings) {
        return execute(new CommandPackage(commandSender,strings));
    }
    public List<String> getTab(CommandPackage pack){
        if (completer==null) return Collections.singletonList(super.getName());
        return completer.apply(pack);
    }
    public List<String> getChildrenTab(CommandPackage pack){
        CommandPackage subPackage = pack.getSubPackage();
        if (subPackage.getArg().length==0) {
            List<String> tab = getTab(pack);
            String arg = pack.getArg()[0];
            tab.sort((o1, o2) -> {
                int a = 0;
                int b = 0;
                if (o1.startsWith(arg)) a = 2;
                else if (o1.contains(arg)) a = 1;
                if (o2.startsWith(arg)) b = 2;
                else if (o2.contains(arg)) b = 1;
                int r = b-a!=0?b-a:o1.length()-o2.length();
                if (r!=0) return r;
                return String.CASE_INSENSITIVE_ORDER.compare(o1,o2);
            });
        }
        String nextArg = subPackage.getArg()[0];
        for (CommandPart part : children) {
            if ( part.getTab(subPackage).contains(nextArg) )
                return part.getChildrenTab(subPackage);
        }
        return new ArrayList<>();
    }
    public CommandPart addChildren(CommandPart part){
        children.add(part);
        return this;
    }

}
