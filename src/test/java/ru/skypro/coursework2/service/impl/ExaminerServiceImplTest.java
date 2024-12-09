package ru.skypro.coursework2.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.coursework2.exception.NotEnoughQuestionsException;
import ru.skypro.coursework2.model.Question;
import ru.skypro.coursework2.service.ExaminerService;
import ru.skypro.coursework2.service.QuestionService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {

    @Mock
    private QuestionService questionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    @Test
    void testGetQuestions() {
        int amount = 2;
        List<Question> questions = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            questions.add(new Question("question" + i, "answer" + i));
        }

        Mockito.when(questionService.getAll()).thenReturn(questions);
        Mockito.when(questionService.getRandomQuestion()).thenReturn(
                questions.get(1), questions.get(1), questions.get(0)
        );

        Collection<Question> randomQuestions = examinerService.getQuestions(amount);

        assertEquals(randomQuestions.size(), amount);
        assertTrue(randomQuestions.containsAll(questions));
        Mockito.verify(questionService, times(3)).getRandomQuestion();
    }

    @Test
    void testNotEnoughQuestions() {
        int amount = 4;
        List<Question> questions = new ArrayList<>();
        for (int i = 0; i < amount-1; i++) {
            questions.add(new Question("question" + i, "answer" + i));
        }
        Mockito.when(questionService.getAll()).thenReturn(questions);

        assertThrows(NotEnoughQuestionsException.class, () -> examinerService.getQuestions(amount));
    }
}