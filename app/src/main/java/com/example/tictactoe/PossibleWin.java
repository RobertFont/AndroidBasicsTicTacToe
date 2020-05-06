package com.example.tictactoe;

class PossibleWin {
    private int first;
    private int second;
    private int third;

    /*alt+insert or RMB to generate a constructor*/

    public PossibleWin(int first, int second, int third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }

    public int getThird() {
        return third;
    }
}
