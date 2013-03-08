package lb.utils


fun ObjectsAreEqual(object1: Any?, object2: Any?): Boolean
{
    if (object1 == object2)
        return true;
    if (object1 == null || object2 == null)
        return false;
    return object1.equals(object2);
}
