package valueExtractor;

import domain.Result;

import javax.validation.valueextraction.ExtractedValue;
import javax.validation.valueextraction.ValueExtractor;

public class ResultValueExtractor implements ValueExtractor<Result<@ExtractedValue ?>> {
    
    @Override
    public void extractValues(Result<?> originalValue, ValueReceiver receiver) {
        receiver.value(null, originalValue.getData());
    }
}