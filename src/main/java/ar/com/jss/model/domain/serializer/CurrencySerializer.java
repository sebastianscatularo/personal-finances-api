package ar.com.jss.model.domain.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Currency;

/**
 * @author sebastianscatularo@gmail.com.
 */
public class CurrencySerializer extends JsonSerializer<Currency> {
    @Override
    public void serialize(Currency currency, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeString(currency.toString());
    }
}
