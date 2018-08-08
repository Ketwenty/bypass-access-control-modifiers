package org.ketwenty;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

/**
 * @author ketwenty
 *
 */
public class BypassACMTest {

	@Test
	public void accederMethodePrivee_test() {

		ClasseAvecMethodePrivees instance = new ClasseAvecMethodePrivees();

		// cas 1 : methode sans paramètres
		Integer resultat = (Integer) BypassACM.accederMethodePrivee(instance, "methodePrivee", new HashMap<>());
		assertTrue(resultat == 5);

		ClasseAvecMethodePrivees instance2 = new ClasseAvecMethodePrivees();

		// cas 2 : methode avec paramètres
		String nom = "Kevin";
		List<Integer> virements = Arrays.asList(100, 205);
		Map<Class<?>, Object> arguments = new HashMap<>();
		arguments.put(String.class, nom);
		arguments.put(List.class, virements); // On spécifie bien List et non ArrayList
		String resultat2 = (String) BypassACM.accederMethodePrivee(instance2, "methodePriveeAvecArguments", arguments);
		assertTrue(resultat2.equals("Kevin : 305"));
	}

	/**
	 * Le test va lever une NoSuchMethodException car le polimmorphisme n'est pas
	 * pris en charge
	 */
	@Test
	public void accederMethodePrivee_heritage_test() {
		// cas 3 : heritage
		ClasseAvecMethodePriveesParametres campp = new ClasseAvecMethodePriveesParametres(1);
		String resultat = (String) BypassACM.accederMethodePrivee(campp, "methodePrivee", new HashMap<>());
		assertTrue(resultat.equals("NoSuchMethodException | SecurityException"));
		fail();
	}

}
