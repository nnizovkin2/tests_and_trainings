package com.train.java;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Solution {
    List<Integer> getRandomSubset (List<Integer> list) {
        Random random = new Random();
        return list.stream().filter(e -> random.nextBoolean()).collect(Collectors.toList());
    }

    long getPopulation(List<Country> countries, String continent) {
        return countries
                .stream()
                .mapToLong(c -> continent.equals(c.getContinent()) ? c.getPopulation() : 0)
                .sum();
    }

    private static class Country {
        private int population;
        private String continent;

        public int getPopulation() {
            return population;
        }

        public String getContinent() {
            return continent;
        }
    }
}
