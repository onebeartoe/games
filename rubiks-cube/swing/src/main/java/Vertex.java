//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

class Vertex {
    public float x;
    public float y;
    public float z;

    public void Print() {
        System.out.println("Vertex [" + this.x + ", " + this.y + ", " + this.z + "]");
    }

    public Vertex() {
        this.x = this.y = this.z = 0.0F;
    }

    public Vertex(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vertex(Vertex v) {
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
    }
}
