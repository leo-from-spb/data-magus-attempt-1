@file:JvmName("PrimitiveAssertions")
@file:Suppress("UsePropertyAccessSyntax", "unused", "unused_parameter")

package org.jetbrains.datamagus.testUtils.assertions

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.datamagus.util.org.jetbrains.datamagus.util.toShorts

//@formatter:off

infix fun Boolean .mustBe(expect: Boolean)                 { assertThat(this).isEqualTo(expect)      }
infix fun Boolean .mustNotBe(unexpect: Boolean)            { assertThat(this).isNotEqualTo(unexpect) }

infix fun Byte    .mustBe(value: Byte)                     { assertThat(this).isEqualTo(value)                                       }
infix fun Byte    .mustBe(predicate: (Byte)->Boolean)      { assertThat(this).matches(predicate)                                     }
infix fun Byte    .mustBe(positive: Positive)              { assertThat(this).isPositive()                                           }
infix fun Byte    .mustBe(negative: Negative)              { assertThat(this).isNegative()                                           }
infix fun Byte    .mustBe(zero: Zero)                      { assertThat(this).isZero()                                               }
infix fun Byte    .mustBeIn(values: Array<Byte>)           { assertThat(this).isIn(*values)                                          }
infix fun Byte    .mustBeIn(values: Collection<Number>)    { assertThat(this).isIn(values)                                           }
infix fun Byte    .mustBeIn(range: IntRange)               { assertThat(this).isBetween(range.first.toByte(), range.last.toByte())   }
infix fun Byte    .mustNotBe(value: Byte)                  { assertThat(this).isNotEqualTo(value)                                    }
infix fun Byte    .mustNotBeIn(values: Array<Byte>)        { assertThat(this).isNotIn(*values)                                       }
infix fun Byte    .mustNotBeIn(values: Collection<Number>) { assertThat(this).isNotIn(values)                                        }
infix fun Byte    .mustNotBe(positive: Positive)           { assertThat(this).isNotPositive()                                        }
infix fun Byte    .mustNotBe(negative: Negative)           { assertThat(this).isNotNegative()                                        }
infix fun Byte    .mustNotBe(zero: Zero)                   { assertThat(this).isNotZero()                                            }

infix fun Short   .mustBe(value: Short)                    { assertThat(this).isEqualTo(value)                                       }
infix fun Short   .mustBe(predicate: (Short)->Boolean)     { assertThat(this).matches(predicate)                                     }
infix fun Short   .mustBe(positive: Positive)              { assertThat(this).isPositive()                                           }
infix fun Short   .mustBe(negative: Negative)              { assertThat(this).isNegative()                                           }
infix fun Short   .mustBe(zero: Zero)                      { assertThat(this).isZero()                                               }
infix fun Short   .mustBeIn(values: Array<Short>)          { assertThat(this).isIn(*values)                                          }
infix fun Short   .mustBeIn(values: Collection<Number>)    { assertThat(this).isIn(values)                                           }
infix fun Short   .mustBeIn(range: IntRange)               { assertThat(this).isBetween(range.first.toShort(), range.last.toShort()) }
infix fun Short   .mustNotBe(value: Short)                 { assertThat(this).isNotEqualTo(value)                                    }
infix fun Short   .mustNotBeIn(values: Array<Short>)       { assertThat(this).isNotIn(*values)                                       }
infix fun Short   .mustNotBeIn(values: Collection<Number>) { assertThat(this).isNotIn(values)                                        }
infix fun Short   .mustNotBe(positive: Positive)           { assertThat(this).isNotPositive()                                        }
infix fun Short   .mustNotBe(negative: Negative)           { assertThat(this).isNotNegative()                                        }
infix fun Short   .mustNotBe(zero: Zero)                   { assertThat(this).isNotZero()                                            }

infix fun Int     .mustBe(value: Int)                      { assertThat(this).isEqualTo(value)                                       }
infix fun Int     .mustBe(predicate: (Int)->Boolean)       { assertThat(this).matches(predicate)                                     }
infix fun Int     .mustBe(positive: Positive)              { assertThat(this).isPositive()                                           }
infix fun Int     .mustBe(negative: Negative)              { assertThat(this).isNegative()                                           }
infix fun Int     .mustBe(zero: Zero)                      { assertThat(this).isZero()                                               }
infix fun Int     .mustBeIn(values: Array<Int>)            { assertThat(this).isIn(*values)                                          }
infix fun Int     .mustBeIn(values: Collection<Number>)    { assertThat(this).isIn(values)                                           }
infix fun Int     .mustBeIn(range: IntRange)               { assertThat(this).isBetween(range.first, range.last)                     }
infix fun Int     .mustNotBe(value: Int)                   { assertThat(this).isNotEqualTo(value)                                    }
infix fun Int     .mustNotBeIn(values: Array<Int>)         { assertThat(this).isNotIn(*values)                                       }
infix fun Int     .mustNotBeIn(values: Collection<Number>) { assertThat(this).isNotIn(values)                                        }
infix fun Int     .mustNotBe(positive: Positive)           { assertThat(this).isNotPositive()                                        }
infix fun Int     .mustNotBe(negative: Negative)           { assertThat(this).isNotNegative()                                        }
infix fun Int     .mustNotBe(zero: Zero)                   { assertThat(this).isNotZero()                                            }

infix fun Long    .mustBe(value: Long)                     { assertThat(this).isEqualTo(value)                                       }
infix fun Long    .mustBe(predicate: (Long)->Boolean)      { assertThat(this).matches(predicate)                                     }
infix fun Long    .mustBe(positive: Positive)              { assertThat(this).isPositive()                                           }
infix fun Long    .mustBe(negative: Negative)              { assertThat(this).isNegative()                                           }
infix fun Long    .mustBe(zero: Zero)                      { assertThat(this).isZero()                                               }
infix fun Long    .mustBeIn(values: Array<Long>)           { assertThat(this).isIn(*values)                                          }
infix fun Long    .mustBeIn(values: Collection<Number>)    { assertThat(this).isIn(values)                                           }
infix fun Long    .mustBeIn(range: IntRange)               { assertThat(this).isBetween(range.first.toLong(), range.last.toLong())   }
infix fun Long    .mustNotBe(value: Long)                  { assertThat(this).isNotEqualTo(value)                                    }
infix fun Long    .mustNotBeIn(values: Array<Long>)        { assertThat(this).isNotIn(*values)                                       }
infix fun Long    .mustNotBeIn(values: Collection<Number>) { assertThat(this).isNotIn(values)                                        }
infix fun Long    .mustNotBe(positive: Positive)           { assertThat(this).isNotPositive()                                        }
infix fun Long    .mustNotBe(negative: Negative)           { assertThat(this).isNotNegative()                                        }
infix fun Long    .mustNotBe(zero: Zero)                   { assertThat(this).isNotZero()                                            }



infix fun ByteArray   .mustBe(values: ByteArray)               { assertThat(this).containsExactly(*values)              }
infix fun ByteArray   .mustBe(values: IntArray)                { assertThat(this).containsExactly(*values)              }
infix fun ByteArray   .mustBe(empty: Empty)                    { assertThat(this).isEmpty()                             }
infix fun ByteArray   .mustBe(notEmpty: NotEmpty)              { assertThat(this).isNotEmpty()                          }
infix fun ByteArray   .mustBe(predicate: (ByteArray)->Boolean) { assertThat(this).matches(predicate)                    }
infix fun ByteArray   .mustHasSize(size: Int)                  { assertThat(this).hasSize(size)                         }
infix fun ByteArray   .mustContain(value: Byte)                { assertThat(this).contains(value)                       }
infix fun ByteArray   .mustContain(value: Int)                 { assertThat(this).contains(value)                       }
infix fun ByteArray   .mustContain(values: Array<Int>)         { assertThat(this).contains(*values.toIntArray())        }
infix fun ByteArray   .mustNotBe(empty: Empty)                 { assertThat(this).isNotEmpty()                          }
infix fun ByteArray   .mustNotContain(value: Byte)             { assertThat(this).doesNotContain(value)                 }
infix fun ByteArray   .mustNotContain(value: Int)              { assertThat(this).doesNotContain(value)                 }
infix fun ByteArray   .mustNotContain(values: Array<Int>)      { assertThat(this).doesNotContain(*values.toIntArray())  }

infix fun ShortArray .mustBe(values: ShortArray)               { assertThat(this).containsExactly(*values)              }
infix fun ShortArray .mustBe(values: IntArray)                 { assertThat(this).containsExactly(*values.toShorts())   }
infix fun ShortArray .mustBe(empty: Empty)                     { assertThat(this).isEmpty()                             }
infix fun ShortArray .mustBe(notEmpty: NotEmpty)               { assertThat(this).isNotEmpty()                          }
infix fun ShortArray .mustBe(predicate: (ShortArray)->Boolean) { assertThat(this).matches(predicate)                    }
infix fun ShortArray .mustHasSize(size: Int)                   { assertThat(this).hasSize(size)                         }
infix fun ShortArray .mustContain(value: Short)                { assertThat(this).contains(value)                       }
infix fun ShortArray .mustContain(value: Int)                  { assertThat(this).contains(value.toShort())             }
infix fun ShortArray .mustNotBe(empty: Empty)                  { assertThat(this).isNotEmpty()                          }
infix fun ShortArray .mustNotContain(value: Short)             { assertThat(this).doesNotContain(value)                 }

infix fun IntArray   .mustBe(values: IntArray)                 { assertThat(this).containsExactly(*values)              }
infix fun IntArray   .mustBe(empty: Empty)                     { assertThat(this).isEmpty()                             }
infix fun IntArray   .mustBe(notEmpty: NotEmpty)               { assertThat(this).isNotEmpty()                          }
infix fun IntArray   .mustBe(predicate: (IntArray)->Boolean)   { assertThat(this).matches(predicate)                    }
infix fun IntArray   .mustHasSize(size: Int)                   { assertThat(this).hasSize(size)                         }
infix fun IntArray   .mustContain(value: Int)                  { assertThat(this).contains(value)                       }
infix fun IntArray   .mustContain(values: Array<Int>)          { assertThat(this).contains(*values.toIntArray())        }
infix fun IntArray   .mustNotBe(empty: Empty)                  { assertThat(this).isNotEmpty()                          }
infix fun IntArray   .mustNotContain(value: Int)               { assertThat(this).doesNotContain(value)                 }
infix fun IntArray   .mustNotContain(values: Array<Int>)       { assertThat(this).doesNotContain(*values.toIntArray())  }

infix fun LongArray  .mustBe(values: LongArray)                { assertThat(this).containsExactly(*values)              }
infix fun LongArray  .mustBe(empty: Empty)                     { assertThat(this).isEmpty()                             }
infix fun LongArray  .mustBe(notEmpty: NotEmpty)               { assertThat(this).isNotEmpty()                          }
infix fun LongArray  .mustBe(predicate: (LongArray)->Boolean)  { assertThat(this).matches(predicate)                    }
infix fun LongArray  .mustHasSize(size: Int)                   { assertThat(this).hasSize(size)                         }
infix fun LongArray  .mustContain(value: Long)                 { assertThat(this).contains(value)                       }
infix fun LongArray  .mustContain(value: Int)                  { assertThat(this).contains(value.toLong())              }
infix fun LongArray  .mustNotBe(empty: Empty)                  { assertThat(this).isNotEmpty()                          }
infix fun LongArray  .mustNotContain(value: Long)              { assertThat(this).doesNotContain(value)                 }

