package com.corviolis.gorge;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.badlogic.gdx.utils.Array;

public class Assets {
    public AssetManager manager = new AssetManager();
    private final float frameRate = 12;
    public static final AssetDescriptor<TextureAtlas> textures = new AssetDescriptor<>(Gdx.files.internal("packed-images/textures_packed.atlas"), TextureAtlas.class);

    public void load() {
        manager.load(textures);
        manager.finishLoading();
    }

    public TextureAtlas.AtlasRegion getTextureRegion(String region) {
        return manager.get(textures).findRegion(region);
    }

    public Array<TextureAtlas.AtlasRegion> getTextureRegions(String region) {
        return manager.get(textures).findRegions(region);
    }

    public Decal createDecal() {
        return Decal.newDecal(getTextureRegion("missing"), true);
    }

    public Animation<TextureAtlas.AtlasRegion> createAnimation(Array<TextureAtlas.AtlasRegion> regions) {
        return new Animation<>(1/frameRate, regions);
    }

    public void dispose() {
        manager.dispose();
    }
}
