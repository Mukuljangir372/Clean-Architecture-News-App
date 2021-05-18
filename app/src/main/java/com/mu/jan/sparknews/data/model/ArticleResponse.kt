package com.mu.jan.sparknews.data.model

data class ArticleResponse(var source: SourceResponse? = null,
                           var author: String? = null,
                           var title: String? = null,
                           var description: String? = null,
                           var url: String? = null,
                           var urlToImage: String? = null,
                           var publishedAt: String? = null,
                           var content: String? = null
)


