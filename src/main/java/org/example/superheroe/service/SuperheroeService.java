package org.example.superheroe.service;

import org.example.superheroe.exception.ResourceNotFoundException;
import org.example.superheroe.model.Superheroe;
import org.example.superheroe.repository.SuperheroeRepository;
import org.example.superheroe.utils.CodigoErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SuperheroeService {

    @Autowired
    private SuperheroeRepository superheroeRepository;

    /**
     * Get All Superheros
     * @return
     */
    @Cacheable("superheroe")
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

    /**
     * Get a supehero by Id
     * @param id
     * @return
     */
    public Superheroe getSuperheroeById(Long id){
        return validateOptional(superheroeRepository.findById(id));
    }


    /**
     * Persist a Superhero to database
     * @param hero
     * @return
     */
    public Superheroe createSuperhero(Superheroe hero){
       return  superheroeRepository.save(hero);
    }

    /**
     * Update a superhero
     * @param hero
     * @param id
     */
    public void updateSuperheroe(Superheroe hero, Long id){
        Optional<Superheroe> superheroe =superheroeRepository.findById(id);
        if(superheroe.isEmpty()){
            throw new ResourceNotFoundException(CodigoErrorEnum.NON_EXISTENT_SUPERHERO.name());
        }
        superheroeRepository.save(hero);
    }

    public void deleteSuperheroe(Long id){
        Optional<Superheroe> superheroe =superheroeRepository.findById(id);
        if(superheroe.isEmpty()){
            throw new ResourceNotFoundException(CodigoErrorEnum.NON_EXISTENT_SUPERHERO.name());
        }
        superheroeRepository.delete(superheroe.get());
    }

    /**
     * Validate Optional object
     * @param superheroe
     * @return
     */
    private Superheroe validateOptional(Optional<Superheroe> superheroe){
        if(superheroe.isEmpty()){
            throw new ResourceNotFoundException(CodigoErrorEnum.NON_EXISTENT_SUPERHERO.name());
        }
        return superheroe.get();
    }


}
