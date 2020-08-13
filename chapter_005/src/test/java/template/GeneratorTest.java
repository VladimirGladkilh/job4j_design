package template;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import java.util.Map;

public class GeneratorTest {
    @Test
    public void testValidTemplate() {
        String template = "${name} мыла ${subject}";
        Map<String, String> args = Map.of("name","Мама","subject", "раму");
        Generator gen = new TemplateGenerator();
        String result = gen.produce(template, args);
        assertThat(result, is("Мама мыла раму"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInValidTemplate() {
        String template = "${Who} мыла ${subject}";
        Map<String, String> args = Map.of("name","Мама","subject", "раму");
        Generator gen = new TemplateGenerator();
        String result = gen.produce(template, args);
        assertThat(result, is("Мама мыла раму"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInValidParamsCount() {
        String template = "${Who} мыла ${subject}";
        Map<String, String> args = Map.of("Who","Мама");
        Generator gen = new TemplateGenerator();
        String result = gen.produce(template, args);
        assertThat(result, is("Мама мыла раму"));
    }

}