package io.nyaperos.libs.cli.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.IOUtils;

import lombok.SneakyThrows;

public class ResourceUtils {

	public static final String RESOURCE = "resource://";

	@SneakyThrows
	public static InputStream getInputStream(String path) {
		InputStream reader = null;
		if (isResource(path)) {
			path = path.replace(RESOURCE, "");
			reader = ResourceUtils.class.getClassLoader().getResourceAsStream(path);
		} else {
			reader = new FileInputStream(path);
		}
		return reader;
	}

	@SneakyThrows
	public static InputStream getInputStream(File file) {
		return new FileInputStream(file);
	}

	public static OutputStream getOutputStream(String path) {
		return getOutputStream(path, false);
	}

	public static OutputStream getOutputStream(File file) {
		return getOutputStream(file, false);
	}

	@SneakyThrows
	public static OutputStream getOutputStream(String path, Boolean append) {
		return new FileOutputStream(path, append);
	}

	@SneakyThrows
	public static OutputStream getOutputStream(File file, Boolean append) {
		return new FileOutputStream(file, append);
	}

	public static File getFile(String path) {
		File file = null;
		if (!isResource(path)) {
			file = new File(path);
		}
		return file;
	}

	public static boolean isResource(String path) {
		return path.startsWith(RESOURCE);
	}

	public static void createParents(File file) {
		File parent = file.getParentFile();
		if (!parent.exists() && !parent.mkdirs()) {
			throw new IllegalStateException("Couldn't create dir: " + parent);
		}
	}

	public static void extract(InputStream inputStream, String filePath) {
		File resultFile = getFile(filePath);
		copy(inputStream, resultFile);
	}

	@SneakyThrows
	private static void copy(InputStream inputStream, File destinationFile) {
		if (!destinationFile.exists())
			destinationFile.createNewFile();
		Path destinationFilePath = Paths.get(destinationFile.getAbsolutePath());
		Files.write(destinationFilePath, IOUtils.toByteArray(inputStream));
	}
}
