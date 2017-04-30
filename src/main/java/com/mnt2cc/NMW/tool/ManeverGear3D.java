package com.mnt2cc.NMW.tool;

import java.util.ArrayList;

import com.mnt2cc.NMW.util.NMWUtil;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.entity.Entity;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.*;
import cn.nukkit.network.protocol.SetEntityLinkPacket;

public class ManeverGear3D {
	
	public static ManeverGear3D instance = new ManeverGear3D();
	private ArrayList<Anchor> anchors = new ArrayList<Anchor>();
	
	private ManeverGear3D(){
		Entity.registerEntity("anchor", Anchor.class, true);
	}
	
	public static ManeverGear3D getInstance(){
		return instance;
	}
	
	public void run(Player player){
		CompoundTag nbt = getNBT(player);
		FullChunk chunk = getChunk(player);
		Anchor anchor = (Anchor) Entity.createEntity("anchor", chunk, nbt, player);
		anchor.spawnToAll();
		anchor.setMotion(anchor.getMotion().multiply(1.5));
		for(Anchor a: anchors){
			if(a.shootingEntity.getName() == player.getName()){
				a.kill();
			}
			if(a.closed){
				anchors.remove(a);
			}
		}
		SetEntityLinkPacket pk = new SetEntityLinkPacket();
		pk.rider = anchor.getId();
		pk.riding = player.getId();
		pk.type = SetEntityLinkPacket.TYPE_RIDE;
		Server.broadcastPacket(player.getLevel().getPlayers().values(), pk);
		anchors.add(anchor);
	}

	private FullChunk getChunk(Player player) {
		FullChunk chunk = player.getLevel().getChunk(player.getFloorX() >> 4, player.getFloorZ() >> 4);
		return chunk;
	}

	private CompoundTag getNBT(Player p){
		double motionX = NMWUtil.round(-Math.sin(p.yaw / 180d * Math.PI) * Math.cos(-p.pitch / 180d * Math.PI), 3);
		double motionY = -NMWUtil.round(-Math.sin(-p.pitch / 180d * Math.PI), 3);
		double motionZ = NMWUtil.round(Math.cos(p.yaw / 180d * Math.PI) * Math.cos(-p.pitch / 180d * Math.PI), 3);
		CompoundTag nbt = new CompoundTag()
                .putList(new ListTag<DoubleTag>("Pos")
                        .add(new DoubleTag("", p.x))
                        .add(new DoubleTag("", p.y + p.getEyeHeight()))
                        .add(new DoubleTag("", p.z)))
                .putList(new ListTag<DoubleTag>("Motion")
                        .add(new DoubleTag("",motionX))
                        .add(new DoubleTag("",motionY))
                        .add(new DoubleTag("",motionZ)))
                .putList(new ListTag<FloatTag>("Rotation")
                        .add(new FloatTag("", (float) p.yaw))
                        .add(new FloatTag("", (float) p.pitch)));
		return nbt;
	}
}