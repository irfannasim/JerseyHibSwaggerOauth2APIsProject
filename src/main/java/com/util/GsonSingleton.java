package com.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonSingleton {

	private static Gson INSTANCE = null;
	private static GsonBuilder BUILDER_INSTANCE = null;

	private GsonSingleton() {
	}

	public static Gson getInstance() {

		if (INSTANCE == null) {
			INSTANCE = new Gson();
		}

		return INSTANCE;
	}

	public static GsonBuilder getBuilderInstance() {

		if (BUILDER_INSTANCE == null) {
			BUILDER_INSTANCE = new GsonBuilder();
		}

		return BUILDER_INSTANCE;
	}
}