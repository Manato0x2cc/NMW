package com.mnt2cc.NMW.magic;

import com.mnt2cc.NMW.util.NMWUtil;

import cn.nukkit.level.Level;
import cn.nukkit.level.particle.DustParticle;
import cn.nukkit.math.Vector3;

public class BeamMagic extends Magic {
	
	double yaw = 0;
	double pitch = 0;
	private static final double RADIUS = 0.6;
	
	public BeamMagic(Level level, Vector3 vec) {
		super(level, vec);
	}
	
	@Override
	public void draw(){
		Line[] lines = new Line[8];
		lines[0] = new Line("sin", "cos", Line.PARAMETRIC);
		lines[1] = new Line("-sin", "cos", Line.PARAMETRIC);
		lines[2] = new Line("sin", "-cos", Line.PARAMETRIC);
		lines[3] = new Line("-sin", "-cos", Line.PARAMETRIC);
		lines[4] = new Line("cos", "sin", Line.PARAMETRIC);
		lines[5] = new Line("-cos", "sin", Line.PARAMETRIC);
		lines[6] = new Line("cos", "-sin", Line.PARAMETRIC);
		lines[7] = new Line("-cos", "-sin", Line.PARAMETRIC);
		Line line;
		Vector3 vec;
		for(double t=0d; t<=18d; t+=0.1){
			for(int i = 0; i < lines.length; i++){
				line = lines[i];
				double[] xyz = line.draw(t);
				xyz = this.turn(xyz, yaw, pitch);
				
				xyz = new double[]{xyz[0]*RADIUS, xyz[1]*RADIUS, xyz[2]*RADIUS};
				
				vec = new Vector3(startPoint.x+xyz[0], startPoint.y+xyz[1], startPoint.z+xyz[2]);
				this.draw(new DustParticle(vec, line.red, line.green, line.blue));
			}
		}
	}

	public void setYaw(double d) {
		this.yaw = d;
	}
	public void setPitch(double pitch) {
		this.pitch = pitch;
	}
	
	private double[] turn(double[] xyz, double yaw, double pitch) {
		double[] xxyyzz = new double[3];
		
		xxyyzz = NMWUtil.rotatePitch(xyz, Math.toRadians(pitch));
		xxyyzz = NMWUtil.rotateYaw(xxyyzz, Math.toRadians(yaw+90));
		
		return xxyyzz;
	}
}
