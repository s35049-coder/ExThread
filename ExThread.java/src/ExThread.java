public class ExThread extends Thread{
    long objCreationTime;
    long firstInstruction;

    public ExThread(){
        objCreationTime = System.nanoTime();
    }

    public void run(){

        firstInstruction = System.nanoTime();
    }

    public long getTime(){

        return firstInstruction - objCreationTime;
    }

}


