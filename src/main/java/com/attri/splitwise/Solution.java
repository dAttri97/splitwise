package com.attri.splitwise;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

class Solution {

    public int longestSubsequence(String s, int k) {
        return 0;
    }

    public void backTrack(String s, String cc, int[] ans, int k) {
        if(Integer.valueOf(cc, 2)<=k) {
            ans[0] = Math.max(ans[0], cc.length());
            return;
        }
    }

    public List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        int right = 0;
        List<Integer> result = new ArrayList<>();
        for(int i=0;i<nums.length;i++) {
            if(nums[i] == key) {
                int left = Math.max(right, i - k);
                right = Math.min(nums.length - 1, i + k);
                for(int index = left; index <= right; index++) {
                    result.add(index);
                }
            }
        }
        return result;
    }

    public long kthSmallestProduct(int[] nums1, int[] nums2, long k) {
        PriorityQueue<Long> pq = new PriorityQueue<>(Collections.reverseOrder());
        int n = nums1.length, m = nums2.length;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                long product = nums1[i] * nums2[j];
                if(pq.size() < k) {
                    pq.offer(product);
                } else if(product < pq.peek()) {
                    pq.poll();
                    pq.offer(product);
                }
            }
        }
        return pq.peek();
    }

}