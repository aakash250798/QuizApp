package com.akash.quizservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.akash.quizservice.feign.QuizInterface;
import com.akash.quizservice.dao.QuizDao;
import com.akash.quizservice.entity.QuestionWrapper;
import com.akash.quizservice.entity.Quiz;
import com.akash.quizservice.entity.Response;

@Service
public class QuizService {
	@Autowired
	QuizDao quizDao;
	
	@Autowired
	QuizInterface quizInterface;
	

	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
		
		List<Integer>questionIds= quizInterface.getQuestionForQuiz(category, numQ).getBody();
		Quiz quiz=new Quiz();
		quiz.setTitle(title);
		quiz.setQuestionIds(questionIds);
		quizDao.save(quiz);
		
		
		return new ResponseEntity<>("Created Quiz",HttpStatus.CREATED);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizById(int id) {
		
		Quiz quiz=quizDao.findById(id).get();
		List<Integer>questionIds=quiz.getQuestionIds();
		ResponseEntity<List<QuestionWrapper>> questions=quizInterface
				.getQuestionsFromId(questionIds);
		
		return questions;
	}

	
	public ResponseEntity<Integer> submitQuiz(int id, List<Response> response) {
		
		ResponseEntity<Integer> score= quizInterface.getScore(response);
		return score;
	}

}
