package co.com.asuarezr.products_msvc.products.domain.errorMessage;

public record ShortErrorMessage(
        String message,
        int statusCode
      ) implements ErrorMessage { }
