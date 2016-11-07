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
import java.io.IOException;
public class SumReducer<T> extends Reducer<T,LongWritable,T,LongWritable> throws IOException,InterruptedException{

private LongWritable count = new LongWritable();

public void reduce(T key,Iterable<LongWritable> values,Context ctx) {
        long sum = 0;
        for (LongWritable l:values){
        sum+=l.get();
        }
        result.set(sum);
        ctx.write(key,count);
        }
        }