package edu.es.eoi.projecteoi.repository;

import edu.es.eoi.projecteoi.entity.Articulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticuloRepository extends JpaRepository<Articulo, Integer> {

}
