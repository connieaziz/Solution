package com.example.solution;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.solution.database.DatabaseHelper;
import com.example.solution.database.Note;

public class ViewNote extends AppCompatActivity {
    int noteId;
    TextView tvTitle;
    TextView tvNoteText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_note);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getNoteId();
        tvTitle=findViewById(R.id.tvTitle);
        tvNoteText=findViewById(R.id.tvNoteText);
        View btnDelete = findViewById(R.id.btnDelete);
        displayNote();

        btnDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DatabaseHelper databaseHelper = new DatabaseHelper(getBaseContext(),"notes",null, 1);
                databaseHelper.deleteNote(noteId);
                finish();
            }
        });
    }
public void getNoteId(){
        Bundle bundle=getIntent().getExtras();
        if (bundle!=null){
            noteId=bundle.getInt("NOTE_ID",0);
        }
}

public void displayNote(){
    DatabaseHelper databaseHelper=new DatabaseHelper(getBaseContext(),"notes",null,1);
    Note note=databaseHelper.getNoteById(noteId);
    tvTitle.setText(note.getNoteText());
    tvNoteText.setText(note.getNoteText());
}
}
