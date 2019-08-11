package kz.dkgroup.hangman;

import acm.program.ConsoleProgram;

import java.awt.*;

public class Client extends ConsoleProgram {
    private HangmanCanvas hangmanCanvas;

    public static void main(String[] args) {
        new Client().start();
    }

    @Override
    public void run() {
        hangmanCanvas = new HangmanCanvas();
        add(hangmanCanvas);
        Game game = new Game(this,hangmanCanvas);
        game.play();
        super.run();
    }
}
