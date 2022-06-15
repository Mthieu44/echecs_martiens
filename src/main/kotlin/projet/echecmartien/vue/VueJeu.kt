package projet.echecmartien.vue

import javafx.scene.layout.BorderPane
import projet.echecmartien.modele.Joueur

class VueJeu(j1 : String, j2 : String) : BorderPane() {
    val plateau = VuePlateau()
    val gauche = VueCompteurPoints(j1, j2, 1280.0, 640.0, 80.0, 80.0)
    init {
        this.center = plateau
        this.left = gauche

    }
}