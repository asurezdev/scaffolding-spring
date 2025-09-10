package co.com.asuarezr.products_msvc.products.infrastructure.adapters.messageBroker;

import co.com.asuarezr.products_msvc.products.application.ports.MessageBroker;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaMessageBroker implements MessageBroker {

  private final KafkaTemplate<String, Object> kafkaTemplate;

  public KafkaMessageBroker(
          KafkaTemplate<String, Object> kafkaTemplate
  ) {
    this.kafkaTemplate = kafkaTemplate;
  }

  @Override
  public void send(String channel, Object data) {
    this.kafkaTemplate.send(channel, data);
  }
}
