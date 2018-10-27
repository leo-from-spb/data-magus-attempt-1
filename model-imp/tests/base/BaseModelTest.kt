package org.jetbrains.datamagus.model.base

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BaseModelTest {

    @Test
    fun instantiate() {
        val model = BaseModel()
        assertThat(model.rootVersionCounter.get()).isEqualTo(0)
        assertThat(model.elementIdCounter.get()).isEqualTo(0)
    }


}