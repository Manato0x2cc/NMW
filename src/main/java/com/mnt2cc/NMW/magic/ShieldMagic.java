package com.mnt2cc.NMW.magic;

import com.mnt2cc.NMW.util.NMWUtil;

import cn.nukkit.Player;
import cn.nukkit.level.particle.FlameParticle;
import cn.nukkit.math.Vector3;

public class ShieldMagic extends Magic {
	
	private static final double DIST = 0.5;

	private Player player;
	public ShieldMagic(Player player) {
		super(player.getLevel(), player);
		this.player = player;	
	}
	
	@Override
	public void draw(){
		Vector3 vec = new Vector3(player.x, player.y+player.getEyeHeight(), player.z);
		double yaw = player.getYaw();
		Line[] lines = new Line[]{
				new Line("-1"+Line.PLUS, "2"+Line.CONSTANT, DIST+Line.CONSTANT),
				new Line("0.5", Line.PARAMETRIC, DIST+Line.CONSTANT),
				new Line("-0.5", Line.PARAMETRIC, DIST+Line.CONSTANT),
				new Line("-1"+Line.PLUS, "0.7"+Line.CONSTANT, DIST+Line.CONSTANT),
				new Line(Line.UNDEFINED){
					@Override
					public double[] draw(double t){
						double x,y,z;					
						x = 0.5 * t - 1;
						y = t+0.7;
						z = DIST;
						double[] xxyyzz = new double[]{x,y,z};
						return xxyyzz;
					}
				},
				new Line(Line.UNDEFINED){
					@Override
					public double[] draw(double t){
						double x,y,z;					
						x = -0.5 * t + 1;
						y = t+0.7;
						z = DIST;
						double[] xxyyzz = new double[]{x,y,z};
						return xxyyzz;
					}
				},
				new Line(Line.UNDEFINED){
					@Override
					public double[] draw(double t){
						double x,y,z;					
						x = 3 * Math.sin(t*Math.PI/2)*Math.cos(t*Math.PI/2);
						y = 3 *  0.5*(1-Math.cos(Math.PI*t)) -0.12;
						z = DIST;
						
						double[] xxyyzz = new double[]{x,y,z};
						return xxyyzz;
					}
				}
		};

		for(double t = 0; t <= 2; t+=0.1){
			for(Line l: lines){
				double[] xyz = l.draw(t);
				xyz = NMWUtil.rotateYaw(xyz, Math.toRadians(yaw+90));
				vec = new Vector3(startPoint.x+xyz[0], startPoint.y+xyz[1], startPoint.z+xyz[2]);
				this.draw(new FlameParticle(vec));
			}
		}
		
	}

}
