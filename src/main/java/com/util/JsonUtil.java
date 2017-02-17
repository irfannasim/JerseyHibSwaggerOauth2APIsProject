package com.util;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.logging.Level;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil {

	public static TimeZone GMT_TIME_ZONE = TimeZone.getTimeZone("GMT");

	public static <T> Object jsonToPOJO(String json, Class<T> objectType)
			throws IOException {
		try {
			Gson gson = new GsonBuilder().setDateFormat(
					AppConstants.DATE_FORMAT1_STRING).create();
			return gson.fromJson(json, objectType);
		} catch (Exception ex) {
			LogUtil.log("exception thrown in ConversionUtil.jsonToPOJO()",
					Level.SEVERE, ex);
			return null;
		}
	}

	public static String pojoToJSONwithoutFilters(Object pojo)
			throws JsonGenerationException, JsonMappingException, IOException {
		try {
			DateFormat dateFormat = new SimpleDateFormat(
					AppConstants.DATE_FORMAT1_STRING);
			dateFormat.setTimeZone(GMT_TIME_ZONE);
			ObjectMapper mapper = new ObjectMapper();
			mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
			mapper.setDateFormat(dateFormat);
			String json = mapper.writeValueAsString(pojo);
			return json;
		} catch (Exception ex) {
			LogUtil.log(
					"exception thrown in ConversionUtil.pojoToJSONwithoutFilters()",
					Level.SEVERE, ex);
			return "";
		}
	}

	public static String pojoToJSONwithoutFilters(Object pojo,
			DateFormat dateFormat) throws JsonGenerationException,
			JsonMappingException, IOException {
		try {
			dateFormat.setTimeZone(GMT_TIME_ZONE);
			ObjectMapper mapper = new ObjectMapper();
			mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
			mapper.setDateFormat(dateFormat);
			String json = mapper.writeValueAsString(pojo);
			return json;
		} catch (Exception ex) {
			LogUtil.log(
					"exception thrown in ConversionUtil.pojoToJSONwithoutFilters()",
					Level.SEVERE, ex);
			return "";
		}
	}

	public static String pojoToJSONWithFilters(Object pojo,
			FilterProvider filters) {
		try {
			DateFormat dateFormat = new SimpleDateFormat(
					AppConstants.DATE_FORMAT1_STRING);
			dateFormat.setTimeZone(GMT_TIME_ZONE);
			ObjectMapper mapper = new ObjectMapper();
			mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
			mapper.setDateFormat(dateFormat);
			String json = mapper.writer(filters).writeValueAsString(pojo);
			return json;
		} catch (Exception ex) {
			LogUtil.log(
					"exception thrown in ConversionUtil.pojoToJSONwithFilters()",
					Level.SEVERE, ex);
			return "";
		}
	}
}
