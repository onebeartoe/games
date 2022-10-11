package edu.umbc.cs.sli2;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

class CubeAction {
    private int Axis;
    private int Plane;
    private boolean Dir;

    int GetAxis() {
        return this.Axis;
    }

    boolean GetDir() {
        return this.Dir;
    }

    CubeAction(int axis, int plane, boolean dir) {
        this.Axis = axis;
        this.Plane = plane;
        this.Dir = dir;
    }

    int GetPlane() {
        return this.Plane;
    }
}
