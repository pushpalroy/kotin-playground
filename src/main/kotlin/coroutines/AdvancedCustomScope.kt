package coroutines

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

data class Article(val source: String, val content: String)

class NewsSourceScope(context: CoroutineContext) : CoroutineScope {
    private val job = Job() + context

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        println("Error: $throwable")
    }

    override val coroutineContext: CoroutineContext
        get() = job + exceptionHandler
}

suspend fun fetchArticlesFromSource(source: String): List<Article> {
    delay(1000) // Simulate network call delay
    if (source == "source3") {
        throw Exception("Error parsing articles from $source")
    }
    return listOf(Article(source, "Article content from $source"))
}

suspend fun fetchAllArticles(scope: NewsSourceScope) {
    val sources = listOf("source1", "source2", "source3", "source4", "source5", "source6")
    sources.forEach { source ->
        scope.launch {
            fetchArticlesFromSource(source)
            println("Fetched articles from source: $source")
        }.join()
    }
}

fun main() = runBlocking {
    val scope = NewsSourceScope(Dispatchers.IO)

    try {
        fetchAllArticles(scope)
    } catch (e: Exception) {
        println("Error fetching all articles: $e")
    } finally {
        scope.cancel()
    }
}
