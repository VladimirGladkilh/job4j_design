package srp;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ReportEngine implements ReportStore {
    private final Store store;

    public ReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        return generate(filter, new LinkedHashMap<>());
    }

    /**
     * Создает отчет c ограничениями по полям
     * @param filter ограничение выборки
     * @param titles карта соотношением Имя поля - заголовок
     * @return текстовка отчета
     */
    @Override
    public String generate(Predicate<Employee> filter, LinkedHashMap<String, String> titles) {
        StringBuilder text = new StringBuilder();
        text.append(createTableHeader(titles));
        for (Employee employee : store.findBy(filter)) {
            text.append(System.lineSeparator());
            text.append(createTableRow(employee, titles));
        }
        return text.toString();
    }


    public String generate(Predicate<Employee> filter, LinkedHashMap<String, String> titles, Comparator<Employee> comparator) {
        StringBuilder text = new StringBuilder();
        text.append(createTableHeader(titles));
        for (Employee employee : store.findBy(filter).stream().sorted(comparator).collect(Collectors.toList())) {
            text.append(System.lineSeparator());
            text.append(createTableRow(employee, titles));
        }
        return text.toString();
    }

    /**
     * Создаем шапку отчета
     * @param titles если карта пустая то вернем апку со всеми столбцами
     * @return строка заголовка
     */
    public String createTableHeader(LinkedHashMap<String, String> titles) {
        StringBuilder text = new StringBuilder();
        if (titles.size() > 0) {
            for (String titl : titles.values()) {
                text.append(titl).append("; ");
            }
        } else {
            text.append("Name; Hired; Fired; Salary; ");
        }
        return text.toString();
    }

    /**
     * Получаем из работника необходимые поля (или все если карта пустая)
     * ПРи работе через SQL можно было бы просто получить только нужные поля
     * в нужном порядке и сразу выводить их в сетку, так было бы быстрее
     * чем перебирать реквизиты каждого работника
     * @param employee работник значения которого получаем
     * @param titles карта с заголовками и полями
     * @return строка со значениями
     */
    public String createTableRow(Employee employee, LinkedHashMap<String, String> titles) {
        StringBuilder text = new StringBuilder();
        if (titles.size() > 0) {
            for (String titl : titles.keySet()) {
                switch (titl) {
                    case "Name" : text.append(employee.getName()).append("; "); break;
                    case "Hired" : text.append(employee.getHired()).append("; "); break;
                    case "Fired" : text.append(employee.getFired()).append("; "); break;
                    case "Salary" : text.append(employee.getSalary()).append("; "); break;
                }
            }
        } else {
            text.append(employee.getName()).append("; ")
                    .append(employee.getHired()).append("; ")
                    .append(employee.getFired()).append("; ")
                    .append(employee.getSalary()).append("; ");
        }
        return text.toString();
    }

}