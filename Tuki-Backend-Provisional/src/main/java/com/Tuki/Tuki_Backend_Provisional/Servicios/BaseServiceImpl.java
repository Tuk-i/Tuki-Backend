package com.Tuki.Tuki_Backend_Provisional.Servicios;

import com.Tuki.Tuki_Backend_Provisional.Entidades.Base;
import com.Tuki.Tuki_Backend_Provisional.Entidades.DTOs.ErrorDTO;
import com.Tuki.Tuki_Backend_Provisional.Entidades.DTOs.Mappers.BaseMapper;
import com.Tuki.Tuki_Backend_Provisional.Repositorys.BaseRepository;
import com.Tuki.Tuki_Backend_Provisional.Servicios.IntefacesServicios.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

public abstract class BaseServiceImpl<E extends Base, Long, DTOPost, DTOUpdate, DTOrespueta> implements BaseService<E, Long, DTOPost, DTOUpdate, DTOrespueta> {

    @Autowired
    BaseRepository<E, Long> baseRepository;
    @Autowired
    BaseMapper<E,DTOPost, DTOUpdate, DTOrespueta> baseMapper;


    protected ResponseEntity<?> registrarConValidacion( boolean existe, String mensajeError, DTOPost dto) {

        if (existe) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorDTO(mensajeError));
        }

        DTOrespueta creado = crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    protected ResponseEntity<?> editarConValidacion(Optional<? extends Base> existente, Long id, String mensajeError, DTOUpdate dto
    ) {
        if (existente.isPresent() && !existente.get().getId().equals(id)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorDTO(mensajeError));
        }

        DTOrespueta actualizado = actualizar(id, dto);
        return ResponseEntity.ok(actualizado);
    }




    @Override
    public List<DTOrespueta> listarTodos(){
        return mapear(baseRepository.findAllByOrderByIdAsc());
    }

    @Override
    public List<DTOrespueta> listarActivos() {
        return mapear(baseRepository.findByEliminadoFalseOrderByIdAsc());
    }

    @Override
    public List<DTOrespueta> listarEliminados(){
        return mapear(baseRepository.findByEliminadoTrueOrderByIdAsc());
    }

    protected List<DTOrespueta> mapear(List<E> entidades) {
        return entidades.stream()
                .map(baseMapper::entityToDTO)
                .toList();
    }



    @Override
    public DTOrespueta crear(DTOPost dtOcreate) {
        E e = baseMapper.dtoToEntity(dtOcreate);
        e = baseRepository.save(e);
        return baseMapper.entityToDTO(e);
    }


    @Override
    public DTOrespueta actualizar(Long id, DTOUpdate dtOmodicador) {
        Optional<E> entidadOpt = baseRepository.findById(id);
        if (entidadOpt.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entidad no encontrada con id: " + id);
        }

        E entidad = entidadOpt.get();
        baseMapper.actualizarEntidad(entidad, dtOmodicador);
        entidad = baseRepository.save(entidad);
        return baseMapper.entityToDTO(entidad);
    }


    @Override
    public Optional<E> eliminar(Long id){
        Optional<E> entidadOpt = baseRepository.findById(id);
        if (entidadOpt.isEmpty()){
            return Optional.empty();
        }

        E entidad = entidadOpt.get();

        entidad.setEliminado(true);

        entidad = baseRepository.save(entidad);
        return Optional.of(entidad);
    }

    public ResponseEntity<?> reactivar(Long id) {
        Optional<E> entidadOpt = baseRepository.findById(id);

        if (entidadOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorDTO( "Estado: inexistente"));
        }

        E entidad = entidadOpt.get();

        if (!entidad.getEliminado()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ErrorDTO("Estado: Activado"));
        }

        entidad.setEliminado(false);
        baseRepository.save(entidad);

        return ResponseEntity.ok(baseMapper.entityToDTO(entidad));
    }

}
