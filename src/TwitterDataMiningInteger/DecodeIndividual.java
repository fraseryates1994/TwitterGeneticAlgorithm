package TwitterDataMiningInteger;


/**
 *
 * @author Fraser
 */
public class DecodeIndividual {
    public static String deCodeIndividual(Individual bestIndividual, int ruleSize) {
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
}
