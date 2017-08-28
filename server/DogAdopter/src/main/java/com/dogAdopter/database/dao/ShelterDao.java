package com.dogAdopter.database.dao;

import java.util.ArrayList;
import java.util.List;

import com.dogAdopter.entity.Shelter;

public interface ShelterDao {
    void save(Shelter shelter);

    void update(Shelter shelter);

    void delete(Shelter shelter);

    ArrayList<Shelter> getAll();

    Shelter getShelterById(Integer id);
}
