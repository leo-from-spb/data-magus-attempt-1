package lb.kollect.const;

import lb.kollect.intf.Collection
import lb.kollect.containers.ArrList


public fun listOf<T>(vararg items: T): ConstList<T> =
        ConstList<T>(items, items.size)


public fun empty<T>(): ConstNothing<T> = ConstNothing()

public fun emptyList<T>(): ConstList<T> = ConstList<T>(array<T>(),0)


// TODO use ConstSet instead of ConstCollection
public fun diff<T>(a: Collection<T>, b: Collection<T>): Couple<ConstCollection<T>>
{
    // TODO reimplement to increase performance

    val bufA = ArrList<T>(); bufA.ensureCapacity(a.size)
    val bufB = ArrList<T>(); bufB.ensureCapacity(b.size)

    for (x in a)
        if (x !in b)
            bufA.add(x);
    for (x in b)
        if (x !in a)
            bufB.add(x);

    return Couple(bufA.turnIntoConstList(), bufB.turnIntoConstList())
}


