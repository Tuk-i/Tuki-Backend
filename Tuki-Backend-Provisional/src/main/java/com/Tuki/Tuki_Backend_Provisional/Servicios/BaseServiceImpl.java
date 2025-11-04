package com.Tuki.Tuki_Backend_Provisional.Servicios;

import com.Tuki.Tuki_Backend_Provisional.Entidades.Base;
import com.Tuki.Tuki_Backend_Provisional.Entidades.UsuarioDTOs.Mappers.BaseMapper;
import com.Tuki.Tuki_Backend_Provisional.Repositorys.BaseRepository;
import com.Tuki.Tuki_Backend_Provisional.Servicios.IntefacesServicios.BaseService;

import java.util.List;
import java.util.Optional;

public abstract class BaseServiceImpl<E extends Base, Long, DTOinput, DTOrespueta> implements BaseService<E, Long, DTOinput, DTOrespueta> {

    protected final BaseRepository<E, Long> baseRepository;
    protected final BaseMapper<E, DTOinput, DTOrespueta> baseMapper;

    public BaseServiceImpl(BaseRepository<E, Long> baseRepository, BaseMapper<E, DTOinput, DTOrespueta> baseMapper) {
        this.baseRepository = baseRepository;
        this.baseMapper = baseMapper;
    }


    @Override
    public List<DTOrespueta> listarTodos(){
        return mapear(baseRepository.findAll());
    }

    @Override
    public List<DTOrespueta> listarActivos() {
        return mapear(baseRepository.findByEliminadoFalse());
    }

    @Override
    public List<DTOrespueta> listarEliminados(){
        return mapear(baseRepository.findByEliminadoTrue());
    }

    private List<DTOrespueta> mapear(List<E> entidades) {
        return entidades.stream()
                .map(baseMapper::entityToDTO)
                .toList();
    }




    @Override
    public DTOrespueta crear(DTOinput dtOcreate) {
        E e = baseMapper.dtoToEntity(dtOcreate);
        e = baseRepository.save(e);
        return baseMapper.entityToDTO(e);
    }


    @Override
    public DTOrespueta actualizar(Long id, DTOinput dtOmodicador) {
        Optional<E> entidadOpt = baseRepository.findById(id);
        if (entidadOpt.isEmpty()){
            throw new RuntimeException("Entidad no encontrada con id: " + id);
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

}
