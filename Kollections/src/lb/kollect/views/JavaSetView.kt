package lb.kollect.views;

import lb.kollect.intf.Set


public class JavaSetView<T> (private val jSet: java.util.Set<T>)
           : JavaCollectionView<T> (jSet),
             Set<T>
{

}



