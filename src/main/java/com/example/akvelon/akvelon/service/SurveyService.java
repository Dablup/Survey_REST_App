package com.example.akvelon.akvelon.service;


import com.example.akvelon.akvelon.model.Survey;
import com.example.akvelon.akvelon.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class SurveyService {

    @Autowired
    private SurveyRepository surveyRepository;


    /**
     * Method for finding all the existing surveys in database
     * @return list of surveys
     */
    public Iterable<Survey> findAll() {
        return surveyRepository.findAll();
    }


    /**
     * Method for finding all the existing surveys in database in sorted order
     * @param methodOfSorting (name, id, dateOfStart)
     * @return list of sorted surveys
     */
    public Iterable<Survey> returnSortedPage(String methodOfSorting) {
        return surveyRepository.findAll(Sort.by(methodOfSorting));
    }


    /**
     * Method for finding all the existing surveys in database in sorted order using pagination
     * @param page -
     * @return list of surveys according to pagination
     */
    public Iterable<Survey> returnSortedPage(Pageable page) {
        return surveyRepository.findAll(page);
    }


    /**
     * Method to save a new survey in the database
     * @param survey - example
     * {
     *   "active": "true",
     *   "dateOfEnd": "2021-12-08T11:46:29.805Z",
     *   "dateOfStart": "2025-12-08T11:46:29.805Z",
     *   "name": "AAABQuiz6"
     * }
     * @return saved survey
     */
    public Survey save(Survey survey) {
        return surveyRepository.save(survey);
    }

    /**
     * Method to delete a survey in the database
     * @param id (id of the survey that we want to delete)
     */
    public void deleteById(Long id) {
        surveyRepository.deleteById(id);
    }


    /**
     * Method to get the survey from the database by id
     * @param id (id of the survey that we watn to get)
     * @return survey
     */
    public Survey getById(Long id) {
        return surveyRepository.getById(id);
    }


    /**
     * Method to update already existing survey in the database
     * @param id (id of the survey to be updated)
     * @param survey (new updated survey)
     */
    public void update(Long id, Survey survey) {
        Survey surveyToBeUpdated = surveyRepository.getById(id);
        if (survey.getName() != null) {
            surveyToBeUpdated.setName(survey.getName());
        }
        if (survey.getDateOfStart() != null) {
            surveyToBeUpdated.setDateOfStart(survey.getDateOfStart());
        }
        if (survey.getDateOfEnd() != null) {
            surveyToBeUpdated.setDateOfEnd(survey.getDateOfEnd());
        }
        if (survey.isActive() != surveyToBeUpdated.isActive()) {
            surveyToBeUpdated.setActive(survey.isActive());
        }
        surveyRepository.save(surveyToBeUpdated);
    }
}
