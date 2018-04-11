package io.verticle.oss.fireboard.client;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FireboardClient posts {{@link Status}} messages to the Fireboard Service.
 *
 * Backported version from former Spring based implementation of FireboardClient. Low dependencies and backward compatible.
 *
 * Use the {{@link MessagePropertyHelper}} to create messages conveniently.
 *
 * Note that the following JDKs should be used to support the Letsencrypt CA out of the box.
 * Java 7 >= 7u111
 * Java 8 >= 8u101
 *
 * @author Jens Saade
 */
public class FireboardClient {

    protected final Logger logger = Logger.getLogger(this.getClass().getSimpleName());

    private static FireboardClient instance;

    private FireboardAPIAccessor fireboardAPIAccessor;

    Queue<Status> queue = new ConcurrentLinkedQueue<Status>();

    /**
     * private constructor
     */
    private FireboardClient(){

        FireboardAccessConfig c = new FireboardAccessConfig();

        fireboardAPIAccessor = new FireboardAPIAccessor(c);

        Thread queueWorker = new Thread(queueThread);
        queueWorker.start();
    }

    /**
     * static singleton
     * @return
     */
    private static FireboardClient getStaticInstance(){
        if (instance == null){
            instance = new FireboardClient();
        }

        return instance;
    }

    /**
     * post a {{@link Status}} message to fireboard
     * @param status
     */
    public static void post(Status status){
        getStaticInstance().queue.add(status);
    }

    /**
     * use lazy initialization if config is not provided via system properties
     * @param config
     */
    public static void lazyInit(FireboardAccessConfig config){
        getStaticInstance().fireboardAPIAccessor.lazyInit( config);
    }


    // use good old threads to stay compatible < JDK8
    Runnable queueThread = new Runnable() {

        Status status;
        public void run() {

            while(true) {

                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                    logger.log(Level.SEVERE, e.getMessage());
                }

                status = queue.peek();
                if (status != null){
                    try {
                        fireboardAPIAccessor.sendStatusEvent(status);
                        queue.poll();
                    } catch (Exception e){
                        logger.log(Level.SEVERE, e.getMessage());
                    }
                }
            }
        }
    };

}
