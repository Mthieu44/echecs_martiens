package projet.echecmartien

import javafx.application.Application

import javafx.stage.Stage
import projet.echecmartien.modele.Coordonnee
import projet.echecmartien.modele.Jeu
import projet.echecmartien.modele.Joueur
import projet.echecmartien.modele.Plateau

class AppliJeuEchecMartien: Application() {
    override fun start(primaryStage: Stage) {

    }

}

fun main(){
    val j1 = Joueur("a")
    val j2 = Joueur("b")
    val j = Jeu()
    j.initialiserPartie(j1, j2, 3)
    println(j)
    j.deplacer(2, 5, 1, 4)
    println(j)

    //Application.launch(AppliJeuEchecMartien::class.java)
}



