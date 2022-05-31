package projet.echecmartien.modele

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class JoueurTest {

    @Test
    fun getPionsCapturesVide() {
        val j = Joueur("zzz")
        assertEquals(setOf<Pion>(), j.getPionsCaptures())
    }

    @Test
    fun getPionsCapturesPasVide() {
        val j = Joueur("zzz")
        j.ajouterPionCaptures(PetitPion())
        j.ajouterPionCaptures(GrandPion())
        j.ajouterPionCaptures(MoyenPion())
        assertEquals(setOf<Pion>(PetitPion(),GrandPion(),MoyenPion()), j.getPionsCaptures())
    }

    @Test
    fun ajouterPionCaptures() {
        val j = Joueur("zzz")
        j.ajouterPionCaptures(PetitPion())
    }

    @Test
    fun getNbPionsCapturesZero() {
        val j = Joueur("zzz")
        assertEquals(0, j.getNbPionsCaptures())
    }

    @Test
    fun getNbPionsCaptures() {
        val j = Joueur("zzz")
        j.ajouterPionCaptures(PetitPion())
        j.ajouterPionCaptures(GrandPion())
        j.ajouterPionCaptures(MoyenPion())
        assertEquals(3, j.getNbPionsCaptures())
    }

    @Test
    fun getPseudo() {
        val j = Joueur("zzz")
        assertEquals("zzz", j.getPseudo())
    }

    @Test
    fun calculerScoreZero() {
        val j = Joueur("zzz")
        assertEquals(0, j.calculerScore())
    }

    @Test
    fun calculerScore() {
        val j = Joueur("zzz")
        j.ajouterPionCaptures(GrandPion())
        j.ajouterPionCaptures(PetitPion())
        j.ajouterPionCaptures(MoyenPion())
        assertEquals(6, j.calculerScore())
    }
}