//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.Graphics;

class CubeModel extends Model3D {
    public Vertex Unproject(float selx, float sely) {
        Matrix3D invmat = super.ViewMat.Inverse();
        int i = 3;

        do {
            Vertex hotpoint = new Vertex();
            Vertex selpoint = new Vertex(selx, sely, ((CubeFace)super.Faces.elementAt(i)).Unproject(selx, sely));
            invmat.Transform(selpoint, hotpoint);
            float x = hotpoint.x;
            float y = hotpoint.y;
            float z = hotpoint.z;
            float deltax = x > 0.0F ? (x > 3.0F ? x - 3.0F : 3.0F - x) : (x < -3.0F ? -3.0F - x : x + 3.0F);
            if ((double)deltax < 1.0E-4 && y >= -3.0F && y <= 3.0F && z >= -3.0F && z <= 3.0F) {
                hotpoint.x = (float)(x > 0.0F ? 3 : -3);
                return hotpoint;
            }

            float deltay = y > 0.0F ? (y > 3.0F ? y - 3.0F : 3.0F - y) : (y < -3.0F ? -3.0F - y : y + 3.0F);
            if ((double)deltay < 1.0E-4 && x >= -3.0F && x <= 3.0F && z >= -3.0F && z <= 3.0F) {
                hotpoint.y = (float)(y > 0.0F ? 3 : -3);
                return hotpoint;
            }

            float deltaz = z > 0.0F ? (z > 3.0F ? z - 3.0F : 3.0F - z) : (z < -3.0F ? -3.0F - z : z + 3.0F);
            if ((double)deltaz < 1.0E-4 && x >= -3.0F && x <= 3.0F && y >= -3.0F && y <= 3.0F) {
                hotpoint.z = (float)(z > 0.0F ? 3 : -3);
                return hotpoint;
            }

            ++i;
        } while(i < 6);

        return null;
    }

    public synchronized void Paint(Graphics graph, int width, int height) {
        this.Sort();
        int i = 3;

        do {
            Face face = (Face)super.Faces.elementAt(i);
            face.Paint(graph, width, height);
            ++i;
        } while(i < 6);

    }

    CubeModel(Vertex viewpoint) {
        super(viewpoint);
    }
}
