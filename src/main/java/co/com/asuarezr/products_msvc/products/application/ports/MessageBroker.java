package co.com.asuarezr.products_msvc.products.application.ports;

public interface MessageBroker {
  void send(String channel, Object data);
}
