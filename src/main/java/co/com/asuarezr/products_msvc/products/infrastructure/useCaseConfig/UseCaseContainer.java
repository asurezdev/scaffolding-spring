package co.com.asuarezr.products_msvc.products.infrastructure.useCaseConfig;

import co.com.asuarezr.products_msvc.products.application.interfaces.Dispatchable;
import co.com.asuarezr.products_msvc.products.application.interfaces.Handler;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UseCaseContainer {
  private final Map<Class<? extends Dispatchable>, Handler> instances = new HashMap<>();

  public void register(Class<? extends Dispatchable> type, Handler usecase) {
    instances.put(type, usecase);
  }

  public Handler resolve(Class<? extends Dispatchable> type) {
    Handler instance = instances.get(type);

    if (instance == null) {
      throw new IllegalArgumentException("No registered instance found for type: " + type.getName());
    }

    return instance;
  }

}
