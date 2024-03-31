import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;

import utilities.Arquivo;
import utilities.Convertion;
import utilities.NewState;
import utilities.State;
import utilities.Transition;

public class Automato {
    private Set<String> alphabet;

    public Set<String> getAlphabet() {
        return alphabet;
    }


    public static void main(String[] args) throws IOException {
        Arquivo arquivo = new Arquivo();
        Automato automato = new Automato();
        Convertion convertion = new Convertion();
        String caminho = arquivo.obterCaminho();
        
        if (caminho != null) {

            automato.setAlphabet();
            
            List<State> listStates = arquivo.listStates(arquivo.getDoc());
            State stateInitial = automato.retornaInicial(listStates);
            NewState newStateInicial = convertion.createInitialState(stateInitial, listStates, String.valueOf(stateInitial.getId()));
            List<NewState> listNewStates = new ArrayList<>();
            int cont = 1;
            convertion.praticalMethod(newStateInicial, automato.getAlphabet(), listStates, listNewStates, cont);
            arquivo.gravarAutomato(listNewStates);
            // for (NewState newState2 : listNewStates) {
            //     System.out.print("\nlabel: " + newState2.getLabel());
            //     System.out.print("\tid: " + newState2.getId());
            //     System.out.print("\tname: " + newState2.getName());
            //     System.out.print("\tisFinal: " + newState2.getIsFinal());
            
                
            //         for (State trans : newState2.getListState()) {
            //             System.out.print("\nid: "+trans.getId()+"\t");
            //             // System.out.print("\tgetread: "+trans.getRead());
            //             // System.out.print("\tgetTo: "+trans.getTo());
            //         }
                    
                
            //     // System.out.println("id" + newState2.getId());
            //     // System.out.println("name" + newState2.getName());
            // }
       
            //listStates.get(listStates.indexOf(automato.retornaInicial(listStates)));

            
        }
    }


    // Retorna o estado inicial
    public State retornaInicial(List<State> states) {
        for (State state : states) {
            if(state.getIsInitial()){
                return state;
            }
        }
        return null;
    }

    
  
    public void setAlphabet() {
        JOptionPane.showMessageDialog(null, "No próximo passo informe os símbolos do alfabeto.",
                "Informe o Sigma:", JOptionPane.INFORMATION_MESSAGE);
        Set<String> sigma = new HashSet<>();
        String simbolo;
        do {
            simbolo = JOptionPane.showInputDialog(null, "Informe um símbolo do alfabeto:", "Informe o Sigma:",
                    JOptionPane.PLAIN_MESSAGE);
            if (simbolo != null && !simbolo.isEmpty()) {
                sigma.add(simbolo);
            } else {
                if (simbolo == null) {
                    break;
                }
            }
        } while (true);
        this.alphabet = sigma;
    }
}