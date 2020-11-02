package com.example.quizer_back.Model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "quiz")
@JsonAutoDetect(fieldVisibility= JsonAutoDetect.Visibility.ANY)
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private int size;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private final List<Question> questions;

    @Transient
    @JsonProperty("isCompleted")
    @JsonInclude
    private boolean isCompleted;


    public Quiz() {
        questions = new ArrayList<>();
    }

    public Quiz(String title, int size) {
        this.title = title;
        this.size = size;
        questions = new ArrayList<>();
    }

    public Quiz(int id, String title, int size, List<Question> questions) {
        this.id = id;
        this.title = title;
        this.size = size;
        this.questions = questions;
    }


    public void changeSize(int change) {
        size += change;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
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
