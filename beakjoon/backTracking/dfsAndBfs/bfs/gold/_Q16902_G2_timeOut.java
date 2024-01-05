package beakjoon.backTracking.dfsAndBfs.bfs.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _Q16902_G2_timeOut {
    private static class Node {
        int x;
        int y;
        int s;

        public Node(int x, int y, int s) {
            this.x = x;
            this.y = y;
            this.s = s;
        }
    }

    private static final char EMPTY = '.';
    private static final char WALL = '#';
    private static int N, M, P;
    private static char[][] map;
    private static int[] offsets;
    private static ArrayList<Node>[] playerPositions;
    private static int[] counts;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());
        map = new char[N][M];
        P = Integer.parseInt(stringTokenizer.nextToken());
        offsets = new int[P + 1];
        playerPositions = new ArrayList[P + 1];
        counts = new int[P + 1];
        for (int i = 0; i <= P; i++) {
            playerPositions[i] = new ArrayList<>();
        }
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 1; i <= P; i++) {
            offsets[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        for (int i = 0; i < N; i++) {
            String row = bufferedReader.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = row.charAt(j);
                if (Character.isDigit(map[i][j])) {
                    playerPositions[Character.getNumericValue(map[i][j])].add(new Node(i, j, 0));
                }
            }
        }

        runGame();

        StringBuilder stringBuilder = new StringBuilder();
        for (int player = 1; player <= P; player++) {
            stringBuilder.append(counts[player]).append(" ");
        }
        System.out.println(stringBuilder);
    }

    private static void runGame() {
        Queue<Integer> queue = new LinkedList<>();
        for (int player = 1; player <= P; player++) {
            queue.offer(player);
        }

        while (!queue.isEmpty()) {
            int player = queue.poll();
            int cnt = takeATurn(player, offsets[player]);
            if (counts[player] == cnt) {
                continue;
            }
            counts[player] = cnt;
            queue.offer(player);
        }
    }

    private static int takeATurn(int player, int s) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        boolean[][] visited = new boolean[N][M];
        Queue<Node> queue = new LinkedList<>();
        int cnt = 0;
        for (Node playerPosition : playerPositions[player]) {
            queue.offer(playerPosition);
            visited[playerPosition.x][playerPosition.y] = true;
            cnt++;
        }
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (current.s >= s) {
                continue;
            }
            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                    continue;
                }

                if (!visited[nx][ny] && Character.getNumericValue(map[nx][ny]) == player) {
                    visited[nx][ny] = true;
                    queue.offer(new Node(nx, ny, current.s));
                    cnt++;
                    continue;
                }

                if (!visited[nx][ny] && map[nx][ny] == EMPTY) {
                    visited[nx][ny] = true;
                    map[nx][ny] = (char) (player + '0');
                    cnt++;
                    queue.offer(new Node(nx, ny, current.s + 1));
                }
            }
        }

        return cnt;
    }
}

