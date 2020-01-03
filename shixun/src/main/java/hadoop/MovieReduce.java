package hadoop;
import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
public class MovieReduce extends Reducer<Movieinfo, NullWritable,Movieinfo,NullWritable> {
    //定义count变量，用于计数
    int count=0;
    protected void reduce(Movieinfo k3,Iterable<NullWritable> v3,Context context)
            throws IOException,InterruptedException{
        //利用一个if判断语句，若count小于5，则执行下面的代码，将数据作为<k4,v4>写入context。
            if (count<5) {
                context.write(k3, NullWritable.get());
                count++;
            }
        }
    }
