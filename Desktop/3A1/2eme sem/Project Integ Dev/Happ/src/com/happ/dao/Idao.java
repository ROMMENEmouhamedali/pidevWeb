/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.happ.dao;

import java.util.List;

/**
 *
 * @author ASUS
 */
public interface Idao <T>{
     public void insert(T o);
    public void delete(T o);
    public List<T> displayAll();
    public T displayById(int IdAC);
    
    public boolean update(T os);
}
