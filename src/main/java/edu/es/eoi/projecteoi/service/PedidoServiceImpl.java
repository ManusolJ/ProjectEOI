package edu.es.eoi.projecteoi.service;

import edu.es.eoi.projecteoi.DTOs.articuloDTOs.ArticuloPedidoDTO;
import edu.es.eoi.projecteoi.DTOs.pedidoDTOs.PedidoCreateDTO;
import edu.es.eoi.projecteoi.DTOs.pedidoDTOs.PedidoDTO;
import edu.es.eoi.projecteoi.entity.Articulo;
import edu.es.eoi.projecteoi.entity.ArticuloPedido;
import edu.es.eoi.projecteoi.entity.Pedido;
import edu.es.eoi.projecteoi.repository.ArticuloPedidoRepository;
import edu.es.eoi.projecteoi.repository.ArticuloRepository;
import edu.es.eoi.projecteoi.repository.PedidoRepository;
import edu.es.eoi.projecteoi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoServiceImpl {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ArticuloPedidoRepository articuloPedidoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ArticuloRepository articuloRepository;


    public List<PedidoDTO> getAllPedidosByNombre(String nombre) {

        List<Pedido> pedidos = pedidoRepository.findAll();

        List<PedidoDTO> pedidoDTOs = new ArrayList<>();

        List<ArticuloPedido> articulosPedidos = articuloPedidoRepository.findAll();



        for (Pedido pedido : pedidos) {
            if(pedido.getNombrePedido().toLowerCase().contains(nombre.toLowerCase())) {
                PedidoDTO pedidoDTO = new PedidoDTO();
                pedidoDTO.setIdPedido(pedido.getIdPedido());
                pedidoDTO.setNombrePedido(pedido.getNombrePedido());
                pedidoDTO.setFechaPedido(pedido.getFechaPedido());

                List<ArticuloPedidoDTO> articulosPedidoDTOs = new ArrayList<>();
                for (ArticuloPedido articuloPedido : articulosPedidos) {
                    if (articuloPedido.getPedido().getIdPedido() == pedido.getIdPedido()) {
                        ArticuloPedidoDTO articuloPedidoDTO = new ArticuloPedidoDTO();
                        articuloPedidoDTO.setIdArticulo(articuloPedido.getArticulo().getIdArticulo());
                        articuloPedidoDTO.setCantidad(articuloPedido.getCantidadPedida());
                        articulosPedidoDTOs.add(articuloPedidoDTO);
                    }
                }
                pedidoDTO.setArticulos(articulosPedidoDTOs);
                pedidoDTOs.add(pedidoDTO);
            }
        }

        return pedidoDTOs;
    }

    public PedidoDTO getPedidoById(String id) {

        int idPedido;

        try {
            idPedido = Integer.parseInt(id);
        }catch (NumberFormatException e) {
            return null;
        }

        Pedido pedido = pedidoRepository.findById(idPedido).get();

        PedidoDTO pedidoDTO = new PedidoDTO();

        List<ArticuloPedidoDTO> articulosPedidos = new ArrayList<>();

        pedidoDTO.setIdPedido(pedido.getIdPedido());
        pedidoDTO.setNombrePedido(pedido.getNombrePedido());
        pedidoDTO.setFechaPedido(pedido.getFechaPedido());
        for (ArticuloPedido articuloPedido : articuloPedidoRepository.findAll()) {
            if (articuloPedido.getPedido().getIdPedido() == idPedido) {
                ArticuloPedidoDTO articuloPedidoDTO = new ArticuloPedidoDTO();
                articuloPedidoDTO.setIdArticulo(articuloPedido.getArticulo().getIdArticulo());
                articuloPedidoDTO.setCantidad(articuloPedido.getCantidadPedida());
                articulosPedidos.add(articuloPedidoDTO);
            }
        }
        pedidoDTO.setArticulos(articulosPedidos);

        return pedidoDTO;
    }

    //FIXME NO FUNCIONA LA CREACION NI EL UPDATE
    public void createPedido(PedidoCreateDTO pedidoDTO) {

        Pedido pedido = new Pedido();

        pedido.setUsuario(usuarioRepository.findById(pedidoDTO.getIdUsuario()).get());
        pedido.setNombrePedido(pedidoDTO.getNombrePedido());
        pedido.setFechaPedido(pedidoDTO.getFechaPedido());

        List<ArticuloPedido> articuloPedidos = new ArrayList<>();

        for (ArticuloPedidoDTO articuloPedidoDTO : pedidoDTO.getArticulos()) {
            Articulo articulo = articuloRepository.findById(articuloPedidoDTO.getIdArticulo()).get();
            ArticuloPedido articuloPedido = new ArticuloPedido();
            articuloPedido.setArticulo(articulo);
            articuloPedido.setPedido(pedido);
            articuloPedido.setCantidadPedida(articuloPedidoDTO.getCantidad());
            articulo.setStock(articulo.getStock() - articuloPedidoDTO.getCantidad());
            articuloRepository.save(articulo);
            articuloPedidos.add(articuloPedido);
        }

        pedido.setArticulosEnPedido(articuloPedidos);

        pedidoRepository.save(pedido);
    }

    public void updatePedido(PedidoDTO pedidoDTO){
        //TODO PENSAR ESTO MEJOR
        //uff... Que pocho esta esto.

        Pedido pedido = pedidoRepository.findById(pedidoDTO.getIdPedido()).get();

        pedido.setNombrePedido(pedidoDTO.getNombrePedido());
        pedido.setFechaPedido(pedidoDTO.getFechaPedido());

        List<ArticuloPedido> articuloPedidos = new ArrayList<>();

        for(ArticuloPedido articuloPedido: pedido.getArticulosEnPedido()){
            Articulo articulo = articuloRepository.findById(articuloPedido.getArticulo().getIdArticulo()).get();
            articulo.setStock(articulo.getStock() + articuloPedido.getCantidadPedida());
            articuloRepository.save(articulo);
        }

        articuloPedidoRepository.deleteById(pedido.getIdPedido());

        for (ArticuloPedidoDTO articuloPedidoDTO : pedidoDTO.getArticulos()) {
            Articulo articulo = articuloRepository.findById(articuloPedidoDTO.getIdArticulo()).get();
            ArticuloPedido articuloPedido = new ArticuloPedido();
            articuloPedido.setArticulo(articulo);
            articuloPedido.setPedido(pedido);
            articuloPedido.setCantidadPedida(articuloPedidoDTO.getCantidad());
            articulo.setStock(articulo.getStock() - articuloPedidoDTO.getCantidad());
            articuloRepository.save(articulo);
            articuloPedidos.add(articuloPedido);
        }

        pedido.setArticulosEnPedido(articuloPedidos);

        pedidoRepository.save(pedido);
    }

    public void deletePedido(String idPedido){

        Pedido pedido = pedidoRepository.findById(Integer.parseInt(idPedido)).get();

        for(ArticuloPedido articuloPedido: pedido.getArticulosEnPedido()){
            Articulo articulo = articuloRepository.findById(articuloPedido.getArticulo().getIdArticulo()).get();
            articulo.setStock(articulo.getStock() + articuloPedido.getCantidadPedida());
            articuloRepository.save(articulo);
        }

        articuloPedidoRepository.deleteById(pedido.getIdPedido());

        pedidoRepository.delete(pedido);
    }
}