package edu.miamioh;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Consider the problem of neatly printing a paragraph with a monospaced font
 * (all characters having the same width) on a printer. The input text is a
 * sequence of n words of lengths l[0], l[1], ,,, l[n-1] , measured in characters.
 * We want to print this paragraph neatly on a number of lines that hold a maximum
 * of M characters each. Our criterion of “neatness” is as follows. If a given
 * line contains words i through j, where i <= j , and we leave exactly one space
 * between words, the number of extra space characters at the end of the line is
 * [EDIT: you are to figure this out], which must be nonnegative so that the words
 * fit on the line. We wish to minimize the sum, over all lines except the last,
 * of the cubes of the numbers of extra space characters at the ends of lines.
 * Give a dynamic-programming algorithm to print a paragraph of n words neatly
 * on a printer. Analyze the running time and space requirements of your algorithm.
 *
 * NOTE: I have provided two files (magna_carta.txt and kubla_kahn.txt). I know what
 * the correct cost is for each, and I know the approximate running
 * time (if you mess up DP then you will be off by several orders of magnitude).
 */
public class Main {

    static int INFINITY = Integer.MAX_VALUE;
    static int MAXLINE = 80;

    static String[] readWordsFromFile(String path) throws IOException {
        Scanner scanner = new Scanner(new File(path));
        ArrayList<String> words = new ArrayList<String>();

        while (scanner.hasNext())
            words.add(scanner.next());

        return words.toArray(new String[words.size()]);
    }


    static int unusedSpace(int i, int j, int[] cumSumOfWordLengths, int maxline){

        return 0; //If S[k] is the cumulative sum of word lengths up to word k, or 0 if k < 0
                  //Then this should be the value M - (S[j] - S[i-1] + j - i)
    }

    static int printNeatly(String[] words, int maxline, OutputStream out){
        // Minimize the cost of printing the words.
        // The cost is the amount of unused space on each line (cubed),
        // or INFINITY of the words don't fit.
        // The last line has no cost.
        // (e.g. there is no penalty for unused spaces on the last line).


        return INFINITY; //The total cost (sum of unusedSpace^3 for all but last line)
    }

    static boolean checkThatNoLinesStartOrEndWithSpaces(String text){
        return true; // false means extra spaces found
    }

    static boolean checkThatNoMoreThanOneSpaceBetweenWords(String text){

        return true; //false means multiple spaces in a row were found
    }

    static boolean checkThatAllLinesAreLessThanMax(String text, int maxline){
        return true; // false means you output a line that was too long
    }

    static int computeCostFromOutput(String text, int maxline){
        return INFINITY;  // Compute the cost to verify that
                          // it matches what we get from printNeatly
    }

    public static void main(String[] args) {

        for (String arg:args) {
            try {
                String[] words = readWordsFromFile(arg);


                ByteArrayOutputStream result = new ByteArrayOutputStream();
                long trials = 0;
                long total = 0;
                long stop = 0;
                long start = System.currentTimeMillis();
                long cost = 0;
                while (total < 30*1000){
                    result.reset();
                    cost = printNeatly(words, MAXLINE,  result);
                    stop = System.currentTimeMillis();
                    total = stop-start;
                    trials += 1;
                }
                float average = total / (float)trials;

                String text = result.toString();
                assert checkThatAllLinesAreLessThanMax(text, MAXLINE);
                assert checkThatNoLinesStartOrEndWithSpaces(text);
                assert checkThatNoMoreThanOneSpaceBetweenWords(text);
                assert computeCostFromOutput(text, MAXLINE) == cost;

                System.out.print(arg);
                System.out.print(", average running time: ");
                System.out.println(average);
                System.out.print(", cost: ");
                System.out.println(cost);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
