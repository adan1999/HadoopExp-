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
        //利用一个for循环，若计数大于等于5，则跳出循环。否则则，将数据写入context
        for(NullWritable nullWritable: v3){
            if (count>=5) {
                break;
            }else{
                context.write(k3, NullWritable.get());
                count++;
            }
        }

    }
}