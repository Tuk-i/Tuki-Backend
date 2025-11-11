package com.Tuki.Tuki_Backend_Provisional.Servicios;

import com.Tuki.Tuki_Backend_Provisional.Entidades.DTOs.CarritoDTOs.CarritoDTO;
import com.Tuki.Tuki_Backend_Provisional.Entidades.DTOs.CarritoDTOs.ItemCarritoDTO;
import com.Tuki.Tuki_Backend_Provisional.Entidades.DTOs.PedidoDTOs.PedidoRespuestaDTO;
import com.Tuki.Tuki_Backend_Provisional.Entidades.DTOs.PedidoDTOs.PedidoUpdateDTO;
import com.Tuki.Tuki_Backend_Provisional.Entidades.DetallePedido;
import com.Tuki.Tuki_Backend_Provisional.Entidades.Enum.Estado;
import com.Tuki.Tuki_Backend_Provisional.Entidades.Mappers.PedidoMapper;
import com.Tuki.Tuki_Backend_Provisional.Entidades.Pedido;
import com.Tuki.Tuki_Backend_Provisional.Entidades.Usuario;
import com.Tuki.Tuki_Backend_Provisional.Repositorys.PedidoRepository;
import com.Tuki.Tuki_Backend_Provisional.Repositorys.UsuarioRepository;
import com.Tuki.Tuki_Backend_Provisional.Servicios.IntefacesServicios.DetallePedidoService;
import com.Tuki.Tuki_Backend_Provisional.Servicios.IntefacesServicios.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PedidoServiceIMP implements PedidoService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    DetallePedidoService detallePedidoService;

    @Autowired
    PedidoMapper pedidoMapper;

    @Override
    public List<PedidoRespuestaDTO> listarTodos() {
        List<Pedido> pedidos = pedidoRepository.findAllByOrderByIdAsc();

        if (pedidos.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay pedidos registrados");
        }

        return pedidos.stream()
                .map(pedidoMapper::crearPedidoDTO)
                .toList();
    }

    @Override
    public List<PedidoRespuestaDTO> listarPedidosPorUsuario(Long usuarioId) {
        List<Pedido> pedidos = pedidoRepository.findByUsuarioIdOrderByIdAsc(usuarioId);

        if (pedidos.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontraron pedidos para el usuario");
        }

        return pedidos.stream()
                .map(pedidoMapper::crearPedidoDTO)
                .toList();
    }


    @Override
    public List<PedidoRespuestaDTO> listarPorEstado(Estado estado) {
        List<Pedido> pedidos = pedidoRepository.findByEstadoOrderByIdAsc(estado);

        if (pedidos.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay pedidos con estado " + estado);
        }

        return pedidos.stream()
                .map(pedidoMapper::crearPedidoDTO)
                .toList();
    }


    @Override
    public ResponseEntity<?> crearPedidoDesdeCarrito(CarritoDTO carritoDTO) {
        Usuario usuario = usuarioRepository.findById(carritoDTO.usuarioId())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        Pedido pedido = new Pedido();
        pedido.setFecha(LocalDate.now());
        pedido.setEstado(Estado.PENDIENTE);

        for (ItemCarritoDTO item: carritoDTO.items()){
            DetallePedido detalle = detallePedidoService.crearDetalle(item.productoId(),item.cantidad());
            pedido.agregarDetalle(detalle);
        }

        pedido.setUsuario(usuario);
        pedido.calcularTotal();
        pedidoRepository.save(pedido);
        PedidoRespuestaDTO dto = pedidoMapper.crearPedidoDTO(pedido);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(dto);
    }

    @Override
    public ResponseEntity<?> actualizarEstadoPedido(PedidoUpdateDTO dto) {
        Pedido pedido = pedidoRepository.findById(dto.pedidoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido no encontrado"));

        List<String> valoresValidos = Arrays.stream(Estado.values())
                .map(Enum::name)
                .toList();

        if (!valoresValidos.contains(dto.nuevoEstado())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Estado invalido | PENDIENTE | CONFIRMADO | CANCELADO | TERMINADO");
        }

        pedidoMapper.actualizarEstado(pedido, dto);
        if (pedido.getEstado()== Estado.CANCELADO){
            recuperarStock(pedido);
        }
        pedidoRepository.save(pedido);


        PedidoRespuestaDTO respuesta = pedidoMapper.crearPedidoDTO(pedido);
        return ResponseEntity.ok(respuesta);
    }

    private void recuperarStock(Pedido pedido){
        List<DetallePedido> detalles = pedido.getDetalles();
        for (DetallePedido detallePedido: detalles){
            detallePedido.recuperarStock();
        }
    }

}
