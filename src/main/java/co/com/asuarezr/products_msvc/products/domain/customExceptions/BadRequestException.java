package co.com.asuarezr.products_msvc.products.domain.customExceptions;

public class BadRequestException extends RuntimeException{

  public BadRequestException(String msg){
    super(msg);
  }

  public BadRequestException(){}

}
