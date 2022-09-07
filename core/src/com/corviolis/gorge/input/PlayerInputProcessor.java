package com.corviolis.gorge.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector3;
import com.corviolis.gorge.entities.Player;

public class PlayerInputProcessor extends InputAdapter {

    private final Vector3 direction = new Vector3();

    public PlayerInputProcessor(Player player) {
        player.setInputProcessor(this);
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.A) direction.add(-1, 0, 0);
        if (keycode == Input.Keys.D) direction.add(1, 0, 0);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.A) direction.add(1, 0, 0);
        if (keycode == Input.Keys.D) direction.add(-1, 0, 0);
        return false;
    }

    public Vector3 getMovementDirection() {
        return direction;
    }
}
