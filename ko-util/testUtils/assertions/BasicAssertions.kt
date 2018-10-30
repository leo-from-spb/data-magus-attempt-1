@file:JvmName("BasicAssertions")
@file:Suppress("UsePropertyAccessSyntax", "unused", "unused_parameter")

package org.jetbrains.datamagus.testUtils.assertions

import org.assertj.core.api.Assertions.assertThat
import kotlin.reflect.KClass

//@formatter:off

infix fun<X> X?             .mustBe(expect: X)           { assertThat(this).isEqualTo(expect)           }
infix fun<X> X?             .mustBeSameAs(expect: X)     { assertThat(this).isSameAs(expect)            }
infix fun<X> X?             .mustNotBe(expect: X)        { assertThat(this).isNotEqualTo(expect)        }
infix fun<X> X?             .mustNotBeSameAs(expect: X)  { assertThat(this).isNotSameAs(expect)         }

infix fun<X> X?             .mustBe(marker: Null)        { assertThat(this).isNull()                    }
infix fun<X> X?             .mustBe(marker: NotNull)     { assertThat(this).isNotNull()                 }
infix fun<X> X?             .mustNotBe(marker: Null)     { assertThat(this).isNotNull()                 }

infix fun<X> X?             .mustBe(klass: KClass<*>)    { assertThat(this).isInstanceOf(klass.java)    }
infix fun<X> X?             .mustBe(klass: Class<*>)     { assertThat(this).isInstanceOf(klass)         }
infix fun<X> X?             .mustNotBe(klass: KClass<*>) { assertThat(this).isNotInstanceOf(klass.java) }
infix fun<X> X?             .mustNotBe(klass: Class<*>)  { assertThat(this).isNotInstanceOf(klass)      }

infix fun<X> Array<X>?      .mustBe(marker: Empty)       { assertThat(this).isEmpty()                   }
infix fun<X> Array<X>?      .mustBe(marker: NotEmpty)    { assertThat(this).isNotEmpty()                }
infix fun<X> Array<X>?      .mustNotBe(marker: Empty)    { assertThat(this).isNotEmpty()                }

infix fun<X> Collection<X>? .mustBe(marker: Empty)       { assertThat(this).isEmpty()                   }
infix fun<X> Collection<X>? .mustBe(marker: NotEmpty)    { assertThat(this).isNotEmpty()                }
infix fun<X> Collection<X>? .mustNotBe(marker: Empty)    { assertThat(this).isNotEmpty()                }

infix fun<X> Iterable<X>?   .mustBe(marker: Empty)       { assertThat(this).isEmpty()                   }
infix fun<X> Iterable<X>?   .mustBe(marker: NotEmpty)    { assertThat(this).isNotEmpty()                }
infix fun<X> Iterable<X>?   .mustNotBe(marker: Empty)    { assertThat(this).isNotEmpty()                }


