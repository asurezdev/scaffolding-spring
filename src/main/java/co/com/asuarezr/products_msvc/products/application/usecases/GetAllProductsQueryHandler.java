package co.com.asuarezr.products_msvc.products.application.usecases;

import co.com.asuarezr.products_msvc.products.application.dtos.ProductDto;
import co.com.asuarezr.products_msvc.products.application.dtos.ResponseProductDto;
import co.com.asuarezr.products_msvc.products.application.interfaces.QueryHandler;
import co.com.asuarezr.products_msvc.products.application.mappers.ProductMapper;
import co.com.asuarezr.products_msvc.products.application.query.GetAllProductsQuery;
import co.com.asuarezr.products_msvc.products.domain.annotations.ApplicationComponent;
import co.com.asuarezr.products_msvc.products.domain.repository.ProductRepository;


import java.util.List;

@ApplicationComponent
public class GetAllProductsQueryHandler implements QueryHandler<GetAllProductsQuery, List<ResponseProductDto>> {

  private final ProductRepository productRepository;
  private final ProductMapper productMapper;

  public GetAllProductsQueryHandler(
          ProductRepository productRepository,
          ProductMapper productMapper
  ) {
    this.productRepository = productRepository;
    this.productMapper = productMapper;
  }

  @Override
  public List<ResponseProductDto> handle(GetAllProductsQuery query) {
   List<ProductDto> response =  this.productRepository.findAll();
   return this.productMapper.toResponseProductDto(response);
  }
}
