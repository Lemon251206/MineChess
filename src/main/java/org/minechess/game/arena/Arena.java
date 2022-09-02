package org.minechess.game.arena;

import org.bukkit.entity.Player;
import org.minechess.game.battle.ChessBattle;

import java.util.HashMap;
import java.util.Map;

public class Arena {
    private final HashMap<ArenaBoard,Arena> boardMap = new HashMap<>();
    {boardMap.put(null,null);}
    //todo: load board to here

    private final ArenaHolder holder;
    private final ChessBattle battle;
    private final ArenaBoard  board;

    public Arena(ChessBattle chessBattle, Player player) {
        this.battle = chessBattle;
        this.holder = new ArenaHolder(this,player);
        Map.Entry<ArenaBoard, Arena> boardEntry = boardMap.entrySet().stream().filter(entry -> entry.getValue() != null).findFirst().orElse(null);
        board = boardEntry!=null?boardEntry.getKey():null;
    }

    public ArenaHolder getHolder() {
        return holder;
    }

    public ChessBattle getBattle() {
        return battle;
    }

    public ArenaBoard getBoard() {
        return board;
    }
}
