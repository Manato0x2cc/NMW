package com.mnt2cc.NMW.magic;

import java.util.ArrayList;

import cn.nukkit.Player;
import cn.nukkit.level.Level;
import cn.nukkit.math.Vector3;

public class MagicFactory {
	
	private ArrayList<Class<? extends Magic>> magics = new ArrayList<Class<? extends Magic>>();
	
	/* Singleton */
	private static MagicFactory mf = new MagicFactory();
	
	private MagicFactory(){
		init();
	}
	
	private void init() {
		magics.add(BurnMagic.class);
		magics.add(BeamMagic.class);
	}

	public static MagicFactory getInstance(){
		return mf;
	}
	
	/////////////////////////////////////////////////////
	
	public Magic getMagic(int type, Level level, Vector3 vec){
		try {
			return magics.get(type).getConstructor(Level.class, Vector3.class).newInstance(level, vec);
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			return null;
		}
	}
	
	public Magic getMagic(int type, Player player){
		return this.getMagic(type, player.getLevel(), player);
	}
	
	public Magic getMagic(int type, Player player, Vector3 vec){
		return this.getMagic(type, player.getLevel(), vec);
	}
}
