package projet.echecmartien.vue

import javafx.scene.layout.BorderPane

class VueJeu() : BorderPane() {
    init {
        this.center = VuePlateau()
        this.left = VueCompteurPoints("Joueur1", "Joueur2", 1280.0, 720.0, 80.0, 80.0)
    }
}