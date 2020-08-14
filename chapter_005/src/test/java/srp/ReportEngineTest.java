package srp;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;

import java.util.*;

public class ReportEngineTest {

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        ReportStore engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary; ")
                .append(System.lineSeparator())
                .append(worker.getName()).append("; ")
                .append(worker.getHired()).append("; ")
                .append(worker.getFired()).append("; ")
                .append(worker.getSalary()).append("; ");
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenHTMLReport() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        ReportStore engine = new ReportHTML(store);
        StringBuilder expect = new StringBuilder()
                .append("<html><head>HTML report</head><body>")
                .append("<tr><td>Name</td><td>Hired</td><td>Fired</td><td>Salary</td></tr>")
                .append("<tr><td>").append(worker.getName()).append("</td>")
                .append("<td>").append(worker.getFired()).append("</td>")
                .append("<td>").append(worker.getHired()).append("</td>")
                .append("<td>").append(worker.getSalary()).append("</td></tr>")
                .append("</body></html>");
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }


    @Test
    public void whenChangeSalary() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        ReportStore engine = new ReportHTML(store);
        LinkedHashMap<String, String> titles = new LinkedHashMap<>();
        titles.put("Name","Имя");
        titles.put("Fired", "Принят");
        titles.put("Hired", "Уволен");
        titles.put("Salary", "Зарплата");
        StringBuilder expect = new StringBuilder()
                .append("<html><head>HTML report</head><body>")
                .append("<tr><td>Имя</td><td>Принят</td><td>Уволен</td><td>Зарплата</td></tr>")
                .append("<tr><td>").append(worker.getName()).append("</td>")
                .append("<td>").append(worker.getFired()).append("</td>")
                .append("<td>").append(worker.getHired()).append("</td>")
                .append("<td>").append(worker.getSalary()).append("</td></tr>")
                .append("</body></html>");
        assertThat(engine.generate(em -> true, titles), is(expect.toString()));
    }

    @Test
    public void whenOrderBySalary() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Egor", now, now, 200);
        Employee worker3 = new Employee("Andrey", now, now, 11000);
        store.add(worker);
        store.add(worker2);
        store.add(worker3);
        Comparator<Employee> comparator = (o1, o2) -> Double.compare(o2.getSalary(), o1.getSalary());
        ReportStore engine = new ReportHTML(store);
        LinkedHashMap<String, String> titles = new LinkedHashMap<>();
        titles.put("Name","Имя");
        titles.put("Salary", "Зарплата");
        StringBuilder expect = new StringBuilder()
                .append("<html><head>HTML report</head><body>")
                .append("<tr><td>Имя</td><td>Зарплата</td></tr>")
                .append("<tr><td>").append(worker3.getName()).append("</td>")
                .append("<td>").append(worker3.getSalary()).append("</td></tr>")
                .append("<tr><td>").append(worker2.getName()).append("</td>")
                .append("<td>").append(worker2.getSalary()).append("</td></tr>")
                .append("<tr><td>").append(worker.getName()).append("</td>")
                .append("<td>").append(worker.getSalary()).append("</td></tr>")
                .append("</body></html>");
        assertThat(engine.generate(em -> true, titles, comparator), is(expect.toString()));
    }
}