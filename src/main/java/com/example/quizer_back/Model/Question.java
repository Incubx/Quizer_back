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

    @OneToMany(mappedBy = "question",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Answer> answers;

    public Question() {
    }
    public Question(Quiz quiz){
        this.quiz=quiz;
    }

    public Question(String questionText, Answer answer, Quiz quiz) {
        this.questionText = questionText;
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


    public List<Answer> getAnswers() {
        return answers;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", questionText='" + questionText + '\'' +
                ", answers=" + answers +
                '}';
    }
}
