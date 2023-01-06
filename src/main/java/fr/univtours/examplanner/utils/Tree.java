package fr.univtours.examplanner.utils;

import javafx.scene.control.TreeItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class Tree {

    private Tree() {super();}

    /**
     * Construit un arbre à partir d'une liste d'éléments et d'une fonction retournant la liste des enfants d'un
     * élement
     *
     * @param list     la liste d'éléments
     * @param children la fonction retournant la liste des enfants d'un élement
     * @param <T>      le type des éléments
     * @return l'arbre
     */
    public static < T > TreeItem< T > fromList(
            List< ? extends T > list,
            Function< ? super T, ? extends List< T > > children
    ) {
        TreeItem< T > root = new TreeItem<>();
        root.setExpanded(true);
        for ( T item : list ) {
            TreeItem< T > i = new TreeItem<>(item);
            List< T > childrenList = children.apply(item);
            if ( Objects.nonNull(childrenList) && !childrenList.isEmpty() ) {
                i.setExpanded(true);
                i.getChildren().addAll(fromList(childrenList, children));
            }
            root.getChildren().add(i);
        }
        return root;
    }

    /**
     * Construit une liste à partir d'un arbre sans enfants
     *
     * @param list la liste d'éléments
     * @param <T>  le type des éléments
     * @return l'arbre
     */
    public static < T > TreeItem< T > fromList( List< ? extends T > list ) {
        return fromList(list, t -> new ArrayList<>());
    }

    /**
     * Construit une liste à partir d'un arbre
     *
     * @param root l'arbre
     * @param <T>  le type des éléments
     * @return la liste
     */
    public static < T > List< T > toList( TreeItem< T > root ) {
        List< T > list = new ArrayList<>();
        addChildren(root, list);
        return list;
    }

    /**
     * Ajoute les enfants d'un arbre à une liste
     *
     * @param item l'arbre
     * @param list la liste
     * @param <T>  le type des éléments
     */
    private static < T > void addChildren( TreeItem< T > item, List< T > list ) {
        if ( Objects.nonNull(item) && Objects.nonNull(item.getValue()) ) {
            list.add(item.getValue());
            for ( TreeItem< T > child : item.getChildren() ) {
                addChildren(child, list);
            }
        }
    }

}
