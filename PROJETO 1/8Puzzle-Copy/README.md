## the project
Implement two versions of the function for the 8-puzzle: one that copies and edits the data structure for the parent node s and one that modifies the parent state directly (undoing the modifications as needed). Write versions of iterative deepening depth-first search that use these functions and compare their performance.
(Available in https://aimacode.github.io/aima-exercises/search-exercises/)

This is the **copy** version.

## solution !
My teacher asked us to solve the 8-puzzle using Iterative-Deepening Search (IDS). I implemented it by defining an initial scrambled state and expanding nodes up to a certain depth limit, increasing the limit incrementally until the goal state is found. The program generates all possible moves from the current state, checks for the goal, and avoids revisiting states. I also ensured the puzzle starts in a solvable state, if it doesn't, its shows a message asking you to play again.

## glossary
randomizeInitialState(): this function generates a random initial configuration of the puzzle by starting from the goal state and performing a series of random swaps.

evokeIDS(): main driver for the IDS algorithm. it repeatedly calls the IDS() function, which performs the actual depth-limited search.

IDS(): It starts with the current state, expands possible moves, and checks if any of the resulting states match the goal. if none are found, it returns and tries with a higher depth.

acoesPossiveis(): this function generates the possible actions (or moves) based on the current position of the empty tile (0) in the puzzle. each possible new state is added to the list of future states to explore(vector and than, stack).

troca(): this is the swap function - swap two tiles in the matrix.

hasSolution(): checks if the current configuration of the puzzle is solvable by counting the number of inversions.

thank you! 
