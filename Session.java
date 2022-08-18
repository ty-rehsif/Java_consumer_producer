import java.util.concurrent.ExecutorService;
//creating session class to handle producer and consumer threads
public class Session {
    /**
     * @param syncBuffer
     * @param executorService
     */
    public void produces(Buffer syncBuffer, ExecutorService executorService) {
        //producer object to be managed by executor service
        Producer producer = new Producer(syncBuffer);
        executorService.execute(producer);
    }

    /**
     * @param syncBuffer
     * @param executorService
     * @param id
     */
    public void consumes(Buffer syncBuffer, ExecutorService executorService, int id) {
        ////producer object to be managed by executor service
        Consumer consumer = new Consumer(syncBuffer, id);
        executorService.execute(consumer);
    }
}
