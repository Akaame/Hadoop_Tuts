/**
 * Created by sddk on 07.11.2016.
 */

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.reduce.LongSumReducer;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;


public class WordcountJob extends Configured implements Tool{
    @Override
    public int run(String[] args) throws Exception{
        Configuration conf = new Configuration();

        Job job = new Job(conf,"WordcountJob");
        job.setJarByClass(WordcountJob.class);
        //job.setJobName(getClass().getSimpleName());

        FileInputFormat.addInputPath(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        job.setMapperClass(ProjectionMapper.class);
        job.setCombinerClass(SumReducer.class);
        job.setReducerClass(SumReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);

        return job.waitForCompletion(true) ? 0 : 1 ;
    }

    public static void main(String[] args) throws Exception{
        int rc = ToolRunner.run(new WordcountJob(),args);
        System.exit(rc);
    }
}