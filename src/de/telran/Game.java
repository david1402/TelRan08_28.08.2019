package de.telran;

public class Game {

    private final GameState gameState = new GameState();
    private final PlayersInteraction playersInteraction = new PlayersInteraction();

    private WordRepository wordRepository;
    private WordToGuess wordToGuess;

    public Game() {
        wordRepository = new WordRepository();
    }

    public void startNewGame() {

        Word word = wordRepository.getRandomWord();
        wordToGuess = new WordToGuess(word.getWord(), word.getDescription());
        playersInteraction.InformUserAboutStart(wordToGuess);


        while (gameState.isGameOn()) {
            if (playersInteraction.askPlayerLetterOrWord())
                sayLetter();
            else
                sayWord();
        }
    }

    private void sayWord() {
        String wordFromPlayer = playersInteraction.askPlayerAWord(this);
        if (wordToGuess.getWord().equals(wordFromPlayer)) {
            gameState.playerGuessedWordAndWon();
            informPlayerAboutWin();
        } else {
            gameState.playerGuessedWordAndFail();
            playersInteraction.informPlayerAboutLose(wordToGuess.getWord(), gameState.getPoints());
        }
    }

    private void sayLetter() {
        char letterFromPlayer = playersInteraction.askPlayerALetter();
        if (wordToGuess.hasChar(letterFromPlayer)) {
            gameState.guessedLetter();
            wordToGuess.openLetter(letterFromPlayer);
            playersInteraction.informPlayerAboutSuccess(letterFromPlayer);
            if (wordToGuess.checkIfGuessed()) {
                informPlayerAboutWin();
                gameState.setPlayerWon(true);
            }
        } else {
            gameState.guessedWrongLetter();
            playersInteraction.informPlayerAboutMistake(letterFromPlayer);
            if (gameState.getNumberOfTries() == 0) {
                gameState.setPoints(0);
                playersInteraction.informPlayerAboutLose(wordToGuess.getWord(), gameState.getPoints());
            }
        }
        printNumberOfTriesAndPoints(gameState.getNumberOfTries());
        playersInteraction.showMaskedWord(wordToGuess.getWordWithStars());
    }

    private void informPlayerAboutWin() {
        System.out.println("You guessed the word! You won!" + wordToGuess);
        System.out.println("You won " + gameState.getPoints() + " points");
    }

    private void printNumberOfTriesAndPoints(int numberOfTries) {
        System.out.println("Your number of tries is " + numberOfTries);
        System.out.println("Your number of points is " + gameState.getPoints());
    }

    private char askPlayerALetter() {
        return playersInteraction.askPlayerALetter();
    }

    private boolean askPlayerLetterOrWord() {
        return playersInteraction.askPlayerLetterOrWord();
    }
}
