package pieguy396.secrethitlerassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InitializerActivity extends AppCompatActivity {
    private Button mPresidencyButton;
    private Button mVotingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initializer);

        mPresidencyButton = (Button) findViewById(R.id.presidency_button);
        mPresidencyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(new Intent(InitializerActivity.this, PresidentActivity.class));
            }
        });

        mVotingButton = (Button) findViewById(R.id.voting_button);
        mVotingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InitializerActivity.this, VotingActivity.class));
            }
        });
    }

    /**
     * Returns a random integer within the bounds, inclusive.
     * It doesn't actually matter whether min or max is greater
     * @param min the minimum integer to return
     * @param max the maximum integer to return
     * @return a random integer within the bounds, inclusive
     */
    public static int randomWithRange(int min, int max) {
        int range = Math.abs(max - min) + 1;
        return (int) (Math.random() * range) + (min <= max ? min : max);
    }
}
