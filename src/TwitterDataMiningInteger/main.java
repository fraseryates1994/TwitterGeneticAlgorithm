package TwitterDataMiningInteger;

import static TwitterDataMiningInteger.DecodeIndividual.*;
import static TwitterDataMiningInteger.Recombination.*;
import static TwitterDataMiningInteger.EvaluateFitness.*;
import static TwitterDataMiningInteger.Selection.*;
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
    public static double mutationRate = 0.002;
    public static double crossoverRate = 0.7;
    public static int totalFitness = 0;
    public static int iteration = 1;
    public static int ruleSize = 10;
    public static int dataSize = 450;
    public static int totalIterations = 10000;
    public static Individual bestIndividual = new Individual();
    public static Random rand = new Random();
    private static Scanner reader = new Scanner(System.in);  // Reading from System.in

    public static void main(String[] args) {
        Individual population[] = new Individual[populationSize];
        Data dataSet[] = new Data[dataSize];

        int validationFitness[] = new int[totalIterations];

        // Ask user for table name/ txt file name and read data in dataSet
        System.out.println("Enter the table you would like to read from:");
        String fileName = reader.next();
        JDBCWrapper wr = new JDBCWrapper("org.apache.derby.jdbc.ClientDriver", "jdbc:derby://localhost:1527/SocialMedia", "social", "fraz");
        SocialMediaDB db = new SocialMediaDB(wr);
        dataSet = db.getTwitterData(fileName, dataSize);
//        Data[] trainingSet = getTrainingSet(dataSet);
//        Data[] validationSet = getValidationSet(dataSet);

        initiate(population);
        evaluateFitness(population, dataSet, populationSize, ruleSize);
        // printGenes(population);

        while (iteration < totalIterations) {
            if (solutionFound(population)) {
                break;
            }
            tournamentSelection(population, populationSize, totalFitness);
            evaluateFitness(population, dataSet, populationSize, ruleSize);
            mutate(population, populationSize, mutationRate);
            evaluateFitness(population, dataSet, populationSize, ruleSize);

            Individual fittest = getFittest(population);
            setFittest(fittest);
            Individual toValidate = clone(fittest);
            evaluateIndFitness(toValidate, dataSet, ruleSize / 2);
            validationFitness[iteration] = toValidate.fitness;

            System.out.println("Generation " + iteration + ". Fittest gene = " + getFittest(population).fitness);
            //System.out.println(getFittest(population).fitness);
//            System.out.println("Training Data  : Generation " + iteration + ". Fittest gene = " + fittest.fitness
//                    + "\nValidation Data: Generation " + iteration + ". fittest gene = " + toValidate.fitness);
//            System.out.println(toValidate.fitness);

            iteration++;
        }
        
//        for (int v : validationFitness) {
//            System.out.println(v);
//        }

        // Print when solution has been found
        System.out.println("Generation = " + (iteration - 1));
        System.out.println("Best Individual = " + bestIndividual);
        seperateRules(bestIndividual);
        String ruleBase = deCodeIndividual(bestIndividual, ruleSize);
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

    public static Data[] getTrainingSet(Data[] dataSet) {
        Data[] trainingSet1 = Arrays.copyOfRange(dataSet, 0, 25);
        Data[] trainingSet2 = Arrays.copyOfRange(dataSet, 50, 75);
        Data[] trainingSet3 = Arrays.copyOfRange(dataSet, 100, 125);
        Data[] trainingSet4 = Arrays.copyOfRange(dataSet, 150, 175);
        Data[] trainingSet5 = Arrays.copyOfRange(dataSet, 200, 225);
        Data[] trainingSet6 = Arrays.copyOfRange(dataSet, 250, 275);
        Data[] trainingSet7 = Arrays.copyOfRange(dataSet, 300, 325);
        Data[] trainingSet8 = Arrays.copyOfRange(dataSet, 350, 375);
        Data[] trainingSet9 = Arrays.copyOfRange(dataSet, 400, 425);

        Data[] test = combine(trainingSet1, trainingSet2);
        Data[] test2 = combine(trainingSet3, trainingSet4);
        Data[] test3 = combine(trainingSet5, trainingSet6);
        Data[] test4 = combine(trainingSet7, trainingSet8);

        Data[] test5 = combine(test, test2);
        Data[] test6 = combine(test3, test4);
        Data[] test7 = combine(test6, trainingSet9);

        Data[] ret = combine(test5, test7);

        return ret;
    }

    public static Data[] getValidationSet(Data[] dataSet) {
        Data[] trainingSet1 = Arrays.copyOfRange(dataSet, 25, 50);
        Data[] trainingSet2 = Arrays.copyOfRange(dataSet, 75, 100);
        Data[] trainingSet3 = Arrays.copyOfRange(dataSet, 125, 150);
        Data[] trainingSet4 = Arrays.copyOfRange(dataSet, 175, 200);
        Data[] trainingSet5 = Arrays.copyOfRange(dataSet, 225, 250);
        Data[] trainingSet6 = Arrays.copyOfRange(dataSet, 275, 300);
        Data[] trainingSet7 = Arrays.copyOfRange(dataSet, 325, 350);
        Data[] trainingSet8 = Arrays.copyOfRange(dataSet, 375, 400);
        Data[] trainingSet9 = Arrays.copyOfRange(dataSet, 425, 450);

        Data[] test = combine(trainingSet1, trainingSet2);
        Data[] test2 = combine(trainingSet3, trainingSet4);
        Data[] test3 = combine(trainingSet5, trainingSet6);
        Data[] test4 = combine(trainingSet7, trainingSet8);

        Data[] test5 = combine(test, test2);
        Data[] test6 = combine(test3, test4);
        Data[] test7 = combine(test6, trainingSet9);

        Data[] ret = combine(test5, test7);

        return ret;
    }

    public static Data[] combine(Data[] a, Data[] b) {
        int length = a.length + b.length;
        Data[] result = new Data[length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }

    // fitness evaluation
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

    public static boolean solutionFound(Individual population[]) {
        for (int i = 0; i < populationSize; i++) {
            if (population[i].fitness == dataSize) {
                System.out.println("Solution Found!");
                return true;
            }
        }
        return false;
    }

    public static Individual clone(Individual individualToCopy) {
        int[] temp = new int[individualToCopy.geneSize];
        Individual twin = new Individual();
        for (int i = 0; i < temp.length; i++) {
            temp[i] = individualToCopy.genes[i];
            twin.genes[i] = temp[i];
        }
        twin.fitness = individualToCopy.fitness;

        return twin;
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
                System.out.print(data1.variables[i] + ", ");
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
