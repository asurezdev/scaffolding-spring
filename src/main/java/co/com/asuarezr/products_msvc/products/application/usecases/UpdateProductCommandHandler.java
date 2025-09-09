package co.com.asuarezr.products_msvc.products.application.usecases;

import co.com.asuarezr.products_msvc.products.application.command.UpdateProductCommand;
import co.com.asuarezr.products_msvc.products.application.dtos.ProductDto;
import co.com.asuarezr.products_msvc.products.application.interfaces.CommandHandler;
import co.com.asuarezr.products_msvc.products.application.mappers.ProductMapper;
import co.com.asuarezr.products_msvc.products.domain.annotations.ApplicationComponent;
import co.com.asuarezr.products_msvc.products.domain.customExceptions.NotFoundException;
import co.com.asuarezr.products_msvc.products.domain.models.Product;
import co.com.asuarezr.products_msvc.products.domain.repository.ProductRepository;

@ApplicationComponent
public class UpdateProductCommandHandler implements CommandHandler<UpdateProductCommand> {

  private final ProductRepository productRepository;
  private final ProductMapper productMapper;

  public UpdateProductCommandHandler(
          ProductRepository productRepository,
          ProductMapper productMapper
  ) {
    this.productRepository = productRepository;
    this.productMapper = productMapper;
  }

  @Override
  public void handle(UpdateProductCommand updateProductCommand) {
    ProductDto currentProduct = this.productRepository.findById(updateProductCommand.id());
    if(currentProduct == null){
      throw new NotFoundException();
    }
    Product newProduct = this.productMapper.merge(currentProduct, updateProductCommand);
    this.productRepository.save(newProduct);
  }
}
