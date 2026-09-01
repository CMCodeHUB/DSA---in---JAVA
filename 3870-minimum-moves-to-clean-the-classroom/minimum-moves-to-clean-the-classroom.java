class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        int sr = 0, sc = 0;
        int litterCount = 0;

        int[][] id = new int[m][n];
        for (int i = 0; i < m; i++) {
            java.util.Arrays.fill(id[i], -1);
            for (int j = 0; j < n; j++) {
                char c = classroom[i].charAt(j);

                if (c == 'S') {
                    sr = i;
                    sc = j;
                } else if (c == 'L') {
                    id[i][j] = litterCount++;
                }
            }
        }

        int allMask = (1 << litterCount) - 1;

        if (litterCount == 0) {
            return 0;
        }

        // State: row, column, collected litter mask, remaining energy
        // We use BFS because every move costs exactly 1.
        int maxStates = m * n * (1 << litterCount) * (energy + 1);

        boolean[][][][] visited =
                new boolean[m][n][1 << litterCount][energy + 1];

        java.util.ArrayDeque<int[]> queue = new java.util.ArrayDeque<>();

        queue.offer(new int[]{sr, sc, 0, energy});
        visited[sr][sc][0][energy] = true;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        int moves = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                int[] cur = queue.poll();

                int r = cur[0];
                int c = cur[1];
                int mask = cur[2];
                int e = cur[3];

                if (mask == allMask) {
                    return moves;
                }

                // If energy is 0, the student can only continue
                // when standing on an R cell.
                if (e == 0) {
                    if (classroom[r].charAt(c) == 'R') {
                        e = energy;
                    } else {
                        continue;
                    }
                }

                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                        continue;
                    }

                    if (classroom[nr].charAt(nc) == 'X') {
                        continue;
                    }

                    int newEnergy = e - 1;
                    int newMask = mask;

                    // Collect litter.
                    if (classroom[nr].charAt(nc) == 'L') {
                        int bit = id[nr][nc];
                        newMask |= (1 << bit);
                    }

                    // Reset energy immediately after entering R.
                    if (classroom[nr].charAt(nc) == 'R') {
                        newEnergy = energy;
                    }

                    if (!visited[nr][nc][newMask][newEnergy]) {
                        visited[nr][nc][newMask][newEnergy] = true;
                        queue.offer(new int[]{
                                nr, nc, newMask, newEnergy
                        });
                    }
                }
            }

            moves++;
        }

        return -1;
    }
}