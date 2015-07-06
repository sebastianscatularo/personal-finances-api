package ar.com.jss.model.domain.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.Currency;

/**
 * @author sebastianscatularo@gmail.com.
 */
public class CurrencyDeserializer extends JsonDeserializer<Currency> {
    @Override
    public Currency deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return Currency.getInstance(jsonParser.getValueAsString());
    }
}
