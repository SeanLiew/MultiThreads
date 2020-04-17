package waitcomsume;

/**
 * desc:
 * author: liuxiaozheng
 * time: 2020/4/17  10:02
 **/
public class Food {
    /**
     *  名称
     */
    private String name;


    /**
     *  生产者
     */
    private String producer;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }
}
