//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.Graphics;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

class Model3D {
    Matrix3D ViewMat;
    Hashtable Objects;
    Vector Faces;

    public void YRotate(String name, double theta) {
        this.GetObject(name).YRotate(theta);
    }

    public void ZRotate(String name, double theta) {
        this.GetObject(name).ZRotate(theta);
    }

    void DefineBound(int width, int height) {
        float xmin = 10000.0F;
        float xmax = -10000.0F;
        float ymin = 10000.0F;
        float ymax = -10000.0F;
        float zmin = 10000.0F;
        float zmax = -10000.0F;
        Enumeration elementsEnum = this.Objects.elements();

        while(elementsEnum.hasMoreElements()) {
            Object3D obj = (Object3D)elementsEnum.nextElement();

            for(int i = 0; i < obj.VerNum; ++i) {
                xmin = Math.min(xmin, obj.Vertices[i].x);
                ymin = Math.min(ymin, obj.Vertices[i].y);
                zmin = Math.min(zmin, obj.Vertices[i].z);
                xmax = Math.max(xmax, obj.Vertices[i].x);
                ymax = Math.max(ymax, obj.Vertices[i].y);
                zmax = Math.max(zmax, obj.Vertices[i].z);
            }
        }

        float ratio = 0.6F * (float)Math.min(width, height) / Math.max(Math.max(xmax - xmin, ymax - ymin), zmax - zmin);
        this.ViewMat.ProScale(ratio, ratio, ratio);
        this.Transform();
    }

    public void XRotateView(double theta) {
        this.ViewMat.ProXRotate(theta);
    }

    public void Unify(String name) {
        this.GetObject(name).Unify();
    }

    protected void QuickSort(int left, int right) {
        int i = left;
        int j = right;
        int mid = (left + right) / 2;

        while(true) {
            while(!((Face)this.Faces.elementAt(mid)).CloserThan((Face)this.Faces.elementAt(i)) || i >= right) {
                while(((Face)this.Faces.elementAt(j)).CloserThan((Face)this.Faces.elementAt(mid)) && j > left) {
                    --j;
                }

                if (i <= j) {
                    Face tempface = (Face)this.Faces.elementAt(i);
                    this.Faces.setElementAt((Face)this.Faces.elementAt(j), i);
                    this.Faces.setElementAt(tempface, j);
                    ++i;
                    --j;
                }

                if (i > j) {
                    if (left < j) {
                        this.QuickSort(left, j);
                    }

                    if (i < right) {
                        this.QuickSort(i, right);
                    }

                    return;
                }
            }

            ++i;
        }
    }

    public synchronized void AddObject(String name, Object3D object) {
        this.Objects.put(name, object);

        for(int i = 0; i < object.FaceNum; ++i) {
            this.Faces.addElement(object.Faces[i]);
        }

    }

    private Object3D GetObject(String name) {
        return (Object3D)this.Objects.get(name);
    }

    public synchronized void DelObject(String name) {
        Object3D object = (Object3D)this.Objects.remove(name);

        for(int i = 0; i < object.FaceNum; ++i) {
            this.Faces.removeElement(object.Faces[i]);
        }

    }

    public synchronized void ClearObject() {
        this.Objects.clear();
        this.Faces.removeAllElements();
    }

    public void YRotateView(double theta) {
        this.ViewMat.ProYRotate(theta);
    }

    public void ZRotateView(double theta) {
        this.ViewMat.ProZRotate(theta);
    }

    protected void Sort() {
        for(int i = 0; i < this.Faces.size(); ++i) {
            Face face = (Face)this.Faces.elementAt(i);
            face.CalcParam(this.ViewMat);
        }

        this.QuickSort(0, this.Faces.size() - 1);
    }

    public void Transform(String name) {
        this.GetObject(name).Transform();
    }

    public void Transform() {
        Enumeration elementsEnum = this.Objects.elements();

        while(elementsEnum.hasMoreElements()) {
            Object3D obj = (Object3D)elementsEnum.nextElement();
            obj.Transform();
        }

    }

    public Model3D() {
        this.ViewMat = null;
        this.Objects = new Hashtable();
        this.Faces = new Vector();
    }

    public Model3D(Vertex viewpoint) {
        this.Objects = new Hashtable();
        this.Faces = new Vector();
        this.SetView(viewpoint);
    }

    public Model3D(Matrix3D viewmat) {
        this.Objects = new Hashtable();
        this.Faces = new Vector();
        this.ViewMat = viewmat;
    }

    public void SetView(Vertex viewpoint) {
        float x = viewpoint.x;
        float y = viewpoint.y;
        float z = viewpoint.z;
        float ro = (float)Math.sqrt((double)(x * x + y * y + z * z));
        float sintheta = (float)((double)y / Math.sqrt((double)(x * x + y * y)));
        float costheta = (float)((double)x / Math.sqrt((double)(x * x + y * y)));
        float sinphi = (float)(Math.sqrt((double)(x * x + y * y)) / (double)ro);
        float cosphi = z / ro;
        float[][] transmat = new float[][]{{-sintheta, costheta, 0.0F, 0.0F}, {-costheta * cosphi, -sintheta * cosphi, sinphi, 0.0F}, {-costheta * sinphi, -sintheta * sinphi, -cosphi, 0.0F}, {0.0F, 0.0F, 0.0F, 1.0F}};
        this.ViewMat = new Matrix3D(transmat);
    }

    public void SetView(Matrix3D viewmat) {
        this.ViewMat = viewmat;
    }

    public void XRotate(String name, double theta) {
        this.GetObject(name).XRotate(theta);
    }

    public synchronized void Paint(Graphics graph, int width, int height) {
        if (!this.Faces.isEmpty()) {
            this.Sort();

            for(int i = 0; i < this.Faces.size(); ++i) {
                Face face = (Face)this.Faces.elementAt(i);
                face.Paint(graph, width, height);
            }

        }
    }

    public Matrix3D GetView() {
        return this.ViewMat;
    }
}
