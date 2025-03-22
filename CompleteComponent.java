class Solution {
    int totalComponent=50;
    public int countCompleteComponents(int n, int[][] edges) {
        int[] parents=new int[n];
        int[] size=new int[n];
        for(int i=0;i<n;i++){
            parents[i]=i;
            size[i]=1;
        }
        int[] degree=new int[n];
        totalComponent=n;
        for(int[] edge:edges){
             degree[edge[0]]++;
             degree[edge[1]]++;
             unionFind(edge[0],edge[1],parents,size);
        }
        boolean[] ncc=new boolean[n];//ncc=not complete components
        for(int i=0;i<n;i++){
            int parent_i=findParent(i,parents);
            ncc[parent_i]= ncc[parent_i] || (size[parent_i]-1!=degree[i]);
        }
        for(boolean flag:ncc) 
            if(flag) totalComponent--;//subtracting ncc from total component
        return totalComponent;
    }
    void unionFind(int i,int j,int[] parents,int[] size){
         int parent_i=findParent(i,parents);
         int parent_j=findParent(j,parents);
         if(parent_i==parent_j) return;
         totalComponent--;
         if(size[parent_i]<size[parent_j]){
            parents[parent_i]=parents[parent_j];
            size[parent_j]+=size[parent_i];
         }
         else{
            parents[parent_j]=parents[parent_i];
            size[parent_i]+=size[parent_j];
         }
    }
    int findParent(int node,int[] parents){
        if(parents[node]==node) return node;
        parents[node]=findParent(parents[node],parents);
        return parents[node];
    }
}