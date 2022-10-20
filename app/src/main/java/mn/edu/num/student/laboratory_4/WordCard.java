package mn.edu.num.student.laboratory_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class WordCard extends AppCompatActivity {

    EditText etMN,etEN;
    Button cancelBtn, updateBtn;
    private DBHandler DBHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_card);

        DBHandler = new DBHandler(this);

        etMN = findViewById(R.id.etMN);
        etEN = findViewById(R.id.etEN);
        cancelBtn = findViewById(R.id.cancel_Button);
        updateBtn = findViewById(R.id.update_Button);
        Intent intent = getIntent();
        etMN.setText(intent.getStringExtra("mn"));
        etEN.setText(intent.getStringExtra("en"));
    }

    public void onClick(View view) {
        Log.i("test","does work");
        switch(view.getId()){
            case R.id.cancel_Button:

                    finish();
                break;
            case R.id.update_Button:
                SQLiteDatabase db = DBHandler.getWritableDatabase();
                ContentValues contentValues = new ContentValues(2);
                contentValues.put("wordMN",etMN.getText().toString());
                contentValues.put("wordEN",etEN.getText().toString());
                db.update("words",contentValues,"ID="+getIntent().getIntExtra("id",0),null );
                finish();
                break;
        }
    }
}