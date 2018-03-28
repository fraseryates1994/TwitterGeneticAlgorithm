package TwitterDataMiningInteger;

import java.util.Random;

/**
 *
 * @author Fraser
 */
public class Recombination {

    public static Random rand = new Random();

    public static boolean isFirstCondition(int index, Individual[] population) {
        for (int i = 0; i < population[0].geneSize; i += 12) {
            if (index == i) {
                return true;
            }
        }
        return false;
    }

    public static boolean isSecondCondition(int index, Individual[] population) {
        for (int i = 1; i < population[0].geneSize; i += 12) {
            if (index == i) {
                return true;
            }
        }
        return false;
    }

    public static boolean isThirdCondition(int index, Individual[] population) {
        for (int i = 2; i < population[0].geneSize; i += 12) {
            if (index == i) {
                return true;
            }
        }
        return false;
    }

    public static boolean isFourthCondition(int index, Individual[] population) {
        for (int i = 3; i < population[0].geneSize; i += 12) {
            if (index == i) {
                return true;
            }
        }
        return false;
    }

    public static boolean isFifthCondition(int index, Individual[] population) {
        for (int i = 4; i < population[0].geneSize; i += 12) {
            if (index == i) {
                return true;
            }
        }
        return false;
    }

    public static boolean isLastConditions(int index, Individual[] population) {
        int i = 5;
        int j = 6;
        int k = 7;
        int l = 8;
        int m = 9;
        int n = 10;

        while (i < population[0].geneSize) {
            if ((index == i) || (index == j) || (index == k) || (index == l) || (index == m) || (index == n)) {
                return true;
            }

            i += 12;
            j += 12;
            k += 12;
            l += 12;
            m += 12;
            n += 12;
        }
        return false;
    }

    public static boolean isOutput(int index, Individual[] population) {
        for (int i = 11; i < population[0].geneSize; i += 12) {
            if (index == i) {
                return true;
            }
        }
        return false;
    }

    public static void mutate(Individual[] population, int populationSize, double mutationRate) {
        Rule rule = new Rule();
        //mutation
        for (int i = 0; i != populationSize; ++i) {
            for (int j = 0; j != population[i].geneSize; ++j) {
                if (isFirstCondition(j, population)) { // context ID 0-3
                    if (rand.nextFloat() <= mutationRate) {
                        switch (population[i].genes[j]) {
                            case 0:
                                if (Math.random() < 0.25) {
                                    population[i].genes[j] = 1;
                                } else if (Math.random() < 0.5) {
                                    population[i].genes[j] = 2;
                                } else if (Math.random() < 0.75) {
                                    population[i].genes[j] = 3;
                                } else {
                                    population[i].genes[j] = 9;
                                }
                                break;
                            case 1:
                                if (Math.random() < 0.25) {
                                    population[i].genes[j] = 0;
                                } else if (Math.random() < 0.5) {
                                    population[i].genes[j] = 2;
                                } else if (Math.random() < 0.75) {
                                    population[i].genes[j] = 3;
                                } else {
                                    population[i].genes[j] = 9;
                                }
                                break;
                            case 2:
                                if (Math.random() < 0.25) {
                                    population[i].genes[j] = 0;
                                } else if (Math.random() < 0.5) {
                                    population[i].genes[j] = 1;
                                } else if (Math.random() < 0.75) {
                                    population[i].genes[j] = 3;
                                } else {
                                    population[i].genes[j] = 9;
                                }
                                break;
                            case 3:
                                if (Math.random() < 0.25) {
                                    population[i].genes[j] = 0;
                                } else if (Math.random() < 0.5) {
                                    population[i].genes[j] = 1;
                                } else if (Math.random() < 0.75) {
                                    population[i].genes[j] = 2;
                                } else {
                                    population[i].genes[j] = 9;
                                }
                                break;
                            case 9:
                                if (Math.random() < 0.25) {
                                    population[i].genes[j] = 0;
                                } else if (Math.random() < 0.5) {
                                    population[i].genes[j] = 1;
                                } else if (Math.random() < 0.75) {
                                    population[i].genes[j] = 2;
                                } else {
                                    population[i].genes[j] = 3;
                                }
                                break;
                            default:
                                break;
                        }
                    }
                } else if (isSecondCondition(j, population)) { // Followers Count 0-3
                    if (rand.nextFloat() <= mutationRate) {
                        switch (population[i].genes[j]) {
                            case 0:
                                if (Math.random() < 0.25) {
                                    population[i].genes[j] = 1;
                                } else if (Math.random() < 0.5) {
                                    population[i].genes[j] = 2;
                                } else if (Math.random() < 0.75) {
                                    population[i].genes[j] = 3;
                                } else {
                                    population[i].genes[j] = 9;
                                }
                                break;
                            case 1:
                                if (Math.random() < 0.25) {
                                    population[i].genes[j] = 0;
                                } else if (Math.random() < 0.5) {
                                    population[i].genes[j] = 2;
                                } else if (Math.random() < 0.75) {
                                    population[i].genes[j] = 3;
                                } else {
                                    population[i].genes[j] = 9;
                                }
                                break;
                            case 2:
                                if (Math.random() < 0.25) {
                                    population[i].genes[j] = 0;
                                } else if (Math.random() < 0.5) {
                                    population[i].genes[j] = 1;
                                } else if (Math.random() < 0.75) {
                                    population[i].genes[j] = 3;
                                } else {
                                    population[i].genes[j] = 9;
                                }
                                break;
                            case 3:
                                if (Math.random() < 0.25) {
                                    population[i].genes[j] = 0;
                                } else if (Math.random() < 0.5) {
                                    population[i].genes[j] = 1;
                                } else if (Math.random() < 0.75) {
                                    population[i].genes[j] = 2;
                                } else {
                                    population[i].genes[j] = 9;
                                }
                                break;
                            case 9:
                                if (Math.random() < 0.25) {
                                    population[i].genes[j] = 0;
                                } else if (Math.random() < 0.5) {
                                    population[i].genes[j] = 1;
                                } else if (Math.random() < 0.75) {
                                    population[i].genes[j] = 2;
                                } else {
                                    population[i].genes[j] = 3;
                                }
                                break;
                            default:
                                break;
                        }
                    }
                } else if (isThirdCondition(j, population)) { // Favourite Count 0-1                
                    if (rand.nextFloat() <= mutationRate) {
                        switch (population[i].genes[j]) {
                            case 0:
                                if (Math.random() < 0.5) {
                                    population[i].genes[j] = 1;
                                } else {
                                    population[i].genes[j] = 9;
                                }
                                break;
                            case 1:
                                if (Math.random() < 0.5) {
                                    population[i].genes[j] = 0;
                                } else {
                                    population[i].genes[j] = 9;
                                }
                                break;
                            case 9:
                                if (Math.random() < 0.5) {
                                    population[i].genes[j] = 0;
                                } else {
                                    population[i].genes[j] = 1;
                                }
                                break;
                            default:
                                break;
                        }
                    }
                } else if (isFourthCondition(j, population)) { // Friend Count 0-3
                    if (rand.nextFloat() <= mutationRate) {
                        switch (population[i].genes[j]) {
                            case 0:
                                if (Math.random() < 0.25) {
                                    population[i].genes[j] = 1;
                                } else if (Math.random() < 0.5) {
                                    population[i].genes[j] = 2;
                                } else if (Math.random() < 0.75) {
                                    population[i].genes[j] = 3;
                                } else {
                                    population[i].genes[j] = 9;
                                }
                                break;
                            case 1:
                                if (Math.random() < 0.25) {
                                    population[i].genes[j] = 0;
                                } else if (Math.random() < 0.5) {
                                    population[i].genes[j] = 2;
                                } else if (Math.random() < 0.75) {
                                    population[i].genes[j] = 3;
                                } else {
                                    population[i].genes[j] = 9;
                                }
                                break;
                            case 2:
                                if (Math.random() < 0.25) {
                                    population[i].genes[j] = 0;
                                } else if (Math.random() < 0.5) {
                                    population[i].genes[j] = 1;
                                } else if (Math.random() < 0.75) {
                                    population[i].genes[j] = 3;
                                } else {
                                    population[i].genes[j] = 9;
                                }
                                break;
                            case 3:
                                if (Math.random() < 0.25) {
                                    population[i].genes[j] = 0;
                                } else if (Math.random() < 0.5) {
                                    population[i].genes[j] = 1;
                                } else if (Math.random() < 0.75) {
                                    population[i].genes[j] = 2;
                                } else {
                                    population[i].genes[j] = 9;
                                }
                                break;
                            case 9:
                                if (Math.random() < 0.25) {
                                    population[i].genes[j] = 0;
                                } else if (Math.random() < 0.5) {
                                    population[i].genes[j] = 1;
                                } else if (Math.random() < 0.75) {
                                    population[i].genes[j] = 2;
                                } else {
                                    population[i].genes[j] = 3;
                                }
                                break;
                            default:
                                break;
                        }
                    }
                } else if (isFifthCondition(j, population)) { // location 0-4
                    if (rand.nextFloat() <= mutationRate) {
                        switch (population[i].genes[j]) {
                            case 0:
                                if (Math.random() < 0.2) {
                                    population[i].genes[j] = 1;
                                } else if (Math.random() < 0.4) {
                                    population[i].genes[j] = 2;
                                } else if (Math.random() < 0.6) {
                                    population[i].genes[j] = 3;
                                } else if (Math.random() < 0.8) {
                                    population[i].genes[j] = 4;
                                } else {
                                    population[i].genes[j] = 9;
                                }
                                break;
                            case 1:
                                if (Math.random() < 0.2) {
                                    population[i].genes[j] = 0;
                                } else if (Math.random() < 0.4) {
                                    population[i].genes[j] = 2;
                                } else if (Math.random() < 0.6) {
                                    population[i].genes[j] = 3;
                                } else if (Math.random() < 0.8) {
                                    population[i].genes[j] = 4;
                                } else {
                                    population[i].genes[j] = 9;
                                }
                                break;
                            case 2:
                                if (Math.random() < 0.2) {
                                    population[i].genes[j] = 0;
                                } else if (Math.random() < 0.4) {
                                    population[i].genes[j] = 1;
                                } else if (Math.random() < 0.6) {
                                    population[i].genes[j] = 3;
                                } else if (Math.random() < 0.8) {
                                    population[i].genes[j] = 4;
                                } else {
                                    population[i].genes[j] = 9;
                                }
                                break;
                            case 3:
                                if (Math.random() < 0.2) {
                                    population[i].genes[j] = 0;
                                } else if (Math.random() < 0.4) {
                                    population[i].genes[j] = 1;
                                } else if (Math.random() < 0.6) {
                                    population[i].genes[j] = 2;
                                } else if (Math.random() < 0.8) {
                                    population[i].genes[j] = 4;
                                } else {
                                    population[i].genes[j] = 9;
                                }
                                break;
                            case 4:
                                if (Math.random() < 0.2) {
                                    population[i].genes[j] = 0;
                                } else if (Math.random() < 0.4) {
                                    population[i].genes[j] = 1;
                                } else if (Math.random() < 0.6) {
                                    population[i].genes[j] = 2;
                                } else if (Math.random() < 0.8) {
                                    population[i].genes[j] = 3;
                                } else {
                                    population[i].genes[j] = 9;
                                }
                                break;
                            case 9:
                                if (Math.random() < 0.2) {
                                    population[i].genes[j] = 0;
                                } else if (Math.random() < 0.4) {
                                    population[i].genes[j] = 1;
                                } else if (Math.random() < 0.6) {
                                    population[i].genes[j] = 2;
                                } else if (Math.random() < 0.8) {
                                    population[i].genes[j] = 3;
                                } else {
                                    population[i].genes[j] = 4;
                                }
                                break;
                            default:
                                break;
                        }
                    }
                } else if (isLastConditions(j, population)) { // Mutate 6th - 11    0-1            
                    if (rand.nextFloat() <= mutationRate) {
                        switch (population[i].genes[j]) {
                            case 0:
                                if (Math.random() < 0.5) {
                                    population[i].genes[j] = 1;
                                } else {
                                    population[i].genes[j] = 9;
                                }
                                break;
                            case 1:
                                if (Math.random() < 0.5) {
                                    population[i].genes[j] = 0;
                                } else {
                                    population[i].genes[j] = 9;
                                }
                                break;
                            case 9:
                                if (Math.random() < 0.5) {
                                    population[i].genes[j] = 0;
                                } else {
                                    population[i].genes[j] = 1;
                                }
                                break;
                            default:
                                break;
                        }
                    }
                } else if (isOutput(j, population)) { // classifier 0-2
                    if (population[i].genes[j] == 9) {
                        if (rand.nextFloat() <= mutationRate) {
                            population[i].genes[j] = 0;
                        } else {
                            population[i].genes[j] = 1;
                        }
                    }
                    if (rand.nextFloat() <= mutationRate) {
                        switch (population[i].genes[j]) {
                            case 0:
                                if (Math.random() < 0.5) {
                                    population[i].genes[j] = 1;
                                } else {
                                    population[i].genes[j] = 2;
                                }
                                break;
                            case 1:
                                if (Math.random() < 0.5) {
                                    population[i].genes[j] = 0;
                                } else {
                                    population[i].genes[j] = 2;
                                }
                                break;
                            case 2:
                                if (Math.random() < 0.5) {
                                    population[i].genes[j] = 0;
                                } else {
                                    population[i].genes[j] = 1;
                                }
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
        }
    }

    public static void crossover(Individual population[], int populationSize, double crossoverRate) {
        for (int i = 0; i < populationSize; i = i + 2) {
            if (Math.random() < crossoverRate) {
                int split = rand.nextInt(population[i].geneSize);
                for (int j = split; j < population[i].geneSize; j++) {
                    int temp = population[i].genes[j];
                    population[i].genes[j] = population[i + 1].genes[j];
                    population[i + 1].genes[j] = temp;
                }
            }
        }
    }
}
