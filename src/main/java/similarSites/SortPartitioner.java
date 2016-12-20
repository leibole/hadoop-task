package similarSites;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;


public class SortPartitioner extends Partitioner<TwoSitesSimilarity, Text> {

    @Override
    public int getPartition(TwoSitesSimilarity key, Text val, int numPartitions) {
        return key.getSite1().toString().hashCode() % numPartitions;
    }

}
