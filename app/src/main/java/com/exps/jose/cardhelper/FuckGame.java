package com.exps.jose.cardhelper;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static android.widget.LinearLayout.VERTICAL;


public class FuckGame extends ActionBarActivity {

    int round, activePl;
    String[] players;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b=this.getIntent().getExtras();
        players=b.getStringArray("players");
        round = 1;
        activePl = 0;




        LinearLayout linearLayout = new LinearLayout(this);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT);
        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(VERTICAL);


        Button button = new Button(this);
        button.setHeight(WRAP_CONTENT);
        button.setText("Start");

        setContentView(R.layout.activity_fuck_game);
        addContentView(linearLayout,params);

        updateTextFields();
    }

    public void updateTextFields(){
        TextView tv = (TextView)findViewById(R.id.activePlayer);
        tv.setText(players[activePl]);


        TextView tv1 = (TextView)findViewById(R.id.roundText);
        char r = ' ';
        if(round < 8)
            r = (char)(round + 48);
        else if(round > 12)
            r = (char)(round + 36);
        else {
            switch (round) {
                case 8:
                    r = 'C';
                    break;
                case 9:
                    r = 'O';
                    break;
                case 10:
                    r = 'P';
                    break;
                case 11:
                    r = 'E';
                    break;
                case 12:
                    r = 'S';
                    break;
            }
        }
       // tv1.setText(r);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fuck_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
