package source.service;

import java.util.Map;

import source.domain.Product;
import source.shared.AbstractCRUD;

public interface ProductService extends AbstractCRUD<Product> {
    public Map<Integer, Product> readAllMap();
}
