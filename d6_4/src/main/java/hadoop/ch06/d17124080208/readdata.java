package hadoop.ch06.d17124080208;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.mapreduce.*;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import java.io.IOException;
public class readdata{
    public static class ddMapper
            extends TableMapper<Text, IntWritable> {
        public void map(ImmutableBytesWritable key, Result value, Context context
        ) throws IOException, InterruptedException {
            String data = Bytes.toString(value.getValue(Bytes.toBytes("address"), Bytes.toBytes("city")));
            String [] words = data.split(" ");
            for (String s:words){
                context.write(new Text(s) , new IntWritable(1));
            }
        }
    }
    public static class wenReducer
            extends TableReducer<Text,IntWritable,ImmutableBytesWritable> {
        private IntWritable result = new IntWritable();
        public void reduce(Text k, Iterable<IntWritable> v,
                           Context context) throws IOException, InterruptedException {
            int sum = 0;
            for (IntWritable i : v) {
                sum += i.get();
            }
            Put put = new Put(Bytes.toBytes(k.toString()));
            put.addColumn(Bytes.toBytes("content"),Bytes.toBytes("count"),Bytes.toBytes(String.valueOf(sum)));
            context.write(new ImmutableBytesWritable(Bytes.toBytes(k.toString())), put);
        }
    }


    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        conf.set("hbase.zookeeper.quorum", "node1");
        conf.set("hbase.rootdir", "hdfs://node1:8020/hbase");
        conf.set("hbase.cluster.distributed", "true");
        Job job = Job.getInstance(conf);
        job.setJarByClass(readdata.class);
        Scan scan = new Scan();
        scan.addColumn(Bytes.toBytes("address"),Bytes.toBytes("city"));
        TableMapReduceUtil.initTableMapperJob("member17124080208" ,scan , ddMapper.class,
                Text.class , IntWritable.class ,job);
        TableMapReduceUtil.initTableReducerJob("result17124080208" , wenReducer.class,job);
        job.waitForCompletion(true);
    }
}





