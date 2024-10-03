import java.util.Random;
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
            int keepVar = actualState.getState()[i1][j1];
            actualState.getState()[i1][j1] = actualState.getState()[i2][j2];
            actualState.getState()[i2][j2] = keepVar;
        return actualState;
       }

       public boolean verification() {
        int[] matrizLinear = new int[9]; // Linearizamos a matriz para um array de tamanho 9
        int iAux = 0;
        
        // Linearizar a matriz (de 3x3 para 1x9)
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrizLinear[iAux] = initialState.getState()[i][j];
                iAux++;
            }
        }
    
        // Contar inversões
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
        while (actualState.depth<l) { 
            PuzzleNode result = IDS(actualState, l);
    
            if (result != null) {
                return result; 
            }
            l++; 
        }
        return actualState;
       }

       public PuzzleNode IDS (PuzzleNode actualState, int l){
        Stack<PuzzleNode> frontier = new Stack<PuzzleNode>();
        frontier.push(actualState); //coloco o que chega na pilha
        
        while(!frontier.isEmpty()){ //enquanto eu n esvazio a fronteira
            PuzzleNode NoTopoPilha = frontier.pop();

            if (NoTopoPilha.equals(goalState)){
                return NoTopoPilha; //achou o nó que tava no topo da pilha, aí quebra o while
                } 

                if(NoTopoPilha.getDepth()<l){ //se o no atual tiver na produndidade  menor do que o limite, aí pode expandir
                    PuzzleNode[] possibilidadesJogo = acoesPossiveis(NoTopoPilha);

                    for(PuzzleNode possibilidade : possibilidadesJogo){
                        possibilidade.setDepth(NoTopoPilha.getDepth()+1); //pega a ultima altura e adiciona +1 ja que temos fronteira nova
                        frontier.push(possibilidade); //cada uma das possibilidades vao entrar na fronteira
                    }

                }
        }
        return null; //buscou, colocou tudo na fronteira, buscou mais e ainda assim nao achou
       }

       public PuzzleNode[] acoesPossiveis(PuzzleNode actualState) {
        PuzzleNode[] vetorDePossibilidades = new PuzzleNode[2];
        PuzzleNode[] vetorDePossibilidades1 = new PuzzleNode[3];
        PuzzleNode[] vetorDePossibilidades2 = new PuzzleNode[4];
    
        if (actualState.getState()[0][0] == 0) { // canto superior esquerdo
            switch (0) {
                case 0: // canto superior esquerdo
                    vetorDePossibilidades[0] = troca(0, 0, 0, 1, actualState); // direita
                    vetorDePossibilidades[1] = troca(0, 0, 1, 0, actualState); // baixo
                    return vetorDePossibilidades;
            }
        } else if (actualState.getState()[0][2] == 0) { // canto superior direito
            switch (2) {
                case 2: // canto superior direito
                    vetorDePossibilidades[0] = troca(0, 2, 0, 1, actualState); // esquerda
                    vetorDePossibilidades[1] = troca(0, 2, 1, 2, actualState); // baixo
                    return vetorDePossibilidades;
            }
        } else if (actualState.getState()[2][0] == 0) { // canto inferior esquerdo
            switch (6) {
                case 6: // canto inferior esquerdo
                    vetorDePossibilidades[0] = troca(2, 0, 1, 0, actualState); // cima
                    vetorDePossibilidades[1] = troca(2, 0, 2, 1, actualState); // direita
                    return vetorDePossibilidades;
            }
        } else if (actualState.getState()[2][2] == 0) { // canto inferior direito
            switch (8) {
                case 8: // canto inferior direito
                    vetorDePossibilidades[0] = troca(2, 2, 1, 2, actualState); // cima
                    vetorDePossibilidades[1] = troca(2, 2, 2, 1, actualState); // esquerda
                    return vetorDePossibilidades;
            }
        } else if (actualState.getState()[0][1] == 0) { // meio superior
            switch (1) {
                case 1:
                    vetorDePossibilidades1[0] = troca(0, 1, 0, 0, actualState); // esquerda
                    vetorDePossibilidades1[1] = troca(0, 1, 1, 1, actualState); // baixo
                    vetorDePossibilidades1[2] = troca(0, 1, 0, 2, actualState); // direita
                    return vetorDePossibilidades1;
            }
        } else if (actualState.getState()[1][0] == 0) { // meio esquerdo
            switch (3) {
                case 3:
                    vetorDePossibilidades1[0] = troca(1, 0, 0, 0, actualState); // cima
                    vetorDePossibilidades1[1] = troca(1, 0, 1, 1, actualState); // direita
                    vetorDePossibilidades1[2] = troca(1, 0, 2, 0, actualState); // baixo
                    return vetorDePossibilidades1;
            }
        } else if (actualState.getState()[1][2] == 0) { // meio direito
            switch (5) {
                case 5:
                    vetorDePossibilidades1[0] = troca(1, 2, 0, 2, actualState); // cima
                    vetorDePossibilidades1[1] = troca(1, 2, 1, 1, actualState); // esquerda
                    vetorDePossibilidades1[2] = troca(1, 2, 2, 2, actualState); // baixo
                    return vetorDePossibilidades1;
            }
        } else if (actualState.getState()[2][1] == 0) { // meio inferior
            switch (7) {
                case 7:
                    vetorDePossibilidades1[0] = troca(2, 1, 2, 0, actualState); // esquerda
                    vetorDePossibilidades1[1] = troca(2, 1, 1, 1, actualState); // cima
                    vetorDePossibilidades1[2] = troca(2, 1, 2, 2, actualState); // direita
                    return vetorDePossibilidades1;
            }
        } else if (actualState.getState()[1][1] == 0) { // centro
            switch (4) {
                case 4:
                    vetorDePossibilidades2[0] = troca(1, 1, 0, 1, actualState); // cima
                    vetorDePossibilidades2[1] = troca(1, 1, 1, 0, actualState); // esquerda
                    vetorDePossibilidades2[2] = troca(1, 1, 2, 1, actualState); // baixo
                    vetorDePossibilidades2[3] = troca(1, 1, 1, 2, actualState); // direita
                    return vetorDePossibilidades2;
            }
        }
        return null; // caso nenhum zero seja encontrado
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
        boolean ver = puzzle.verification();

        if (ver) {
            System.out.println("Estado inicial embaralhado:");
            puzzle.printState(puzzle.initialState.getState());
            
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
