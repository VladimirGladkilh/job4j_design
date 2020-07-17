package io;

import java.nio.file.Path;
import java.util.function.Predicate;

public class PredicatFactory {

    public static Predicate<Path> preparePredicate(Param param) {
        if (!param.fileName().equals("")) {
            return path -> path.toFile().getName().toLowerCase().equals(param.fileName().toLowerCase());
        } else {
            if (!param.mask().equals("")) {
                return prepareMask(param.mask());
            } else {
                return path -> path.toFile().getName().matches(param.regexp());
            }
        }
    }

    private static Predicate<Path> prepareMask(String mask) {
        String[] searcher = mask.split("\\.");
        if (searcher[0].equals("*")) {
            return path -> path.toFile().getName().endsWith(searcher[1]);
        } else {
            if (searcher[1].equals("*")) {
                return path -> path.toFile().getName().startsWith(searcher[0]);
            } else {
                return path -> path.toFile().getName().toLowerCase().equals(mask.toLowerCase());
            }
        }

    }


}
