//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.Graphics;

class CubeSystem {
    static final String[] PlaneName = new String[]{"Plane0", "Plane1", "Plane2"};
    static final int XPlane = 0;
    static final int YPlane = 1;
    static final int ZPlane = 2;
    CubeMap Map = new CubeMap();
    Object3D Cube = this.WholeCube();
    CubePlane[][] PlaneTable;
    Model3D[] ModelPlane;
    CubeModel ModelCube;
    private boolean InSpin;
    private int CurAxis;
    private int CurPlane;
    private boolean CurCounterclock;
    private static final int[][] SpinTable = new int[][]{{500, 202, 201, 200, 400, 410, 420, 300, 301, 302, 520, 510, 500}, {501, 212, 211, 210, 401, 411, 421, 310, 311, 312, 521, 511, 501}, {502, 222, 221, 220, 402, 412, 422, 320, 321, 322, 522, 512, 502}, {502, 120, 110, 100, 402, 401, 400, 0, 10, 20, 500, 501, 502}, {512, 121, 111, 101, 412, 411, 410, 1, 11, 21, 510, 511, 512}, {522, 122, 112, 102, 422, 421, 420, 2, 12, 22, 520, 521, 522}, {100, 101, 102, 320, 310, 300, 2, 1, 0, 200, 210, 220, 100}, {110, 111, 112, 321, 311, 301, 12, 11, 10, 201, 211, 221, 110}, {120, 121, 122, 322, 312, 302, 22, 21, 20, 202, 212, 222, 120}};

    public static int SelectFace(Vertex hotpoint) {
        if (hotpoint == null) {
            return -1;
        } else if (hotpoint.x == -3.0F) {
            return 0;
        } else if (hotpoint.x == 3.0F) {
            return 1;
        } else if (hotpoint.y == -3.0F) {
            return 2;
        } else if (hotpoint.y == 3.0F) {
            return 3;
        } else if (hotpoint.z == -3.0F) {
            return 4;
        } else {
            return hotpoint.z == 3.0F ? 5 : -1;
        }
    }

    public void SpinMap(int axis, int plane, boolean dir) {
        this.Map.Spin(axis, plane, dir);
    }

    void DefineBound(int width, int height) {
        this.ModelCube.DefineBound(width, height);
    }

    CubeSystem(Vertex viewpoint) {
        this.ModelCube = new CubeModel(viewpoint);
        this.ModelPlane = new Model3D[3];
        int i = 0;

        do {
            this.ModelPlane[i] = new Model3D(this.ModelCube.GetView());
            ++i;
        } while(i <= 2);

        this.PlaneTable = new CubePlane[3][3];
        i = 0;

        do {
            int plane = 0;

            do {
                this.PlaneTable[i][plane] = new CubePlane(i, plane);
                this.ModelPlane[i].AddObject(PlaneName[plane], this.PlaneTable[i][plane]);
                ++plane;
            } while(plane < 3);

            ++i;
        } while(i <= 2);

        i = 0;

        do {
            ((CubeFace)this.Cube.Faces[i]).SetMap(this.Map.ColorMap[i]);
            ++i;
        } while(i < 6);

        this.ModelCube.AddObject("Cube", this.Cube);
    }

    void XRotateView(double theta) {
        this.ModelCube.XRotateView(theta);
    }

    public synchronized void SpinBegin(int axis, int plane, boolean counterclock) {
        while(this.InSpin) {
            try {
                this.wait();
            } catch (InterruptedException var6) {
            }
        }

        this.InSpin = true;
        int i = 0;

        do {
            this.PlaneTable[axis][i].SetMap(this.Map.ColorMap);
            ++i;
        } while(i < 3);

        this.CurAxis = axis;
        this.CurPlane = plane;
        this.CurCounterclock = counterclock;
    }

    public static int ComputeSpin(Vertex start, Vertex end) {
        int startface = SelectFace(start);
        int startver = SelectVer(start);
        int starthor = SelectHor(start);
        int startmagic = startface * 100 + startver * 10 + starthor;
        if (startface != -1 && startver != -1 && starthor != -1) {
            int endface = SelectFace(end);
            int endver = SelectVer(end);
            int endhor = SelectHor(end);
            int endmagic = endface * 100 + endver * 10 + endhor;
            if (endface != -1 && endver != -1 && endhor != -1) {
                int circle = 0;

                do {
                    int num = 0;

                    do {
                        if (SpinTable[circle][num] == startmagic && SpinTable[circle][num + 1] == endmagic) {
                            return circle + 1;
                        }

                        if (SpinTable[circle][num] == endmagic && SpinTable[circle][num + 1] == startmagic) {
                            return -(circle + 1);
                        }

                        ++num;
                    } while(num <= 11);

                    ++circle;
                } while(circle < 9);

                return 9999;
            } else {
                return 9999;
            }
        } else {
            return 9999;
        }
    }

    public void SetCubeMap() {
        int i = 0;

        do {
            ((CubeFace)this.Cube.Faces[i]).SetMap(this.Map.ColorMap[i]);
            ++i;
        } while(i < 6);

    }

    void YRotateView(double theta) {
        this.ModelCube.YRotateView(theta);
    }

    void ZRotateView(double theta) {
        this.ModelCube.ZRotateView(theta);
    }

    public static int SelectHor(Vertex hotpoint) {
        if (hotpoint == null) {
            return -1;
        } else {
            int pos = -1;
            if (hotpoint.x == -3.0F || hotpoint.x == 3.0F) {
                pos = (int)((hotpoint.y + 3.0F) / 2.0F);
            }

            if (hotpoint.y == -3.0F || hotpoint.y == 3.0F) {
                pos = (int)((hotpoint.z + 3.0F) / 2.0F);
            }

            if (hotpoint.z == -3.0F || hotpoint.z == 3.0F) {
                pos = (int)((hotpoint.x + 3.0F) / 2.0F);
            }

            return pos < 3 ? pos : 2;
        }
    }

    private Object3D WholeCube() {
        Vertex[] vertex = new Vertex[8];
        CubeFace[] face = new CubeFace[6];
        vertex[0] = new Vertex(-3.0F, -3.0F, -3.0F);
        vertex[1] = new Vertex(3.0F, -3.0F, -3.0F);
        vertex[2] = new Vertex(3.0F, 3.0F, -3.0F);
        vertex[3] = new Vertex(-3.0F, 3.0F, -3.0F);
        vertex[4] = new Vertex(-3.0F, -3.0F, 3.0F);
        vertex[5] = new Vertex(3.0F, -3.0F, 3.0F);
        vertex[6] = new Vertex(3.0F, 3.0F, 3.0F);
        vertex[7] = new Vertex(-3.0F, 3.0F, 3.0F);
        int i = 0;

        do {
            face[i] = new CubeFace(3, 3);
            ++i;
        } while(i < 6);

        face[0].Vertices[0] = vertex[0];
        face[0].Vertices[1] = vertex[4];
        face[0].Vertices[2] = vertex[7];
        face[0].Vertices[3] = vertex[3];
        face[1].Vertices[0] = vertex[1];
        face[1].Vertices[1] = vertex[2];
        face[1].Vertices[2] = vertex[6];
        face[1].Vertices[3] = vertex[5];
        face[2].Vertices[0] = vertex[0];
        face[2].Vertices[1] = vertex[1];
        face[2].Vertices[2] = vertex[5];
        face[2].Vertices[3] = vertex[4];
        face[3].Vertices[0] = vertex[3];
        face[3].Vertices[1] = vertex[7];
        face[3].Vertices[2] = vertex[6];
        face[3].Vertices[3] = vertex[2];
        face[4].Vertices[0] = vertex[0];
        face[4].Vertices[1] = vertex[3];
        face[4].Vertices[2] = vertex[2];
        face[4].Vertices[3] = vertex[1];
        face[5].Vertices[0] = vertex[4];
        face[5].Vertices[1] = vertex[5];
        face[5].Vertices[2] = vertex[6];
        face[5].Vertices[3] = vertex[7];
        Object3D cube = new Object3D();
        cube.Define(vertex, 8, face, 6);
        return cube;
    }

    public Vertex Unproject(float selx, float sely) {
        return this.ModelCube.Unproject(selx, sely);
    }

    public void NewGame() {
        this.Map.Reset();
        this.SetCubeMap();
    }

    public static int SelectVer(Vertex hotpoint) {
        if (hotpoint == null) {
            return -1;
        } else {
            int pos = -1;
            if (hotpoint.x == -3.0F || hotpoint.x == 3.0F) {
                pos = (int)((hotpoint.z + 3.0F) / 2.0F);
            }

            if (hotpoint.y == -3.0F || hotpoint.y == 3.0F) {
                pos = (int)((hotpoint.x + 3.0F) / 2.0F);
            }

            if (hotpoint.z == -3.0F || hotpoint.z == 3.0F) {
                pos = (int)((hotpoint.y + 3.0F) / 2.0F);
            }

            return pos < 3 ? pos : 2;
        }
    }

    public synchronized void Paint(Graphics graph, int width, int height) {
        if (this.InSpin) {
            this.ModelPlane[this.CurAxis].Paint(graph, width, height);
        } else {
            this.ModelCube.Paint(graph, width, height);
        }

    }

    public synchronized void Spin(int step) {
        if (this.InSpin) {
            double theta = (double)((this.CurCounterclock ? 15 : -15) * step) * Math.PI / 180.0;
            switch (this.CurAxis) {
                case 0:
                    this.ModelPlane[0].XRotate(PlaneName[this.CurPlane], theta);
                    break;
                case 1:
                    this.ModelPlane[1].YRotate(PlaneName[this.CurPlane], theta);
                    break;
                case 2:
                    this.ModelPlane[2].ZRotate(PlaneName[this.CurPlane], theta);
            }

            this.ModelPlane[this.CurAxis].Transform(PlaneName[this.CurPlane]);
        }
    }

    public synchronized void SpinEnd() {
        if (this.InSpin) {
            this.ModelPlane[this.CurAxis].Unify(PlaneName[this.CurPlane]);
            this.ModelPlane[this.CurAxis].Transform(PlaneName[this.CurPlane]);
            this.SpinMap(this.CurAxis, this.CurPlane, this.CurCounterclock);
            this.SetCubeMap();
            this.InSpin = false;
            this.notify();
        }
    }
}
