package com.mnt2cc.NMW.task;

import java.util.HashMap;
import java.util.Map.Entry;

import com.mnt2cc.NMW.util.NMWUtil;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.level.Level;
import cn.nukkit.math.Vector3;
import cn.nukkit.scheduler.Task;

public class FireworkTask extends Task {
	
	public static final int TICK = 25;
	
	public FireworkTask(){
		super();
	}
	
	private HashMap<String, Vector3> map = new HashMap<String, Vector3>();
	@Override
	public void onRun(int arg0) {
		String name;
		Vector3 v;
		for(Entry<String, Vector3> entry : map.entrySet()) {
		    name = entry.getKey();
		    v = entry.getValue();
		    Player player = Server.getInstance().getPlayer(name);
		    if(player == null) continue;
		    player.sendPopup("Speed: "+String.valueOf(NMWUtil.calcSpeedPerSecond(v, player, TICK/20))+"m/s");
		}
		
		for(Level l: Server.getInstance().getLevels().values()){
			for(Player p: l.getPlayers().values()){
				if(p.isGliding()) map.put(p.getName(), new Vector3(p.x,p.y,p.z));
				else if(!p.isGliding() && map.containsKey(p.getName())) map.remove(p.getName());
			}
		}
	}

}
