package projet.echecmartien.controleur

import javafx.event.EventHandler
import javafx.scene.input.MouseEvent
import javafx.stage.FileChooser
import projet.echecmartien.modele.Jeu
import projet.echecmartien.vue.VuePlateau

class ControleurBoutonSave(modele : Jeu, vue : VuePlateau) : EventHandler<MouseEvent> {
    private val modele : Jeu
    private val vue : VuePlateau

    init {
        this.modele = modele
        this.vue = vue
    }

    override fun handle(p0: MouseEvent?) {
        modele.serialiser("save")
    }


    /*val fileChooser = FileChooser()
    var SaveContent = modele.serialiserForum("save")

    fileChooser.initialFileName = "save"
    File("save").writeText("$SaveContent")
    //Set extension filter for text files

    //Set extension filter for text files
    val extFilter = FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt")
    fileChooser.extensionFilters.add(extFilter)

    //Show save file dialog

    //Show save file dialog
    val file = fileChooser.showSaveDialog(primaryStage)
    //Its important towrite showSaveDialog!!!
*/
}