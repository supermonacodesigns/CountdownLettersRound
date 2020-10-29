package com.SuperMonacoDesigns.CountdownLettersRound.service;

import java.util.Comparator;

public class WordSorter {
    public Comparator<String> sortWordsByLongestFirst = new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
            if(o1.length() > o2.length())
                return -1;

            if(o2.length() > o1.length())
                return 1;

            return 1;
        }
    };
}
