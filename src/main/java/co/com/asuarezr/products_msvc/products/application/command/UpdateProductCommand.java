package co.com.asuarezr.products_msvc.products.application.command;

import co.com.asuarezr.products_msvc.products.application.interfaces.Command;
import jakarta.validation.constraints.Null;

public record UpdateProductCommand(
        @Null
        String id,
        String name,
        String description,
        Double price,
        String currency
) implements Command {

  public UpdateProductCommand withId(String id){
    return new UpdateProductCommand(
            id,
            this.name(),
            this.description(),
            this.price(),
            this.currency()
    );
  }
}
