package co.com.asuarezr.products_msvc.products.application.interfaces;

public interface CommandHandler<T extends Command> extends Handler {
  void handle(T command);
}
