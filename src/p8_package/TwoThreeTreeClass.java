package p8_package;
// TwoThreeTreeClass //////////////////////////////////////////////////////////


/**
 * A class implementing the Two-Three Tree data structure
 *
 * @author Jared Weinberger
 * @version 1
 */
public class TwoThreeTreeClass
{
    /**
     * 2-3 node class using NodeDataClass data and three references
     */
    private class TwoThreeNodeClass
    {
        /**
         * internal 2-3 node data
         */
        private int leftData, centerData, rightData, numItems;

        /**
         * references from 2-3 node
         */
        private TwoThreeNodeClass leftChildRef, centerChildRef, rightChildRef;

        /**
         * references for managing 2-3 node adjustments
         */
        private TwoThreeNodeClass auxLeftRef, auxRightRef;

        /**
         * parent reference for 2-3 node
         */
        private TwoThreeNodeClass parentRef;

        /**
         * Default 2-3 node class constructor
         */
        private TwoThreeNodeClass()
        {
            leftData = centerData = rightData = numItems = 0;

            leftChildRef = centerChildRef = rightChildRef = null;

            auxLeftRef = auxRightRef = parentRef = null;
        }

        /**
         * Initialization 2-3 node class constructor
         *
         * @param centerIn integer data sets first node initialization
         */
        private TwoThreeNodeClass( int centerIn )
        {
            centerData = centerIn;

            leftData = rightData = 0;

            numItems = 1;

            leftChildRef = centerChildRef = rightChildRef = null;

            auxLeftRef = auxRightRef = parentRef = null;
        }

        /**
         * Private constructor used to create new left or right child node of
         * given parent with the children linked from a current three-node
         * object
         *
         * @param childSelect integer selection value that informs the
         *                    constructor to create a left or a right child
         *                    along with making all the sub-child links; uses
         *                    class constants LEFT_CHILD_SELECT and
         *                    RIGHT_CHILD_SELECT
         * @param localRef    TwoThreeNodeClass reference with three-node data
         *                    and associated references
         * @param parRef      TwoThreeNodeclass parent reference for linking
         *                    with new left or right child node that is created
         */
        private TwoThreeNodeClass( int childSelect,
                                   TwoThreeNodeClass localRef,
                                   TwoThreeNodeClass parRef )
        {
            if( childSelect == LEFT_CHILD_SELECT )
            {
                this.centerData = localRef.leftData;
                this.leftChildRef = localRef.leftChildRef;
                this.rightChildRef = localRef.auxLeftRef;

                if( leftChildRef != null )
                {
                    this.leftChildRef.parentRef = this;
                    this.rightChildRef.parentRef = this;
                }
            }

            else  // assume right child select
            {
                this.centerData = localRef.rightData;
                this.leftChildRef = localRef.auxRightRef;
                this.rightChildRef = localRef.rightChildRef;

                if( rightChildRef != null )
                {
                    this.leftChildRef.parentRef = this;
                    this.rightChildRef.parentRef = this;
                }
            }

            this.leftData = this.rightData = 0;
            this.numItems = 1;
            this.centerChildRef = null;
            this.auxLeftRef = this.auxRightRef = null;
            this.parentRef = parRef;
        }

        /**
         * Copy 2-3 node class constructor
         * <p>
         * Note: Only copies data; does not copy links as these would be
         * incorrect for the new tree (i.e., they would be related to the copied
         * tree)
         *
         * @param copied TwoThreeNodeClass object to be copied
         */
        private TwoThreeNodeClass( TwoThreeNodeClass copied )
        {
            leftData = copied.leftData;
            centerData = copied.centerData;
            rightData = copied.rightData;

            numItems = copied.numItems;

            leftChildRef = centerChildRef = rightChildRef = null;

            auxLeftRef = auxRightRef = parentRef = null;
        }
    }


    /**
     * constant used in constructor to indicate which child to create - Right
     */
    private final int LEFT_CHILD_SELECT = 108;

    /**
     * constant used in constructor to indicate which child to create - Right
     */
    private final int RIGHT_CHILD_SELECT = 114;

    /**
     * constant used for identifying one data item stored
     */
    private final int ONE_DATA_ITEM = 1;

    /**
     * constant used for identifying two data items stored
     */
    private final int TWO_DATA_ITEM = 2;

    /**
     * constant used for identifying three data items stored
     */
    private final int THREE_DATA_ITEM = 3;

    /**
     * Used for acquiring ordered tree visitations in String form
     */
    private TwoThreeNodeClass root;

    /**
     * Root of the tree
     */
    private String outputString;


    /**
     * Default 2-3 tree constructor
     */
    public TwoThreeTreeClass()
    {
        root = null;
        outputString = "";
    }

    /**
     * Copy 2-3 tree constructor
     *
     * @param copied TwoThreeTreeClass object to be duplicated; data is copied,
     *               but new nodes and references must be created
     */
    public TwoThreeTreeClass( TwoThreeTreeClass copied )
    {
        this.root = copyConstructorHelper( copied.root );

    }


    /**
     * Implements tree duplication effort with recursive method; copies data
     * into newly created nodes and creates all new references to child nodes
     *
     * @param workingRef TwoThreeNodeClass reference that is updated to lower
     *                   level children with each recursive call
     * @return TwoThreeNodeClass reference to next higher level node; last
     * return is to root node of THIS object
     */
    private TwoThreeNodeClass copyConstructorHelper( TwoThreeNodeClass workingRef )
    {
        TwoThreeNodeClass copiedNode;

        if( workingRef == null )
        {
            return null;
        }
        else
        {
            copiedNode = new TwoThreeNodeClass( workingRef );

            copiedNode.leftChildRef =
                    copyConstructorHelper( workingRef.leftChildRef );
            if( copiedNode.leftChildRef != null )
            {
                copiedNode.leftChildRef.parentRef = copiedNode;
            }

            copiedNode.rightChildRef =
                    copyConstructorHelper( workingRef.rightChildRef );
            if( copiedNode.rightChildRef != null )
            {
                copiedNode.rightChildRef.parentRef = copiedNode;
            }

            copiedNode.centerChildRef =
                    copyConstructorHelper( workingRef.centerChildRef );
            if( copiedNode.centerChildRef != null )
            {
                copiedNode.centerChildRef.parentRef = copiedNode;
            }

            return copiedNode;
        }
    }


    /**
     * Adds item to 2-3 tree using addItemHelper as needed
     *
     * @param itemVal integer value to be added to the tree
     */
    public void addItem( int itemVal )
    {

    }

    /**
     * Helper method searches from top of tree to bottom using divide and
     * conquer strategy to find correct location (node) for new added value;
     * once location is found, item is added to node using addAndOrganizeData
     * and then fixUpInsert is called in case the updated node has become a
     * three-value node
     *
     * @param parRef   TwoThreeNodeClass reference to the parent of the current
     *                 reference at a given point in the recursion process
     * @param localRef TwoThreeNodeClass reference to the current item at the
     *                 same given point in the recursion process
     * @param itemVal  integer value to be added to the tree
     */
    private void addItemHelper( TwoThreeNodeClass parRef
            , TwoThreeNodeClass localRef
            , int itemVal )
    {

    }

    /**
     * Method is called when addItemHelper arrives at the bottom of the 2-3
     * search tree (i.e., all node's children are null);
     * <p>
     * Assumes one- or two- value nodes and adds one more to the appropriate one
     * resulting in a two- or three- value node
     *
     * @param localRef TwoThreeNodeClass reference has original node data and
     *                 contains added value when method completes; method does
     *                 not manage any node links
     * @param itemVal  integer value to be added to 2-3 node
     */
    private void addAndOrganizeData( TwoThreeNodeClass localRef
            , int itemVal )
    {

    }

    /**
     * Method used to fix tree any time a three-value node has been added to the
     * bottom of the tree; it is always called but decides to act only if it
     * finds a three-value node Resolves current three-value node which may add
     * a value to the node above; if the node above becomes a three-value node,
     * then this is resolved with the next recursive call
     * <p>
     * Recursively climbs from bottom to top of tree resolving any three-value
     * nodes found
     *
     * @param localRef TwoThreeNodeClas reference initially given the currently
     *                 updated node, then is given parent node recursively each
     *                 time a three-value node was resolved
     */
    private void fixUpInsert( TwoThreeNodeClass localRef )
    {

    }

    /**
     * Tests center value if single node, tests left and right values if
     * two-value node; returns true if specified data is found in any given
     * node
     * <p>
     * Note: Method does not use any branching operations such as if/else/etc.
     *
     * @param localRef TwoThreeNodeClass reference to node with one or two data
     *                 items in it
     * @param searchVal integer value to be found in given node
     * @return boolean result of test
     */
    private boolean foundInNode( TwoThreeNodeClass localRef, int searchVal )
    {
        return localRef.leftData == searchVal
               || localRef.centerData == searchVal
               || localRef.rightData == searchVal;
    }

    /**
     * Search method used by programmer to find specified item in 2-3 tree
     *
     * @param searchVal integer value to be found
     * @return boolean result of search effort
     */
    public boolean search( int searchVal )
    {
        return searchHelper( root, searchVal );
    }

    /**
     * Search helper method that traverses through tree in a recursive divide
     * and conquer search fashion to find given integer in 2-3 tree
     *
     * @param localRef  TwoThreeNodeClass reference to given node at any point
     *                  during the recursive process
     * @param searchVal integer value to be found in tree
     * @return boolean result of search effort
     */
    private boolean searchHelper( final TwoThreeNodeClass localRef
            , int searchVal )
    {
        // java short circuits and will not keep searching if
        // localRef is null or
        return ( localRef != null )
               && ( foundInNode( localRef, searchVal )
                    || searchHelper( localRef.leftChildRef, searchVal )
                    || searchHelper( localRef.centerChildRef, searchVal )
                    || searchHelper( localRef.rightChildRef, searchVal ) );
    }

    /**
     * Method clears tree so that new items can be added again
     */
    public void clear()
    {
        root = null;
        System.gc();
    }

    /**
     * Public method called by user to display data in order
     * @return String result to be displayed and/or analyzed
     */
    public String inOrderTraversal()
    {

    }

    /**
     * Helper method conducts in order traversal with 2-3 tree
     * <p>
     * Shows location of each value in a node: "C" at center of single node "L"
     * or "R" at left or right of two-value node
     * @param localRef TwoThreeNodeClass reference to current location at any
     *                 given point in the recursion process search
     */
    public void inOrderTraversalHelper( TwoThreeNodeClass localRef )
    {
        // is node null?
    }
}
