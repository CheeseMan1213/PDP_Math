package com.james2ch9developer.pdp_math.producer;


import com.james2ch9developer.pdp_math.comsumer.QuadraticFormulaConsumer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
public class QuadraticFormulaProducerTest {

  @Autowired
  private QuadraticFormulaProducer producer;

  @Autowired
  private QuadraticFormulaConsumer consumer;

  @Value("${spring.kafka.topic}")
  private String topic;

  @Test
  public void producerQuadraticFormulaMessage() throws Exception {
    // At the moment, this test is only test that the producers successfully produces, but it is
    // not concerned with what would be considered producing the right thing.
    String quadraticFormulaArguments = "1, 4, 3, john.doe@yahoo.com";

    producer.send(topic, quadraticFormulaArguments);

    boolean messageConsumed = consumer.getLatch()
      .await(10, TimeUnit.SECONDS);
    assertTrue(messageConsumed);
    // assertThat(consumer.getMessage()[0], containsString(quadraticFormulaArguments));
  }
}
