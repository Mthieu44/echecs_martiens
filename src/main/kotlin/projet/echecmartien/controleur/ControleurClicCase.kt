package projet.echecmartien.controleur

import javafx.event.EventHandler
import javafx.scene.Node
import javafx.scene.input.MouseEvent
import javafx.scene.layout.GridPane
import projet.echecmartien.modele.Jeu
import projet.echecmartien.vue.Plateau

class ControleurClicCase(modele : Jeu, vue : Plateau) : EventHandler<MouseEvent?> {
    val modele : Jeu
    val vue : Plateau

    init {
        this.modele = modele
        this.vue = vue
    }

    override fun handle(event: MouseEvent?) {
        val l = GridPane.getRowIndex(event!!.source as Node)
        val c = GridPane.getColumnIndex(event.source as Node)
        println(l)
        println(c)
    }
}