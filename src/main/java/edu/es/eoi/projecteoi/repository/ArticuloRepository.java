package edu.es.eoi.projecteoi.repository;

import edu.es.eoi.projecteoi.entity.Articulo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticuloRepository extends JpaRepository<Articulo, Integer> {

    @Query("SELECT a FROM Articulo a JOIN ArticuloPedido ap ON a.idArticulo = ap.articulo.idArticulo GROUP BY a.idArticulo ORDER BY SUM(ap.cantidadPedida) DESC")
    List<Articulo> findArticulosMasVendidos(Pageable pageable);
}
