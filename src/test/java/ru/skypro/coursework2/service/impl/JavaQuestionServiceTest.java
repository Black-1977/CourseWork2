package ru.skypro.coursework2.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.skypro.coursework2.model.Question;
import ru.skypro.coursework2.service.QuestionService;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class JavaQuestionServiceTest {

    private QuestionService questionService;

    @BeforeEach
    void init() {
        questionService = new JavaQuestionService();
    }

    @Test
    void testAdd() {
        Question expectedQuestion = new Question("question", "answer");
        Question actualQuestion = questionService.add(expectedQuestion);
        assertEquals(expectedQuestion, actualQuestion);
    }

    @Test
    void testRemove() {
        Question expectedQuestion = new Question("question", "answer");
        questionService.add(expectedQuestion);
        Question actualQuestion = questionService.remove(expectedQuestion);
        assertEquals(expectedQuestion, actualQuestion);
    }

    @Test
    void testGetAll() {
        Question expectedQuestion1 = new Question("question1", "answer1");
        Question expectedQuestion2 = new Question("question2", "answer2");

        questionService.add(expectedQuestion1);
        questionService.add(expectedQuestion2);

        Set<Question> expectedQuestions = new HashSet<Question>() {{
            add(expectedQuestion1);
            add(expectedQuestion2);
        }};

        Collection<Question> actualQuestions = questionService.getAll();

        assertEquals(expectedQuestions, actualQuestions);
    }

    @Test
    void testGetRandomQuestion() {
        Question expectedQuestion1 = new Question("question1", "answer1");
        Question expectedQuestion2 = new Question("question2", "answer2");

        questionService.add(expectedQuestion1);
        questionService.add(expectedQuestion2);

        Set<Question> expectedQuestions = new HashSet<Question>() {{
            add(expectedQuestion1);
            add(expectedQuestion2);
        }};

        Question randomQuestion = questionService.getRandomQuestion();

        assertTrue(questionService.getAll().contains(randomQuestion));
    }
}