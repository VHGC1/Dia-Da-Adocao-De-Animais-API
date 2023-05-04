package br.com.iteris.universidade.diadaadocaodeanimaisapijava.service;

import br.com.iteris.universidade.diadaadocaodeanimaisapijava.domain.dto.AnimalCreateRequest;
import br.com.iteris.universidade.diadaadocaodeanimaisapijava.domain.dto.AnimalFilterRequest;
import br.com.iteris.universidade.diadaadocaodeanimaisapijava.domain.dto.AnimalResponse;
import br.com.iteris.universidade.diadaadocaodeanimaisapijava.domain.dto.AnimalUpdateRequest;
import br.com.iteris.universidade.diadaadocaodeanimaisapijava.domain.entity.Animal;
import br.com.iteris.universidade.diadaadocaodeanimaisapijava.repository.AnimalRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnimalsService {
    private final AnimalRepository repository;

    public AnimalsService(final AnimalRepository repository, AnimalRepository repository1) {
        this.repository = repository1;
    }

    public AnimalResponse criarAnimal(AnimalCreateRequest animalCreateRequest) {
        List<String> especiesPermitidas = List.of("Cachorro", "Gato", "Coelho", "Capivara");

        String speciesUpper = firstLetterUpperCase(animalCreateRequest.getSpecies());

        if (!especiesPermitidas.contains(speciesUpper)) {
            throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, "A especie deve ser cachorro, gato, coelho ou capivara");
        }

        var novoAnimal = new Animal();

        novoAnimal.setName(animalCreateRequest.getName());
        novoAnimal.setAge(animalCreateRequest.getAge());
        novoAnimal.setSpecies(speciesUpper);
        novoAnimal.setBirthDate(animalCreateRequest.getBirthDate());
        novoAnimal.setCuteness(animalCreateRequest.getCuteness());
        novoAnimal.setKindness(animalCreateRequest.getKindness());
        novoAnimal.setEmail(animalCreateRequest.getEmail());

        var animalSalvo = repository.save(novoAnimal);

        return new AnimalResponse(
                animalSalvo.getId(),
                animalSalvo.getName(),
                animalSalvo.getAge(),
                animalSalvo.getSpecies(),
                animalSalvo.getBirthDate(),
                animalSalvo.getCuteness(),
                animalSalvo.getKindness(),
                animalSalvo.getEmail()
        );
    }

    public String firstLetterUpperCase(String word) {
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }

    public List<AnimalResponse> listar(AnimalFilterRequest filter) {
        var resultado = repository.listarComFiltroNativo(filter.getName(), filter.getSpecies());

        return resultado.stream().map(animal -> new AnimalResponse(
                animal.getId(),
                animal.getName(),
                animal.getAge(),
                animal.getSpecies(),
                animal.getBirthDate(),
                animal.getCuteness(),
                animal.getKindness(),
                animal.getEmail()
        )).collect(Collectors.toList());
    }

    public AnimalResponse buscarPorId(Integer id) {
        var animalEncontrado = repository.findById(id);

        var animalSalvo = animalEncontrado.get();

        return new AnimalResponse(
                animalSalvo.getId(),
                animalSalvo.getName(),
                animalSalvo.getAge(),
                animalSalvo.getSpecies(),
                animalSalvo.getBirthDate(),
                animalSalvo.getCuteness(),
                animalSalvo.getKindness(),
                animalSalvo.getEmail()
        );
    }

    public List<AnimalResponse> buscarPorNome(String nome) {
        var animalEncontrado = repository.findByNameContaining(nome);

        if (animalEncontrado.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Animal não encontrado");
        }

        return animalEncontrado.stream().map(animal -> new AnimalResponse(
                animal.getId(),
                animal.getName(),
                animal.getAge(),
                animal.getSpecies(),
                animal.getBirthDate(),
                animal.getCuteness(),
                animal.getKindness(),
                animal.getEmail()
        )).collect(Collectors.toList());
    }

    public AnimalResponse atualizarAnimal(Integer id, AnimalUpdateRequest animalUpdateRequest) {
        var animalEncontrado = repository.findById(id);

        if (animalEncontrado.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Animal não encontrado");
        }

        var animal = animalEncontrado.get();

        animal.setEmail(animalUpdateRequest.getEmail());

        var animalSalvo = repository.save(animal);

        return new AnimalResponse(
                animalSalvo.getId(),
                animalSalvo.getName(),
                animalSalvo.getAge(),
                animalSalvo.getSpecies(),
                animalSalvo.getBirthDate(),
                animalSalvo.getCuteness(),
                animalSalvo.getKindness(),
                animalSalvo.getEmail()
        );
    }

    public AnimalResponse deletarAnimal(Integer id) {
        var animalEncontrado = repository.findById(id);

        if (animalEncontrado.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Animal não encontrado");
        }

        var animal = animalEncontrado.get();
        repository.delete(animal);

        return new AnimalResponse(
                animal.getId(),
                animal.getName(),
                animal.getAge(),
                animal.getSpecies(),
                animal.getBirthDate(),
                animal.getCuteness(),
                animal.getKindness(),
                animal.getEmail()
        );
    }
}
