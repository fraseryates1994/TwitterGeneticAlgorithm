package TwitterDataMiningInteger;

/**
 *
 * @author Fraser
 */
public class Results {
    private String ruleBase;
    private double mutationRate;
    private int populationSize;
    private String fitness;

    public Results(String ruleBase, double mutationRate, int populationSize, String fitness) {
        this.ruleBase = ruleBase;
        this.mutationRate = mutationRate;
        this.populationSize = populationSize;
        this.fitness = fitness;
    }
    
    // GETTERS
    public String getRuleBase() {
        return ruleBase;
    }

    public double getMutationRate() {
        return mutationRate;
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public String getFitness() {
        return fitness;
    }
    
}
