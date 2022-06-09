package projet.echecmartien.controleur

import javafx.event.EventHandler
import javafx.scene.Node
import javafx.scene.input.MouseEvent
import javafx.scene.layout.GridPane
import projet.echecmartien.modele.Jeu
import projet.echecmartien.vue.CasePlateau
import projet.echecmartien.vue.VuePlateau

class ControleurClicCase(modele : Jeu, vue : CasePlateau) : EventHandler<MouseEvent> {
    val modele : Jeu
    val vue : CasePlateau

    init {
        this.modele = modele
        this.vue = vue
    }

    override fun handle(event: MouseEvent) {
        println(vue.getX())
        println(vue.getY())

    }
}