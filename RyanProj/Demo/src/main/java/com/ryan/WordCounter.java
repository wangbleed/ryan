package com.ryan;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by Ryan on 2015/1/21.
 */
public class WordCounter {

    public static void main(String[] args) {
        try {
            List<WordOccurrence> list = WordCounter.doCount("test.txt");
            for (WordOccurrence e : list) System.out.println(e.word + ":" + e.count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The word count container.
     */
    static class WordOccurrence implements Comparable<WordOccurrence> {
        String word;
        int count;

        public WordOccurrence(String word, int count) {
            this.word = word;
            this.count = count;
        }

        /**
         * sort in reverse order.
         */
        @Override
        public int compareTo(WordOccurrence o) {
            if (this.count < o.count) return 1;
            if (this.count > o.count) return -1;
            return 0;
        }
    }

    /**
     * Count the occurrences of each word, and sort them in descending order.
     * @param fileName the name of the file to be parsed
     * @return the word list in descending order of occurrence
     * @throws IOException
     */
    private static List<WordOccurrence> doCount(String fileName) throws IOException {
        File file = new File(fileName);
        Reader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = null;
        Map<String, Integer> wordMap = new HashMap<String, Integer>();
        while ((line = bufferedReader.readLine()) != null) {
            String[] words = line.split(Pattern.compile("[ |,|.|!]").pattern());
            for (int i = 0; i < words.length; i++) {
                if (wordMap.containsKey(words[i])) {
                    wordMap.put(words[i], wordMap.get(words[i]).intValue() + 1);
                } else {
                    wordMap.put(words[i], 1);
                }

            }
        }
        List<WordOccurrence> wordList = new ArrayList();
        for (Iterator iterator = wordMap.keySet().iterator(); iterator.hasNext(); ) {
            String key = (String) iterator.next();
            WordOccurrence wordOccurrence = new WordOccurrence(key, wordMap.get(key).intValue());
            wordList.add(wordOccurrence);
        }
        Collections.sort(wordList);
        return wordList;
    }
}
