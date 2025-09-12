package co.com.asuarezr.products_msvc.products.application.usecases;

import co.com.asuarezr.products_msvc.products.application.dtos.FakeApiDto;
import co.com.asuarezr.products_msvc.products.application.dtos.ProductDto;
import co.com.asuarezr.products_msvc.products.application.dtos.ResponseProductDto;
import co.com.asuarezr.products_msvc.products.application.interfaces.QueryHandler;
import co.com.asuarezr.products_msvc.products.application.mappers.ProductMapper;
import co.com.asuarezr.products_msvc.products.application.ports.FakeStoreApi;
import co.com.asuarezr.products_msvc.products.application.query.GetProductByIdQuery;
import co.com.asuarezr.products_msvc.products.domain.annotations.ApplicationComponent;
import co.com.asuarezr.products_msvc.products.domain.customExceptions.NotFoundException;
import co.com.asuarezr.products_msvc.products.domain.repositories.ProductRepository;


@ApplicationComponent
public class GetProductByIdQueryHandler implements QueryHandler<GetProductByIdQuery, ResponseProductDto> {
  private final ProductRepository productRepository;
  private final ProductMapper productMapper;
  private final FakeStoreApi fakeStoreApi;

  public GetProductByIdQueryHandler(
          ProductRepository productRepository,
          ProductMapper productMapper,
          FakeStoreApi fakeStoreApi
  ) {
    this.productRepository = productRepository;
    this.productMapper = productMapper;
    this.fakeStoreApi = fakeStoreApi;
  }

  @Override
  public ResponseProductDto handle(GetProductByIdQuery query) {
    FakeApiDto fake = this.fakeStoreApi.getProductById(1L);
    System.out.println(fake);
    ProductDto response = this.productRepository.findById(query.id());
    if(response == null){
      throw new NotFoundException();
    }
    return this.productMapper.toResponseProductDto(response);
  }
}
