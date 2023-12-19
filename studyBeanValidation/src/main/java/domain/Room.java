package domain;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

public class Room {

    public String name;
    public boolean finished;

    @NotNull
    public String getName() {
        return name;
    }

    @AssertTrue
    public boolean isFinished() {
        return finished;
    }
}