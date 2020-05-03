package sg.edu.np.WhackAMole;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;

import com.example.myapplication.R;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

  
    private static final String TAG = "Whack-A-Mole"; 

    private TextView resultViewer; 
    private Button firstbutton; 
    private Button secondbutton;
    private Button thirdbutton;
    private List<Button> holeList = new ArrayList<>(); 
    private Integer randomisedLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultViewer = (TextView)findViewById(R.id.resultViewer);
        firstbutton = (Button)findViewById(R.id.hole1);
        holeList.add(firstbutton);
        secondbutton = (Button)findViewById(R.id.hole2);
        holeList.add(secondbutton);
        thirdbutton = (Button)findViewById(R.id.hole3);
        holeList.add(thirdbutton);
        Log.v(TAG, "Finished Pre-Initialisation!");
    }

    @Override
    protected void onStart(){
        super.onStart();
        setNewMole();
        
        View.OnClickListener clicker = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button pressedButton = (Button) v;
                Log.v(TAG,"Reached");
                switch (holeList.indexOf(pressedButton)) {
                    case 0:
                        Log.v(TAG,"Left Button Clicked!");
                        break;
                    case 1:
                        Log.v(TAG,"Middle Button Clicked!");
                        break;
                    case 2:
                        Log.v(TAG,"Right Button Clicked!");
                        break;
                    default:
                        Log.v(TAG,"No input found.");
                }

                Integer score = Integer.parseInt(resultViewer.getText().toString());
                switch (pressedButton.getText().toString()) {
                    case "*": //
                        Log.v(TAG,"Successful, points added!");
                        score = score + 1;
                        resultViewer.setText(score.toString());
                        break;
                    case "O":
                        if (score <= 0)
                        {
                            Log.v(TAG,"Reminder: To score points hit the button with the '*' in it");
                            score = 0;
                        }
                        else
                        {
                            Log.v(TAG,"Unsuccessful, points deducted!");
                            score = score - 1; 
                        }
                        resultViewer.setText(score.toString());
                        break;
                    default: 
                        Log.v(TAG,"To score points hit the button with the '*' in it!");
                }
                holeList.get(randomLocation).setText("O"); //Set all other holes as distractions
                setNewMole();
            }
        };
        firstbutton.setOnClickListener(clicker);
        secondbutton.setOnClickListener(clicker);
        thirdbutton.setOnClickListener(clicker);
        
        Log.v(TAG, "Starting GUI!");
    }
    
    @Override
    protected void onPause() {
        super.onPause();
        Log.v(TAG,"Pausing the game.");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(TAG,"Resuming the game.");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(TAG,"Exiting the game.");
    }

    public void setNewMole()
    {
        Random ran = new Random();
        randomLocation = ran.nextInt(3);
        holeList.get(randomLocation).setText("*"); 
    }
}
