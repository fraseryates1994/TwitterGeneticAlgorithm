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
    public static double mutationRate = 0.001;
    public static double crossoverRate = 0.7;
    public static int totalFitness = 0;
    public static int iteration = 1;
    public static int ruleSize = 5;
    public static int dataSize = 200;
    public static int totalIterations = 10000;
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
        evaluateFitness(population, dataSet, populationSize, ruleSize);
        printGenes(population);

        while (iteration < totalIterations) {
            if (solutionFound(population)) {
                break;
            }
            tournamentSelection(population, populationSize, totalFitness);
            evaluateFitness(population, dataSet, populationSize, ruleSize);
            mutate(population, populationSize, mutationRate);
            evaluateFitness(population, dataSet, populationSize, ruleSize);

            setFittest(getFittest(population)); // Print most fit 
            System.out.println("Generation " + iteration + ". Fittest gene = " + getFittest(population).fitness);
            //System.out.println(getFittest(population).fitness);

            iteration++;
        }
        
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
