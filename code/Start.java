class Start {
    public static void main(String[] data) {
        int[] wood = { 1, 3, 4, 5 };
        System.out.println(minCost(7, wood));
    }
    
    public static int minCost(int n, int[] cuts) {        
       // first, we have to sort  
        java.util.Arrays.sort(cuts);
        int[] points = new int[cuts.length + 2];
        points[0] = 0; points[points.length - 1] = n;
        for(int i = 0; i < cuts.length; i ++)
            points[i+1] = cuts[i];
        return go(points, 0, points.length - 1);
    }
    
    static int go(int[] points, int start, int finish) {
        if (start >= finish) return Integer.MAX_VALUE;
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
        return best;
    }
}