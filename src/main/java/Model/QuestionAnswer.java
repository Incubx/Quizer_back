package Model;



public class QuestionAnswer {


    private int id;


    Answer answer;

    Question question;

    @SuppressWarnings("unused")
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
