package com.corviolis.gorge.util;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g3d.decals.Decal;

import java.util.Comparator;

public class ZSort implements Comparator<Decal> {

    private final Camera cam;

    public ZSort(Camera cam) {
        this.cam = cam;
    }

    @Override
    public int compare (Decal decal1, Decal decal2) {
        float dist1 = cam.position.dst(0, 0, decal1.getPosition().z);
        float dist2 = cam.position.dst(0, 0, decal2.getPosition().z);
        return (int) Math.signum(dist1 - dist2);
    }
}
