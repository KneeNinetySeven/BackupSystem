package de.knee.backup.ui;

import de.knee.backup.fileManagement.FileWorker;
import de.knee.backup.ui.treeView.ViewTreeBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Nils on 10.02.2017.
 * Package: de.knee.backup.ui
 */
public class UiController implements Initializable {

    private final FileWorker fileWorker = new FileWorker();
    ObservableList<String> paths;
    TreeItem<String> treeRoot;

    private boolean dirValid = false;
    private boolean fileValid = false;

    @FXML Button setDirectory;

    @FXML Button remDirectory;

    @FXML ListView directoryList;

    @FXML Label statusLabel;

    @FXML TreeView<String> treeView;

    @FXML TextField targetDir;

    @FXML Label targetDirValid;

    @FXML TextField targetName;

    @FXML Label targetFileValid;

    @FXML Button runBtn;

    @FXML
    protected void setDirectoryButtonPressed() {
        setStatus("Adding Directory.");
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select new directory");
        File selectedDir = directoryChooser.showDialog(null);

        // In case the dialog gets closed without selection, abort.
        if (selectedDir == null) return;

        String path = selectedDir.getAbsolutePath();
        paths.add(path);
        directoryList.setItems(paths);
        setStatus("Loading Treeview.");
        treeView.getRoot().getChildren().add(ViewTreeBuilder.buildRecursiveFromPath(path, new TreeItem<String>()));
        directoryList.refresh();
        treeView.refresh();

    }

    @FXML
    protected void remDirectoryButtonPressed() {
        setStatus("Removing Diretory.");
        int selectedItem = directoryList.getSelectionModel().getSelectedIndex();
        if (selectedItem >= 0) {
            directoryList.getItems().remove(selectedItem);
            treeView.getRoot().getChildren().remove(selectedItem);
            directoryList.refresh();
            treeView.refresh();
        } else {
            setStatus("Error Removing Directory.");

            System.out.println("Selected Item: " + selectedItem);
        }
    }

    @FXML
    protected void checkTargetDirExistence(){
        File targetDirFile = new File(targetDir.getText());
        if(fileWorker.verifyPath(targetDirFile)){
            dirValid = false;
            targetDirValid.setText("Directory not existent");
        } else {
            if(!targetDirFile.isDirectory()) {
                dirValid = false;
                targetDirValid.setText("Target directory is no directory");
            } else {
                dirValid = true;
                targetDirValid.setText("");
            }
        }
        runBtn.setDisable(!(dirValid && fileValid));
    }

    @FXML
    protected void checkTargetFileNotExisting(){
        File targetFile = new File(targetDir.getText() + "/" + targetName.getText());
        String text = "";

        if(fileWorker.verifyFile(targetFile)){
            text = "File okay";
            fileValid = true;
        } else {
            text += "File is already existing";
            fileValid = false;
        }

        if(! fileWorker.verifyFileEnding(targetFile)){
            text += "\nInvalid file ending " + fileWorker.fileEnding(targetFile) + "!";
            fileValid = false;
        }

        targetFileValid.setText(text);
        runBtn.setDisable(!(dirValid && fileValid));
    }

    public void initialize(URL location, ResourceBundle resources) {
        this.paths = FXCollections.observableArrayList();
        this.treeRoot = new TreeItem<String>();
        treeRoot.setValue("All Files");
        treeView.setRoot(treeRoot);
    }

    private void setStatus(String status) {
        statusLabel.setText("Status: " + status);
    }

}
