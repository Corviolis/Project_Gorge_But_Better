package com.corviolis.gorge.entities;

import com.badlogic.gdx.math.Vector3;

public abstract class Entity {

    protected final Vector3 position = new Vector3();
    protected int direction = 0;

    public void translate(Vector3 translation) {
        position.add(translation);
        if (translation.x > 0) direction = 1;
        if (translation.x < 0) direction = -1;
    }

    public void setPosition(Vector3 position) {
        this.position.set(position);
    }

    public final Vector3 getPosition() {
        return position.cpy(); //Return new vec so that we can add/sub from it without changing the instances position
    }

    // Called each game cycle
    public void update(float delta) {}
}