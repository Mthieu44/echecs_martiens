package projet.echecmartien

import javafx.application.Application

import javafx.stage.Stage
import projet.echecmartien.modele.Coordonnee
import projet.echecmartien.modele.Plateau

class AppliJeuEchecMartien: Application() {
    override fun start(primaryStage: Stage) {

       TODO()
      

    }

}

fun main(){
    val p = Plateau()
    p.initialiser()

    //Application.launch(AppliJeuEchecMartien::class.java)
}



