package co.com.asuarezr.products_msvc.products.domain.customExceptions;

public class UnauthorizedException extends RuntimeException{

    public UnauthorizedException(){

    }
    public UnauthorizedException(String message) {
        super(message);
    }
}
  