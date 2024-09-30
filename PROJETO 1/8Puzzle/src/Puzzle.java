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
        //eu quero que embaralhe 10 vezes os numeros
        for(int i = 0; i < 10;i++){
            int i1 = random.nextInt(3);
            int j1 = random.nextInt(3);
            int i2 = random.nextInt(3);
            int j2 = random.nextInt(3);
    
            int keepVar = initialState.getState()[i1][j1];
            initialState.getState()[i1][j1] = initialState.getState()[i2][j2];
            initialState.getState()[i2][j2] = keepVar;
        }
       }

       public PuzzleNode evokeIDS (PuzzleNode actualState){
        while (actualState.depth>l) {
            actualState = actualState.getPointerNode();
           // if (actualState.getState().equals(goalState.getState()) == false){
            //    actualState.setDepth(+1);
            //    l++;
         //   }    
        }
        return actualState;
       }

       public PuzzleNode IDS (PuzzleNode actualState, int l){
        Stack<PuzzleNode> frontier = new Stack<PuzzleNode>();
        frontier.push(actualState); //coloco o que chega na pilha
        
        while(!frontier.isEmpty()){ //enquanto eu n esvazio a fronteira
            PuzzleNode NoTopoPilha = frontier.pop();

            if (NoTopoPilha.equals(goalState) ){
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

       public PuzzleNode[] acoesPossiveis(PuzzleNode actualState){
        //aqui ele vai pegar o no atual, analisar qual a posicao que o 0 se encontra na matriz
        // e a partir disso, analisar se as possibilidades são de gerar 2,3 ou 4 filhos, e ai armazenar em um vetor e retornar ele
        //aí ainda tem, se a possibilidade for 2: ai ve os mais proximos do 2 sei la ou entao analisa
        // caso por caso pra dizer quais das acoes(cima baixo lado lado) pode ser
        return null;
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
            puzzle.printState(puzzle.initialState.getState());
        }
}
