package com.example.akvelon.akvelon.repository;

import com.example.akvelon.akvelon.model.Survey;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface QuestionRepository extends PagingAndSortingRepository<Survey,Long> {
}
