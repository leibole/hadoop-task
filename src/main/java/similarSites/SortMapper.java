package similarSites;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;


public class SortMapper extends Mapper<LongWritable, Text, TwoSitesSimilarity, Text> {

@Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] sites = line.split("	")[0].split(" ");
        String site1 = sites[0];
        String site2 = sites[1];
        String count = line.split("	")[1];

        context.write(new TwoSitesSimilarity(site1, site2 , count), new Text(site2 + " " + count));
        context.write(new TwoSitesSimilarity(site2, site1 , count), new Text(site1 + " " + count));
    }
}