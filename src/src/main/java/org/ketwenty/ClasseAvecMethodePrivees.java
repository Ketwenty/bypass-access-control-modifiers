package org.ketwenty;
import java.util.List;

/**
 * @author ketwenty
 *
 */
public class ClasseAvecMethodePrivees {

	@SuppressWarnings("unused")
	private int methodePrivee() {
		return 5;
	}

	@SuppressWarnings("unused")
	private String methodePriveeAvecArguments(String nom, List<Integer> virements) {
		int somme = virements.stream().reduce((a, b) -> a + b).get();
		return nom + " : " + somme;
	}
}
