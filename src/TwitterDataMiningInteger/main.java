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

    public static int populationSize = 500;
    public static double mutationRate = 0.001;
    public static double crossoverRate = 0.7;
    public static int totalFitness = 0;
    public static int iteration = 1;
    public static int ruleSize = 5;
    public static int dataSize = 200;
    public static int totalIterations = 1000;
    public static Individual bestIndividual = new Individual();
    public static Random rand = new Random();
    private static Scanner reader = new Scanner(System.in);  // Reading from System.in

    public static void main(String[] args) {
        Individual population[] = new Individual[populationSize];
        Data dataSet[] = new Data[dataSize];

        // Ask user for table name/ txt file name and read data in dataSet
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
        String ruleBase = deCodeIndividual(bestIndividual);
        System.out.println(ruleBase);
        String fitness = bestIndividual.fitness + " / " + dataSize;

        // Ask user for table/ txt file name
        System.out.println("Would you like to write the results to the database? y/n");
        String input = reader.next();
        if (input.equals("y")) {
            db.writeResults(new Results(ruleBase, mutationRate, populationSize, fitness));
            System.out.println("Done");
        }
    }

    public static String deCodeIndividual(Individual bestIndividual) {
        String out = "";
        int i = 0;
        int j = 0;
        int ruleNumber = 0;

        while (j < ruleSize) {
            ruleNumber = j + 1;
            out = out + "Rule " + ruleNumber + "\n";

            if (bestIndividual.genes[i] == 0) {
                out = out + "If Donald Trump says witch hunt\n";
            } else if (bestIndividual.genes[i] == 1) {
                out = out + "If Kim Kardashian says baby chicago\n";
            } else if (bestIndividual.genes[i] == 2) {
                out = out + "If Jeremy Corbyn posts about meeting a prime minister\n";
            } else if (bestIndividual.genes[i] == 3) {
                out = out + "If Michael Eric Dyson on Trump\n";
            }

            if (bestIndividual.genes[++i] == 0) {
                out = out + "If followers is in the range of 0 - 10\n";
            } else if (bestIndividual.genes[i] == 1) {
                out = out + "If followers is in the range of 11 - 100\n";
            } else if (bestIndividual.genes[i] == 2) {
                out = out + "If followers is in the range of 101 - 300\n";
            } else if (bestIndividual.genes[i] == 3) {
                out = out + "If followers is 300+\n";
            }

            if (bestIndividual.genes[++i] == 0) {
                out = out + "If reply is not favourited\n";
            } else if (bestIndividual.genes[i] == 1) {
                out = out + "If reply is favourited\n";
            }

            if (bestIndividual.genes[++i] == 0) {
                out = out + "If friends is in the range of 0 - 10\n";
            } else if (bestIndividual.genes[i] == 1) {
                out = out + "If friends is in the range of 10 - 100\n";
            } else if (bestIndividual.genes[i] == 2) {
                out = out + "If friends is in the range of 100 - 300\n";
            } else if (bestIndividual.genes[i] == 3) {
                out = out + "If friends is 300+\n";
            }

            if (bestIndividual.genes[++i] == 0) {
                out = out + "If location is in Africa\n";
            } else if (bestIndividual.genes[i] == 1) {
                out = out + "If location is in Asia\n";
            } else if (bestIndividual.genes[i] == 2) {
                out = out + "If location is in Oceania\n";
            } else if (bestIndividual.genes[i] == 3) {
                out = out + "If location is in Europe\n";
            } else if (bestIndividual.genes[i] == 4) {
                out = out + "If location is in North America\n";
            } else if (bestIndividual.genes[i] == 5) {
                out = out + "If location is in South America\n";
            }

            if (bestIndividual.genes[++i] == 0) {
                out = out + "If user is not verified\n";
            } else if (bestIndividual.genes[i] == 1) {
                out = out + "If user is verified\n";
            }

            if (bestIndividual.genes[++i] == 0) {
                out = out + "If comment does not contains a swear word\n";
            } else if (bestIndividual.genes[i] == 1) {
                out = out + "If comment contains a swear word\n";
            }

            if (bestIndividual.genes[++i] == 0) {
                out = out + "If comment does not contain a positive word\n";
            } else if (bestIndividual.genes[i] == 1) {
                out = out + "If comment contains a positive word\n";
            }

            if (bestIndividual.genes[++i] == 0) {
                out = out + "If comment does not contain a negative word\n";
            } else if (bestIndividual.genes[i] == 1) {
                out = out + "If comment contains a negative word\n";
            }

            if (bestIndividual.genes[++i] == 0) {
                out = out + "If comment does not contain a positive emoji\n";
            } else if (bestIndividual.genes[i] == 1) {
                out = out + "If comment contains a positive emoji\n";
            }

            if (bestIndividual.genes[++i] == 0) {
                out = out + "If comment does not contain a negative emoji\n";
            } else if (bestIndividual.genes[i] == 1) {
                out = out + "If comment contains a negative emoji\n";
            }

            if (bestIndividual.genes[++i] == 0) {
                out = out + "THEN comment is negative\n";
            } else if (bestIndividual.genes[i] == 1) {
                out = out + "THEN comment is neutral\n";
            } else if (bestIndividual.genes[i] == 2) {
                out = out + "THEN comment is positive\n";
            }

            j++;
            i++;
        }
        return out;
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
                if (isFirstCondition(j, population)) { // context ID 0-3
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
