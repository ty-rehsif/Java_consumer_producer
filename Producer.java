
import java.util.Scanner;
//class implementing runnable
public class Producer implements Runnable{
    //creating object to reference shared buffer
    private final Buffer syncCObj;

    /**
     * @param syncCObj
     */
    //constructor initializing this class buffer to the passed object's
    public Producer(Buffer syncCObj){
        this.syncCObj = syncCObj;
    }

    //run method that contains the classes execution codes
    @Override
    public void run() {
        //scanner variable for input values
        Scanner input = new Scanner(System.in);
        try{
            //dividing size by 2 so the program doesn't run n^2 times
            for (int i=0; i<(Driver.size/2); i++) {
                //inputting into buffer via loop
                String message = input.nextLine();
                syncCObj.put(message);
            }
            //exception to catch if the thread gets interrupted while sleeping or waiting
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }

}
