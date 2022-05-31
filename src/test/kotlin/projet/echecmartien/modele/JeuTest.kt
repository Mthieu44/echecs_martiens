package projet.echecmartien.modele

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

internal class JeuTest {

    @Test
    fun getCoordOrigineDeplacement() {
        val j = Jeu()
        j.initialiserPartie(Joueur("zzz"), Joueur("kkk"), 2)
        assertEquals(null, j.getCoordOrigineDeplacement())
        j.setCoordOrigineDeplacement(Coordonnee(2, 5))
        assertEquals(Coordonnee(2, 5), j.getCoordOrigineDeplacement())
    }

    @Test
    fun getCoordDestinationDeplacement() {
        val j = Jeu()
        j.initialiserPartie(Joueur("zzz"), Joueur("kkk"), 2)
        assertEquals(null, j.getCoordDestinationDeplacement())
        j.setCoordDestinationDeplacement(Coordonnee(2, 5))
        assertEquals(Coordonnee(2, 5), j.getCoordDestinationDeplacement())
    }

    @Test
    fun setCoordOrigineDeplacement() {
        val j = Jeu()
        j.initialiserPartie(Joueur("zzz"), Joueur("kkk"), 2)
        j.setCoordOrigineDeplacement(Coordonnee(2, 3))
        assertEquals(Coordonnee(2, 3), j.getCoordOrigineDeplacement())
    }

    @Test
    fun setCoordDestinationDeplacement() {
        val j = Jeu()
        j.initialiserPartie(Joueur("zzz"), Joueur("kkk"), 2)
        j.setCoordDestinationDeplacement(Coordonnee(2, 3))
        assertEquals(Coordonnee(2, 3), j.getCoordDestinationDeplacement())
    }

    @Test
    fun getJoueurCourant() {
        val j = Jeu()
        val joueur1 = Joueur("zzz")
        val joueur2 = Joueur("kkk")
        j.initialiserPartie(joueur1, joueur2, 2)
        assertEquals(joueur1, j.getJoueurCourant())
    }

    @Test
    fun arretPartieLimiteMouvement() {
        val j = Jeu()
        val joueur1 = Joueur("zzz")
        val joueur2 = Joueur("kkk")
        j.initialiserPartie(joueur1, joueur2, 2)
    }

    @Test
    fun arretPartieJoueur1Vide() {
    }

    @Test
    fun arretPartieJoueur2Vide() {
    }

    @Test
    fun arretPartieNon() {
    }

    @Test
    fun changeJoueurCourant() {
        val j = Jeu()
        val joueur1 = Joueur("zzz")
        val joueur2 = Joueur("kkk")
        j.initialiserPartie(joueur1, joueur2, 2)
        assertEquals(joueur1, j.getJoueurCourant())
        j.changeJoueurCourant()
        assertEquals(joueur2, j.getJoueurCourant())
    }

    @Test
    fun initialiserPartieAppartenanceCaseJoueur() {
        val jeu = Jeu()
        val joueur1 = Joueur("zzz")
        val joueur2 = Joueur("kkk")
        jeu.initialiserPartie(joueur1, joueur2, 2)
        for (i in 0 until 4){
            for (j in 0 until 8){
                if (j<4) {
                    assertEquals(joueur2, jeu.getPlateau().getCases()[i][j].getJoueur())
                }else{
                    assertEquals(joueur1, jeu.getPlateau().getCases()[i][j].getJoueur())
                }
            }
        }

    }

    @Test
    fun joueurVainqueur() {
    }

    @Test
    fun deplacementPossibleOrigineOnlyPossible() {
        val j = Jeu()
        val joueur1 = Joueur("zzz")
        val joueur2 = Joueur("kkk")
        j.initialiserPartie(joueur1, joueur2, 5)
        assertTrue(j.deplacementPossible(1,6))
    }

    @Test
    fun deplacementPossibleOrigineOnlyPasPossibleJoueur() {
        val j = Jeu()
        val joueur1 = Joueur("zzz")
        val joueur2 = Joueur("kkk")
        j.initialiserPartie(joueur1, joueur2, 5)
        assertFalse(j.deplacementPossible(2,2))
    }

    @Test
    fun deplacementPossibleOrigineOnlyPasPossiblePionBloque() {
        val j = Jeu()
        val joueur1 = Joueur("zzz")
        val joueur2 = Joueur("kkk")
        j.initialiserPartie(joueur1, joueur2, 5)
        assertFalse(j.deplacementPossible(3,7))
    }

    @Test
    fun deplacementPossibleOrigineOnlyPasPossiblePasPion() {
        val j = Jeu()
        val joueur1 = Joueur("zzz")
        val joueur2 = Joueur("kkk")
        j.initialiserPartie(joueur1, joueur2, 5)
        assertFalse(j.deplacementPossible(2,4))
    }

    @Test
    fun deplacementPossible5ParamsPossible() {
        val j = Jeu()
        val joueur1 = Joueur("zzz")
        val joueur2 = Joueur("kkk")
        j.initialiserPartie(joueur1, joueur2, 5)
        assertTrue(j.deplacementPossible(3,5,3,3,joueur1))
    }

    @Test
    fun deplacementPossible5ParamsPasPossiblePasJoueurCourant() {
        val j = Jeu()
        val joueur1 = Joueur("zzz")
        val joueur2 = Joueur("kkk")
        j.initialiserPartie(joueur1, joueur2, 5)
        assertFalse(j.deplacementPossible(0,2,0,3,joueur2))
    }

    @Test
    fun deplacementPossible5ParamsPasPossibleMauvaisCote() {
        val j = Jeu()
        val joueur1 = Joueur("zzz")
        val joueur2 = Joueur("kkk")
        j.initialiserPartie(joueur1, joueur2, 5)
        assertFalse(j.deplacementPossible(2,2,3,3,joueur1))
    }

    @Test
    fun deplacementPossible5ParamsPasPossiblePionBloque() {
        val j = Jeu()
        val joueur1 = Joueur("zzz")
        val joueur2 = Joueur("kkk")
        j.initialiserPartie(joueur1, joueur2, 5)
        assertFalse(j.deplacementPossible(3,7,3,6,joueur1))
    }

    @Test
    fun deplacementPossible5ParamsPasPossibleLongueurPetitPion() {
        val j = Jeu()
        val joueur1 = Joueur("zzz")
        val joueur2 = Joueur("kkk")
        j.initialiserPartie(joueur1, joueur2, 5)
        assertFalse(j.deplacementPossible(2,5,0,3,joueur1))
    }

    @Test
    fun deplacementPossible5ParamsPasPossibleLongueurMoyenPion() {
        val j = Jeu()
        val joueur1 = Joueur("zzz")
        val joueur2 = Joueur("kkk")
        j.initialiserPartie(joueur1, joueur2, 5)
        assertFalse(j.deplacementPossible(3,5,3,1,joueur1))
    }

    @Test
    fun deplacer() {
    }
}