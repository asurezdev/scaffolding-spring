package co.com.asuarezr.products_msvc.products.infrastructure.configurations.httpClientConfig;

import co.com.asuarezr.products_msvc.products.domain.customExceptions.BadRequestException;
import co.com.asuarezr.products_msvc.products.domain.customExceptions.ForbiddenException;
import co.com.asuarezr.products_msvc.products.domain.customExceptions.NotFoundException;
import co.com.asuarezr.products_msvc.products.domain.customExceptions.UnauthorizedException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class ErrorDecoderHttpClient implements ErrorDecoder {

  @Override
  public Exception decode(String methodKey, Response response) {
    int status = response.status();
    String url = response.request().url();
    String message = "Fail remote service " + url;

    return switch (status) {
      case 403 -> new ForbiddenException(message);
      case 401 -> new UnauthorizedException(message);
      case 404 -> new NotFoundException(message);
      case 400 -> new BadRequestException(message);
      default -> new RuntimeException(message);
    };
  }

}
