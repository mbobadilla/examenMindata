package org.example.superheroe.repository;

import org.example.superheroe.model.Superheroe;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface SuperheroeRepository extends PagingAndSortingRepository<Superheroe, Long> {
    List<Superheroe> findByNombreContainingIgnoreCase(String nombre);
    List<Superheroe> findByNombreContainsIgnoreCase(String nombre);
    List<Superheroe> findByNombreIsContainingIgnoreCase(String nombre);
}
