package domain;

import lombok.Data;
import org.hibernate.validator.constraints.ScriptAssert;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

//@ScriptAssert(lang = "javascript", alias = "_", script = "_.maxStuNum >= _.studentNames.length")
@Data
public class RoomNum {
    @Positive
    private int maxStuNum;
    @NotNull
    private List<String> studentNames;
}