package co.com.asuarezr.products_msvc.products.application.usecases;

import co.com.asuarezr.products_msvc.products.application.dtos.ProductDto;
import co.com.asuarezr.products_msvc.products.application.dtos.ResponseProductDto;
import co.com.asuarezr.products_msvc.products.application.interfaces.QueryHandler;
import co.com.asuarezr.products_msvc.products.application.mappers.ProductMapper;
import co.com.asuarezr.products_msvc.products.application.query.GetProductByIdQuery;
import co.com.asuarezr.products_msvc.products.domain.annotations.ApplicationComponent;
import co.com.asuarezr.products_msvc.products.domain.customExceptions.NotFoundException;
import co.com.asuarezr.products_msvc.products.domain.repository.ProductRepository;


@ApplicationComponent
public class GetProductByIdQueryHandler implements QueryHandler<GetProductByIdQuery, ResponseProductDto> {
  private final ProductRepository productRepository;
  private final ProductMapper productMapper;

  public GetProductByIdQueryHandler(
          ProductRepository productRepository,
          ProductMapper productMapper
  ) {
    this.productRepository = productRepository;
    this.productMapper = productMapper;
  }

  @Override
  public ResponseProductDto handle(GetProductByIdQuery query) {
    ProductDto response = this.productRepository.findById(query.id());
    if(response == null){
      throw new NotFoundException();
    }
    return this.productMapper.toResponseProductDto(response);
  }
}
