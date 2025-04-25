import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

// Producer class
class ProducerSystem implements Runnable {
    private BlockingQueue<Integer> producerQueue;
    private int id;

    public ProducerSystem(BlockingQueue<Integer> queue, int id) {
        this.producerQueue = queue;
        this.id = id;
    }
    public void run() {
        int count = 0;
        try {
            while (true) {
                Thread.sleep(500);
                producerQueue.put(count);
                System.out.println("Producer : " + id + " produced : " + count);
                count++;
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

// Consumer class
class ConsumerSystem implements Runnable {
    private BlockingQueue<Integer> consumerQueue;
    private int id;

    public ConsumerSystem(BlockingQueue<Integer> queue, int id) {
        this.consumerQueue = queue;
        this.id = id;
    }
    public void run() {
        try {
            while (true) {
                int item = consumerQueue.take();
                System.out.println("Consumer : " + id + " consumed : " + item);
                Thread.sleep(800); 
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

// Producer Consumer System Main class
public class ProducerConsumerSystem {
    public static void main(String[] args) {
        BlockingQueue<Integer> mainClassQueue = new ArrayBlockingQueue<>(5);

        Thread producer1 = new Thread(new ProducerSystem(mainClassQueue, 1));
        Thread producer2 = new Thread(new ProducerSystem(mainClassQueue, 2));
        Thread consumer1 = new Thread(new ConsumerSystem(mainClassQueue, 1));
        Thread consumer2 = new Thread(new ConsumerSystem(mainClassQueue, 2));

        producer1.start();
        producer2.start();
        consumer1.start();
        consumer2.start();
    }
}
