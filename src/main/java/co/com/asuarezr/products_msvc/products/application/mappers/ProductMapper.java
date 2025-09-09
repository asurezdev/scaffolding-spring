package co.com.asuarezr.products_msvc.products.application.mappers;

import co.com.asuarezr.products_msvc.products.application.command.CreateProductCommand;
import co.com.asuarezr.products_msvc.products.application.command.UpdateProductCommand;
import co.com.asuarezr.products_msvc.products.application.dtos.ProductDto;
import co.com.asuarezr.products_msvc.products.application.dtos.ResponseProductDto;
import co.com.asuarezr.products_msvc.products.domain.annotations.ApplicationComponent;
import co.com.asuarezr.products_msvc.products.domain.models.Product;

import java.util.List;

@ApplicationComponent
public class ProductMapper {

  public Product toDomain(CreateProductCommand createProductDto) {
    return new Product(
            createProductDto.id(),
            createProductDto.name(),
            createProductDto.description(),
            createProductDto.price(),
            createProductDto.currency()
    );
  }

  public ResponseProductDto toResponseProductDto(ProductDto product) {
    return new ResponseProductDto(
            product.id(),
            product.name(),
            product.description(),
            product.price(),
            product.currency()
    );
  }

  public List<ResponseProductDto> toResponseProductDto(List<ProductDto> products) {
    return products.stream().map(this::toResponseProductDto).toList();
  }

  public Product merge(ProductDto product, UpdateProductCommand updateProductCommand) {
    String id = updateProductCommand.id();
    String name = updateProductCommand.name();
    String description = updateProductCommand.description();
    Double price = updateProductCommand.price();
    String currency = updateProductCommand.currency();
    if(name == null){
      name = product.name();
    }
    if(description == null){
      description = product.description();
    }
    if(price == null){
      price = product.price();
    }
    if(currency == null){
      currency = product.currency();
    }
    return new Product(
            id,
            name,
            description,
            price,
            currency
    );
  }
}
