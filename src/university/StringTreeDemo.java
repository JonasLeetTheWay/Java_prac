package university;

public class StringTreeDemo
{
    public static void main (String[] args)
    {
       StringTree tree = null;
        StringTree tree2 = null;
        StringTree tree3 = null;
       
	   String[] wordsToBeSorted = {"G", "A", "K", "C", "B", "E", "D", "I", "H", "F" }; //must be of lenght at least 9
	   
        tree = new StringTree("E");
        System.out.println(tree);
        
		for(int i = 0; i < wordsToBeSorted.length; i++){
			tree.insert(wordsToBeSorted[i]);
			System.out.println(tree);
		}

        tree2 = new StringTree("E");
        System.out.println(tree2.toStringPreOrder());

        for(int i = 0; i < wordsToBeSorted.length; i++){
            tree2.insert(wordsToBeSorted[i]);
            System.out.println(tree2.toStringPreOrder());
        }

        tree3 = new StringTree("E");
        System.out.println(tree3.toStringPostOrder());

        for(int i = 0; i < wordsToBeSorted.length; i++){
            tree3.insert(wordsToBeSorted[i]);
            System.out.println(tree3.toStringPostOrder());
        }

		
    }
}
