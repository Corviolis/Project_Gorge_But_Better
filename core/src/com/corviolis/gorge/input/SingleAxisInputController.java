package com.corviolis.gorge.input;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector3;
import com.corviolis.gorge.entities.Entity;

public class SingleAxisInputController extends InputAdapter {

    private final Entity entity;
    private final Vector3 vector3 = new Vector3();

    public SingleAxisInputController(Entity entity) {
        this.entity = entity;
    }

    public void update() {
        entity.translate(vector3);
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Keys.A) vector3.add(1, 0, 0);
        if (keycode == Keys.D) vector3.add(-1, 0, 0);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Keys.A) vector3.add(-1, 0, 0);
        if (keycode == Keys.D) vector3.add(1, 0, 0);
        return false;
    }
}
