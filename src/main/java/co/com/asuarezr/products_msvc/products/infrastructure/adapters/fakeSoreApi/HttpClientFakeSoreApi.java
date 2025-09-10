package co.com.asuarezr.products_msvc.products.infrastructure.adapters.fakeSoreApi;

import co.com.asuarezr.products_msvc.products.application.dtos.FakeApiDto;
import co.com.asuarezr.products_msvc.products.application.ports.FakeStoreApi;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HttpClientFakeSoreApi implements FakeStoreApi {

  private final FeignFakeStoreApi feignFakeStoreApi;

  public HttpClientFakeSoreApi(
          FeignFakeStoreApi feignFakeStoreApi
  ) {
    this.feignFakeStoreApi = feignFakeStoreApi;
  }

  @Override
  public List<FakeApiDto> getAllProducts() {
    return this.feignFakeStoreApi.getAllProducts();
  }

  @Override
  public FakeApiDto getProductById(Long id) {
    return this.feignFakeStoreApi.getProductById(id);
  }

  @Override
  public FakeApiDto CreateProduct(FakeApiDto fakeApiDto) {
    return this.feignFakeStoreApi.CreateProduct(fakeApiDto);
  }

  @Override
  public FakeApiDto UpdateProduct(Long id, FakeApiDto fakeApiDto) {
    return this.feignFakeStoreApi.UpdateProduct(id,fakeApiDto);
  }

  @Override
  public void deleteProductById(Long id) {
    this.feignFakeStoreApi.deleteProductById(id);
  }
}
