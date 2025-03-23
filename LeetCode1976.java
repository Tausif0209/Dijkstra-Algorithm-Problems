class Solution {
    public int countPaths(int n, int[][] roads) {
    int[] countPath=new int[n];
    long[] dist=new long[n];
    boolean[] isVisited=new boolean[n];
    ArrayList<ArrayList<Integer>>[] edges =new ArrayList[n];
    ArrayList<Integer> list;
    for(int i=0;i<n;i++) {
        countPath[i]=(i==0)?1:0;
        dist[i]=(i==0)?0:Long.MAX_VALUE;
        edges[i] = new ArrayList<>();
    }
    for(int[] road:roads){
        list=new ArrayList<>();
        list.add(road[1]);
        list.add(road[2]);
        edges[road[0]].add(list);
        list=new ArrayList<>();
        list.add(road[0]);
        list.add(road[2]);
        edges[road[1]].add(list);
    }

    PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[1]));
    pq.offer(new long[]{0,0});
    while(!pq.isEmpty()){
        long[] currNode=pq.poll();
        int node=(int)currNode[0];
        if(isVisited[node]) continue;
        isVisited[node]=true;
        for(ArrayList<Integer> neighbor: edges[node]) {
            int dest = neighbor.get(0);
            int wt = neighbor.get(1);
            if (wt + currNode[1] < dist[dest]) {

                dist[dest] = wt + currNode[1];
                countPath[dest] = countPath[node];
            } else if (wt + currNode[1] == dist[dest]) countPath[dest] = (countPath[dest]+countPath[node])%((int)Math.pow(10,9)+7);
            pq.offer(new long[]{dest, dist[dest]});
        }
    }
    return countPath[n-1];
    }
}