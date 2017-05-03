package matrizmelhorada;

/**
 * Created by cristian on 23/04/17.
 */
public class ElemMat {

    private int i;
    private int j;
    private float elem;

    ElemMat(int i, int j, float pElem){
        this.i = i;
        this.j = j;
        this.elem = pElem;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public float getElem(int i, int j) {
        return elem;
    }

    public void setElem(int i, int j, float elem) {
        this.elem = elem;
    }

    public boolean equals(ElemMat pElemMat){
        return this.elem == pElemMat.elem;
    }

}
