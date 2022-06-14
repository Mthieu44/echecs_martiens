package projet.echecmartien.controleur


import javafx.event.EventHandler
import javafx.scene.input.MouseEvent
import projet.echecmartien.modele.Jeu
import projet.echecmartien.vue.VueAccueil

class ControleurCheckboxIA(vue : VueAccueil, modele : Jeu) : EventHandler<MouseEvent>{
    val vue : VueAccueil
    val modele : Jeu

    init {
        this.vue = vue
        this.modele = modele
    }

    override fun handle(p0: MouseEvent?) {
        vue.textFieldPseudoj2.isDisable = vue.checkBoxIA.isSelected
    }
}