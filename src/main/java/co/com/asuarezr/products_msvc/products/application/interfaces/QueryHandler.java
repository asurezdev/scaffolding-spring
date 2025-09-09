package co.com.asuarezr.products_msvc.products.application.interfaces;

public interface QueryHandler<Q extends Query<R>, R> extends Handler{
  R handle(Q query);
}
