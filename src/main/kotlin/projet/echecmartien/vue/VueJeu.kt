package projet.echecmartien.vue

import javafx.scene.layout.BorderPane
import projet.echecmartien.modele.Joueur

class VueJeu(j1 : String, j2 : String, bot : Boolean) : BorderPane() {
    val plateau = VuePlateau(bot)
    val gauche = VueCompteurPoints(j1, j2, 1280.0, 640.0, 80.0, 80.0)
    val droite = VueBouton()

    init {
        this.center = plateau
        this.left = gauche
        this.right=droite
        this.style = "-fx-background-color: linear-gradient(from 50% 0% to 50% 100%, #2192e610, #dec08860);"
    }
}