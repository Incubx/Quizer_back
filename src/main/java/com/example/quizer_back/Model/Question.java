package com.example.quizer_back.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "text")
    private String questionText;

    @ManyToOne
    @JoinColumn(name = "quizId")
    @JsonIgnore
    private Quiz quiz;

    @ManyToOne
    @JoinColumn(name = "rightAnswerId")
    private Answer rightAnswer;




    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "questionAnswer",
            joinColumns = @JoinColumn(name = "questionId"),
            inverseJoinColumns = @JoinColumn(name = "answerId"))
    private List<Answer> answerList;

    public Question() {
    }

    public Question(String questionText, Answer answer, Quiz quiz) {
        this.questionText = questionText;
        this.rightAnswer = answer;
        this.quiz = quiz;

    }

    public int getId() {
        return id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public Answer getRightAnswer() {
        return rightAnswer;
    }

    public List<Answer> getAnswerList() {
        return answerList;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", question='" + questionText + '\'' +
                ", rightAnswer=" + rightAnswer +
                '}';
    }
}
