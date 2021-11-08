package com.example.multipledatasource.service;

import com.example.multipledatasource.entity.a.A;
import com.example.multipledatasource.repository.a.ADao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AService {
    @Autowired
    private ADao aDao;

    public List<A> getAllA()
    {
        return aDao.findAll();
    }
}