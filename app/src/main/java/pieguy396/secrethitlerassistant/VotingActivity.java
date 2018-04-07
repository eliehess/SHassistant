package pieguy396.secrethitlerassistant;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by eliem on 3/25/2018.
 */

public class VotingActivity extends AppCompatActivity {
    private static final String KEY_NUM_PLAYERS = "pieguy396.numplayers";
    private static final String KEY_JA_VOTES = "pieguy396.javotes";
    private static final String KEY_NEIN_VOTES = "pieguy396.neinvotes";
    private static final String KEY_VISIBILITY = "pieguy396.visibility";
    private Button m5Button, m6Button, m7Button, m8Button, m9Button, m10Button;
    private TextView mResultText, mVoteCounter, mSelectPlayersText, mPlayerCounter;
    private Button mJaButton, mNeinButton, mRepeatButton;
    private int mNumPlayers = 0;
    private int mJaVotes = 0, mNeinVotes = 0;
    private String mVisibility = "potato";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voting);

        mResultText = (TextView) findViewById(R.id.result_text);
        mVoteCounter = (TextView) findViewById(R.id.vote_counter);
        mPlayerCounter = (TextView) findViewById(R.id.player_counter);

        if(savedInstanceState != null) {
            mJaVotes = savedInstanceState.getInt(KEY_JA_VOTES);
            mNeinVotes = savedInstanceState.getInt(KEY_NEIN_VOTES);
            mVisibility = savedInstanceState.getString(KEY_VISIBILITY);
            mNumPlayers = savedInstanceState.getInt(KEY_NUM_PLAYERS);

            String temp;
            temp = mNumPlayers + " players";
            mPlayerCounter.setText(temp);

            temp = "";
            int totalvotes = mJaVotes + mNeinVotes;
            if(totalvotes > 0)
                temp = "Total votes: " + String.valueOf(totalvotes);
            mVoteCounter.setText(temp);
        }

        mSelectPlayersText = (TextView) findViewById(R.id.select_players_text_voting);

        m5Button = (Button) findViewById(R.id.player_button_5);
        m5Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNumPlayers(5);
            }
        });

        m6Button = (Button) findViewById(R.id.player_button_6);
        m6Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNumPlayers(6);
            }
        });

        m7Button = (Button) findViewById(R.id.player_button_7);
        m7Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNumPlayers(7);
            }
        });

        m8Button = (Button) findViewById(R.id.player_button_8);
        m8Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNumPlayers(8);
            }
        });

        m9Button = (Button) findViewById(R.id.player_button_9);
        m9Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNumPlayers(9);
            }
        });

        m10Button = (Button) findViewById(R.id.player_button_10);
        m10Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNumPlayers(10);
            }
        });

        mJaButton = (Button) findViewById(R.id.ja_button);
        mJaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voteJa();
            }
        });

        mNeinButton = (Button) findViewById(R.id.nein_button);
        mNeinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voteNein();
            }
        });

        mRepeatButton = (Button) findViewById(R.id.vote_again_button);
        mRepeatButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               reset();
           }
       });

        if(mJaVotes + mNeinVotes > 0)
            checkCompletion();

        if(mVisibility.equals("potato")) {
            if (mNumPlayers == 0)
                activePlayerCount();
            else
                activeJaNein();
        } else {
            switch (mVisibility){
                case "P":
                    activePlayerCount();
                case "J":
                    activeJaNein();
                case "F":
                    activeFinalStuff();
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(KEY_NUM_PLAYERS, mNumPlayers);
        savedInstanceState.putInt(KEY_JA_VOTES, mJaVotes);
        savedInstanceState.putInt(KEY_NEIN_VOTES, mNeinVotes);
        savedInstanceState.putString(KEY_VISIBILITY, mVisibility);
    }

    private void voteJa() {
        mJaVotes++;
        checkCompletion();
    }

    private void voteNein() {
        mNeinVotes++;
        checkCompletion();
    }

    private void reset() {
        activeJaNein();
        mJaVotes = mNeinVotes = 0;
        mVoteCounter.setText("");
    }

    private void checkCompletion() {
        int totalVotes = mJaVotes + mNeinVotes;
        String temp = "Total votes: " + String.valueOf(totalVotes);
        mVoteCounter.setText(temp);

        if (totalVotes != mNumPlayers)
            return;

        setPlayerCountVisibilities(View.INVISIBLE);
        setJaNeinVisibilities(View.INVISIBLE);
        setFinalStuffVisibilities(View.VISIBLE);
        mVisibility = "F";

        if(mNeinVotes >= mJaVotes)
            temp = "Nein! (" + mJaVotes + " to " + mNeinVotes + ")";
        else
            temp = "Ja! (" + mJaVotes + " to " + mNeinVotes + ")";

        mResultText.setText(temp);
    }

    private void setNumPlayers(int numPlayers) {
        mNumPlayers = numPlayers;
        String temp = String.valueOf(numPlayers) + " players";
        mPlayerCounter.setText(temp);

        setPlayerCountVisibilities(View.INVISIBLE);
        setJaNeinVisibilities(View.VISIBLE);
        setFinalStuffVisibilities(View.INVISIBLE);
        mVisibility = "J";
    }

    private void setPlayerCountVisibilities(int visibility) {
        m5Button.setVisibility(visibility);
        m6Button.setVisibility(visibility);
        m7Button.setVisibility(visibility);
        m8Button.setVisibility(visibility);
        m9Button.setVisibility(visibility);
        m10Button.setVisibility(visibility);
        mSelectPlayersText.setVisibility(visibility);
    }

    private void setJaNeinVisibilities(int visibility) {
        mJaButton.setVisibility(visibility);
        mNeinButton.setVisibility(visibility);
        mVoteCounter.setVisibility(visibility);
        mPlayerCounter.setVisibility(visibility);
    }

    private void setFinalStuffVisibilities(int visibility) {
        mRepeatButton.setVisibility(visibility);
        mResultText.setVisibility(visibility);
    }

    private void activePlayerCount() {
        setPlayerCountVisibilities(View.VISIBLE);
        setJaNeinVisibilities(View.INVISIBLE);
        setFinalStuffVisibilities(View.INVISIBLE);
        mVisibility = "P";
    }

    private void activeJaNein() {
        setPlayerCountVisibilities(View.INVISIBLE);
        setJaNeinVisibilities(View.VISIBLE);
        setFinalStuffVisibilities(View.INVISIBLE);
        mVisibility = "J";
    }

    private void activeFinalStuff() {
        setPlayerCountVisibilities(View.INVISIBLE);
        setJaNeinVisibilities(View.INVISIBLE);
        setFinalStuffVisibilities(View.VISIBLE);
        mVisibility = "F";
    }
}
