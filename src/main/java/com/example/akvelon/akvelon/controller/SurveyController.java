package com.example.akvelon.akvelon.controller;

import com.example.akvelon.akvelon.model.Survey;
import com.example.akvelon.akvelon.service.SurveyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(description = "Endpoints for Creating, Retrieving, Updating and Deleting of Contacts.",
        tags = {"Survey Controller"})
@RestController
@RequestMapping("/survey")
public class SurveyController {

    @Autowired
    private SurveyService surveyService;


    @Operation(summary = "Getting all the existing surveys")
    @GetMapping("/all")
    public ResponseEntity<Iterable<Survey>> index() {
        return ResponseEntity.ok(surveyService.findAll());
    }


    @Operation(summary = "Getting all the surveys sorted by {name} or {dateOfStart} or {id}")
    @GetMapping("/all_sorted_by_{methodOfSorting}")
    public ResponseEntity<Iterable<Survey>> returnSortedPage(@PathVariable @ApiParam(value = "**methodOfSorting** must be <i><ins>name</ins></i>," +
            " <i><ins>dateOfStart</ins></i> or <i><ins>id</ins></i>") String methodOfSorting) {
        Pageable firstPageWithTwoElements = PageRequest.of(0, 4, Sort.by(methodOfSorting));
        return ResponseEntity.ok(surveyService.returnSortedPage(firstPageWithTwoElements));
    }

    @Operation(summary = "Creation of the new survey")
    @PostMapping("/create")
    public ResponseEntity<Survey> create(@Valid @RequestBody Survey survey) {
        Survey createdSurvey = surveyService.save(survey);
        return ResponseEntity.ok(createdSurvey);
    }

    @Operation(summary = "Deletion of the existing survey")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Long> delete(@PathVariable("id") Long id) {
        surveyService.deleteById(id);
        return ResponseEntity.ok(id);
    }


    @Operation(summary = "Updating the existing survey")
    @PatchMapping("/update/{id}")
    public ResponseEntity<Long> update(@PathVariable("id") Long id, @Valid @RequestBody Survey survey) {
        surveyService.update(id, survey);
        return ResponseEntity.ok(id);
    }
}
