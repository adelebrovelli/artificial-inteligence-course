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
public int hashCode() {
    return Arrays.deepHashCode(state);  // state = matriz 2d
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
