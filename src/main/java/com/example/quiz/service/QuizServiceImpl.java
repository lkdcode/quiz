package com.example.quiz.service;

import com.example.quiz.entity.Quiz;
import com.example.quiz.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class QuizServiceImpl implements QuizService {

    @Autowired
    QuizRepository repository;

    @Override
    public Iterable<Quiz> selectAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Quiz> selectOneById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Quiz> selectOneRandomQuiz() {
        Integer ranId = repository.getRandomId();

        if (ranId == null) {
            return Optional.empty();
        }

        return repository.findById(ranId);
    }

    @Override
    public Boolean checkQuiz(Integer id, boolean userAnswer) {
        Boolean check = false;

        Optional<Quiz> optQuiz = repository.findById(id);
        if (optQuiz.isPresent()) {
            Quiz quiz = optQuiz.get();

            if (quiz.isAnswer() == userAnswer) {
                check = true;
            }
        }
        return check;
    }

    @Override
    public void insertQuiz(Quiz quiz) {
        repository.save(quiz);
    }

    @Override
    public void updateQuiz(Quiz quiz) {
        repository.save(quiz);
    }

    @Override
    public void deleteQuiz(Integer id) {
        repository.deleteById(id);
    }
}
