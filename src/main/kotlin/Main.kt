fun main() {
    val notesManager = Notes()
    val note1 = Note(0, "", "")
    notesManager.add(note1)
    println()

}

data class Note(
    var noteId: Int,  // айди заметки
    val title: String, // заголовок заметки
    val text: String, // текст заметки
    val comments: Comment = Comment() // комментарии к записи
)

data class Comment(
    var count: Int = 0, // — количество комментариев;
    val canPost: Boolean = false, //— информация о том, может ли текущий пользователь комментировать запись (1 — может, 0 — не может);
    val groupsCanPost: Boolean = false, //— информация о том, могут ли сообщества комментировать запись;
    val canClose: Boolean = false, //— может ли текущий пользователь закрыть комментарии к записи;
    val canOpen: Boolean = false  //— может ли текущий пользователь открыть комментарии к записи.
)

data class CommentDelete(
    var count: Int = 0, // — количество комментариев;
    val canPost: Boolean = false, //— информация о том, может ли текущий пользователь комментировать запись (1 — может, 0 — не может);
    val groupsCanPost: Boolean = false, //— информация о том, могут ли сообщества комментировать запись;
    val canClose: Boolean = false, //— может ли текущий пользователь закрыть комментарии к записи;
    val canOpen: Boolean = false  //— может ли текущий пользователь открыть комментарии к записи.
)

public interface MutableCollections<E> : MutableCollection<E> {
    fun add(note: Note): Boolean//Создает новую заметку у текущего пользователя.
    fun createComment(comment: Comment): Boolean// Добавляет новый комментарий к заметке.
    fun delete(noteId: Int): Boolean//Удаляет заметку текущего пользователя.
    fun deleteComment(count: Int): Boolean// Удаляет комментарий к заметке.
    fun edit(noteId: Int, editNote: Note): Boolean// Редактирует заметку текущего пользователя.
    fun editComment(count: Int, editComment: Comment): Boolean// Редактирует указанный комментарий у заметки.
    fun get() //Возвращает список заметок, созданных пользователем.
    fun getById(noteId: Int ) : Note  //Возвращает заметку по её id.
    fun getComments(count: Int ) : Comment// Возвращает список комментариев к заметке.
    fun restoreComment(count: E) //Восстанавливает удалённый комментарий.
}

class Notes : MutableCollections<Note> {
    private val notes = mutableListOf<Note>()
    private val comments = mutableListOf<Comment>()
    private var iNote = 0
    private var iCom = 0

    override fun add(note: Note): Boolean {
        val result = notes.add(note)
        note.noteId = iNote++
        return result
    }

    override fun createComment(comment: Comment): Boolean {
        val result = comments.add(comment)
        comment.count = iCom++
        return result
    }

    override fun delete(noteId: Int): Boolean {
        val iterator = notes.iterator()
        while (iterator.hasNext()) {
            val note = iterator.next()
            if (note.noteId == noteId) {
                iterator.remove()
                return true
            }
        }
        return false
    }

    override fun deleteComment(count: Int): Boolean {
        val iterator = comments.iterator()
        while (iterator.hasNext()) {
            val comment = iterator.next()
            if (comment.count == count) {
                iterator.remove()
                return true
            }
        }
        return false
    }

    override fun editComment(count: Int, editComment: Comment): Boolean {
        val iterator = comments.iterator()
        while (iterator.hasNext()) {
            val comment = iterator.next()
            if (comment.count == count) {
                iterator.remove()
                comments.add(editComment.copy(count = count))
                return true
            }
        }
        return false
    }

    override fun edit(noteId: Int, editNote: Note): Boolean {
        val iterator = notes.iterator()
        while (iterator.hasNext()) {
            val note = iterator.next()
            if (note.noteId == noteId) {
                iterator.remove()
                notes.add(editNote.copy(noteId = noteId))
                return true
            }
        }
        return false
    }

    override fun getById(noteId: Int ) : Note{
        val iterator = notes.iterator()
        while (iterator.hasNext()) {
            val note = iterator.next()
            if (note.noteId == noteId) {
                return note
            }
        }
        throw PostNotFoundException("No note with id $noteId")
    }

    override fun getComments(count: Int ) : Comment{
        val iterator = comments.iterator()
        while (iterator.hasNext()) {
            val comment = iterator.next()
            if (comment.count == count) {
                return comment
            }
        }
        throw PostNotFoundException("No comment with id $count")
    }

    }


class PostNotFoundException (message: String) :RuntimeException(message)




