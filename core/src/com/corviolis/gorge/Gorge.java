package com.corviolis.gorge;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.decals.CameraGroupStrategy;
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.corviolis.gorge.entities.EntityManager;
import com.corviolis.gorge.entities.Player;
import com.corviolis.gorge.input.PlayerInputProcessor;

public class Gorge extends ApplicationAdapter {
	private PerspectiveCamera cam;
	private DecalBatch decalBatch;
	private EntityManager entityManager;
	private SceneManager sceneManager;
	private float delta = 0;

	public static final float FRAME_RATE = 12;
	private Assets assets;

	@Override
	public void create() {
		//Camera Setup
		cam = new PerspectiveCamera(24.4f, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		decalBatch = new DecalBatch(new CameraGroupStrategy(cam));

		//Internal Services
		assets = new Assets();
		entityManager = new EntityManager(assets, cam);
		sceneManager = new SceneManager(assets, entityManager);
		sceneManager.loadScene(-1);

		//Input Setup
		InputMultiplexer inputMultiplexer = new InputMultiplexer();
		if (entityManager.isPlayerLoaded()) {
			Player player = entityManager.getPlayer();
			entityManager.getCameraEntity().trackEntity(player);
			inputMultiplexer.addProcessor(new PlayerInputProcessor(player));
		}
		Gdx.input.setInputProcessor(inputMultiplexer);
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

		sceneManager.processActiveScene(decalBatch);
		decalBatch.flush();
		entityManager.processEntities(delta, decalBatch);
		decalBatch.flush();
	}
	
	@Override
	public void dispose() {
		decalBatch.dispose();
		assets.dispose();
	}
}