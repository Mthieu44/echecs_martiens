package projet.echecmartien.modele


/**
 * Classe Pion
 */
 abstract class Pion {
	/**
	 * récupère la valeur du score d'un pion
	 * @return la valeur du score
	 */
	abstract fun getScore():Int


	/**
	 * donne le chemin de coordonnées que constitue le déplacement
	 * du point de départ vers le point d'arrivée. Les déplacements autorisés sont horizontaux, verticaux, diagonaux.
	 *
	 * @param deplacement le déplacement
	 * @return une liste de coordonnées qui constitue le déplacement du point de départ vers le point d'arrivée.
	 * La liste ne contient pas les coordonnées du point de départ.
	 *
	 * @throws DeplacementException est levée si le déplacement n'est pas possible
	 */
	abstract fun getDeplacement(deplacement: Deplacement):List<Coordonnee>

	override fun toString(): String {
		return getScore().toString()
	}

	override fun equals(other: Any?): Boolean {
		if (other !is Pion)
			return false
		return getScore().equals(other.getScore())
	}
   }