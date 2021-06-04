package org.example.superheroe.service;

import org.example.superheroe.exception.ResourceNotFoundException;
import org.example.superheroe.model.Superheroe;
import org.example.superheroe.repository.SuperheroeRepository;
import org.example.superheroe.utils.CodigoErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

@Service
public class SuperheroeService {
    @Autowired
    private SuperheroeRepository superheroeRepository;

    /**
     * Get All Superheros
     * @return
     */
    public List<Superheroe> getSuperheroes()  {
        List<Superheroe> list=new ArrayList<>();

        if(!superheroeRepository.findAll().iterator().hasNext()){
            throw new ResourceNotFoundException(CodigoErrorEnum.DATABASE_EMPTY.name());
        }

        superheroeRepository.findAll().forEach(s-> list.add(s));
       return  list;
    }

    /**
     * Match character for superheros
     * @param nombre
     * @return
     */
    public List<Superheroe> getSuperheroesAsLike(String nombre)  {

        List<Superheroe> list=superheroeRepository.findByNombreIsContainingIgnoreCase(nombre);

        if(list.isEmpty()){
            throw new ResourceNotFoundException(CodigoErrorEnum.DATABASE_EMPTY.name());
        }
        return list;

    }
}
