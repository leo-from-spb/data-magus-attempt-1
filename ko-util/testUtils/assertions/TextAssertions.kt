@file:JvmName("TextAssertions")
@file:Suppress("UsePropertyAccessSyntax", "unused", "unused_parameter")

package org.jetbrains.datamagus.testUtils.assertions

import org.assertj.core.api.Assertions.assertThat
import java.util.regex.Pattern


infix fun CharSequence?.mustBe(text: CharSequence) { assertThat(this).isEqualTo(text) }
infix fun CharSequence?.mustLikes(text: CharSequence) { assertThat(this).isEqualToIgnoringCase(text) }

infix fun CharSequence?.mustBe(predicate: (CharSequence)->Boolean) { assertThat(this).matches(predicate) }

infix fun CharSequence?.mustBe(marker: Null) { assertThat(this).isNull() }
infix fun CharSequence?.mustBe(marker: NotNull) { assertThat(this).isNotNull() }
infix fun CharSequence?.mustNotBe(marker: NotNull) { assertThat(this).isNotNull() }

infix fun CharSequence?.mustBe(marker: Uppercase) { assertThat(this).isUpperCase() }
infix fun CharSequence?.mustBe(marker: Lowercase) { assertThat(this).isLowerCase() }
infix fun CharSequence?.mustBe(marker: Blank) { assertThat(this).isBlank() }
infix fun CharSequence?.mustNotBe(marker: Blank) { assertThat(this).isNotBlank() }

infix fun CharSequence?.mustMatch(pattern: Pattern) { assertThat(this).matches(pattern) }
infix fun CharSequence?.mustMatch(pattern: String) { assertThat(this).matches(pattern) }
infix fun CharSequence?.mustNotMatch(pattern: Pattern) { assertThat(this).doesNotMatch(pattern) }
infix fun CharSequence?.mustNotMatch(pattern: String) { assertThat(this).doesNotMatch(pattern) }

infix fun CharSequence?.mustContain(infix: CharSequence) { assertThat(this).contains(infix) }
infix fun CharSequence?.mustStartWith(prefix: CharSequence) { assertThat(this).startsWith(prefix) }
infix fun CharSequence?.mustFinishWith(suffix: CharSequence) { assertThat(this).endsWith(suffix) }

infix fun CharSequence?.mustContain(infix: Char) { assertThat(this).contains(infix.toString()) }
infix fun CharSequence?.mustStartWith(prefix: Char) { assertThat(this).startsWith(prefix.toString()) }
infix fun CharSequence?.mustFinishWith(suffix: Char) { assertThat(this).endsWith(suffix.toString()) }

infix fun CharSequence?.mustNotContain(infix: CharSequence) { assertThat(this).doesNotContain(infix) }
infix fun CharSequence?.mustNotStartWith(prefix: CharSequence) { assertThat(this).doesNotStartWith(prefix) }
infix fun CharSequence?.mustNotFinishWith(suffix: CharSequence) { assertThat(this).doesNotEndWith(suffix) }

infix fun CharSequence?.mustNotContain(infix: Char) { assertThat(this).doesNotContain(infix.toString()) }
infix fun CharSequence?.mustNotStartWith(prefix: Char) { assertThat(this).doesNotStartWith(prefix.toString()) }
infix fun CharSequence?.mustNotFinishWith(suffix: Char) { assertThat(this).doesNotEndWith(suffix.toString()) }

infix fun CharSequence?.mustHasSize(size: Int) { assertThat(this).hasSize(size) }

