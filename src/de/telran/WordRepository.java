package de.telran;

import java.util.Random;

public class WordRepository {

    private Word[] words = {
            new Word("apple", "A sweet, edible fruit produced by an apple tree "),
            new Word("peach", "A deciduous tree native to the region of Northwest China between the Tarim Basin and the north slopes of the Kunlun Mountains"),
            new Word("orange", "The fruit of the citrus species Citrus × sinensis in the family Rutaceae, native to China."),
            new Word("pineapple", "a tropical plant with an edible fruit and the most economically significant plant in the family Bromeliaceae")
    };

    public Word getRandomWord() {
        Random rnd = new Random();
        int index = rnd.nextInt(words.length);
        return words[index];
    }
}
