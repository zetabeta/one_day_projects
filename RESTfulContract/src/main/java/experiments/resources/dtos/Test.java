package experiments.resources.dtos;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.ObjectMapper;

import experiments.exceptions.JsonException;

/**
 * @author zlatka
 * 
 */
@XmlRootElement
public class Test {

    private Long id;
    private String str;
    private Integer intgr;

    public Test() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public Integer getIntgr() {
        return intgr;
    }

    public void setIntgr(Integer intgr) {
        this.intgr = intgr;
    }

    public String toJson() throws JsonException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (Exception e) {
            throw new JsonException();
        }
    }

    public static Test fromJson(String json) throws JsonException {
        ObjectMapper mapper = new ObjectMapper();
        Test test;
        try {
            test = mapper.readValue(json, Test.class);
            return test;
        } catch (Exception e) {
            throw new JsonException();
        }

    }

}
