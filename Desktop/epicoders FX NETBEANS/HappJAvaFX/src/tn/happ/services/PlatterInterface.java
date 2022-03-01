package tn.happ.services;

import tn.happ.Model.Platter;

import java.util.List;

public interface PlatterInterface {
    public void addPlatter( Platter p);
    public List<Platter> displayAllPlatter();
    public void deletePlatter(Platter p);
    public void UpdatePlatter(Platter p);

}
