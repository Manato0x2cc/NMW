package com.mnt2cc.NMW.task;

import com.mnt2cc.NMW.magic.Magic;
import com.mnt2cc.NMW.magic.ShieldMagic;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.item.Item;
import cn.nukkit.level.Level;
import cn.nukkit.scheduler.Task;

public class ShieldTask extends Task {

	@Override
	public void onRun(int arg0) {
		for(Level l: Server.getInstance().getLevels().values()){
			for(Player p: l.getPlayers().values()){
				if(p.getInventory().getItemInHand().getId() == Item.COOKIE){
					ShieldMagic shield = new ShieldMagic(p);
					Magic.run(shield);
				}
			}
		}
	}

}
