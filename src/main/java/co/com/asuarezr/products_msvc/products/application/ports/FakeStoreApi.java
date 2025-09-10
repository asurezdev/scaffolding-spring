package co.com.asuarezr.products_msvc.products.application.ports;

import co.com.asuarezr.products_msvc.products.application.dtos.FakeApiDto;

import java.util.List;

public interface FakeStoreApi {

  List<FakeApiDto> getAllProducts();

  FakeApiDto getProductById(Long id);

  FakeApiDto CreateProduct(FakeApiDto fakeApiDto);

  FakeApiDto UpdateProduct(Long id, FakeApiDto fakeApiDto);

  void deleteProductById(Long id);
}
