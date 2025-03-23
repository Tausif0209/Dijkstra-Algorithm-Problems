/*
class iPair {
    int first, second;

    iPair(int first, int second) {
        this.first = first;
        this.second = second;
    }
}
*/

// User function Template for Java
class Solution {
    // Function to find the shortest distance of all the vertices
    // from the source vertex src.
    ArrayList<Integer> dijkstra(ArrayList<ArrayList<iPair>> adj, int src) {
        int n=adj.size();
        int[] min=new int[n];
        boolean[] isVisited=new boolean[n];
        for(int i=0;i<n;i++) min[i]=(i==src)?0:Integer.MAX_VALUE;
        PriorityQueue<int[]> pq=new PriorityQueue<>((a,b)->a[1]-b[1]);
        pq.offer(new int[]{src,0});
        while(!pq.isEmpty()){
            int[] currNode=pq.poll();
            if(isVisited[currNode[0]]) continue;
            isVisited[currNode[0]]=true;
            for(iPair pair:adj.get(currNode[0])){
                if(currNode[1]+pair.second< min[pair.first]) min[pair.first]=currNode[1]+pair.second;
                pq.offer(new int[]{pair.first,min[pair.first]});
            }
        }
        ArrayList ans=new ArrayList<>();
        for(int el:min) ans.add(el);
        return ans;
    }
}