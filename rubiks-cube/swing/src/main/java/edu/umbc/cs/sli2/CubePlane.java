package edu.umbc.cs.sli2;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.Color;

class CubePlane extends Object3D {
    static final int XPlane = 0;
    static final int YPlane = 1;
    static final int ZPlane = 2;
    private final int[][] TransFace = new int[][]{{0, 1, 2, 3, 4, 5}, {2, 3, 4, 5, 0, 1}, {4, 5, 0, 1, 2, 3}};
    private Vertex[] PlaneVertex = new Vertex[8];
    private Face[] PlaneFace = new Face[6];
    private int Plane;
    private int Axis;

    CubePlane(int axis, int plane) {
        this.Plane = plane;
        this.Axis = axis;
        this.NewPlane(this.PlaneVertex, this.PlaneFace);
        this.Define(this.PlaneVertex, 8, this.PlaneFace, 6);
    }

    private void NewPlane(Vertex[] vertex, Face[] face) {
        switch (this.Axis) {
            case 0:
                vertex[0] = new Vertex((float)(-3 + this.Plane * 2), -3.0F, -3.0F);
                vertex[1] = new Vertex((float)(-3 + this.Plane * 2), -3.0F, 3.0F);
                vertex[2] = new Vertex((float)(-3 + this.Plane * 2), 3.0F, 3.0F);
                vertex[3] = new Vertex((float)(-3 + this.Plane * 2), 3.0F, -3.0F);
                vertex[4] = new Vertex((float)(-1 + this.Plane * 2), -3.0F, -3.0F);
                vertex[5] = new Vertex((float)(-1 + this.Plane * 2), -3.0F, 3.0F);
                vertex[6] = new Vertex((float)(-1 + this.Plane * 2), 3.0F, 3.0F);
                vertex[7] = new Vertex((float)(-1 + this.Plane * 2), 3.0F, -3.0F);
                break;
            case 1:
                vertex[0] = new Vertex(-3.0F, (float)(-3 + this.Plane * 2), -3.0F);
                vertex[1] = new Vertex(3.0F, (float)(-3 + this.Plane * 2), -3.0F);
                vertex[2] = new Vertex(3.0F, (float)(-3 + this.Plane * 2), 3.0F);
                vertex[3] = new Vertex(-3.0F, (float)(-3 + this.Plane * 2), 3.0F);
                vertex[4] = new Vertex(-3.0F, (float)(-1 + this.Plane * 2), -3.0F);
                vertex[5] = new Vertex(3.0F, (float)(-1 + this.Plane * 2), -3.0F);
                vertex[6] = new Vertex(3.0F, (float)(-1 + this.Plane * 2), 3.0F);
                vertex[7] = new Vertex(-3.0F, (float)(-1 + this.Plane * 2), 3.0F);
                break;
            case 2:
                vertex[0] = new Vertex(-3.0F, -3.0F, (float)(-3 + this.Plane * 2));
                vertex[1] = new Vertex(-3.0F, 3.0F, (float)(-3 + this.Plane * 2));
                vertex[2] = new Vertex(3.0F, 3.0F, (float)(-3 + this.Plane * 2));
                vertex[3] = new Vertex(3.0F, -3.0F, (float)(-3 + this.Plane * 2));
                vertex[4] = new Vertex(-3.0F, -3.0F, (float)(-1 + this.Plane * 2));
                vertex[5] = new Vertex(-3.0F, 3.0F, (float)(-1 + this.Plane * 2));
                vertex[6] = new Vertex(3.0F, 3.0F, (float)(-1 + this.Plane * 2));
                vertex[7] = new Vertex(3.0F, -3.0F, (float)(-1 + this.Plane * 2));
        }

        if (this.Plane == 0) {
            face[0] = new CubeFace(3, 3);
        } else {
            face[0] = new Face(4);
            face[0].FaceColor = Color.black;
        }

        if (this.Plane == 2) {
            face[1] = new CubeFace(3, 3);
        } else {
            face[1] = new Face(4);
            face[1].FaceColor = Color.black;
        }

        int i = 2;

        do {
            if (i != 2 && i != 5) {
                face[i] = new CubeFace(1, 3);
            } else {
                face[i] = new CubeFace(3, 1);
            }

            ++i;
        } while(i < 6);

        face[0].Vertices[0] = vertex[0];
        face[0].Vertices[1] = vertex[1];
        face[0].Vertices[2] = vertex[2];
        face[0].Vertices[3] = vertex[3];
        face[1].Vertices[0] = vertex[4];
        face[1].Vertices[1] = vertex[7];
        face[1].Vertices[2] = vertex[6];
        face[1].Vertices[3] = vertex[5];
        face[2].Vertices[0] = vertex[0];
        face[2].Vertices[1] = vertex[4];
        face[2].Vertices[2] = vertex[5];
        face[2].Vertices[3] = vertex[1];
        face[3].Vertices[0] = vertex[3];
        face[3].Vertices[1] = vertex[2];
        face[3].Vertices[2] = vertex[6];
        face[3].Vertices[3] = vertex[7];
        face[4].Vertices[0] = vertex[0];
        face[4].Vertices[1] = vertex[3];
        face[4].Vertices[2] = vertex[7];
        face[4].Vertices[3] = vertex[4];
        face[5].Vertices[0] = vertex[1];
        face[5].Vertices[1] = vertex[5];
        face[5].Vertices[2] = vertex[6];
        face[5].Vertices[3] = vertex[2];
    }

    public void Reset() {
        this.Define(this.PlaneVertex, 8, this.PlaneFace, 6);
    }

    public void SetMap(Color[][][] map) {
        if (this.Plane == 0) {
            ((CubeFace)super.Faces[0]).SetMap(map[this.TransFace[this.Axis][0]]);
        }

        if (this.Plane == 2) {
            ((CubeFace)super.Faces[1]).SetMap(map[this.TransFace[this.Axis][1]]);
        }

        int i = 2;

        do {
            int h = 0;

            do {
                int faceindex = this.TransFace[this.Axis][i];
                boolean order = faceindex % 2 == 0;
                if (i == 2 || i == 3) {
                    order = !order;
                }

                Color color;
                if (!order) {
                    color = map[faceindex][h][this.Plane];
                } else {
                    color = map[faceindex][this.Plane][h];
                }

                if (i != 2 && i != 5) {
                    ((CubeFace)super.Faces[i]).SetMap(color, 0, h);
                } else {
                    ((CubeFace)super.Faces[i]).SetMap(color, h, 0);
                }

                ++h;
            } while(h < 3);

            ++i;
        } while(i <= 5);

    }

    public void Spin(boolean counterclock) {
        double theta = (double)(counterclock ? 15 : -15) * Math.PI / 180.0;
        switch (this.Axis) {
            case 0:
                this.XRotate(theta);
                break;
            case 1:
                this.YRotate(theta);
                break;
            case 2:
                this.ZRotate(theta);
        }

        this.Transform();
    }
}
