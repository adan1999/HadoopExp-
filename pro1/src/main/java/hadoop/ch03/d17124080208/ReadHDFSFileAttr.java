package hadoop.ch03.d17124080208;

        import com.google.inject.internal.util.$Lists;
        import com.google.inject.internal.util.$ToStringBuilder;
        import org.apache.hadoop.conf.Configuration;
        import org.apache.hadoop.fs.*;

        import java.lang.reflect.Type;
        import java.net.URI;

public class ReadHDFSFileAttr {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        //配置 NameNode 地址，具体根据你的 NameNode IP 配置
        URI uri = new URI("hdfs://192.168.30.131:8020");
        //指定用户名，获取FileSystem对象
        FileSystem fs = FileSystem.get(uri, conf, "hadoop");
        Path dfs = new Path("/17124080208/dan1.txt");
        String ss = "1724080208" ;
        byte[] a = ss.getBytes();
        fs.setXAttr(dfs,"user.id",a);
        fs.getXAttr(dfs,"user.id");
        String b = new String(fs.getXAttr(dfs,"user.id"));
        System.out.println(b);

    }
}
