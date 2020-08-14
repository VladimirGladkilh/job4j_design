package srp;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public interface ReportStore {
    String generate(Predicate<Employee> filter);
    String generate(Predicate<Employee> filter, LinkedHashMap<String, String> titles);
    String generate(Predicate<Employee> filter, LinkedHashMap<String, String> titles, Comparator<Employee> comparator);
    String createTableHeader(LinkedHashMap<String, String> titles) ;
    String createTableRow(Employee employee, LinkedHashMap<String, String> titles);
}
