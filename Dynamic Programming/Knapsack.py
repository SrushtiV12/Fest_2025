"""
How to use:
Usage (0/1 knapsack, default):
    python knapsack.py <<EOF
    4 7
    6 13
    4 8
    3 6
    5 12
    EOF
Output:
    mode: 0/1
    capacity: 7
    max_value: 14
    chosen_indices_0based: [1, 2]
    chosen_items (w,v): [(4, 8), (3, 6)]

Usage (unbounded knapsack):
    python knapsack.py --unbounded <<EOF
    3 11
    6 30
    3 14
    4 16
    EOF
"""
from __future__ import annotations
from dataclasses import dataclass
import argparse
from typing import List, Tuple

@dataclass
class Item:
    w: int
    v: int

def knapsack_01(items: List[Item], W: int) -> Tuple[int, List[int]]:
    """
    0/1 knapsack (O(n*W)) returning (max_value, chosen_indices).
    Uses DP table for easy reconstruction.
    """
    n = len(items)
    dp = [[0]*(W+1) for _ in range(n+1)]
    for i in range(1, n+1):
        wi, vi = items[i-1].w, items[i-1].v
        for w in range(W+1):
            dp[i][w] = dp[i-1][w]
            if wi <= w:
                dp[i][w] = max(dp[i][w], dp[i-1][w-wi] + vi)
    res_idx: List[int] = []
    w = W
    for i in range(n, 0, -1):
        if dp[i][w] != dp[i-1][w]:
            res_idx.append(i-1)
            w -= items[i-1].w
    res_idx.reverse()
    return dp[n][W], res_idx

def knapsack_unbounded(items: List[Item], W: int) -> Tuple[int, List[int]]:
    """
    Unbounded knapsack (O(n*W)) returning (max_value, chosen_indices with repeats).
    1D DP; reconstruct via parent pointers.
    """
    dp = [0]*(W+1)
    parent = [-1]*(W+1)
    prev_w = [-1]*(W+1)

    for i, it in enumerate(items):
        for w in range(it.w, W+1):
            cand = dp[w - it.w] + it.v
            if cand > dp[w]:
                dp[w] = cand
                parent[w] = i
                prev_w[w] = w - it.w
    res_idx: List[int] = []
    w = W
    if dp[w] == 0:
        best_w = max(range(W+1), key=lambda x: dp[x])
        w = best_w
    while w > 0 and parent[w] != -1:
        i = parent[w]
        res_idx.append(i)
        w = prev_w[w]
    res_idx.reverse()
    return dp[W], res_idx

def parse_stdin() -> Tuple[List[Item], int]:
    """
    Input format:
        n W
        w1 v1
        w2 v2
        ...
        wn vn
    """
    import sys
    data = [list(map(int, line.split())) for line in sys.stdin if line.strip()]
    if not data:
        raise SystemExit("No input provided.")
    n, W = data[0]
    rows = data[1:]
    if len(rows) != n:
        raise SystemExit(f"Expected {n} item lines, got {len(rows)}.")
    items = [Item(w, v) for (w, v) in rows]
    return items, W

def main():
    ap = argparse.ArgumentParser(description="Knapsack solver (0/1 and Unbounded).")
    ap.add_argument("--unbounded", action="store_true", help="Use Unbounded Knapsack instead of 0/1.")
    args = ap.parse_args()

    items, W = parse_stdin()
    if any(it.w < 0 or it.v < 0 for it in items) or W < 0:
        raise SystemExit("Weights/values/capacity must be non-negative integers.")

    if args.unbounded:
        maxv, idxs = knapsack_unbounded(items, W)
        mode = "unbounded"
    else:
        maxv, idxs = knapsack_01(items, W)
        mode = "0/1"

    chosen = [ (items[i].w, items[i].v) for i in idxs ]
    print(f"mode: {mode}")
    print(f"capacity: {W}")
    print(f"max_value: {maxv}")
    print(f"chosen_indices_0based: {idxs}")
    print(f"chosen_items (w,v): {chosen}")

if __name__ == "__main__":
    main()