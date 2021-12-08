package com.example.akvelon.akvelon.repository;

import com.example.akvelon.akvelon.model.Survey;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurveyRepository extends PagingAndSortingRepository<Survey, Long>  {

    @Query(value = "SELECT * FROM survey WHERE name=:name", nativeQuery = true)
    Page<Survey> findByName(@Param("name") String name, Pageable pageable);

    @Query(value = "SELECT * FROM survey WHERE name=:name", nativeQuery = true)
    List<Survey> findByName(@Param("name") String name);

    @Query(value = "SELECT * FROM survey WHERE active=true", nativeQuery = true)
    Page<Survey> findByActive(Pageable pageable);

    @Query(value = "SELECT * FROM survey WHERE active=true", nativeQuery = true)
    List<Survey> findByActive();

    @Query(value = "SELECT * FROM survey WHERE id=:id", nativeQuery = true)
    Page getById(@Param("id") Long id, Pageable pageable);

    @Query(value = "SELECT * FROM survey WHERE id=:id", nativeQuery = true)
    Survey getById(@Param("id") Long id);

    void flush();

}