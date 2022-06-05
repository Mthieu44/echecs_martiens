package projet.echecmartien

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.layout.*

import javafx.stage.Stage
import projet.echecmartien.modele.Jeu
import projet.echecmartien.modele.Joueur

class AppliJeuEchecMartien: Application() {

    private val plateau = VBox()
    override fun start(primaryStage: Stage) {
        val root = BorderPane()
        val scene = Scene(root, 1280.0, 720.0)
        primaryStage.scene = scene


        root.center = plateau

        val plateauHaut = GridPane()
        val plateauBas = GridPane()


        val bx = 80.0 //taille Bouton x
        val by = 80.0 //taille Bouton y
        val nbr_buttons_x = 4
        val nbr_buttons_y = 8
        //val cy: Double = nbr_buttons_y * bx //taille Colonne y


        var lettresColonnes: Array<String> = arrayOf()
        var chr = 'A'
        for (lettre in 0 until nbr_buttons_x) {
            lettresColonnes = lettresColonnes.plus("${chr}")
            chr += 1
        }
        var numLignes: Array<String> = arrayOf()
        for (num in 1 until nbr_buttons_y +1)
            numLignes = numLignes.plus("$num")

        var lettreColonne: String
        var numligne: String

        for (num_ligne in 0 until (nbr_buttons_y/2)){ //pour chaque ligne...
            numligne = numLignes[num_ligne]
            for (num_collonne in 0 until nbr_buttons_x){ //pour chaque bouton de la ligne
                lettreColonne = lettresColonnes[num_collonne]
                val bouton = Button("$lettreColonne$numligne")
                bouton.setPrefSize(bx,by)
                plateauHaut.add(bouton,num_collonne,num_ligne)
            }
        }
        plateau.children.add(plateauHaut)

        val pane = Pane()
        pane.maxWidth = 320.0
        pane.minHeight = 10.0
        pane.style = "-fx-background-color: #000000;"
        plateau.children.add(pane)

        for (num_ligne in nbr_buttons_y/2 until nbr_buttons_y){ //pour chaque ligne...
            numligne = numLignes[num_ligne]
            for (num_collonne in 0 until nbr_buttons_x){ //pour chaque bouton de la ligne
                lettreColonne = lettresColonnes[num_collonne]
                val bouton = Button("$lettreColonne$numligne")
                bouton.setPrefSize(bx,by)
                plateauBas.add(bouton,num_collonne,num_ligne)
            }
        }
        plateau.children.add(plateauBas)

        primaryStage.show()
    }
}

fun main(){
    val j1 = Joueur("a")
    val j2 = Joueur("b")
    val j = Jeu()
    j.initialiserPartie(j1, j2, 3)
    println(j)
    println(j.deplacementPossible(1, 5, 0, 4, j1))
    j.deplacer(1, 5, 0, 4)
    println(j)

    Application.launch(AppliJeuEchecMartien::class.java)
}