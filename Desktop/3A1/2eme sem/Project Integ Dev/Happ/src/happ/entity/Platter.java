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
public class Platter {
    private String IdPlatter;
    private String NamePlatter;
    private String ImagePlatter;
    private String ingredient;
    private float PricePlatter;
    private int NbPlatter;
    private String DescriptionPlatter;

    public Platter() {
    }

    public Platter(String IdPlatter, String NamePlatter, String ImagePlatter, String ingredient, float PricePlatter, int NbPlatter, String DescriptionPlatter) {
        this.IdPlatter = IdPlatter;
        this.NamePlatter = NamePlatter;
        this.ImagePlatter = ImagePlatter;
        this.ingredient = ingredient;
        this.PricePlatter = PricePlatter;
        this.NbPlatter = NbPlatter;
        this.DescriptionPlatter = DescriptionPlatter;
    }

    public Platter(String NamePlatter, String ImagePlatter, String ingredient, float PricePlatter, int NbPlatter, String DescriptionPlatter) {
        this.NamePlatter = NamePlatter;
        this.ImagePlatter = ImagePlatter;
        this.ingredient = ingredient;
        this.PricePlatter = PricePlatter;
        this.NbPlatter = NbPlatter;
        this.DescriptionPlatter = DescriptionPlatter;
    }

    public String getIdPlatter() {
        return IdPlatter;
    }

    public String getNamePlatter() {
        return NamePlatter;
    }

    public String getImagePlatter() {
        return ImagePlatter;
    }

    public String getIngredient() {
        return ingredient;
    }

    public float getPricePlatter() {
        return PricePlatter;
    }

    public int getNbPlatter() {
        return NbPlatter;
    }

    public String getDescriptionPlatter() {
        return DescriptionPlatter;
    }

    public void setIdPlatter(String IdPlatter) {
        this.IdPlatter = IdPlatter;
    }

    public void setNamePlatter(String NamePlatter) {
        this.NamePlatter = NamePlatter;
    }

    public void setImagePlatter(String ImagePlatter) {
        this.ImagePlatter = ImagePlatter;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public void setPricePlatter(float PricePlatter) {
        this.PricePlatter = PricePlatter;
    }

    public void setNbPlatter(int NbPlatter) {
        this.NbPlatter = NbPlatter;
    }

    public void setDescriptionPlatter(String DescriptionPlatter) {
        this.DescriptionPlatter = DescriptionPlatter;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.IdPlatter);
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
        final Platter other = (Platter) obj;
        if (!Objects.equals(this.IdPlatter, other.IdPlatter)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Platter{" + "IdPlatter=" + IdPlatter + ", NamePlatter=" + NamePlatter + ", ImagePlatter=" + ImagePlatter + ", ingredient=" + ingredient + ", PricePlatter=" + PricePlatter + ", NbPlatter=" + NbPlatter + ", DescriptionPlatter=" + DescriptionPlatter + '}';
    }
    
    
}
