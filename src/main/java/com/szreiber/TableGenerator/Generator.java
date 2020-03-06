package com.szreiber.TableGenerator;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Generator {
    private static List<String> lastNames = readNames("last_names.csv");
    private static List<String> maleNames = readNames("male_names.csv");
    private static List<String> femaleNames = readNames("female_names.csv");
    private static Random random = new Random();


    private static List<String> readNames(String fileName) {
        fileName = "src/main/resources/" + fileName;
        List<String> names = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new FileReader(fileName));) {
            String[] values;
            while ((values = csvReader.readNext()) != null) {
                names.add(values[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return names;
    }

    private static String getRandom(List<String> list) {
        int index = random.nextInt(list.size());
        return list.get(index);
    }


    public static String getRandomLastName() {
        return getRandom(lastNames);
    }

    public static String getRandomFirstName() {
        if (random.nextBoolean()) {
            return getRandom(maleNames);
        } else {
            return getRandom(femaleNames);
        }
    }
}
