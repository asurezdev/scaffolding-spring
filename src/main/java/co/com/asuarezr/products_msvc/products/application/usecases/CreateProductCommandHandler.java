package co.com.asuarezr.products_msvc.products.application.usecases;

import co.com.asuarezr.products_msvc.products.application.command.CreateProductCommand;
import co.com.asuarezr.products_msvc.products.application.interfaces.CommandHandler;
import co.com.asuarezr.products_msvc.products.application.mappers.ProductMapper;
import co.com.asuarezr.products_msvc.products.application.ports.MessageBroker;
import co.com.asuarezr.products_msvc.products.domain.annotations.ApplicationComponent;
import co.com.asuarezr.products_msvc.products.domain.models.Product;
import co.com.asuarezr.products_msvc.products.domain.repository.ProductRepository;


@ApplicationComponent
public class CreateProductCommandHandler implements CommandHandler<CreateProductCommand> {

  private final ProductRepository productRepository;
  private final ProductMapper productMapper;
  private final MessageBroker messageBroker;

  public CreateProductCommandHandler(
          ProductRepository productRepository,
          ProductMapper productMapper,
          MessageBroker messageBroker
  ) {
    this.productRepository = productRepository;
    this.productMapper = productMapper;
    this.messageBroker = messageBroker;
  }


  @Override
  public void handle(CreateProductCommand createProductCommand) {
    Product newProduct = this.productMapper.toDomain(createProductCommand);
    this.productRepository.save(newProduct);
    this.messageBroker.send("add-product", createProductCommand);
  }

}
