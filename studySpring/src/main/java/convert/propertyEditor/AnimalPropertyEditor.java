package convert.propertyEditor;

import java.beans.PropertyEditorSupport;

public class AnimalPropertyEditor extends PropertyEditorSupport {

    @Override
    public String getAsText() {
        return null;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
    }
}