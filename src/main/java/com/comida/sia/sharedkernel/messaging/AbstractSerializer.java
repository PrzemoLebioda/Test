package com.comida.sia.sharedkernel.messaging;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.comida.sia.sharedkernel.period.InfinitePeriod;
import com.comida.sia.sharedkernel.period.MonthlyPeriod;
import com.comida.sia.sharedkernel.period.Period;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import lombok.Getter;

public abstract class AbstractSerializer {
	@Getter private Gson gson;
	
	protected AbstractSerializer(boolean isCompact) {
        this(false, isCompact);
    }
	
	protected AbstractSerializer(boolean isPretty, boolean isCompact) {
        super();

        if (isPretty && isCompact) {
            this.buildForPrettyCompact();
        } else if (isCompact) {
            this.buildForCompact();
        } else {
            this.build();
        }
    }
	
	private void buildForPrettyCompact() {
    	this.gson = new GsonBuilder()
    			.registerTypeAdapter(Date.class, new DateSerializer())
    			.registerTypeAdapter(Date.class, new DateDeserializer())
    			.registerTypeAdapter(Payload.class, new NotificationPayloadSerializer<Payload>())
    			.registerTypeAdapter(Payload.class, new NotificationPayloadDeserializer<Payload>())
    			.registerTypeAdapter(Period.class, new PeriodSerializer<Period>())
    			.registerTypeAdapter(Period.class, new PeriodDeserializer<Period>())
    			.registerTypeAdapter(SimpleDateFormat.class, new SimpleDateFormatSerializer())
    			.setPrettyPrinting()
    			.create();
    }
    
    private void buildForCompact() {
    	this.gson = new GsonBuilder()
    			.registerTypeAdapter(Date.class, new DateSerializer())
    			.registerTypeAdapter(Date.class, new DateDeserializer())
    			.registerTypeAdapter(Payload.class, new NotificationPayloadSerializer<Payload>())
    			.registerTypeAdapter(Payload.class, new NotificationPayloadDeserializer<Payload>())
    			.registerTypeAdapter(Period.class, new PeriodSerializer<Period>())
    			.registerTypeAdapter(Period.class, new PeriodDeserializer<Period>())
    			.registerTypeAdapter(SimpleDateFormat.class, new SimpleDateFormatSerializer())
    			.create();
    }
 
    private void build() {
    	this.gson = new GsonBuilder()
    			.registerTypeAdapter(Date.class, new DateSerializer())
    			.registerTypeAdapter(Date.class, new DateDeserializer())
    			.registerTypeAdapter(Payload.class, new NotificationPayloadSerializer<Payload>())
    			.registerTypeAdapter(Payload.class, new NotificationPayloadDeserializer<Payload>())
    			.registerTypeAdapter(Period.class, new PeriodSerializer<Period>())
    			.registerTypeAdapter(Period.class, new PeriodDeserializer<Period>())
    			.registerTypeAdapter(SimpleDateFormat.class, new SimpleDateFormatSerializer())
    			.serializeNulls()
    			.create();
    }
    
    private class DateSerializer implements JsonSerializer<Date> {

		@Override
		public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
			return new JsonPrimitive(Long.toString(src.getTime()));
		}
    	
    }

    private class DateDeserializer implements JsonDeserializer<Date> {

		@Override
		public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			long time = Long.parseLong(json.getAsJsonPrimitive().getAsString());
            return new Date(time);
		}
    
    }
    
    private class NotificationPayloadSerializer<T extends Payload> implements JsonSerializer<T> {

		@Override
		public JsonElement serialize(T src, Type typeOfSrc, JsonSerializationContext context) {
	        return context.serialize(src, src.getClass());
		}
    }
    
    private class NotificationPayloadDeserializer<T extends Payload> implements JsonDeserializer<T>{
    	private static final String CLASSNAME = "className";
    	
    	@SuppressWarnings("unchecked")
        public Class<T> getClassInstance(String className) {
            try {
                return (Class<T>) Class.forName(className);
            } catch (ClassNotFoundException cnfe) {
                throw new JsonParseException(cnfe.getMessage());
            }
        }

		@Override
		public T deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			JsonObject jsonObject = json.getAsJsonObject();
            JsonPrimitive prim = (JsonPrimitive) jsonObject.get(CLASSNAME);
            String className = prim.getAsString();
            Class<T> clazz = getClassInstance(className);
            
            return context.deserialize(jsonObject, clazz);
		}
    }  
    
    private class PeriodSerializer<T extends Period> implements JsonSerializer<T>{

		@Override
		public JsonElement serialize(T src, Type typeOfSrc, JsonSerializationContext context) {
			JsonObject jsonObject = new JsonObject();
			if (src instanceof InfinitePeriod) {
				jsonObject.add("type", new JsonPrimitive("INFINITE"));
				jsonObject.add("periodValue", new JsonPrimitive(src.getFormatted()));
			}
			
			if(src instanceof MonthlyPeriod) {
				jsonObject.add("type", new JsonPrimitive("MONTHLY"));
				jsonObject.add("periodValue", new JsonPrimitive(src.getFormatted()));
			}
			
			return jsonObject;
		}
    }
    
    private class PeriodDeserializer<T extends Period> implements JsonDeserializer<T>{

		@SuppressWarnings("unchecked")
		@Override
		public T deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			
			JsonObject jsonObject = json.getAsJsonObject();
            JsonPrimitive type = (JsonPrimitive) jsonObject.get("type");
            JsonPrimitive value = (JsonPrimitive) jsonObject.get("periodValue");
            
            if(type.getAsString().equals("INFINITE")) {
            	return (T) new InfinitePeriod();
            }
            
            if(type.getAsString().equals("MONTHLY")) {
            	return (T) new MonthlyPeriod(value.getAsString());
            }
            
			return null;
		}
    	
    }
    
    private class SimpleDateFormatSerializer implements JsonSerializer<SimpleDateFormat>{

		@Override
		public JsonElement serialize(SimpleDateFormat src, Type typeOfSrc, JsonSerializationContext context) {
			// TODO Auto-generated method stub
			return null;
		}
    	
    }
}
