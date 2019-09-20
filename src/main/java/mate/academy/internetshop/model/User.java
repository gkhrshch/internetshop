package mate.academy.internetshop.model;

import java.util.ArrayList;
import java.util.List;
import mate.academy.internetshop.IdGenerator;

public class User {
    private final Long id;
    private String name;
    private String surname;
    private String login;
    private String password;
    //private Bucket bucket;
    //Uncomment private bucket after creating healthy bucket service in webapp
    private List<Order> orders;

    public User(String name) {
        this.id = IdGenerator.getUserId();
        this.name = name;
        this.orders = new ArrayList<>();
        //this.bucket = new Bucket(this.id);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /*public Bucket getBucket() {
        return bucket;
    }

    public void setBucket(Bucket bucket) {
        this.bucket = bucket;
    }*/

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", name=" + name
                + ", orders=" + orders
                //+ ", bucket=" + this.bucket.getItems()
                + '}';
    }
}
