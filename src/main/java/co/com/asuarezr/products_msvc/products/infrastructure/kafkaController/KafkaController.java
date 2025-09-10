package co.com.asuarezr.products_msvc.products.infrastructure.kafkaController;

import co.com.asuarezr.products_msvc.products.application.command.CreateProductCommand;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class KafkaController {

  @KafkaListener(topics = "add-product")
  void testListener(CreateProductCommand msg, Acknowledgment ack){
    System.out.println("mensaje recibido Kafka controller");
    System.out.println(msg);
    ack.acknowledge();// Confirmar procesamiento exitoso
  }
}
