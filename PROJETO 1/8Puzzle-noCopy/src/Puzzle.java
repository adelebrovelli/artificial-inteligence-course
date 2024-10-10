import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

public class Puzzle {
    Random random = new Random();
    PuzzleNode initialState;
    PuzzleNode goalState;
    int l = 1; 

    public Puzzle(){
        int[][] goalStateM = {{1,2,3}, {4,5,6}, {7,8,0}}; 
        goalState = new PuzzleNode(goalStateM); 

        int[][] initialStateM = new int[3][3]; ;
        initialState = new PuzzleNode(initialStateM);
    }
    
    public void randomizeInitialState(){ 
        for (int i = 0; i < 3; i++) { 
            for (int j = 0; j < 3; j++) {
                initialState.getState()[i][j] = goalState.getState()[i][j]; 
            } 
        }
        
        for(int i = 0;i<10;i++) {

            int i1 = random.nextInt(3);
            int j1 = random.nextInt(3);
            int i2 = random.nextInt(3);
            int j2 = random.nextInt(3);
            
            troca(i1, j1, i2, j2, initialState);
        
        }
       }

       public void troca(int i1, int j1, int i2, int j2, PuzzleNode actualState) {
        
            int keepVar = actualState.getState()[i1][j1];
            actualState.getState()[i1][j1] = actualState.getState()[i2][j2];
            actualState.getState()[i2][j2] = keepVar;

       }

       public boolean hasSolution() {
        int[] matrizLinear = new int[9]; 
        int iAux = 0;
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrizLinear[iAux] = initialState.getState()[i][j];
                iAux++;
            }
        }
    
        int cont = 0;
        for (int i = 0; i < matrizLinear.length; i++) {
            for (int j = i + 1; j < matrizLinear.length; j++) {
                if (matrizLinear[i] != 0 && matrizLinear[j] != 0 && matrizLinear[i] > matrizLinear[j]) {
                    cont++;
                }
            }
        }
    
        return cont % 2 == 0; 
    }

       public PuzzleNode evokeIDS (PuzzleNode actualState){
        while (true) { 
            PuzzleNode result = IDS(actualState, l);
    
            if (result != null) {
                return result; 
            }
            l++; 
             System.out.println("Aumentando o limite de profundidade para: " + l);

        }
       }

       public PuzzleNode IDS (PuzzleNode actualState, int l) {
        Stack<PuzzleNode> frontier = new Stack<>();
        Set<PuzzleNode> visitedStates = new HashSet<>(); 
        frontier.push(actualState); 
        visitedStates.add(actualState.copy()); 
    
        while(!frontier.isEmpty()) { 
            PuzzleNode NoTopoPilha = frontier.pop(); 
            System.out.println("Expandindo o nó com profundidade: " + NoTopoPilha.getDepth());
            printState(NoTopoPilha.getState());
    
            if (NoTopoPilha.equals(goalState)) {
                return NoTopoPilha; 
            } 
    
            if (NoTopoPilha.getDepth() < l + 1) { 
                List<PuzzleNode> possibilidadesJogo = acoesPossiveis(NoTopoPilha); 
    
                for (PuzzleNode possibilidade : possibilidadesJogo) {
                    if (!visitedStates.contains(possibilidade)) {
                        possibilidade.setDepth(NoTopoPilha.getDepth() + 1); 
                        frontier.push(possibilidade); 
                        visitedStates.add(possibilidade.copy());
                    }
                }
            }
        }
        return null; 
    }
    

       public List<PuzzleNode> acoesPossiveis(PuzzleNode actualState) {
        List<PuzzleNode> vetorDePossibilidades = new ArrayList<>();
        
        if (actualState.getState()[0][0] == 0) {
            trocaEDestroca(0, 0, 0, 1, actualState, vetorDePossibilidades);    
            trocaEDestroca(0, 0, 1, 0, actualState, vetorDePossibilidades);    
        }
        else if (actualState.getState()[0][2] == 0) {
            trocaEDestroca(0, 2, 0, 1, actualState, vetorDePossibilidades);    
            trocaEDestroca(0, 2, 1, 2, actualState, vetorDePossibilidades);    
        }
        else if (actualState.getState()[2][0] == 0) {
            trocaEDestroca(2, 0, 1, 0, actualState, vetorDePossibilidades);    
            trocaEDestroca(2, 0, 2, 1, actualState, vetorDePossibilidades);   
        }
        else if (actualState.getState()[2][2] == 0) {
            trocaEDestroca(2, 2, 1, 2, actualState, vetorDePossibilidades);   
            trocaEDestroca(2, 2, 2, 1, actualState, vetorDePossibilidades);   
        }
        else if (actualState.getState()[0][1] == 0) {
            trocaEDestroca(0, 1, 0, 0, actualState, vetorDePossibilidades);   
            trocaEDestroca(0, 1, 1, 1, actualState, vetorDePossibilidades);   
            trocaEDestroca(0, 1, 0, 2, actualState, vetorDePossibilidades);   
        }
        else if (actualState.getState()[1][0] == 0) {
            trocaEDestroca(1, 0, 0, 0, actualState, vetorDePossibilidades);   
            trocaEDestroca(1, 0, 1, 1, actualState, vetorDePossibilidades);   
            trocaEDestroca(1, 0, 2, 0, actualState, vetorDePossibilidades);   
        }
        else if (actualState.getState()[1][2] == 0) {
            trocaEDestroca(1, 2, 0, 2, actualState, vetorDePossibilidades);   
            trocaEDestroca(1, 2, 1, 1, actualState, vetorDePossibilidades);   
            trocaEDestroca(1, 2, 2, 2, actualState, vetorDePossibilidades);   
        }
        else if (actualState.getState()[2][1] == 0) {
            trocaEDestroca(2, 1, 2, 0, actualState, vetorDePossibilidades);   
            trocaEDestroca(2, 1, 1, 1, actualState, vetorDePossibilidades);   
            trocaEDestroca(2, 1, 2, 2, actualState, vetorDePossibilidades);   
        }
        else if (actualState.getState()[1][1] == 0) {
            trocaEDestroca(1, 1, 0, 1, actualState, vetorDePossibilidades);   
            trocaEDestroca(1, 1, 1, 0, actualState, vetorDePossibilidades);   
            trocaEDestroca(1, 1, 2, 1, actualState, vetorDePossibilidades);    
            trocaEDestroca(1, 1, 1, 2, actualState, vetorDePossibilidades);    
        }
    
        return vetorDePossibilidades;
    }
    
    private void trocaEDestroca(int x1, int y1, int x2, int y2, PuzzleNode state, List<PuzzleNode> vetor) {
        troca(x1, y1, x2, y2, state); 
        vetor.add(state.copy());      
        destroca(x1, y1, x2, y2, state); 
    }
    
    private void destroca(int x1, int y1, int x2, int y2, PuzzleNode state) {
        troca(x2, y2, x1, y1, state); 
    }
    
    
        public void printState(int[][] state) {
            for (int i = 0; i < state.length; i++) {
                for (int j = 0; j < state[i].length; j++) {
                    System.out.print(state[i][j] + " ");
                }
                System.out.println(); 
            }
        }
    
        public static void main(String[] args) throws Exception {
            Puzzle puzzle = new Puzzle();
            puzzle.randomizeInitialState();
            boolean ver = puzzle.hasSolution();

        if (ver) {
            System.out.println("Estado inicial embaralhado:");
            puzzle.printState(puzzle.initialState.getState());

            try {   
                Thread.sleep(3000);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
            
            System.out.println(" nBuscando solução...");
            PuzzleNode solvedNode = puzzle.evokeIDS(puzzle.initialState);

            if (solvedNode != null && solvedNode.equals(puzzle.goalState)) {
                System.out.println("\nSolução encontrada:");
                puzzle.printState(solvedNode.getState());
            } else {
                System.out.println("\nNão foi possível encontrar uma solução.");
            }

        } else {
            System.out.println("Desculpe, estado inicial não solucionável, reinicie.");
        }
        }
}
