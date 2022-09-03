package com.corviolis.gorge;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.corviolis.gorge.entities.Entity;
import com.corviolis.gorge.entities.EntityManager;

import java.util.ArrayList;

public class SceneManager {

    private String activeSceneName;
    private int activeSceneId;
    private final ArrayList<Decal> decals = new ArrayList<>();
    private final Assets assets;
    private final EntityManager entityManager;
    private final PerspectiveCamera cam;

    private final Vector3 up = new Vector3(0, 1, 0);
    private final Vector3 front = new Vector3(0, 0, 1);

    public SceneManager(Assets assets, EntityManager entityManager, PerspectiveCamera cam) {
        this.assets = assets;
        this.entityManager = entityManager;
        this.cam = cam;
    }

    public void loadScene(int level) {
        FileHandle levelFile = Gdx.files.internal("levels/" + level + ".json");
        JsonValue json = new JsonReader().parse(levelFile);

        decals.clear();
        entityManager.clearEntities();

        activeSceneName = json.getString("name");
        activeSceneId = level;

        cam.position.set(new Vector3(convertToVec(json.get("camera_offset").asFloatArray())));
        cam.up.set(up);
        cam.update();

        for (JsonValue worldElement : json.get("world")) {
            Decal decal = assets.createDecal();
            decals.add(decal);

            String plane = worldElement.getString("plane");
            if (plane.equals("floor")) decal.lookAt(up, up);
            else if (plane.equals("wall")) decal.lookAt(front, up);

            decal.setTextureRegion(assets.getTextureRegion(worldElement.getString("texture")));
            decal.setScale(worldElement.getInt("scale"));

            decal.setPosition(convertToVec(worldElement.get("position").asFloatArray()));
        }

        for (JsonValue entityData : json.get("entities")) {
            Entity entity = entityManager.getEntityFromType(entityData.getString("type"));
            entity.setPosition(convertToVec(entityData.get("position").asFloatArray()));
            entity.setDecalPlane(front, up);
            entityManager.loadEntity(entity);
        }
    }

    public void reloadActiveScene() {
        loadScene(activeSceneId);
    }

    private Vector3 convertToVec(float[] array) {
        return new Vector3(array[0], array[1], array[2]);
    }

    public String getActiveSceneName() {
        return activeSceneName;
    }

    public void processActiveScene(DecalBatch decalBatch) {
        for (Decal decal : decals) decalBatch.add(decal);
    }
}