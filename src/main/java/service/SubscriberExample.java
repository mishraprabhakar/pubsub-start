package service;

import com.google.cloud.pubsub.v1.AckReplyConsumer;
import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.cloud.pubsub.v1.Subscriber;
import com.google.pubsub.v1.PubsubMessage;

import org.json.simple.JSONObject;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class SubscriberExample {
    public static void subscriberExample(String subscriptionName){


        // Instantiate an asynchronous message receiver.
        MessageReceiver receiver = (PubsubMessage message, AckReplyConsumer consumer) -> {
            System.out.println("Data: "+message.getData().toStringUtf8());
            consumer.ack();
        };

        Subscriber subscriber = null;
        subscriber = Subscriber.newBuilder(subscriptionName, receiver).build();

        //Start the subsciber
        subscriber.startAsync().awaitRunning();
        System.out.printf("Listening the message on %s:\n", subscriptionName.toString());

        //Allow the subscriber to run for 30 seconds unless an unrecoverable error occurs
        try {
            subscriber.awaitTerminated(10, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            // Shut down the subscriber after 30s. Stop receiving messages.
            System.out.println();
            subscriber.stopAsync();
        }
    }
}
