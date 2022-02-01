package com.example.assignment1.service;

import com.example.assignment1.model.Salt;
import com.example.assignment1.repository.SaltRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SaltService {

    @Autowired
    SaltRepository saltRepository;

    public Salt newSalt(Salt salt){
        return saltRepository.save(salt);
    }

    public List<Salt> addSalts(List<Salt> salt) {
        return saltRepository.saveAll(salt);
    }

    public List<Salt> getSalts() {
        return saltRepository.findAll();
    }

    public Salt getSaltById(String id) {
        return saltRepository.findByIdAndDeletedFalse(id);
    }

    public Salt getSaltBySaltNameAndControl(String saltName, boolean control){
        return saltRepository.findBySaltNameAndControl(saltName, control);
    }

    public String deleteSalt(String id) {
        saltRepository.delete(getSaltById(id));
        return "salt of " + id +"removed";
    }

    public Salt updateSalt(Salt salt) {
        Salt existingProduct = saltRepository.findById(salt.getId()).orElse(null);
        assert existingProduct != null;
        existingProduct.setSaltName(salt.getSaltName());
        existingProduct.setControlStartDate(salt.getControlStartDate());
        existingProduct.setControlEndDate(salt.getControlEndDate());
        existingProduct.setAilment(salt.getAilment());
        existingProduct.setApiGroup(salt.getApiGroup());
        existingProduct.setUrl(salt.getUrl());
        return saltRepository.save(existingProduct);
    }

    public Page<Salt> listAll(){
        Pageable pageable = PageRequest.of(0, 10);
        return saltRepository.findAll(pageable);
    }

    public List<Salt> findAll() {
        return saltRepository.findAll();
    }

    /*public List<Salt> listAll(String saltName) {
        if (saltName != null) {
            return saltRepository.search(saltName);
        }
        return saltRepository.findAll();
    }*/



}
