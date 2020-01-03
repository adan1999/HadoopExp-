package hadoop;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
//定义Movieinfo类实现序列化接口
public class Movieinfo implements WritableComparable<Movieinfo> {
    //由以上定义变量
    private String title;
    private int year;
    private String type;
    private float star;
    private String director;
    private String actor;
    private String time;
    private String film_page;
    @Override
    public String toString() {
		return "信息：{title=" + title + ", year=" + year + ", type=" +
                type + ", star=" + star + ",film_page=" + film_page+ "}";
    }
    public  int compareTo(Movieinfo o){
        if(this.star > o.getStar()) {
            return -1;
        }else if(this.star < o.getStar()){
            return 1;
        }
        if (this.year>=o.getYear()){
            return -1;
        }else{
            return 1;
        }
    }
    //序列化方法：将java对象转化为可跨机器传输数据流（二进制串/字节）的一种技术
    public void write(DataOutput out) throws IOException {
        out.writeUTF(this.title);
        out.writeInt(this.year);
        out.writeUTF(this.type);
        out.writeFloat(this.star);
        out.writeUTF(this.director);
        out.writeUTF(this.actor);
        out.writeUTF(this.time);
        out.writeUTF(this.film_page);

    }
    //反序列化方法：将可跨机器传输数据流（二进制串）转化为java对象的一种技术
    public void readFields(DataInput in) throws IOException {
        this.title = in.readUTF();
        this.year = in.readInt();
        this.type = in.readUTF();
        this.star = in.readFloat();
        this.director = in.readUTF();
        this.actor = in.readUTF();
        this.time = in.readUTF();
        this.film_page = in.readUTF();
    }
    //其他类通过set/get方法操作变量：Source-->Generator Getters and Setters
    //定义了一个私有的成员变量的时候，如果需要访问或者获取这个变量的时候，就可以编写set或者get方法去调用。
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year= year;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public float getStar() {
        return star;
    }
    public void setStar(float star) {
        this.star = star;
    }
    public String getDirector() {
        return director;
    }
    public void setDirector(String director) {
        this.director = director;
    }
    public String getActor() {
        return actor;
    }
    public void setActor(String actor) {
        this.actor = actor;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getFilm_page() {
        return film_page;
    }
    public void setFilm_page(String film_page) {
        this.film_page = film_page;
    }
}

