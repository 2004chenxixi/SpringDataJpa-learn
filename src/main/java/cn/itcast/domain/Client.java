//这里的Spring Data Jpa ==  Gorm ---增删改查的命令
package cn.itcast.domain;
//要想直接调出-get-set-toString--（command+N）

import javax.persistence.*;

@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "job")
    private String job;
    @Column(name = "email")
    private String email;
    @Column(name = "number")
    private int number;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return this.job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getEmail() {
        return this.email = email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNumber() {
        return this.number = number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "domain{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", job='" + job + '\'' +
                ", email='" + email + '\'' +
                ", number=" + number +
                '}';
    }

}
