package com.mu.jan.sparknews.data.model

data class NewsResponse(var status: String? = null,
                        var totalResults: Int? = null,
                        var articles: List<ArticleResponse>? = null
                        )

