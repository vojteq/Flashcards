package vojteq.android.flashcards.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vojteq.android.flashcards.R;
import vojteq.android.flashcards.fragments.FlashcardFragment;
import vojteq.android.flashcards.model.FlashcardSet;
import vojteq.android.flashcards.util.Util;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;

    private List<FlashcardSet> flashcardSetList;

    public RecyclerViewAdapter(Context context, List<FlashcardSet> flashcardSetList) {
        this.context = context;
        this.flashcardSetList = flashcardSetList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.flashcard_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        FlashcardSet flashcardSet = flashcardSetList.get(position);
        viewHolder.flashcardSetName.setText(flashcardSet.getName());
        viewHolder.flashcardSetCreateDate.setText(flashcardSet.getCreateDate());
        viewHolder.flashcardSetEditDate.setText(flashcardSet.getEditDate());
    }

    @Override
    public int getItemCount() {
        return flashcardSetList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView flashcardSetName;

        public TextView flashcardSetCreateDate;

        public TextView flashcardSetEditDate;

        public ImageView editFlashcardSetButton;

        public ImageView deleteFlashcardSetButton;

        public final int id;

        public ViewHolder(View view) {
            super(view);
            this.id = view.getId();
            flashcardSetName = view.findViewById(R.id.flashcard_name);
            flashcardSetCreateDate = view.findViewById(R.id.create_date);
            flashcardSetEditDate = view.findViewById(R.id.edit_date);
            editFlashcardSetButton = view.findViewById(R.id.edit_button);
            deleteFlashcardSetButton = view.findViewById(R.id.delete_button);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            FlashcardSet flashcardSet = flashcardSetList.get(position);

            switch (view.getId()) {
                case R.id.edit_button:
                    //todo
                    return;
                case R.id.delete_button:
                    //todo
                    return;
                default:
                    startFlashcardSetActivity(flashcardSet, view);
            }
        }

        private void startFlashcardSetActivity(FlashcardSet flashcardSet, View view) {
            Intent intent = new Intent(context, FlashcardFragment.class);
            intent.putExtra(Util.TABLE_NAME, flashcardSet.getTableName());
            context.startActivity(intent);
        }
    }
}
