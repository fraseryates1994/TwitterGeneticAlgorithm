package TwitterDataMiningInteger;

import java.io.File;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Fraser
 */
public class main {

    public static int populationSize = 100;
    public static double mutationRate = 0.001;
    public static double crossoverRate = 0.7;
    public static int totalFitness = 0;
    public static int iteration = 1;
    public static int ruleSize = 3;
    public static int dataSize = 100;
    public static int totalIterations = 10000;
    public static Individual bestIndividual = new Individual();
    public static Random rand = new Random();
    private static Scanner reader = new Scanner(System.in);  // Reading from System.in

    public static void main(String[] args) {
        Individual population[] = new Individual[populationSize];
        Data dataSet[] = new Data[dataSize];

        // Ask user for table name/ txt file name
        System.out.println("Enter the table you would like to read from:");
        String fileName = reader.next();

        JDBCWrapper wr = new JDBCWrapper("org.apache.derby.jdbc.ClientDriver", "jdbc:derby://localhost:1527/SocialMedia", "social", "fraz");
        SocialMediaDB db = new SocialMediaDB(wr);
        dataSet = db.getTwitterData(fileName, dataSize);
        printData(dataSet);

        initiate(population);
        evaluateFitness(population, dataSet);
        printGenes(population);

        while (iteration < totalIterations) {
            if (solutionFound(population)) {
                break;
            }
            tournamentSelection(population);
            evaluateFitness(population, dataSet);
            mutate(population);
            evaluateFitness(population, dataSet);

            setFittest(getFittest(population)); // Print most fit 
            System.out.println("Generation " + iteration + ". Fittest gene = " + getFittest(population).fitness);
            //System.out.println(getFittest(population).fitness);
            iteration++;
        }
        // Print when solution has been found
        System.out.println("Generation = " + (iteration - 1));
        System.out.println("Best Individual = " + bestIndividual);
        seperateRules(bestIndividual);
    }

    public static void setFittest(Individual individual) {
        if (individual.fitness > bestIndividual.fitness) {
            for (int i = 0; i < individual.geneSize; i++) {
                bestIndividual.genes[i] = individual.genes[i];
            }
            bestIndividual.fitness = individual.fitness;
        }
    }

    public static void seperateRules(Individual individual) {
        int m = 0;
        Rule[] ruleBase = new Rule[ruleSize];
        for (int i = 0; i < ruleSize; i++) {
            ruleBase[i] = new Rule();
            for (int j = 0; j < ruleBase[i].conditionSize; j++) {
                System.out.print(individual.genes[m++] + " ");
            }
            System.out.print(" = " + individual.genes[m++]);
            System.out.print("\n");
        }
    }

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

    public static boolean solutionFound(Individual population[]) {
        for (int i = 0; i < populationSize; i++) {
            if (population[i].fitness == dataSize) {
                System.out.println("Solution Found!");
                return true;
            }
        }
        return false;
    }

    public static void seperateRules(Individual[] population) {
        Individual fittest = getFittest(population);
        for (int i = 0; i != fittest.geneSize; i += 6) {
            for (int j = 0; j != 5; ++j) {
                System.out.print(fittest.genes[i + j]);
            }
            System.out.println(" output: " + fittest.genes[i + 5]);
        }
    }

    public static Individual getFittest(Individual population[]) {
        Individual fittest = new Individual();
        for (int i = 0; i < populationSize; i++) {
            if (population[i].fitness > fittest.fitness) {
                fittest = population[i];
            }
        }
        return fittest;
    }

    public static void printData(Data[] data) {
        for (Data data1 : data) {
            for (int i = 0; i < data1.variableSize; i++) {
                System.out.print(data1.variables[i]);
            }
            System.out.print(data1.output);
            System.out.println("");
        }
    }

    public static void printGenes(Individual population[]) {
        totalFitness = 0;
        for (int i = 0; i < populationSize; i++) {
            System.out.println(Arrays.toString(population[i].genes) + " Fitness = " + population[i].fitness);
            totalFitness = totalFitness + population[i].fitness;
        }
        System.out.println("Total Fitness: " + totalFitness);
    }

    public static void evaluateFitness(Individual[] population, Data[] dataSet) {
        for (int i = 0; i < populationSize; i++) {
            evaluateIndFitness(population[i], dataSet);
        }
    }

    public static void evaluateIndFitness(Individual individual, Data[] dataSet) {
        Rule ruleBase[] = new Rule[ruleSize];
        int k = 0;
        individual.fitness = 0;

        // Make rules
        for (int i = 0; i < ruleSize; i++) {
            ruleBase[i] = new Rule();
            for (int j = 0; j < ruleBase[i].conditionSize; j++) {
                ruleBase[i].condition[j] = individual.genes[k++];
            }
            ruleBase[i].output = individual.genes[k++];
        }

        // Incremement if rule = condition from txt file
        for (Data dataSet1 : dataSet) {
            for (Rule ruleBase1 : ruleBase) {
                if (matchesCondition(dataSet1, ruleBase1, dataSet1.variables.length)) {
                    if (matchesOutput(dataSet1, ruleBase1)) {
                        individual.fitness++;
                    }
                    break;
                }
            }
        }
    }

    public static boolean matchesCondition(Data inputData, Rule rule, int loopSize) {
        for (int i = 0; i < loopSize; i++) {
            if (inputData.variables[i] != rule.condition[i] && (rule.condition[i] != 9)) {
                return false;
            }
        }
        return true;
    }

    public static boolean matchesOutput(Data inputData, Rule rule) {
        return inputData.output == rule.output;
    }

    public static void mutate(Individual[] population) {
        Rule rule = new Rule();
        //mutation
        for (int i = 0; i != populationSize; ++i) {
            for (int j = 0; j != population[i].geneSize; ++j) {
                if (isFirstCondition(j, population)) {
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
                } else if (isSecondCondition(j, population)) { // first condition 0-3
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
                } else if (isThirdCondition(j, population)) { // Mutate 3rd condition 0-1                 
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
                } else if (isFourthCondition(j, population)) { // Mutate 4th condition 0-3
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
                } else if (isFifthCondition(j, population)) { // Matches 5th condition 0-4
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
                } else if (isLastConditions(j, population)) { // Mutate 6th - 11                
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
                } else if (isOutput(j, population)) {
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

//    public static void mutateContextOutput(Individual[] population) {
//        Rule rule = new Rule();
//        //mutation
//        for (int i = 0; i != populationSize; ++i) {
//            for (int j = 0; j != population[i].geneSize; ++j) {
//                if (isOutput(j)) {
//                    switch (population[i].genes[j]) {
//                        case 0:
//                            if (Math.random() < 0.5) {
//                                population[i].genes[j] = 1;
//                            } else {
//                                population[i].genes[j] = 9;
//                            }
//                            break;
//                        case 1:
//                            if (Math.random() < 0.5) {
//                                population[i].genes[j] = 0;
//                            } else {
//                                population[i].genes[j] = 9;
//                            }
//                            break;
//                        case 9:
//                            if (Math.random() < 0.5) {
//                                population[i].genes[j] = 0;
//                            } else {
//                                population[i].genes[j] = 1;
//                            }
//                            break;
//                        default:
//                            break;
//                    }
//                } else if (isSecondCondition(j)) { // first condition 0-3
//                    if (rand.nextFloat() <= mutationRate) {
//                        switch (population[i].genes[j]) {
//                            case 0:
//                                if (Math.random() < 0.25) {
//                                    population[i].genes[j] = 1;
//                                } else if (Math.random() < 0.5) {
//                                    population[i].genes[j] = 2;
//                                } else if (Math.random() < 0.75) {
//                                    population[i].genes[j] = 3;
//                                } else {
//                                    population[i].genes[j] = 9;
//                                }
//                                break;
//                            case 1:
//                                if (Math.random() < 0.25) {
//                                    population[i].genes[j] = 0;
//                                } else if (Math.random() < 0.5) {
//                                    population[i].genes[j] = 2;
//                                } else if (Math.random() < 0.75) {
//                                    population[i].genes[j] = 3;
//                                } else {
//                                    population[i].genes[j] = 9;
//                                }
//                                break;
//                            case 2:
//                                if (Math.random() < 0.25) {
//                                    population[i].genes[j] = 0;
//                                } else if (Math.random() < 0.5) {
//                                    population[i].genes[j] = 1;
//                                } else if (Math.random() < 0.75) {
//                                    population[i].genes[j] = 3;
//                                } else {
//                                    population[i].genes[j] = 9;
//                                }
//                                break;
//                            case 3:
//                                if (Math.random() < 0.25) {
//                                    population[i].genes[j] = 0;
//                                } else if (Math.random() < 0.5) {
//                                    population[i].genes[j] = 1;
//                                } else if (Math.random() < 0.75) {
//                                    population[i].genes[j] = 2;
//                                } else {
//                                    population[i].genes[j] = 9;
//                                }
//                                break;
//                            case 9:
//                                if (Math.random() < 0.25) {
//                                    population[i].genes[j] = 0;
//                                } else if (Math.random() < 0.5) {
//                                    population[i].genes[j] = 1;
//                                } else if (Math.random() < 0.75) {
//                                    population[i].genes[j] = 2;
//                                } else {
//                                    population[i].genes[j] = 3;
//                                }
//                                break;
//                            default:
//                                break;
//                        }
//                    }
//                } else if (isThirdCondition(j)) { // Mutate 3rd condition 0-1                 
//                    if (rand.nextFloat() <= mutationRate) {
//                        switch (population[i].genes[j]) {
//                            case 0:
//                                if (Math.random() < 0.5) {
//                                    population[i].genes[j] = 1;
//                                } else {
//                                    population[i].genes[j] = 9;
//                                }
//                                break;
//                            case 1:
//                                if (Math.random() < 0.5) {
//                                    population[i].genes[j] = 0;
//                                } else {
//                                    population[i].genes[j] = 9;
//                                }
//                                break;
//                            case 9:
//                                if (Math.random() < 0.5) {
//                                    population[i].genes[j] = 0;
//                                } else {
//                                    population[i].genes[j] = 1;
//                                }
//                                break;
//                            default:
//                                break;
//                        }
//                    }
//                } else if (isFourthCondition(j)) { // Mutate 4th condition 0-3
//                    if (rand.nextFloat() <= mutationRate) {
//                        switch (population[i].genes[j]) {
//                            case 0:
//                                if (Math.random() < 0.25) {
//                                    population[i].genes[j] = 1;
//                                } else if (Math.random() < 0.5) {
//                                    population[i].genes[j] = 2;
//                                } else if (Math.random() < 0.75) {
//                                    population[i].genes[j] = 3;
//                                } else {
//                                    population[i].genes[j] = 9;
//                                }
//                                break;
//                            case 1:
//                                if (Math.random() < 0.25) {
//                                    population[i].genes[j] = 0;
//                                } else if (Math.random() < 0.5) {
//                                    population[i].genes[j] = 2;
//                                } else if (Math.random() < 0.75) {
//                                    population[i].genes[j] = 3;
//                                } else {
//                                    population[i].genes[j] = 9;
//                                }
//                                break;
//                            case 2:
//                                if (Math.random() < 0.25) {
//                                    population[i].genes[j] = 0;
//                                } else if (Math.random() < 0.5) {
//                                    population[i].genes[j] = 1;
//                                } else if (Math.random() < 0.75) {
//                                    population[i].genes[j] = 3;
//                                } else {
//                                    population[i].genes[j] = 9;
//                                }
//                                break;
//                            case 3:
//                                if (Math.random() < 0.25) {
//                                    population[i].genes[j] = 0;
//                                } else if (Math.random() < 0.5) {
//                                    population[i].genes[j] = 1;
//                                } else if (Math.random() < 0.75) {
//                                    population[i].genes[j] = 2;
//                                } else {
//                                    population[i].genes[j] = 9;
//                                }
//                                break;
//                            case 9:
//                                if (Math.random() < 0.25) {
//                                    population[i].genes[j] = 0;
//                                } else if (Math.random() < 0.5) {
//                                    population[i].genes[j] = 1;
//                                } else if (Math.random() < 0.75) {
//                                    population[i].genes[j] = 2;
//                                } else {
//                                    population[i].genes[j] = 3;
//                                }
//                                break;
//                            default:
//                                break;
//                        }
//                    }
//                } else if (isFifthCondition(j)) { // Matches 5th condition 0-4
//                    if (rand.nextFloat() <= mutationRate) {
//                        switch (population[i].genes[j]) {
//                            case 0:
//                                if (Math.random() < 0.2) {
//                                    population[i].genes[j] = 1;
//                                } else if (Math.random() < 0.4) {
//                                    population[i].genes[j] = 2;
//                                } else if (Math.random() < 0.6) {
//                                    population[i].genes[j] = 3;
//                                } else if (Math.random() < 0.8) {
//                                    population[i].genes[j] = 4;
//                                } else {
//                                    population[i].genes[j] = 9;
//                                }
//                                break;
//                            case 1:
//                                if (Math.random() < 0.2) {
//                                    population[i].genes[j] = 0;
//                                } else if (Math.random() < 0.4) {
//                                    population[i].genes[j] = 2;
//                                } else if (Math.random() < 0.6) {
//                                    population[i].genes[j] = 3;
//                                } else if (Math.random() < 0.8) {
//                                    population[i].genes[j] = 4;
//                                } else {
//                                    population[i].genes[j] = 9;
//                                }
//                                break;
//                            case 2:
//                                if (Math.random() < 0.2) {
//                                    population[i].genes[j] = 0;
//                                } else if (Math.random() < 0.4) {
//                                    population[i].genes[j] = 1;
//                                } else if (Math.random() < 0.6) {
//                                    population[i].genes[j] = 3;
//                                } else if (Math.random() < 0.8) {
//                                    population[i].genes[j] = 4;
//                                } else {
//                                    population[i].genes[j] = 9;
//                                }
//                                break;
//                            case 3:
//                                if (Math.random() < 0.2) {
//                                    population[i].genes[j] = 0;
//                                } else if (Math.random() < 0.4) {
//                                    population[i].genes[j] = 1;
//                                } else if (Math.random() < 0.6) {
//                                    population[i].genes[j] = 2;
//                                } else if (Math.random() < 0.8) {
//                                    population[i].genes[j] = 4;
//                                } else {
//                                    population[i].genes[j] = 9;
//                                }
//                                break;
//                            case 4:
//                                if (Math.random() < 0.2) {
//                                    population[i].genes[j] = 0;
//                                } else if (Math.random() < 0.4) {
//                                    population[i].genes[j] = 1;
//                                } else if (Math.random() < 0.6) {
//                                    population[i].genes[j] = 2;
//                                } else if (Math.random() < 0.8) {
//                                    population[i].genes[j] = 3;
//                                } else {
//                                    population[i].genes[j] = 9;
//                                }
//                                break;
//                            case 9:
//                                if (Math.random() < 0.2) {
//                                    population[i].genes[j] = 0;
//                                } else if (Math.random() < 0.4) {
//                                    population[i].genes[j] = 1;
//                                } else if (Math.random() < 0.6) {
//                                    population[i].genes[j] = 2;
//                                } else if (Math.random() < 0.8) {
//                                    population[i].genes[j] = 3;
//                                } else {
//                                    population[i].genes[j] = 4;
//                                }
//                                break;
//                            default:
//                                break;
//                        }
//                    }
//                } else if (isLastConditions(j)) { // Mutate 6th - 11                
//                    if (rand.nextFloat() <= mutationRate) {
//                        switch (population[i].genes[j]) {
//                            case 0:
//                                if (Math.random() < 0.5) {
//                                    population[i].genes[j] = 1;
//                                } else {
//                                    population[i].genes[j] = 9;
//                                }
//                                break;
//                            case 1:
//                                if (Math.random() < 0.5) {
//                                    population[i].genes[j] = 0;
//                                } else {
//                                    population[i].genes[j] = 9;
//                                }
//                                break;
//                            case 9:
//                                if (Math.random() < 0.5) {
//                                    population[i].genes[j] = 0;
//                                } else {
//                                    population[i].genes[j] = 1;
//                                }
//                                break;
//                            default:
//                                break;
//                        }
//                    }
//                } else if (isFirstCondition(j)) {
//                    if (population[i].genes[j] == 9) {
//                        if (rand.nextFloat() <= mutationRate) {
//                            population[i].genes[j] = 0;
//                        } else {
//                            population[i].genes[j] = 1;
//                        }
//                    }
//                    if (rand.nextFloat() <= mutationRate) {
//                        switch (population[i].genes[j]) {
//                            case 0:
//                                if (Math.random() < 0.5) {
//                                    population[i].genes[j] = 1;
//                                } else {
//                                    population[i].genes[j] = 2;
//                                }
//                                break;
//                            case 1:
//                                if (Math.random() < 0.5) {
//                                    population[i].genes[j] = 0;
//                                } else {
//                                    population[i].genes[j] = 2;
//                                }
//                                break;
//                            case 2:
//                                if (Math.random() < 0.5) {
//                                    population[i].genes[j] = 0;
//                                } else {
//                                    population[i].genes[j] = 1;
//                                }
//                                break;
//                            default:
//                                break;
//                        }
//                    }
//                }
//            }
//        }
//    }

    public static void crossover(Individual population[]) {
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

    public static void rouletteSelection(Individual[] population) {
        Individual offspring[] = new Individual[populationSize];

        for (Individual individual : population) {
            totalFitness += individual.fitness;
        }

        for (int i = 0; i < populationSize; i++) {
            offspring[i] = new Individual();
        }

        for (int i = 0; i < populationSize; i++) {
            int stopped = rand.nextInt(totalFitness);

            int j = 0;
            for (j = 0; j < populationSize; j++) {
                stopped = stopped - population[j].fitness;
                if (stopped <= 0) {
                    break;
                }
            }
            offspring[i] = population[j];
        }
    }

    public static void tournamentSelection(Individual population[]) {
        Individual offspring[] = new Individual[populationSize];

        // Selecting 2 random genes, evaluate them and add to the offspring
        for (int i = 0; i < populationSize; i++) {

            int parent1 = rand.nextInt(populationSize);
            int parent2 = rand.nextInt(populationSize);

            // Initialise offspring
            offspring[i] = new Individual();

            if (population[parent1].fitness >= population[parent2].fitness) {
                offspring[i] = population[parent1];
            } else {
                offspring[i] = population[parent2];
            }
        }

        // Set offspring back to population
        for (int i = 0; i < populationSize; i++) {
            population[i] = new Individual();
            for (int j = 0; j < offspring[i].geneSize; j++) {
                population[i].genes[j] = offspring[i].genes[j];
            }
            population[i].fitness = offspring[i].fitness;
        }
    }

    public static void initiate(Individual[] population) {
        // Populating initial random population
        for (int i = 0; i < populationSize; i++) {
            population[i] = new Individual();
            for (int j = 0; j < population[i].geneSize; j++) {
                population[i].genes[j] = rand.nextInt(2);
                population[i].genes[++j] = rand.nextInt(4);
                population[i].genes[++j] = rand.nextInt(2);
                population[i].genes[++j] = rand.nextInt(4);
                population[i].genes[++j] = rand.nextInt(5);
                population[i].genes[++j] = rand.nextInt(2);
                population[i].genes[++j] = rand.nextInt(2);
                population[i].genes[++j] = rand.nextInt(2);
                population[i].genes[++j] = rand.nextInt(2);
                population[i].genes[++j] = rand.nextInt(2);
                population[i].genes[++j] = rand.nextInt(2);
                population[i].genes[++j] = rand.nextInt(3);
            }
        }
    }
}
