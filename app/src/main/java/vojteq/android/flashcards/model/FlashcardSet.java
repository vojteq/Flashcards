package vojteq.android.flashcards.model;

public class FlashcardSet {

    private int id;

    private String name;

    private String createDate;

    private String editDate;

    private String tableName;

    public FlashcardSet() {
    }

    public FlashcardSet(int id, String name, String tableName, String createDate, String editDate) {
        this.id = id;
        this.name = name;
        this.createDate = createDate;
        this.editDate = editDate;

        //todo zrobic zeby tablename robilo sie automatycznie z name przez zastapienie spacji podloga i drukowanymi
        this.tableName = tableName;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCreateDate() {
        return createDate;
    }

    public String getEditDate() {
        return editDate;
    }

    public String getTableName() {
        return tableName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public void setEditDate(String editDate) {
        this.editDate = editDate;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
