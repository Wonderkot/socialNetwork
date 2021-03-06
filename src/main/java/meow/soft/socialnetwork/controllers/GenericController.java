package meow.soft.socialnetwork.controllers;

import meow.soft.socialnetwork.model.GenericEntity;
import meow.soft.socialnetwork.repo.GenericRepository;
import meow.soft.socialnetwork.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

public abstract class GenericController<T extends GenericEntity<T>> {

    private final GenericService<T> service;

    public GenericController(GenericRepository<T> repository) {
        service = new GenericService<>(repository) {
        };
    }

    @GetMapping("")
    public ResponseEntity<Page<T>> getPage(Pageable pageable) {
        return ResponseEntity.ok(service.getPage(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> getOne(@PathVariable UUID id) {
        return ResponseEntity.ok(service.get(id));
    }

    @PutMapping("")
    public ResponseEntity<T> update(@RequestBody T updated) {
        return ResponseEntity.ok(service.update(updated));
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody T created) {
        //return ResponseEntity.ok(service.create(created));
        service.create(created);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.ok("Ok");
    }

    protected GenericService<T> getService() {
        return service;
    }
}

