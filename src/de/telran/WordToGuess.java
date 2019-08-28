package de.telran;

public class WordToGuess {

    private String word;
    private String description;
    private boolean[] guessed;


    public WordToGuess(String word, String description) {
        this.word = word;
        this.description = description;
        this.guessed = new boolean[word.length()];
    }


    public String getWordWithStars() {
        StringBuilder wordWithStars = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            if (guessed[i]) {
                wordWithStars.append(word.charAt(i));
            } else {
                wordWithStars.append("*");
            }
        }
        return wordWithStars.toString();
    }

    public boolean hasChar(char ch) {
        for (char currentChar: word.toCharArray()) {
            if (ch == currentChar)
                return true;
        }
        return false;
    }

    public void openLetter(char ch) {
        for (int i = 0; i < word.length(); i++) {
            if (ch == word.charAt(i)) {
                guessed[i] = true;
            }
        }
    }

    public boolean openWord(String word) {
        if (this.word.equals(word)) {
            for (int i = 0; i < guessed.length; i++) {
                guessed[i] = true;
            }
            return true;
        }
        return false;
    }

    public String getWord() {
        return word;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WordToGuess wordToGuess1 = (WordToGuess) o;

        if (word != null ? !word.equals(wordToGuess1.word) : wordToGuess1.word != null) return false;
        return description != null ? description.equals(wordToGuess1.description) : wordToGuess1.description == null;

    }

    @Override
    public int hashCode() {
        int result = word != null ? word.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "WordToGuess{" +
                "word='" + word + '\'' +
                ", description='" + description + '\'' +
                "array length=" + guessed.length +
                '}';
    }

    public boolean checkIfGuessed() {
        for (boolean b: guessed) {
            if (!b) return false;
        }
        return true;
    }
}
