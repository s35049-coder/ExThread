public class RunTask implements Runnable{
    long objCreationTime;
    long firstInstruction;

    public RunTask(){

        objCreationTime = System.nanoTime();
    }

    public void run(){

        firstInstruction = System.nanoTime();
    }

    public long getTime(){

        return firstInstruction - objCreationTime;
    }
}
