package com.mnt2cc.NMW.magic;

import cn.nukkit.level.Level;
import cn.nukkit.level.particle.RedstoneParticle;
import cn.nukkit.math.Vector3;

public class BurnMagic extends Magic {
	
	public BurnMagic(Level level, Vector3 vec) {
		super(level, vec);
	}

	@Override
	public void draw(){

		Vector3 v = new Vector3(0,0,0);
		double x, z;
		for(double t=0d; t <= 22d; t+=0.025){
			x = Math.sin(2*t) * Math.cos(Math.PI*t);
			z = Math.sin(2*t) * Math.sin(Math.PI*t);
			
			x*=7;
			z*=7;
			
			v = new Vector3(this.startPoint.x+x+0.5, this.startPoint.y+1, this.startPoint.z+z+0.5);
			this.draw(new RedstoneParticle(v,1));
		}
	}
}
