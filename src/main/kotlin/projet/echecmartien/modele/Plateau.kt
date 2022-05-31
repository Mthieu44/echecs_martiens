package projet.echecmartien.modele

import projet.echecmartien.librairie.GeneralData
import projet.echecmartien.librairie.TAILLEHORIZONTALE
import projet.echecmartien.librairie.TAILLEVERTICALE


class Plateau {
    private val tailleHorizontale : Int = TAILLEHORIZONTALE
    private val tailleVerticale : Int = TAILLEVERTICALE
    private val cases : Array<Array<Case>> = Array(tailleHorizontale){ Array(tailleVerticale){Case()}}



    /**
     * initialise le plateau de jeu avec les pions
     */
    fun initialiser() {
        val t = GeneralData().tableau
        for (i in t.indices){
            for (j in t[i].indices){
                when (t[i][j].valeur) {
                    "G" -> cases[i][j].setPion(GrandPion())
                    "M" -> cases[i][j].setPion(MoyenPion())
                    "P" -> cases[i][j].setPion(PetitPion())
                }
            }
        }
    }



    /**
     * donne la taille horizontale du plateau
     * @return la taille horizontale du plateau
     */
    fun getTailleHorizontale(): Int {
        return tailleHorizontale
    }


    /**
     * donne la taille verticale du plateau
     * @return la taille verticale du plateau
     */
    fun getTailleVerticale(): Int {
       return tailleVerticale
    }


    /**
     * donne le tableau des cases du plateau
     * @return les cases du plateau
     */
    fun getCases(): Array<Array<Case>> {
        return cases
    }


}