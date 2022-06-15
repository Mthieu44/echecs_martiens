package projet.echecmartien.vue

import javafx.scene.layout.BorderPane
import projet.echecmartien.controleur.ControleurBoutonValider

class VueJeu(nomJoueur1: String, nomJoueur2: String) : BorderPane() {
    val plateau = VuePlateau()
    val gauche = VueCompteurPoints(nomJoueur1, nomJoueur2, 1280.0, 720.0, 80.0, 80.0)

    init {
        this.center = plateau
        this.left = gauche
    }
}