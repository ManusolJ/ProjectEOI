package edu.es.eoi.projecteoi.service;

import edu.es.eoi.projecteoi.DTOs.articuloDTOs.ArticuloDTO;
import edu.es.eoi.projecteoi.DTOs.articuloDTOs.ArticuloPedidoDTO;
import edu.es.eoi.projecteoi.DTOs.pedidoDTOs.PedidoDTO;
import edu.es.eoi.projecteoi.DTOs.usuarioDTOs.UsuarioDTO;
import edu.es.eoi.projecteoi.entity.Articulo;
import edu.es.eoi.projecteoi.entity.ArticuloPedido;
import edu.es.eoi.projecteoi.entity.Pedido;
import edu.es.eoi.projecteoi.entity.Usuario;
import edu.es.eoi.projecteoi.repository.ArticuloPedidoRepository;
import edu.es.eoi.projecteoi.repository.ArticuloRepository;
import edu.es.eoi.projecteoi.repository.PedidoRepository;
import edu.es.eoi.projecteoi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportServiceImpl {

    private static final int PAGE_SIZE_ARTICULOS = 10;

    private static final int PAGE_SIZE_PEDIDOS = 3;

    @Autowired
    ArticuloRepository articuloRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    ArticuloPedidoRepository articuloPedidoRepository;

    public List<ArticuloDTO> findArticulosMasVendidos() {

        Pageable paginado = PageRequest.of(0,PAGE_SIZE_ARTICULOS);

        List<Articulo> articulos = articuloRepository.findArticulosMasVendidos(paginado);

        List<ArticuloDTO> articulosDTOs = new ArrayList<>();

        for (Articulo articulo : articulos) {
            ArticuloDTO articuloDTO = new ArticuloDTO();
            articuloDTO.setIdArticulo(articulo.getIdArticulo());
            articuloDTO.setNombreArticulo(articulo.getNombreArticulo());
            articuloDTO.setPrecio(articulo.getPrecio());
            articuloDTO.setStock(articulo.getStock());
            articulosDTOs.add(articuloDTO);
        }

        return articulosDTOs;
    }

    //PAIN
    public List<PedidoDTO> findMejoresPedidos() {
        Pageable paginado = PageRequest.of(0, PAGE_SIZE_PEDIDOS);

        List<Pedido> pedidos = pedidoRepository.findTop3PedidosMasCaros(paginado);
        List<PedidoDTO> pedidosDTOs = new ArrayList<>();

        for (Pedido pedido : pedidos) {
            PedidoDTO pedidoDTO = new PedidoDTO();
            pedidoDTO.setIdPedido(pedido.getIdPedido());
            pedidoDTO.setNombrePedido(pedido.getNombrePedido());
            pedidoDTO.setFechaPedido(pedido.getFechaPedido());

            List<ArticuloPedidoDTO> articulosDTO = new ArrayList<>();
            for (ArticuloPedido articuloPedido : pedido.getArticulosEnPedido()) {
                ArticuloPedidoDTO articuloDTO = new ArticuloPedidoDTO();
                articuloDTO.setIdArticulo(articuloPedido.getArticulo().getIdArticulo());
                articuloDTO.setCantidad(articuloPedido.getCantidadPedida());
                articulosDTO.add(articuloDTO);
            }
            pedidoDTO.setArticulos(articulosDTO);
            pedidosDTOs.add(pedidoDTO);
        }

        return pedidosDTOs;

    }

    public List<UsuarioDTO> findUsuariosPorTotalGastado(){

        List<Usuario> usuarios = usuarioRepository.ListarUsuariosPorTotalGastado();

        List<UsuarioDTO> usuariosDTOs = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            UsuarioDTO usuarioDTO = new UsuarioDTO();
            usuarioDTO.setIdUsuario(usuario.getIdUsuario());
            usuarioDTO.setNombreUsuario(usuario.getNombreUsuario());
            usuarioDTO.setPassword(usuario.getPassword());
            usuariosDTOs.add(usuarioDTO);
        }

        return usuariosDTOs;
    }
}
