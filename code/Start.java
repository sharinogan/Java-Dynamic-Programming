class Start {
    
    public static void main(String[] data) {
        String test = "eegiicgaeadbcfacfhifdbiehbgejcaeggcgbahfcajfhjjdgj";
        
        Solution s = new Solution();
        int r = s.minCut(test);
        System.out.println(r);
    }
    
}

class Solution {
    
    String test = "eegiicgaeadbcfacfhifdbiehbgejcaeggcgbahfcajfhjjdgj";
    
    public int minCut(String s) {
        char[] a = s.toCharArray();
        int[][] matrix = new int[a.length][a.length];
        for (int width = 0; width < a.length; width++) {
            for (int start = 0; start + width < a.length; start++) {
                int finish = start + width;
                if (start == width) { 
                    matrix[start][finish] = 0; 
                    continue;
                }
                if (check(a, start, finish)) {
                    matrix[start][finish] = 0;
                    continue;
                }
                int best = Integer.MAX_VALUE;
                for (int i = start; i < finish; i++) {
                    int total = matrix[start][i] + 1 + matrix[i+1][finish];
                    if (total < best) best = total;
                }
                if (best == Integer.MAX_VALUE) best = 0;
                matrix[start][finish] = best;
            }
        }
        return matrix[0][a.length - 1];
    }
    
    
    boolean check(char[] a, int head, int tail) {
        int left = head;
        int right = tail;
        while(left < right) {
            if(a[left] != a[right]) { 
                return false; 
            } 
            left++;
            right--;
        }
        return true;
    }
}