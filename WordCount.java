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
	public static void traverseFolder(String path, String outpath) throws IOException{
        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files.length == 0) {
                System.out.println("the folder is empty.");
                return;
            }
            else {
            		wrf = true;
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                    		writer(outpath, file2.getAbsolutePath() + "\n");
                        traverseFolder(file2.getAbsolutePath(), outpath);
                    } 
                    else {
                    		writer(outpath, file2.getAbsolutePath() + "\n");
                    		infile(file2.getAbsolutePath());
                        outfile(outpath);
                    }
                }
            }
        }
        else {
            System.out.println("the folder/file is not exist.");
        }
    }
	public static void infile(String filepath) throws IOException{
		File file = new File (filepath);
	    if (!file.exists()) {  
            throw new RuntimeException("file not exist.");  
        }  
	    InputStream in = null;
        in = new FileInputStream(file);
        wc(in);
	}
	public static void outfile(String outpath) throws IOException{
		int tempt1, tempt2, tempt3;
        tempt1 = chars;
		tempt2 = words;
		tempt3 = lines;
	    if (outpath != null) {
			if (wrf == false) {
				if (cf == true) {
		        		output.println("chars count: " + tempt1);
		        }
		        if (wf == true) {
		        		output.println("words count: " + tempt2);
		        }
		        if (lf == true) {
		        		output.println("lines count: " + tempt3);
		        }
			}
			else {
				if (cf == true) {
	        			writer(outpath, "chars count: " + tempt1 + "\n");
		        }
		        if (wf == true) {
		        		writer(outpath, "words count: " + tempt2 + "\n");
		        }
		        if (lf == true) {
		        		writer(outpath, "lines count: " + tempt3 + "\n\n");
		        }
			}
			output.close();
	        outpath = null;
	    }
	    else System.out.println("No file path was given.");
	    System.out.println("Successful.");
	}
	public static void writer(String path, String content) {   
        try {
            FileWriter writer = new FileWriter(path, true);   
            writer.write(content);   
            writer.close();   
        } catch (IOException e) {   
            e.printStackTrace();   
        }   
    }
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		cf = wf = lf = false;
		String filepath = null;
		String outpath = null;
		int argc = args.length;
		for (int i = 0; i < argc; i++) {
	    		switch (args[i]) {
	    		case "-c":
	    			cf = true;
	    			break;
	    		case "-w":
	    			wf = true;
	    			break;
	    		case "-l":
	    			lf = true;
	    			break;
	    		default:
	    			break;
	    		}
		}
		for (int i = 0; i < argc; i++) {
			if (args[i].equals("-o")) {
				filepath = args[i - 1];
				outpath = args[i + 1];
				break;
			}
			else {
				filepath = args[argc -1];
				outpath = "result.txt";
			}
		}
		File outfile = new File ("result.txt");
		if (!outfile.exists()) outfile.createNewFile();
		try {
			output = new PrintWriter(new FileOutputStream(outpath));
			}
		catch (FileNotFoundException e) {
			System.out.println("Error opening the file " + outpath);
			System.exit(0);
		}
		for (int i = 0; i < argc; i++) {
			if (args[i].equals("-s")) {
				traverseFolder(filepath, outpath);
				return;
			}
		}
		infile(filepath);
		outfile(outpath);
		return;
	}
}
