package com.example.tictactoe;

class Player {
    private String symbol;
    /*press alt+enter to refactor victory text*/
    private String victoryText;

    /*player constructor*/
    public Player(String symbol, String victoryText) {
        this.symbol = symbol;
        this.victoryText = victoryText;
    }

    /*alt+insert to create a getter*/
    public String getVictoryText() {
        return victoryText;
    }

    /*we create a getter in order to get access to the private String*/
    public String getSymbol() {
        return symbol;
    }
}
