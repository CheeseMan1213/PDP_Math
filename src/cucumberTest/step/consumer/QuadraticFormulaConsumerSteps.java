//package feature.consumer;
//
//import ch.qos.logback.classic.Logger;
//import com.james2ch9developer.pdp_math.comsumer.QuadraticFormulaConsumer;
//import com.james2ch9developer.pdp_math.document.QuadraticFormula;
//import com.james2ch9developer.pdp_math.repository.QuadraticFormulaRepository;
//import org.junit.jupiter.api.Test;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.testcontainers.containers.GenericContainer;
//import org.testcontainers.containers.KafkaContainer;
//import org.testcontainers.containers.MongoDBContainer;
//// import org.testcontainers.containers.KafkaContainer;
//// import org.testcontainers.containers.MongoDBContainer;
//import org.testcontainers.junit.jupiter.Container;
//import org.testcontainers.junit.jupiter.Testcontainers;
//import org.testcontainers.utility.DockerImageName;
//import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
//
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
////@DirtiesContext
////@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
//@SpringBootTest
//@Testcontainers
//class QuadraticFormulaConsumerSteps {
//  private final Logger logger = (Logger) LoggerFactory.getLogger(QuadraticFormulaConsumerTest.class);
//
//  @Container
//  @ServiceConnection
//  private static final GenericContainer MONGODB_CONTAINER = new GenericContainer(DockerImageName
//  .parse("mongo:latest"));
//  @Container
//  @ServiceConnection
//  private static final GenericContainer KAFKA_CONTAINER = new GenericContainer(DockerImageName
//    .parse("confluentinc/cp-kafka:latest"));
//
//  @Autowired
//  private KafkaTemplate<String, String> template;
//
//  @Autowired
//  private QuadraticFormulaRepository repository;
//
//  @Autowired
//  private QuadraticFormulaConsumer consumer;
//
//  @Value("${spring.kafka.topic}")
//  private String topic;
//
//  @Test
//  public void consumerMessageReceivedTest() {
//    logger.info("consumerMessageReceivedTest()");
//
//    String quadraticFormulaArguments = "1, 4, 3, john.doe@yahoo.com";
//    // DeleteMe: This is just to test the spotbugs error: NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE
//    // String name = null;
//    // System.out.println(name.length());
//
//    template.send(topic, quadraticFormulaArguments);
//
//    // This Thread.sleep() needs to be here. I was getting the error: IllegalArgumentException
//    // And it was coming from the one that I throw in my if statement from QuadraticFormula.java
//    // I surmised that there was not enough time between the production of the message in the
//    // consumption of the message. I was right, because this Thread.sleep() fixed it.
//    try {
//      Thread.sleep(3000);
//    } catch (InterruptedException e) {
//      logger.error("InterruptedException: " + e.getMessage());
//    }
//
//    QuadraticFormula quadraticFormulaData = new QuadraticFormula.Builder()
//        .setCoefficientsAndUserEmail(consumer.getMessage()).build();
//
//    assertEquals(1, quadraticFormulaData.getA());
//    assertEquals(4, quadraticFormulaData.getB());
//    assertEquals(3, quadraticFormulaData.getC());
//    assertEquals("john.doe@yahoo.com", quadraticFormulaData.getUserEmail());
//    assertEquals(-1.0, quadraticFormulaData.getAnswer1());
//    assertEquals(-3.0, quadraticFormulaData.getAnswer2());
//
//    repository.save(quadraticFormulaData);
//
//    QuadraticFormula savedQuadraticFormulaData = repository.findById(quadraticFormulaData.getUserEmail()).orElse(null);
//
//    assert savedQuadraticFormulaData != null;
//    assertEquals(1, savedQuadraticFormulaData.getA());
//    assertEquals(4, savedQuadraticFormulaData.getB());
//    assertEquals(3, savedQuadraticFormulaData.getC());
//    assertEquals("john.doe@yahoo.com", savedQuadraticFormulaData.getUserEmail());
//    assertEquals(-1.0, savedQuadraticFormulaData.getAnswer1());
//    assertEquals(-3.0, savedQuadraticFormulaData.getAnswer2());
//  }
//}
