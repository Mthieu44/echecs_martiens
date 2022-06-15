package projet.echecmartien.controleur

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.stage.FileChooser
import javafx.stage.Stage
import projet.echecmartien.modele.Jeu
import projet.echecmartien.vue.VuePlateau
import java.io.File

class ControleurBoutonSauvegarder(primary : Stage, vue: VuePlateau, modele : Jeu) : EventHandler<ActionEvent> {
    private val primary : Stage
    private val modele: Jeu
    private val vue: VuePlateau

    init {
        this.primary = primary
        this.modele = modele
        this.vue = vue
    }

    override fun handle(p0: ActionEvent?) {
        modele.serialiser("save.json")
        val fileChooser = FileChooser()
        var SaveContent = modele.serialiser("save.json")

        fileChooser.initialFileName = "save.json"
        File("file:eq_4_05_bergeron-mathieu_cailleteau-pacome_chusseau-nicolas_hardy-clement").writeText("$SaveContent")

        val extensionFilter = FileChooser.ExtensionFilter("Json file (*.json)", "*.json")
        fileChooser.extensionFilters.add(extensionFilter)

        //Show save file dialog
        val file = fileChooser.showSaveDialog(primary)
        //Its important towrite showSaveDialog!!!

    }
}