package ru.skypro.coursework2.service;

import ru.skypro.coursework2.model.Question;

import java.util.Collection;

public interface ExaminerService {

    public Collection<Question> getQuestions(int amount);
}
