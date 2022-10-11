package edu.umbc.cs.sli2;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

class Matrix3D {
    float xx;
    float xy;
    float xz;
    float xd;
    float yx;
    float yy;
    float yz;
    float yd;
    float zx;
    float zy;
    float zz;
    float zd;
    float dx;
    float dy;
    float dz;
    float dd;
    static final double pi = 3.14159265;

    Matrix3D Inverse() {
        float A11 = this.yy * this.zz * this.dd + this.zy * this.dz * this.yd + this.dy * this.yz * this.zd - this.yd * this.zz * this.dy - this.zd * this.dz * this.yy - this.dd * this.yz * this.zy;
        float A12 = -(this.yx * this.zz * this.dd + this.zx * this.dz * this.yd + this.dx * this.yz * this.zd - this.yd * this.zz * this.dx - this.zd * this.dz * this.yx - this.dd * this.yz * this.zx);
        float A13 = this.yx * this.zy * this.dd + this.zx * this.dy * this.yd + this.dx * this.yy * this.zd - this.yd * this.zy * this.dx - this.zd * this.dy * this.yx - this.dd * this.yy * this.zx;
        float A14 = -(this.yx * this.zy * this.dz + this.zx * this.dy * this.yz + this.dx * this.yy * this.zz - this.yz * this.zy * this.dx - this.zz * this.dy * this.yx - this.dz * this.yy * this.zx);
        float A21 = -(this.xy * this.zz * this.dd + this.zy * this.dz * this.xd + this.dy * this.xz * this.zd - this.xd * this.zz * this.dy - this.zd * this.dz * this.xy - this.dd * this.xz * this.zy);
        float A22 = this.xx * this.zz * this.dd + this.zx * this.dz * this.xd + this.dx * this.xz * this.zd - this.xd * this.zz * this.dx - this.zd * this.dz * this.xx - this.dd * this.xz * this.zx;
        float A23 = -(this.xx * this.zy * this.dd + this.zx * this.dy * this.xd + this.dx * this.xy * this.zd - this.xd * this.zy * this.dx - this.zd * this.dy * this.xx - this.dd * this.xy * this.zx);
        float A24 = this.xx * this.zy * this.dz + this.zx * this.dy * this.xz + this.dx * this.xy * this.zz - this.xz * this.zy * this.dx - this.zz * this.dy * this.xx - this.dz * this.xy * this.zx;
        float A31 = this.xy * this.yz * this.dd + this.yy * this.dz * this.xd + this.dy * this.xz * this.yd - this.xd * this.yz * this.dy - this.yd * this.dz * this.xy - this.dd * this.xz * this.yy;
        float A32 = -(this.xx * this.yz * this.dd + this.yx * this.dz * this.xd + this.dx * this.xz * this.yd - this.xd * this.yz * this.dx - this.yd * this.dz * this.xx - this.dd * this.xz * this.yx);
        float A33 = this.xx * this.yy * this.dd + this.yx * this.dy * this.xd + this.dx * this.xy * this.yd - this.xd * this.yy * this.dx - this.yd * this.dy * this.xx - this.dd * this.xy * this.yx;
        float A34 = -(this.xx * this.yy * this.dz + this.yx * this.dy * this.xz + this.dx * this.xy * this.yz - this.xz * this.yy * this.dx - this.yz * this.dy * this.xx - this.dz * this.xy * this.yx);
        float A41 = -(this.xy * this.yz * this.zd + this.yy * this.zz * this.xd + this.zy * this.xz * this.yd - this.xd * this.yz * this.zy - this.yd * this.zz * this.xy - this.zd * this.xz * this.yy);
        float A42 = this.xx * this.yz * this.zd + this.yx * this.zz * this.xd + this.zx * this.xz * this.yd - this.xd * this.yz * this.zx - this.yd * this.zz * this.xx - this.zd * this.xz * this.yx;
        float A43 = -(this.xx * this.yy * this.zd + this.yx * this.zy * this.xd + this.zx * this.xy * this.yd - this.xd * this.yy * this.zx - this.yd * this.zy * this.xx - this.zd * this.xy * this.yx);
        float A44 = this.xx * this.yy * this.zz + this.yx * this.zy * this.xz + this.zx * this.xy * this.yz - this.xz * this.yy * this.zx - this.yz * this.zy * this.xx - this.zz * this.xy * this.yx;
        float det = this.xx * A11 + this.xy * A12 + this.xz * A13 + this.xd * A14;
        float[][] mat = new float[][]{{A11 / det, A21 / det, A31 / det, A41 / det}, {A12 / det, A22 / det, A32 / det, A42 / det}, {A13 / det, A23 / det, A33 / det, A43 / det}, {A14 / det, A24 / det, A34 / det, A44 / det}};
        return new Matrix3D(mat);
    }

    void PreYRotate(double theta) {
        double cost = Math.cos(theta);
        double sint = Math.sin(theta);
        float nxz = (float)((double)this.xz * cost + (double)this.xx * sint);
        float nyz = (float)((double)this.yz * cost + (double)this.yx * sint);
        float nzz = (float)((double)this.zz * cost + (double)this.zx * sint);
        float ndz = (float)((double)this.dz * cost + (double)this.dx * sint);
        float nxx = (float)((double)this.xx * cost - (double)this.xz * sint);
        float nyx = (float)((double)this.yx * cost - (double)this.yz * sint);
        float nzx = (float)((double)this.zx * cost - (double)this.zz * sint);
        float ndx = (float)((double)this.dx * cost - (double)this.dz * sint);
        this.xz = nxz;
        this.yz = nyz;
        this.zz = nzz;
        this.dz = ndz;
        this.xx = nxx;
        this.yx = nyx;
        this.zx = nzx;
        this.dx = ndx;
    }

    void PreZRotate(double theta) {
        double cost = Math.cos(theta);
        double sint = Math.sin(theta);
        float nxx = (float)((double)this.xx * cost + (double)this.xy * sint);
        float nyx = (float)((double)this.yx * cost + (double)this.yy * sint);
        float nzx = (float)((double)this.zx * cost + (double)this.zy * sint);
        float ndx = (float)((double)this.dx * cost + (double)this.dy * sint);
        float nxy = (float)((double)this.xy * cost - (double)this.xx * sint);
        float nyy = (float)((double)this.yy * cost - (double)this.yx * sint);
        float nzy = (float)((double)this.zy * cost - (double)this.zx * sint);
        float ndy = (float)((double)this.dy * cost - (double)this.dx * sint);
        this.xx = nxx;
        this.yx = nyx;
        this.zx = nzx;
        this.dx = ndx;
        this.xy = nxy;
        this.yy = nyy;
        this.zy = nzy;
        this.dy = ndy;
    }

    Vertex Origin() {
        float A41 = -(this.xy * this.yz * this.zd + this.yy * this.zz * this.xd + this.zy * this.xz * this.yd - this.xd * this.yz * this.zy - this.yd * this.zz * this.xy - this.zd * this.xz * this.yy);
        float A42 = this.xx * this.yz * this.zd + this.yx * this.zz * this.xd + this.zx * this.xz * this.yd - this.xd * this.yz * this.zx - this.yd * this.zz * this.xx - this.zd * this.xz * this.yx;
        float A43 = -(this.xx * this.yy * this.zd + this.yx * this.zy * this.xd + this.zx * this.xy * this.yd - this.xd * this.yy * this.zx - this.yd * this.zy * this.xx - this.zd * this.xy * this.yx);
        float A44 = this.xx * this.yy * this.zz + this.yx * this.zy * this.xz + this.zx * this.xy * this.yz - this.xz * this.yy * this.zx - this.yz * this.zy * this.xx - this.zz * this.xy * this.yx;
        float det = this.dx * A41 + this.dy * A42 + this.dz * A43 + this.dd * A44;
        float x = A41 / det;
        float y = A42 / det;
        float z = A43 / det;
        float d = A44 / det;
        return new Vertex(x / d, y / d, z / d);
    }

    Matrix3D() {
        this.xx = 1.0F;
        this.yy = 1.0F;
        this.zz = 1.0F;
        this.dd = 1.0F;
    }

    Matrix3D(float[][] mat) {
        this.xx = mat[0][0];
        this.xy = mat[0][1];
        this.xz = mat[0][2];
        this.xd = mat[0][3];
        this.yx = mat[1][0];
        this.yy = mat[1][1];
        this.yz = mat[1][2];
        this.yd = mat[1][3];
        this.zx = mat[2][0];
        this.zy = mat[2][1];
        this.zz = mat[2][2];
        this.zd = mat[2][3];
        this.dx = mat[3][0];
        this.dy = mat[3][1];
        this.dz = mat[3][2];
        this.dd = mat[3][3];
    }

    void PreTranslate(float x, float y, float z) {
        this.xd += x * this.xx;
        this.yd += y * this.yy;
        this.zd += z * this.zz;
    }

    void ProXRotate(double theta) {
        double cost = Math.cos(theta);
        double sint = Math.sin(theta);
        float nyx = (float)((double)this.yx * cost - (double)this.zx * sint);
        float nyy = (float)((double)this.yy * cost - (double)this.zy * sint);
        float nyz = (float)((double)this.yz * cost - (double)this.zz * sint);
        float nyd = (float)((double)this.yd * cost - (double)this.zd * sint);
        float nzx = (float)((double)this.zx * cost + (double)this.yx * sint);
        float nzy = (float)((double)this.zy * cost + (double)this.yy * sint);
        float nzz = (float)((double)this.zz * cost + (double)this.yz * sint);
        float nzd = (float)((double)this.zd * cost + (double)this.yd * sint);
        this.yx = nyx;
        this.yy = nyy;
        this.yz = nyz;
        this.yd = nyd;
        this.zx = nzx;
        this.zy = nzy;
        this.zz = nzz;
        this.zd = nzd;
    }

    public void Print() {
        System.out.println("--------------------");
        System.out.println("|" + this.xx + " " + this.xy + " " + this.xz + " " + this.xd + "| ");
        System.out.println("|" + this.yx + " " + this.yy + " " + this.yz + " " + this.yd + "| ");
        System.out.println("|" + this.zx + " " + this.zy + " " + this.zz + " " + this.zd + "| ");
        System.out.println("|" + this.dx + " " + this.dy + " " + this.dz + " " + this.dd + "| ");
    }

    void Unify() {
        this.xx = 1.0F;
        this.xy = 0.0F;
        this.xz = 0.0F;
        this.xd = 0.0F;
        this.yx = 0.0F;
        this.yy = 1.0F;
        this.yz = 0.0F;
        this.yd = 0.0F;
        this.zx = 0.0F;
        this.zy = 0.0F;
        this.zz = 1.0F;
        this.zd = 0.0F;
        this.dx = 0.0F;
        this.dy = 0.0F;
        this.dz = 0.0F;
        this.dd = 1.0F;
    }

    void SetValue(float xx, float xy, float xz, float yx, float yy, float yz, float zx, float zy, float zz) {
        this.xx = xx;
        this.xy = xy;
        this.xz = xz;
        this.yx = yx;
        this.yy = yy;
        this.yz = yz;
        this.zx = zx;
        this.zy = zy;
        this.zz = zz;
        this.xd = 0.0F;
        this.yd = 0.0F;
        this.zd = 0.0F;
        this.dx = 0.0F;
        this.dy = 0.0F;
        this.dz = 0.0F;
        this.dd = 1.0F;
    }

    void ProYRotate(double theta) {
        double cost = Math.cos(theta);
        double sint = Math.sin(theta);
        float nzx = (float)((double)this.zx * cost - (double)this.xx * sint);
        float nzy = (float)((double)this.zy * cost - (double)this.xy * sint);
        float nzz = (float)((double)this.zz * cost - (double)this.xz * sint);
        float nzd = (float)((double)this.zd * cost - (double)this.xd * sint);
        float nxx = (float)((double)this.xx * cost + (double)this.zx * sint);
        float nxy = (float)((double)this.xy * cost + (double)this.zy * sint);
        float nxz = (float)((double)this.xz * cost + (double)this.zz * sint);
        float nxd = (float)((double)this.xd * cost + (double)this.zd * sint);
        this.zx = nzx;
        this.zy = nzy;
        this.zz = nzz;
        this.zd = nzd;
        this.xx = nxx;
        this.xy = nxy;
        this.xz = nxz;
        this.xd = nxd;
    }

    void ProZRotate(double theta) {
        double cost = Math.cos(theta);
        double sint = Math.sin(theta);
        float nxx = (float)((double)this.xx * cost - (double)this.yx * sint);
        float nxy = (float)((double)this.xy * cost - (double)this.yy * sint);
        float nxz = (float)((double)this.xz * cost - (double)this.yz * sint);
        float nxd = (float)((double)this.xd * cost - (double)this.yd * sint);
        float nyx = (float)((double)this.yx * cost + (double)this.xx * sint);
        float nyy = (float)((double)this.yy * cost + (double)this.xy * sint);
        float nyz = (float)((double)this.yz * cost + (double)this.xz * sint);
        float nyd = (float)((double)this.yd * cost + (double)this.xd * sint);
        this.xx = nxx;
        this.xy = nxy;
        this.xz = nxz;
        this.xd = nxd;
        this.yx = nyx;
        this.yy = nyy;
        this.yz = nyz;
        this.yd = nyd;
    }

    void ProScale(float factorx, float factory, float factorz) {
        this.xx *= factorx;
        this.xy *= factorx;
        this.xz *= factorx;
        this.xd *= factorx;
        this.yx *= factory;
        this.yy *= factory;
        this.yz *= factory;
        this.yd *= factory;
        this.zx *= factorz;
        this.zy *= factorz;
        this.zz *= factorz;
        this.zd *= factorz;
    }

    void Transform(Vertex[] v, Vertex[] tv, int vernum) {
        for(int i = 0; i < vernum; ++i) {
            float x = v[i].x;
            float y = v[i].y;
            float z = v[i].z;
            float d = x * this.dx + y * this.dy + z * this.dz + this.dd;
            tv[i].x = (x * this.xx + y * this.xy + z * this.xz + this.xd) / d;
            tv[i].y = (x * this.yx + y * this.yy + z * this.yz + this.yd) / d;
            tv[i].z = (x * this.zx + y * this.zy + z * this.zz + this.zd) / d;
        }

    }

    void Transform(Vertex v, Vertex tv) {
        float x = v.x;
        float y = v.y;
        float z = v.z;
        float d = x * this.dx + y * this.dy + z * this.dz + this.dd;
        tv.x = (x * this.xx + y * this.xy + z * this.xz + this.xd) / d;
        tv.y = (x * this.yx + y * this.yy + z * this.yz + this.yd) / d;
        tv.z = (x * this.zx + y * this.zy + z * this.zz + this.zd) / d;
    }

    void ProTranslate(float x, float y, float z) {
        this.xx += x * this.dx;
        this.xy += x * this.dy;
        this.xz += x * this.dz;
        this.xd += x * this.dd;
        this.yx += y * this.dx;
        this.yy += y * this.dy;
        this.yz += y * this.dz;
        this.yd += y * this.dd;
        this.zx += z * this.dx;
        this.zy += z * this.dy;
        this.zz += z * this.dz;
        this.zd += z * this.dd;
    }

    void PreXRotate(double theta) {
        double cost = Math.cos(theta);
        double sint = Math.sin(theta);
        float nxy = (float)((double)this.xy * cost + (double)this.xz * sint);
        float nyy = (float)((double)this.yy * cost + (double)this.yz * sint);
        float nzy = (float)((double)this.zy * cost + (double)this.zz * sint);
        float ndy = (float)((double)this.dy * cost + (double)this.dz * sint);
        float nxz = (float)((double)this.xz * cost - (double)this.xy * sint);
        float nyz = (float)((double)this.yz * cost - (double)this.yy * sint);
        float nzz = (float)((double)this.zz * cost - (double)this.zy * sint);
        float ndz = (float)((double)this.dz * cost - (double)this.dy * sint);
        this.xy = nxy;
        this.yy = nyy;
        this.zy = nzy;
        this.dy = ndy;
        this.xz = nxz;
        this.yz = nyz;
        this.zz = nzz;
        this.dz = ndz;
    }

    void PreScale(float factorx, float factory, float factorz) {
        this.xx *= factorx;
        this.yx *= factorx;
        this.zx *= factorx;
        this.dx *= factorx;
        this.xy *= factory;
        this.yy *= factory;
        this.zy *= factory;
        this.dy *= factory;
        this.xz *= factorz;
        this.yz *= factorz;
        this.zz *= factorz;
        this.dz *= factorz;
    }
}
