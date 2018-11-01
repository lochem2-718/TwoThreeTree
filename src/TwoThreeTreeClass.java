// TwoThreeTreeClass //////////////////////////////////////////////////////////


/**
 * A class implementing the Two-Three Tree data structure
 * @author Jared Weinberger
 * @version 1
 */
public class TwoThreeTreeClass
{
    static int LEFT_CHILD_SELECT = 0;

    /**
     * 2-3 node class using NodeDataClass data and three references
     *
     * @author MichaelL
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
         * Private constructor used to create new left or right child node
         * of given parent with the children linked from
         * a current three-node object
         *
         * @param childSelect integer selection value that informs
         * the constructor to create a left or a right child
         * along with making all the sub-child links;
         * uses class constants LEFT_CHILD_SELECT and RIGHT_CHILD_SELECT
         *
         * @param localRef TwoThreeNodeClass reference
         * with three-node data and associated references
         *
         * @param parRef TwoThreeNodeclass parent reference
         * for linking with new left or right child node that is created
         */
        private TwoThreeNodeClass( int childSelect,
                                   TwoThreeNodeClass localRef, TwoThreeNodeClass parRef )
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
         * Note: Only copies data; does not copy links
         * as these would be incorrect for the new tree
         * (i.e., they would be related to the copied tree)
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

}
