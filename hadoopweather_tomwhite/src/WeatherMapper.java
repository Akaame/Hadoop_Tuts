
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import javax.print.DocFlavor;
import java.io.IOException;

/**
 * Created by sddk on 07.11.2016.
 */
public class WeatherMapper extends Mapper<LongWritable,Text,Text,IntWritable> {
    private Text date = new Text();
    private IntWritable temp = new IntWritable();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException,InterruptedException{
        String strkeyout = value.toString().substring(15,19); // parse date
        String strvalueout = value.toString().substring(87,92); // parse temp
        String quality = value.toString().substring(92,93);

        int sign = strvalueout.charAt(0) != '-' ? -1 :1;
        int val = sign * Integer.parseInt(strvalueout.substring(1,5)); // further parse temp

        date.set(strkeyout);
        temp.set(val); // set keys values

        // ignore if missing or unqualified(regex)
        if(val!=9999 && quality.matches("[01459]")){
            context.write(date,temp);
        }
    }
}
