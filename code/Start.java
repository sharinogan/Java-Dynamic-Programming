class Start {
    
    public static void main(String[] data) {
        int[] cuts = { 1, 3, 4, 5 };
        int wood_size = 7;
        
        Wood w = new Wood();
        System.out.println(w.minCost(wood_size, cuts));
    }
    
}

class Wood {
    
    int[][] matrix;
    public int minCost(int n, int[] cuts) {        
       // first, we have to sort  
        java.util.Arrays.sort(cuts);
        int[] points = new int[cuts.length + 2];
        points[0] = 0; points[points.length - 1] = n;
        for(int i = 0; i < cuts.length; i ++)
            points[i+1] = cuts[i];
        matrix = new int[points.length][points.length];
        for(int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix.length; j++)
                matrix[i][j] = Integer.MAX_VALUE;
        return go(points, 0, points.length - 1);
    }
    
    int go(int[] points, int start, int finish) {
       if (start >= finish) return Integer.MAX_VALUE;
        if (matrix[start][finish] != Integer.MAX_VALUE)
            return matrix[start][finish]; // use previous value
        
        int best = Integer.MAX_VALUE;  // about 2 billion
        // try to cuts every point from (start, finish)
        for(int i = start + 1; i < finish; i++) {
            int cost = points[finish] - points[start]; // cost of cutting
            int left = go(points, start, i);
            int right = go(points, i, finish);
            int total = cost + left + right;
            if (total < best) best = total;
        }
        if (best == Integer.MAX_VALUE) best = 0;
        matrix[start][finish] = best;
        return best;
    }
}