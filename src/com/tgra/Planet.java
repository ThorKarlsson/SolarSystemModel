package com.tgra;

import java.nio.FloatBuffer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.BufferUtils;

public class Planet
{
	int stacks;
	int slices;
	FloatBuffer vertexBuffer;
	FloatBuffer normalBuffer;
	FloatBuffer textureBuffer;
	int vertexCount;
	Texture tex;
	float size;
	float distance;
	
	float x = 0;
	float y = 0;
	float z = 0;
	float rotationAngle;
	public Planet(int i_stacks, int i_slices, Texture tex, float size, float AUdistance)
	{
		this.tex = tex;
		this.size = size;
		stacks = i_stacks;
		slices = i_slices;
		vertexCount = 0;
		distance = AUdistance;
		
		x = (distance * 1000); //1 AU (astronomical unit) is equal to to the distance from the earth to the sun)
		
		float[] array = new float[(stacks)*(slices+1)*6];
		float stackInterval = (float)Math.PI / (float)stacks;
		float sliceInterval = 2.0f*(float)Math.PI / (float)slices;
		
		float[] texArray = new float[(stacks)*(slices+1)*4];
		
		float stackAngle, sliceAngle;
		for(int stackCount = 0; stackCount < stacks; stackCount++)
		{
			stackAngle = stackCount * stackInterval;
			for(int sliceCount = 0; sliceCount < slices+1; sliceCount++)
			{
				sliceAngle = sliceCount * sliceInterval;
				array[vertexCount*3] = 	 (float)Math.sin(stackAngle) * (float)Math.cos(sliceAngle);
				array[vertexCount*3 + 1] = (float)Math.cos(stackAngle);
				array[vertexCount*3 + 2] = (float)Math.sin(stackAngle) * (float)Math.sin(sliceAngle);

				array[vertexCount*3 + 3] = (float)Math.sin(stackAngle + stackInterval) * (float)Math.cos(sliceAngle);
				array[vertexCount*3 + 4] = (float)Math.cos(stackAngle + stackInterval);
				array[vertexCount*3 + 5] = (float)Math.sin(stackAngle + stackInterval) * (float)Math.sin(sliceAngle);
				
				texArray[vertexCount*2] = (float)sliceCount / (float)slices;
				texArray[vertexCount*2 + 1] = (float)stackCount / (float)stacks;
				texArray[vertexCount*2 + 2] = (float)sliceCount / (float)slices;
				texArray[vertexCount*2 + 3] = (float)(stackCount+1) / (float)stacks;
				
				vertexCount += 2;
			}
		}
		vertexBuffer = BufferUtils.newFloatBuffer(vertexCount*3);
		vertexBuffer.put(array);
		vertexBuffer.rewind();
		normalBuffer = BufferUtils.newFloatBuffer(vertexCount*3);
		normalBuffer.put(array);
		normalBuffer.rewind();
		textureBuffer = BufferUtils.newFloatBuffer(vertexCount*2);
		textureBuffer.put(texArray);
		textureBuffer.rewind();
	}

	public void draw()
	{
		Gdx.gl11.glShadeModel(GL11.GL_SMOOTH);
		Gdx.gl11.glEnableClientState(GL11.GL_NORMAL_ARRAY);
		Gdx.gl11.glVertexPointer(3, GL11.GL_FLOAT, 0, vertexBuffer);
		Gdx.gl11.glNormalPointer(GL11.GL_FLOAT, 0, normalBuffer);
		
		Gdx.gl11.glEnable(GL11.GL_TEXTURE_2D);
		Gdx.gl11.glEnableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
		
		Gdx.gl11.glTexCoordPointer(2, GL11.GL_FLOAT, 0, textureBuffer);
		tex.bind();
		
		for(int i = 0; i < vertexCount; i += (slices+1)*2)
		{
			Gdx.gl11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, i, (slices+1)*2);
		}
		Gdx.gl11.glDisable(GL11.GL_TEXTURE_2D);
		Gdx.gl11.glDisableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
		Gdx.gl11.glDisableClientState(GL11.GL_NORMAL_ARRAY);
	}
	
	public void update(float time)
	{
		rotationAngle += (size) * time;
		if(rotationAngle == 360)
		{
			rotationAngle = 0;
		}
	}
}
