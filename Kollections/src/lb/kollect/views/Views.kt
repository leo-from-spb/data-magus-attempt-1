package lb.kollect.views;

import lb.kollect.intf.List


public fun join<T>(vararg lists: List<T>): List<T> = JoinView(lists)



