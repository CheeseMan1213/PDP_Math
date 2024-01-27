//package feature.producer;
//
//import ch.qos.logback.classic.Logger;
//import com.james2ch9developer.pdp_math.comsumer.QuadraticFormulaConsumer;
//import org.junit.jupiter.api.Test;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
//import org.testcontainers.containers.KafkaContainer;
//import org.testcontainers.junit.jupiter.Container;
//import org.testcontainers.junit.jupiter.Testcontainers;
//import org.testcontainers.utility.DockerImageName;
//
//import java.util.concurrent.TimeUnit;
//
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
////@DirtiesContext
////@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
//@SpringBootTest
//@Testcontainers
//public class QuadraticFormulaProducerTest {
//  private final Logger logger = (Logger) LoggerFactory.getLogger(QuadraticFormulaProducerTest.class);
//
//  @Container
//  @ServiceConnection
//  private static final KafkaContainer KAFKA_CONTAINER = new KafkaContainer(DockerImageName
//    .parse("confluentinc/cp-kafka:latest"));
//
//  @Autowired
//  private QuadraticFormulaProducer producer;
//
//  @Autowired
//  private QuadraticFormulaConsumer consumer;
//
//  @Value("${spring.kafka.topic}")
//  private String topic;
//
//  @Test
//  public void producerQuadraticFormulaMessage() throws Exception {
//    logger.info("Sending quadratic formula arguments to the topic: " + topic);
//    // At the moment, this test is only test that the producers successfully produces, but it is
//    // not concerned with what would be considered producing the right thing.
//    String quadraticFormulaArguments = "1, 4, 3, john.doe@yahoo.com";
//
//    producer.send(topic, quadraticFormulaArguments);
//
//    boolean messageConsumed = consumer.getLatch()
//      .await(10, TimeUnit.SECONDS);
//    assertTrue(messageConsumed);
//    // assertThat(consumer.getMessage()[0], containsString(quadraticFormulaArguments));
//  }
//}
