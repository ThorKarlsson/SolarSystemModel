package com.tgra;

import com.badlogic.gdx.graphics.Texture;

public class Atmosphere extends Planet{
	
	public Atmosphere(int i_stacks, int i_slices, Texture tex, Planet parent)
	{
		
		super (i_stacks, i_slices, tex, (parent.size + 0.03f), parent.distance);
		
	}
}
