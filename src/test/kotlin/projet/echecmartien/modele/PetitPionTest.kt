package projet.echecmartien.modele

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

import org.junit.jupiter.api.Assertions.*
import projet.echecmartien.exeptions.DeplacementExeption

internal class PetitPionTest {

    @Test
    fun getScore() {
        val p = PetitPion()
        assertEquals(1,p.getScore())
    }

    @Test
    fun getDeplacementExeptionLongeur() {
        val p = PetitPion()
        val d = Deplacement(Coordonnee(0,0), Coordonnee(2,2))
        assertThrows<DeplacementExeption> { p.getDeplacement(d) }
    }

    @Test
    fun getDeplacementExeptionDiagonale() {
        val p = PetitPion()
        val d = Deplacement(Coordonnee(0,0), Coordonnee(0,1))
        assertThrows<DeplacementExeption> { p.getDeplacement(d) }
    }

    @Test
    fun getDeplacementExeptionDeplacement() {
        val p = PetitPion()
        val d = Deplacement(Coordonnee(0,0), Coordonnee(1,3))
        assertThrows<DeplacementExeption> { p.getDeplacement(d) }
    }

    @Test
    fun getDeplacementQuiMarche(){
        val p = PetitPion()
        val d = Deplacement(Coordonnee(0,0), Coordonnee(1,1))
        assertEquals(listOf(Coordonnee(1,1)), p.getDeplacement(d))
    }
}