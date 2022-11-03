//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.Color;
import java.awt.Graphics;

class Face {
    public int VerNum;
    public Vertex[] Vertices;
    public Color FaceColor;
    private float XMin;
    private float XMax;
    private float YMin;
    private float YMax;
    private float ZMin;
    private float ZMax;
    protected Vertex[] ScreenVer;
    protected int[] ScreenX;
    protected int[] ScreenY;
    protected boolean Visible;
    protected float Illuminator;
    protected static boolean needlight;

    public final boolean CloserThan(Face face) {
        return this.ZMax + this.ZMin < face.ZMax + face.ZMin;
    }

    public void Print() {
        System.out.println("Face -- ");

        for(int i = 0; i < this.VerNum; ++i) {
            System.out.print("Origin  ");
            this.Vertices[i].Print();
            System.out.print("Screen  ");
            this.ScreenVer[i].Print();
        }

    }

    void CalcParam(Matrix3D viewmat) {
        viewmat.Transform(this.Vertices, this.ScreenVer, this.VerNum);
        Vector3D v1 = new Vector3D(this.ScreenVer[1], this.ScreenVer[2]);
        Vector3D v2 = new Vector3D(this.ScreenVer[1], this.ScreenVer[0]);
        Vector3D normal = Vector3D.crosspro(v1, v2);
        normal.Normalize();
        this.Visible = normal.z > 0.0F;
        this.Illuminator = 0.5F + 0.5F * normal.z;
        this.XMin = this.ScreenVer[0].x;
        this.XMax = this.XMin;
        this.YMin = this.ScreenVer[0].y;
        this.YMax = this.YMin;
        this.ZMin = this.ScreenVer[0].z;
        this.ZMax = this.ZMin;

        for(int i = 1; i < this.VerNum; ++i) {
            this.XMin = Math.min(this.XMin, this.ScreenVer[i].x);
            this.XMax = Math.max(this.XMax, this.ScreenVer[i].x);
            this.YMin = Math.min(this.YMin, this.ScreenVer[i].y);
            this.YMax = Math.max(this.YMax, this.ScreenVer[i].y);
            this.ZMin = Math.min(this.ZMin, this.ScreenVer[i].z);
            this.ZMax = Math.max(this.ZMax, this.ScreenVer[i].z);
        }

    }

    public Face(int vernum) {
        this.VerNum = vernum;
        this.Vertices = new Vertex[vernum];
        this.ScreenX = new int[vernum];
        this.ScreenY = new int[vernum];
        this.ScreenVer = new Vertex[vernum];

        for(int i = 0; i < vernum; ++i) {
            this.ScreenVer[i] = new Vertex();
        }

    }

    public static void SetLight(boolean light) {
        needlight = light;
    }

    public void Paint(Graphics graph, int width, int height) {
        if (this.Visible) {
            for(int i = 0; i < this.VerNum; ++i) {
                this.ScreenX[i] = (int)this.ScreenVer[i].x + width / 2;
                this.ScreenY[i] = height / 2 - (int)this.ScreenVer[i].y;
            }

            Color color;
            if (needlight) {
                color = new Color((int)(this.Illuminator * (float)this.FaceColor.getRed()), (int)(this.Illuminator * (float)this.FaceColor.getGreen()), (int)(this.Illuminator * (float)this.FaceColor.getBlue()));
            } else {
                color = this.FaceColor;
            }

            graph.setColor(color);
            graph.fillPolygon(this.ScreenX, this.ScreenY, this.VerNum);
            graph.setColor(Color.black);
            graph.drawPolygon(this.ScreenX, this.ScreenY, this.VerNum);
        }
    }
}
