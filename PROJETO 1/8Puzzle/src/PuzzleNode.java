import java.util.Arrays; 

public class PuzzleNode {
// esta classe seria equivalente aos nós do puzzle, logo, os estados.
int[][] state;
int depth;

public PuzzleNode(int[][] state) {
    this.state = state;
    this.depth = 1; // profundidade inicial como 1
}

public int[][] getState() {
    return state;
}

public void setState(int[][] state) {
    this.state = state;
}

public int getDepth() {
    return depth;
}

public void setDepth(int depth) {
    this.depth = depth;
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
