//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

class Vector3D {
    public float x;
    public float y;
    public float z;

    public Vector3D(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3D(Vertex ver) {
        this.x = ver.x;
        this.y = ver.y;
        this.z = ver.z;
    }

    public Vector3D(Vertex v1, Vertex v2) {
        this.x = v2.x - v1.x;
        this.y = v2.y - v1.y;
        this.z = v2.z - v1.z;
    }

    public static float dotpro(Vector3D v1, Vector3D v2) {
        return v1.x * v2.x + v1.y * v2.y + v1.z * v2.z;
    }

    public static Vector3D crosspro(Vector3D v1, Vector3D v2) {
        return new Vector3D(v1.y * v2.z - v1.z * v2.y, v1.z * v2.x - v1.x * v2.z, v1.x * v2.y - v1.y * v2.x);
    }

    public void Normalize() {
        double length = Math.sqrt((double)(this.x * this.x + this.y * this.y + this.z * this.z));
        this.x = (float)((double)this.x / length);
        this.y = (float)((double)this.y / length);
        this.z = (float)((double)this.z / length);
    }
}
