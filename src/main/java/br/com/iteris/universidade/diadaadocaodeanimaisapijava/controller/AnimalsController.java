package br.com.iteris.universidade.diadaadocaodeanimaisapijava.controller;

import br.com.iteris.universidade.diadaadocaodeanimaisapijava.domain.dto.AnimalCreateRequest;
import br.com.iteris.universidade.diadaadocaodeanimaisapijava.domain.dto.AnimalFilterRequest;
import br.com.iteris.universidade.diadaadocaodeanimaisapijava.domain.dto.AnimalResponse;
import br.com.iteris.universidade.diadaadocaodeanimaisapijava.domain.dto.AnimalUpdateRequest;
import br.com.iteris.universidade.diadaadocaodeanimaisapijava.service.AnimalsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AnimalsController {
    private final AnimalsService service;

    public AnimalsController(final AnimalsService service) {
        this.service = service;
    }

    @PostMapping(value = "api/animals")
    public ResponseEntity<AnimalResponse> criarAnimal(@RequestBody @Valid AnimalCreateRequest animal) {
        var animalResponse = service.criarAnimal(animal);
        return ResponseEntity.ok(animalResponse);
    }

    @GetMapping(value = "api/animals")
    public ResponseEntity<List<AnimalResponse>> listar(AnimalFilterRequest filter) {
        var listaDeAnimais = service.listar(filter);
        return ResponseEntity.ok(listaDeAnimais);
    }

    @GetMapping(value = "api/animals/{id}")
    public ResponseEntity<AnimalResponse> buscarPorId(@PathVariable Integer id) {
        var animalResponse = service.buscarPorId(id);
        return ResponseEntity.ok(animalResponse);
    }

    @GetMapping(value = "api/animals/name/{name}")
    public ResponseEntity<List<AnimalResponse>> buscarPorNome(@PathVariable String name) {
        var animalResponse = service.buscarPorNome(name);
        return
                ResponseEntity.ok(animalResponse);
    }

    @PutMapping(value = "api/animals/{id}")
    public ResponseEntity<AnimalResponse> atualizarAnimal(@PathVariable Integer id, @RequestBody @Valid AnimalUpdateRequest animalUpdateRequest) {
        var animal = service.atualizarAnimal(id, animalUpdateRequest);
        return ResponseEntity.ok(animal);
    }

    @DeleteMapping(value = "api/animals/{id}")
    public ResponseEntity<AnimalResponse> deletarAnimal(@PathVariable Integer id) {
        var animal = service.deletarAnimal(id);
        return ResponseEntity.ok(animal);
    }
}
