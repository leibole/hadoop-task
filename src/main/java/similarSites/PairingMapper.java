package similarSites;

import java.io.IOException;
import java.util.Arrays;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class PairingMapper extends Mapper<Object, Text, Text, IntWritable> {
    private Text pair = new Text();
    private IntWritable one = new IntWritable(1);
 
    public void map(Object key, Text value, Context context)
            throws IOException, InterruptedException {
 
        String[] sites = value.toString().split("\\s")[1].split(",");
        Arrays.sort(sites);
        for (int i=0;i<sites.length; i++){
        	for (int j=i+1; j<sites.length; j++){
        		pair.set(sites[i] + " " + sites[j]);
        		context.write(pair, one);
        	}
        }
    }

}
