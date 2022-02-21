/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package happ.entity;

import java.util.Objects;

/**
 *
 * @author ASUS
 */
public class Product {
   private int IdProduct;
   private String RefProduct;
   private String SupplierName;
   private Float UnitPriceProduct;
   private int QuantityProduct;
   

    public Product() {
    }

    public Product(String RefProduct, String SupplierName, Float UnitPriceProduct, int QuantityProduct) {
        this.RefProduct = RefProduct;
        this.SupplierName = SupplierName;
        this.UnitPriceProduct = UnitPriceProduct;
        this.QuantityProduct = QuantityProduct;
    }

    public Product(int IdProduct, String RefProduct, String SupplierName, Float UnitPriceProduct, int QuantityProduct) {
        this.IdProduct = IdProduct;
        this.RefProduct = RefProduct;
        this.SupplierName = SupplierName;
        this.UnitPriceProduct = UnitPriceProduct;
        this.QuantityProduct = QuantityProduct;
    }

    public void setIdProduct(int IdProduct) {
        this.IdProduct = IdProduct;
    }

    public void setRefProduct(String RefProduct) {
        this.RefProduct = RefProduct;
    }

    public void setSupplierName(String SupplierName) {
        this.SupplierName = SupplierName;
    }

    public void setUnitPriceProduct(Float UnitPriceProduct) {
        this.UnitPriceProduct = UnitPriceProduct;
    }

    public void setQuantityProduct(int QuantityProduct) {
        this.QuantityProduct = QuantityProduct;
    }

    public int getIdProduct() {
        return IdProduct;
    }

    public String getRefProduct() {
        return RefProduct;
    }

    public String getSupplierName() {
        return SupplierName;
    }

    public Float getUnitPriceProduct() {
        return UnitPriceProduct;
    }

    public int getQuantityProduct() {
        return QuantityProduct;
    }

    @Override
    public String toString() {
        return "Product{" + "IdProduct=" + IdProduct + ", RefProduct=" + RefProduct + ", SupplierName=" + SupplierName + ", UnitPriceProduct=" + UnitPriceProduct + ", QuantityProduct=" + QuantityProduct + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.RefProduct);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (this.IdProduct != other.IdProduct) {
            return false;
        }
        return true;
    }

    
}
