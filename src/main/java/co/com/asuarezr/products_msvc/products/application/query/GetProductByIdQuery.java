package co.com.asuarezr.products_msvc.products.application.query;

import co.com.asuarezr.products_msvc.products.application.dtos.ResponseProductDto;
import co.com.asuarezr.products_msvc.products.application.interfaces.Query;

public record GetProductByIdQuery (
        String id
)implements Query<ResponseProductDto> { }
