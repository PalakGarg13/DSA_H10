import java.util.*;

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> resultList = new ArrayList<>();
        backtrack(resultList, new ArrayList<>(), nums);
        return resultList;
    }
    private void backtrack(List<List<Integer>> resultList, ArrayList<Integer> tempList, int[] nums) {
        // Base case: permutation complete
        if (tempList.size() == nums.length) {
            resultList.add(new ArrayList<>(tempList));
            return;
        }
        // Try adding each number
        for (int number : nums) {
            if (tempList.contains(number)) {
                continue; // skip already used numbers
            }
            // choose
            tempList.add(number);
            // explore
            backtrack(resultList, tempList, nums);
            // unchoose (backtrack)
            tempList.remove(tempList.size() - 1);
        }
    }
}