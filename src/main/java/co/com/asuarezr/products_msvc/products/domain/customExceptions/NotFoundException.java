package co.com.asuarezr.products_msvc.products.domain.customExceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(){

    }
    public NotFoundException(String message) {
        super(message);
    }
}
  