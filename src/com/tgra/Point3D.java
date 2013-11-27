package com.tgra;

public class Point3D
{
	public float x;
	public float y;
	public float z;
	
	public Point3D(float xx, float yy, float zz)
	{
		x = xx;
		y = yy;
		z = zz;
	}
	
	public void set(float xx, float yy, float zz)
	{
		x = xx;
		y = yy;
		z = zz;
	}
	
	public void add(Vector3D v)
	{
		x += v.x;
		y += v.y;
		z += v.z;
	}
	
	public boolean equals(Point3D point)
	{
		if(this.x == point.x && this.y == point.y && this.z == point.z)
		{
			return true;
		}
		return false;
	}
	
	public String toString()
	{
		return (x + " " + y + " " + z);
	}
}
