package TwitterDataMiningInteger;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SocialMediaDB {

    JDBCWrapper wrapper;

    public SocialMediaDB(JDBCWrapper w) {
        wrapper = w;
    }

    public void writeResults(Results results) {
        wrapper.createStatement();

        int id = getMaxId("results") + 1;
        try {
            wrapper.getStatement().executeUpdate("insert into " + "results"
                    + "(ID, RULEBASE, FITNESS, populationSize, mutationRate) values(" + id + ",'" + results.getRuleBase() + "','" + results.getFitness() + "'," + results.getPopulationSize() + ",'" + String.valueOf(results.getMutationRate()) + "')");
        } catch (SQLException ex) {
            Logger.getLogger(SocialMediaDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int getMaxId(String tableName) {
        wrapper.createStatement();
        wrapper.createResultSet("SELECT MAX(id) FROM " + tableName);
        int maxId = 0;
        try {
            while (wrapper.getResultSet().next()) {
                maxId = wrapper.getResultSet().getInt(1);
                if (wrapper.getResultSet().wasNull()) {
                    return -1;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (NullPointerException ex) {
            return 0;
        }
        return maxId;
    }

    public Data[] getTwitterData(String tableName, int dataSize) {
        Data dataSet[] = new Data[dataSize];
        wrapper.createStatement();
        int i = 0;
        int j = 0;

        try {
            while (i < dataSize) {
                wrapper.createResultSet("SELECT * FROM " + tableName + " WHERE id=" + i);
                wrapper.getResultSet().next();

                j = 0;
                dataSet[i] = new Data();
                dataSet[i].variables[j] = wrapper.getResultSet().getInt("contextId");
                dataSet[i].variables[++j] = wrapper.getResultSet().getInt("followersCount");
                dataSet[i].variables[++j] = wrapper.getResultSet().getInt("favouriteCount");
                dataSet[i].variables[++j] = wrapper.getResultSet().getInt("friendCount");
                dataSet[i].variables[++j] = wrapper.getResultSet().getInt("location");
                dataSet[i].variables[++j] = wrapper.getResultSet().getInt("isVerified");
                dataSet[i].variables[++j] = wrapper.getResultSet().getInt("hasSwear");
                dataSet[i].variables[++j] = wrapper.getResultSet().getInt("hasPositiveWord");
                dataSet[i].variables[++j] = wrapper.getResultSet().getInt("hasNegativeWord");
                dataSet[i].variables[++j] = wrapper.getResultSet().getInt("hasPositiveEmoji");
                dataSet[i].variables[++j] = wrapper.getResultSet().getInt("hasNegativeEmoji");
                dataSet[i].output = wrapper.getResultSet().getInt("classifier");
                i++;

            }
        } catch (SQLException ex) {
            Logger.getLogger(SocialMediaDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dataSet;
    }
}
