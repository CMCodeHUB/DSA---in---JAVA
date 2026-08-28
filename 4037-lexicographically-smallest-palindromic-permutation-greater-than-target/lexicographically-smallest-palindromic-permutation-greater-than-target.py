class Solution(object):
    def lexPalindromicPermutation(self, s, target):
        """
        :type s: str
        :type target: str
        :rtype: str
        """

        n = len(s)

        # Count characters
        cnt = [0] * 26
        for ch in s:
            cnt[ord(ch) - ord('a')] += 1

        # Check if palindrome is possible
        middle = ""
        for i in range(26):
            if cnt[i] % 2:
                if middle:
                    return ""
                middle = chr(ord('a') + i)

        # Count characters for the first half
        half_cnt = [x // 2 for x in cnt]
        half_len = n // 2

        def build_pal(half):
            return half + middle + half[::-1]

        # Try to build the first half equal to target's first half
        target_half = target[:half_len]

        half = []
        remaining = half_cnt[:]

        for i in range(half_len):
            c = ord(target_half[i]) - ord('a')

            if remaining[c] == 0:
                break

            half.append(target_half[i])
            remaining[c] -= 1

        else:
            # target's first half can be formed exactly
            candidate = build_pal(''.join(half))

            # It may already be strictly greater because of
            # the middle character or the remaining mirrored part.
            if candidate > target:
                return candidate

            # Otherwise find the next greater first half.
            for pos in range(half_len - 1, -1, -1):

                # Return the character used at this position
                old = ord(half[pos]) - ord('a')
                remaining[old] += 1

                # Try the smallest character greater than target[pos]
                for c in range(old + 1, 26):
                    if remaining[c] > 0:
                        half[pos] = chr(ord('a') + c)
                        remaining[c] -= 1

                        # Fill the suffix with smallest characters
                        suffix = []

                        for x in range(26):
                            suffix.extend(
                                [chr(ord('a') + x)] * remaining[x]
                            )

                        result_half = (
                            ''.join(half[:pos + 1]) +
                            ''.join(suffix)
                        )

                        return build_pal(result_half)

                half.pop()

            return ""

        # We could not match target_half completely.
        # The current position is the first unmatched position.
        pos = len(half)

        # Backtrack to find the first position that can be increased.
        for p in range(pos, -1, -1):

            if p < pos:
                old = ord(half[p]) - ord('a')
                remaining[old] += 1

            need = ord(target_half[p]) - ord('a')

            # Try the smallest available character > target[p]
            for c in range(need + 1, 26):
                if remaining[c] > 0:

                    new_half = half[:p]
                    new_half.append(chr(ord('a') + c))
                    remaining[c] -= 1

                    # Fill the rest in sorted order
                    for x in range(26):
                        new_half.extend(
                            [chr(ord('a') + x)] * remaining[x]
                        )

                    return build_pal(''.join(new_half))

            if p < pos:
                half.pop()

        return ""