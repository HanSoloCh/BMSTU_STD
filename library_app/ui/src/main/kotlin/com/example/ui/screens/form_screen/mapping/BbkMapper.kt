package com.example.ui.screens.form_screen.mapping

import com.example.ui.model.BbkModel
import com.example.ui.screens.form_screen.form.BbkForm

object BbkMapper {
    fun toModel(form: BbkForm) = BbkModel(
        id = form.id,
        code = form.code,
        description = form.description
    )

    fun toForm(model: BbkModel) = BbkForm(
        id = model.id,
        code = model.code,
        description = model.description,
    )
}