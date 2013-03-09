package lb.datamagus.ui

import javafx.application.Application
import javafx.scene.*
import javafx.scene.control.*
import javafx.scene.layout.*
import javafx.stage.Stage

class DataMagusApplication: Application()
{

    public override fun start(primaryStage: Stage)
    {
        primaryStage.setTitle("DataMagus")


        val rootPane = VBox()
        val scene = Scene(rootPane:Parent, 600.0, 600.0)

        val menuBar = makeMenu()
        rootPane.getChildren().addAll(menuBar)

        primaryStage.setScene(scene)
        primaryStage.show()
    }


    fun makeMenu(): MenuBar
    {
        val menuBar = MenuBar()

        val menuProject = Menu("Project")
        menuProject.getItems().addAll(MenuItem("New…"), SeparatorMenuItem(), MenuItem("Quit"))

        val menuEdit = Menu("Edit")
        menuEdit.getItems().addAll(MenuItem("Copy"), MenuItem("Paste"))

        val menuDatabase = Menu("Database")
        menuBar.getMenus().addAll(menuProject, menuEdit, menuDatabase)
        menuBar.setUseSystemMenuBar(true)

        return menuBar
    }

}
