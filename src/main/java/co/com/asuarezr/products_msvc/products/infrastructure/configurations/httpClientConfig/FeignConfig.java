package co.com.asuarezr.products_msvc.products.infrastructure.configurations.httpClientConfig;

import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

  //aca configuracion de interceptores.

  @Bean
  public ErrorDecoder errorDecoder() {
    return new ErrorDecoderHttpClient();
  }

}
