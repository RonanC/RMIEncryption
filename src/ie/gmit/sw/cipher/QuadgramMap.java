package ie.gmit.sw.cipher;

import java.util.*;
import java.io.*;

public class QuadgramMap {
	private Map<String, Integer> map = new HashMap<String, Integer>();
	// maps an object

	public Map<String, Integer> getMap() {
		return map;
	}

	public QuadgramMap(String filename) throws Exception {
		parse(filename);
	}

	// we will check score moving one char over at a time
	// we decrypt the cypher text with every possible combination of letters,
	// this method gets how often each 4 letters matches the quadgrams generated
	// from the war and peace file
	public float getScore(String text) {
		float total = (float) map.size();
		float score = 0.00f;
		StringBuffer sb = new StringBuffer();

		sb.append(text.substring(0, 3));
		for (int i = 4; i < text.length(); i++) {
			sb.append(text.charAt(i));

			// System.out.println(map.get(sb.toString()));
			if (map.get(sb.toString()) != null) {
				float frequency = (float) map.get(sb.toString());

				score += Math.log10(total / frequency);

				// System.out.println("freq: " + frequency + "\ttotal: " + total
				// + "\tscore: " + score);
			}

			sb.delete(0, 1);
		}

		return score;
	}

	private void parse(String filename) throws Exception {
		// don't need bufferedreader here
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
		StringBuffer sb = new StringBuffer();

		// we don't just want to read the file in 4 char chunks.
		// we want to read a quadgram per index
		// example ANTIDISESTABLISHMENTARIANISM is:
		// ANTI, DISE, STAB
		// we want it to be:
		// ANTI, NTID, TIDI

		// need to be able to parse any file
		int j;
		while ((j = br.read()) != -1) {
			processNextChar(sb, j);
		}
		br.close();

		System.out.println("Quadgram Map created: " + map);
	}

	private void processNextChar(StringBuffer sb, int j) {
		char next = (char) j;
		// key and cipher are upper case so we will read in the file as
		// upper-case
		next = Character.toUpperCase(next);

		// A = 64, Z = 90, we will not keep spaces // || next == ' '
		if (next >= 'A' && next <= 'Z') {
			sb.append(next);
		}
		if (sb.length() == 4) {
			String qGram = sb.toString();

			int frequency = 0;
			if (map.containsKey(qGram)) {
				frequency = map.get(qGram);
			}

			frequency++;
			map.put(qGram, frequency);

			// delete first char
			sb.delete(0, 1);
		}
	}

	public static void main(String[] args) throws Exception {
		new QuadgramMap("./WarAndPeace-Tolstoy.txt");
	}
}
