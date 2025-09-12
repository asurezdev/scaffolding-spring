package co.com.asuarezr.products_msvc.products.infrastructure.databese.product;

import co.com.asuarezr.products_msvc.products.application.dtos.ProductDto;
import co.com.asuarezr.products_msvc.products.domain.models.Product;
import co.com.asuarezr.products_msvc.products.domain.repositories.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostgresProductRepository implements ProductRepository {
  private final ProductCrudRepository productCrudRepository;

  public PostgresProductRepository(
          ProductCrudRepository productCrudRepository
  ) {
    this.productCrudRepository = productCrudRepository;
  }

  @Override
  public ProductDto save(Product product) {
    ProductEntity productEntity = this.toEntity(product);
    ProductEntity productSaved = this.productCrudRepository.save(productEntity);
    return  this.toDto(productSaved);
  }

  @Override
  public List<ProductDto> findAll() {
    return this.productCrudRepository.findProductsBy();
  }

  @Override
  public ProductDto findById(String id) {
    return this.productCrudRepository.findProductById(id);
  }


  @Override
  public void deleteById(String id) {

  }

  // mappers
  private ProductEntity toEntity(Product product){
    return new ProductEntity(
            product.getId(),
            product.getName(),
            product.getDescription(),
            product.getPrice(),
            product.getCurrency()
    );
  }

  private Product toDomain(ProductEntity productEntity){
    return new Product(
            productEntity.getId(),
            productEntity.getName(),
            productEntity.getDescription(),
            productEntity.getPrice(),
            productEntity.getCurrency()
    );
  }

  private ProductDto toDto(ProductEntity productEntity){
    return new ProductDto(
            productEntity.getId(),
            productEntity.getName(),
            productEntity.getDescription(),
            productEntity.getPrice(),
            productEntity.getCurrency()
    );
  }

}
