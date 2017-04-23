package com.mnt2cc.NMW.magic;

public class Line{
	
	public static final String PLUS = "pl";
	public static final String CONSTANT = "con";
	public static final String PARAMETRIC = "pa";
	
	//IMPORTANT: If you use this constant you must overwrite draw method.
	public static final String UNDEFINED = "?";
	String x_calc,y_calc,z_calc;
	public int red, green, blue;
	public Line(String x, String y, String z){
		this.x_calc = x;
		this.y_calc = y;
		this.z_calc = z;
		this.red = rand();
		this.green = rand();
		this.blue = rand();
	}
	
	public Line(String s){
		if(!s.equals(UNDEFINED)) System.out.println("引数が間違っています。Line.java");
		this.red = rand();
		this.green = rand();
		this.blue = rand();
	}
	
	public double[] draw(double t){
		double x, y, z;
		double[] xyz = new double[3];
		
		x = calc(t, x_calc);
		y = calc(t, y_calc);
		z = calc(t, z_calc);
		
		xyz[0] = x;
		xyz[1] = y;
		xyz[2] = z;
		return xyz;
	}
	
	private double calc(double n, String calc){
		if(calc == PARAMETRIC){
			return n;
		}
		
		if(calc == "sin"){
			return Math.sin(n);
		}
		
		if(calc == "-sin"){
			return -Math.sin(n);
		}
		
		if(calc == "cos"){
			return Math.cos(n);
		}
		
		if(calc == "-cos"){
			return -Math.cos(n);
		}
		
		if(calc.indexOf(Line.CONSTANT) != -1){
			calc = calc.replaceAll(Line.CONSTANT, "");
			try{
				return Double.parseDouble(calc);
			}catch(NumberFormatException e){
				return 0;
			}
		}
			
		if(calc.indexOf(Line.PLUS) != -1){
			calc = calc.replaceAll(Line.PLUS, "");
			try{
				return Double.parseDouble(calc)+n;
			}catch(NumberFormatException e){
				return 0;
			}
		}
		
		try{
			return Double.parseDouble(calc) * n;
		}catch(NumberFormatException e){
			return n;
		}
	}
	
	private int rand() {
		return (int) (Math.random() * 255);
	}
}
