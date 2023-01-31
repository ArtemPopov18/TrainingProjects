package com.popov.cleanarchitecture.data

import com.popov.cleanarchitecture.entity.UsefulActivity

class UsefulActivityDto(
    override val accessibility: Double,
    override val activity: String,
    override val key: String,
    override val link: String,
    override val participants: Int,
    override val price: Double,
    override val type: String
) : UsefulActivity {
}