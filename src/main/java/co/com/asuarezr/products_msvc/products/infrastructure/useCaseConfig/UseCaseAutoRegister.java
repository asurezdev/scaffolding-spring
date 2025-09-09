package co.com.asuarezr.products_msvc.products.infrastructure.useCaseConfig;

import co.com.asuarezr.products_msvc.products.application.interfaces.CommandHandler;
import co.com.asuarezr.products_msvc.products.application.interfaces.Dispatchable;
import co.com.asuarezr.products_msvc.products.application.interfaces.QueryHandler;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.util.Map;

@Component
public class UseCaseAutoRegister implements CommandLineRunner {

  private final UseCaseContainer useCaseContainer;
  private final ApplicationContext applicationContext;

  public UseCaseAutoRegister(
          UseCaseContainer useCaseContainer,
          ApplicationContext applicationContext
  ) {
    this.useCaseContainer = useCaseContainer;
    this.applicationContext = applicationContext;
  }

  @Override
  public void run(String... args) throws Exception {
    // Registrar todos los CommandHandlers
    Map<String, CommandHandler> commandHandlers = applicationContext.getBeansOfType(CommandHandler.class);
    commandHandlers.values().forEach(handler -> {
      Class<Dispatchable> commandType = getGenericType(handler.getClass());
      useCaseContainer.register(commandType, handler);
    });
    // Registrar todos los QueryHandlers
    Map<String, QueryHandler> queryHandlers = applicationContext.getBeansOfType(QueryHandler.class);
    queryHandlers.values().forEach(handler -> {
      Class<Dispatchable> queryType = getGenericType(handler.getClass());
      useCaseContainer.register(queryType, handler);
    });
  }

  @SuppressWarnings("unchecked")
  private Class<Dispatchable> getGenericType(Class<?> handlerClass) {
    // Aquí analizas el tipo genérico real usando reflection
    ParameterizedType type = (ParameterizedType) handlerClass.getGenericInterfaces()[0];
    return (Class<Dispatchable>) type.getActualTypeArguments()[0];
  }

}
