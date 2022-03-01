package tn.happ.Model;


import java.util.Objects;

public class Platter {
    private int IdPlatter;
    private String NamePlatter;
    private String ImagePlatter;
    private String ingredient;
    private float PricePlatter;
    private int NbPlatter;
    private String DescriptionPlatter;
    private String TypePlatter;
  //  private int ID_USER;
    private User user;

    public Platter() {
    }


    public Platter(int idPlatter, String namePlatter, String imagePlatter, String ingredient, float pricePlatter, int nbPlatter, String descriptionPlatter, String typePlatter, User user) {
        IdPlatter = idPlatter;
        NamePlatter = namePlatter;
        ImagePlatter = imagePlatter;
        this.ingredient = ingredient;
        PricePlatter = pricePlatter;
        NbPlatter = nbPlatter;
        DescriptionPlatter = descriptionPlatter;
        TypePlatter = typePlatter;
        this.user = user;
    }

    public Platter(String namePlatter, String imagePlatter, String ingredient, float pricePlatter, int nbPlatter, String descriptionPlatter, String typePlatter, User user) {
        NamePlatter = namePlatter;
        ImagePlatter = imagePlatter;
        this.ingredient = ingredient;
        PricePlatter = pricePlatter;
        NbPlatter = nbPlatter;
        DescriptionPlatter = descriptionPlatter;
        TypePlatter = typePlatter;
        this.user = user;
    }

    public int getIdPlatter() {
        return IdPlatter;
    }

    public void setIdPlatter(int idPlatter) {
        IdPlatter = idPlatter;
    }

    public String getNamePlatter() {
        return NamePlatter;
    }

    public void setNamePlatter(String namePlatter) {
        NamePlatter = namePlatter;
    }

    public String getImagePlatter() {
        return ImagePlatter;
    }

    public void setImagePlatter(String imagePlatter) {
        ImagePlatter = imagePlatter;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public float getPricePlatter() {
        return PricePlatter;
    }

    public void setPricePlatter(float pricePlatter) {
        PricePlatter = pricePlatter;
    }

    public int getNbPlatter() {
        return NbPlatter;
    }

    public void setNbPlatter(int nbPlatter) {
        NbPlatter = nbPlatter;
    }

    public String getDescriptionPlatter() {
        return DescriptionPlatter;
    }

    public void setDescriptionPlatter(String descriptionPlatter) {
        DescriptionPlatter = descriptionPlatter;
    }

    public String getTypePlatter() {
        return TypePlatter;
    }

    public void setTypePlatter(String typePlatter) {
        TypePlatter = typePlatter;
    }

    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Platter platter = (Platter) o;
        return IdPlatter == platter.IdPlatter;
    }

    @Override
    public int hashCode() {
        return Objects.hash(IdPlatter);
    }

    @Override
    public String toString() {
        return "Platter{" +
                "IdPlatter=" + IdPlatter +
                ", NamePlatter='" + NamePlatter + '\'' +
                ", ImagePlatter='" + ImagePlatter + '\'' +
                ", ingredient='" + ingredient + '\'' +
                ", PricePlatter=" + PricePlatter +
                ", NbPlatter=" + NbPlatter +
                ", DescriptionPlatter='" + DescriptionPlatter + '\'' +
                ", TypePlatter='" + TypePlatter + '\'' +
                '}';
    }



}

