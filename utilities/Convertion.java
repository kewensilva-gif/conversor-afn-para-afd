package utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Convertion {
    List<NewState> listNewStates = new ArrayList<>();
    private int y = 90;
    private int x = 80;

    public List<NewState> getListNewStates() {
        return listNewStates;
    }

    public void setListNewStates(NewState newState) {
        this.listNewStates.add(newState);
    } 

    // Essa função é responsável por criar o estado inicial para o método prático
    public NewState createInitialState(State stateInitial, List<State> listStates, String label) {
        NewState newState = new NewState(0, "q0", true, null, 80, 90, label, null);
        newState.setListState(stateInitial);
        this.getStatesLambda(stateInitial, listStates, newState);
        newState.setIsFinal(this.verifyIsFinal(newState.getListState()));

        return newState;
    }


    // Essa função é responsável verificar todos os estados alcançado através do lambda
    public void getStatesLambda(State state, List<State> listStates, NewState newState) {
        for (Transition trans : state.getList()) {
            if(trans.getRead().equals("")){

                State stateTo = findState(listStates, trans.getTo());

                if(stateTo != null && this.verifyEqualsStates(newState, stateTo)) {
                    String letters = newState.getLabel() + stateTo.getId();
                    newState.setLabel(this.ordenationLabel(letters));
                    newState.setListState(stateTo);
                    
                    this.getStatesLambda(stateTo, listStates, newState);
                }
            }
        }
    }

    // Essa função verifica se o estado já foi adicionado a label
    public Boolean verifyEqualsStates(NewState newState, State state) {
       
        if(newState.getLabel().indexOf(String.valueOf(state.getId())) == -1){
            return true;
        }

        return false;
    }

    // Essa função é responsável por encontrar o estado equivalente a transição to
    public State findState(List<State> listStates, int trans) {
        for (State state : listStates) {
           
            if (state.getId() == trans) {
                return state;
            }
        }
        return null;
    }

    public void praticalMethod(NewState stateInitial, Set<String> alphabet, List<State> listStates,List<NewState> listNewStates, int cont) {
        listNewStates.add(stateInitial);

        for (String letter : alphabet) {
            NewState stateTemp = this.createNewState(stateInitial, listStates, cont, letter);
            if(stateTemp != null){
                // verifica se já está na lista
                // System.out.println("new");
                // System.out.println("state");
            
                if (!insertTransitionOrNewState(listNewStates, stateTemp, letter, stateInitial)) {
                    if(stateInitial.getList() != null){
                        stateInitial.setList(new Transition(stateInitial.getId(), stateTemp.getId(), letter));
                    } else {    
                        stateInitial.setList(new ArrayList<>());
                        stateInitial.setList(new Transition(stateInitial.getId(), stateTemp.getId(), letter));
                    }
                    //listNewStates.add(stateTemp);
                    cont++;
                    praticalMethod(stateTemp, alphabet, listStates, listNewStates, cont);
                } 
            }
        }
    }


    // Essa função é responsável por verifica se há o estado na lista, caso haja ele deve ser inserido apenas nas transições
    public Boolean insertTransitionOrNewState(List<NewState> listNewStates, NewState state, String letter, NewState stateInicial) {
        for (NewState newState : listNewStates) {
            if(newState.getLabel().equals(state.getLabel())) {
                // insere a transição
                if(stateInicial.getList() != null){
                    stateInicial.setList(new Transition(stateInicial.getId(), newState.getId(), letter));
                } else {
                    stateInicial.setList(new ArrayList<>());
                    stateInicial.setList(new Transition(stateInicial.getId(), newState.getId(), letter));
                }
                return true;
            } 
        }
        return false;
    }

    public String ordenationLabel(String letters) {
        String[] numbers = letters.split("");
        Arrays.sort(numbers);
        String lettersOrd = String.join("", numbers);

        return lettersOrd;
    }

    public NewState createNewState(NewState stateInitial, List<State> listStates, int cont, String letter) {
        NewState newState = new NewState(0, null, null, null, 0, 0, "", null);
        for (State state : stateInitial.getListState()) {
            // System.out.println("\nState: "+state.getName());
            for (Transition trans : state.getList()) {
                if(trans.getRead().equals(letter)){
                    // System.out.print("\ngetFrom: "+trans.getFrom());
                    // System.out.print("\tgetread: "+trans.getRead());
                    // System.out.print("\tgetTo: "+trans.getTo());
                    State stateTo = this.findState(listStates, trans.getTo());
                    this.getStatesLambda(stateTo, listStates, newState);
                    if(this.verifyEqualsStates(newState, stateTo)){
                        String letters = newState.getLabel()+stateTo.getId();
                        if (this.verifyEqualsStates(newState, stateTo)) {
                            newState.setListState(stateTo);
                        }
                        newState.setLabel(this.ordenationLabel(letters));
                        newState.setName("q"+cont);
                        newState.setId(cont);
                        newState.setIsInitial(false);
                        newState.setIsFinal(this.verifyIsFinal(newState.getListState()));
                        if(cont%3 == 0) {
                            newState.setX(80);
                            
                            newState.setY(y+=200);
                        } else {
                            newState.setX(x+=50);
                            newState.setY(y+=90);
                            y-=100;
                        }
                    }
          
                }
            }
        }
        
        if(newState.getLabel().equals("")){
            return null;
        }

        return newState;
    }

    public Boolean verifyIsFinal(List<State> states) {
        for (State state : states) {
            if(state.getIsFinal()){
                return true;
            }
        }

        return false;
    }
}
