package khobir.abdul.bareksa_test.data

import khobir.abdul.bareksa_test.data.mapper.Mapper
import khobir.abdul.bareksa_test.utils.ResultWrapper

fun <Response, Model> SimpleResponse<BaseModel<Response>>.mapToResult(mapper: Mapper<Response, Model>): ResultWrapper<Model> {
    return when {
        this.isSuccessful -> {
            val body = this.body()
            when {
                body != null -> {
                    if (body.code==2200) {
                        val mappedResult = mapper.mapFromResponse(body.data)
                        ResultWrapper.success(mappedResult)
                    } else {
                        ResultWrapper.fail(body.message, body.error)
                    }
                }
                else -> {
                    ResultWrapper.fail("Error", "Success but unknown failure")
                }
            }
        }
        else -> ResultWrapper.fail("Error", message = this.errorBody()?.string().orEmpty())
    }
}