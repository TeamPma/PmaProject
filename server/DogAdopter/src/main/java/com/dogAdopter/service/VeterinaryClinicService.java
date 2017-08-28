package com.dogAdopter.service;

import com.dogAdopter.entity.VeterinaryClinic;

import java.util.ArrayList;

public interface VeterinaryClinicService {
    void save(VeterinaryClinic clinic);

    void update(VeterinaryClinic clinic);

    void delete(VeterinaryClinic clinic);

    VeterinaryClinic findVeterinaryById(Integer id);

    ArrayList<VeterinaryClinic> findAllVeterinary();


}
