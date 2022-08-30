package org.minechess.game.arena;

import org.bukkit.Location;

public class ArenaBoard {
    //layout 8x8 / real size 16x16 -> 2x2 for each slot

    private final Location location;

    public ArenaBoard(Location location) {
        this.location = location;
    }
    public Location getCenter(){
        return location.add(8,1,8);
    }


    /**
     * Lấy vị trí trên bàn này trùng với vị trí tương đối của vị tí hiện tại và một bàn khác.
     * Ví dụ Bàn other có vị trí 0:0:0, bạn hiện đang ở vị trí 1 2 3 và bàn này có vị trí 100 100 100
     * thí vị trí tương đối trả về sẽ có giá trị là 101 102 103. Điều này có nghĩa là nếu 2 bàn trông
     * giống nhau thì khi dịch chuyển giữa các vị trí tương đối sẽ trông như không dịch chuyển. Ngoài
     * ra, vị trí đươc trả về cũng sẽ thay đổi góc nhìn sao cho cái bàn trông như bị xoay ngược lại.
     */
    public Location getRelativeLocation(Location current,ArenaBoard other){
        Location newLoc = location.add(current.toVector().subtract(other.location.toVector()));
        newLoc.setYaw(180F-newLoc.getYaw());
        return newLoc;
    }
    public Location getRelativeLocation(Location current,Arena otherArena){
        if (otherArena==null) return getCenter();
        ArenaBoard other = otherArena.getBoard();
        Location newLoc = location.add(current.toVector().subtract(other.location.toVector()));
        newLoc.setYaw(180F-newLoc.getYaw());
        return newLoc;
    }


}
