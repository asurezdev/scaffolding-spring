package co.com.asuarezr.products_msvc.products.infrastructure.configurations.useCaseConfig;

import co.com.asuarezr.products_msvc.products.application.interfaces.Command;
import co.com.asuarezr.products_msvc.products.application.interfaces.CommandHandler;
import co.com.asuarezr.products_msvc.products.application.interfaces.Query;
import co.com.asuarezr.products_msvc.products.application.interfaces.QueryHandler;
import org.springframework.stereotype.Component;

@Component
public class UseCaseMediator {
  private final UseCaseContainer useCaseContainer;

  public UseCaseMediator(UseCaseContainer useCaseContainer) {
    this.useCaseContainer = useCaseContainer;
  }

  @SuppressWarnings("unchecked")
  public <R, Q extends Query<R>> R dispatch(Q query) {
    QueryHandler<Q, R> instance = (QueryHandler<Q, R>) this.useCaseContainer.resolve(query.getClass());

    if (instance == null) {
      throw new IllegalArgumentException("No registered instance found for type: " + query.getClass().getName());
    }

    return instance.handle(query);
  }

  @SuppressWarnings("unchecked")
  public <C extends Command> void dispatch(C command) {
    CommandHandler<C> instance = (CommandHandler<C>) this.useCaseContainer.resolve(command.getClass());

    if (instance == null) {
      throw new IllegalArgumentException("No registered instance found for type: " + command.getClass().getName());
    }

    instance.handle(command);
  }
}
