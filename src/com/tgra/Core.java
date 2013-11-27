package com.tgra;
import java.nio.FloatBuffer;
import java.util.ArrayList;

import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.BufferUtils;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;


public class Core implements ApplicationListener
{
	float rotateAngle = 0;
	float x = 5;
	float y = -2;
	float z = 0;
	float speed = 5;
	float elapsedTime;
	float warp = 50000;
	
	Travel travel;
	boolean freeFlight = true;
	boolean destinationInput = false;
	Planet destination;
	
	Camera cam;
	
	SkySphere universe;
	
	Planet sun;
	Planet mercury;
	Planet venus;
	Planet earth;
	Planet earthClouds;
	Planet mars;
	Planet jupiter;
	Planet saturn;
	Planet uranus;
	Planet neptune;
	Planet pluto;
	
	Satellite moon;
	
	Satellite io;
	Satellite europa;
	Satellite ganymede;
	Satellite callisto;
	
	Satellite titan;
	Satellite tethys;
	Satellite rhea;
	Satellite dione;
	Satellite lapetus;
	
	Atmosphere earthAtmosphere;
	Atmosphere jupiterAtmosphere;
	
	ArrayList<Planet> planets = new ArrayList<Planet>();
	ArrayList<Satellite> moons = new ArrayList<Satellite>();
	ArrayList<Atmosphere> atmospheres = new ArrayList<Atmosphere>();
	
	@Override
	public void create() {
		
		travel = new Travel();
		/** All textures provided by http://planetpixelemporium.com  with the exception of Jupiters moons which were provided by http://www.planetaryvisions.com/index.php*/
		Texture uniTex = new Texture("textures/universe.jpg");
		
		Texture sunTex = new Texture("textures/sun.jpg");
		
		Texture mercuryTex = new Texture("textures/mercury.jpg");
		Texture venusTex = new Texture("textures/venus.jpg");
		
		Texture earthTex = new Texture("textures/earth.jpg");
		Texture earthCloudsTex = new Texture("textures/Clouds.png");
		Texture moonTex = new Texture("textures/moon.jpg");
		
		Texture marsTex = new Texture("textures/mars.jpg");
		
		Texture jupiterTex = new Texture("textures/jupiter.jpg");
		Texture ioTex = new Texture("textures/io.jpg");
		Texture europaTex = new Texture("textures/europa.jpg");
		Texture ganymedeTex = new Texture("textures/ganymede.jpg");
		Texture callistoTex = new Texture("textures/callisto.jpg");
		
		Texture saturnTex = new Texture("textures/saturn.jpg");
		Texture titanTex = new Texture("textures/titan.jpg");
		Texture tethysTex = new Texture("textures/tethys.jpg");
		Texture rheaTex = new Texture("textures/rhea.jpg");
		Texture dioneTex = new Texture("textures/dione.jpg");
		Texture lapetusTex = new Texture("textures/lapetus.jpg");
		
		Texture uranusTex = new Texture("textures/uranus.jpg");
		Texture neptuneTex = new Texture("textures/neptune.jpg");
		Texture plutoTex = new Texture("textures/pluto.jpg");
		
		universe = new SkySphere(24, 48, uniTex);
		/** Planet size is a ratio to Earth's size and was taken from http://sciencenetlinks.com/interactives/messenger/psc/PlanetSize.html */
		sun = new Planet(24, 48, sunTex, 108, 0);//The sun is a star!
		mercury = new Planet(24, 48, mercuryTex, 0.39f, 0.4f);
		venus = new Planet(24, 48, venusTex, 0.95f, 0.7f);
		earth = new Planet(24, 48, earthTex, 1, 1);
		earthClouds = new Planet(24, 48, earthCloudsTex, 1.03f, 1);
		mars = new Planet(24, 48, marsTex, 0.53f, 1.5f);
		jupiter = new Planet(24, 48, jupiterTex, 11.1f, 5.2f);
		saturn = new Planet(24, 48, saturnTex, 9.41f, 9.5f);
		uranus = new Planet(24, 48, uranusTex, 4.0f, 19.2f);
		neptune = new Planet(24, 48, neptuneTex, 3.88f, 30.1f);
		pluto = new Planet(24, 48, plutoTex, 0.18f, 39.7f);
		
		/* moon distances are not to scale**/
		moon = new Satellite(24, 48, moonTex, 0.27f, 0.002f, earth, 0);
		
		io = new Satellite(24, 48, ioTex, 0.286f, 0.001f, jupiter);
		europa = new Satellite(24, 48, europaTex, 0.245f, 0.002f, jupiter);
		ganymede = new Satellite(24, 48, ganymedeTex, 0.413f, 0.002f, jupiter, 0);
		callisto = new Satellite(24, 48, callistoTex, 0.378f, 0.001f, jupiter, 0);
		
		titan = new Satellite(24, 48, titanTex, 0.404f, 0.002f, saturn, 0);
		tethys = new Satellite(24, 48, tethysTex, 0.083f, 0.002f, saturn);
		rhea = new Satellite(24, 48, rheaTex, 0.12f, 0.003f, saturn);
		dione = new Satellite(24, 48, dioneTex, 0.10f, 0.0025f, saturn);
		lapetus = new Satellite(24, 48, lapetusTex, 0.12f, 0.004f, saturn, 0);
		
		earthAtmosphere = new Atmosphere(24, 48, earthCloudsTex, earth);
		
		planets.add(mercury);
		planets.add(venus);
		planets.add(earth);
		planets.add(mars);
		planets.add(jupiter);
		planets.add(saturn);
		planets.add(uranus);
		planets.add(neptune);
		planets.add(pluto);
		
		moons.add(moon);
		moons.add(io);
		moons.add(callisto);
		moons.add(europa);
		moons.add(ganymede);
		moons.add(titan);
		moons.add(tethys);
		moons.add(rhea);
		moons.add(dione);
		moons.add(lapetus);
		
		atmospheres.add(earthAtmosphere);
		
		Gdx.gl11.glEnable(GL11.GL_LIGHTING);
		Gdx.gl11.glEnable(GL11.GL_LIGHT0);
		Gdx.gl11.glEnable(GL11.GL_DEPTH_TEST);
		Gdx.gl11.glEnable(GL11.GL_BLEND);
		Gdx.gl11.glEnable(GL11.GL_ALPHA_TEST);
		Gdx.gl11.glAlphaFunc(GL11.GL_GREATER, 0);
		//Gdx.gl11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		
		Gdx.gl11.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

		Gdx.gl11.glMatrixMode(GL11.GL_PROJECTION);
		Gdx.gl11.glLoadIdentity();
		Gdx.glu.gluPerspective(Gdx.gl11, 90, 1.333333f, 0.1f, 100000.0f);

		Gdx.gl11.glEnableClientState(GL11.GL_VERTEX_ARRAY);

		cam = new Camera(new Point3D(1000f, 0.0f, 5.0f), new Point3D(0.0f, 0.0f, 0.0f), new Vector3D(0.0f, 1.0f, 0.0f));
		
		FloatBuffer vertexBuffer = BufferUtils.newFloatBuffer(72);
		vertexBuffer.put(new float[] {-0.5f, -0.5f, -0.5f, -0.5f, 0.5f, -0.5f,
									  0.5f, -0.5f, -0.5f, 0.5f, 0.5f, -0.5f,
									  0.5f, -0.5f, -0.5f, 0.5f, 0.5f, -0.5f,
									  0.5f, -0.5f, 0.5f, 0.5f, 0.5f, 0.5f,
									  0.5f, -0.5f, 0.5f, 0.5f, 0.5f, 0.5f,
									  -0.5f, -0.5f, 0.5f, -0.5f, 0.5f, 0.5f,
									  -0.5f, -0.5f, 0.5f, -0.5f, 0.5f, 0.5f,
									  -0.5f, -0.5f, -0.5f, -0.5f, 0.5f, -0.5f,
									  -0.5f, 0.5f, -0.5f, -0.5f, 0.5f, 0.5f,
									  0.5f, 0.5f, -0.5f, 0.5f, 0.5f, 0.5f,
									  -0.5f, -0.5f, -0.5f, -0.5f, -0.5f, 0.5f,
									  0.5f, -0.5f, -0.5f, 0.5f, -0.5f, 0.5f});
		vertexBuffer.rewind();
		Gdx.gl11.glVertexPointer(3, GL11.GL_FLOAT, 0, vertexBuffer);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}
	
	private void update()
	{
		float deltaTime = Gdx.graphics.getDeltaTime();
		elapsedTime += deltaTime;
		
		sun.update(deltaTime);
		
		for(Satellite s : moons)
		{
			s.update(deltaTime);
		}
		
		for(Atmosphere a : atmospheres)
		{
			a.update(deltaTime);
		}
		
		for(Planet p : planets)
		{
			p.update(deltaTime);
		}
		
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT))//turn left
		{
			cam.turn(-90.0f * deltaTime);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))//turn right
		{
			cam.turn(90.0f * deltaTime);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.UP))
		{
			cam.pitch(-90.0f * deltaTime);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
		{
			cam.pitch(90.0f * deltaTime);
		}
		
		if(freeFlight)
		{
			if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT))//activates input
			{
				System.out.println("Enter planetary destination");
				freeFlight = false;
				destinationInput = true;
			}
		}
		if(destinationInput)
		{		
			boolean input = false;
			if(Gdx.input.isKeyPressed(Input.Keys.NUM_1))
			{
				destination = mercury;
				input = true;
			}
			else if(Gdx.input.isKeyPressed(Input.Keys.NUM_2))
			{
				destination = venus;
				input = true;
			}
			else if(Gdx.input.isKeyPressed(Input.Keys.NUM_3))
			{
				destination = earth;
				input = true;
			}
			else if(Gdx.input.isKeyPressed(Input.Keys.NUM_4))
			{
				destination = mars;
				input = true;
			}
			else if(Gdx.input.isKeyPressed(Input.Keys.NUM_5))
			{
				destination = jupiter;
				input = true;
			}
			else if(Gdx.input.isKeyPressed(Input.Keys.NUM_6))
			{
				destination = saturn;
				input = true;
			}
			else if(Gdx.input.isKeyPressed(Input.Keys.NUM_7))
			{
				destination = neptune;
				input = true;
			}
			else if(Gdx.input.isKeyPressed(Input.Keys.NUM_8))
			{
				destination = uranus;
				input = true;
			}
			else if(Gdx.input.isKeyPressed(Input.Keys.NUM_9))
			{
				destination = pluto;
				input = true;
			}
			elapsedTime = 0;
			if(input)
			{
				travel.setDestination(destination, cam.eye);
				destinationInput = false;
				elapsedTime = 0;
			}
		}
		
		if(freeFlight)
		{	
			if(Gdx.input.isKeyPressed(Input.Keys.SPACE))
			{
				cam.slide(0.0f, 0.0f, -5 * deltaTime);
			}
		}
		else if(freeFlight == false && destinationInput == false)//travel may commence
		{
			cam.setEye(travel.update(deltaTime));
			warpFunction(elapsedTime, deltaTime);
		}
		
		if(elapsedTime > 11)
		{
			freeFlight = true;
		}
	}
	
	private void display()
	{
		Gdx.gl11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);

		cam.setModelViewMatrix();
		
		float[] lightDiffuse = {5.0f, 5.0f, 5.0f, 1000.0f};
		Gdx.gl11.glLightfv(GL11.GL_LIGHT0, GL11.GL_DIFFUSE, lightDiffuse, 0);

		float[] lightPosition = {0.0f, 0.0f, 0.0f, 1.0f};
		Gdx.gl11.glLightfv(GL11.GL_LIGHT0, GL11.GL_POSITION, lightPosition, 0);
		
		float[] ambient = {3.0f, 3.0f, 3.0f, 3.0f};
		Gdx.gl11.glLightfv(GL11.GL_LIGHT0, GL11.GL_AMBIENT, ambient, 0);

		float[] materialDiffuse = {100.0f, 100.0f, 100.0f, 0.0f};
		Gdx.gl11.glMaterialfv(GL11.GL_BACK, GL11.GL_DIFFUSE, materialDiffuse, 0);
		
		float[] emission = {1, .3f, .5f, 0};
		float[] emission2 = {0, 0, 0, 0};
		Gdx.gl11.glMaterialfv(GL11.GL_FRONT, GL11.GL_EMISSION, emission, 0);
		
		float[] specular = {1, 1, 1, 1};
		float[] shininess = {50,50,50,50};
		
		Gdx.gl11.glLightfv(GL11.GL_LIGHT0, GL11.GL_SPECULAR, specular, 0);
		Gdx.gl11.glMaterialfv(GL11.GL_FRONT, GL11.GL_SHININESS, shininess, 0);
		Gdx.gl11.glMaterialfv(GL11.GL_FRONT, GL11.GL_SPECULAR, specular, 0);
		
		//draw sun
		Gdx.gl11.glPushMatrix();
		Gdx.gl11.glTranslatef(sun.x, sun.y, sun.z);
		Gdx.gl11.glScalef(sun.size, sun.size, sun.size);
		Gdx.gl11.glRotatef(sun.rotationAngle/sun.size, 1, 1, 0);
		sun.draw();
		Gdx.gl11.glPopMatrix();
		
		Gdx.gl11.glMaterialfv(GL11.GL_FRONT, GL11.GL_EMISSION, emission2, 0);

		//draw skySphere
		Gdx.gl11.glPushMatrix();
		Gdx.gl11.glTranslatef(20000, 0, 0);
		Gdx.gl11.glScalef(50000, warp, warp);
		universe.draw();
		Gdx.gl11.glPopMatrix();
		
		for(Planet p : planets)
		{
			Gdx.gl11.glPushMatrix();
			Gdx.gl11.glTranslatef(p.x, 0, 0);
			Gdx.gl11.glRotatef(p.rotationAngle, 0, 1, 0);
			Gdx.gl11.glScalef(p.size, p.size, p.size);
			p.draw();
			Gdx.gl11.glPopMatrix();
		}
		
		for(Satellite s : moons)
		{
			Gdx.gl11.glPushMatrix();
			Gdx.gl11.glTranslatef(s.orbitPoint.x, s.orbitPoint.y, s.orbitPoint.z);
			Gdx.gl11.glScalef(s.size, s.size, s.size);
			Gdx.gl11.glRotatef(s.rotationAngle, 0, 1, 0);
			Gdx.gl11.glTranslatef((s.x - s.orbitPoint.x), s.y, 0);
			s.draw();
			Gdx.gl11.glPopMatrix();
		}
		
		for(Atmosphere a : atmospheres)
		{
			Gdx.gl11.glPushMatrix();
			Gdx.gl11.glTranslatef(a.x, 0, 0);
			Gdx.gl11.glScalef(a.size, a.size, a.size);
			Gdx.gl11.glRotatef(a.rotationAngle, 0, -1, 0);
			a.draw();
			Gdx.gl11.glPopMatrix();
		}
		
	}

	@Override
	public void render()
	{
		update();
		display();
	}

	public void warpFunction(float elaspedTime, float deltaTime)//simple function that regulates the "star trek" effect
	{
		if(elapsedTime < 2 && warp > 10000)
		{
			warp -= (50000 * deltaTime);
		}
		if(elapsedTime > 8 && warp < 50000)
		{
			warp += (50000 * deltaTime);
		}
	}
	
	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

}
