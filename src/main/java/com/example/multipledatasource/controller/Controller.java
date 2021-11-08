package com.example.multipledatasource.controller;


import com.example.multipledatasource.entity.a.A;
import com.example.multipledatasource.service.AService;
import com.example.multipledatasource.service.BService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {
    @Autowired
    private AService aService;

    @Autowired
    private BService bService;

    @PostMapping("/migrate")
    public void migrate()
    {
        List<A> users=aService.getAllA();
        for(A a:users)
        {
            bService.saveB(a);
        }
    }
}
