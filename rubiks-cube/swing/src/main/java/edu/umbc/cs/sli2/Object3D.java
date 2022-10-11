package edu.umbc.cs.sli2;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

class Object3D {
    Matrix3D TransMat = new Matrix3D();
    Vertex[] TransVer;
    public Vertex[] Vertices;
    public Face[] Faces;
    public int VerNum;
    public int FaceNum;

    void YRotate(double theta) {
        this.TransMat.ProYRotate(theta);
    }

    void ZRotate(double theta) {
        this.TransMat.ProZRotate(theta);
    }

    public void Unify() {
        this.TransMat.Unify();
    }

    public void Define(Vertex[] vertices, int vernum, Face[] faces, int facenum) {
        this.Vertices = new Vertex[vernum];

        for(int i = 0; i < vernum; ++i) {
            this.Vertices[i] = new Vertex(vertices[i]);
        }

        this.TransVer = vertices;
        this.VerNum = vernum;
        this.Faces = faces;
        this.FaceNum = facenum;
    }

    void Transform() {
        this.TransMat.Transform(this.Vertices, this.TransVer, this.VerNum);
    }

    public Object3D() {
        this.TransMat.Unify();
    }

    void XRotate(double theta) {
        this.TransMat.ProXRotate(theta);
    }
}
