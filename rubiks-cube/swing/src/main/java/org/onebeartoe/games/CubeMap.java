//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.Color;

class CubeMap {
    Color[][][] ColorMap = new Color[6][3][3];
    static final int XPlane = 0;
    static final int YPlane = 1;
    static final int ZPlane = 2;
    private final Color[] ColorList = new Color[]{new Color(0, 255, 255), new Color(255, 128, 0), new Color(255, 128, 255), new Color(128, 0, 255), new Color(255, 255, 255), new Color(128, 0, 0)};
    private final int[][] SpinMap = new int[][]{{4, 2, 5, 3}, {0, 4, 1, 5}, {2, 0, 3, 1}};

    CubeMap() {
        this.Reset();
    }

    private void SpinCounterclock(int axis, int plane) {
        Color color0 = this.ColorMap[this.SpinMap[axis][0]][plane][0];
        Color color1 = this.ColorMap[this.SpinMap[axis][0]][plane][1];
        Color color2 = this.ColorMap[this.SpinMap[axis][0]][plane][2];
        this.ColorMap[this.SpinMap[axis][0]][plane][0] = this.ColorMap[this.SpinMap[axis][1]][2][plane];
        this.ColorMap[this.SpinMap[axis][0]][plane][1] = this.ColorMap[this.SpinMap[axis][1]][1][plane];
        this.ColorMap[this.SpinMap[axis][0]][plane][2] = this.ColorMap[this.SpinMap[axis][1]][0][plane];
        this.ColorMap[this.SpinMap[axis][1]][0][plane] = this.ColorMap[this.SpinMap[axis][2]][0][plane];
        this.ColorMap[this.SpinMap[axis][1]][1][plane] = this.ColorMap[this.SpinMap[axis][2]][1][plane];
        this.ColorMap[this.SpinMap[axis][1]][2][plane] = this.ColorMap[this.SpinMap[axis][2]][2][plane];
        this.ColorMap[this.SpinMap[axis][2]][0][plane] = this.ColorMap[this.SpinMap[axis][3]][plane][2];
        this.ColorMap[this.SpinMap[axis][2]][1][plane] = this.ColorMap[this.SpinMap[axis][3]][plane][1];
        this.ColorMap[this.SpinMap[axis][2]][2][plane] = this.ColorMap[this.SpinMap[axis][3]][plane][0];
        this.ColorMap[this.SpinMap[axis][3]][plane][0] = color0;
        this.ColorMap[this.SpinMap[axis][3]][plane][1] = color1;
        this.ColorMap[this.SpinMap[axis][3]][plane][2] = color2;
        this.SpinFace(axis, plane, false);
    }

    private void SpinClock(int axis, int plane) {
        Color color0 = this.ColorMap[this.SpinMap[axis][0]][plane][0];
        Color color1 = this.ColorMap[this.SpinMap[axis][0]][plane][1];
        Color color2 = this.ColorMap[this.SpinMap[axis][0]][plane][2];
        this.ColorMap[this.SpinMap[axis][0]][plane][0] = this.ColorMap[this.SpinMap[axis][3]][plane][0];
        this.ColorMap[this.SpinMap[axis][0]][plane][1] = this.ColorMap[this.SpinMap[axis][3]][plane][1];
        this.ColorMap[this.SpinMap[axis][0]][plane][2] = this.ColorMap[this.SpinMap[axis][3]][plane][2];
        this.ColorMap[this.SpinMap[axis][3]][plane][0] = this.ColorMap[this.SpinMap[axis][2]][2][plane];
        this.ColorMap[this.SpinMap[axis][3]][plane][1] = this.ColorMap[this.SpinMap[axis][2]][1][plane];
        this.ColorMap[this.SpinMap[axis][3]][plane][2] = this.ColorMap[this.SpinMap[axis][2]][0][plane];
        this.ColorMap[this.SpinMap[axis][2]][0][plane] = this.ColorMap[this.SpinMap[axis][1]][0][plane];
        this.ColorMap[this.SpinMap[axis][2]][1][plane] = this.ColorMap[this.SpinMap[axis][1]][1][plane];
        this.ColorMap[this.SpinMap[axis][2]][2][plane] = this.ColorMap[this.SpinMap[axis][1]][2][plane];
        this.ColorMap[this.SpinMap[axis][1]][0][plane] = color2;
        this.ColorMap[this.SpinMap[axis][1]][1][plane] = color1;
        this.ColorMap[this.SpinMap[axis][1]][2][plane] = color0;
        this.SpinFace(axis, plane, true);
    }

    public void Reset() {
        int face = 0;

        do {
            int h = 0;

            do {
                int w = 0;

                do {
                    this.ColorMap[face][h][w] = this.ColorList[face];
                    ++w;
                } while(w < 3);

                ++h;
            } while(h < 3);

            ++face;
        } while(face < 6);

    }

    private void SpinFace(int axis, int plane, boolean clockwise) {
        if (plane != 1) {
            int face = plane == 0 ? axis * 2 : axis * 2 + 1;
            boolean flag = face == 1 || face == 3 || face == 5;
            if (clockwise) {
                flag = !flag;
            }

            Color tempcolor;
            if (flag) {
                tempcolor = this.ColorMap[face][0][0];
                this.ColorMap[face][0][0] = this.ColorMap[face][2][0];
                this.ColorMap[face][2][0] = this.ColorMap[face][2][2];
                this.ColorMap[face][2][2] = this.ColorMap[face][0][2];
                this.ColorMap[face][0][2] = tempcolor;
                tempcolor = this.ColorMap[face][0][1];
                this.ColorMap[face][0][1] = this.ColorMap[face][1][0];
                this.ColorMap[face][1][0] = this.ColorMap[face][2][1];
                this.ColorMap[face][2][1] = this.ColorMap[face][1][2];
                this.ColorMap[face][1][2] = tempcolor;
            } else {
                tempcolor = this.ColorMap[face][0][0];
                this.ColorMap[face][0][0] = this.ColorMap[face][0][2];
                this.ColorMap[face][0][2] = this.ColorMap[face][2][2];
                this.ColorMap[face][2][2] = this.ColorMap[face][2][0];
                this.ColorMap[face][2][0] = tempcolor;
                tempcolor = this.ColorMap[face][0][1];
                this.ColorMap[face][0][1] = this.ColorMap[face][1][2];
                this.ColorMap[face][1][2] = this.ColorMap[face][2][1];
                this.ColorMap[face][2][1] = this.ColorMap[face][1][0];
                this.ColorMap[face][1][0] = tempcolor;
            }

        }
    }

    void Spin(int axis, int plane, boolean counterclock) {
        if (counterclock) {
            this.SpinCounterclock(axis, plane);
        } else {
            this.SpinClock(axis, plane);
        }

    }
}
