package com.mnt2cc.NMW.util;

import cn.nukkit.math.Vector3;

public class NMWUtil {
	
	public static double[] rotatePitch(double[] xyz, double theta){
		//X'Y'Z'
		double[] xxyyzz = new double[3];
		xxyyzz[0] = xyz[0];
		xxyyzz[1] = xyz[1] * cos(theta) - xyz[2] * sin(theta);
		xxyyzz[2] = xyz[1] * sin(theta) + xyz[2] * cos(theta);

				
		return xxyyzz;
	}
	
	public static double[] rotateYaw(double[] xyz, double theta){
		//X'Y'Z'
		double[] xxyyzz = new double[3];
		xxyyzz[0] = xyz[0] * cos(theta) + xyz[2] * sin(theta);
		xxyyzz[1] = xyz[1];
		xxyyzz[2] = -xyz[0] * sin(theta) + xyz[2] * cos(theta);
		
		theta = Math.toDegrees(theta);
		if(!(theta < 180) || !(theta > 270 && theta < 360)){
			double dum = xxyyzz[0];
			xxyyzz[0] = xxyyzz[2];
			xxyyzz[2] = dum;
		}
				
		return xxyyzz;
	}
	
	public static double cos(double d){
		return Math.cos(d);
	}
	public static double sin(double d){
		return Math.sin(d);
	}
	
	public static double round(double d, int i) {
		d = d * Math.pow(10, i);
		return Math.round(d) / Math.pow(10, i);
	}
	
	public static double calcSpeedPerSecond(Vector3 from, Vector3 to, double second){
		return NMWUtil.calcSpeedPerSecond(NMWUtil.distance(from, to), second);
	}
	
	public static double distance(Vector3 from, Vector3 to) {
		return NMWUtil.round(Math.sqrt(Math.pow(to.x - from.x, 2) + Math.pow(to.y - from.y, 2) + Math.pow(to.z - from.z, 2)), 2);
	}

	public static double calcSpeedPerSecond(double distance, double second){
		return distance / second;
	}
}
