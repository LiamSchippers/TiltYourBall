package com.example.gridtest.playground;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.gridtest.R;
import com.example.gridtest.playground.database.MyDatabase;
import com.example.gridtest.playground.model.Player;

import java.util.ArrayList;
import java.util.List;

import static com.example.gridtest.playground.MainActivity.myDatabase;

/* De highscore activity laat de top 5 scores met namen van de spelers zien,
 * de gebruiker heeft de mogelijkheid om een level te kiezen waar hij/zij de top 5 van wil zien.
 */
public class HighscoreActivity extends Activity implements AdapterView.OnItemSelectedListener{
    TextView name1ST, name2ND, name3RD, name4TH, name5TH;
    TextView points1ST, points2ND, points3RD, points4TH, points5TH;
    TextView levelNumber;
    String[] scores, names;
    Spinner spinner;

    /* Instantieert de textviews en de spinner
     * Haalt de scores met namen op van level 1 en laat die zien
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //hide title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //set app to full screen and keep screen on
        getWindow().setFlags(0xFFFFFFF, WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        name1ST = findViewById(R.id.textViewName1ST);
        name2ND = findViewById(R.id.textViewName2ND);
        name3RD = findViewById(R.id.textViewName3RD);
        name4TH = findViewById(R.id.textViewName4TH);
        name5TH = findViewById(R.id.textViewName5TH);

        points1ST = findViewById(R.id.textViewPoints1ST);
        points2ND = findViewById(R.id.textViewPoints2ND);
        points3RD = findViewById(R.id.textViewPoints3RD);
        points4TH = findViewById(R.id.textViewPoints4TH);
        points5TH = findViewById(R.id.textViewPoints5TH);

        spinner = findViewById(R.id.spinner);
        levelNumber = findViewById(R.id.textViewLevelNumber);

        String[] levels = {"Level 1", "Level 2", "Level 3", "Level 4", "Level 5"};

        clearScores();
        scores = MainActivity.myDatabase.getScores(1);
        names = MainActivity.myDatabase.getNames(1);
        setTextViews(names, scores);
        levelNumber.setText("1");

        spinner.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, levels);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aa);
    }

    // Zet de namen en scoren van de top 5 spelers in de textviews in de highscores
    private void setTextViews(String[] names, String[] scores){
        for(int i = 0 ; i < names.length; i++){
            if(i == 0){
                name1ST.setText(names[i]);
                points1ST.setText(scores[i]);
            }
            if(i == 1){
                name2ND.setText(names[i]);
                points2ND.setText(scores[i]);
            }
            if(i == 2){
                name3RD.setText(names[i]);
                points3RD.setText(scores[i]);
            }
            if(i == 3){
                name4TH.setText(names[i]);
                points4TH.setText(scores[i]);
            }
            if(i == 4){
                name5TH.setText(names[i]);
                points5TH.setText(scores[i]);
            }
        }
    }

    // Zet alle textviews leeg
    private void clearScores(){
        name1ST.setText(" ");
        name2ND.setText(" ");
        name3RD.setText(" ");
        name4TH.setText(" ");
        name5TH.setText(" ");

        points1ST.setText(" ");
        points2ND.setText(" ");
        points3RD.setText(" ");
        points4TH.setText(" ");
        points5TH.setText(" ");
    }

    /* Wanneer een level wordt geselecteerd bij de spinner, worden de scores worden van dat level opgehaald
     * uit de database en in de textviews gezet.
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        clearScores();
        scores = myDatabase.getScores((position+1));
        names = myDatabase.getNames((position+1));
        setTextViews(names, scores);
        if(position == 0){
            levelNumber.setText("1");
        }
        else if (position == 1){
            levelNumber.setText("2");
        }
        else if (position == 2){
            levelNumber.setText("3");
        }
        else if (position == 3){
            levelNumber.setText("4");
        }
        else if (position == 4){
            levelNumber.setText("5");
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
