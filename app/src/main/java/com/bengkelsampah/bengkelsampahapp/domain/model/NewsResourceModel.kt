package com.bengkelsampah.bengkelsampahapp.domain.model

data class NewsResourceModel(
    var author: String,
    var title: String,
    var description: String,
    var url: String,
    var urlToImage: String,
    var publishedAt: String,
    var content: String
){
    companion object{
        val dummyData = listOf(
            NewsResourceModel(
                author = "sodales",
                title = "ferri",
                description = "inani",
                url = "https://duckduckgo.com/?q=patrioque",
                urlToImage = "https://egsa.geo.ugm.ac.id/wp-content/uploads/sites/94/2019/10/WhatsApp-Image-2019-10-19-at-7.39.49-PM-540x360.jpeg",
                publishedAt = "potenti",
                content = "vivendo"
            ),
            NewsResourceModel(
                author = "sodales",
                title = "ferri",
                description = "inani",
                url = "https://duckduckgo.com/?q=patrioque",
                urlToImage = "https://egsa.geo.ugm.ac.id/wp-content/uploads/sites/94/2019/10/WhatsApp-Image-2019-10-19-at-7.39.49-PM-540x360.jpeg",
                publishedAt = "potenti",
                content = "vivendo"
            ),
            NewsResourceModel(
                author = "sodales",
                title = "ferri",
                description = "inani",
                url = "https://duckduckgo.com/?q=patrioque",
                urlToImage = "https://egsa.geo.ugm.ac.id/wp-content/uploads/sites/94/2019/10/WhatsApp-Image-2019-10-19-at-7.39.49-PM-540x360.jpeg",
                publishedAt = "potenti",
                content = "vivendo"
            ),
            NewsResourceModel(
                author = "sodales",
                title = "ferri",
                description = "inani",
                url = "https://duckduckgo.com/?q=patrioque",
                urlToImage = "https://egsa.geo.ugm.ac.id/wp-content/uploads/sites/94/2019/10/WhatsApp-Image-2019-10-19-at-7.39.49-PM-540x360.jpeg",
                publishedAt = "potenti",
                content = "vivendo"
            ),
            NewsResourceModel(
                author = "sodales",
                title = "ferri",
                description = "inani",
                url = "https://duckduckgo.com/?q=patrioque",
                urlToImage = "https://egsa.geo.ugm.ac.id/wp-content/uploads/sites/94/2019/10/WhatsApp-Image-2019-10-19-at-7.39.49-PM-540x360.jpeg",
                publishedAt = "potenti",
                content = "vivendo"
            ),
        )
    }
}
