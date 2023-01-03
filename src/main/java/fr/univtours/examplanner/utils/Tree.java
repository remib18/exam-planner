package fr.univtours.examplanner.utils;

import javafx.scene.control.TreeItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Tree {

    private Tree() {super();}

    public static < T > List< T > treeItemToList( TreeItem< T > root ) {
        List< T > list = new ArrayList<>();
        addChildren(root, list);
        return list;
    }

    private static < T > void addChildren( TreeItem< T > item, List< T > list ) {
        if ( Objects.nonNull(item) && Objects.nonNull(item.getValue()) ) {
            list.add(item.getValue());
            for ( TreeItem< T > child : item.getChildren() ) {
                addChildren(child, list);
            }
        }
    }

}
