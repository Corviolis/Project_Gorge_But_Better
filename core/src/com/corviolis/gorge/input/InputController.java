package com.corviolis.gorge.input;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.Vector3;
import com.corviolis.gorge.SceneManager;
import com.corviolis.gorge.entities.Entity;

public class InputController extends InputAdapter {

    private final Entity entity;
    private final PerspectiveCamera cam;
    private final Vector3 vector3 = new Vector3();
    private final SceneManager levelManager;

    public InputController(Entity entity, SceneManager levelManager, PerspectiveCamera cam) {
        this.entity = entity;
        this.levelManager = levelManager;
        this.cam = cam;
    }

    public void update() {
        entity.translate(vector3);
        cam.position.set(entity.getPosition().x, cam.position.y, cam.position.z);
        cam.update();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Keys.A) vector3.add(-1, 0, 0);
        if (keycode == Keys.D) vector3.add(1, 0, 0);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Keys.A) vector3.add(1, 0, 0);
        if (keycode == Keys.D) vector3.add(-1, 0, 0);

        if (keycode == Keys.R) levelManager.reloadActiveScene();
        return false;
    }
}
