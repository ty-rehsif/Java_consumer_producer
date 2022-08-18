//interface to houses methods to be overwritten
public interface Buffer {
     void put(String value) throws InterruptedException;
     String take() throws InterruptedException;
}
