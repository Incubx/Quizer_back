package com.example.quizer_back.Model;

import javax.persistence.*;

@Entity
@Table(name = "user_quiz")
public class UserQuiz {

    @EmbeddedId
    private UserQuizId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("quizId")
    private Quiz quiz;

    @Column(name = "rating")
    private int rating;

    public UserQuiz() {
    }

    public UserQuiz(User user, Quiz quiz, int rating) {
        this.user = user;
        this.quiz = quiz;
        id = new UserQuizId(user.getId(),quiz.getId());
        this.rating = rating;
    }
}
