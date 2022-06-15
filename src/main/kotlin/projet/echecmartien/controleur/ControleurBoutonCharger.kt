package projet.echecmartien.controleur

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.transform.Scale
import projet.echecmartien.modele.Jeu
import projet.echecmartien.vue.VueJeu

class ControleurBoutonCharger(primary : Scale, vue : VueJeu, modele : Jeu) : EventHandler<ActionEvent> {
    val primary : Scale
    val vue : VueJeu
    val modele : Jeu

    init {
        this.primary = primary
        this.vue=vue
        this.modele=modele
    }

    override fun handle(p0: ActionEvent?) {
        TODO("Not yet implemented")
    }

}