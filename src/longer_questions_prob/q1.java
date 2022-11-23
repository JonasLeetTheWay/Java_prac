package longer_questions_prob;

public class q1 {

    /*
    A candy shop sells M candy(ies) for 1 dollar. To promote their business,
    the shop also lets customers exchange P candy wrappers for Q new candy(ies).
    Suppose that Ahmad has N dollar.
     */
    static int func(int M, int N, int P, int Q) throws InterruptedException {
        int candies_for_1dollar = M;
        int dollar = N;
        int wrapper_count = 0;
        int wrapper_rate = P;
        int candy_exchange = Q;
        int candy_total = 0;

        if (dollar >= 0) {
            candy_total += dollar * candies_for_1dollar;
            wrapper_count += candy_total;
        }

        int wrapper_to_exchange = wrapper_count / wrapper_rate;
        while (wrapper_to_exchange != 0) {
            //System.out.println("wrapper count:\t"+wrapper_count);
            wrapper_to_exchange = wrapper_count / wrapper_rate;
            wrapper_count = wrapper_count % wrapper_rate;
            //System.out.println("to exchange:\t"+wrapper_to_exchange+", wrapper count:\t"+wrapper_count);
            int candy_exchanged = wrapper_to_exchange * candy_exchange;
            //System.out.println("exchanged:\t"+candy_exchanged);
            candy_total += candy_exchanged;
            wrapper_count += candy_exchanged;
            //System.out.println("candy total:\t"+candy_total+", wrapper count:\t"+wrapper_count);
            //System.out.println("--------------------");
        }
        System.out.println("........................");

        return candy_total;
    }

    static void run() throws InterruptedException {
        int[][] arrs = {{3,55,9,4},{2,100,5,1},{2,100,10,2},{2,1,3,1},{3,1,2,1},{1,30,5,2},{2,50,8,3},{3,100,10,9}};
        for (int[] arr : arrs) {
            System.out.println(q1.func(arr[0], arr[1], arr[2], arr[3]));
        }
    }

    public static void main(String[] args) throws InterruptedException  {
        q1.run();
    }
}
