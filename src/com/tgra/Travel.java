package com.tgra;

public class Travel {

	Planet destination;
	Point3D pos1;
	Point3D pos2;
	Point3D pos3;
	Point3D pos4;
	Point3D returnPos;
	float elapsedTime;
	
	public Travel(Planet destination, Point3D pos)
	{
		this.destination = destination;
		this.pos1 = pos;
		pos4 = new Point3D(destination.x, 2*destination.size, 3*destination.size);
		
		pos2 = new Point3D(pos1.x, destination.size, pos.z - 500);
		pos3 = new Point3D(pos4.x, destination.size, -500);
		
		
		elapsedTime = 0;
		returnPos = pos1;
	}
	public Travel()
	{
		pos1 = new Point3D(0,0,0);
		pos2 = new Point3D(0,0,0);
		pos3 = new Point3D(0,0,0);
		pos4 = new Point3D(0,0,0);
		returnPos = new Point3D(0,0,0);
	}
	public void setDestination(Planet destination, Point3D pos)
	{
		this.destination = destination;
		this.pos1.set(pos.x, pos.y, pos.z);
		pos4.set(destination.x, 2*destination.size, -3*destination.size);
		pos2 = new Point3D(pos1.x, destination.size, pos.z - 500);
		pos3 = new Point3D(pos4.x, destination.size, -500);
		
		elapsedTime = 0;
		returnPos.set(pos.x, pos.y, pos.z);
	}
	
	public Point3D update(float time)
	{
		elapsedTime += time;
		if(elapsedTime == 0)
		{
			returnPos = pos1;
		}
		else if(elapsedTime >= 10) //traveling to a planet will take 10 seconds
		{
			returnPos.set(pos4.x, pos4.y, pos4.z);
		}
		else
		{
			float t = elapsedTime / 10f;
			
			returnPos.x = (1.0f - t)*(1.0f - t)*(1.0f - t)*pos1.x
			   + 3*(1.0f - t)*(1.0f - t)*t*pos2.x
			   + 3*(1.0f - t)*t*t*pos3.x
			   + t*t*t*pos4.x;
			returnPos.y = (1.0f - t)*(1.0f - t)*(1.0f - t)*pos1.y
			   + 3*(1.0f - t)*(1.0f - t)*t*pos2.y
			   + 3*(1.0f - t)*t*t*pos3.y
			   + t*t*t*pos4.y;
			returnPos.z = (1.0f - t)*(1.0f - t)*(1.0f - t)*pos1.z
					   + 3*(1.0f - t)*(1.0f - t)*t*pos2.z
					   + 3*(1.0f - t)*t*t*pos3.z
					   + t*t*t*pos4.z;
		}
		
		return returnPos;
		
	}
}
