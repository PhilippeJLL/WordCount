import java.io.*;

public class WordCount {
	public static int words = 1;
	public static int lines = 1;
	public static int chars = 0;
	public static boolean cf, wf, lf;
	public static boolean wrf = false;
	public static PrintWriter output = null;
	public static void wc(InputStream f) throws IOException { 
		int c = 0;
		boolean lastNotWhite = false;
		String whiteSpace = " \t\n\r";
		while ((c = f.read()) != -1) {
			chars++;
			if (c == '\n') {
				lines++;
			}
			if (whiteSpace.indexOf(c) != -1) {
				if (lastNotWhite) {
					words++;
				}
				lastNotWhite = false;
			}
			else lastNotWhite = true;
		}
	}
}