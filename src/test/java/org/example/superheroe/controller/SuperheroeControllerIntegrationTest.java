package org.example.superheroe.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.superheroe.model.Superheroe;
import org.example.superheroe.repository.SuperheroeRepository;
import org.example.superheroe.service.SuperheroeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class SuperheroeControllerIntegrationTest {
    private static final ObjectMapper om = new ObjectMapper();
    @Autowired
    private TestRestTemplate restTemplate;
    @MockBean
    private SuperheroeService superheroeService;
    @MockBean
    private SuperheroeRepository superheroeRepository;

    @Before
    public void init() {
        Superheroe superman= new Superheroe();
        superman.setId(Long.valueOf(1));
        superman.setNombre("Superman");
        superman.setCopyright("Marvel");
        Superheroe spidereman= new Superheroe();
        spidereman.setId(Long.valueOf(2));
        spidereman.setNombre("SSpiderman");
        spidereman.setCopyright("Marvel");

        List<Superheroe> listSuperheroe = new ArrayList();
        listSuperheroe.add(spidereman);
        listSuperheroe.add(superman);

        when(superheroeService.getSuperheroes()).thenReturn(listSuperheroe);
        when(superheroeService.getSuperheroesAsLike("man")).thenReturn(listSuperheroe);
        when(superheroeService.createSuperhero(superman)).thenReturn(superman);


    }

    @Test
    public void getAllSuperheroeTest(){
        ResponseEntity<List> response =  restTemplate.getForEntity("/superheroes", List.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
        verify(superheroeService, times(2)).getSuperheroes();
    }

    @Test
    public void findSuperheroeByNameTest(){
        ResponseEntity<List> response =  restTemplate.getForEntity("/superheroes/man", List.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
        verify(superheroeService, times(1)).getSuperheroesAsLike("man");
    }

    @Test
    public void createSuperHeroe() throws Exception{
        Superheroe superman= new Superheroe();
        superman.setId(Long.valueOf(1));
        superman.setNombre("Superman");
        superman.setCopyright("Warner Bros");

        when(superheroeRepository.save(any(Superheroe.class))).thenReturn(superman);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(om.writeValueAsString(superman), headers);
        ResponseEntity<String> response = restTemplate.exchange("/superheroe/create", HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void updateSuperHeroeTest() throws Exception{
        Superheroe superman= new Superheroe();
        superman.setId(Long.valueOf(1));
        superman.setNombre("Superman");
        superman.setCopyright("Warner Bros");

        when(superheroeRepository.save(any(Superheroe.class))).thenReturn(superman);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(om.writeValueAsString(superman), headers);

        ResponseEntity<String> response = restTemplate.exchange("/superheroe/update/1", HttpMethod.PUT, entity, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

    }
    @Test
    public void deleteSuperHeroeTest() throws Exception{
        Superheroe superman= new Superheroe();
        superman.setId(Long.valueOf(1));
        superman.setNombre("Superman");
        superman.setCopyright("Warner Bros");

        when(superheroeRepository.save(any(Superheroe.class))).thenReturn(superman);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(om.writeValueAsString(superman), headers);

        ResponseEntity<String> response = restTemplate.exchange("/superheroe/delete/1", HttpMethod.DELETE, entity, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }


}
