package projet.echecmartien.modele

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class CaseTest {

    @Test
    fun estLibreVrai() {
        val c = Case()
        assertTrue(c.estLibre())
    }

    @Test
    fun estLibreFaux() {
        val c = Case()
        c.setPion(PetitPion())
        assertFalse(c.estLibre())
    }

    @Test
    fun getJoueurNull() {
        val c = Case()
        assertEquals(null, c.getJoueur())
    }


    @Test
    fun setJoueurNull() {
        val c = Case()
        c.setJoueur(null)
        assertEquals(null, c.getJoueur())
    }

    @Test
    fun setEtGetJoueurPasNull() {
        val c = Case()
        c.setJoueur(Joueur("zzz"))
        assertEquals(Joueur("zzz"), c.getJoueur())
    }

    @Test
    fun getPionNull() {
        val c = Case()
        assertEquals(null, c.getPion())
    }

    @Test
    fun setPionNull() {
        val c = Case()
        c.setPion(null)
        assertEquals(null, c.getPion())
    }

    @Test
    fun setEtGetPionPasNull() {
        val c = Case()
        c.setPion(PetitPion())
        assertEquals(PetitPion(), c.getPion())
    }
}