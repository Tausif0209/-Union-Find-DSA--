class Solution {
    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        int[] parents=new int[26];
        for(int i=0;i<26;i++) parents[i]=i;
        int len=s1.length();
        for(int i=0;i<len;i++) {
            char ch1=s1.charAt(i);
            char ch2=s2.charAt(i);
            if(ch1==ch2) continue;
            connect(ch1,ch2,parents);
        }
        StringBuilder ans=new StringBuilder();
        len=baseStr.length();
        for(int i=0;i<len;i++) ans.append((char)(findParent(baseStr.charAt(i)-'a',parents)+'a'));
        return ans.toString();
    }
    private void connect(char ch1,char ch2,int[] parents){
        int parent1=findParent(ch1-'a',parents);
        int parent2=findParent(ch2-'a',parents);
        if(parent1<parent2) parents[parent2]=parent1;
        else parents[parent1]=parent2;
    }
    private int findParent(int i,int[] parents){
      if(parents[i]==i) return i;
      parents[i]=findParent(parents[i],parents);
      return parents[i];
    }
}