package projet.echecmartien.modele

import projet.echecmartien.exeptions.DeplacementExeption
import kotlin.math.abs


/**
 * cette classe permet de tester les déplacements sur le plateau de jeu
 *
 */

class Deplacement(origine: Coordonnee, destination: Coordonnee) {

    private val origine: Coordonnee
    private val destination: Coordonnee


    /**
     * dans le constructeur la validité du déplacement dans la grille est testée
     *@throws DeplacementException si le déplacement n'est ni horizontal, ni vertical est ni diagonal
     * les autres cas lèvent une IllegalArgumentException (peut être mis en place avec "require")
     */
    init {
        this.origine = origine
        this.destination = destination
    }


    /**
     * getter
     * @return la destination de ce déplacement
     */
    fun getDestination(): Coordonnee {
        return destination
    }


    /**
     * getter
     * @return l'origine de ce déplacement
     */
    fun getOrigine(): Coordonnee {
        return origine
    }

    /**
     *méthode qui permet de tester si le déplacement est horizontal
     * @return true si le déplacement est horizontal, false sinon
     */
    fun estHorizontal(): Boolean {
        return origine.getY() == destination.getY()
    }

    /**
     *méthode qui permet de tester si le déplacement est vertical
     * @return true si le déplacement est vertical, false sinon
     */
    fun estVertical(): Boolean {
        return origine.getX() == destination.getX()
    }

    /**
     * méthode qui permet de tester si le déplacement est diagonal
     * @return true si le déplacement est diagonal, false sinon
     */
    fun estDiagonal(): Boolean {
        return abs(origine.getX() - destination.getX()) == abs(origine.getY() - destination.getY())
    }

    /**
     *méthode qui permet de calculer le nombre de case d'un déplacement
     * @return le nombre de case que le pion sera déplacée
     */
    fun longueur(): Int {
        if (estHorizontal())
            return abs(origine.getX() - destination.getX())
        return abs(origine.getY() - destination.getY())
    }


    /**
     * méthode qui permet de déterminer le sens d'un déplacement vertical
     *
     *@return true si le déplacement est positif, false sinon
     */
    fun estVerticalPositif(): Boolean {
        return origine.getY() - destination.getY() < 0 && estVertical()
    }

    /**
     * méthode qui permet de déterminer le sens d'un déplacement horizontal
     *
     * @return true si le déplacement est positif, false sinon
     */
    fun estHorizontalPositif(): Boolean {
        return origine.getX() - destination.getX() < 0 && estHorizontal()
    }

    /**
     * méthode qui permet de déterminer si le sens d'un déplacement diagonal est positif en X et en Y
     *
     * @return true si le déplacement est positif en X et Y, false sinon
     */
    fun estDiagonalPositifXPositifY(): Boolean {
        return origine.getX() - destination.getX() < 0 && origine.getY() - destination.getY() < 0 && estDiagonal()
    }

    /**
     * méthode qui permet de déterminer si le sens d'un déplacement diagonal est négatif en X et positif en Y
     *
     * @return true si le déplacement est négatif en X et positif en Y, false sinon
     */
    fun estDiagonalNegatifXPositifY(): Boolean {
        return origine.getX() - destination.getX() >= 0 && origine.getY() - destination.getY() < 0 && estDiagonal()
    }

    /**
     *
     * méthode qui permet de déterminer si le sens d'un déplacement diagonal est positif en X et négatif en Y
     *
     * @return true si le déplacement est positif en X et négatif en Y, false sinon
     */
    fun estDiagonalPositifXNegatifY(): Boolean {
        return origine.getX() - destination.getX() < 0 && origine.getY() - destination.getY() >= 0 && estDiagonal()
    }

    /**
     * méthode qui permet de déterminer si le sens d'un déplacement diagonal est négatif en X et négatif en Y
     *
     * @return true si le déplacement est négatif en X et négatif en Y, false sinon
     */
    fun estDiagonalNegatifXNegatifY(): Boolean {
        return origine.getX() - destination.getX() >= 0 && origine.getY() - destination.getY() >= 0 && estDiagonal()
    }

    /**
     * donne le chemin de coordonnées que constitue le déplacement
     * du point de départ vers le point d'arrivée si le déplacement demandé est vertical.
     *
     * @return une liste de coordonnées qui constitue le déplacement du point de départ vers le point d'arrivée
     * si le déplacement est vertical. Le point de départ n'est pas stocké dans la liste.
     * @throws DeplacementException est levée si le déplacement n'est pas vertical
     */
    fun getCheminVertical(): List<Coordonnee> {
        if (!estVertical())
            throw DeplacementExeption("Le chemin n'est pas vertical")
        var res = listOf<Coordonnee>()
        if (estVerticalPositif()) {
            for (i in origine.getY() + 1 until destination.getY() + 1) {
                res = res.plus(Coordonnee(origine.getX(), i))
            }
            return res
        }
        for (i in origine.getY() - 1 downTo destination.getY()) {
            res = res.plus(Coordonnee(origine.getX(), i))
        }
        return res
    }


    /**
     * donne le chemin de coordonnées que constitue le déplacement
     * du point de départ vers le point d'arrivée si le déplaceme{"origine Y dépasse"}nt demandé est horizontal.
     *
     * @return une liste de coordonnées qui constitue le déplacement du point de départ vers le point d'arrivée.
     * Le point de départ n'est pas stocké dans la liste.
     * si le déplacement est horizontal
     * @throws DeplacementException est levée si le déplacement n'est pas horizontal
     */
    fun getCheminHorizontal(): List<Coordonnee> {
        if (!estHorizontal())
            throw DeplacementExeption("Le chemin n'est pas horizontal")
        var res = listOf<Coordonnee>()
        if (estHorizontalPositif()) {
            for (i in origine.getX() + 1 until destination.getX() + 1) {
                res = res.plus(Coordonnee(i, origine.getY()))
            }
            return res
        }
        for (i in origine.getX() - 1 downTo destination.getX()) {
            res = res.plus(Coordonnee(i, origine.getY()))
        }
        return res
    }


    /**
     * donne le chemin de coordonnées que constitue le déplacement
     * du point de départ vers le point d'arrivée si le déplacement demandé est diagonal.
     * Le point de départ n'est pas stocké dans la liste.
     *
     * @return une liste de coordonnées qui constitue le déplacement du point de départ vers le point d'arrivée
     * si le déplacement est diagonal
     * @throws DeplacementException est levée si le déplacement n'est pas diagonal
     */
    fun getCheminDiagonal(): List<Coordonnee> {
        if (!estDiagonal())
            throw DeplacementExeption("Le chemin n'est pas diagonal")
        var res = listOf<Coordonnee>()
        if (estDiagonalPositifXPositifY()) {
            for (i in 0 until longueur()) {
                res = res.plus(Coordonnee(origine.getX() + i + 1, origine.getY() + i + 1))
            }
            return res
        }
        if (estDiagonalNegatifXNegatifY()) {
            for (i in 0 until longueur()) {
                res = res.plus(Coordonnee(origine.getX() - 1 - i, origine.getY() - i - 1))
            }
            return res
        }
        if (estDiagonalPositifXNegatifY()) {
            for (i in 0 until longueur()) {
                res = res.plus(Coordonnee(origine.getX() + 1 + i, origine.getY() - i - 1))
            }
            return res
        }
        for (i in 0 until longueur()) {
            res = res.plus(Coordonnee(origine.getX() - 1 - i, origine.getY() + i + 1))
        }
        return res
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Deplacement)
            return false
        return origine == other.origine && destination == other.destination
    }


}
