package com.corviolis.gorge.entities;

import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.Vector3;

public class CameraEntity extends Entity {

    private Entity trackingEntity;
    private final PerspectiveCamera camera;
    private Vector3 trackingOffset;

    public CameraEntity(PerspectiveCamera camera) {
        this.camera = camera;
        this.camera.near = 1f;
        this.camera.far = 300f;
    }

    @Override
    public void translate(Vector3 translation) {
        super.translate(translation);
        camera.translate(translation);
    }

    @Override
    public void setPosition(Vector3 position) {
        super.setPosition(position);
        camera.position.set(position);
    }

    public void trackEntity(Entity entity) {
        trackingEntity = entity;
        trackingOffset = camera.position.cpy();
    }

    public void stopTracking() {
        trackingEntity = null;
    }

    @Override
    public void update(float delta) {
        if (trackingEntity == null) return;
        camera.position.set(trackingEntity.getPosition().add(trackingOffset));
        camera.update();
    }
}