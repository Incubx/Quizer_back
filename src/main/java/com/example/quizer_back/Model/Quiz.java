package com.example.quizer_back.Model;

import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "quiz")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;


    private String title;


    private int size;

    @Column(name = "paid")
    private boolean paid;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private final List<Question> questions;

    public Quiz() {
        questions = new ArrayList<>();
    }

    public Quiz(String title, int size, boolean paid) {
        this.title = title;
        this.size = size;
        this.paid = paid;
        questions = new ArrayList<>();
    }

    public void changeSize(int change){
        size+=change;
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


    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
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
