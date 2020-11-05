package com.example.quizer_back.Model;

import javax.persistence.*;

@Entity
@Table(name = "user_quiz")
public class UserQuiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quizId")
    private Quiz quiz;

    @Column(name = "rating")
    private int rating;

    @Column(name = "attempts")
    private int attempts;

    public UserQuiz() {
    }

    public UserQuiz(User user, Quiz quiz, int rating) {
        this.user = user;
        this.quiz = quiz;
        this.rating = rating;
        attempts = 1;
    }

    public User getUser() {
        return user;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public int getRating() {
        return rating;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void increaseAttempt() {
        attempts++;
    }

    public int getAttempts() {
        return attempts;
    }
}
