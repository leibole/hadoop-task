package similarSites;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

public class SitesPerTagReducer extends Reducer<Text, Text, Text, Text> {
    public void reduce(Text text, Iterable<Text> values, Context context)
            throws IOException, InterruptedException {
    	List<String> sites = new ArrayList<String>();
    	String out = "";
        for (Text value : values) {
        	out = out + "," + value.toString();
            sites.add(value.toString());
        }
        out = out.substring(1);
        
        context.write(text, new Text(out));
    }
}
