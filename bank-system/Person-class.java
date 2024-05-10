public class Person {
    String name;
    private   String address,username,password, id;

    public Person(String name, String address, String username, String password, String id) {
        this.name = name;
        setAddress(address);
        setUsername(username);
        setPassword(password);
        setId(id);
    }

    public String address() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String username() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String password() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String id() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String toString() {
        return "Name: " + name + "\n" +
                "Address: " + address() + "\n" +
                "ID: " + id();
    }
}
