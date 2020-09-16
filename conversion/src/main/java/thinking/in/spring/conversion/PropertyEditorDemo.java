package thinking.in.spring.conversion;

public class PropertyEditorDemo {
    public static void main(String[] args) {
        //模拟Spring Framework 工作
        String text = "name = ruby";
        StringToPropertiesPropertyEditor propertyEditor = new StringToPropertiesPropertyEditor();
        propertyEditor.setAsText(text);
        System.out.println(propertyEditor.getValue());
        System.out.println(propertyEditor.getAsText());


    }
}
