package monitoring.handler.indicators

/**
 * Created by Mariya on 27.03.2015.
 */
class GroovyTestBean {

    private String name;
    private Integer number;
    private Date date;

    public GroovyTestBean(String name, Integer number, Date date) {
        this.name = name
        this.number = number
        this.date = date
    }

    public String getName() {
        return name
    }

    public void setName(String name) {
        this.name = name
    }

    public Integer getNumber() {
        return number
    }

    public void setNumber(Integer number) {
        this.number = number
    }

    public Date getDate() {
        return date
    }

    public  void setDate(Date date) {
        this.date = date
    }
}
