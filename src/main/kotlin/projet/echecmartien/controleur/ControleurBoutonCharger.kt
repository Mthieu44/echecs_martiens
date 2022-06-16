package projet.echecmartien.controleur

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.stage.FileChooser
import javafx.stage.Stage
import projet.echecmartien.modele.*
import projet.echecmartien.vue.VueJeu
import java.io.File
import java.io.FileReader


class ControleurBoutonCharger(primary : Stage, vue : VueJeu, modele : Jeu) : EventHandler<ActionEvent> {
    val primary : Stage
    val vue : VueJeu
    val modele : Jeu

    init {
        this.primary = primary
        this.vue=vue
        this.modele=modele
    }

    override fun handle(p0: ActionEvent?) {
        val fileChooser = FileChooser()
        val file = fileChooser.showOpenDialog(primary)
        val fileReader = FileReader(file)
        var charCode: Int
        var data: MutableList<String> = mutableListOf()
        var string = ""
        while (fileReader.read().also { charCode = it } != -1) {
            if (charCode.toChar().toString() == ";") {
                data.add(string)
                string = ""
            }
            else {
                string += charCode.toChar().toString()
            }
        }
        fileReader.close()


        val nombreCoupsSansPrise = data[0][1].toString().toInt()
        val nombreCoupsSansPriseMax = data[1].toInt()
        val joueursStr = data[2]
        var stringJoueur1 = joueursStr.substring(1, joueursStr.indexOf(','))
        var stringJoueur2 = joueursStr.substring(joueursStr.indexOf(',')+2, joueursStr.length-1)
        val j1 = Joueur(stringJoueur1)
        val j2 = Joueur(stringJoueur2)
        val joueurs = arrayOf(j1,j2)
        val pionJ1Str = data[3]
        val pionJ2Str = data[4]
        for (e in pionJ1Str){
            if (e == '1'){
                j1.ajouterPionCaptures(PetitPion())
            }
            if (e == '2'){
                j1.ajouterPionCaptures(MoyenPion())
            }
            if (e == '3'){
                j1.ajouterPionCaptures(GrandPion())
            }
        }
        for (i in pionJ2Str){
            if (i == '1'){
                j2.ajouterPionCaptures(PetitPion())
            }
            if (i == '2'){
                j2.ajouterPionCaptures(MoyenPion())
            }
            if (i == '3'){
                j2.ajouterPionCaptures(GrandPion())
            }
        }
        val joueurCourant = Joueur(data[5])

        var nouveauJeu = Jeu(nombreCoupsSansPrise,nombreCoupsSansPriseMax,null,null,joueurs,joueurCourant)
        nouveauJeu.initialiserPartie(j1, j2, nombreCoupsSansPriseMax)
        if (nouveauJeu.getJoueurCourant() != joueurCourant){
            nouveauJeu.changeJoueurCourant()
        }
        val plateau = nouveauJeu.getPlateau()
        val plateauStr = data[6]
        var indice = 0
        var i = 0
        var j = 0
        while (indice<plateauStr.length){
            if (plateauStr[indice]=='0'){
                plateau.getCases()[i][j].setPion(null)
                i+=1
                if (i==4){
                    i=0
                    j++
                }
            }
            else if (plateauStr[indice]=='1'){
                plateau.getCases()[i][j].setPion(PetitPion())
                i+=1
                if (i==4){
                    i=0
                    j++
                }
            }
            else if (plateauStr[indice]=='2'){
                plateau.getCases()[i][j].setPion(MoyenPion())
                i+=1
                if (i==4){
                    i=0
                    j++
                }
            }
            else if (plateauStr[indice]=='3'){
                plateau.getCases()[i][j].setPion(GrandPion())
                i+=1
                if (i==4){
                    i=0
                    j++
                }
            }
            indice ++
        }
        if ("null" !in data[7]){
            nouveauJeu.setCoordPionArriveDeZone(Coordonnee(data[7][1].digitToInt(), data[7][3].digitToInt()))
            nouveauJeu.setPionArriveDeZone(plateau.getCases()[data[7][1].digitToInt()][data[7][3].digitToInt()].getPion())
        }

        nouveauJeu.contreBot = data[8].toBooleanStrict()
        fileReader.close()

        val root = VueJeu(j1.getPseudo(), j2.getPseudo(), data[8].toBooleanStrict())
        val scene = Scene(root, 870.0, 650.0)
        root.plateau.clic(ControleurClicCase(nouveauJeu, root.plateau, root.gauche))
        root.droite.fixeBoutonListener(root.droite.boutonAfficherRegles, ControleurAfficherRegles(root, nouveauJeu))
        root.droite.fixeBoutonListener(root.droite.boutonRecommencer, ControleurBoutonRecommencer(root, nouveauJeu))
        root.droite.fixeBoutonListener(root.droite.boutonRetourAccueil, ControleurBoutonRetourAccueil(primary, root, nouveauJeu))
        root.droite.fixeBoutonListener(root.droite.boutonSauvegarder, ControleurBoutonSauvegarder(primary,root.plateau, nouveauJeu))
        root.droite.fixeBoutonListener(root.droite.boutonCharger, ControleurBoutonCharger(primary,root, nouveauJeu))

        for (i in 0 until 8){
            for (j in 0 until 4){
                if (nouveauJeu.getPlateau().getCases()[j][i].estLibre()){
                    root.plateau.tableauCase[i][j].retirerPion()
                }else{
                    root.plateau.tableauCase[i][j].placerPion(nouveauJeu.getPlateau().getCases()[j][i].getPion().toString())
                }
            }
        }
        root.gauche.texteAQuiDeJouer.text = "C'est au tour de\n@${nouveauJeu.getJoueurCourant()}\nde jouer !"
        root.gauche.compteJoueur1 = nouveauJeu.getJoueurs()[0].calculerScore()
        root.gauche.compteJoueur2 = nouveauJeu.getJoueurs()[1].calculerScore()
        root.gauche.compteGrandPionJoueur1 = comptePionCapture(nouveauJeu.getJoueurs()[0], GrandPion())
        root.gauche.compteMoyenPionJoueur1 = comptePionCapture(nouveauJeu.getJoueurs()[0], MoyenPion())
        root.gauche.comptePetitPionJoueur1 = comptePionCapture(nouveauJeu.getJoueurs()[0], PetitPion())
        root.gauche.compteGrandPionJoueur2 = comptePionCapture(nouveauJeu.getJoueurs()[1], GrandPion())
        root.gauche.compteMoyenPionJoueur2 = comptePionCapture(nouveauJeu.getJoueurs()[1], MoyenPion())
        root.gauche.comptePetitPionJoueur2 = comptePionCapture(nouveauJeu.getJoueurs()[1], PetitPion())
        root.gauche.updateScore()

        primary.title = "Echec Martien"
        primary.scene = scene
        primary.isResizable = false
        primary.show()


    }

    private fun comptePionCapture(joueur: Joueur, typePion: Pion) : Int{
        var c = 0
        for (p in joueur.getPionsCaptures()){
            if (p == typePion){
                c += 1
            }
        }
        return c
    }


}