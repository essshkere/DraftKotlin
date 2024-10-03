fun main(){
    val noteService = ServiceCRUD<Note>()
    val commentService = ServiceCRUD<Comment>()
    val addedNote = noteService.create(Note())
    val addedComment = commentService.create(Comment())
    val addedNote2 = noteService.create(Note())
    val addedComment2 = commentService.create(Comment())
}

data class Note(
    var id: Int = 0,
    var text: String = "0",
    val comment: Comment = Comment(),
    var isDeleted: Boolean = false
)


data class Comment(
    var id: Int = 0,
    val noteId: Int = 0,
    var text: String = "0",
    var isDeleted: Boolean = false
)


interface Service<T> {
    fun create(item: T): T
    fun read(id: Int): T?
    fun update(item: T): T?
    fun delete(id: Int): Boolean
}


class ServiceCRUD<T>(private val items: MutableList<T> = mutableListOf()) : Service<T> {
    private var i = 1

    override fun create(item: T): T {
        if (item is Note) {
            item.id = i++
        } else if (item is Comment) {
            item.id = i++
        }
        items.add(item)
        return item
    }

    override fun read(id: Int): T? {
        return items.find {
            if (it is Note) {
                it.id == id
            } else if (it is Comment) {
                it.id == id
            } else {
                false
            }
        }
    }

    override fun update(item: T): T? {
        val index =
            items.indexOfFirst { (it as? Note)?.id == (item as? Note)?.id || (it as? Comment)?.id == (item as? Comment)?.id }
        if (index != -1) {
            items[index] = item
            return item
        }
        return null
    }


    override fun delete(id: Int): Boolean {
        val item = read(id)
        if (item != null) {
            if (item is Note) {
                item.isDeleted = true
                return true
            } else if (item is Comment) {
                item.isDeleted = true
                return true
            }
        }
        return false
    }
}
