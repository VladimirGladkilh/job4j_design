package srp;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.function.Predicate;
import org.json.JSONObject;
import org.json.XML;

public class ReportJSON extends ReportXML implements ReportStore {
    private final Store store;


    public ReportJSON(Store store) {
        super(store);
        this.store = store;
    }


    @Override
    public String generate(Predicate<Employee> filter) {
        return generate(filter, new LinkedHashMap<>());
    }

    @Override
    public String generate(Predicate<Employee> filter, LinkedHashMap<String, String> titles) {
        String mainData = super.generate(filter, titles);
        return convertXmlToJSON(mainData);
    }

    @Override
    public String generate(Predicate<Employee> filter, LinkedHashMap<String, String> titles, Comparator<Employee> comparator) {
        String mainData = super.generate(filter, titles, comparator);
        return convertXmlToJSON(mainData);
    }

    private String convertXmlToJSON(String mainData) {
        JSONObject xmlJSONObj = XML.toJSONObject(mainData);
        return xmlJSONObj.toString();
    }

}
