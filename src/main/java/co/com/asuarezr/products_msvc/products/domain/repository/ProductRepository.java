package co.com.asuarezr.products_msvc.products.domain.repository;

import co.com.asuarezr.products_msvc.products.application.dtos.ProductDto;
import co.com.asuarezr.products_msvc.products.domain.models.Product;

import java.util.List;

public interface ProductRepository {
  List<ProductDto> findAll();
  ProductDto findById(String id);
  ProductDto save(Product product);
  void deleteById(String id);
}
