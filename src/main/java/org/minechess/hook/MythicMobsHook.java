package org.minechess.hook;

import io.lumine.mythic.api.adapters.AbstractLocation;
import io.lumine.mythic.api.mobs.MythicMob;
import io.lumine.mythic.api.mobs.entities.SpawnReason;
import io.lumine.mythic.bukkit.MythicBukkit;
import io.lumine.mythic.core.mobs.ActiveMob;
import io.lumine.mythic.core.mobs.MobExecutor;
import io.lumine.mythic.bukkit.utils.serialize.Position;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;


public class MythicMobsHook {
    private final boolean enable;
    private MobExecutor mobManager;

    public MythicMobsHook(){
        this.enable = Bukkit.getPluginManager().isPluginEnabled("MythicMobs");
        if (!enable) return;
        this.mobManager = MythicBukkit.inst().getMobManager();
    }

    /**
     * Chuyển Entity (xử lý bởi bukkit) thành ActiveMob (xử lý bởi MythicMobs)
     * @param entity Mục tiêu cần chuyển đổi sang ActiveMob
     * @return ActiveMob hoặc null nếu Entity đó không được quản lý bởi Mythicmobs
     */
    public ActiveMob getActiveMob(Entity entity){
        if (!enable) return null;
        return mobManager.getActiveMob(entity.getUniqueId()).orElse(null);
    }

    /**
     * Lấy MythicMob dùng cho triệu hồi bằng tên/id của mob đó
     * @param type Tên/ID của mob cần lấy
     * @return MythicMob được yêu cầu hoăc null nếu mob đó không tồn tại
     */
    public MythicMob getMythicMob(String type){
        return MythicBukkit.inst().getAPIHelper().getMythicMob(type);
    }

    /**
     * Triệu mồi một Mob tại điểm chỉ định với level cụ thể
     * @param type Loại Mob cần được triệu hồi ({@link MythicMobsHook#getMythicMob(String)})
     * @param level Level của mob được triệu hồi
     * @param location Vị trí Mob xuất hiện
     * @return Mob được triệu hồi dưới dạng ActiveMob
     */
    public ActiveMob summon(MythicMob type,double level, Location location){
        if (type==null|| level==0 || location.getWorld()==null) return null;
        AbstractLocation loc = new AbstractLocation(Position.of(location.getX(),location.getY(),location.getZ(),location.getWorld()));
        return type.spawn(loc,level, SpawnReason.SUMMON);
    }

    /**
     * Triệu mồi một Mob tại điểm chỉ định với level bằng 1
     * @param type Loại Mob cần được triệu hồi ({@link MythicMobsHook#getMythicMob(String)})
     * @param location Vị trí Mob xuất hiện
     * @return Mob được triệu hồi dưới dạng ActiveMob
     */
    public ActiveMob summon(MythicMob type, Location location){
        return summon(type,1,location);
    }

    /**
     * Triệu mồi một Mob tại điểm chỉ định với level bằng 1
     * @param type Loại Mob cần được triệu hồi (String)
     * @param location Vị trí Mob xuất hiện
     * @return Mob được triệu hồi dưới dạng ActiveMob
     */
    public ActiveMob summon(String type, Location location){
        return summon(getMythicMob(type),location);
    }

    /**
     * Làm cho một mob trở nên bất động (Không di chuyển và không bị đẩy lùi)
     * @param mob Mob được chỉ định
     */
    public void freeze(ActiveMob mob){
        mob.getEntity().setAI(false);
        mob.getEntity().getBukkitEntity().setInvulnerable(true);
    }

    /**
     * Hóa giải trạng thái bất động của mob (cho phép di chuyển và có thể bị đẩy lùi)
     * @param mob Mob được chỉ định
     */
    public void defrosting(ActiveMob mob){
        mob.getEntity().setAI(true);
        mob.getEntity().getBukkitEntity().setInvulnerable(false);
    }

}
