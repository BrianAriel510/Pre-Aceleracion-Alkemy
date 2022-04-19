package com.alekmy.peliculas.service.impl;

import com.alekmy.peliculas.dto.GeneroBasicDTO;
import com.alekmy.peliculas.dto.GeneroDTO;
import com.alekmy.peliculas.entity.GeneroEntity;
import com.alekmy.peliculas.mapper.GeneroMapper;
import com.alekmy.peliculas.repository.GeneroRepository;
import com.alekmy.peliculas.service.IGeneroService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeneroServiceImpl implements IGeneroService {

    @Autowired
    private GeneroMapper generoMapper;
    
    @Autowired
    private GeneroRepository generoRepository;

    @Override
    public GeneroDTO save(GeneroBasicDTO dto) {
        GeneroEntity generoEntity = generoMapper.generoDto2Entity(dto);
        GeneroEntity generoSaved = generoRepository.save(generoEntity);
        GeneroDTO result = generoMapper.generoEntity2DTO(generoSaved,false);
        return result;
    }

    @Override
    public List<GeneroDTO> getAllGeneros() {
        List<GeneroEntity> generoEntities = generoRepository.findAll();
        List<GeneroDTO> result = generoMapper.generoEntityList2DTOList(generoEntities,false);
        return result;
    }

    @Override
    public List<GeneroBasicDTO> getAllGenerosBasic() {
        List<GeneroEntity>generoEntities = generoRepository.findAll();
        List<GeneroBasicDTO> result= generoMapper.generoBasicEntityList2DTOList(generoEntities);
        return result;
    }
    
    @Override
    public void delete(Long id) {
        generoRepository.deleteById(id);
    }//IMPORTANTE: aca sabe que tiene que hacer un soft delete y no un hard, porque en la entidad se lo entoy aclarando


}
