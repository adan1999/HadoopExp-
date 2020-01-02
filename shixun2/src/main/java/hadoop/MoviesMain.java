package hadoop;
import com.alibaba.fastjson.JSON;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
public class MoviesMain {
    public static void main(String[] args)throws Exception{
        //创建一个使用默认大小输入缓冲区的缓冲字符输出流,读取文件系统，可以读取一行数据
        BufferedReader read =new BufferedReader(new FileReader("C:/Users/ASUS/Desktop/Hadoop部署/Film.json"));
        //创建一个空的CSV文件
        FileWriter file =new FileWriter(new File("Film.csv"));
        String line=null;
        //读取输入文件的一行数据，并赋值给 line，判断line是否为空，若不为空，则执行下面的代码
        while((line =read.readLine())!=null){
            //Fastjson 把每行的json 字符串转换为对象。
            Movieinfo movi= JSON.parseObject(line,Movieinfo.class);
            //获取包含“冯淬帆”的演员列表
            if(movi.getActor().contains("冯淬帆")){
                //取出演员名单，并分割
                String[] actors =movi.getActor().split(",");
                //遍历actors数组，将一个一个演员写出film中
                for(String ac:actors){
                    if(!ac.equals("冯淬帆")){
                        file.append(movi.getYear()+","+movi.getTitle()+","
                        +ac+","+movi.getStar()+","+movi.getFilm_page()+"\n");
                    }
                }
            }
        }
        //将缓存区关闭，让所有数据都存入内存处理
        file.close();
        read.close();
    }
}
