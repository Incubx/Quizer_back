package com.example.quizer_back.Model;



public class QuestionAnswer {


    private int id;


    Answer answer;

    Question question;

    public QuestionAnswer() {
    }

    public QuestionAnswer(Question question, Answer answer) {
        this.answer = answer;
        this.question = question;
    }

    public Answer getAnswer() {
        return answer;
    }

}
