package khobir.abdul.bareksa_test.data.mapper

interface Mapper<Response, Model> {
    fun mapFromResponse(response: Response): Model
}