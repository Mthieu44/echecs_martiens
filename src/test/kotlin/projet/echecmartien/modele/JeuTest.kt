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
        j.initialiserPartie(joueur1, joueur2, 3)
        j.deplacer(1, 5, 0, 4)
        j.deplacer(2, 2, 3, 3)
        assertFalse(j.arretPartie())
        j.deplacer(0, 4, 1, 5)
        assertTrue(j.arretPartie())
    }

    @Test
    fun arretPartieJoueur1Vide() {
        val j = Jeu()
        val joueur1 = Joueur("zzz")
        val joueur2 = Joueur("kkk")
        j.initialiserPartie(joueur1, joueur2, 10)
        j.deplacer(1, 5, 0, 4)
        j.deplacer(2, 2, 3, 3)

        // On supprimer tous les pions du plateau sauf 2 petits pions, un de chaque côté
        for (colonne in j.getPlateau().getCases().indices){
            for (ligne in j.getPlateau().getCases()[0].indices){
                if (ligne != 3 && ligne != 4)
                    j.getPlateau().getCases()[colonne][ligne].setPion(null)
            }
        }

        j.deplacer(0, 4, 1, 3)
        assertTrue(j.arretPartie())
    }

    @Test
    fun arretPartieJoueur2Vide() {
        val j = Jeu()
        val joueur1 = Joueur("zzz")
        val joueur2 = Joueur("kkk")
        j.initialiserPartie(joueur1, joueur2, 10)
        j.deplacer(1, 5, 0, 4)
        j.deplacer(2, 2, 3, 3)

        // On supprimer tous les pions du plateau sauf 2 petits pions, un de chaque côté
        for (colonne in j.getPlateau().getCases().indices){
            for (ligne in j.getPlateau().getCases()[0].indices){
                if (ligne != 3 && ligne != 4)
                    j.getPlateau().getCases()[colonne][ligne].setPion(null)
            }
        }

        j.deplacer(0, 4, 1, 5)
        assertFalse(j.arretPartie())
        j.deplacer(3, 3, 2, 4)
        assertTrue(j.arretPartie())
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
    fun joueurVainqueur1() {
        val j = Jeu()
        val joueur1 = Joueur("zzz")
        val joueur2 = Joueur("kkk")
        j.initialiserPartie(joueur1, joueur2, 10)
        joueur1.ajouterPionCaptures(PetitPion())
        assertEquals(joueur1, j.joueurVainqueur())
    }

    @Test
    fun joueurVainqueur2() {
        val j = Jeu()
        val joueur1 = Joueur("zzz")
        val joueur2 = Joueur("kkk")
        j.initialiserPartie(joueur1, joueur2, 10)
        joueur2.ajouterPionCaptures(GrandPion())
        assertEquals(joueur2, j.joueurVainqueur())
    }
    @Test
    fun joueurVainqueurEgal() {
        val j = Jeu()
        val joueur1 = Joueur("zzz")
        val joueur2 = Joueur("kkk")
        j.initialiserPartie(joueur1, joueur2, 10)
        joueur2.ajouterPionCaptures(GrandPion())
        joueur1.ajouterPionCaptures(PetitPion())
        joueur1.ajouterPionCaptures(MoyenPion())
        assertNull(j.joueurVainqueur())
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
    fun deplacementPossible5ParamsPasPossibleHorizontalPetitPion() {
        val j = Jeu()
        val joueur1 = Joueur("zzz")
        val joueur2 = Joueur("kkk")
        j.initialiserPartie(joueur1, joueur2, 5)
        assertFalse(j.deplacementPossible(1,5,0,5,joueur1))
    }

    @Test
    fun deplacementPossible5ParamsPasPossibleVerticalPetitPion() {
        val j = Jeu()
        val joueur1 = Joueur("zzz")
        val joueur2 = Joueur("kkk")
        j.initialiserPartie(joueur1, joueur2, 5)
        assertFalse(j.deplacementPossible(1,5,1,4,joueur1))
    }

    @Test
    fun deplacementPossible5ParamsPasPossiblePionSurLeChemin() {
        val j = Jeu()
        val joueur1 = Joueur("zzz")
        val joueur2 = Joueur("kkk")
        j.initialiserPartie(joueur1, joueur2, 5)
        j.getPlateau().getCases()[3][6].setPion(null)
        assertFalse(j.deplacementPossible(3,7,3,3,joueur1))
    }

    @Test
    fun deplacementPossible5ParamsPasPossiblePionArriveDeZone() {
        val j = Jeu()
        val joueur1 = Joueur("zzz")
        val joueur2 = Joueur("kkk")
        j.initialiserPartie(joueur1, joueur2, 5)
        j.deplacer(3,5,3,3)
        assertFalse(j.deplacementPossible(3,3,3,5,joueur2))
    }


    @Test
    fun deplacerSurUnPion() {
        val j = Jeu()
        val joueur1 = Joueur("zzz")
        val joueur2 = Joueur("kkk")
        j.initialiserPartie(joueur1, joueur2, 5)
        j.deplacer(3,5,2,5)
        assertFalse(j.getPlateau().getCases()[3][5].estLibre())
        assertEquals(PetitPion(),j.getPlateau().getCases()[2][5].getPion())
    }

    @Test
    fun deplacerMauvaisJoueur() {
        val j = Jeu()
        val joueur1 = Joueur("zzz")
        val joueur2 = Joueur("kkk")
        j.initialiserPartie(joueur1, joueur2, 5)
        j.deplacer(0,2,0,3)
        assertTrue(j.getPlateau().getCases()[0][3].estLibre())
        assertEquals(MoyenPion(),j.getPlateau().getCases()[0][2].getPion())
    }

    @Test
    fun deplacerVraiCaseLibre() {
        val j = Jeu()
        val joueur1 = Joueur("zzz")
        val joueur2 = Joueur("kkk")
        j.initialiserPartie(joueur1, joueur2, 5)
        j.deplacer(1,5,0,4)
        assertTrue(j.getPlateau().getCases()[1][5].estLibre())
        assertEquals(PetitPion(),j.getPlateau().getCases()[0][4].getPion())
    }


    @Test
    fun deplacerVraiCoupAvecPrise() {
        val j = Jeu()
        val joueur1 = Joueur("zzz")
        val joueur2 = Joueur("kkk")
        j.initialiserPartie(joueur1, joueur2, 5)
        j.deplacer(1,5,0,4)
        j.deplacer(0,2,0,4)
        assertTrue(j.getPlateau().getCases()[0][2].estLibre())
        assertEquals(MoyenPion(),j.getPlateau().getCases()[0][4].getPion())
    }

    @Test
    fun deplacerImpossiblePionArriveDeZone() {
        val j = Jeu()
        val joueur1 = Joueur("zzz")
        val joueur2 = Joueur("kkk")
        j.initialiserPartie(joueur1, joueur2, 5)
        j.deplacer(1,5,0,4)
        j.deplacer(0,2,0,4)
        j.deplacer(0,4,0,3)
        assertTrue(j.getPlateau().getCases()[0][3].estLibre())
        assertEquals(MoyenPion(),j.getPlateau().getCases()[0][4].getPion())
    }

}