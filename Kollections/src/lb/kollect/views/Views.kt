package lb.kollect.views;

import lb.kollect.intf.List


//// UTILITY FUNCTIONS \\\\

public fun join<T>(vararg lists: List<T>): List<T> = JoinView(lists)



//// ADAPTER TO JAVA COLLECTIONS \\\\

public fun<T> java.util.List<T>.asList(): List<T> = JavaListView<T>(this)


