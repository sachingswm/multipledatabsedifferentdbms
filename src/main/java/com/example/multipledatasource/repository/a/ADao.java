package com.example.multipledatasource.repository.a;

import com.example.multipledatasource.entity.a.A;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ADao extends JpaRepository<A,Integer> {

}