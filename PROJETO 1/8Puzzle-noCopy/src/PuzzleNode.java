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

public PuzzleNode copy() {
    int[][] newState = new int[this.state.length][this.state[0].length];
    
    for (int i = 0; i < this.state.length; i++) {
        for (int j = 0; j < this.state[i].length; j++) {
            newState[i][j] = this.state[i][j];
        }
    }

    return new PuzzleNode(newState);
}

@Override
public int hashCode() {
    return Arrays.deepHashCode(state);  // state é a matriz no node
}

@Override
public boolean equals(Object obj) {
    if (this == obj) {
        return true; 
    }
    if (obj == null || getClass() != obj.getClass()) {
        return false; 
    }
    PuzzleNode other = (PuzzleNode) obj;
    return Arrays.deepEquals(this.state, other.state);
}


}
