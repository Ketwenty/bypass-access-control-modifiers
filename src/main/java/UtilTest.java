import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

/**
 * @author ketwenty
 *
 */
public class UtilTest {

	class ClasseAvecMethodePrivees {

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

	class ClasseAvecMethodePriveesParametres extends ClasseAvecMethodePrivees {

		@SuppressWarnings("unused")
		private Integer nombre;

		public ClasseAvecMethodePriveesParametres(Integer nombre) {
			super();
			this.nombre = nombre;
		}

	}

	@Test
	public void accederMethodePrivee_test() {
		ClasseAvecMethodePrivees instance = new ClasseAvecMethodePrivees();

		// cas 1 : methode sans paramètres
		Integer resultat = (Integer) Util.accederMethodePrivee(instance, "methodePrivee", new HashMap<>());
		assertTrue(resultat == 5);

		ClasseAvecMethodePrivees instance2 = new ClasseAvecMethodePrivees();

		// cas 2 : methode avec paramètres
		String nom = "Kevin";
		List<Integer> virements = Arrays.asList(100, 205);
		Map<Object, Class<?>> arguments = new HashMap<>();
		arguments.put(nom, String.class);
		arguments.put(virements, List.class); // On spécifie bien List et non ArrayList
		String resultat2 = (String) Util.accederMethodePrivee(instance2, "methodePriveeAvecArguments", arguments);
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
		String resultat = (String) Util.accederMethodePrivee(campp, "methodePrivee", new HashMap<>());
		assertTrue(resultat.equals("NoSuchMethodException | SecurityException"));
	}

}
