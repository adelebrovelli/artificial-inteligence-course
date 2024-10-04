import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

public class Puzzle {
    //esta é a classe do jogo em si
    Random random = new Random();
    PuzzleNode initialState;
    PuzzleNode goalState;
    int l = 1; //limit

    public Puzzle(){
        int[][] goalStateM = {{1,2,3}, {4,5,6}, {7,8,0}}; //matriz
        goalState = new PuzzleNode(goalStateM); //colocando a matriz dentro do no

        int[][] initialStateM = new int[3][3]; //{{0,0,0}, {0,0,0}, {0,0,0}};
        initialState = new PuzzleNode(initialStateM);
    }
    
    public void randomizeInitialState(){ //aqui deixando o jogo embaralhadinho pra comecar
        for (int i = 0; i < 3; i++) { 
            for (int j = 0; j < 3; j++) {
                initialState.getState()[i][j] = goalState.getState()[i][j]; //agora initial tá igual ao objetivo
            } 
        }
        
        //eu quero que embaralhe 10 vezes os numeros e seja aleatorio
        for(int i = 0;i<10;i++) {

            int i1 = random.nextInt(3);
            int j1 = random.nextInt(3);
            int i2 = random.nextInt(3);
            int j2 = random.nextInt(3);
            
            initialState = troca(i1, j1, i2, j2, initialState);
        
        }
       }

       public PuzzleNode troca(int i1, int j1, int i2, int j2, PuzzleNode actualState) {
        int[][] stateCopy = new int[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    stateCopy[i][j] = actualState.getState()[i][j];
                }
            }
        
            int keepVar = stateCopy[i1][j1];
            stateCopy[i1][j1] = stateCopy[i2][j2];
            stateCopy[i2][j2] = keepVar;

        return new PuzzleNode(stateCopy);
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
    
        return cont % 2 == 0; //vai retornar true se for par
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

       public PuzzleNode IDS (PuzzleNode actualState, int l){
        Stack<PuzzleNode> frontier = new Stack<PuzzleNode>();
        Set<PuzzleNode> visitedStates = new HashSet<>();
        frontier.push(actualState); //coloco o que chega na pilha

        while(!frontier.isEmpty()){ //enquanto eu n esvazio a fronteira
            PuzzleNode NoTopoPilha = frontier.pop();
            System.out.println("Expandindo o nó com profundidade: " + NoTopoPilha.getDepth());
            printState(NoTopoPilha.getState());

            if (NoTopoPilha.equals(goalState)){
                return NoTopoPilha; //achou o nó que tava no topo da pilha, aí quebra o while
                } 

                if(NoTopoPilha.getDepth()<l){ //se o no atual tiver na produndidade  menor do que o limite, aí pode expandir
                    List<PuzzleNode> possibilidadesJogo = acoesPossiveis(NoTopoPilha);

                    for(PuzzleNode possibilidade : possibilidadesJogo){
                        if (!visitedStates.contains(possibilidade)) {
                        possibilidade.setDepth(NoTopoPilha.getDepth()+1); //pega a ultima altura e adiciona +1 ja que temos fronteira nova
                        frontier.push(possibilidade); //cada uma das possibilidades vao entrar na fronteira
                        visitedStates.add(possibilidade); //aqui vai marcar os estados visitados
                    }
                }

            }
        }
        return null; //buscou, colocou tudo na fronteira, buscou mais e ainda assim nao achou
       }

       public List<PuzzleNode> acoesPossiveis(PuzzleNode actualState) {
    List<PuzzleNode> vetorDePossibilidades = new ArrayList<>();
    
    // Verificando a posição do zero e gerando as possíveis ações
    if (actualState.getState()[0][0] == 0) { // canto superior esquerdo
        vetorDePossibilidades.add(troca(0, 0, 0, 1, actualState)); // direita
        vetorDePossibilidades.add(troca(0, 0, 1, 0, actualState)); // baixo
    } else if (actualState.getState()[0][2] == 0) { // canto superior direito
        vetorDePossibilidades.add(troca(0, 2, 0, 1, actualState)); // esquerda
        vetorDePossibilidades.add(troca(0, 2, 1, 2, actualState)); // baixo
    } else if (actualState.getState()[2][0] == 0) { // canto inferior esquerdo
        vetorDePossibilidades.add(troca(2, 0, 1, 0, actualState)); // cima
        vetorDePossibilidades.add(troca(2, 0, 2, 1, actualState)); // direita
    } else if (actualState.getState()[2][2] == 0) { // canto inferior direito
        vetorDePossibilidades.add(troca(2, 2, 1, 2, actualState)); // cima
        vetorDePossibilidades.add(troca(2, 2, 2, 1, actualState)); // esquerda
    } else if (actualState.getState()[0][1] == 0) { // meio superior
        vetorDePossibilidades.add(troca(0, 1, 0, 0, actualState)); // esquerda
        vetorDePossibilidades.add(troca(0, 1, 1, 1, actualState)); // baixo
        vetorDePossibilidades.add(troca(0, 1, 0, 2, actualState)); // direita
    } else if (actualState.getState()[1][0] == 0) { // meio esquerdo
        vetorDePossibilidades.add(troca(1, 0, 0, 0, actualState)); // cima
        vetorDePossibilidades.add(troca(1, 0, 1, 1, actualState)); // direita
        vetorDePossibilidades.add(troca(1, 0, 2, 0, actualState)); // baixo
    } else if (actualState.getState()[1][2] == 0) { // meio direito
        vetorDePossibilidades.add(troca(1, 2, 0, 2, actualState)); // cima
        vetorDePossibilidades.add(troca(1, 2, 1, 1, actualState)); // esquerda
        vetorDePossibilidades.add(troca(1, 2, 2, 2, actualState)); // baixo
    } else if (actualState.getState()[2][1] == 0) { // meio inferior
        vetorDePossibilidades.add(troca(2, 1, 2, 0, actualState)); // esquerda
        vetorDePossibilidades.add(troca(2, 1, 1, 1, actualState)); // cima
        vetorDePossibilidades.add(troca(2, 1, 2, 2, actualState)); // direita
    } else if (actualState.getState()[1][1] == 0) { // centro
        vetorDePossibilidades.add(troca(1, 1, 0, 1, actualState)); // cima
        vetorDePossibilidades.add(troca(1, 1, 1, 0, actualState)); // esquerda
        vetorDePossibilidades.add(troca(1, 1, 2, 1, actualState)); // baixo
        vetorDePossibilidades.add(troca(1, 1, 1, 2, actualState)); // direita
    }
    return vetorDePossibilidades; // retorna a lista de possibilidades
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

            try { //esperar antes de correr pra solução
                Thread.sleep(3000);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
            
            System.out.println("\nBuscando solução...");
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
