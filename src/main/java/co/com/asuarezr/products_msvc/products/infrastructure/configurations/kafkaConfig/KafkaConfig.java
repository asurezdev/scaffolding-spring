package co.com.asuarezr.products_msvc.products.infrastructure.configurations.kafkaConfig;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.FixedBackOff;


@Configuration
@EnableKafka
public class KafkaConfig {

  @Value("${spring.kafka.listener.retry.max-attempts}")
  private long maxAttempts;

  @Value("${spring.kafka.listener.retry.backoff-delay}")
  private long backoffDelay;

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(
          ConsumerFactory<String, String> consumerFactory,
          KafkaTemplate<Object, Object> kafkaTemplate) {

    var factory = new ConcurrentKafkaListenerContainerFactory<String, String>();
    factory.setConsumerFactory(consumerFactory);
    factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);

    // Dead Letter Publishing Recoverer
    DeadLetterPublishingRecoverer recoverer = new DeadLetterPublishingRecoverer(
            kafkaTemplate,
            // Redirige al topic original + ".DLT"
            (record, ex) -> new TopicPartition(record.topic() + ".DLT", record.partition())
    );

    // Configura reintentos:
    FixedBackOff backOff = new FixedBackOff(backoffDelay, maxAttempts);

    // Manejador de errores
    DefaultErrorHandler errorHandler = new DefaultErrorHandler(recoverer, backOff);

    factory.setCommonErrorHandler(errorHandler);
    return factory;
  }

  // topics Register
  @Bean
  public NewTopic addProductTopic() {
    return new NewTopic("add-product", 3, (short) 1);
  }

}
