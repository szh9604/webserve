package online.zhihao;

/**
 * Created by Super_hao on 2017/4/10.
 */
public class student {
    private int id;
    private int age;
    private String name;
    private int score;

    public student(int id, int age, String name, int score) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.score = score;
    }


    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int scord) {
        this.score = scord;
    }

    public student(int age, String name, int scord) {
        this.age = age;
        this.name = name;
        this.score = scord;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {

        return id;
    }

    @Override
    public String toString() {
        return "student{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", score=" + score +
                '}';
    }

    public student() {

    }
}
