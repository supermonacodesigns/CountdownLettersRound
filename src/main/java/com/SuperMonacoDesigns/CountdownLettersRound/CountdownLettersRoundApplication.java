package com.SuperMonacoDesigns.CountdownLettersRound;

import com.SuperMonacoDesigns.CountdownLettersRound.service.WordSorter;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;


@SpringBootApplication
public class CountdownLettersRoundApplication {

	private static HttpServletResponse response;

	private static final Logger log = LoggerFactory.getLogger(CountdownLettersRoundApplication.class);

	public static void main(String[] args) throws IOException, JSONException {
		SpringApplication.run(CountdownLettersRoundApplication.class, args);

		char[] vowels = {'A','E','I','O','U'};
		char[] consonants = {'B','C','D','F','G','H','J','K','L','M','N','P','Q','R','S','T','V','W','X','Y','Z'};

		WordSorter wordSorter = new WordSorter();

		List<Character> letters = new ArrayList<>();
		TreeSet<String> possibleWords = new TreeSet<>(wordSorter.sortWordsByLongestFirst);
		List<String> longest3Words = new ArrayList<>();



		int remainingTiles;

		Scanner in = new Scanner(System.in);

		/*
		for (remainingTiles = 9; remainingTiles > 0; remainingTiles--) {
			System.out.print("Select vowel [V] or consonant [C] : ");

			char choice = in.next().toUpperCase().charAt(0);

			switch (choice) {
				case 'V':
					int rand = (int) (Math.random() * vowels.length);
					letters.add(vowels[rand]);
					System.out.println(letters);
					break;

				case 'C':
					rand = (int) (Math.random() * consonants.length);
					letters.add(consonants[rand]);
					System.out.println(letters);
					break;
			}
		}

		 */

        letters.add('L');
        letters.add('A');
        letters.add('O');
        letters.add('R');
        letters.add('V');
        letters.add('Z');
        letters.add('Y');
        letters.add('S');
        letters.add('I');


        System.out.println("\n" +letters);
		System.out.println("\nSearching for longest words...");

		Scanner dictionaryReader = new Scanner(new File("words_filtered.txt"));

		while (dictionaryReader.hasNext()) {
			List<Character> candidateLetters = new ArrayList<>(letters);

			String word = dictionaryReader.next().toUpperCase();
			String candidateWord = "";

			for (int i = 0; i < word.length(); i++) {
				if (candidateLetters.contains(word.charAt(i))) {
					candidateWord = candidateWord + word.charAt(i);
					candidateLetters.remove((Character)word.charAt(i));
				}
			}

			if (candidateWord.length() == word.length()) {
				possibleWords.add(candidateWord);
			}
		}

		System.out.println("\n" +possibleWords);

		int j = 0;
		int query = 0;

		while (longest3Words.size() < 1) {

			RestTemplate restTemplate = new RestTemplate();

			String path = "" + possibleWords.first();
			String url = "https://od-api.oxforddictionaries.com/api/v2/entries/en-gb/" + path;

			System.out.println("\nPath = " +path);

			OkHttpClient client = new OkHttpClient().newBuilder()
					.build();
			Request request = new Request.Builder()
					.url(url)
					.method("GET", null)
					.addHeader("app_id", "b378f415")
					.addHeader("app_key", "fbc8a0f062b2d4d78c1383c5c596db3d")
					.build();
			Response response = client.newCall(request).execute();
			String responseString = response.body().string();
			JSONObject json = new JSONObject(responseString);

			query++;

			if (response.code() == 200) {
				longest3Words.add(path);
				System.out.println("\n"+path + " found in Oxford Universities dictionary!");
				System.out.println("\n"+json);
			}

			System.out.println("\nQuery " +query + " processed");
			possibleWords.pollFirst();


			response.close();
			j++;
		}

		System.out.println("\n" +longest3Words);

	}
}
