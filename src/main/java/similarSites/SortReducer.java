package similarSites;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;


public class SortReducer extends Reducer<TwoSitesSimilarity, Text, Text, Text> {

    @Override
    protected void reduce(TwoSitesSimilarity key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        
    	int i = 0;
    	Text site = key.getSite1();
    	Text similarSite = new Text();
    	for (Text value:values){
    		if (i==10)
    			return;
    		similarSite.set(value.toString());
    		context.write(site, similarSite);
    		i++;
    			
    	}
    }
}