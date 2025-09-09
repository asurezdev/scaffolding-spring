package co.com.asuarezr.products_msvc.products.application.dtos;

public record ResponseProductDto(
        String id,
        String name,
        String description,
        Double price,
        String currency
) {}
