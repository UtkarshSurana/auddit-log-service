package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.entity.Data;


public interface DataRepository  extends JpaRepository<Data, Long>{

}
