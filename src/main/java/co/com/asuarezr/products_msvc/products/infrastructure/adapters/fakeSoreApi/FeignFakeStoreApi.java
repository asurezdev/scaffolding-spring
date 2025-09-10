package co.com.asuarezr.products_msvc.products.infrastructure.adapters.fakeSoreApi;


import co.com.asuarezr.products_msvc.products.application.dtos.FakeApiDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "fakeStoreApi", url = "https://fakestoreapi.com")
public interface FeignFakeStoreApi {

  @GetMapping("/products")
  List<FakeApiDto> getAllProducts();

  @GetMapping("/products/{id}")
  FakeApiDto getProductById(@PathVariable Long id);

  @PostMapping("/products")
  FakeApiDto CreateProduct(@RequestBody FakeApiDto fakeApiDto);

  @PutMapping("/products/{id}")
  FakeApiDto UpdateProduct(@PathVariable Long id, @RequestBody FakeApiDto fakeApiDto);

  @DeleteMapping("/products/{id}")
  void deleteProductById(@PathVariable Long id);
}
