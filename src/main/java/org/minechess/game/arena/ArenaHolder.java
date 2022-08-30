package org.minechess.game.arena;

import org.bukkit.entity.Player;

public class ArenaHolder {
    private final Player player;
    private final Arena arena;
    private int health = 100;

    public ArenaHolder(Arena arena,Player player){
        this.arena = arena;
        this.player = player;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Arena getArena() {
        return arena;
    }

    public Player getPlayer() {
        return player;
    }
}
