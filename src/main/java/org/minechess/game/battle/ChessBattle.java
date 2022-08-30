package org.minechess.game.battle;

import org.bukkit.entity.Player;
import org.minechess.game.arena.Arena;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class ChessBattle {
    private final ArrayList<Arena> arenaList = new ArrayList<>();
    private final ArrayList<Player> spectator = new ArrayList<>();
    private final HashMap<Player,Arena> currentArena = new HashMap<>();

    abstract int getArenaLimit();

    /**
     * Thêm một người chơi vào trận đấu và tạo một arena
     * mới hoặc thêm với tư cách khán giả nếu đã đủ người
     * @param player Người chơi sẽ tham gia trân đấu
     */
    public void playerJoin(Player player){
        if (isInArena(player))
        if (arenaList.size()>=getArenaLimit()) {
            playerSpectating(player);
            return;
        }
        teleportPlayer(player,createArena(player));
        //TODO call an event (PlayerJoinBattleEvent)
    }

    /**
     * Đưa một người ra khỏi trận đấu và trở về điểm spawn.
     * Nếu người đó là một người chơi thì arena của họ cũng
     * như là các mob sẽ bị loại bỏ.
     * @param player Người chơi sẽ rời trận đấu
     */
     public void playerLeave(Player player){
         if(!isInArena(player) && !spectator.contains(player)) return;

     }

    /**
     * Thêm một người chơi với tư cách khán giả.
     * Vô hình, tự do bay lượn và tự do dịch chuyển
     * giữa các arena
     * @param player Người chơi sẽ làm khán giả của trận đấu
     */
    void playerSpectating(Player player){
        spectator.add(player);
        teleportPlayer(player,arenaList.get(0));
    }

    public void teleportPlayer(Player player, Arena arena){
        teleportPlayer(player,arena,false);
    }
    public void teleportPlayer(Player player, Arena arena,boolean center){
        player.teleport(center?arena.getBoard().getCenter():arena.getBoard().getRelativeLocation(player.getLocation(),currentArena.get(player)));
        currentArena.put(player,arena);
    }

    public boolean isInArena(Player player){
        return arenaList.stream().anyMatch(arena -> arena.getHolder().getPlayer().equals(player));
    }
    private Arena createArena(Player player){
        Arena arena = new Arena(this,player);
        arenaList.add(arena);
        return arena;
    }
    public Arena getArena(Player player){
        return arenaList.stream().filter(arena -> arena.getHolder().getPlayer().equals(player)).findFirst().orElse(null);
    }

    void removeArena(Player p){

    }

}
