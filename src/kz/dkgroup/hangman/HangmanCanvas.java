package kz.dkgroup.hangman;

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {

    /* Constants for the simple version of the picture (in pixels) */
    private static final int SCAFFOLD_HEIGHT = 360;
    private static final int BEAM_LENGTH = 144;
    private static final int ROPE_LENGTH = 18;
    private static final int HEAD_RADIUS = 36;
    private static final int BODY_LENGTH = 144;
    private static final int ARM_OFFSET_FROM_HEAD = 28;
    private static final int UPPER_ARM_LENGTH = 72;
    private static final int LOWER_ARM_LENGTH = 44;
    private static final int HIP_WIDTH = 36;
    private static final int LEG_LENGTH = 108;
    private static final int FOOT_LENGTH = 28;

    /**
     * Resets the display so that only the scaffold appears
     */
    public void reset() {

        double topX = getWidth() / 2 - UPPER_ARM_LENGTH - HEAD_RADIUS * 2;
        double topY = getHeight() / 2 - BODY_LENGTH - HEAD_RADIUS * 2 - ROPE_LENGTH;
        double bottomY = topY + SCAFFOLD_HEIGHT;
        GLine scaffold = new GLine(topX, topY, topX, bottomY);
        add(scaffold);
        double rightX = topX + BEAM_LENGTH;
        GLine beam = new GLine(topX, topY, rightX, topY);
        add(beam);
        double bottomRopeY = topY + ROPE_LENGTH;
        GLine rope = new GLine(rightX, topY, rightX, bottomRopeY);
        add(rope);
    }

    /**
     * Updates the word on the screen to correspond to the current
     * state of the game. The argument string shows what letters have
     * been guessed so far; unguessed letters are indicated by hyphens.
     */
    public void displayWord(String word) {
        //adds the label with the correctly guessed letters
        double x = getWidth() / 4;
        double y = getHeight() - HEAD_RADIUS * 2;
        GLabel unGuessedWord = new GLabel(word, x, y);
        //removes the latest hidden word and replaces it
        //with the newest one with the new updated guessed letter
        if (getElementAt(x, y) != null) {
            remove(getElementAt(x, y));
        }
        add(unGuessedWord);
    }

    /**
     * Updates the display to correspond to an incorrect guess by the
     * user. Calling this method causes the next body part to appear
     * on the scaffold and adds the letter to the list of incorrect
     * guesses that appears at the bottom of the window.
     */
    public void noteIncorrectGuess(String word) {
        //adds the label with the incorrect letters
        double x = getWidth() / 4;
        double y = getHeight() - HEAD_RADIUS;
        GLabel incorrectLetters = new GLabel(word, x, y);
        //checks to see if there is already a list of incorrect letters in place,
        //and removes them before adding the new string, with the latest letter
        if (getElementAt(x, y) != null) {
            remove(getElementAt(x, y));
        }
        add(incorrectLetters);
        //checks how many incorrect guessed letters there are
        //and draws the next appropriate body part of the kz
        switch (word.length()) {
            case 1:
                drawHead();
                break;
            case 2:
                drawBody();
                break;
            case 3:
                drawLeftArm();
                break;
            case 4:
                drawRightArm();
                break;
            case 5:
                drawLeftLeg();
                break;
            case 6:
                drawRightLeg();
                break;
            case 7:
                drawLeftFoot();
                break;
            case 8:
                drawRightFoot();
                break;
        }

    }

    private void drawHead() {
        double x = getWidth() / 2 - HEAD_RADIUS;
        double y = getHeight() / 2 - BODY_LENGTH - HEAD_RADIUS * 2;
        GOval head = new GOval(x, y, HEAD_RADIUS * 2, HEAD_RADIUS * 2);
        add(head);
    }

    private void drawBody() {
        double x = getWidth() / 2 + UPPER_ARM_LENGTH / 2 - HEAD_RADIUS;
        double topY = getHeight() / 2 - BODY_LENGTH;
        double bottomY = topY + BODY_LENGTH;
        GLine body = new GLine(x, topY, x, bottomY);
        add(body);
    }

    private void drawLeftArm() {
        double startX = getWidth() / 2 + UPPER_ARM_LENGTH / 2 - HEAD_RADIUS;
        double endX = getWidth() / 2 + UPPER_ARM_LENGTH / 2 - HEAD_RADIUS - UPPER_ARM_LENGTH;
        double upperHeightY = getHeight() / 2 - BODY_LENGTH + ARM_OFFSET_FROM_HEAD;
        GLine upperArm = new GLine(startX, upperHeightY, endX, upperHeightY);
        add(upperArm);
        double lowerArmY = upperHeightY + LOWER_ARM_LENGTH;
        GLine lowerArmLine = new GLine(endX, upperHeightY, endX, lowerArmY);
        add(lowerArmLine);
    }

    private void drawRightArm() {
        double startX = getWidth() / 2 + UPPER_ARM_LENGTH / 2 - HEAD_RADIUS;
        double endX = getWidth() / 2 + UPPER_ARM_LENGTH / 2 - HEAD_RADIUS + UPPER_ARM_LENGTH;
        double upperHeightY = getHeight() / 2 - BODY_LENGTH + ARM_OFFSET_FROM_HEAD;
        GLine upperArm = new GLine(startX, upperHeightY, endX, upperHeightY);
        add(upperArm);
        double lowerArmY = upperHeightY + LOWER_ARM_LENGTH;
        GLine lowerArm = new GLine(endX, upperHeightY, endX, lowerArmY);
        add(lowerArm);
    }

    private void drawLeftLeg() {
        double startX = getWidth() / 2 + UPPER_ARM_LENGTH / 2 - HEAD_RADIUS;
        double endX = startX - HIP_WIDTH;
        double heightY = getHeight() / 2;
        GLine line = new GLine(startX, heightY, endX, heightY);
        add(line);
        double legY = heightY + LEG_LENGTH;
        GLine leg = new GLine(endX, heightY, endX, legY);
        add(leg);

    }

    private void drawRightLeg() {
        double startX = getWidth() / 2 + UPPER_ARM_LENGTH / 2 - HEAD_RADIUS;
        double endX = startX + HIP_WIDTH;
        double heightY = getHeight() / 2;
        GLine line = new GLine(startX, heightY, endX, heightY);
        add(line);
        double legEndY = heightY + LEG_LENGTH;
        GLine leg = new GLine(endX, heightY, endX, legEndY);
        add(leg);
    }

    private void drawLeftFoot() {
        double startX = getWidth() / 2 + UPPER_ARM_LENGTH / 2 - HEAD_RADIUS - HIP_WIDTH;
        double endX = startX - FOOT_LENGTH;
        double heightY = getHeight() / 2 + LEG_LENGTH;
        GLine leftFoot = new GLine(startX, heightY, endX, heightY);
        add(leftFoot);
    }

    private void drawRightFoot() {
        double startX = getWidth() / 2 + UPPER_ARM_LENGTH / 2 - HEAD_RADIUS + HIP_WIDTH;
        double endX = startX + FOOT_LENGTH;
        double heightY = getHeight() / 2 + LEG_LENGTH;
        GLine rightFoot = new GLine(startX, heightY, endX, heightY);
        add(rightFoot);
    }
}
