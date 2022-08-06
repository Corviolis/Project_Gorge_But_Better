package com.corviolis.gorge;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;

public class PlayerInputController extends InputAdapter {

    private final ModelInstance instance;
    private final Vector3 vector3 = new Vector3();

    public PlayerInputController(ModelInstance instance) {
        this.instance = instance;
    }

    public void update() {
        instance.transform.translate(vector3);
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Keys.W) vector3.add(0, 0, 1);
        if (keycode == Keys.S) vector3.add(0, 0, -1);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Keys.W) vector3.add(0, 0, -1);
        if (keycode == Keys.S) vector3.add(0, 0, 1);
        return false;
    }
}
