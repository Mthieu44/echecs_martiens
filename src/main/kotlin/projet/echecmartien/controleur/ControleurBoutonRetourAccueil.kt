package projet.echecmartien.controleur

import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.input.MouseEvent
import javafx.stage.Stage
import projet.echecmartien.modele.Jeu
import projet.echecmartien.vue.VueAccueil
import projet.echecmartien.vue.VueJeu

class ControleurBoutonRetourAccueil(primary : Stage, vue : VueJeu, modele : Jeu) : EventHandler<MouseEvent> {
    val vue: VueJeu
    val modele: Jeu
    val primary: Stage

    init {
        this.vue = vue
        this.modele = modele
        this.primary = primary
    }

    override fun handle(p0: MouseEvent?) {
        val root = VueAccueil()
        val scene = Scene(root, 1090.0, 550.0)
        root.checkBox(ControleurCheckboxIA(root, modele))
        root.valider(ControleurBoutonValider(primary, root, modele))
        primary.title = "Page d'accueil"
        primary.scene = scene
        primary.show()
    }

}