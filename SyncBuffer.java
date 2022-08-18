
import java.util.concurrent.ArrayBlockingQueue;

public class SyncBuffer implements Buffer {
    //creating Queue to hold the values
    private final ArrayBlockingQueue<String> dataPool;

    //constructor initializing the queue capacity
    public SyncBuffer(){
        dataPool = new ArrayBlockingQueue<String>(1);
    }

    /**
     * @param word
     * @throws InterruptedException
     */
    //overriding interface method where when executed a value is placed in the buffer
    @Override
    public void put(String word) throws InterruptedException {
        dataPool.put(word);
    }

    //overriding interface method where when executed a value is returned from the buffer
    @Override
    public String take() throws InterruptedException {
        return dataPool.take();
    }


}
