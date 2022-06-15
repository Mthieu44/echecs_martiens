package projet.echecmartien.controleur

import javafx.event.EventHandler
import javafx.scene.input.MouseEvent
import projet.echecmartien.modele.Coordonnee
import projet.echecmartien.modele.Jeu
import projet.echecmartien.modele.Joueur
import projet.echecmartien.vue.VueCompteurPoints
import projet.echecmartien.vue.VuePlateau

class ControleurClicCase(modele: Jeu, vueP: VuePlateau, vueG : VueCompteurPoints) : EventHandler<MouseEvent> {
    val modele: Jeu
    val vueDuPlateau : VuePlateau
    val vueDeGauche : VueCompteurPoints

    init {
        this.vueDeGauche = vueG
        this.modele = modele
        this.vueDuPlateau = vueP
    }



    override fun handle(event: MouseEvent) {
        val x = (event.x / vueDuPlateau.bx).toInt()
        val y: Int
        if (event.y > 4 * vueDuPlateau.by) {
            y = ((event.y - vueDuPlateau.pane.minHeight) / vueDuPlateau.by).toInt()
        } else {
            y = (event.y / vueDuPlateau.by).toInt()
        }
        if (x<0 || x>3 || y<0 || y>7 || modele.arretPartie()) {
            println(modele)
            return
        }

        if (modele.getCoordOrigineDeplacement() == Coordonnee(x, y)) {
            modele.setCoordOrigineDeplacement(null)
            enleveCouleur(vueDuPlateau)
            return
        }
        if (modele.getCoordOrigineDeplacement() == null || (modele.getPlateau().getCases()[x][y].getJoueur() == modele.getJoueurCourant() && !modele.getPlateau().getCases()[x][y].estLibre())) {
            modele.setCoordOrigineDeplacement(Coordonnee(x, y))
            deplPossible(x, y, modele, vueDuPlateau)
            return
        }
        if (modele.getCoordOrigineDeplacement() != Coordonnee(x, y)) {
            val xO = modele.getCoordOrigineDeplacement()!!.getX()
            val yO = modele.getCoordOrigineDeplacement()!!.getY()
            if (modele.deplacementPossible(xO, yO, x, y, modele.getJoueurCourant())) {
                vueDuPlateau.tableauCase[y][x].placerPion(modele.getPlateau().getCases()[xO][yO].getPion().toString())
                vueDuPlateau.tableauCase[yO][xO].retirerPion()
                modele.deplacer(xO, yO, x, y)
                if (modele.arretPartie()){
                    vueDeGauche.texteAQuiDeJouer.text = texteDeFin()
                }
            }
        }
        modele.setCoordOrigineDeplacement(null)
        enleveCouleur(vueDuPlateau)
    }

    private fun texteDeFin():String{
        val vainqueur = modele.joueurVainqueur()
        val affichage : String
        if (vainqueur==null) {
            affichage = "Il y a égalite, chauque joueur à ${modele.getJoueurCourant()!!.calculerScore()} points !"
        }
        else {
            val j1 : Joueur = modele.getJoueurCourant()!!
            modele.changeJoueurCourant()
            val j2 : Joueur = modele.getJoueurCourant()!!
            if (j1.calculerScore() > j2.calculerScore()){
                affichage = "$j1 a gagné contre $j2 à ${j1.calculerScore()}-${j2.calculerScore()} !"
            }
            else{
                affichage = "$j2 a gagné contre $j1 à ${j2.calculerScore()}-${j1.calculerScore()} !"
            }
        }
        return affichage
    }

}


fun deplPossible(x: Int, y: Int, modele: Jeu, vue: VuePlateau) {
    for (i in 0 until 4) {
        for (j in 0 until 8) {
            vue.tableauCase[j][i].changeCouleur("None")
            if (modele.deplacementPossible(x, y, i, j, modele.getJoueurCourant())) {
                if (modele.getPlateau().getCases()[i][j].estLibre()) {
                    vue.tableauCase[j][i].changeCouleur("#A5E3F6")
                } else {
                    vue.tableauCase[j][i].changeCouleur("#FF6961")
                }
            }
        }
    }
    if (!modele.getPlateau().getCases()[x][y].estLibre() && modele.getPlateau().getCases()[x][y].getJoueur() == modele.getJoueurCourant())
        vue.tableauCase[y][x].changeCouleur("#B0F2B6")
}

fun enleveCouleur(vue: VuePlateau) {
    for (i in 0 until 4) {
        for (j in 0 until 8) {
            vue.tableauCase[j][i].changeCouleur("None")
        }
    }
}