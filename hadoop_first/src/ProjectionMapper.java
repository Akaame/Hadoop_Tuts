/**
 * Created by sddk on 07.11.2016.
 */

import org.apache.hadoop.io.LongWritablr;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.mapreduce.lib.input.FileInputFormat;
import org.apache.mapreduce.lib.output.FileOutputFormat;
import org.apache.mapreduce.lib.reduce.LongSumReducer;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;



import java.io.IOException;

public class ProjectionMapper extends Mapper<LongWritable,Test,Text,LongWritable>{
    private Text word = new Text();
    private LongWritable count = new LongWritable();

    @override
    protected void map(LongWritable key,Text value,Context ctx) throws IOException,InterruptedException{
        String[] split = value.toString().split("\t+");
        word.set(string[0]);
        if(split.length > 2){
            try{
                count.set(Long.parseLong(split[2]));
                ctx.write(word,count);
            } catch (NumberFormatException e){

            }
        }
    }

}
