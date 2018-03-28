package TwitterDataMiningInteger;

import java.util.Random;

/**
 *
 * @author Fraser
 */
public class Selection {

    public static Random rand = new Random();

    public static void rouletteSelection(Individual[] population, int populationSize, int totalFitness) {
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

    public static void tournamentSelection(Individual population[], int populationSize, int totalFitness) {
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
}
