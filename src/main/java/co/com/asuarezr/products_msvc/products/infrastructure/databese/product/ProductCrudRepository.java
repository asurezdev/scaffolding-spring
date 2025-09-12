package co.com.asuarezr.products_msvc.products.infrastructure.databese.product;

import co.com.asuarezr.products_msvc.products.application.dtos.ProductDto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductCrudRepository extends CrudRepository<ProductEntity, String> {

  List<ProductDto> findProductsBy();

  ProductDto findProductById(String id);

}
