package com.Tuki.Tuki_Backend_Provisional.Servicios.IntefacesServicios;


import java.util.List;
import java.util.Optional;

public interface BaseService<E, Long, DTOinput, DTOrespueta>  {
    List<DTOrespueta> listarActivos();
    List<DTOrespueta> listarTodos();
    List<DTOrespueta> listarEliminados();
    DTOrespueta crear(DTOinput dtOcreate);
    DTOrespueta actualizar(Long id, DTOinput dtOmodicador);
    Optional<E> eliminar(Long id);

}
