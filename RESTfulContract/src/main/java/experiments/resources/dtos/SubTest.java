package experiments.resources.dtos;

/**
 * Test DTO for dependent REST resource.
 * 
 * @author zlatka
 * 
 */
public class SubTest {

    private Long id;
    private String content;
    private Long testId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }

}
