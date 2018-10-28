package org.jetbrains.datamagus.model.ancillary


import com.jetbrains.datamagus.model.ancillary.Family
import com.jetbrains.datamagus.model.ancillary.findByName
import com.jetbrains.datamagus.model.ancillary.indexOfName
import com.jetbrains.datamagus.model.content.AbElement
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.datamagus.model.content.ImmConAttribute
import org.jetbrains.datamagus.model.content.ImmConDomain
import org.jetbrains.datamagus.model.content.ImmElement
import org.junit.jupiter.api.Test


class ImmFamilyTest
{

    @Test
    fun emptyFamily() {
        val f: Family<AbElement> = EmptyFamily
        assertThat(f.size).isEqualTo(0)
        assertThat(f.isEmpty()).isTrue()
        assertThat(f.isNotEmpty()).isFalse()
    }

    @Test
    fun emptyFamilyIterator() {
        val f: Family<AbElement> = EmptyFamily
        val iterator = f.iterator()
        assertThat(iterator.hasNext()).isFalse()
    }

    @Test
    fun singletonFamily() {
        val e: ImmElement = ImmConDomain(111)
        val f: Family<AbElement> = ImmSingletonFamily<ImmElement>(e)
        assertThat(f[0]).isSameAs(e)
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
        val f: Family<AbElement> = ImmMultFamily(arrayOf(e1, e2, e3))
        assertThat(f[0]).isSameAs(e1)
        assertThat(f[1]).isSameAs(e2)
        assertThat(f[2]).isSameAs(e3)
        assertThat(f.size).isEqualTo(3)
        assertThat(f.isEmpty()).isFalse()
        assertThat(f.isNotEmpty()).isTrue()
    }

    @Test
    fun multFamilyIterator() {
        val e1: ImmElement = ImmConDomain(111)
        val e2: ImmElement = ImmConDomain(222)
        val e3: ImmElement = ImmConDomain(333)
        val f: Family<AbElement> = ImmMultFamily(arrayOf(e1, e2, e3))
        val iterator = f.iterator()
        assertThat(iterator.hasNext()).isTrue()
        assertThat(iterator.next()).isSameAs(e1)
        assertThat(iterator.hasNext()).isTrue()
        assertThat(iterator.next()).isSameAs(e2)
        assertThat(iterator.hasNext()).isTrue()
        assertThat(iterator.next()).isSameAs(e3)
        assertThat(iterator.hasNext()).isFalse()
    }

    @Test
    fun familyOf_0() {
        val f = familyOf<ImmConAttribute>()
        assertThat(f.size).isEqualTo(0)
    }

    @Test
    fun familyOf_1() {
        val a1 = ImmConAttribute(26, "Mura")
        val f = familyOf(a1)
        assertThat(f.size).isEqualTo(1)
        assertThat(f.first).isSameAs(a1)
    }

    @Test
    fun familyOf_2() {
        val a1 = ImmConAttribute(26, "Мура")
        val a2 = ImmConAttribute(42, "Лабуда")
        val f = familyOf(a1, a2)
        assertThat(f.size).isEqualTo(2)
        assertThat(f.first).isSameAs(a1)
        assertThat(f.last).isSameAs(a2)
    }


    @Test
    fun findByName_basic() {
        val a1 = ImmConAttribute(11, "Мура")
        val a2 = ImmConAttribute(22, "Лабуда")
        val a3 = ImmConAttribute(26, "То что надо")
        val a4 = ImmConAttribute(66, "Что-то другое")
        val f = familyOf(a1, a2, a3, a4)
        assertThat(f.findByName("То что надо")).isSameAs(a3)
        assertThat(f.findByName("то что НАДО")).isSameAs(a3)
        assertThat(f.findByName("то что НАДО", true)).isNull()
    }

    @Test
    fun indexOfName_basic() {
        val a1 = ImmConAttribute(11, "Мура")
        val a2 = ImmConAttribute(22, "Лабуда")
        val a3 = ImmConAttribute(26, "То что надо")
        val a4 = ImmConAttribute(66, "Что-то другое")
        val f = familyOf(a1, a2, a3, a4)
        assertThat(f.indexOfName("То что надо")).isEqualTo(2)
        assertThat(f.indexOfName("то что НАДО")).isEqualTo(2)
        assertThat(f.indexOfName("то что НАДО", true)).isEqualTo(-1)
    }


}