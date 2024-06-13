package main.screens.input.data.local.mapper

import main.screens.input.domain.model.Intake
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import main.screens.input.data.local.model.IntakeEntity


fun IntakeEntity.mapToDomainModel(): Intake {
    return Intake(
        id = this.id,
        name = this.name,
        mg = this.mg
    )
}

fun Intake.mapFromDomainModel(): IntakeEntity {
    return IntakeEntity(
        id = this.id,
        name = this.name,
        mg = this.mg
    )
}

fun List<IntakeEntity>.mapToDomainModelList():  List<Intake> {
    return this.map { it.mapToDomainModel() }
}

fun List<Intake>.mapFromDomainModelList():  List<IntakeEntity> {
    return this.map { it.mapFromDomainModel() }
}

fun Flow<List<IntakeEntity>>.mapToDomainModelList():  Flow<List<Intake>> {
    return this.map { it.mapToDomainModelList() }
}