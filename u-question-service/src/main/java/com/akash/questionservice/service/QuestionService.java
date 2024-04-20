package com.akash.questionservice.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.akash.questionservice.dao.QuestionDao;
import com.akash.questionservice.entity.Question;
import com.akash.questionservice.entity.QuestionWrapper;
import com.akash.questionservice.entity.Response;

@Service
public class QuestionService {
	@Autowired
	QuestionDao questionDao;
	public ResponseEntity<List<Question>> getAllQuestion() {
		// TODO Auto-generated method stub
		try {
			return new ResponseEntity<>(questionDao.findAll(),HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
	}
	public ResponseEntity<List<Question>> getByCategory(String category) {
		// TODO Auto-generated method stub
		try {
			return new ResponseEntity<>(questionDao.findByCategory(category),HttpStatus.OK);}
		catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
	}
	
	
	public ResponseEntity<Question> addQuestion(Question question) {
		// TODO Auto-generated method stub
		try {
			return new ResponseEntity<>(questionDao.save(question),HttpStatus.CREATED);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new Question(),HttpStatus.CREATED);
	}
	public ResponseEntity<List<Integer>> getQuestionForQuiz(String category, Integer numQ) {
		List<Integer>questionsId=questionDao.findRandomQuestionsByCategory(category,numQ);
		return new ResponseEntity<>(questionsId,HttpStatus.OK);
		
	}
	public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds) {
		List<QuestionWrapper>wrappers=new ArrayList<>();
		
		Question question=new Question();
		for(Integer id:questionIds) {
			question=questionDao.findById(id).get();
			wrappers.add(new QuestionWrapper(question.getId(),
					question.getQuestionTitle(),question.getOption1(),
					question.getOption2(),question.getOption3(),question.getOption4()));
		}
		
		return new ResponseEntity<>(wrappers, HttpStatus.OK);
	}
	public ResponseEntity<Integer> getScore(List<Response> responses) {
		Question question=new Question();
		Integer score=0;
		for(Response r:responses) {
			question=questionDao.findById(r.getId()).get();
			if(question.getRightAnswer().equals(r.getResponse()))
					score++;
		}
		
		return new ResponseEntity<>(score,HttpStatus.OK);
	}
	
}
