package co.com.asuarezr.products_msvc.products.domain.errorMessage;

import java.util.List;

public record FullErrorMessage(
        List<String> message,
        String error,
        int statusCode
      ) implements ErrorMessage { }
