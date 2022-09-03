package com.corviolis.gorge.entities;

import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;
import com.corviolis.gorge.Assets;

import java.util.ArrayList;

public class EntityManager {

    private final Player player;
    private final Assets assets;

    private final ArrayList<Entity> loadedEntities = new ArrayList<>();

    public EntityManager(Assets assets) {
        this.assets = assets;
        this.player = new Player(assets);;
    }

    public Entity getEntityFromType(String type) {
        switch (type) {
            case "player": return player;
        }
        return null;
    }

    public Player getPlayer() {
        return player;
    }

    public void loadEntity(Entity entity) {
        loadedEntities.add(entity);
    }

    public void clearEntities() {
        loadedEntities.clear();
    }

    public void processEntities(float delta, DecalBatch decalBatch) {
        for (Entity entity : loadedEntities) {
            decalBatch.add(entity.getDecal(delta));
            entity.update(delta);
        }
    }
}
