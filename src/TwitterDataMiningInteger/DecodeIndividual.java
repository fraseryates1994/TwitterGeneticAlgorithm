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
                out = out + "If Donald Trump says theres a witch hunt against him\n";
            } else if (bestIndividual.genes[i] == 1) {
                out = out + "If Kim Kardashian posts about her new baby Chicago\n";
            } else if (bestIndividual.genes[i] == 2) {
                out = out + "If Jeremy Corbyn posts about meeting the prime minister of Macedonia\n";
            } else if (bestIndividual.genes[i] == 3) {
                out = out + "If Michael Eric Dyson says Obama was a better president than Trump\n";
            } else if (bestIndividual.genes[i] == 4) {
                out = out + "If Dwyane Wade posts about a fan of his that died in a school shooting\n";
            } else if (bestIndividual.genes[i] == 5) {
                out = out + "If Stranger Things posts about a tour of theres\n";
            } else if (bestIndividual.genes[i] == 6) {
                out = out + "If Vadim Lavrusik posts about being involved in a school shooting\n";
            } else if (bestIndividual.genes[i] == 7) {
                out = out + "If Arctic Monkeys post about their new album\n";
            } else if (bestIndividual.genes[i] == 8) {
                out = out + "If Fortnite post about an update for their game\n";
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
