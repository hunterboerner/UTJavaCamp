package me.hunterboerner.yaml;

import static java.lang.String.format;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public final class Configuration {

	private static String debug;

	public String getDebug() {
		return debug;

	}

	public void setDebug() {

	}

	@Override
	public String toString() {
		return new StringBuilder().append(format("Debug: %s\n", debug))
				.toString();
	}

	public static void yamlDump() throws IOException {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("debug", "false");
		Yaml yaml = new Yaml();
		String output = yaml.dump(data);
		System.out.println(output);
		File yamlFile = new File("../config.yaml");

		System.out.println(yamlFile.getAbsolutePath());
		yamlFile.createNewFile();

		FileOutputStream outputFile = new FileOutputStream(yamlFile, false);
		outputFile.write(output.getBytes());
		outputFile.close();
		System.out.println("test");

	}

	public static void loadFromStream() throws IOException {
		InputStream input = null;
		File yamlFile = new File("../config.yaml");
		if (!yamlFile.exists()) {
			yamlDump();
		}
		try {
			input = new FileInputStream(new File("../config.yaml"));
			Yaml yaml = new Yaml();
			// Object data = yaml.load(input);

			@SuppressWarnings("unchecked")
			Map<String, Object> data = (Map<String, Object>) yaml.load(input);
			//String[] dataSplit = data.get(key));
			//System.out.println(dataSplit);
			debug = data.toString();
			System.out.println(debug.lastIndexOf("debug"));
			
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
}
