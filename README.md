# hadoop-task
A home task in hadoop

# Run instructions

To run it, run the .jar file in the 'target' folder like so:

hadoop jar yotpoIdoTask-0.0.1-SNAPSHOT.jar similarSites.SiteSimDriver 'input path' 'output path1' output path2' 'output path3'

The three output paths are for the three different stages of map reduce. The third output is where the final output will be, after secondary sort.

# Task description

Requirements:
 
in this task you should write hadoop java map reduce job/s  
The Task: calculate similar sites by count of common tags  
The input will be text file (tsv format ):  
Site1 tag1  
Site1 tag2  
Site3 tag3  
...  
The final output should be in text file ( top 10 similar sites per site ) – should be sorted by secondary sort.  
Site1 Similar1 count-of-common-tags  
Site1 Similar2 count-of-common-tags  
Site2 Similar1 count-of-common-tags  
…  
 
Data:  
Each website have 10 tags.  
There are 20M websites.  
Can be popular tags with 10k sites per one tag.  
