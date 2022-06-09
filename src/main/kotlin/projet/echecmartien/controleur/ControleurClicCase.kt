package projet.echecmartien.controleur

import javafx.event.EventHandler
import javafx.scene.input.MouseEvent
import projet.echecmartien.modele.Jeu
import projet.echecmartien.vue.CasePlateau

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