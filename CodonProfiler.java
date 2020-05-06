import java.util.*;

public class CodonProfiler {

	/**
	 * Count how many times each codon in an array of codons occurs
	 * in a strand of DNA. Return int[] such that int[k] is number
	 * of occurrences of codons[k] in strand. Strand codons can start
	 * at all valid indexes that are multiples of 3: 0, 3, 6, 9, 12, 15, ...
	 *
	 * @param strand is DNA to be analyzed for codon occurrences.
	 * @param codons is an array of strings, each has three characters
	 * @return int[] such that int[k] is number of occurrences of codons[k] in
	 * strand.
	 */
	/**
	 * Count how many times each codon in an array of codons occurs
	 * in a strand of DNA. Return int[] such that int[k] is number
	 * of occurrences of codons[k] in strand. Strand codons can start
	 * at all valid indexes that are multiples of 3: 0, 3, 6, 9, 12, 15, ...
	 *
	 * @param strand is DNA to be analyzed for codon occurrences.
	 * @param codons is an array of strings, each has three characters
	 * @return int[] such that int[k] is number of occurrences of codons[k] in
	 * strand.
	 * The change that we made was that we used a hashmap where the keys were
	 * each codon in codons and the values were the amount of times they occurred
	 * we then used that map to create the int array we returned using a for loop
	 * and the .get and .put methods of a map
	 */
	public int[] getCodonProfile(IDnaStrand strand, String[] codons) {
		HashMap<String, Integer> map = new HashMap<>();
		int[] ret = new int[codons.length];


		Iterator<Character> iter = strand.iterator();
		while (iter.hasNext()) {
			char a = iter.next();
			char b = 'z';           // not part of any real codon
			char c = 'z';
			if (iter.hasNext()) {
				b = iter.next();
			}
			if (iter.hasNext()) {
				c = iter.next();
			}
			String cod = "" + a + b + c;
			if (map.containsKey(cod)) {
				map.put(cod, map.get(cod) + 1);
			}
			if (!map.containsKey(cod)) {
				map.put(cod, 1);
			}
		}
		for(int i = 0; i < codons.length;i++) {
			if (map.get(codons[i]) != null) {
				ret[i] = map.get(codons[i]);
			}
		}

		return ret;
	}
}
