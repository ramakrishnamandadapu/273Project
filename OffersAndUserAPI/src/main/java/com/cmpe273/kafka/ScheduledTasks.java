package com.cmpe273.kafka;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class ScheduledTasks {
    private static Producer producerThread = new Producer(KafkaProperties.topic);
   // private static Consumer consumerThread = new Consumer(KafkaProperties.topic);

    public ScheduledTasks(){
       producerThread.start();
      // consumerThread.start();
    }

    @Scheduled(fixedRate = 5000)
    public void reportExpiredPolls(){}
}
