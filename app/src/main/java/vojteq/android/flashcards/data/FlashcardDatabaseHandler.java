package vojteq.android.flashcards.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.format.DateFormat;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import vojteq.android.flashcards.model.Flashcard;
import vojteq.android.flashcards.model.FlashcardSet;
import vojteq.android.flashcards.util.Util;

public class FlashcardDatabaseHandler extends SQLiteOpenHelper {

    public FlashcardDatabaseHandler(Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_FLASHCARD_TABLE = "CREATE TABLE " + Util.FLASHCARD_SET_TABLE + "("
                + Util.FLASHCARD_SET_ID + " INTEGER PRIMARY KEY, "
                + Util.FLASHCARD_SET_NAME + " TEXT, "
                + Util.FLASHCARD_TABLE_NAME + " TEXT, "
                + Util.FLASHCARD_SET_CREATE_DATE + " TEXT, "
                + Util.FLASHCARD_SET_EDIT_DATE + " TEXT"
                + ")";

        db.execSQL(CREATE_FLASHCARD_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        for flashcardSet in allFlashcardSets:
//          String DROP_TABLE = "DROP TABLE IF EXISTS" + flashcardSet.getTableName();
//          db.execSQL(DROP_TABLE, new String[]{tableName});
    }

    // FLASHCARDS SET CRUD

    public void addFlashcardSet(FlashcardSet flashcardSet) {
        String currentDate = DateFormat.format("yyyy.MM.dd", new Date()).toString();
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.FLASHCARD_SET_ID, flashcardSet.getId());
        contentValues.put(Util.FLASHCARD_SET_NAME, flashcardSet.getName());
        contentValues.put(Util.FLASHCARD_TABLE_NAME, flashcardSet.getTableName());
        contentValues.put(Util.FLASHCARD_SET_CREATE_DATE, currentDate);
        contentValues.put(Util.FLASHCARD_SET_EDIT_DATE, currentDate);

        database.insert(Util.FLASHCARD_SET_TABLE, null, contentValues);
        Log.d("DBHandler", "addFlashcardSet: " + flashcardSet);
        database.close();
    }

    public List<FlashcardSet> getAllFlashcardSets() {
        List<FlashcardSet> flashcardSetList = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();
        String SELECT_ALL = "SELECT * FROM " + Util.FLASHCARD_SET_TABLE;
        Cursor cursor = database.rawQuery(SELECT_ALL, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                flashcardSetList.add(new FlashcardSet(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)
                ));
            } while (cursor.moveToNext());
            cursor.close();
        }
        database.close();
        return flashcardSetList;
    }


    //FLASHCARDS CRUD

    public void addFlashcard(FlashcardSet flashcardSet, Flashcard flashcard) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.FLASHCARD_ID, flashcard.getId());
        contentValues.put(Util.FLASHCARD_TEXT, flashcard.getText());
        contentValues.put(Util.FLASHCARD_ANSWER, flashcard.getAnswer());

        database.insert(flashcardSet.getTableName(), null, contentValues);
        Log.d("DBHandler", "addFlashcard: " + flashcard);
        database.close();
    }

    public Flashcard getFlashcard(FlashcardSet flashcardSet, int id) {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(
                flashcardSet.getTableName(),
                new String[]{Util.FLASHCARD_ID, Util.FLASHCARD_TEXT, Util.FLASHCARD_ANSWER},
                Util.FLASHCARD_ID + "=?",
                new String[] {String.valueOf(id)},
                null,
                null,
                null
        );
        if (cursor != null) {
            cursor.moveToFirst();
            Flashcard flashcard = new Flashcard(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2)
            );
            cursor.close();
            return flashcard;
        }
        database.close();
        return null;
    }

    public List<Flashcard> getAllFlashcards(FlashcardSet flashcardSet) {
        List<Flashcard> flashcardList = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();
        String SELECT_ALL = "SELECT * FROM " + flashcardSet.getTableName();
        Cursor cursor = database.rawQuery(SELECT_ALL, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                flashcardList.add(new Flashcard(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2)
                ));
            } while (cursor.moveToNext());
            cursor.close();
        }
        database.close();
        return flashcardList;
    }

    public int updateFlashcard(Flashcard flashcard) {
        //todo
        return 0;
    }

    public void deleteFlashcard(Flashcard flashcard) {
        //todo
    }

    public int getCount(FlashcardSet flashcardSet) {
        String GET_ALL = "SELECT * FROM " + flashcardSet.getTableName();
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(GET_ALL, null);
        int count = cursor.getCount();
        cursor.close();
        database.close();
        return count;
    }
}
