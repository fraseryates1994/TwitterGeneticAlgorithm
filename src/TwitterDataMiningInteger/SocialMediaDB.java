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
