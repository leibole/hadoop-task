package similarSites;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;




public class SiteSimDriver {

	public static void main(String[] args) throws IOException,
    InterruptedException, ClassNotFoundException {
		// 

		  
        Path inputPath = new Path(args[0]);
        Path outputDir = new Path(args[1]);
        Path pairingOutDir = new Path(args[2]);
        Path sortOutDir = new Path(args[3]);
//        Path pairingOutDir = new Path("/Users/idoleibovich/Documents/pairingOut");
//        Path sortOutDir = new Path("/Users/idoleibovich/Documents/sortOut");
 
        // Create configuration
        Configuration conf = new Configuration(true);
 
        // Create job
        Job job = new Job(conf, "SitesPerTag");
 
        // Setup MapReduce
        job.setMapperClass(SitesPerTagMapper.class);
        job.setReducerClass(SitesPerTagReducer.class);
        job.setNumReduceTasks(1);
 
        // Specify key / value
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
 
        // Input
        FileInputFormat.addInputPath(job, inputPath);
        job.setInputFormatClass(TextInputFormat.class);
 
        // Output
        FileOutputFormat.setOutputPath(job, outputDir);
        job.setOutputFormatClass(TextOutputFormat.class);
 
        // Delete output if exists
        FileSystem hdfs = FileSystem.get(conf);
        if (hdfs.exists(outputDir))
            hdfs.delete(outputDir, true);
 
        // Execute job
        int code = job.waitForCompletion(true) ? 0 : 1;
        
        Job piaringJob = new Job(conf, "SitesPerTag");
        
        // Setup MapReduce
        piaringJob.setMapperClass(PairingMapper.class);
        piaringJob.setReducerClass(PairingReducer.class);
        piaringJob.setNumReduceTasks(1);
 
        // Specify key / value
        piaringJob.setOutputKeyClass(Text.class);
        piaringJob.setOutputValueClass(IntWritable.class);
 
        // Input
        FileInputFormat.addInputPath(piaringJob, outputDir);
        piaringJob.setInputFormatClass(TextInputFormat.class);
 
        // Output
        FileOutputFormat.setOutputPath(piaringJob, pairingOutDir);
        piaringJob.setOutputFormatClass(TextOutputFormat.class);
 
        // Execute job
        if (hdfs.exists(pairingOutDir))
            hdfs.delete(pairingOutDir, true);
        
        int code2 = piaringJob.waitForCompletion(true) ? 0 : 1;
        
        Job sortJob = new Job(conf, "SortSites");
        
        sortJob.setJarByClass(SiteSimDriver.class);
        sortJob.setOutputKeyClass(TwoSitesSimilarity.class);
        sortJob.setOutputValueClass(Text.class);
        sortJob.setMapperClass(SortMapper.class);
        sortJob.setPartitionerClass(SortPartitioner.class);
        sortJob.setGroupingComparatorClass(SortGroupingComparator.class);
        sortJob.setReducerClass(SortReducer.class);

  	  
        // Input
        FileInputFormat.addInputPath(sortJob, pairingOutDir);
        sortJob.setInputFormatClass(TextInputFormat.class);
 
        // Output
        FileOutputFormat.setOutputPath(sortJob, sortOutDir);
        sortJob.setOutputFormatClass(TextOutputFormat.class);
        
        
        // Delete output if exists
        if (hdfs.exists(sortOutDir))
            hdfs.delete(sortOutDir, true);
 
        // Execute job
        int code3 = sortJob.waitForCompletion(true) ? 0 : 1;
        System.exit(code3);
 

	}

}
