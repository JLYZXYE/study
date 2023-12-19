package interfaces;

import domain.Person;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface PersonInterface {
    void save(@NotNull @Valid Person person) throws NoSuchMethodException;
}