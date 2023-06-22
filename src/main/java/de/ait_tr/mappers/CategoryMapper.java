package de.ait_tr.mappers;

import de.ait_tr.models.Category;

import java.util.Arrays;
import java.util.List;

public class CategoryMapper {
    private CategoryMapper() {
    }

    public static String ToLines() {
        StringBuilder output = new StringBuilder();
        int counter = 1;
        List<String> lines = Arrays.stream(Category.values())
                .map(Category::getDescription)
                .sorted(String::compareTo)
                .toList();
        for (String description : lines) {
            output.append(counter++)
                    .append(". ")
                    .append(description)
                    .append(System.lineSeparator())
            ;
        }
        return output.toString();
    }
}

