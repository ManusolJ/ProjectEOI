package edu.es.eoi.projecteoi.service;

import edu.es.eoi.projecteoi.DTOs.articuloDTOs.ArticuloCreateDTO;
import edu.es.eoi.projecteoi.DTOs.articuloDTOs.ArticuloDTO;
import edu.es.eoi.projecteoi.entity.Articulo;
import edu.es.eoi.projecteoi.repository.ArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticuloService {

    @Autowired
    private ArticuloRepository articuloRepository;

    public List<ArticuloDTO> getAllArticulosByNombre(String nombre) {

        List<Articulo> articulos = articuloRepository.findAll();

        List<ArticuloDTO> articuloDTOs = new ArrayList<>();

        for (Articulo articulo : articulos) {
            if(articulo.getNombreArticulo().toLowerCase().contains(nombre.toLowerCase())) {
                ArticuloDTO articuloDTO = new ArticuloDTO();

                articuloDTO.setIdArticulo(articulo.getIdArticulo());
                articuloDTO.setNombreArticulo(articulo.getNombreArticulo());
                articuloDTO.setPrecio(articulo.getPrecio());
                articuloDTO.setStock(articulo.getStock());

                articuloDTOs.add(articuloDTO);
            }
        }

        return articuloDTOs;
    }

    public ArticuloDTO getArticuloById(String id) {

        int idArticulo;

        try {
            idArticulo = Integer.parseInt(id);
        }catch (NumberFormatException e) {
            return null;
        }

        Articulo articulo = articuloRepository.findById(idArticulo).get();

        ArticuloDTO articuloDTO = new ArticuloDTO();

        articuloDTO.setIdArticulo(idArticulo);
        articuloDTO.setNombreArticulo(articulo.getNombreArticulo());
        articuloDTO.setPrecio(articulo.getPrecio());
        articuloDTO.setStock(articulo.getStock());

        return articuloDTO;
    }

    public void createArticulo(ArticuloCreateDTO articuloDTO) {

        Articulo articulo = new Articulo();

        articulo.setNombreArticulo(articuloDTO.getNombreArticulo());
        articulo.setPrecio(articuloDTO.getPrecio());
        articulo.setStock(articuloDTO.getStock());

        articuloRepository.save(articulo);
    }

    public void updateArticulo(ArticuloDTO articuloDTO) {

        Articulo articulo = articuloRepository.findById(articuloDTO.getIdArticulo()).get();

        articulo.setNombreArticulo(articuloDTO.getNombreArticulo());
        articulo.setPrecio(articuloDTO.getPrecio());
        articulo.setStock(articuloDTO.getStock());

        articuloRepository.save(articulo);
    }
}
