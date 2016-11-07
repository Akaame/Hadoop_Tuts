import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by sddk on 07.11.2016.
 */
public class MaxReducer extends Reducer<Text,Iterable<IntWritable>,Text,IntWritable>{
    private Text date = new Text();
    private IntWritable maxima = new IntWritable();
    public void reduce(Text key,Iterable<IntWritable> values,Context context) throws IOException,InterruptedException{
        // keys are dates
        // value is an integer array
        int max = -1*Integer.MAX_VALUE;
        for(IntWritable i : values){
            int temp = i.get();
            if(temp>max){
                max = temp;
            }
        }

        date.set(key);
        maxima.set(max);

        context.write(date,maxima);
    }
}
