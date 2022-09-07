package com.corviolis.gorge.entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.badlogic.gdx.math.Vector3;
import com.corviolis.gorge.Assets;
import com.corviolis.gorge.input.PlayerInputProcessor;

public class Player extends DecalEntity {

    private PlayerInputProcessor inputProcessor;
    private final Animation<TextureAtlas.AtlasRegion> walk_right;
    private final Animation<TextureAtlas.AtlasRegion> walk_left;

    public Player(Assets assets) {
        super(assets);
        walk_right = assets.createAnimation(assets.getTextureRegions("player/walk_left"));
        walk_left = assets.createAnimation(assets.getTextureRegions("player/walk_right"));
        walk_right.setPlayMode(Animation.PlayMode.LOOP);
        walk_left.setPlayMode(Animation.PlayMode.LOOP);
    }

    public void setInputProcessor(PlayerInputProcessor inputProcessor) {
        this.inputProcessor = inputProcessor;
    }

    @Override
    public Decal getDecal(float time) {
        if (direction == -1) decal.setTextureRegion(walk_right.getKeyFrame(time));
        else if (direction == 1) decal.setTextureRegion(walk_left.getKeyFrame(time));
        else if (direction == 0); //Set Idle Animation
        return decal;
    }

    @Override
    public void update(float delta) {
        Vector3 direction = inputProcessor.getMovementDirection();
        this.translate(direction);
    }
}
