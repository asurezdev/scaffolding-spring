package co.com.asuarezr.products_msvc.products.domain.models;


import co.com.asuarezr.products_msvc.products.domain.customExceptions.ConflictException;

public class Product {
  private final String id;
  private final String name;
  private final String description;
  private final Double price;
  private final String currency;

  public Product(
          String id,
          String name,
          String description,
          Double price,
          String currency
  ) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.price = price;
    this.currency = currency;
    this.priceValidator();
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public Double getPrice() {
    return price;
  }

  public String getCurrency() {
    return currency;
  }

  //validators
  private void priceValidator(){
    if(this.price == null || this.price <= 0){
      throw  new ConflictException();
    }
  }

}
