import java.util.Arrays;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
public class Main {

    public static long measureExt() throws InterruptedException {
        ExThread thread = new ExThread();

        thread.start();
        thread.join();

        return thread.getTime();
    }

    public static long measureRun() throws InterruptedException{
        RunTask task = new RunTask();

        Thread thread = new Thread(task);

        thread.start();
        thread.join();

        return task.getTime();


    }
    public static void main(String[] args) throws InterruptedException, FileNotFoundException {

        final int WARMUP = 5000;
        final int N = 50000;
        long sum = 0;
        long sumRun = 0;

        for (int i = 0; i < WARMUP; i++) {

            measureExt();

            measureRun();

        }
        long[] extTimes = new long[N];
        long[] runTimes = new long[N];

        for (int i = 0; i < N; i++) {
            extTimes[i] = measureExt();
            runTimes[i] = measureRun();
        }



        for (int i = 0; i < extTimes.length; i++) {
            sum = extTimes[i] + sum;
        }
        double mean = (double) sum / extTimes.length;


        for (int i = 0; i < runTimes.length; i++) {
            sumRun = sumRun + runTimes[i];
        }

        double meanRun = (double) sumRun / runTimes.length;

        PrintWriter writer = new PrintWriter("results.csv");

        writer.println("ExtThread,Runnable");

        for (int i = 0; i < N; i++) {
            writer.println(extTimes[i] + ";" + runTimes[i]);
        }

        writer.close();

        Arrays.sort(extTimes);
        double medianExt =
                (extTimes[N / 2 - 1] + extTimes[N / 2]) / 2.0;

        double medianRun =
                (runTimes[N / 2 - 1] + runTimes[N / 2]) / 2.0;

        System.out.println("N = " + N);

        System.out.println("ExtThread mean: " + mean + " ns");
        System.out.println("ExtThread P50: " + medianExt + " ns");

        System.out.println("Runnable mean: " + meanRun + " ns");
        System.out.println("Runnable P50: " + medianRun + " ns");
    }

}
