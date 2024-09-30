import java.util.Random;
import java.util.Arrays; 

public class PuzzleNode {
// esta classe seria equivalente aos nós do puzzle, logo, os estados.
int[][] state;
PuzzleNode pointerNode; //nó pai
int depth = 1;

public PuzzleNode(int[][] state) {
    this.state = state;
    this.pointerNode = pointerNode;
    this.depth = depth;
}

public int[][] getState() {
    return state;
}



public void setState(int[][] state) {
    this.state = state;
}



public PuzzleNode getPointerNode() {
    return pointerNode;
}



public void setPointerNode(PuzzleNode pointerNode) {
    this.pointerNode = pointerNode;
}



public int getDepth() {
    return depth;
}



public void setDepth(int depth) {
    this.depth = depth;
}



public void printState(int[][] state) {
    for (int i = 0; i < state.length; i++) {
        for (int j = 0; j < state[i].length; j++) {
            System.out.print(state[i][j] + " ");
        }
        System.out.println(); 
    }
}

@Override
public boolean equals(Object obj) {
    if (this == obj) {
        return true; // Se são o mesmo objeto
    }
    if (obj == null || getClass() != obj.getClass()) {
        return false; // Se o objeto é nulo ou de classe diferente
    }
    PuzzleNode other = (PuzzleNode) obj;
    // Comparar os arrays (uso de Arrays.deepEquals para comparar o conteúdo das matrizes)
    return Arrays.deepEquals(this.state, other.state);
}

   
}
