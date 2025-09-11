package co.com.asuarezr.products_msvc.products.infrastructure.rest.controller;

import co.com.asuarezr.products_msvc.products.application.command.CreateProductCommand;
import co.com.asuarezr.products_msvc.products.application.command.UpdateProductCommand;
import co.com.asuarezr.products_msvc.products.application.dtos.ResponseProductDto;
import co.com.asuarezr.products_msvc.products.application.query.GetAllProductsQuery;
import co.com.asuarezr.products_msvc.products.application.query.GetProductByIdQuery;
import co.com.asuarezr.products_msvc.products.infrastructure.configurations.useCaseConfig.UseCaseMediator;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/products")
public class ProductsRestController {

  private final UseCaseMediator useCaseMediator;

  public ProductsRestController(UseCaseMediator useCaseMediator) {
    this.useCaseMediator = useCaseMediator;
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  public void create(@RequestBody @Valid CreateProductCommand createProductCommand){
    this.useCaseMediator.dispatch(createProductCommand);
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping
  public List<ResponseProductDto> findAll(){
    return this.useCaseMediator.dispatch(new GetAllProductsQuery());
  }


  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/{id}")
  public ResponseProductDto findById(@PathVariable String id){
    return this.useCaseMediator.dispatch(new GetProductByIdQuery(id));
  }

  @ResponseStatus(HttpStatus.OK)
  @PatchMapping("/{id}")
  public void updated(@RequestBody @Valid UpdateProductCommand updateProductCommand, @PathVariable String id){
    UpdateProductCommand updateProductCommandWithId = updateProductCommand.withId(id);
    this.useCaseMediator.dispatch(updateProductCommandWithId);
  }


}
