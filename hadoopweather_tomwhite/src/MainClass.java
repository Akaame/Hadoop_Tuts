import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;

/**
 * Created by sddk on 07.11.2016.
 */
public class MainClass extends Configured implements Tool{

    public int run(String[] args) throws IOException,InterruptedException,ClassNotFoundException{
        Configuration conf = new Configuration();

        Job j = new Job(conf,"MainClass");
        j.setJarByClass(MainClass.class);

        FileInputFormat.addInputPath(j,new Path(args[0]));
        FileOutputFormat.setOutputPath(j, new Path(args[1]));

        j.setMapperClass(WeatherMapper.class);
        j.setReducerClass(MaxReducer.class);
        j.setCombinerClass(MaxReducer.class);

        j.setMapOutputKeyClass(Text.class);
        j.setMapOutputValueClass(IntWritable.class);

        return j.waitForCompletion(true) ? 1 : 0;
    }

    public static void main(String[] args) throws Exception{
        int rc = ToolRunner.run(new MainClass(),args);
        System.exit(rc);
    }
}
