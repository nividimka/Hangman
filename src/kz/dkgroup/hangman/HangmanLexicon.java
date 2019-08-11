package kz.dkgroup.hangman;

import acm.util.ErrorException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class HangmanLexicon {
    List<String> wordList;

    public HangmanLexicon() {
        try {
            BufferedReader hangmanWords = new BufferedReader(new FileReader("./wordlist.txt"));
            while(true) {
                String line = hangmanWords.readLine();
                if(line == null) break;
                wordList.add(line);
            }
            hangmanWords.close();
        } catch (IOException ex) {
            throw new ErrorException(ex);
        }
    }

    /**
     * Returns the number of words in the lexicon.
     */
    public int getWordCount() {
        return wordList.size();
    }

    /**
     * Returns the word at the specified index.
     */
    public String getWord(int index) {
        return wordList.get(index);
    }

    public String getRandomWord(){
        return getWord(new Random().nextInt(wordList.size()));
    }
}