package hadoop;
import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import com.alibaba.fastjson.JSON;
public class MovieMapper extends Mapper<LongWritable,Text,Movieinfo,NullWritable>{
    @Override
    protected void map(LongWritable key1,Text value1,Context context)
            throws IOException,InterruptedException{
        String josn = value1.toString();
        //Fastjson 转换 json 字符串为 Java 对象。反射
        Movieinfo movi=JSON.parseObject(josn,Movieinfo.class);
        //从movi中获取actor字段的数据，如果包含冯淬帆，则作为<k2,v2>写入context
        if(movi.getActor().contains("冯淬帆")){
            context.write(movi, NullWritable.get());
        }else{}
    }
}
