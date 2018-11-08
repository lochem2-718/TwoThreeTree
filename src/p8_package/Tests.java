package p8_package;

public class Tests
{
    static int[] array = { 1, 2, 5, 3, 8, 7, 21, 9, 36, 45, 54, 43, 12 };

    public static void main( String[] arguments )
    {
        TwoThreeTreeClass tree = new TwoThreeTreeClass();
        for( int number : array )
        {
            tree.addItem( number );
        }
        System.out.println( "First Tree Inorder:\n" + tree.inOrderTraversal() );

        TwoThreeTreeClass treeCopy = new TwoThreeTreeClass( tree );
        System.out.println( "Copy Tree Inorder:\n" + tree.inOrderTraversal() );
    }
}
