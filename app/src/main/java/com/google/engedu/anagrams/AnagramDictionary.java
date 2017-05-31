/* Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.engedu.anagrams;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private Random random = new Random();
    ArrayList<String> arrayListDummy = new ArrayList<>();
    HashSet<String> wordset = new HashSet<>();
    HashMap<String, ArrayList<String>> lettrsToWord = new HashMap<>();

    public AnagramDictionary(Reader reader) throws IOException {
        BufferedReader in = new BufferedReader(reader);
        String line;
	//read line until empty line reached
	// store in hash map, hash set or something : hashmap, hash set
	// hashing : an algo/function : to store data as an array otherwise : 

	while((line = in.readLine()) != null) {
            String word = line.trim();
            wordset.add(word);
        if(lettrsToWord.containsKey(sortWords(word))){
            ArrayList<String> temp = lettrsToWord.get(sortWords(word));
            temp.add(word);
            lettrsToWord.put(sortWords(word), temp);
        } else{
            ArrayList<String> newWord = new ArrayList<>();
            newWord.add(word);
            lettrsToWord.put(sortWords(word), newWord);
        }
	    }
        Log.e("Hashmap Structure", lettrsToWord.toString());
    }

    public boolean isGoodWord(String word, String base) {
        //for(int i = 0 ; i< arrayListDummy.size() ; i++){
        //    Log.e("printing : sorted ", word);
        //    Log.e("value of i", ""+i);
        //    Log.e("printing : res ", arrayListDummy.get(1));
        //    Log.e("printing : word ", word);
            for(int i=0 ; i<arrayListDummy.size() ;i++){
            if(arrayListDummy.get(i).contains(word.trim()) && !word.contains(base))return true;
           }return false;
    }

    public List<String> getAnagrams(String targetWord) {
        ArrayList<String> result = new ArrayList<String>();
        int count = 0;
        char[] a_1 = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        while (count < 26) {
            int i = 0;
            String str = targetWord + a_1[count];
            if (lettrsToWord.containsKey(sortWords(str))) {
                arrayListDummy.add(i, (lettrsToWord.get(sortWords(str))).toString() );
                Log.e("Words : ", arrayListDummy.get(0));
                i++;
                }
            count++;
            }
            return result;
        }

    /*public List<String> getAnagramsWithOneMoreLetter(String word) {
        ArrayList<String> result = new ArrayList<>();
        /*int count = 0;
        char[] a_1 = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        while (count<26){
         String str = word + a_1[count];

            if(lettrsToWord.containsKey(sortWords(str))){
                ArrayList<String> arrayListDummy = new ArrayList<>();
                arrayListDummy = lettrsToWord.get(sortWords(str));
                //for(int i = 0 ; i<arrayListDummy.l ){
                Log.e("Words : ", arrayListDummy.get(0));
                //}
            }
            count++;
        }
        return result;
    } */

    public String pickGoodStarterWord() {
        int size = wordset.size();
        int item = new Random().nextInt(size);
        int i = 0 ;
        for(Object object : wordset){
            if( i == item) {
                Log.e("in pick good starter",""+object.toString());
                return object.toString();
            }i++;
        }
        return "stop";
    }

    public String sortWords(String source){
        char[] sourceWords = source.toCharArray();
        Arrays.sort(sourceWords);
        return new String(sourceWords);
    }
}
