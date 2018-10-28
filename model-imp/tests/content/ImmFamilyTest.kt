package org.jetbrains.datamagus.model.content


import com.jetbrains.datamagus.model.ancillary.Family
import com.jetbrains.datamagus.model.content.AbElement
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class ImmFamilyTest
{

    @Test
    fun emptyFamily() {
        val f: Family<AbElement> = ImmEmptyFamily<ImmElement>()
        assertThat(f.size).isEqualTo(0)
        assertThat(f.isEmpty()).isTrue()
        assertThat(f.isNotEmpty()).isFalse()
    }

    @Test
    fun emptyFamilyIterator() {
        val f: Family<AbElement> = ImmEmptyFamily<ImmElement>()
        val iterator = f.iterator()
        assertThat(iterator.hasNext()).isFalse()
    }

    @Test
    fun singletonFamily() {
        val e: ImmElement = ImmConDomain(111)
        val f: Family<AbElement> = ImmSingletonFamily<ImmElement>(e)
        assertThat(f.size).isEqualTo(1)
        assertThat(f.isEmpty()).isFalse()
        assertThat(f.isNotEmpty()).isTrue()
    }

    @Test
    fun singletonFamilyIterator() {
        val e: ImmElement = ImmConDomain(111)
        val f: Family<AbElement> = ImmSingletonFamily<ImmElement>(e)
        val iterator = f.iterator()
        assertThat(iterator.hasNext()).isTrue()
        assertThat(iterator.next()).isSameAs(e)
        assertThat(iterator.hasNext()).isFalse()
    }

    @Test
    fun multFamily() {
        val e1: ImmElement = ImmConDomain(111)
        val e2: ImmElement = ImmConDomain(222)
        val e3: ImmElement = ImmConDomain(333)
        val f: Family<AbElement> = ImmMultFamily(arrayOf(e1, e2, e3), true)
        assertThat(f.size).isEqualTo(3)
        assertThat(f.isEmpty()).isFalse()
        assertThat(f.isNotEmpty()).isTrue()
    }

    @Test
    fun multFamilyIterator() {
        val e1: ImmElement = ImmConDomain(111)
        val e2: ImmElement = ImmConDomain(222)
        val e3: ImmElement = ImmConDomain(333)
        val f: Family<AbElement> = ImmMultFamily(arrayOf(e1, e2, e3), true)
        val iterator = f.iterator()
        assertThat(iterator.hasNext()).isTrue()
        assertThat(iterator.next()).isSameAs(e1)
        assertThat(iterator.hasNext()).isTrue()
        assertThat(iterator.next()).isSameAs(e2)
        assertThat(iterator.hasNext()).isTrue()
        assertThat(iterator.next()).isSameAs(e3)
        assertThat(iterator.hasNext()).isFalse()
    }



}