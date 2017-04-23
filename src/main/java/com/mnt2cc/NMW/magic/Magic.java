package com.mnt2cc.NMW.magic;

import com.mnt2cc.BMW.task.MagicTask;

import cn.nukkit.Server;
import cn.nukkit.level.Level;
import cn.nukkit.level.particle.Particle;
import cn.nukkit.math.Vector3;

public class Magic {
	public Vector3 startPoint;
	public Level level;
	
	public static int BURN = 0;
	public static int BEAM = 1;
	
	public Magic(Level level, Vector3 vec){
		this.level = level;
		this.startPoint = vec;
	}
	
	public static void run(Magic magic, int delay){
		Server.getInstance().getScheduler().scheduleDelayedTask(
				new MagicTask(magic), 
				delay
				);
	}
	
	public static void run(Magic magic){
		run(magic, 1);
	}
	
	
	public void draw(){};
	
	public void draw(Particle p){
		this.level.addParticle(p);
	}
	
}
