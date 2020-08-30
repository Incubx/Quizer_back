package com.example.quizer_back.Model;




public class Question {


    private int id;

    private String questionText;

    private int rightAnswer;

    private Quiz quiz;



    public Question() {
    }

    public Question(String questionText, int rightAnswer, Quiz quiz) {
        this.questionText = questionText;
        this.rightAnswer = rightAnswer;
        this.quiz = quiz;

    }


    public String getQuestionText() {
        return questionText;
    }

    public int getRightAnswer() {
        return rightAnswer;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }


    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", question='" + questionText + '\'' +
                ", rightAnswer=" + rightAnswer +
                ", quiz=" + quiz +
                '}';
    }
}
