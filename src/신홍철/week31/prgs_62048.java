class Solution {
    public long getGcd(long a,long b){
        if(b == 0) return a;
        else return getGcd(b,a%b);
    }
    public void swap(int a,int b){
        int t = a;
        a = b;
        b = t;
    }
    public long solution(int w, int h) {
        long answer = 0;
        long gcd = 0;
        if(w<h) swap(w,h);
        
        gcd = getGcd(w,h);
        answer = (long)w*(long)h - w - h + gcd;
        return answer;
    }
}
