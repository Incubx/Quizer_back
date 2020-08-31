package com.example.quizer_back.Model;

import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "quiz")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "size")
    private int size;

    @Column(name = "paid")
    private boolean paid;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Question> questions;

    public Quiz() {
        questions = new ArrayList<>();
    }

    public Quiz(String title, int size, boolean paid) {
        this.title = title;
        this.size = size;
        this.paid = paid;
        questions = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public boolean isPaid() {
        return paid;
    }

    public String getTitle() {
        return title;
    }

    public int getSize() {
        return size;
    }

    public List<Question> getQuestions() {
        return questions;
    }


    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    @NonNull
    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", size=" + size +
                ", free=" + paid +
                '}';
    }

}
