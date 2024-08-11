package com.example.game_list.domain.usescases

abstract class BaseUsesCases<In,Out> {
    abstract suspend fun execute(input: In) : Out
}