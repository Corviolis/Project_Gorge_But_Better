package com.corviolis.gorge.entities;

import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.badlogic.gdx.math.Vector3;
import com.corviolis.gorge.Assets;

public abstract class DecalEntity extends Entity {

    protected final Decal decal;

    public DecalEntity(Assets assets) {
        decal = assets.createDecal();
    }

    @Override
    public void translate(Vector3 translation) {
        super.translate(translation);
        decal.translate(translation);
    }

    @Override
    public void setPosition(Vector3 position) {
        super.setPosition(position);
        decal.setPosition(position);
    }

    public final void setDecalPlane(Vector3 position, Vector3 up) {
        decal.lookAt(this.position.add(position), up);
    }

    public abstract Decal getDecal(float time);
}