package com.example.springmavensql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class DataController {

    private final DataRepository dataRepository;

    @Autowired
    public DataController(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateData(@PathVariable Long id, @RequestBody Data updatedData) {
        Optional<Data> optionalData = dataRepository.findById(id);

        if (optionalData.isPresent()) {
            Data existingData = optionalData.get();
            existingData.setName(updatedData.getName());

            dataRepository.save(existingData);

            return ResponseEntity.ok("Data updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteData(@PathVariable Long id) {

        dataRepository.deleteById(id);

        return ResponseEntity.ok("Entry deleted");
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<String> getData(@PathVariable Long id) {

        Data data = dataRepository.getReferenceById(id);

        return ResponseEntity.ok(data.getName());
    }

    @PostMapping("/post")
    public ResponseEntity<String> postData(@RequestBody Data data) {

        dataRepository.save(data);

        return ResponseEntity.ok("Data posted successfully");
    }
}
