package lab6;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Person {
    private String name;
    private String surname;
    private int age;
    private int phone;
    private String gender;

    public Person() {
    }
    public Person(String name, String surname, int age, int phone, String gender) {
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
    public int getAge() {
        return age;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
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