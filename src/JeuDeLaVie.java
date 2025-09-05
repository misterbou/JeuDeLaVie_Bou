
public class JeuDeLaVie {
    private static final int SIZE = 5;

    public static void main(String[] args) {
        // Nombre de générations (par défaut 5, ou passez un argument)
        int generations = 5;
        if (args.length > 0) {
            try {
                generations = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid argument, using default generations (5).");
            }
        }

        // Initial grid (blinker in center)
        int[][] grid = {
                {0,0,0,0,0},
                {0,0,0,0,0},
                {0,1,1,1,0},
                {0,0,0,0,0},
                {0,0,0,0,0}
        };


        System.out.println("Initial state:");
        printGrid(grid);

        for (int gen = 1; gen <= generations; gen++) {
            grid = nextGeneration(grid);
            System.out.println("Generation " + gen + ":");
            printGrid(grid);
        }
    }

    private static int[][] nextGeneration(int[][] grid) {
        int[][] newGrid = new int[SIZE][SIZE];

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                int aliveNeighbors = countNeighbors(grid, i, j);

                if (grid[i][j] == 1) {
                    newGrid[i][j] = (aliveNeighbors == 2 || aliveNeighbors == 3) ? 1 : 0;
                } else {
                    newGrid[i][j] = (aliveNeighbors == 3) ? 1 : 0;
                }
            }
        }
        return newGrid;
    }


    private static int countNeighbors(int[][] grid, int x, int y) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;
                int nx = x + i, ny = y + j;
                if (nx >= 0 && nx < SIZE && ny >= 0 && ny < SIZE) {
                    count += grid[nx][ny];
                }
            }
        }
        return count;
    }

    private static void printGrid(int[][] grid) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(grid[i][j] == 1 ? "*" : ".");
                if (j < SIZE-1) System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
