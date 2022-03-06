package tn.happ.Model;

import java.util.ArrayList;
import java.util.List;

public class TechnicalSheet {
    List<Product> productArrayList = new ArrayList<>();
    private Platter platterTS;
    private int Qte;
    private float DiffPrice;
    //private ;


    public TechnicalSheet(List<Product> productArrayList, Platter platterTS) {
        this.productArrayList = productArrayList;
        this.platterTS = platterTS;
    }

    public List<Product> getProductArrayList() {
        return productArrayList;
    }

    public void setProductArrayList(List<Product> productArrayList) {
        this.productArrayList = productArrayList;
    }

    public Platter getPlatterTS() {
        return platterTS;
    }

    public void setPlatterTS(Platter platterTS) {
        this.platterTS = platterTS;
    }
}
