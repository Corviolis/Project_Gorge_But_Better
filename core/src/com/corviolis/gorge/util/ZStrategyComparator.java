package com.corviolis.gorge.util;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g3d.decals.Decal;

import java.util.Comparator;

public class ZStrategyComparator implements Comparator<Decal> {

    private final Camera cam;

    public ZStrategyComparator(Camera cam) {
        this.cam = cam;
    }

    @Override
    public int compare (Decal o1, Decal o2) {
        float dist1 = cam.position.dst(0, 0, o1.getPosition().z);
        float dist2 = cam.position.dst(0, 0, o2.getPosition().z);
        return (int) Math.signum(dist2 - dist1);
    }

}
