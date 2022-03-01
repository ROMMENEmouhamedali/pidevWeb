package tn.happ.services;


import java.util.List;
import tn.happ.Model.Product;

public interface ProductInterface {
    public void addProduct( Product p);
    public List<Product> displayAllProduct();
    public void deleteProduct(int IdProduct);
    public void UpdateProduct(Product p);
}
