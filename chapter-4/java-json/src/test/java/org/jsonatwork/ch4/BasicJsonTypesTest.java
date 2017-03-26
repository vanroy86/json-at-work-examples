package org.jsonatwork.ch4;

import static org.junit.Assert.*;

import java.io.*;
import java.util.*;

import org.junit.Test;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.type.*;
import com.fasterxml.jackson.databind.*;

/**
 * @author tmarrs
 *
 */
public class BasicJsonTypesTest {

	@Test
	public void serializeBasicTypes() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			Writer writer = new StringWriter();
			int age = 39;
			String fullName = new String("Larson Richard");
			List<String> tags = new ArrayList<String>(
					              Arrays.asList("json", "rest", "api", "oauth"));

			boolean registered = true;

			writer.write("age = ");
			mapper.writeValue(writer, age);
			writer.write("\nfullName = ");
			mapper.writeValue(writer, fullName);
			writer.write("\ntags = ");
			mapper.writeValue(writer, tags);
			writer.write("\nregistered = ");
			mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
			mapper.writeValue(writer, registered);
			System.out.println(writer.toString());
			assertTrue(true);
		} catch (JsonGenerationException jge) {
			jge.printStackTrace();
			fail(jge.getMessage());
		} catch (JsonMappingException jme) {
			jme.printStackTrace();
			fail(jme.getMessage());
		} catch (IOException ioe) {
			ioe.printStackTrace();
			fail(ioe.getMessage());
		}
	}

	@Test
	public void deSerializeBasicTypes() {
		try {
			String ageJson = "{ \"age\": 39 }";
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Integer> ageMap = mapper.readValue(ageJson,
					                        new TypeReference<HashMap<String,Integer>>() {});

			Integer age = ageMap.get("age");
			 
			System.out.println("age = " + age + "\n\n\n");
			assertEquals(39, age.intValue());
      assertTrue(true);
		} catch (JsonMappingException jme) {
			jme.printStackTrace();
			fail(jme.getMessage());
		} catch (IOException ioe) {
			ioe.printStackTrace();
			fail(ioe.getMessage());
		}
	}
}