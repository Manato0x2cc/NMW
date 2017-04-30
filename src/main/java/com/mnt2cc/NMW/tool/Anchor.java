package com.mnt2cc.NMW.tool;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.entity.Entity;
import cn.nukkit.entity.projectile.EntityArrow;
import cn.nukkit.entity.projectile.EntityProjectile;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.network.protocol.AddEntityPacket;
import cn.nukkit.network.protocol.SetEntityLinkPacket;

public class Anchor extends EntityProjectile{
	
	public static final int NETWORK_ID = EntityArrow.NETWORK_ID;
	
	@Override
    public float getWidth() {
        return 0.25f;
    }

    @Override
    public float getLength() {
        return 0.25f;
    }

    @Override
    public float getHeight() {
        return 0.25f;
    }

    @Override
    protected float getGravity() {
        return 0.02f;
    }

    @Override
    protected float getDrag() {
        return 0.01f;
    }

    @Override
    public boolean onUpdate(int currentTick) {
        if (this.closed) {
            return false;
        }

        this.timing.startTiming();

        boolean hasUpdate = super.onUpdate(currentTick);

        if (this.age > 600) {
            this.kill();
            hasUpdate = true;
        }

        if (this.isCollided) {
            this.kill();
        }

        this.timing.stopTiming();

        return hasUpdate;
    }
    
    @Override
    public void kill(){
    	SetEntityLinkPacket pk = new SetEntityLinkPacket();
		pk.rider = this.getId();
		pk.riding = this.shootingEntity.getId();
		pk.type = SetEntityLinkPacket.TYPE_RIDE;
		Server.broadcastPacket(this.shootingEntity.getLevel().getPlayers().values(), pk);
		
		super.kill();
    }

    @Override
    public void spawnTo(Player player) {
        AddEntityPacket pk = new AddEntityPacket();
        pk.type = Anchor.NETWORK_ID;
        pk.entityUniqueId = this.getId();
        pk.entityRuntimeId = this.getId();
        pk.x = (float) this.x;
        pk.y = (float) this.y;
        pk.z = (float) this.z;
        pk.speedX = (float) this.motionX;
        pk.speedY = (float) this.motionY;
        pk.speedZ = (float) this.motionZ;
        pk.metadata = this.dataProperties;
        player.dataPacket(pk);

        super.spawnTo(player);
    }
	public Anchor(FullChunk chunk, CompoundTag nbt, Entity shootingEntity) {
		super(chunk, nbt, shootingEntity);
	}

	@Override
	public int getNetworkId() {
		return Anchor.NETWORK_ID;
	}
	
}
