package projet.echecmartien.modele

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class PlateauTest {

    @Test
    fun getTailleHorizontale() {
        val p = Plateau()
        assertEquals(4,p.getTailleHorizontale())
    }

    @Test
    fun getTailleVerticale() {
        val p = Plateau()
        assertEquals(8,p.getTailleVerticale())
    }

    @Test
    fun getCases() {
        val p = Plateau()
        for (e in p.getCases()){
            for (el in e){
                assertNull(el.getPion())
                assertNull(el.getJoueur())
            }
        }
    }

    @Test
    fun testInitialiserPlateau() {
        val p = Plateau()
        p.initialiser()
        // Test des Pions du joueur 1 (celui du haut)
        assertEquals(GrandPion(), p.getCases()[0][0].getPion())
        assertEquals(GrandPion(), p.getCases()[1][0].getPion())
        assertEquals(GrandPion(), p.getCases()[0][1].getPion())
        assertEquals(MoyenPion(), p.getCases()[2][0].getPion())
        assertEquals(MoyenPion(), p.getCases()[1][1].getPion())
        assertEquals(MoyenPion(), p.getCases()[0][2].getPion())
        assertEquals(PetitPion(), p.getCases()[2][1].getPion())
        assertEquals(PetitPion(), p.getCases()[2][2].getPion())
        assertEquals(PetitPion(), p.getCases()[1][2].getPion())

        //Test de toutes les cases vides du plateau
        for (i in 0 until 4) {
            assertNull(p.getCases()[3][i].getPion()) // Test des cases vides du coté du joueur 1 à droite
            assertNull(p.getCases()[0][4+i].getPion()) // Test des cases vides du coté du joueur 2 à gauche
            assertNull(p.getCases()[i][3].getPion()) // Test des cases vides du coté du joueur 1 devant ces pions
            assertNull(p.getCases()[i][4].getPion())  // Test des cases vides du coté du joueur 2 devant ces pions
        }

        //Test des Pions du joueurs 2 (celui du bas)
        assertEquals(GrandPion(), p.getCases()[3][7].getPion())
        assertEquals(GrandPion(), p.getCases()[3][6].getPion())
        assertEquals(GrandPion(), p.getCases()[2][7].getPion())
        assertEquals(MoyenPion(), p.getCases()[1][7].getPion())
        assertEquals(MoyenPion(), p.getCases()[2][6].getPion())
        assertEquals(MoyenPion(), p.getCases()[3][5].getPion())
        assertEquals(PetitPion(), p.getCases()[2][5].getPion())
        assertEquals(PetitPion(), p.getCases()[1][5].getPion())
        assertEquals(PetitPion(), p.getCases()[1][6].getPion())
    }


}