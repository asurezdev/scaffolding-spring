package co.com.asuarezr.products_msvc.products.application.command;

import co.com.asuarezr.products_msvc.products.application.interfaces.Command;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

public record CreateProductCommand(
        @Null
        String id,

        @NotNull
        String name,

        @NotNull
        String description,

        @NotNull
        Double price,

        @NotNull
        String currency
) implements Command { }
