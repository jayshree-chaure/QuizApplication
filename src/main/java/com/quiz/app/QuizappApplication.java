package com.quiz.app;

import com.quiz.app.answer.repository.AnswerRepository;
import com.quiz.app.question.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.quiz.app")
@SpringBootApplication
public class QuizappApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(QuizappApplication.class, args);
	}

}
