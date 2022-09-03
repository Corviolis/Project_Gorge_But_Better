package com.corviolis.gorge;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g3d.decals.CameraGroupStrategy;
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.corviolis.gorge.entities.EntityManager;
import com.corviolis.gorge.entities.Player;
import com.corviolis.gorge.input.InputController;
import com.corviolis.gorge.util.ZStrategyComparator;

public class Gorge extends ApplicationAdapter {
	private PerspectiveCamera cam;
	private DecalBatch decalBatch;
	private InputController controller;
	private EntityManager entityManager;
	private SceneManager sceneManager;
	private float delta = 0;

	private static final float frameRate = 12;
	private Assets assets;

	@Override
	public void create() {
		assets = new Assets();

		//Camera Stuff
		cam = new PerspectiveCamera(24.4f, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.near = 1f;
		cam.far = 300f;
		decalBatch = new DecalBatch(new CameraGroupStrategy(cam, new ZStrategyComparator(cam)));

		entityManager = new EntityManager(assets);
		sceneManager = new SceneManager(assets, entityManager, cam);
		sceneManager.loadScene(-1);

		controller = new InputController(entityManager.getPlayer(), sceneManager, cam);
		Gdx.input.setInputProcessor(controller);
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
		delta += Gdx.graphics.getDeltaTime();
		controller.update();

		sceneManager.processActiveScene(decalBatch);
		entityManager.processEntities(delta, decalBatch);
		decalBatch.flush();
	}
	
	@Override
	public void dispose() {
		decalBatch.dispose();
		assets.dispose();
	}
}