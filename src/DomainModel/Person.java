package DomainModel;

public abstract class Person {
    private String name;
    private int age;
    private String email;
    private int userid;
    private String gender;

    public Person(String name, int age, String email, int userid, String gender){
        this.name = name;
        this.age = age;
        this.email = email;
        this.userid = userid;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public int getUserid() {
        return userid;
    }

    public String getGender() {
        return gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}

