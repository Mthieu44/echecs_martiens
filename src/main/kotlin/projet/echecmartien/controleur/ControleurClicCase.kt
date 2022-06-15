package projet.echecmartien.controleur

import javafx.event.EventHandler
import javafx.scene.Node
import javafx.scene.input.MouseEvent
import javafx.scene.layout.GridPane
import projet.echecmartien.modele.Coordonnee
import projet.echecmartien.modele.Jeu
import projet.echecmartien.vue.VueCasePlateau
import projet.echecmartien.vue.VuePlateau

class ControleurClicCase(modele: Jeu, vue: VuePlateau) : EventHandler<MouseEvent> {
    val modele: Jeu
    val vue: VuePlateau

    init {
        this.modele = modele
        this.vue = vue
    }

    override fun handle(event: MouseEvent) {
        val x = (event.x / vue.bx).toInt()
        val y: Int
        if (event.y > 4 * vue.by) {
            y = ((event.y - vue.pane.minHeight) / vue.by).toInt()
        } else {
            y = (event.y / vue.by).toInt()
        }
        if (modele.getCoordOrigineDeplacement() == Coordonnee(x, y)) {
            modele.setCoordOrigineDeplacement(null)
            enleveCouleur(vue)
            return
        }
        if (modele.getCoordOrigineDeplacement() == null || (modele.getPlateau()
                .getCases()[x][y].getJoueur() == modele.getJoueurCourant() && !modele.getPlateau()
                .getCases()[x][y].estLibre())
        ) {
            modele.setCoordOrigineDeplacement(Coordonnee(x, y))
            deplPossible(x, y, modele, vue)
            return
        }
        if (modele.getCoordOrigineDeplacement() != Coordonnee(x, y)) {
            val xO = modele.getCoordOrigineDeplacement()!!.getX()
            val yO = modele.getCoordOrigineDeplacement()!!.getY()
            if (modele.deplacementPossible(xO, yO, x, y, modele.getJoueurCourant())) {
                vue.tableauCase[y][x].placerPion(modele.getPlateau().getCases()[xO][yO].getPion().toString())
                vue.tableauCase[yO][xO].retirerPion()
                modele.deplacer(xO, yO, x, y)
            }
        }
        modele.setCoordOrigineDeplacement(null)
        enleveCouleur(vue)
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
    if (!modele.getPlateau().getCases()[x][y].estLibre() && modele.getPlateau()
            .getCases()[x][y].getJoueur() == modele.getJoueurCourant()
    )
        vue.tableauCase[y][x].changeCouleur("#B0F2B6")
}

fun enleveCouleur(vue: VuePlateau) {
    for (i in 0 until 4) {
        for (j in 0 until 8) {
            vue.tableauCase[j][i].changeCouleur("None")
        }
    }
}