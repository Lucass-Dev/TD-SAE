ppackage fr.montreuil.iut.Lucas_Adrien_Imman.modele.Deplacement;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Deplacement.ModeDeplacement;

import java.util.ArrayDeque;
import java.util.Queue;

public class BFS implements ModeDeplacement {

    private int[][] distances;

    public BFS(int mapHeight, int mapWidth) {
        distances = new int[mapHeight][mapWidth];
    }

    public void calculerChemin(int destX, int destY, int[][] map) {
        boolean[][] visited = new boolean[map.length][map[0].length];

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(destX);
        queue.offer(destY);
        visited[destY][destX] = true;

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                distances[i][j] = Integer.MAX_VALUE; // Initialisation des distances
            }
        }

        distances[destY][destX] = 0;

        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};

        while (!queue.isEmpty()) {
            int x = queue.poll();
            int y = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (isValidMove(nx, ny, map) && !visited[ny][nx]) {
                    queue.offer(nx);
                    queue.offer(ny);
                    visited[ny][nx] = true;
                    distances[ny][nx] = distances[y][x] + 1;
                }
            }
        }
    }

    public void deplacerSoldat(Deplacable d, int[][] map) {
        int startX = (int) (d.getX0Value() / 32); // Assuming the tile size is 32x32
        int startY = (int) (d.getY0Value() / 32);

        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};

        int nextX = startX;
        int nextY = startY;
        int minDistance = Integer.MAX_VALUE;

        for (int i = 0; i < 4; i++) {
            int nx = startX + dx[i];
            int ny = startY + dy[i];

            if (isValidMove(nx, ny, map) && distances[ny][nx] < minDistance) {
                nextX = nx;
                nextY = ny;
                minDistance = distances[ny][nx];
            }
        }

        d.setX0(nextX * 32);
        d.setY0(nextY * 32);
    }

    private boolean isValidMove(int x, int y, int[][] map) {
        return x >= 0 && x < map[0].length && y >= 0 && y < map.length && map[y][x] != 10;
    }
}

