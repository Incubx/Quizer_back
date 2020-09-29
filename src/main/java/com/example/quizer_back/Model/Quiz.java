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
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "size")
    private int size;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private final List<Question> questions;

    public Quiz() {
        questions = new ArrayList<>();
    }

    public Quiz(String title, int size, boolean paid) {
        this.title = title;
        this.size = size;

        questions = new ArrayList<>();
    }

    public void changeSize(int change){
        size+=change;
    }

    public int getId() {
        return id;
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



    @NonNull
    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", size=" + size +
                '}';
    }
}
