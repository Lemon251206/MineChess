package org.minechess.game;

import org.minechess.game.battle.ChessBattle;

import java.util.ArrayList;

public class BattleManager {
    
    private final ArrayList<ChessBattle> battleList = new ArrayList<>();
    public BattleManager(){

    }
    public ArrayList<ChessBattle> getBattleList() {
        return battleList;
    }
}
