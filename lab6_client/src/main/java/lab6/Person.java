package lab6;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Person {
    private String name;
    private String surname;
    private Integer age;
    private Integer phone;
    private String gender;

    public Person() {
    }
    public Person(String name, String surname, Integer age, Integer phone, String gender) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.phone = phone;
        this.gender = gender;
    }
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public Integer getAge() {
        return age;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Person{" + "name=" + name + ", surname=" + surname + ", age=" + age + '}';
    }

}