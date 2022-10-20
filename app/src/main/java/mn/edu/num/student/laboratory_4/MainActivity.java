package mn.edu.num.student.laboratory_4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import Word.Word;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ListView wordList;
    FloatingActionButton addWordButton;
    ArrayList<Word> words;
    ArrayAdapter<Word> arrayAdapter;
    private DBHandler DBHandler;
    String DefaultVisibilityMode = "MonEng";
    SharedPreferences sp;
    MenuItem action_settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp = getSharedPreferences("Settings",MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sp.edit();
        myEdit.putString("Visibility_mode", DefaultVisibilityMode);
        myEdit.commit();

        wordList = findViewById(R.id.wordList);
        addWordButton = findViewById(R.id.addWordButton);
        action_settings = findViewById(R.id.action_settings);
    }

    @Override
    protected void onResume() {
        super.onResume();
        refresh();
        wordList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Object o = wordList.getItemAtPosition(position);
                Intent intent = new Intent();
                intent.putExtra("id",words.get(position).getId());
                intent.putExtra("mn",words.get(position).getWord_mn());
                intent.putExtra("en",words.get(position).getWord_en());
                intent.setClass(getApplicationContext(),WordCard.class);
                startActivityForResult(intent,1);
            }
        });

        wordList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                Log.i("test",words.get(i).getWord_en());

                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                //Yes button clicked
                                SQLiteDatabase db = DBHandler.getWritableDatabase();
                                ContentValues contentValues = new ContentValues(1);
                                db.delete("words","id="+words.get(i).getId(),null);
                                refresh();
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                Log.i("test","no");
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setMessage("Устгахдаа итгэлтэй байна уу?\n\n" +
                                "Монгол: " + words.get(i).getWord_mn() + "\n" +
                                "Англи: " + words.get(i).getWord_en() + "\n")
                        .setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search,menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);

        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){

            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                arrayAdapter.getFilter().filter(s);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent i = new Intent(this,Settings.class);
                this.startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent();
        switch(view.getId()){
            case R.id.addWordButton:
                intent.setClass(this,AddWordActivity.class);
                break;
        }
        startActivityForResult(intent,1);
    }

    public void refresh(){
        DBHandler = new DBHandler(this);
        SQLiteDatabase db = DBHandler.getWritableDatabase();
        Cursor result = db.rawQuery("Select * FROM words",null);
        words = new ArrayList<Word>();
        if(result.getCount() > 0){
            for (result.moveToFirst(); !result.isAfterLast(); result.moveToNext()) {
                words.add(new Word(result.getInt(0),result.getString(1),result.getString(2)));
            }
        }
        Log.i("test",words.size() + "-shirheg ug bna.");

        String[] haha = new String[words.size()];
        Word.visibility_mode = sp.getString("Visibility_mode","");

        Log.i("test",sp.getString("Visibility_mode",""));
        arrayAdapter = new ArrayAdapter<Word>(this,android.R.layout.simple_list_item_1, words);
        wordList.setAdapter(arrayAdapter);
    }
}