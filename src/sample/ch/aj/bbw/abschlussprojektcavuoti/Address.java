package sample.ch.aj.bbw.abschlussprojektcavuoti;

public class Address {

    private int id;
    private String name;
    private String street;
    private int zip;
    private int age;

    public Address(int id, String name, String street, int zip, int age) {
        this.id = id;
        this.name = name;
        this.street = street;
        this.zip = zip;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
