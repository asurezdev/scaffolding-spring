package co.com.asuarezr.products_msvc.products.domain.customExceptions;

public class ForbiddenException extends RuntimeException {
    public ForbiddenException(){

    }
    public ForbiddenException(String message) {
        super(message);
    }
}
  