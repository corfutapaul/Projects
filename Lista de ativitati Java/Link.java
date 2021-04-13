package Assignment5.Assignment5;

public class Link {
    private static String link;

	public Link(String link) {
		super();
		this.link = link;
	}

	public static String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	public static String getPath()
	{
	
		return getLink();
	}
}
