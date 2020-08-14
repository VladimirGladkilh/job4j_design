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
        String expect = "Name; Hired; Fired; Salary; " +
                System.lineSeparator() +
                worker.getName() + "; " +
                worker.getHired() + "; " +
                worker.getFired() + "; " +
                worker.getSalary() + "; ";
        assertThat(engine.generate(em -> true), is(expect));
    }

    @Test
    public void whenHTMLReport() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        ReportStore engine = new ReportHTML(store);
        String expect = "<html><head>HTML report</head><body>" +
                "<tr><td>Name</td><td>Hired</td><td>Fired</td><td>Salary</td></tr>" +
                "<tr><td>" + worker.getName() + "</td>" +
                "<td>" + worker.getFired() + "</td>" +
                "<td>" + worker.getHired() + "</td>" +
                "<td>" + worker.getSalary() + "</td></tr>" +
                "</body></html>";
        assertThat(engine.generate(em -> true), is(expect));
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
        String expect = "<html><head>HTML report</head><body>" +
                "<tr><td>Имя</td><td>Принят</td><td>Уволен</td><td>Зарплата</td></tr>" +
                "<tr><td>" + worker.getName() + "</td>" +
                "<td>" + worker.getFired() + "</td>" +
                "<td>" + worker.getHired() + "</td>" +
                "<td>" + worker.getSalary() + "</td></tr>" +
                "</body></html>";
        assertThat(engine.generate(em -> true, titles), is(expect));
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
        String expect = "<html><head>HTML report</head><body>" +
                "<tr><td>Имя</td><td>Зарплата</td></tr>" +
                "<tr><td>" + worker3.getName() + "</td>" +
                "<td>" + worker3.getSalary() + "</td></tr>" +
                "<tr><td>" + worker2.getName() + "</td>" +
                "<td>" + worker2.getSalary() + "</td></tr>" +
                "<tr><td>" + worker.getName() + "</td>" +
                "<td>" + worker.getSalary() + "</td></tr>" +
                "</body></html>";
        assertThat(engine.generate(em -> true, titles, comparator), is(expect));
    }

    @Test
    public void whenXmlTest() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Egor", now, now, 200);
        Employee worker3 = new Employee("Andrey", now, now, 11000);
        store.add(worker);
        store.add(worker2);
        store.add(worker3);
        Comparator<Employee> comparator = (o1, o2) -> Double.compare(o2.getSalary(), o1.getSalary());
        ReportStore engine = new ReportXML(store);
        LinkedHashMap<String, String> titles = new LinkedHashMap<>();
        titles.put("Name","Имя");
        titles.put("Salary", "Зарплата");
        String expect = "<?xml version=\"1.0\" encoding=\"WINDOWS-1251\"?><table>" +
                "<tr><td>Имя</td><td>Зарплата</td></tr>" +
                "<tr><td>" + worker3.getName() + "</td>" +
                "<td>" + worker3.getSalary() + "</td></tr>" +
                "<tr><td>" + worker2.getName() + "</td>" +
                "<td>" + worker2.getSalary() + "</td></tr>" +
                "<tr><td>" + worker.getName() + "</td>" +
                "<td>" + worker.getSalary() + "</td></tr>" +
                "</table>";
        assertThat(engine.generate(em -> true, titles, comparator), is(expect));
    }

    @Test
    public void whenJSOMTest() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Egor", now, now, 200);
        Employee worker3 = new Employee("Andrey", now, now, 11000);
        store.add(worker);
        store.add(worker2);
        store.add(worker3);
        ReportStore engine = new ReportJSON(store);
        LinkedHashMap<String, String> titles = new LinkedHashMap<>();
        titles.put("Name","Имя");
        titles.put("Salary", "Зарплата");
        String expect = "{\"table\":{\"tr\":[{\"td\":[\"Имя\",\"Зарплата\"]}" +
                ",{\"td\":[\"" + worker.getName() + "\"," + (int) worker.getSalary() + "]}" +
                ",{\"td\":[\"" + worker2.getName() + "\"," + (int) worker2.getSalary() + "]}" +
                ",{\"td\":[\"" + worker3.getName() + "\"," + (int) worker3.getSalary() + "]}" +
                "]}}";
        assertThat(engine.generate(em -> true, titles), is(expect));
    }
}