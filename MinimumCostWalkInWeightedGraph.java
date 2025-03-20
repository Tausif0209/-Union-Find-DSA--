class Solution {
    public int[] minimumCost(int n, int[][] edges, int[][] query) {
        int[] parents=new int[n];
        int[] size=new int[n];
        int[] cost=new int[n];
        for(int i=0;i<n;i++){
            parents[i]=i;
            size[i]=1;
            cost[i]=-1;
        }
        for(int[] edge:edges){
          unionFind(edge[0],edge[1],edge[2],parents,cost,size);
        }
        int[] ans=new int[query.length];
        for(int i=0;i<query.length;i++)
        {   int[] q=query[i];
            int p1=findParent(q[0],parents);
            int p2=findParent(q[1],parents);
            ans[i]=(p1==p2)?cost[p1]:-1;
        }
        return ans;
    }
    void unionFind(int i,int j,int wt,int[] parents,int[] cost,int[] size){
       int parent_i=findParent(i,parents);
       int parent_j=findParent(j,parents);
       if(parent_i==parent_j) {
        cost[parent_i]&=wt;
        return;
       }
       if(size[parent_i]>size[parent_j]){
        parents[parent_j]=parents[parent_i];
        size[parent_i]+=size[parent_j];
        if(cost[parent_j]!=-1) wt&=cost[parent_j];
        cost[parent_i]&=wt;
       }
       else{
        parents[parent_i]=parents[parent_j];
        size[parent_j]+=size[parent_i];
        if(cost[parent_j]==-1) cost[parent_j]=wt;
        else if(cost[parent_i]==-1) cost[parent_j]&=wt;
        else cost[parent_j]&=(wt&cost[parent_i]);
       }
    }
    int findParent(int node,int[] parents){
        if(parents[node]==node) return node;
        parents[node]=findParent(parents[node],parents);
        return parents[node];
    }
}