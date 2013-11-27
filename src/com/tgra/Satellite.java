package com.tgra;

import com.badlogic.gdx.graphics.Texture;

public class Satellite extends Planet {
	Planet orbitPoint; 
	
	public Satellite(int i_stacks, int i_slices, Texture tex, float size, float AUdistance, Planet orbitPoint) 
	{
		super(i_stacks, i_slices, tex, size, AUdistance);
		this.orbitPoint = orbitPoint;
		x = (orbitPoint.x + (AUdistance * 4400 * orbitPoint.size)); //this will place the planet at its orbit point plus the distance of the satellite from the orbitPoint
		y =  2;
		rotationAngle = 30;
	}
	
	public Satellite(int i_stacks, int i_slices, Texture tex, float size, float AUdistance, Planet orbitPoint, int start) 
	{
		super(i_stacks, i_slices, tex, size, AUdistance);
		this.orbitPoint = orbitPoint;
		
		y = 2;
		rotationAngle = 30;
		
		switch(start){
		case 0:
			x = (orbitPoint.x - (AUdistance * 4400 * orbitPoint.size));
			break;
		default:
			x = (orbitPoint.x + (AUdistance * 4400 * orbitPoint.size));
			break;
		}
	}
	
	public void update(float time)
	{
		if(rotationAngle == 360)
		{
			rotationAngle = 0;
		}
		rotationAngle += 1 * time;
	}
	
}
