package com.mnt2cc.NMW.tool;

import com.mnt2cc.NMW.util.NMWUtil;

import cn.nukkit.Player;
import cn.nukkit.math.Vector3;

public class Firework {
	
	public static void run(Player player){
		double motionX = NMWUtil.round(-Math.sin(player.yaw / 180d * Math.PI) * Math.cos(-player.pitch / 180d * Math.PI), 3);
		double motionY = -NMWUtil.round(-Math.sin(-player.pitch / 180d * Math.PI), 3);
		double motionZ = NMWUtil.round(Math.cos(player.yaw / 180d * Math.PI) * Math.cos(-player.pitch / 180d * Math.PI), 3);
		player.setMotion(new Vector3(motionX, motionY, motionZ).multiply(1.8));
	}
}
