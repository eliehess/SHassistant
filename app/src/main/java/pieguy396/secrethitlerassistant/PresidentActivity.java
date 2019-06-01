package pieguy396.secrethitlerassistant;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by eliem on 3/20/2018.
 */

public class PresidentActivity extends AppCompatActivity {
    private static final String KEY_TEXT = "pieguy396.text";

    private Button m5Button, m6Button, m7Button, m8Button, m9Button, m10Button;
    private TextView mDisplay;
    private String mText = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_president);

        mDisplay = findViewById(R.id.president_display);

        m5Button = findViewById(R.id.player_button_5);
        m5Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mText = generatePositionString(5);
                mDisplay.setText(mText);
            }
        });

        m6Button = findViewById(R.id.player_button_6);
        m6Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mText = generatePositionString(6);
                mDisplay.setText(mText);
            }
        });

        m7Button = findViewById(R.id.player_button_7);
        m7Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mText = generatePositionString(7);
                mDisplay.setText(mText);
            }
        });

        m8Button = findViewById(R.id.player_button_8);
        m8Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mText = generatePositionString(8);
                mDisplay.setText(mText);
            }
        });

        m9Button = findViewById(R.id.player_button_9);
        m9Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mText = generatePositionString(9);
                mDisplay.setText(mText);
            }
        });

        m10Button = findViewById(R.id.player_button_10);
        m10Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mText = generatePositionString(10);
                mDisplay.setText(mText);
            }
        });

        setButtonVisibility(View.VISIBLE);

        if (savedInstanceState != null) {
            mText = savedInstanceState.getString(KEY_TEXT);
            mDisplay.setText(mText);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(KEY_TEXT, mText);
    }

    private void setButtonVisibility(int visibility) {
        m5Button.setVisibility(visibility);
        m6Button.setVisibility(visibility);
        m7Button.setVisibility(visibility);
        m8Button.setVisibility(visibility);
        m9Button.setVisibility(visibility);
        m10Button.setVisibility(visibility);
    }

    private String generatePositionString(int numPlayers) {
        String fin;
        int pos = InitializerActivity.randomWithRange(1, numPlayers);

        if (pos <= 0 || pos > numPlayers)
            fin = "ERROR";
        else if (pos == numPlayers)
            fin = "You!";
        else if (pos == 1)
            fin = "the person to your left.";
        else if (pos == (numPlayers - 1))
            fin = "the person to your right.";
        else if (pos < ((numPlayers + 1) / 2))
            fin = pos + " people to your left.";
        else
            fin = (numPlayers - pos) + " people to your right.";

        return fin;
    }
}
