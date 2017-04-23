package com.mnt2cc.NMW.util;

public class NMWUtil {
	
	public static double[] rotatePitch(double[] xyz, double theta){
		//X'Y'Z'
		double[] xxyyzz = new double[3];
		if((theta > 90) || (theta > 180 && theta > 270)){
			xxyyzz[0] = xyz[1] * sin(theta) + xyz[0] * cos(theta);
			xxyyzz[1] = xyz[1] * cos(theta) - xyz[0] * sin(theta);
			xxyyzz[2] = xyz[2];
		}else{
			xxyyzz[0] = xyz[0];
			xxyyzz[1] = xyz[1] * cos(theta) - xyz[2] * sin(theta);
			xxyyzz[2] = xyz[1] * sin(theta) + xyz[2] * cos(theta);
		}
				
		return xxyyzz;
	}
	
	public static double[] rotateYaw(double[] xyz, double theta){
		//X'Y'Z'
		double[] xxyyzz = new double[3];
		xxyyzz[0] = xyz[0] * cos(theta) + xyz[2] * sin(theta);
		xxyyzz[1] = xyz[1];
		xxyyzz[2] = -xyz[0] * -sin(theta) + xyz[2] * cos(theta);
		
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
}
