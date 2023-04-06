package convert.propertyEditor;

import org.springframework.beans.propertyeditors.UUIDEditor;

public class PersonCatUUIDEditor extends UUIDEditor {

    private static final String SUFFIX = "_YourBatman";

    @Override
    public String getAsText() {
        return super.getAsText().concat(SUFFIX);
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        text = text.replace(SUFFIX, "");
        super.setAsText(text);
    }
}