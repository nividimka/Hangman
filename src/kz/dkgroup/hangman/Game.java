package kz.dkgroup.hangman;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
public class Game {
    private HangmanLexicon lexicon;
    Scanner scanner;
    HangmanCanvas hangmanCanvas;
    Client client;

    public Game(Client client, HangmanCanvas hangmanCanvas) {
        this.client = client;
        this.hangmanCanvas = hangmanCanvas;
        lexicon = new HangmanLexicon();
        scanner = new Scanner(System.in);
    }

    public void play() {
        hangmanCanvas.reset();
        client.println("Welcome to Hangman!");

        Set<Character> correctLetters = new HashSet<>();
        Set<Character> incorrectLetters = new HashSet<>();
        int guessCount = 8;
        String word = lexicon.getRandomWord();
        boolean wordUnknown = true;
        while (wordUnknown && guessCount != 0) {
            client.print("The word looks like this: ");
            StringBuilder hiddenWord = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                if (correctLetters.contains(Character.toLowerCase(word.charAt(i)))) {
                    hiddenWord.append(word.charAt(i));
                    client.print(word.charAt(i));
                } else {
                    client.print("-");
                    hiddenWord.append("-");
                }
            }
            hangmanCanvas.displayWord(hiddenWord.toString());
            client.println();
            if (guessCount != 1) {
                client.println("You have " + guessCount + " guesses left.");
            } else {
                client.println("You have only one guess left.");
            }
            client.print("You guess:");

            Character guessedChar = client.readLine().charAt(0);
            if (incorrectLetters.contains(Character.toLowerCase(guessedChar)) || correctLetters.contains(Character.toLowerCase(guessedChar))) {
                client.println("You already asked this letter");
                continue;
            }
            if (word.toLowerCase().contains(String.valueOf(Character.toLowerCase(guessedChar)))) {
                client.println("That guess is correct.");
                correctLetters.add(Character.toLowerCase(guessedChar));
            } else {
                client.println("There are no " + guessedChar + "'s in the word.");
                incorrectLetters.add(Character.toLowerCase(guessedChar));
                guessCount--;
            }
            int correctCount = 0;
            for (int i = 0; i < word.length(); i++) {
                if (correctLetters.contains(Character.toLowerCase(word.charAt(i)))) {
                    correctCount++;
                }
            }
            if (correctCount == word.length()) {
                wordUnknown = false;
            }
            StringBuilder letters = new StringBuilder();
            for(Character letter : incorrectLetters){
                letters.append(letter);
            }
            hangmanCanvas.noteIncorrectGuess(letters.toString());
        }
        if (!wordUnknown) {
            client.println("You guessed the word:" + word);
            client.println("You win.");
        }
        if (guessCount == 0) {
            client.println("You're completely hung");
            client.println("The word was:" + word);
            client.println("You lose.");
        }
    }
}
