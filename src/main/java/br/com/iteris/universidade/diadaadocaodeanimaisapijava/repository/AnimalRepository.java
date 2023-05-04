package br.com.iteris.universidade.diadaadocaodeanimaisapijava.repository;

import br.com.iteris.universidade.diadaadocaodeanimaisapijava.domain.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer> {
    @Query(
            nativeQuery = true,
            value = "SELECT * FROM Animal WHERE name = :name"
    )
    List<Animal> findByNameContaining(String name);

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM Animal WHERE (:name IS NULL OR name = :name) AND (:species IS NULL OR species = :species)"
    )
    List<Animal> listarComFiltroNativo(@Param("name") String name, @Param("species") String species);
}
