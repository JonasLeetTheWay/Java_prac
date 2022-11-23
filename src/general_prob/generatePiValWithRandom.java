package general_prob;

public class generatePiValWithRandom {

    static double pointsInCircle = 0;
    static double gridNum = 100000000; // adjust precision

    public static void main(String[] args) {
        System.out.println(piCalculator(gridNum));
    }

    public static double piCalculator(double gridNum){
        for (int i = 0; i< gridNum; i++){

            double x = Math.random();
            double y = Math.random();

            double distance = Math.sqrt(x*x + y*y);

            if (distance < 1){
                pointsInCircle += 1;
            }
        }
        return 4 * pointsInCircle / gridNum;
    }
}
