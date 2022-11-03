//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.Color;
import java.awt.Graphics;

class CubeFace extends Face {
    int Height;
    int Width;
    Color[][] ColorMap;
    int[] SquareX = new int[4];
    int[] SquareY = new int[4];
    int[][] MapX;
    int[][] MapY;

    void SetMap(Color[][] map) {
        for(int i = 0; i < this.Height; ++i) {
            for(int j = 0; j < this.Width; ++j) {
                this.ColorMap[i][j] = map[i][j];
            }
        }

    }

    void SetMap(Color color, int height, int width) {
        this.ColorMap[height][width] = color;
    }

    CubeFace(int height, int width) {
        super(4);
        this.Height = height;
        this.Width = width;
        this.ColorMap = new Color[this.Height][this.Width];
        this.MapX = new int[this.Height + 1][this.Width + 1];
        this.MapY = new int[this.Height + 1][this.Width + 1];
    }

    public float Unproject(float x, float y) {
        float x1 = super.ScreenVer[1].x;
        float y1 = super.ScreenVer[1].y;
        float z1 = super.ScreenVer[1].z;
        float x2 = super.ScreenVer[2].x;
        float y2 = super.ScreenVer[2].y;
        float z2 = super.ScreenVer[2].z;
        float x3 = super.ScreenVer[3].x;
        float y3 = super.ScreenVer[3].y;
        float z3 = super.ScreenVer[3].z;
        float A11 = y2 * z3 - z2 * y3;
        float A12 = z2 * x3 - x2 * z3;
        float A13 = x2 * y3 - y2 * x3;
        float A21 = z1 * y3 - y1 * z3;
        float A22 = x1 * z3 - z1 * x3;
        float A23 = y1 * x3 - x1 * y3;
        float A31 = y1 * z2 - z1 * y2;
        float A32 = z1 * x2 - x1 * z2;
        float A33 = x1 * y2 - y1 * x2;
        float det = x1 * A11 + y1 * A12 + z1 * A13;
        float a = A11 + A21 + A31;
        float b = A12 + A22 + A32;
        float c = A13 + A23 + A33;
        return (det - a * x - b * y) / c;
    }

    public void Paint(Graphics graph, int width, int height) {
        if (super.Visible) {
            for(int i = 0; i < super.VerNum; ++i) {
                super.ScreenX[i] = (int)super.ScreenVer[i].x + width / 2;
                super.ScreenY[i] = height / 2 - (int)super.ScreenVer[i].y;
            }

            float deltaxx = (float)(super.ScreenX[1] - super.ScreenX[0]) / (float)this.Width;
            float deltaxy = (float)(super.ScreenY[1] - super.ScreenY[0]) / (float)this.Width;
            float deltayx = (float)(super.ScreenX[3] - super.ScreenX[0]) / (float)this.Height;
            float deltayy = (float)(super.ScreenY[3] - super.ScreenY[0]) / (float)this.Height;

            int h;
            int w;
            for(h = 0; h <= this.Height; ++h) {
                for(w = 0; w <= this.Width; ++w) {
                    this.MapX[h][w] = (int)((double)((float)super.ScreenX[0] + (float)w * deltaxx + (float)h * deltayx) + 0.5);
                    this.MapY[h][w] = (int)((double)((float)super.ScreenY[0] + (float)w * deltaxy + (float)h * deltayy) + 0.5);
                }
            }

            for(h = 0; h < this.Height; ++h) {
                for(w = 0; w < this.Width; ++w) {
                    this.SquareX[0] = this.MapX[h][w];
                    this.SquareX[1] = this.MapX[h][w + 1];
                    this.SquareX[2] = this.MapX[h + 1][w + 1];
                    this.SquareX[3] = this.MapX[h + 1][w];
                    this.SquareY[0] = this.MapY[h][w];
                    this.SquareY[1] = this.MapY[h][w + 1];
                    this.SquareY[2] = this.MapY[h + 1][w + 1];
                    this.SquareY[3] = this.MapY[h + 1][w];
                    Color color;
                    if (Face.needlight) {
                        color = new Color((int)(super.Illuminator * (float)this.ColorMap[h][w].getRed()), (int)(super.Illuminator * (float)this.ColorMap[h][w].getGreen()), (int)(super.Illuminator * (float)this.ColorMap[h][w].getBlue()));
                    } else {
                        color = this.ColorMap[h][w];
                    }

                    graph.setColor(color);
                    graph.fillPolygon(this.SquareX, this.SquareY, 4);
                }
            }

            graph.setColor(Color.black);

            for(h = 0; h <= this.Height; ++h) {
                graph.drawLine((int)((double)((float)super.ScreenX[0] + (float)h * deltayx) + 0.5), (int)((double)((float)super.ScreenY[0] + (float)h * deltayy) + 0.5), (int)((double)((float)super.ScreenX[1] + (float)h * deltayx) + 0.5), (int)((double)((float)super.ScreenY[1] + (float)h * deltayy) + 0.5));
            }

            for(w = 0; w <= this.Width; ++w) {
                graph.drawLine((int)((double)((float)super.ScreenX[0] + (float)w * deltaxx) + 0.5), (int)((double)((float)super.ScreenY[0] + (float)w * deltaxy) + 0.5), (int)((double)((float)super.ScreenX[3] + (float)w * deltaxx) + 0.5), (int)((double)((float)super.ScreenY[3] + (float)w * deltaxy) + 0.5));
            }

        }
    }
}
