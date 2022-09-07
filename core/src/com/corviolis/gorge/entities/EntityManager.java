package com.corviolis.gorge.entities;

import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;
import com.corviolis.gorge.Assets;

import java.util.ArrayList;

public class EntityManager {

    private final Player player;
    private final CameraEntity cameraEntity;

    private final ArrayList<Entity> loadedEntities = new ArrayList<>();

    public EntityManager(Assets assets, PerspectiveCamera camera) {
        this.player = new Player(assets);
        cameraEntity = new CameraEntity(camera);
    }

    public Entity getEntityFromType(String type) {
        switch (type) {
            case "player": return player;
            case "camera": return cameraEntity;
        }
        return null;
    }

    public Player getPlayer() {
        return player;
    }

    public CameraEntity getCameraEntity() {
        return cameraEntity;
    }

    public void loadEntity(Entity entity) {
        loadedEntities.add(entity);
    }

    public boolean isPlayerLoaded() {
        return loadedEntities.contains(player);
    }

    public void clearEntities() {
        loadedEntities.clear();
    }

    public void processEntities(float delta, DecalBatch decalBatch) {
        for (Entity entity : loadedEntities) {
            if (entity instanceof DecalEntity) decalBatch.add(((DecalEntity) entity).getDecal(delta));
            entity.update(delta);
        }
    }
}
