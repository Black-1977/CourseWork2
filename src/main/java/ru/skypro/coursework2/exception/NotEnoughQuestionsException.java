package ru.skypro.coursework2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class NotEnoughQuestionsException extends RuntimeException {
    public NotEnoughQuestionsException() {
        super("Not enough questions");
    }
}
