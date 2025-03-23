package SlidingWindow.Advanced;

class Solution {
    public int numberOfSubstrings(String s) {
        int n = s.length();
        int[] prefix = new int[n];
        prefix[0] = s.charAt(0) == '1' ? 1 : 0;
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + ((s.charAt(i) == '1') ? 1 : 0);
        }
        int count = 0;
        for (int start = 0; start < n; start++) {
            for (int stop = start; stop < n; stop++) {
                int one = prefix[stop] - (start > 0 ? prefix[start - 1] : 0);
                int zero = (stop - start + 1) - one;
                if (one == zero * zero) {
                    // System.out.print(start + " => "+stop);
                    // System.out.print(" => exactly equal");
                    // System.out.println(" count increased by 1");

                    count++;
                } else if (one > zero * zero) {

                    // hamaare paas kuch extras ones hai
                    int extra = (int) Math.sqrt(one) - zero;
                    // ab iss range mai chaahe zero aaye ya fir one fark nahi padtaaa wo sab manjoor
                    // honge
                    // System.out.print(start + " => "+stop);
                    // System.out.print(" => extra =>"+extra);

                    // count += 1 + extra;
                    if (stop + extra < n) {
                        count = count + 1 + extra;
                        // System.out.print(" => count increased by "+(1+extra));
                    } else {
                        count = count + (n - stop);
                        // System.out.print(" => count increased by "+(n-stop));
                    }
                    stop = stop + extra;
                    // System.out.println(" => stop became : "+ (stop+1));
                } else {
                    // number of ones kam pad rahe hai
                    int req = zero * zero - one;
                    // System.out.print(start + " => "+stop);
                    // System.out.print(" => req => "+req);
                    stop = stop + req - 1; // ab agla agar sahi aadmi chaahiye toh kam se kam itne one toh chaahiye hi
                                           // honge
                    // System.out.println(" => stop became : "+(stop+1));
                }
            }
        }
        return count;
    }
}