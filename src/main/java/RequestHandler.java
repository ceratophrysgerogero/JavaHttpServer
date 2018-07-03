package main.java;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import main.java.enums.Status;

public class RequestHandler {
	private static Path BAD_REQUEST_HTML_PATH	 = Paths.get("public/400.html");
	private static Path FORBIDDEN_HTML_PATH  	 = Paths.get("public/403.html");
	private static Path NOT_FOUND_HTMLPATH	 = Paths.get("public/404.html");

	private static String HTML_MIME = "text/html;charset=utf8";
	private static MimeDetector mimeDetector = new MimeDetector("mimes.properties");

	public Response handleRequest(Request request) throws IOException {

		if(request == null) {
			return new Response(Status.BAD_REQUEST, HTML_MIME, Files.readAllBytes(BAD_REQUEST_HTML_PATH));
		}

		Path resourcePath = Paths.get("public",request.path).normalize();

		if (!resourcePath.startsWith("public/")){// ディレクトリトラバーサル
			return new Response(Status.FORBIDDEN, HTML_MIME, Files.readAllBytes(FORBIDDEN_HTML_PATH));
		}
		if (Files.isReadable(resourcePath)) {
			String mime = mimeDetector.getMime(resourcePath);

		}

	}


}
