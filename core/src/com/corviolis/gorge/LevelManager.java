package com.corviolis.gorge;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import java.util.ArrayList;

public class LevelManager {

    private String activeLevelName;
    private final ArrayList<Decal> decals = new ArrayList<>();
    private Assets assets;

    public LevelManager(Assets assets) {
        this.assets = assets;
    }

    public void loadLevel(int level) {
        FileHandle levelFile = Gdx.files.internal("levels/" + level + ".json");
        JsonValue json = new JsonReader().parse(levelFile);

        activeLevelName = json.getString("name");
        decals.clear();

        for (int j = 0; j <= 5; j++) {
            for (int i = 0; i <= 20; i++) {
                Decal decal = assets.createDecal();
                decal.lookAt(new Vector3(0, 1, 0), new Vector3(0, 1, 0));
                decal.setTextureRegion(assets.getTextureRegion(json.getString("floor")));
                float scale = 1f;
                decal.setScale(scale);
                decal.setPosition(decal.getWidth() / 2 * -i * scale, 0, (decal.getHeight() / 2 * -j * scale) + 20);
                decals.add(decal);

                /*if (j == 4) {
                    Decal decal1 = assets.createDecal();
                    decal1.lookAt(new Vector3(0, 0, -1), new Vector3(0, 1, 0));
                    decal1.setTextureRegion(assets.getTextureRegion(json.getString("floor")));
                    decal1.setScale(scale/2);
                    decal1.setPosition(decal.getX(), -4, -16);
                    decal1.setColor(Color.GRAY);
                    decals.add(decal1);
                }*/
            }
        }
    }

    public String getActiveLevelName() {
        return activeLevelName;
    }

    public ArrayList<Decal> getDecals() {
        return decals;
    }
}