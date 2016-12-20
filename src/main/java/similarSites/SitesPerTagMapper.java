package similarSites;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class SitesPerTagMapper extends Mapper<Object, Text, Text, Text> {
    private Text tag = new Text();
    private Text site = new Text();
 
    public void map(Object key, Text value, Context context)
            throws IOException, InterruptedException {
 
        String[] siteTag = value.toString().split(" ");
        site.set(siteTag[0]);
        tag.set(siteTag[1]);
        context.write(tag, site);
    }

}
