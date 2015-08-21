import java.util.*;


public class WordManager 
{
	private TreeMap<Character,PrefixNode> manager;
	
	public WordManager()
	{
		manager = new TreeMap<Character,PrefixNode>();
	}
	
	//Add method, Uses scanner input, checks characters for an apostrophe and strips it from the string
	public void add(Scanner in)
	{
		while(in.hasNext()) {
			String key = "'";
			String word = in.next();
			String build = "";
			for(int i = 0; i<word.length();i++) 
			{
				if(word.charAt(i) != key.charAt(0)) {
					build = build + word.charAt(i);
				}
			}
			addWord(build);
		}
	}
	
	public void addWord(String ch)
	{
		int i;
		PrefixNode curr;
		
		String c = ch.toLowerCase();
		
		if (manager.containsKey(c.charAt(0)))
			curr = manager.get(c.charAt(0));
		else
		{
			curr = new PrefixNode();
			manager.put(c.charAt(0),curr);
		}
		
		
		for (i=1;i<c.length();i++)
		{
			curr.addWord(ch);
			curr = curr.getNode(c.charAt(i));
		}
		curr.addWord(ch);
	}
	
	public ArrayList<String> getWords(String prefix)
	{
		ArrayList<String> list;
		PrefixNode curr;
		int i;
		
		prefix = prefix.toLowerCase();
		
		if (prefix.length()<1)
		{
			return new ArrayList<String>();
		}
		curr = manager.get(prefix.charAt(0));
		
		
		for (i=1;i<prefix.length();i++)
		{
			curr = curr.getNode(prefix.charAt(i));
		}
		list = curr.getWords();
		return list;
	}
}
