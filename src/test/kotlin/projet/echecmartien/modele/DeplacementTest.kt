package projet.echecmartien.modele

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows
import projet.echecmartien.exeptions.DeplacementExeption

internal class DeplacementTest {

    @Test
    fun getDestination() {
        val origine = Coordonnee(0,1)
        val destination = Coordonnee(3,6)
        val d = Deplacement(origine, destination)
        assertEquals(destination,d.getDestination())
    }

    @Test
    fun getOrigine() {
        val origine = Coordonnee(0,1)
        val destination = Coordonnee(3,6)
        val d = Deplacement(origine, destination)
        assertEquals(origine,d.getOrigine())
    }

    @Test
    fun estHorizontalNull() {
        val origine = Coordonnee(0,0)
        val d = Deplacement(origine, origine)
        assertTrue(d.estHorizontal())
    }

    @Test
    fun estHorizontalVrai(){
        val origine = Coordonnee(0,1)
        val destination = Coordonnee(3,1)
        val d = Deplacement(origine, destination)
        assertTrue(d.estHorizontal())
    }

    @Test
    fun estHorizontalFaux(){
        val origine = Coordonnee(0,1)
        val destination = Coordonnee(3,6)
        val d = Deplacement(origine, destination)
        assertFalse(d.estHorizontal())
    }

    @Test
    fun estVerticalNull() {
        val origine = Coordonnee(0,1)
        val d = Deplacement(origine, origine)
        assertTrue(d.estVertical())
    }

    @Test
    fun estVerticalVrai(){
        val origine = Coordonnee(0,1)
        val destination = Coordonnee(0,6)
        val d = Deplacement(origine, destination)
        assertTrue(d.estVertical())
    }

    @Test
    fun estVerticalFaux(){
        val origine = Coordonnee(0,1)
        val destination = Coordonnee(3,6)
        val d = Deplacement(origine, destination)
        assertFalse(d.estVertical())
    }

    @Test
    fun estDiagonalNull() {
        val origine = Coordonnee(0,1)
        val d = Deplacement(origine, origine)
        assertTrue(d.estDiagonal())
    }

    @Test
    fun estDiagonalVrai1(){
        val origine = Coordonnee(0,0)
        val destination = Coordonnee(3,3)
        val d = Deplacement(origine, destination)
        assertTrue(d.estDiagonal())
    }

    @Test
    fun estDiagonalVrai2(){
        val origine = Coordonnee(3,0)
        val destination = Coordonnee(0,3)
        val d = Deplacement(origine, destination)
        assertTrue(d.estDiagonal())
    }

    @Test
    fun estDiagonalVrai3(){
        val origine = Coordonnee(3,3)
        val destination = Coordonnee(0,0)
        val d = Deplacement(origine, destination)
        assertTrue(d.estDiagonal())
    }

    @Test
    fun estDiagonalVrai4(){
        val origine = Coordonnee(0,3)
        val destination = Coordonnee(3,0)
        val d = Deplacement(origine, destination)
        assertTrue(d.estDiagonal())
    }

    @Test
    fun estDiagonalFaux(){
        val origine = Coordonnee(0,1)
        val destination = Coordonnee(3,6)
        val d = Deplacement(origine, destination)
        assertFalse(d.estDiagonal())
    }

    @Test
    fun longueurZero() {
        val origine = Coordonnee(0,1)
        val d = Deplacement(origine, origine)
        assertEquals(0,d.longueur())
    }

    @Test
    fun longeurHorizontale(){
        val origine = Coordonnee(0,0)
        val destination = Coordonnee(3,0)
        val d = Deplacement(origine, destination)
        assertEquals(3,d.longueur())
    }

    @Test
    fun longeurVerticale(){
        val origine = Coordonnee(0,1)
        val destination = Coordonnee(0,6)
        val d = Deplacement(origine, destination)
        assertEquals(5,d.longueur())
    }

    @Test
    fun longeurDiagonale(){
        val origine = Coordonnee(0,1)
        val destination = Coordonnee(1,0)
        val d = Deplacement(origine, destination)
        assertEquals(1,d.longueur())
    }

    @Test
    fun estVerticalPositifVrai() {
        val origine = Coordonnee(0,1)
        val destination = Coordonnee(0,6)
        val d = Deplacement(origine, destination)
        assertTrue(d.estVerticalPositif())
    }

    @Test
    fun estVerticalPositifFaux(){
        val origine = Coordonnee(0,6)
        val destination = Coordonnee(0,5)
        val d = Deplacement(origine, destination)
        assertFalse(d.estVerticalPositif())
    }

    @Test
    fun estHorizontalPositifVrai() {
        val origine = Coordonnee(0,2)
        val destination = Coordonnee(3,2)
        val d = Deplacement(origine, destination)
        assertTrue(d.estHorizontalPositif())
    }

    @Test
    fun estHorizontalPositifFaux() {
        val origine = Coordonnee(3,2)
        val destination = Coordonnee(1,2)
        val d = Deplacement(origine, destination)
        assertFalse(d.estHorizontalPositif())
    }

    @Test
    fun estDiagonalPositifXPositifYVrai() {
        val origine = Coordonnee(0,0)
        val destination = Coordonnee(2,2)
        val d = Deplacement(origine, destination)
        assertTrue(d.estDiagonalPositifXPositifY())
    }

    @Test
    fun estDiagonalPositifXPositifYFaux1() {
        val origine = Coordonnee(2,0)
        val destination = Coordonnee(5,2)
        val d = Deplacement(origine, destination)
        assertFalse(d.estDiagonalPositifXPositifY())
    }

    @Test
    fun estDiagonalPositifXPositifYFaux2() {
        val origine = Coordonnee(2,2)
        val destination = Coordonnee(4,0)
        val d = Deplacement(origine, destination)
        assertFalse(d.estDiagonalPositifXPositifY())
    }

    @Test
    fun estDiagonalPositifXPositifYFaux3() {
        val origine = Coordonnee(4,0)
        val destination = Coordonnee(2,2)
        val d = Deplacement(origine, destination)
        assertFalse(d.estDiagonalPositifXPositifY())
    }

    @Test
    fun estDiagonalPositifXPositifYFaux4() {
        val origine = Coordonnee(4,2)
        val destination = Coordonnee(2,0)
        val d = Deplacement(origine, destination)
        assertFalse(d.estDiagonalPositifXPositifY())
    }

    @Test
    fun estDiagonalNegatifXPositifYVrai() {
        val origine = Coordonnee(2,0)
        val destination = Coordonnee(0,2)
        val d = Deplacement(origine, destination)
        assertTrue(d.estDiagonalNegatifXPositifY())
    }

    @Test
    fun estDiagonalNegatifXPositifYFaux1() {
        val origine = Coordonnee(2,2)
        val destination = Coordonnee(0,0)
        val d = Deplacement(origine, destination)
        assertFalse(d.estDiagonalNegatifXPositifY())
    }

    @Test
    fun estDiagonalNegatifXPositifYFaux2() {
        val origine = Coordonnee(2,0)
        val destination = Coordonnee(0,3)
        val d = Deplacement(origine, destination)
        assertFalse(d.estDiagonalNegatifXPositifY())
    }

    @Test
    fun estDiagonalNegatifXPositifYFaux3() {
        val origine = Coordonnee(0,2)
        val destination = Coordonnee(2,0)
        val d = Deplacement(origine, destination)
        assertFalse(d.estDiagonalNegatifXPositifY())
    }

    @Test
    fun estDiagonalNegatifXPositifYFaux4() {
        val origine = Coordonnee(0,0)
        val destination = Coordonnee(2,2)
        val d = Deplacement(origine, destination)
        assertFalse(d.estDiagonalNegatifXPositifY())
    }

    @Test
    fun estDiagonalPositifXNegatifYVrai() {
        val origine = Coordonnee(0,2)
        val destination = Coordonnee(2,0)
        val d = Deplacement(origine, destination)
        assertTrue(d.estDiagonalPositifXNegatifY())
    }

    @Test
    fun estDiagonalPositifXNegatifYFaux1() {
        val origine = Coordonnee(0,3)
        val destination = Coordonnee(2,0)
        val d = Deplacement(origine, destination)
        assertFalse(d.estDiagonalPositifXNegatifY())
    }

    @Test
    fun estDiagonalPositifXNegatifYFaux2() {
        val origine = Coordonnee(2,2)
        val destination = Coordonnee(0,0)
        val d = Deplacement(origine, destination)
        assertFalse(d.estDiagonalPositifXNegatifY())
    }

    @Test
    fun estDiagonalPositifXNegatifYFaux3() {
        val origine = Coordonnee(0,0)
        val destination = Coordonnee(2,2)
        val d = Deplacement(origine, destination)
        assertFalse(d.estDiagonalPositifXNegatifY())
    }

    @Test
    fun estDiagonalPositifXNegatifYFaux4() {
        val origine = Coordonnee(2,0)
        val destination = Coordonnee(0,2)
        val d = Deplacement(origine, destination)
        assertFalse(d.estDiagonalPositifXNegatifY())
    }

    @Test
    fun estDiagonalNegatifXNegatifYVrai() {
        val origine = Coordonnee(2,2)
        val destination = Coordonnee(0,0)
        val d = Deplacement(origine, destination)
        assertTrue(d.estDiagonalNegatifXNegatifY())
    }

    @Test
    fun estDiagonalNegatifXNegatifYFaux1() {
        val origine = Coordonnee(0,0)
        val destination = Coordonnee(2,2)
        val d = Deplacement(origine, destination)
        assertFalse(d.estDiagonalNegatifXNegatifY())
    }

    @Test
    fun estDiagonalNegatifXNegatifYFaux2() {
        val origine = Coordonnee(1,2)
        val destination = Coordonnee(0,0)
        val d = Deplacement(origine, destination)
        assertFalse(d.estDiagonalNegatifXNegatifY())
    }

    @Test
    fun estDiagonalNegatifXNegatifYFaux3() {
        val origine = Coordonnee(0,2)
        val destination = Coordonnee(2,0)
        val d = Deplacement(origine, destination)
        assertFalse(d.estDiagonalNegatifXNegatifY())
    }

    @Test
    fun estDiagonalNegatifXNegatifYFaux4() {
        val origine = Coordonnee(2,0)
        val destination = Coordonnee(0,2)
        val d = Deplacement(origine, destination)
        assertFalse(d.estDiagonalNegatifXNegatifY())
    }

    @Test
    fun getCheminVerticalPositif() {
        val origine = Coordonnee(0,0)
        val destination = Coordonnee(0,3)
        val d = Deplacement(origine, destination)
        val l = d.getCheminVertical()
        assertEquals(listOf(Coordonnee(0,1),Coordonnee(0,2),Coordonnee(0,3)),l)
    }

    @Test
    fun getCheminVerticalNegatif() {
        val origine = Coordonnee(0,3)
        val destination = Coordonnee(0,0)
        val d = Deplacement(origine, destination)
        val l = d.getCheminVertical()
        assertEquals(listOf(Coordonnee(0,2),Coordonnee(0,1),Coordonnee(0,0)),l)
    }

    @Test
    fun getCheminVerticalExeption(){
        val origine = Coordonnee(0,1)
        val destination = Coordonnee(3,6)
        val d = Deplacement(origine, destination)
        assertThrows<DeplacementExeption> { d.getCheminVertical() }
    }

    @Test
    fun getCheminHorizontalExeption(){
        val origine = Coordonnee(0,1)
        val destination = Coordonnee(3,6)
        val d = Deplacement(origine, destination)
        assertThrows<DeplacementExeption> { d.getCheminHorizontal() }
    }

    @Test
    fun getCheminHorizontalPositif() {
        val origine = Coordonnee(0,0)
        val destination = Coordonnee(3,0)
        val d = Deplacement(origine, destination)
        val l = d.getCheminHorizontal()
        assertEquals(listOf(Coordonnee(1,0),Coordonnee(2,0),Coordonnee(3,0)),l)
    }

    @Test
    fun getCheminHorizontalNegatif() {
        val origine = Coordonnee(3,0)
        val destination = Coordonnee(0,0)
        val d = Deplacement(origine, destination)
        val l = d.getCheminHorizontal()
        assertEquals(listOf(Coordonnee(2,0),Coordonnee(1,0),Coordonnee(0,0)),l)
    }

    @Test
    fun getCheminDiagonalPxPy() { //P = Positif, N = Negatif
        val origine = Coordonnee(0,0)
        val destination = Coordonnee(3,3)
        val d = Deplacement(origine, destination)
        val l = d.getCheminDiagonal()
        assertEquals(listOf(Coordonnee(1,1),Coordonnee(2,2),Coordonnee(3,3)),l)
    }

    @Test
    fun getCheminDiagonalPxNy() { //P = Positif, N = Negatif
        val origine = Coordonnee(0,3)
        val destination = Coordonnee(3,0)
        val d = Deplacement(origine, destination)
        val l = d.getCheminDiagonal()
        assertEquals(listOf(Coordonnee(1,2),Coordonnee(2,1),Coordonnee(3,0)),l)
    }

    @Test
    fun getCheminDiagonalNxPy() { //P = Positif, N = Negatif
        val origine = Coordonnee(3,0)
        val destination = Coordonnee(0,3)
        val d = Deplacement(origine, destination)
        val l = d.getCheminDiagonal()
        assertEquals(listOf(Coordonnee(2,1),Coordonnee(1,2),Coordonnee(0,3)),l)
    }

    @Test
    fun getCheminDiagonalNxNy() { //P = Positif, N = Negatif
        val origine = Coordonnee(3,3)
        val destination = Coordonnee(0,0)
        val d = Deplacement(origine, destination)
        val l = d.getCheminDiagonal()
        assertEquals(listOf(Coordonnee(2,2),Coordonnee(1,1),Coordonnee(0,0)),l)
    }

    @Test
    fun getCheminDiagonalExeption(){
        val origine = Coordonnee(0,1)
        val destination = Coordonnee(3,6)
        val d = Deplacement(origine, destination)
        assertThrows<DeplacementExeption> { d.getCheminDiagonal() }
    }
}