package nktns.spacex.data.company

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import nktns.spacex.data.database.company.CompanyDatabaseModel
import nktns.spacex.util.Result
import javax.inject.Inject

class CompanyRepository @Inject constructor(
    private val remoteSource: CompanyRemoteSource,
    private val localSource: CompanyLocalSource,
) {
    fun fetchCompany(): Flow<Result<CompanyDatabaseModel>> = flow {

        if (localSource.isValid()) {
            emit(Result.Success(localSource.getCompany()))
        }

        when (val result = remoteSource.getCompany()) {

            is Result.Error -> emit(result)

            is Result.Success -> {
                emit(Result.Success(result.data.asDatabaseModel()))
                localSource.saveCompany(result.data.asDatabaseModel())
            }
        }
    }
}
