package com.rodriguez.driversdirectory.data.datasources

import com.google.gson.Gson
import com.rodriguez.driversdirectory.data.network.ApiResponse
import com.rodriguez.driversdirectory.data.network.DriversRoutesService
import com.rodriguez.driversdirectory.data.network.dtos.BaseResponseDto
import com.rodriguez.driversdirectory.data.network.dtos.DriversRoutesResponseDto
import okhttp3.ResponseBody
import java.net.UnknownHostException

interface IDriversRoutesDataSource {
    suspend fun retrieveDriversRoutes(): ApiResponse<DriversRoutesResponseDto>
}

data class DriverRoutersDataSource(private val rsService: DriversRoutesService) : IDriversRoutesDataSource {
    override suspend fun retrieveDriversRoutes(): ApiResponse<DriversRoutesResponseDto> {
        return runCatching {
            val response = rsService.retrieveDriversAndRoutes()
            if (response.isSuccessful) {
                ApiResponse.Success(response.body()!!)
            } else {
                processErrorResponse(response.errorBody())
            }

        }.getOrElse {
            when (it) {
                is UnknownHostException -> ApiResponse.ConnectionError
                else -> {
                    ApiResponse.Fail(exception = Exception(it.message))
                }
            }
        }
    }

    private fun processErrorResponse(response: ResponseBody?): ApiResponse.Fail {
        return Gson().fromJson(response?.toString(), BaseResponseDto::class.java)?.let {
            when (it.error.name) {
                usageLimitError -> ApiResponse.Fail(
                    exception = UsageLimitErrorException("User Api Limit Reached"))

                else -> {
                    ApiResponse.Fail(Exception())
                }
            }
        } ?: ApiResponse.Fail(Exception())
    }

    companion object {
        private const val usageLimitError = "usageLimitError"
    }
}

/**
 * Local Data Source to use in order to not reach API calls limit.
 */
class DriverRoutersLocalDataSource : IDriversRoutesDataSource {
    override suspend fun retrieveDriversRoutes(): ApiResponse<DriversRoutesResponseDto> {
        return ApiResponse.Success(Gson().fromJson("{\n" +
                "  \"drivers\": [\n" +
                "    {\n" +
                "      \"id\": \"9\",\n" +
                "      \"name\": \"Bruce Spruce\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"19\",\n" +
                "      \"name\": \"Andy Garcia\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"14\",\n" +
                "      \"name\": \"Jenny Lowe\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"13\",\n" +
                "      \"name\": \"Amber Shoe\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"6\",\n" +
                "      \"name\": \"Adam Stand\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"15\",\n" +
                "      \"name\": \"Ellis Roth\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"2\",\n" +
                "      \"name\": \"Chris Willis\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"16\",\n" +
                "      \"name\": \"Danika Johnson\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"3\",\n" +
                "      \"name\": \"Archie King\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"25\",\n" +
                "      \"name\": \"Monica Brown\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"routes\": [\n" +
                "    {\n" +
                "      \"id\": 1,\n" +
                "      \"type\": \"R\",\n" +
                "      \"name\": \"West Side Residential Route\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 2,\n" +
                "      \"type\": \"C\",\n" +
                "      \"name\": \"West Side Commercial Route\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 3,\n" +
                "      \"type\": \"I\",\n" +
                "      \"name\": \"West Side Industrial Route\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 4,\n" +
                "      \"type\": \"R\",\n" +
                "      \"name\": \"East Side Residential Route\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 5,\n" +
                "      \"type\": \"C\",\n" +
                "      \"name\": \"East Side Commercial Route\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 6,\n" +
                "      \"type\": \"I\",\n" +
                "      \"name\": \"East Side Industrial Route\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 7,\n" +
                "      \"type\": \"R\",\n" +
                "      \"name\": \"North Side Residential Route\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 8,\n" +
                "      \"type\": \"C\",\n" +
                "      \"name\": \"North Side Commercial Route\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 9,\n" +
                "      \"type\": \"I\",\n" +
                "      \"name\": \"North Side Industrial Route\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 10,\n" +
                "      \"type\": \"R\",\n" +
                "      \"name\": \"South Side Residential Route\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 11,\n" +
                "      \"type\": \"C\",\n" +
                "      \"name\": \"South Side Commercial Route\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 12,\n" +
                "      \"type\": \"I\",\n" +
                "      \"name\": \"South Side Industrial Route\"\n" +
                "    }\n" +
                "  ]\n" +
                "}", DriversRoutesResponseDto::class.java))
    }
}

class UsageLimitErrorException(message: String) : Exception(message)