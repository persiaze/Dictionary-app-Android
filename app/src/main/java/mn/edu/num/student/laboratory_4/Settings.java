package mn.edu.num.student.laboratory_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Settings extends AppCompatActivity {

    Button backBtn,updateBtn;
    RadioGroup settingsRadio;
    RadioButton monEng,engOnly,monOnly;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        backBtn = findViewById(R.id.backBtn);
        updateBtn = findViewById(R.id.updateBtn);
        settingsRadio = findViewById(R.id.settingsRadio);
        monEng = findViewById(R.id.radioButton);
        monOnly = findViewById(R.id.radioButton2);
        engOnly = findViewById(R.id.radioButton3);

        sp = getApplicationContext().getSharedPreferences("Settings", Context.MODE_PRIVATE);
        String visibility_mode = sp.getString("Visibility_mode","");
        switch(visibility_mode){
            case "MonEng":
                settingsRadio.check(R.id.radioButton);
                break;
            case "Mon":
                settingsRadio.check(R.id.radioButton2);
                break;
            case "Eng":
                settingsRadio.check(R.id.radioButton3);
                break;
        }
    }
    public void onClick(View view) {
        SharedPreferences.Editor myEdit = sp.edit();
        switch(view.getId()){
            case R.id.backBtn:
                    finish();
                break;
            case R.id.updateBtn:
                    if(settingsRadio.getCheckedRadioButtonId() == R.id.radioButton){
                        myEdit.putString("Visibility_mode","MonEng");
                    }else if(settingsRadio.getCheckedRadioButtonId() == R.id.radioButton2){
                        myEdit.putString("Visibility_mode","Mon");
                    }else{
                        myEdit.putString("Visibility_mode","Eng");
                    }
                    myEdit.commit();
                    finish();
                break;
        }
    }

}