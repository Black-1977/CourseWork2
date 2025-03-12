package ru.skypro.coursework2.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.coursework2.exception.NotEnoughQuestionsException;
import ru.skypro.coursework2.model.Question;
import ru.skypro.coursework2.service.ExaminerService;
import ru.skypro.coursework2.service.QuestionService;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    public final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {

        int questionsCount = questionService.getAll().size();
        if (amount > questionsCount) {
            throw new NotEnoughQuestionsException();
        }

        Set<Question> randomQuestions = new HashSet<>();
        while (randomQuestions.size() < amount) {
            randomQuestions.add(questionService.getRandomQuestion());
        }
        return randomQuestions;
    }
}
