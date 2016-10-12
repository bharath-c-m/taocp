package art.of.programming.recursion;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RecursiveFileListing {

	static Logger l = LoggerFactory.getLogger(RecursiveFileListing.class);

	public static void main(String[] args) {
		if (args != null && args.length > 0) {
			String fileLocation = args[0];
			File f = new File(fileLocation);
			if (f.exists()) {
				l.info("File listing for " + f.getAbsolutePath());
				listFiles(f, 0);
			} else {
				l.info("Unable to resolve location {}", fileLocation);
			}
		} else {
			l.info("Maven usage -> mvn exec:java  -Dexec.mainClass=art.of.programming.recursion.RecursiveFileListing -Dexec.arguments=<file_location>");
			l.info("simple usage java RecursiveFileListing <file_location>");
		}
	}

	public static void listFiles(File f, int identation) {
		StringBuilder sb = new StringBuilder();
		if (f.isDirectory()) {
			for (int i = 0; i < identation; i++) {
				sb.append("..\\");
			}
			sb.append(f.getName());
			System.out.println(sb.toString());
			File[] childs = f.listFiles();
			for (File cf : childs) {
				listFiles(cf, identation + 1);
			}
		} else {
			for (int i = 0; i < identation; i++) {
				sb.append("..\\");
			}
			sb.append(f.getName());
			System.out.println(sb.toString());
		}
	}
}
