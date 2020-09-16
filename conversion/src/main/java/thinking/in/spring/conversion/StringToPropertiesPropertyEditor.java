package thinking.in.spring.conversion;

import sun.invoke.empty.Empty;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.io.StringReader;
import java.util.Map;
import java.util.Properties;

public class StringToPropertiesPropertyEditor extends PropertyEditorSupport implements PropertyEditor {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        Properties properties = new Properties();
        try {
            properties.load(new StringReader(text));
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
        setValue(properties);
    }

    @Override
    public String getAsText() {
        Properties properties = (Properties) getValue();
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            stringBuilder.append(entry.getKey().toString())
                    .append("=")
                    .append(entry.getValue().toString())
                    .append(System.getProperty("line.separator"));
        }
        return stringBuilder.toString();
    }
}
