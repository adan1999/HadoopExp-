package hadoop;
import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.VIntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
public class MovieMain{
    public static void main(String[] args)throws Exception{
        //创建一个job
        Job job = Job.getInstance(new Configuration());
        job.setJarByClass(MovieMain.class);
    //这里访问的是 HDFS 上jar包路径
        job.addFileToClassPath(new Path("/fastjson-1.2.62.jar"));
    //指定job的mapper和输出的类型 k2，v2
        job.setMapperClass(MovieMapper.class);
        job.setMapOutputKeyClass(Movieinfo.class);
        job.setMapOutputValueClass(NullWritable.class);
        //指定job的reduce和输出的类型 k4，v4
        job.setReducerClass(MovieReduce.class);
        job.setOutputKeyClass(Movieinfo.class);
        job.setOutputValueClass(NullWritable.class);

        //指定job的输入和输出
        FileInputFormat.setInputPaths(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));
        //执行job
        job.waitForCompletion(true);
    }
}