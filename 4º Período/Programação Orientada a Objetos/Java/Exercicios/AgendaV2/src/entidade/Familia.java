package entidade;

public class Familia extends Contato implements IFamiliar {
    private String parentesco;

    public Familia() {


    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

}
