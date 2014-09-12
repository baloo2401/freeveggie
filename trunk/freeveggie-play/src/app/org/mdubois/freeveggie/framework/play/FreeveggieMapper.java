package org.mdubois.freeveggie.framework.play;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.Version;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.module.SimpleModule;
import org.codehaus.jackson.map.ser.std.SerializerBase;
import org.mdubois.freeveggie.framework.msg.MessageEnum;

import play.Logger;

/**
 * Freeveggie {@link ObjectMapper} implementation.
 * 
 * @author Mickael Dubois
 * 
 */
public class FreeveggieMapper extends ObjectMapper {

	/**
	 * {@link ObjectMapper} base only on field (not any getter.
	 * 
	 * If used to serialize {@link MessageEnum} it will write only the int
	 * value.
	 */
	protected static final ObjectMapper MAPPER = new ObjectMapper()
			.setVisibility(JsonMethod.FIELD, JsonAutoDetect.Visibility.ANY)
			.setVisibility(JsonMethod.GETTER, JsonAutoDetect.Visibility.NONE);

	static {
		// We had a serializer to deal with MessageEnum enum.
		SimpleModule simpleModule = new SimpleModule("SimpleModule",
				new Version(1, 0, 0, null));
		simpleModule.addSerializer(new ItemSerializer());
		MAPPER.registerModule(simpleModule);
	}

	private FreeveggieMapper() {

	}

	public static ObjectMapper getInstance() {
		return MAPPER;
	}

	public static String writeAsString(Object obj) {
		try {
			return MAPPER.writeValueAsString(obj);
		} catch (JsonGenerationException e) {
			Logger.warn("Enable to serialise object : " + obj, e);
		} catch (JsonMappingException e) {
			Logger.warn("Enable to serialise object : " + obj, e);
		} catch (IOException e) {
			Logger.warn("Enable to serialise object : " + obj, e);
		}
		return null;
	}

	/**
	 * {@link SerializerBase} for {@link MessageEnum} object
	 * 
	 * @author Mickael Dubois
	 * 
	 */
	private static class ItemSerializer extends SerializerBase<MessageEnum> {

		/**
		 * Default constructor.
		 */
		public ItemSerializer() {
			super(MessageEnum.class);
		}

		@Override
		public void serialize(MessageEnum value, JsonGenerator jgen,
				SerializerProvider arg2) throws IOException,
				JsonGenerationException {
			jgen.writeNumber(value.toInt());
		}
	}
}
