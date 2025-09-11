package co.com.asuarezr.products_msvc.products.infrastructure.configurations.useCaseConfig;


import co.com.asuarezr.products_msvc.products.domain.annotations.ApplicationComponent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "co.com.asuarezr.products_msvc.products.application",
        includeFilters = @ComponentScan.Filter(
                type = FilterType.ANNOTATION, value = ApplicationComponent.class
        )
)
public class UseCaseConfig {
}
