package tn.happ.Model;

import java.util.Objects;

public class Product {
    private int IdProduct;
    private String RefProduct;
    private String SupplierName;
    private Float UnitPriceProduct;
    private int QuantityProduct;

    public Product(int idProduct, String refProduct, String supplierName, Float unitPriceProduct, int quantityProduct) {
        IdProduct = idProduct;
        RefProduct = refProduct;
        SupplierName = supplierName;
        UnitPriceProduct = unitPriceProduct;
        QuantityProduct = quantityProduct;
    }

    public Product(String refProduct, String supplierName, Float unitPriceProduct, int quantityProduct) {
        RefProduct = refProduct;
        SupplierName = supplierName;
        UnitPriceProduct = unitPriceProduct;
        QuantityProduct = quantityProduct;
    }

    public Product() {

    }

    public int getIdProduct() {
        return IdProduct;
    }

    public void setIdProduct(int idProduct) {
        IdProduct = idProduct;
    }

    public String getRefProduct() {
        return RefProduct;
    }

    public void setRefProduct(String refProduct) {
        RefProduct = refProduct;
    }

    public String getSupplierName() {
        return SupplierName;
    }

    public void setSupplierName(String supplierName) {
        SupplierName = supplierName;
    }

    public Float getUnitPriceProduct() {
        return UnitPriceProduct;
    }

    public void setUnitPriceProduct(Float unitPriceProduct) {
        UnitPriceProduct = unitPriceProduct;
    }

    public int getQuantityProduct() {
        return QuantityProduct;
    }

    public void setQuantityProduct(int quantityProduct) {
        QuantityProduct = quantityProduct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return IdProduct == product.IdProduct;
    }

    @Override
    public int hashCode() {
        return Objects.hash(IdProduct);
    }

    @Override
    public String toString() {
        return "Product{" +
                "IdProduct=" + IdProduct +
                ", RefProduct='" + RefProduct + '\'' +
                ", SupplierName='" + SupplierName + '\'' +
                ", UnitPriceProduct=" + UnitPriceProduct +
                ", QuantityProduct=" + QuantityProduct +
                '}';
    }


}
