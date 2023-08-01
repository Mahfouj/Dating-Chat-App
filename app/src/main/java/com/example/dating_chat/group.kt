package com.example.dating_chat

data class group(
    val groupName: String = "",
    val groupImage: String = "",
    val groupBio: String = "",
    val groupAdmin: String = "",
    val groupID: String = "",
    val groupMember: MutableList<String> = mutableListOf()
)
