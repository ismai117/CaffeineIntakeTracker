package main.screens.input.presentation

import main.screens.input.data.local.service.IntakeLocalService
import main.screens.input.data.repository.IntakeRepositoryImpl
import main.screens.input.domain.repository.IntakeRepository
import org.koin.dsl.module


val intakeModule = module {
    single { IntakeLocalService(get()) }
    single<IntakeRepository> { IntakeRepositoryImpl(get()) }
    factory { IntakeViewModel(get()) }
}