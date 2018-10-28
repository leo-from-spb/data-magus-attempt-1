package com.jetbrains.datamagus.model.ancillary

sealed class Ref
{

    abstract val targetId: Int

    abstract val forkId: Int

    abstract fun get(index: Int): Int

    abstract val size: Int
}


class Ref1
(
        override val targetId: Int
)
    : Ref()
{

    override val forkId: Int
        get() = targetId

    override fun get(index: Int): Int = when (index)
    {
        0    -> targetId
        else -> throw IndexOutOfBoundsException("Index is $index when size is 1")
    }

    override val size: Int get() = 1
}


class Ref2
(
        override val targetId: Int,
        override val forkId: Int
)
    : Ref()
{

    override fun get(index: Int): Int = when (index)
    {
        0    -> targetId
        1    -> forkId
        else -> throw IndexOutOfBoundsException("Index is $index when size is 2")
    }

    override val size: Int get() = 2
}


class Ref3
(
        override val targetId: Int,
        val middleId: Int,
        override val forkId: Int
)
    : Ref()
{
    override fun get(index: Int): Int = when (index)
    {
        0    -> targetId
        1    -> middleId
        2    -> forkId
        else -> throw IndexOutOfBoundsException("Index is $index when size is 3")
    }

    override val size: Int get() = 3
}


class Ref4
(
        override val targetId: Int,
        val middle1Id: Int,
        val middle2Id: Int,
        override val forkId: Int
)
    : Ref()
{
    override fun get(index: Int): Int = when (index)
    {
        0    -> targetId
        1    -> middle1Id
        2    -> middle2Id
        3    -> forkId
        else -> throw IndexOutOfBoundsException("Index is $index when size is 4")
    }

    override val size: Int get() = 4
}


class Ref5
(
        override val targetId: Int,
        val middle1Id: Int,
        val middle2Id: Int,
        val middle3Id: Int,
        override val forkId: Int
)
    : Ref()
{
    override fun get(index: Int): Int = when (index)
    {
        0    -> targetId
        1    -> middle1Id
        2    -> middle2Id
        3    -> middle3Id
        4    -> forkId
        else -> throw IndexOutOfBoundsException("Index is $index when size is 5")
    }

    override val size: Int get() = 5
}

