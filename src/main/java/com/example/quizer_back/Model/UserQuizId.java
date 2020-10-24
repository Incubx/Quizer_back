package com.example.quizer_back.Model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserQuizId implements Serializable {
    @Column(name = "userId")
    private int userId;

    @Column(name = "quizId")
    private int quizId;


    public UserQuizId() {
    }

    public UserQuizId(int userId, int quizId) {
        this.userId = userId;
        this.quizId = quizId;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        UserQuizId that = (UserQuizId) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(quizId, that.quizId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, quizId);
    }
}
