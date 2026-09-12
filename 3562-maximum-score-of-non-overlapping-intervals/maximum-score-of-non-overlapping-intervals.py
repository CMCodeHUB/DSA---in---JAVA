class Solution(object):
    def maximumWeight(self, intervals):
        """
        :type intervals: List[List[int]]
        :rtype: List[int]
        """
        import bisect

        n = len(intervals)

        # right, left, weight, original index
        arr = []
        for i, (l, r, w) in enumerate(intervals):
            arr.append((r, l, w, i))

        arr.sort()

        ends = [x[0] for x in arr]

        # Previous non-overlapping interval
        prev = [-1] * n

        for i in range(n):
            l = arr[i][1]
            prev[i] = bisect.bisect_left(ends, l, 0, i) - 1

        # dp[k] = best state using at most k intervals
        # State = (score, tuple of indices)
        dp = [[(0, ()) for _ in range(n + 1)] for _ in range(5)]

        def better(a, b):
            if a[0] != b[0]:
                return a if a[0] > b[0] else b

            return a if a[1] < b[1] else b

        for k in range(1, 5):
            for i in range(1, n + 1):
                # Don't choose current interval
                best = dp[k][i - 1]

                r, l, w, idx = arr[i - 1]

                # Last compatible interval
                p = prev[i - 1] + 1

                old = dp[k - 1][p]

                # Choose current interval
                candidate_indices = tuple(sorted(old[1] + (idx,)))

                candidate = (
                    old[0] + w,
                    candidate_indices
                )

                dp[k][i] = better(best, candidate)

        return list(dp[4][n][1])