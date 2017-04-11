package online.zhihao;

/**
 * Created by Super_hao on 2017/4/10.
 */
public class adminuser {
    private String adn;
    private String adp;


    public adminuser() {

    }

    @Override
    public String toString() {
        return "adminuser{" +
                "adn='" + adn + '\'' +
                ", adp='" + adp + '\'' +
                '}';
    }

    public void setAdn(String adn) {
        this.adn = adn;
    }

    public void setAdp(String adp) {
        this.adp = adp;
    }

    public String getAdn() {

        return adn;
    }

    public String getAdp() {
        return adp;
    }

    public adminuser(String adn, String adp) {

        this.adn = adn;
        this.adp = adp;
    }
}
