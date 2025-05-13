package com.app.controller;

import com.app.entity.Data;
import com.app.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/data")
public class CrudController {

    @Autowired
    private CrudService crudService;

    @PostMapping
    public String createData(@RequestBody Data data) {
        crudService.createData(data);
        return "Data created successfully";
    }

    @PutMapping
    public String updateData(@RequestBody Data data) {
        crudService.updateData(data);
        return "Data updated successfully";
    }

    @DeleteMapping("/{id}")
    public String deleteData(@PathVariable Long id) {
        crudService.deleteData(id);
        return "Data deleted successfully";
    }
}
