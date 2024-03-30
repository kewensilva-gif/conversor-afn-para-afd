package utilities;

import java.util.List;

public class State {
    private int id;
    private Integer idAntigo;
    private String name;
    private float x, y;
    private Boolean isInitial;
    private Boolean isFinal;
    private String label;

    private List<Transition> list;
    
   

    public List<Transition> getList() {
        return list;
    }

    public void setList(Transition transition) {
        this.list.add(transition);
    }

    public void setList(List<Transition> list) {
        this.list = list;
    }
    
    public void exibeLista() {
        for (Transition transition : this.list) {
            System.out.println("from" + transition.getFrom());
            System.out.println("to" + transition.getTo());
        }
    }
    public State(int id, String name, Boolean isInitial, Boolean isFinal, float x, float y, String label, List<Transition> list) {
        this.id = id;
        this.name = name;
        this.isInitial = isInitial;
        this.isFinal = isFinal;
        this.x = x;
        this.y = y;
        this.label = label;
        this.idAntigo = null;
        this.list = list;
    }

    public Integer getIdAntigo() {
        return idAntigo;
    }

    public void setIdAntigo(int idAntigo) {
        this.idAntigo = idAntigo;
    }
    
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Boolean getIsInitial() {
        return isInitial;
    }

    public void setIsInitial(Boolean isInitial) {
        this.isInitial = isInitial;
    }

    public Boolean getIsFinal() {
        return isFinal;
    }

    public void setIsFinal(Boolean isFinal) {
        this.isFinal = isFinal;
    }

}
