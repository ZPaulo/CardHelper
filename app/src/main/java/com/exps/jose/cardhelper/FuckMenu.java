package com.exps.jose.cardhelper;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static android.widget.LinearLayout.VERTICAL;


public class FuckMenu extends ActionBarActivity {

    private List<EditText> editTextList = new ArrayList<EditText>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout linearLayout = new LinearLayout(this);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT);
        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(VERTICAL);

        int count = 7;
        linearLayout.addView(plNames(count));
        linearLayout.addView(submitButton());
        setContentView(linearLayout);
    }

    private Button submitButton() {
        Button button = new Button(this);
        button.setHeight(WRAP_CONTENT);
        button.setText("Start");
        button.setOnClickListener(startListener);
        return button;
    }

    // Access the value of the EditText

    private View.OnClickListener startListener = new View.OnClickListener() {
        public void onClick(View view) {
            Vector players = new Vector();
            for (EditText editText : editTextList) {
                String s = editText.getText().toString();
                if(!(s.equals("") || s.trim().length() == 0)){
                    players.add(s);
                }
            }
            if(players.size() < 2){
                alertMess("There must be at least 2 players!");
            }
            else {
                String[] res = new String[players.size()];
                for (int i = 0; i < players.size(); i++) {
                    res[i] = players.get(i).toString();
                }
                startGame(res);
            }
        }
    };

    public void startGame(String[] players){
        Bundle b = new Bundle();
        b.putStringArray("players", players);
        Intent i=new Intent(this, FuckGame.class);
        i.putExtras(b);
        startActivity(i);
    }

    public void alertMess(String s) {
        new AlertDialog.Builder(this)
                .setTitle("Warning")
                .setMessage(s)
                .setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
    private RelativeLayout plNames(int count) {
        RelativeLayout rl = new RelativeLayout(this);

        for (int i = 0; i < count; i++) {
          EditText editText = new EditText(this);

            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(WRAP_CONTENT,WRAP_CONTENT);
          if(i != 0) {
              params.addRule(RelativeLayout.BELOW, editTextList.get(i-1).getId());
          }
          editText.setId(Integer.valueOf(10 + i));
          editText.setHint("Jogador " + String.valueOf(i + 1));
          editText.setLayoutParams(params);
          editTextList.add(editText);
          rl.addView(editText);
        }
        return rl;
    }
}
