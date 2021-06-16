package org.example.superheroe.controller;

import org.example.superheroe.annotation.Performance;
import org.example.superheroe.model.Superheroe;
import org.example.superheroe.service.SuperheroeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * Superhero controller
 */
@RestController
public class SuperheroeController   {

@Autowired
private SuperheroeService superheroeService;
    private static final Logger LOGGER = LoggerFactory.getLogger(SuperheroeController.class);

    /**
     * returns the list of all superheroes
     */
    @GetMapping("/v1/superheroes")
    @Performance
    public ResponseEntity<?> getSuperheroes(){
            return new ResponseEntity<>(superheroeService.getSuperheroes(), HttpStatus.OK);
    }

    /**
     * Given a series of characters, it returns the match (s)
     * @param name
     * @return
     */
    @GetMapping(value="/v1/superheroes/{name}")
    @Performance
    public ResponseEntity<?> getSuperheroesByName(@PathVariable String name){
       // return new ResponseEntity<>(indexationEvent.findSuperheroes(name), HttpStatus.OK);
        return new ResponseEntity<>(superheroeService.getSuperheroesAsLike(name), HttpStatus.OK);

    }
    @GetMapping(value="/v1/superheroe/{id}")
    @Performance
    public ResponseEntity<?> getSuperHeroeById(@PathVariable Long id){
        return new ResponseEntity<>(superheroeService.getSuperheroeById(id),HttpStatus.OK);
    }


    /**
     * Create a superhero
     * @param hero
     * @return
     */
    @PostMapping(value="/v1/superheroe/create")
    @Performance
    public ResponseEntity<?> createSuperheroe(@RequestBody Superheroe hero){
        superheroeService.createSuperhero(hero);
        return new  ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Update a superhero
     * @param hero
     * @param id
     * @return
     */
    @PutMapping (value="/v1/superheroe/update/{id}")
    @Performance
    public ResponseEntity<?> updateSuperheroe(@RequestBody Superheroe hero,@PathVariable Long id){
        superheroeService.updateSuperheroe(hero,id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping(value="/v1/superheroe/delete/{id}")
    @Performance
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deleteSuperheroe(@PathVariable Long id){
        superheroeService.deleteSuperheroe(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
