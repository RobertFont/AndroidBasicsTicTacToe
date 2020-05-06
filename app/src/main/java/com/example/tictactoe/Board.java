package com.example.tictactoe;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

class Board {
    /*we initialize the list with empty values*/
    private List<String> marks = new ArrayList<>(asList("", "", "", "", "", "", "", "", ""));

    /*created possible win class*/
    private List<PossibleWin> possibleWins = new ArrayList<>();

    /*created Board constructor*/
    public Board() {
        /*horizontal win conditions*/
        possibleWins.add(new PossibleWin(0, 1, 2));
        possibleWins.add(new PossibleWin(3, 4, 5));
        possibleWins.add(new PossibleWin(6, 7, 8));

        /*vertical win conditions*/
        possibleWins.add(new PossibleWin(0, 3, 6));
        possibleWins.add(new PossibleWin(1, 4, 7));
        possibleWins.add(new PossibleWin(2, 5, 8));

        possibleWins.add(new PossibleWin(0, 4, 8));
        possibleWins.add(new PossibleWin(2, 4, 6));

    }

    public void mark(Integer position, String symbol) {
        marks.set(position, symbol);
    }

    public boolean hasWon(Player player) {
        /*iterate on the possible wins*/
        for (PossibleWin possibleWin : possibleWins) {
            /*if any of the 3 possitions are the same as the ones with the players symbol then it's true*/
            if (checkIfAreTheSame(possibleWin, player.getSymbol())) {
                return true;

            }
        }
        return false;
    }

    /*control+alt+L let's you reformat the code*/
    private boolean checkIfAreTheSame(PossibleWin possibleWin, String playerSymbol) {
        return  marks.get(possibleWin.getFirst()).equals(playerSymbol) &&
                marks.get(possibleWin.getSecond()).equals(playerSymbol) &&
                marks.get(possibleWin.getThird()).equals(playerSymbol);
    }

    /*method that resets game*/
    public void reset(){
        marks = new ArrayList<>(asList("", "", "", "", "", "", "", "", ""));
    }

    public boolean isFull() {
        for (String mark : marks) {
            if(mark.isEmpty()){
                return false;
            }
        }
        return true;
    }

    public boolean isEmpty(int iaMove) {
        return marks.get(iaMove).isEmpty();
    }
}
