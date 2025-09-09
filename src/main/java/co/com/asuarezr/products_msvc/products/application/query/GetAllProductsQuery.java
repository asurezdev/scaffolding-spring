package co.com.asuarezr.products_msvc.products.application.query;

import co.com.asuarezr.products_msvc.products.application.dtos.ResponseProductDto;
import co.com.asuarezr.products_msvc.products.application.interfaces.Query;

import java.util.List;

public record GetAllProductsQuery() implements Query<List<ResponseProductDto>> {}
