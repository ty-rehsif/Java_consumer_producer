/*
Tyrese Fisher
Assignment #3: Send and You Shall Receive
Program: ISS Honors Bachelors
Programmer

description:
   - alternates sender and receiver using threading
   - sends messages to database where it can updated, deleted and can insert more information in the table
 */
//inputting all the classes
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//driver class
public class Driver {
    //# of inputs
    static int size;

    /**
     * @param args
     * @throws InterruptedException
     */
//driver code
    public static void main(String[] args) throws InterruptedException {
        //creating buffer object to be shared by sender and receiver
        Buffer syncBuffer = new SyncBuffer();
        //creating pool of threads to be used
        ExecutorService executorService = Executors.newCachedThreadPool();
        //creating objects to consume and produce; vice versa
        Session object1 = new Session();
        Session object2 = new Session();
        //scanner object creation
        Scanner input = new Scanner(System.in);
        //user prompt for # of messages
        System.out.println("20 word limiter set in DB. Please avoid going over limit");
        System.out.println("# of messages to input");

        size = input.nextInt();
        //user prompt for messages that will be inputted into buffer from producer
        System.out.println("Enter messages");
        //object one producing, and object two consuming
        object1.produces(syncBuffer, executorService);
        object2.consumes(syncBuffer, executorService,1);
        //object two producing, and object one consuming
        object2.produces(syncBuffer, executorService);
        object1.consumes(syncBuffer, executorService,2);
        //shutting down executor service
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
    }
}
