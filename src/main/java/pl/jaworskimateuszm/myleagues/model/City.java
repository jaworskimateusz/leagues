package pl.jaworskimateuszm.myleagues.model;

public class City {

  private long id;
  private String name;
  private String state;
  private String country;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }


  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  @Override
  public String toString() {
    return "City{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", state='" + state + '\'' +
            ", country='" + country + '\'' +
            '}';
  }
}
