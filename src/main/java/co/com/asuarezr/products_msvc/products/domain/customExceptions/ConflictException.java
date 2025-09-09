package co.com.asuarezr.products_msvc.products.domain.customExceptions;

public class ConflictException extends RuntimeException {
    public ConflictException(){}
    public ConflictException(String message) {
        super(message);
    }
}
  