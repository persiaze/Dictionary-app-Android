package mn.edu.num.student.laboratory_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import Word.Word;

public class AddWordActivity extends AppCompatActivity {

    private DBHandler DBHandler;
    EditText wordMn,wordEn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);
        wordMn = findViewById(R.id.wordMN);
        wordEn = findViewById(R.id.wordEN);
    }

    public void onClick(View view) {
        Log.i("asd","works");

        switch ( view.getId()){
            case R.id.cancelBtn:
                setResult(RESULT_CANCELED);
                finish();
                break;
            case R.id.okBtn:

                if(!wordMn.getText().toString().equals("") && !wordEn.getText().toString().equals("")){
                    ContentValues contentValues = new ContentValues(2);
                    contentValues.put("wordMN",wordMn.getText().toString());
                    contentValues.put("wordEN",wordEn.getText().toString());

                    Log.i("test",contentValues.toString());
                    DBHandler = new DBHandler(this);
                    SQLiteDatabase db = DBHandler.getWritableDatabase();
                    db.insert("words",null,contentValues);
                    finish();
                }else{
                    Toast.makeText(this, "fill out :-)", Toast.LENGTH_SHORT).show();
                }

                setResult(RESULT_OK);

                break;
        }
    }
}