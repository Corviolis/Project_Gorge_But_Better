package com.corviolis.gorge;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g3d.decals.CameraGroupStrategy;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.corviolis.gorge.entities.Entity;
import com.corviolis.gorge.entities.Player;
import com.corviolis.gorge.input.SingleAxisInputController;
import com.corviolis.gorge.util.ZStrategyComparator;

import java.util.ArrayList;
import java.util.Comparator;

public class Gorge extends ApplicationAdapter {
	private PerspectiveCamera cam;
	private DecalBatch decalBatch;
	private SingleAxisInputController controller;
	private LevelManager levelManager;
	private float elapsedTime = 0;
	private final ArrayList<Entity> entities = new ArrayList<>();
	private static final float frameRate = 12;
	private Assets assets;

	@Override
	public void create() {
		assets = new Assets();
		assets.load();

		levelManager = new LevelManager(assets);
		levelManager.loadLevel(-1);

		//Camera Stuff
		cam = new PerspectiveCamera(24.4f, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.position.set(0f, 10f, -60f);
		cam.lookAt(0,7,0);
		cam.near = 1f;
		cam.far = 300f;
		cam.update();

		decalBatch = new DecalBatch(new CameraGroupStrategy(cam, new ZStrategyComparator(cam)));

		//Setup Player
		Player player = new Player(assets);
		player.setPosition(new Vector3(0, 8,-3));
		entities.add(player);
		controller = new SingleAxisInputController(player);
		Gdx.input.setInputProcessor(controller);

		//Set all decals to face camera
		for (Entity entity : entities) {
			entity.setDecalPlane(cam.position);
		}
	}

	@Override
	public void resize(int width, int height) {
		cam.viewportHeight = height;
		cam.viewportWidth = width;
		cam.update();
	}

	@Override
	public void render() {
		ScreenUtils.clear(Color.DARK_GRAY, true);
		elapsedTime += Gdx.graphics.getDeltaTime();

		controller.update();

		for (Decal decal : levelManager.getDecals()) {
			decalBatch.add(decal);
		}

		//Render all Entities
		for (Entity entity : entities) {
			if (entity instanceof Player) {
				cam.position.set(entity.getPosition().x, 10, -60);
				cam.update();
			}
			decalBatch.add(entity.getDecal(elapsedTime));
		}
		decalBatch.flush();
	}
	
	@Override
	public void dispose() {
		decalBatch.dispose();
		assets.dispose();
	}

	public static Animation<TextureAtlas.AtlasRegion> createAnimation(Array<TextureAtlas.AtlasRegion> regions) {
		return new Animation<>(1/frameRate, regions);
	}
}
