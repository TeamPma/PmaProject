package com.dogAdopter.database.dao.impl;

import org.springframework.stereotype.Repository;

import com.dogAdopter.database.dao.VeterinaryClinicDao;
import com.dogAdopter.entity.VeterinaryClinic;
import com.dogAdopter.util.CustomHibernateDaoSupport;

import java.util.ArrayList;
import java.util.List;

@Repository("veterinaryClinicDao")
public class VeterinaryClinicDaoImpl extends CustomHibernateDaoSupport implements VeterinaryClinicDao {

    public void save(VeterinaryClinic clinic) {
        getHibernateTemplate().save(clinic);
    }

    public void update(VeterinaryClinic clinic) {
        getHibernateTemplate().update(clinic);
    }

    public void delete(VeterinaryClinic clinic) {
        getHibernateTemplate().delete(clinic);
    }

    @Override
    public VeterinaryClinic findVeterinaryById(Integer id) {
        Object[] params = {id};
        String[] paramsS = {"id"};
        List<VeterinaryClinic> list = getHibernateTemplate().findByNamedQueryAndNamedParam(VeterinaryClinic.FIND_VETERINARY_WITH_ID, paramsS, params);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public ArrayList<VeterinaryClinic> findAllVeterinary() {
        List<VeterinaryClinic> list = getHibernateTemplate().findByNamedQuery(VeterinaryClinic.FIND_ALL_VETERINARY);
        return (ArrayList<VeterinaryClinic>) list;
    }

}
