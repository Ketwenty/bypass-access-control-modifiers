import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 
 */

/**
 * @author ketwenty
 *
 */
public class Util {

	/**
	 * Permet d'appeler une méthode privée depuis une autre classe - ne fonctionne
	 * pas avec l'heritage. Il faut donc spécifier le type du paramètre dans une
	 * association et toujours utiliser la classe mère de la methode comme instance
	 * : celle qui implemente la méthode au niveau le plus haut de la hierarchie
	 * 
	 * @param instance
	 * @param nomMethode
	 * @param associationParametreType
	 * @return le resulat de retour de la methode priveé | null
	 */
	public static Object accederMethodePrivee(Object instance, String nomMethode,
			Map<Object, Class<?>> associationParametreType) {
		Object resultat = null;
		int nombreArguments = 0;
		// Creer la liste de classe a partir de la liste d'argumentsMethode
		Class<?>[] TypesArgument;
		if (associationParametreType != null && (nombreArguments = associationParametreType.entrySet().size()) > 0) {

			TypesArgument = new Class<?>[nombreArguments];
			int i = 0;
			for (Entry<Object, Class<?>> association : associationParametreType.entrySet()) {
				TypesArgument[i++] = association.getValue();
			}

		} else {
			TypesArgument = new Class<?>[0];
		}
		try {
			Method method = instance.getClass().getDeclaredMethod(nomMethode, TypesArgument);
			method.setAccessible(Boolean.TRUE);
			resultat = method.invoke(instance, associationParametreType.keySet().toArray());
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			resultat = "NoSuchMethodException | SecurityException";
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} finally {

		}
		return resultat;

	}
}
