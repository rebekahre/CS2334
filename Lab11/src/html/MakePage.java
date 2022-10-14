package html;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeSet;


public class MakePage {

	// private static final int TOP = 16777215;
	private static final int TOP = 10000;
	private static final int STEP = 10;
	// You will use this directory on your web server
	// private static final String DIRECTORY = "/var/www/html/colors/";
	private static final String DIRECTORY = "./colors/";

	private static HashMap<String, String> director;

	public MakePage() {
		// TODO Auto-generated constructor stub
		director = new HashMap<>();
	}

	public static String myPage(String previousPage, String nextPage, String colorValue) {
		var page = new StringBuilder("<html>");

		page.append("<head>\n");
		page.append("  <title>Colorz</title>\n");
		page.append("\n");
		page.append("<style type=\"text/css\">\n");
		page.append("   div { background-color:#" + colorValue +"; height:300px; font-size:28px; }\n");
		page.append("   ul { color: blue; }\n");
		page.append("   p { color: #ababab }\n");
		page.append("   .previous {\n");
		page.append("           background-color: #f1f1f1;\n");
		page.append("           color: black;\n");
		page.append("   }\n");
		page.append("   .next {\n");
		page.append("           background-color: #4caf50;\n");
		page.append("           color: white;\n");
		page.append("   }\n");
		page.append("</style>\n");
		page.append("</head>\n");
		page.append("\n");
		page.append("<body>\n");
		page.append("<div id=\"box\"> \n");
		page.append("<p>Hello </p> \n");
		page.append("<p> color: " + colorValue + " </p>\n");
		page.append("</div>\n");
		page.append("<div>\n");
		page.append("<p> You could provide your text here. </p>\n");
		page.append("</div>\n");
		page.append("<div>\n");
		page.append("<hr>\n");
		page.append("<span style=\"background-color: #FFFF00\">Navigation:</span>");
		page.append("<ul>\n");
		page.append("<li><a href=\"" + previousPage + "\" class=\"previous\">previous</a></li>\n");
		page.append("<li><a href=\"" + nextPage + "\" class=\"next\">next</a></li>\n");
		page.append("</ul>\n");
		page.append("</div>\n");
		page.append("</body>\n");
		page.append("</html>\n");

		return page.toString();
	}



	public static String genHex() {
		Random random = new Random();
		int val = random.nextInt();
		String Hex = new String();
		Hex = Integer.toHexString(val);
		return Hex;
	}

	// Create a mapping between a color value, and the name of the web page
	private void direct() {
		for (int i = 0; i < TOP; i = i + STEP) {
			String hex = String.format("%06X", i);
			String pageAddress = "doc" + hex + ".html";
			director.put(hex, pageAddress);
			System.out.println(pageAddress);
		}
	}

	public static void main(String[] args) {

		var maker = new MakePage();
		maker.direct();

		TreeSet<String> myKeys = new TreeSet<String>(director.keySet());
		
		// get familiar with this method, that allows you to get "lower" and "higher" values from the ordered set
		for( String str : myKeys) {
			String p = myKeys.lower(str);
			String n = myKeys.higher(str);
			System.out.println(p + "<= " + str + " =>" + n);
		}
			
		// we need previous and next web page name for the navigation between different pages
		String previousPage = "previous.html";
		String nextPage = "next.html";
		for (Entry<String, String> entry : director.entrySet()) {
			System.out.println("Key = " + entry.getKey() + "Value = " + entry.getValue());
			String colorValue = entry.getKey();
			nextPage = director.get(myKeys.higher(colorValue));
			String str = myPage(previousPage, nextPage, colorValue);
			
			// write to content of our HTML page to a file
			try {
				String fileName = entry.getValue();
				previousPage = fileName;
				fileName = DIRECTORY + fileName;
				var writer = new FileWriter(fileName);
				writer.write(str);
				writer.close();
				System.out.println("Done writing");
			} catch (IOException e) {
				System.out.println(e.toString());
				e.printStackTrace();
			}
		}

	}

}