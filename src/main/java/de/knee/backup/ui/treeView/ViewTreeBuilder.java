package de.knee.backup.ui.treeView;

import javafx.scene.control.TreeItem;

import java.io.File;

/**
 * Created by Nils on 10.02.2017.
 * Package: de.knee.backup.ui.treeView
 */
public class ViewTreeBuilder {

    public static TreeItem<String> buildRecursiveFromPath(String path, TreeItem<String> treeRoot) {
        File folder = new File(path);

        treeRoot.setValue(folder.getName());
        File[] content = folder.listFiles();
        if(content == null) return treeRoot;
        for (File item : content) {
            if (item.isDirectory()) {
                treeRoot.getChildren().add(ViewTreeBuilder.buildRecursiveFromPath(item.getPath(), new TreeItem<>()));
            } else if (item.isFile()) {
                TreeItem<String> newItem = new TreeItem<>();
                newItem.setValue(item.getName());
                treeRoot.getChildren().add(newItem);
            }
        }

        return treeRoot;
    }

}
