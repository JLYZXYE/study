package domain;

import lombok.Data;

import javax.validation.Valid;
import java.io.Serializable;

@Data
public final class Result<T> implements Serializable {

    private boolean success = true;

    @Valid
    private T data = null;
    
    private String errCode;
    private String errMsg;
}