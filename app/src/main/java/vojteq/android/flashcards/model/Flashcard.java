package vojteq.android.flashcards.model;

public class Flashcard {

    private int id;

    private String text;

    private String answer;

    public Flashcard() {
    }

    public Flashcard(int id, String text, String answer) {
        this.id = id;
        this.text = text;
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getAnswer() {
        return answer;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Flashcard{" +
                "id=" + id +
                ", flashcardText='" + text + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
