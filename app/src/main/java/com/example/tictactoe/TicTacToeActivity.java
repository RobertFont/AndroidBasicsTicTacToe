package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
/*imported List*/
import java.util.List;

public class TicTacToeActivity extends AppCompatActivity {

    /*variables*/

    /*public static final String STRING = "X";*/
    public static final String PLAYER_SYMBOL = "X";
    public static final String AI_SYMBOL = "O";
    private TextView gameResultTv;
    /*  == STEP: 02 == Made the array global*/
    private List<Button> buttons;
    private Button startButton;
    private Board board;
    private Player player;
    /*alt+enter to extract resources*/
    private Player aiPlayer;
    private AiMoveGenerator aiMoveGenerator;
    private boolean gameOver = false;

    /*obligatory method*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tictactoe);
        initializeUi();
        /*we created a new class, then used control+alt+F to create the variable above to make it global*/
        /*Board board = new Board();*/
        /*Control + ALT + F to make the variable global*/
        board = new Board();
        aiMoveGenerator = new AiMoveGenerator(board);
        player = new Player(PLAYER_SYMBOL, getString(R.string.victory));
        aiPlayer = new Player(AI_SYMBOL, getString(R.string.lost));

    }

    private void initializeUi() {
        /*variable linked to the ID of a TextView*/
        /* if you press CONTROL+ALT+F you create a global variable for gameResultTv
        TextView gameResultTv = findViewById(R.id.tv_gameResult);*/
        gameResultTv = findViewById(R.id.tv_gameresult);
        /*Alt + Enter in the text "initializeButtons" to create method*/
        initializeButtons();
        initializeStartButton();
    }

    private void initializeStartButton() {
        startButton = findViewById(R.id.b_start);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cleanButtons();
                board.reset();
                gameOver = false;
                gameResultTv.setText("");
                enableButtons();

            }
        });
    }


    private void initializeButtons() {
        /*Created an array of buttons so we can have access in the addButtonsActions method*/
        /*== STEP: 01 == ALT+CONTROL+F to make the array global*/
        /*List<Button> buttons = new ArrayList<>();*/
        buttons = new ArrayList<>();
        /*buttons.add(findViewById(R.id.b_top_left) doesn't work because a List cannot be applied to a view*/
        buttons.add((Button) findViewById(R.id.b_top_left));
        buttons.add((Button) findViewById(R.id.b_top_center));
        buttons.add((Button) findViewById(R.id.b_top_right));

        buttons.add((Button) findViewById(R.id.b_middle_left));
        buttons.add((Button) findViewById(R.id.b_middle_center));
        buttons.add((Button) findViewById(R.id.b_middle_right));

        buttons.add((Button) findViewById(R.id.b_bottom_left));
        buttons.add((Button) findViewById(R.id.b_bottom_center));
        buttons.add((Button) findViewById(R.id.b_bottom_right));
        /*initializes addButtonsActions*/
        addButtonsActions();
    }


    private void addButtonsActions() {
        /*typed 'iter' to create this for*/
        /*no idea why final is in place*/
        for (final Button button : buttons) {
            button.setOnClickListener(new View.OnClickListener() {
                /*anonymous class*/
                @Override
                public void onClick(View clickedview) {
                    /*we casted into a Button*/
                    Button clickedButton = (Button) clickedview;
                    movePlayer(buttons.indexOf(clickedButton));
                    if(!gameOver){
                        moveAi();
                    }
                }

            });
        }
    }

    private void moveAi() {
        Integer aiMove = aiMoveGenerator.getMove();
        if(aiMove != null){
            markButton(buttons.get(aiMove), aiPlayer);
            markBoard(aiMove, aiPlayer);
            /*Control+Alt+M to refactor method*/
            checkWin(aiPlayer);
            checkTie();
        }
    }

    private void movePlayer( int playerMove) {
        markButton(buttons.get(playerMove), player);
        markBoard(playerMove, player);
        /*Control+Alt+M to refactor method*/
        checkWin(player);
        checkTie();
    }

    private void checkTie() {
        if(!gameOver && board.isFull()){
            gameOver = true;
            /*Alt + Enter to extract resources (turn hardcoded string to a resource)*/
            gameResultTv.setText(R.string.tie);
        }

    }

    private void checkWin(Player player) {
        if(board.hasWon(player)){
            gameOver = true;
            /*we get the string from resources*/
            gameResultTv.setText(player.getVictoryText());
            /*ALT+ENTER to create method*/
            disableButtons();
        }
    }

    private void markBoard(int position, Player player) {
        board.mark(position,player.getSymbol());
    }

    private void markButton(Button clickedButton, Player player) {
    /*use ALT+CONTROL+C to make the "X" a constant instead of using
     clickedButton.setText("X");*/
        clickedButton.setText(player.getSymbol());
        /*once selected the bottom cannot be reselected*/
        clickedButton.setClickable(false);
    }

    private void disableButtons() {
        for (Button button : buttons) {
            button.setClickable(false);
        }
    }

    private void enableButtons(){
        for (Button button : buttons) {
            button.setClickable(true);
        }
    }

    /*sets all buttons text's to empty*/
    private void cleanButtons() {
        for (Button button : buttons) {
            button.setText("");
        }
    }
}
