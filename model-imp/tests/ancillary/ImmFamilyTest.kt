package org.jetbrains.datamagus.model.ancillary


import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.datamagus.model.content.AbElement
import org.jetbrains.datamagus.model.content.FixConAttribute
import org.jetbrains.datamagus.model.content.FixConDomain
import org.jetbrains.datamagus.model.content.FixElement
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
        val e: FixElement = FixConDomain(111)
        val f: Family<AbElement> = FixSingletonFamily<FixElement>(e)
        assertThat(f[0]).isSameAs(e)
        assertThat(f.size).isEqualTo(1)
        assertThat(f.isEmpty()).isFalse()
        assertThat(f.isNotEmpty()).isTrue()
    }

    @Test
    fun singletonFamilyIterator() {
        val e: FixElement = FixConDomain(111)
        val f: Family<AbElement> = FixSingletonFamily<FixElement>(e)
        val iterator = f.iterator()
        assertThat(iterator.hasNext()).isTrue()
        assertThat(iterator.next()).isSameAs(e)
        assertThat(iterator.hasNext()).isFalse()
    }

    @Test
    fun multFamily() {
        val e1: FixElement = FixConDomain(111)
        val e2: FixElement = FixConDomain(222)
        val e3: FixElement = FixConDomain(333)
        val f: Family<AbElement> = FixMultFamily(arrayOf(e1, e2, e3))
        assertThat(f[0]).isSameAs(e1)
        assertThat(f[1]).isSameAs(e2)
        assertThat(f[2]).isSameAs(e3)
        assertThat(f.size).isEqualTo(3)
        assertThat(f.isEmpty()).isFalse()
        assertThat(f.isNotEmpty()).isTrue()
    }

    @Test
    fun multFamilyIterator() {
        val e1: FixElement = FixConDomain(111)
        val e2: FixElement = FixConDomain(222)
        val e3: FixElement = FixConDomain(333)
        val f: Family<AbElement> = FixMultFamily(arrayOf(e1, e2, e3))
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
        val f = familyOf<FixConAttribute>()
        assertThat(f.size).isEqualTo(0)
    }

    @Test
    fun familyOf_1() {
        val a1 = FixConAttribute(26, "Mura")
        val f = familyOf(a1)
        assertThat(f.size).isEqualTo(1)
        assertThat(f.first).isSameAs(a1)
    }

    @Test
    fun familyOf_2() {
        val a1 = FixConAttribute(26, "Мура")
        val a2 = FixConAttribute(42, "Лабуда")
        val f = familyOf(a1, a2)
        assertThat(f.size).isEqualTo(2)
        assertThat(f.first).isSameAs(a1)
        assertThat(f.last).isSameAs(a2)
    }


    @Test
    fun findByName_basic() {
        val a1 = FixConAttribute(11, "Мура")
        val a2 = FixConAttribute(22, "Лабуда")
        val a3 = FixConAttribute(26, "То что надо")
        val a4 = FixConAttribute(66, "Что-то другое")
        val f = familyOf(a1, a2, a3, a4)
        assertThat(f.findByName("То что надо")).isSameAs(a3)
        assertThat(f.findByName("то что НАДО")).isSameAs(a3)
        assertThat(f.findByName("то что НАДО", true)).isNull()
    }

    @Test
    fun indexOfName_basic() {
        val a1 = FixConAttribute(11, "Мура")
        val a2 = FixConAttribute(22, "Лабуда")
        val a3 = FixConAttribute(26, "То что надо")
        val a4 = FixConAttribute(66, "Что-то другое")
        val f = familyOf(a1, a2, a3, a4)
        assertThat(f.indexOfName("То что надо")).isEqualTo(2)
        assertThat(f.indexOfName("то что НАДО")).isEqualTo(2)
        assertThat(f.indexOfName("то что НАДО", true)).isEqualTo(-1)
    }


}