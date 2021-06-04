package org.example.superheroe.controller;

import org.example.superheroe.service.SuperheroeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Superhero controller
 */
@RestController
public class SuperheroeController   {

@Autowired
private SuperheroeService superheroeService;
    @Autowired

    /**
     * returns the list of all superheroes
     */
    @GetMapping("/superheroes")
    public ResponseEntity<?> getSuperheroes(){
            return new ResponseEntity<>(superheroeService.getSuperheroes(), HttpStatus.OK);

    }

    /**
     * Given a series of characters, it returns the match (s)
     * @param name
     * @return
     */
    @GetMapping(value="/superheroes/{name}")
    public ResponseEntity<?> getSuperheroesByName(@PathVariable String name){
       // return new ResponseEntity<>(indexationEvent.findSuperheroes(name), HttpStatus.OK);
        return new ResponseEntity<>(superheroeService.getSuperheroesAsLike(name), HttpStatus.OK);

    }

}
