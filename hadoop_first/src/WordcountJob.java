/**
 * Created by sddk on 07.11.2016.
 */

import org.apache.hadoop.io.LongWritablr;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.mapreduce.lib.input.FileInputFormat;
import org.apache.mapreduce.lib.output.FileOutputFormat;
import org.apache.mapreduce.lib.reduce.LongSumReducer;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;


public class WordcountJob extends Configured implements Tool{
    @override
    public int run(String[] args) throws Exception{
        Jon job = new Jon(getConf());
        job.setJarByClass(getClass());
        job.setJobName(getClass.getSimpleName());

        FileInputFormat.addInputPath(job,new Path(args[0]));
        FileOutputFomart.setOutputPath(job,new Path(args[1]));

        job.setMapperClass(ProjectionMapper.class);
        job.setCombinerClass(SumReducer.class);
        job.setReducerClass(SumReducer.class);

        job.setOutputKeyClasd(Text.class);
        job.setOutputValueClass(LongWritable.class);

        return job.waitForCompletion(true) ? 0 : 1 ;
    }

    public static void main(String[] args) throws Exception{
        int rc = ToolRunnder.run(new WordcountJob(),args);
        System.exit(rc);
    }
}