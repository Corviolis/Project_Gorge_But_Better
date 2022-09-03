package com.corviolis.gorge.entities;

import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.badlogic.gdx.math.Vector3;
import com.corviolis.gorge.Assets;

public abstract class Entity {

    protected final Decal decal;
    protected final Vector3 position = new Vector3();
    protected int direction = 0;
    protected Assets assets;

    public Entity(Assets assets) {
        this.assets = assets;
        decal = assets.createDecal();
    }

    public void setDecalPlane(Vector3 position, Vector3 up) {
        decal.lookAt(this.position.add(position), up);
    }

    public void translate(Vector3 translation) {
        position.add(translation);
        decal.setPosition(position);

        if (translation.x > 0) direction = 1;
        if (translation.x < 0) direction = -1;
    }

    public void setPosition(Vector3 position) {
        this.position.set(position);
        decal.setPosition(position);
    }

    public Vector3 getPosition() {
        return position;
    }

    public abstract Decal getDecal(float time);

    // Called each game cycle
    public abstract void update(float delta);
}
