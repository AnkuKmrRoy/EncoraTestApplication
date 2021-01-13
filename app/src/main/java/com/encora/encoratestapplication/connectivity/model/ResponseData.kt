package com.encora.encoratestapplication.connectivity.model

import java.util.jar.Attributes

data class ResponseData(val feed:FeedDataModel) {
    data class FeedDataModel(val entry:ArrayList<Any>)
}