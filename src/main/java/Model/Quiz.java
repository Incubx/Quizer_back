package Model;

import org.springframework.lang.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;



public class Quiz implements Serializable {

    private int id;

    private String title;

    private int size;

    private boolean solved;

    private boolean free;

    private Collection<Question> questions;

    public Quiz(String title, int size, boolean solved, boolean free,@NonNull Collection<Question> questions) {
        this.title = title;
        this.size = size;
        this.solved = solved;
        this.free = free;
        this.questions = questions;
    }

    public Quiz(String title, int size, boolean solved, boolean free) {
        this.title = title;
        this.size = size;
        this.solved = solved;
        this.free = free;
        questions = new ArrayList<>();
    }

    public Quiz() {

    }

    public Collection<Question> getQuestions() {
        return questions;
    }


    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }

    public String getTitle() {
        return title;
    }

    public void addQuestion(Question question) {
        question.setQuiz(this);
        questions.add(question);
    }

    public int getSize() {
        return size;
    }

    @NonNull
    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", size=" + size +
                ", solved=" + solved +
                ", free=" + free +
                '}';
    }


}
