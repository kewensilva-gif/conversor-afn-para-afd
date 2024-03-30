package utilities;

import java.util.ArrayList;
import java.util.List;

public class NewState extends State {
    private List<State> listState = new ArrayList<>();


    public List<State> getListState() {
        return listState;
    }


    public void setListState(State state) {
        this.listState.add(state);
    }


    public NewState(int id, String name, Boolean isInitial, Boolean isFinal, float x, float y, String label,
            List<Transition> list) {
        super(id, name, isInitial, isFinal, x, y, label, list);
    }

   
}
